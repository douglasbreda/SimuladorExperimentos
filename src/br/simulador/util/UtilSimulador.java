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
     * @param limite - Define qual é o limite para gerar o número randômico
     * @return 
     */
    public static int getNumeroRandomico(int limite){
        Random random = new Random();
        
        return random.nextInt(limite);
    }
}
