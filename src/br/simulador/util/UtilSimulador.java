/**
 * Classe com funções úteis utilizados em vários locais da aplicação
 */
package br.simulador.util;

import java.util.Random;

/**
 *
 * @author Douglas
 */
public final class UtilSimulador {

    /**
     * Retorna um número randômico
     *
     * @param limite - Define qual é o limite para gerar o número randômico
     * @return
     */
    public static int getNumeroRandomico(int limite) {
        Random random = new Random();

        return random.nextInt(limite);
    }

    /**
     * Centralização do método para exibir mensagens no console
     *
     * @param mensagem
     */
    public static void setLog(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Método para converter um valor em Inteiro
     *
     * @param valor
     * @return
     */
    public static int toInt(Object valor) {
        try {
            return Integer.parseInt(String.valueOf(valor));
        } catch (Exception ex) {
            setLog("Ocorreu um erro ao converter para inteiro: " + ex.getMessage());
            return 0;
        }
    }

    /**
     * Método para converter um valor em Double
     *
     * @param valor
     * @return
     */
    public static double toDouble(Object valor) {
        try {
            return Double.parseDouble(String.valueOf(valor));
        } catch (Exception ex) {
            setLog("Ocorreu um erro ao converter para double" + ex.getMessage());
            return 0;
        }
    }

    /**
     * Método para converter um valor em Boolean
     * @param valor
     * @return 
     */
    public static boolean toBoolean(Object valor) {
        try {
            return Boolean.parseBoolean(String.valueOf(valor));
        } catch (Exception ex) {
            setLog("Ocorreu um erro ao converter para boolean" + ex.getMessage());
            return false;
        }
    }
}
