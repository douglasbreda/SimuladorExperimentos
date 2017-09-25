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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public final class GerenciadorExecucao {
    
    private static ArrayList<IAgente> listaAgentes = null;
    
    private static PainelBase painelBase = null;
    
    private static GerenciadorExecucao instance = null;

    /**
     * Retorna uma instância do gerenciador da simulação
     *
     * @return
     */
    public static GerenciadorExecucao getInstance() {
        if (instance == null) {
            instance = new GerenciadorExecucao();
        }
        
        return instance;
    }

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
    public void executarMetodo(String nome_metodo, Object... parametros) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        
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
     *
     * @param listaAgentes
     */
    public void setListaAgentes(ArrayList<IAgente> listaAgentes) {
        GerenciadorExecucao.listaAgentes = listaAgentes;
    }

    /**
     * Centralização do método de criação de agentes
     *
     * @param numero_agentes
     * @param aleatorio
     */
    public void criar_agentes(int numero_agentes, boolean aleatorio) throws ErroExecucaoBiblioteca, InterruptedException {
        
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
        setListaAgentes(getPainelBase().getListaAgentes());
    }

    /**
     * Método interno apenas para centralização das mensagens de console
     */
    private static void setLog(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Controle interno para buscar o painel onde está ocorrendo a simulação
     *
     * @return
     */
    private PainelBase getPainelBase() {
        if (painelBase == null) {
            painelBase = GerenciadorInicializacao.getInstance().getAmbienteSimulacao();
        }
        
        return painelBase;
    }

    /**
     * Retorna o número de agentes da simulação
     *
     * @return
     */
    public int contar_agentes() {
        return listaAgentes.size();
    }

    /**
     * Retorna a média de uma parâmetro do agente
     *
     * @param nome_parametro
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public double media(String nome_parametro) throws ErroExecucaoBiblioteca, InterruptedException {
        
        double media = 0;
        
        if (listaAgentes.size() > 0) {
            for (IAgente agente : listaAgentes) {
                media += agente.retornar_atributo_real(nome_parametro);
            }
            
            media = media / listaAgentes.size();
        }
        
        return media;
    }

    /**
     * Adiciona atributos/parametros a todos os agentes da lista
     *
     * @param nome
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void adicionar_atributo_agentes(String nome) throws ErroExecucaoBiblioteca, InterruptedException {
        for (IAgente agente : listaAgentes) {
            agente.criar_parametro(nome);
        }
    }

    /**
     * Atribui um valor específico a um agente da lista
     *
     * @param nome_atributo
     * @param valor
     * @param id_agente
     */
    public void definir_valor_atributo(String nome_atributo, String valor, int id_agente) throws ErroExecucaoBiblioteca, InterruptedException {
        
        for (IAgente agente : listaAgentes) {
            if (agente.retornar_id() == id_agente) {
                agente.definir_valor_atributo(nome_atributo, valor);
            }
        }
    }
}
