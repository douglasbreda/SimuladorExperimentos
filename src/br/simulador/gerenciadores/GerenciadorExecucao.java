/**
 * Gerenciador para controlar a execução das Threads para cada agente
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.Agente;
import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.componentes.PainelBase;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public class GerenciadorExecucao {

    private static ArrayList<IAgente> listaAgentes = null;
    
    private static PainelBase painelBase = null;

    /**
     * Executa os métodos para todos os agentes da lista através de reflection
     *
     * @param nome_metodo
     * @param parametros
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void executarMetodo(String nome_metodo, Object... parametros) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        if (listaAgentes != null) {

            Object classe = Class.forName(Agente.class.getName()).newInstance();

            for (IAgente agente : listaAgentes) {

                Method m = classe.getClass().getMethod(nome_metodo);
                System.out.println(m.invoke(agente, parametros));
            }
        }
    }

    /**
     * Atualiza a lista de agentes para as execuções dos métodos
     * @param listaAgentes 
     */
    public static void setListaAgentes(ArrayList<IAgente> listaAgentes) {
        GerenciadorExecucao.listaAgentes = listaAgentes;
    }
    
    /**
     * Centralização do método de criação de agentes
     * @param numero_agentes
     * @param aleatorio 
     */
    public static void criar_agentes(int numero_agentes, boolean aleatorio) throws ErroExecucaoBiblioteca, InterruptedException{
        
        int coordenadaX = 0;
        int coordenadaY = 0;
        int id = 0;
        
        for (int i = 0; i < numero_agentes; i++) {
                    
            coordenadaX = UtilSimulador.getNumeroRandomico(800);
            coordenadaY = UtilSimulador.getNumeroRandomico(600);
            
            IAgente agente = new Agente(coordenadaX, coordenadaY, ++id);
            
            setLog("------------------------------------------");
            
            setLog("Agente: " + agente.retornar_id());
            setLog("X: " + agente.retornar_coordenada_X());
            setLog("Y: " + agente.retornar_coordenada_Y());
            
            setLog("------------------------------------------");
            
            getPainelBase().adicionar_agente_lista(agente);
        }

        getPainelBase().criar_posicoes_agentes();
    }
    
    /**
     * Método interno apenas para centralização das mensagens de console
     */
    private static void setLog(String mensagem){
        System.out.println(mensagem);
    }

    /**
     * Controle interno para buscar o painel onde está ocorrendo a simulação
     * @return 
     */
    private static PainelBase getPainelBase() {
        if(painelBase == null)
            painelBase = GerenciadorInicializacao.getInstance().getAmbienteSimulacao();
        
        return painelBase;
    }
    
    
}
