/**
 * Classe para transformar em código java o código java de cada biblioteca
 */
package br.simulador.gerador;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.univali.portugol.nucleo.CompiladorSimulador;
import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.SimuladorPrograma;
import br.univali.portugol.nucleo.analise.ResultadoAnalise;
import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.execucao.gerador.GeradorCodigoJava;
import br.univali.portugol.nucleo.mensagens.ErroAnalise;
import br.univali.ps.nucleo.Caminhos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 *
 * @author Douglas
 */
public class GeradorCodigoJavaSimulador {

    long idPrograma = System.currentTimeMillis();
    private static final File DIRETORIO_TEMPORARIO = new File(System.getProperty("java.io.tmpdir"));
    private static final File DIRETORIO_COMPILACAO = new File(DIRETORIO_TEMPORARIO, "portugol");
    private static final String NOME_PACOTE = "programas";
    private static final File DIRETORIO_PACOTE = new File(DIRETORIO_COMPILACAO, NOME_PACOTE);
    private ResultadoAnalise resultadoAnalise;
    private String classPath;
    private String caminhoJavac;
    private static CompiladorSimulador compilador;

    /**
     * Método que gera e compila o código Portugol para Java, retornando uma
     * instância da classe SimuladorPrograma
     *
     * @param asa
     * @return
     * @throws ErroCompilacao
     */
    public SimuladorPrograma gerar_codigo_java(ASAPrograma asa) throws ErroCompilacao {
        long idPrograma = System.currentTimeMillis();

        String nomeClasse = "Programa".concat(String.valueOf(idPrograma));
        String nomeArquivoJava = nomeClasse.concat(".java");
        String nomeArquivoClass = nomeClasse.concat(".class");
        classPath = GerenciadorExecucao.getInstance().getPlugin().getUtilizadorPlugins().obterClassPathParaCompilacao();
        caminhoJavac = Caminhos.obterCaminhoExecutavelJavac();
        compilador = new CompiladorSimulador();

        DIRETORIO_PACOTE.mkdirs();

        File arquivoJava = new File(DIRETORIO_PACOTE, nomeArquivoJava);
        File arquivoClass = new File(DIRETORIO_PACOTE, nomeArquivoClass);

        try (PrintWriter writerArquivoJava = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoJava), Charset.forName("utf-8"))))) {

            GeradorCodigo gerador = new GeradorCodigo();
            GeradorCodigoJava.Opcoes opcoes = new GeradorCodigoJava.Opcoes(false, false, false);
            gerador.gera(asa, writerArquivoJava, nomeClasse, opcoes);
            writerArquivoJava.flush();

            return compilador.compilarJava(nomeClasse, arquivoJava, DIRETORIO_COMPILACAO, resultadoAnalise, classPath, caminhoJavac);
        } catch (final IOException | ExcecaoVisitaASA ex) {
            resultadoAnalise.adicionarErro(new ErroAnalise() {
                @Override
                protected String construirMensagem() {
                    return ex.getMessage();
                }
            });

            throw new ErroCompilacao(resultadoAnalise);
        } finally {
//            arquivoJava.delete();
//            arquivoClass.delete();
        }
    }
}
