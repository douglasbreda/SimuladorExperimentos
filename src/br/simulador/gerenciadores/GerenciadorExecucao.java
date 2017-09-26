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
     * Sobrecarga para os métodos que possuem strings como tipos de parâmetros
     * pois eles são maioria na biblioteca
     *
     * @param nome_metodo
     * @param parametros
     * @param numero_parametros
     */
    public void executarMetodo(String nome_metodo, int numero_parametros, Object... parametros) {
        Class[] tipo_parametros = new Class[numero_parametros];

        for (int i = 0; i < numero_parametros; i++) {
            tipo_parametros[i] = String.class;
        }

        executarMetodo(nome_metodo, tipo_parametros, parametros);
    }

    /**
     * Executa os métodos para todos os agentes da lista através de reflection
     *
     * @param nome_metodo
     * @param parametros
     * @param tipo_parametros
     * @throws IllegalArgumentException
     */
    public void executarMetodo(String nome_metodo, Class[] tipo_parametros, Object... parametros) {

        if (listaAgentes != null) {

            try {
                Object classe = Class.forName(Agente.class.getName()).newInstance();

                for (IAgente agente : listaAgentes) {

                    Method m = classe.getClass().getMethod(nome_metodo, tipo_parametros);
                    System.out.println(m.invoke(agente, parametros));
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
                Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
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

        System.out.println("A média é: " + media);
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
     * Define um valor a um atributo por agente 
     * @param nome_atributo
     * @param valor
     * @param id ID do agente que terá o atributo alterado
     */
    public void definir_valor_atributo_por_agente(String nome_atributo, String valor, int id) {
        try {
            for (IAgente agente : listaAgentes) {

                if (agente.retornar_id() == id) {
                    agente.definir_valor_atributo(nome_atributo, valor);
                }
            }
        } catch (ErroExecucaoBiblioteca ex) {
            Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
