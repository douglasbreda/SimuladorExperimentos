/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.gerenciadores;

import br.simulador.gerador.SimuladorPrograma_;
import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.analise.ResultadoAnalise;
import br.univali.portugol.nucleo.analise.semantica.erros.ErroCodigoNaoAlcancavel;
import br.univali.portugol.nucleo.analise.semantica.erros.ErroVariavelPodeNaoTerSidoInicializada;
import br.univali.portugol.nucleo.mensagens.ErroAnalise;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DaemonExecutor;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.LogOutputStream;
import org.apache.commons.exec.PumpStreamHandler;

/**
 *
 * @author Douglas
 */
public class GerenciadorCompilacao {

    public SimuladorPrograma_ compilarJava(String nomeClasse, File arquivoJava, File diretorioCompilacao,
            ResultadoAnalise resultadoAnalise, String classPath, String caminhoJavac) throws ErroCompilacao {

        if (classPath == null) {
            throw new IllegalArgumentException("ClassPath não pode ser nulo!");
        }

        CollectingLogOutputStream outStream = new CollectingLogOutputStream();
        CollectingLogOutputStream errStream = new CollectingLogOutputStream();

        try {
            /* 
             * Utilizando a biblioteca Apache Commons Exec para gerenciar o processo do Java. 
             * Esta biblioteca resolve automaticamente o problema de caminhos com espaços.
             */

            Map paths = new HashMap();

            paths.put("classpath", classPath + ".");
            paths.put("arquivoJava", arquivoJava);

            CommandLine linhaComando = new CommandLine(caminhoJavac);

            linhaComando.addArgument("-encoding");
            linhaComando.addArgument("utf8");
            linhaComando.addArgument("-cp");
            linhaComando.addArgument("${classpath}");
            linhaComando.addArgument("${arquivoJava}");
            linhaComando.setSubstitutionMap(paths);

            DefaultExecutor executor = new DaemonExecutor();

            executor.setStreamHandler(new PumpStreamHandler(outStream, errStream));
            executor.setStreamHandler(new PumpStreamHandler(outStream, errStream));
            executor.setExitValue(0);

            int exitValue = executor.execute(linhaComando);

            if (exitValue != 0 || !compilouSemErros(errStream)) {
                resultadoAnalise.adicionarErro(new ErroAnaliseNaCompilacao("Erro na compilação!"));
                throw new ErroCompilacao(resultadoAnalise);
            }
        } catch (ExecuteException ex) {
            resultadoAnalise.adicionarErro(new ErroAnaliseNaCompilacao("Erro na compilação!"));

            for (String mensagemErro : errStream.getLines()) {
                if (mensagemErro.contains("error: unreachable statement")) {
                    resultadoAnalise.adicionarErro(new ErroCodigoNaoAlcancavel());
                    break;
                } else if (mensagemErro.contains("might not have been initialized")) {
                    resultadoAnalise.adicionarErro(new ErroVariavelPodeNaoTerSidoInicializada(mensagemErro));
                    break;
                }
                resultadoAnalise.adicionarErro(new ErroAnaliseNaCompilacao(mensagemErro));
            }

            throw new ErroCompilacao(resultadoAnalise);
        } catch (final IOException ex) {
            resultadoAnalise.adicionarErro(new ErroAnaliseNaCompilacao(ex.getMessage()));

            throw new ErroCompilacao(resultadoAnalise);
        }

        return carregaProgramaCompilado(diretorioCompilacao, nomeClasse, classPath, resultadoAnalise);
    }

    private class ErroAnaliseNaCompilacao extends ErroAnalise {

        private final String mensagem;

        public ErroAnaliseNaCompilacao(String mensagem) {
            this.mensagem = mensagem;
        }

        @Override
        protected String construirMensagem() {
            return mensagem;
        }

    }

    public class CollectingLogOutputStream extends LogOutputStream {

        private final List<String> lines = new LinkedList<>();

        @Override
        protected void processLine(String line, int level) {
            lines.add(line);
        }

        public List<String> getLines() {
            return lines;
        }
    }

    private boolean compilouSemErros(CollectingLogOutputStream errStream) {
        for (String linha : errStream.getLines()) {
            if (linha.contains("error:")) {
                System.out.println(linha);
                return false; // encontrou um erro
            }
        }

        return true;
    }

    private SimuladorPrograma_ carregaProgramaCompilado(File diretorioCompilacao, String nomeClasseCompilada, String classpath, ResultadoAnalise resultadoAnalise) throws ErroCompilacao {
        try {
            String classpathSeparator = ";";

            if (!classpath.contains(classpathSeparator)) {
                classpathSeparator = ":";
            }

            String[] paths = classpath.split(classpathSeparator);
            List<URL> classpathUrls = new ArrayList<>();

            for (String path : paths) {
                //JOptionPane.showMessageDialog(null, path);
                classpathUrls.add(new File(path).toURI().toURL());
            }

            URLClassLoader systemLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL[] systemUrls = systemLoader.getURLs();

            for (URL systemUrl : systemUrls) {
                if (classpathUrls.contains(systemUrl)) {
                    classpathUrls.remove(systemUrl);
                }
            }

            classpathUrls.add(diretorioCompilacao.toURI().toURL());

            ClassLoader prevCl = Thread.currentThread().getContextClassLoader();
            
            ClassLoader classLoader = URLClassLoader.newInstance(classpathUrls.toArray(new URL[]{}), prevCl);

            Class<?> loadedClass = classLoader.loadClass("programas".concat(".").concat(nomeClasseCompilada));
                        
            SimuladorPrograma_ programa = (SimuladorPrograma_) loadedClass.newInstance();
            
            return programa;

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | MalformedURLException | RuntimeException ex) {
            resultadoAnalise.adicionarErro(new ErroAnaliseNaCompilacao(ex.getMessage()));

            throw new ErroCompilacao(resultadoAnalise);
        }
    }
}
