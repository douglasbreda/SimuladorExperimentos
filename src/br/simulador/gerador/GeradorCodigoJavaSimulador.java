/**
 * Classe para transformar em código java o código java de cada biblioteca
 */
package br.simulador.gerador;

import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.execucao.gerador.GeradorCodigoJava;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void gerar_codigo_java(ASAPrograma asa) {
        try {
            long idPrograma = System.currentTimeMillis();

            String nomeClasse = "Programa".concat(String.valueOf(idPrograma));
            String nomeArquivoJava = nomeClasse.concat(".java");
            String nomeArquivoClass = nomeClasse.concat(".class");

            DIRETORIO_PACOTE.mkdirs();

            File arquivoJava = new File(DIRETORIO_PACOTE, nomeArquivoJava);
            File arquivoClass = new File(DIRETORIO_PACOTE, nomeArquivoClass);

            try (PrintWriter writerArquivoJava = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoJava), Charset.forName("utf-8"))))) {

                GeradorCodigo gerador = new GeradorCodigo();
                GeradorCodigoJava.Opcoes opcoes = new GeradorCodigoJava.Opcoes(false, false, false);
                gerador.gera(asa, writerArquivoJava, nomeClasse, opcoes);
                writerArquivoJava.flush();
            } catch (ExcecaoVisitaASA | IOException ex) {
                Logger.getLogger(GeradorCodigoJavaSimulador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
