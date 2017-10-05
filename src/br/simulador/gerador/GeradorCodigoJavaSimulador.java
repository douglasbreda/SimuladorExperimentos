/**
 * Classe para transformar em código java o código java de cada biblioteca
 */
package br.simulador.gerador;

import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.execucao.gerador.GeradorCodigoJava;
import java.io.BufferedWriter;
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

    String nomeClasse = "Programa".concat(String.valueOf(idPrograma));
    String nomeArquivoJava = nomeClasse.concat(".java");
    String nomeArquivoClass = nomeClasse.concat(".class");

    public void gerar_codigo_java(ASAPrograma asa) {
        try (PrintWriter writerArquivoJava = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\Desenvolvimento\\TTC II\\Plugin Experimentos\\Testes"), Charset.forName("utf-8"))))) {
            GeradorCodigoJava gerador = new GeradorCodigoJava();
            GeradorCodigoJava.Opcoes opcoes = new GeradorCodigoJava.Opcoes(true, true, true);
            gerador.gera(asa, writerArquivoJava, nomeClasse, opcoes);
            writerArquivoJava.flush();
        } catch (ExcecaoVisitaASA ex) {
            Logger.getLogger(GeradorCodigoJavaSimulador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeradorCodigoJavaSimulador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
