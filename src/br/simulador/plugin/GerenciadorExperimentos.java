/**
 * Classe que tem o objetivo de controlar algumas ações da simulação
 */
package br.simulador.plugin;

import br.simulador.inicializador.Inicializador;

/**
 *
 * @author Douglas
 */
public class GerenciadorExperimentos {
    
    private static Inicializador inicializador = null;
    
    /**
     * Cria uma instância da simulação e abre a tela 
     */
    public static void criarInstanciaSimulacao(){
        if(inicializador == null){
            inicializador = new Inicializador();
            inicializador.run();
        }
    }
}
