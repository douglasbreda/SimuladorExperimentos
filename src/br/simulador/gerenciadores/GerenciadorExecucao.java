/**
 * Gerenciador para controlar a execução das Threads para cada agente
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.Agente;
import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.SimuladorPrograma;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import br.univali.ps.plugins.base.Plugin;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Douglas
 */
public final class GerenciadorExecucao {

    private static ArrayList<IAgente> listaAgentes = null;

    private static GerenciadorExecucao instance = null;

    private static IAgente agenteAtual;

    private static Plugin plugin;

    private static SimuladorPrograma simuladorPrograma = null;

    private static boolean executando = false;
    
    private static boolean ambienteInicializado = false;
    
    //Define os passos da simulação
    //Ou seja, cada vez que um ciclo de execução é completado
    private static int ticks = 0;

    public void inicializar_ambiente() throws ErroExecucaoBiblioteca, InterruptedException, InvocationTargetException, ErroExecucao {
        UtilSimulador.setLog("Vai inicializar o ambiente");
        ambienteInicializado = true;
        GerenciadorInterface.getInstance().inicializarTela();
        UtilSimulador.setLog("Inicializou o ambiente");
    }

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
     * @return
     * @throws IllegalArgumentException
     */
    public Object executarMetodo(String nome_metodo, Class[] tipo_parametros, Object... parametros) {

        if (listaAgentes != null) {

            try {
                Object classe = Class.forName(Agente.class.getName()).newInstance();

                for (IAgente agente : listaAgentes) {

                    Method m = classe.getClass().getMethod(nome_metodo, tipo_parametros);
                    return m.invoke(agente, parametros);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
                Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return new Object();
    }

    /**
     * Chamada do método de execução dos métodos do agente que não possuem
     * parâmetros
     *
     * @param nome_metodo
     * @throws IllegalArgumentException
     */
    public Object executarMetodo(String nome_metodo) {

        if (listaAgentes != null) {

            try {
                Object classe = Class.forName(Agente.class.getName()).newInstance();

                for (IAgente agente : listaAgentes) {

                    Method m = classe.getClass().getMethod(nome_metodo);
                    return m.invoke(agente, new Object[0]);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
                Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Object();
    }

    /**
     * Adiciona um agente a lista de agentes
     *
     * @param agente
     */
    public void addAgente(IAgente agente) {
        if (listaAgentes == null) {
            listaAgentes = new ArrayList<>();
        }

        listaAgentes.add(agente);
    }

    /**
     * Centralização do método de criação de agentes
     *
     * @param numero_agentes
     * @param aleatorio
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void criar_agentes(int numero_agentes, boolean aleatorio) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {

        int coordenadaX = 0;
        int coordenadaY = 0;
        int id = 0;
        int minX = GerenciadorInterface.getInstance().retorna_limite_minimo_borda_X();
        int minY = GerenciadorInterface.getInstance().retorna_limite_minimo_borda_Y();
        int maxX = GerenciadorInterface.getInstance().retorna_limite_maximo_borda_X();
        int maxY = GerenciadorInterface.getInstance().retorna_limite_maximo_borda_Y();
        int velocidade = 0;
        int valor_fixo_x = (maxX / 2);
        int valor_fixo_y = (maxY / 2);

//        int valor_fixo_x = 446;
//        int valor_fixo_y = 267;
        for (int i = 0; i < numero_agentes; i++) {

            if (aleatorio) {
                coordenadaX = UtilSimulador.getNumeroRandomico(minX, maxX);
                coordenadaY = UtilSimulador.getNumeroRandomico(minY, maxY);
                velocidade = UtilSimulador.getNumeroRandomico(5);
            } else {
                coordenadaX = valor_fixo_x;
                coordenadaY = valor_fixo_y;
            }

            IAgente agente = new Agente(coordenadaX, coordenadaY, ++id, velocidade);

            UtilSimulador.setLog("------------------------------------------");

            UtilSimulador.setLog("Agente: " + agente.retornar_id());
            UtilSimulador.setLog("X: " + agente.retornar_coordenada_X());
            UtilSimulador.setLog("Y: " + agente.retornar_coordenada_Y());

            UtilSimulador.setLog("------------------------------------------");

            addAgente(agente);
            GerenciadorInterface.getInstance().renderizar_tela_parcial();
        }

//        GerenciadorInterface.getInstance().atualizar_total_agentes(listaAgentes.size());
    }

    /**
     * Retorna o número de agentes da simulação
     *
     * @return
     */
    public int contar_agentes() {
        UtilSimulador.setLog("Contou os agentes");
        return getListaAgentes().size();
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

        UtilSimulador.setLog("A média é: " + media);
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
            agente.criar_atributo(nome);
        }
    }

    /**
     * Define um valor a um atributo por agente
     *
     * @param nome_atributo
     * @param valor
     * @param id ID do agente que terá o atributo alterado
     */
    public void definir_valor_atributo_por_agente(String nome_atributo, String valor, int id) {
        try {
            for (IAgente agente : listaAgentes) {

                if (agente.retornar_id() == id) {
                    agente.definir_valor_atributo(nome_atributo, valor, id);
                }
            }
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpa a lista de agentes
     */
    public void limpar_tudo() {
        UtilSimulador.setLog("Número de agentes atuais: " + listaAgentes.size());

        listaAgentes.clear();

        UtilSimulador.setLog("Após limpeza: " + listaAgentes.size());
    }

    /**
     * Retorna a lista de agentes da aplicação que estão visiveis
     *
     * @return
     */
    public ArrayList<IAgente> getListaAgentes() {
        ArrayList<IAgente> listaRetorno = null;

        if (listaAgentes != null) {
            List<IAgente> listaNova = listaAgentes.stream().filter(x -> x.esta_visivel()).collect(Collectors.toList());

            if (listaRetorno == null) {
                listaRetorno = new ArrayList<>();
            } else {
                listaRetorno.clear();
            }

            listaRetorno.addAll(listaNova);
        }

        return listaRetorno;
    }

    /**
     * Retorna o número de agentes que contém uma determinada cor passada por
     * parâmetro
     *
     * @param cor
     * @return
     */
    public int agentes_com_cor(int cor) {
        int numero_agentes = 0;
        if (listaAgentes != null) {
            numero_agentes = UtilSimulador.toInt(listaAgentes.stream()
                    .filter(agente -> comparar_cor_agente(agente, cor))
                    .count());

        }

        return numero_agentes;
    }

    /**
     * Método criado para disparar exceção dentro de um filter
     *
     * @param agente
     * @param cor
     * @return
     */
    private boolean comparar_cor_agente(IAgente agente, int cor) {
        try {
            return agente.retornar_cor_agente() == cor;
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int agentes_em(int coordenadaX, int coordenadaY) throws InterruptedException, ErroExecucao {
        return agentes_em(coordenadaX, coordenadaY, agenteAtual.retornar_altura_agente(), agenteAtual.retornar_largura_agente());
    }

    /**
     * Encontra quantos agentes estão em um determinado retalho
     *
     * @param coordenadaX
     * @param coordenadaY
     * @param altura
     * @param largura
     * @return
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public int agentes_em(int coordenadaX, int coordenadaY, int altura, int largura) throws InterruptedException, ErroExecucao {
        int numero_agentes = 0;

        Retalho retalho = GerenciadorInterface.getInstance().get_retalho(coordenadaX, coordenadaY, altura, largura);

        if (retalho != null) {
            retalho.set_cor(2);
            numero_agentes = GerenciadorInterface.getInstance().buscar_numero_agentes(retalho);
            GerenciadorInterface.getInstance().renderizar_tela();
        }

        return numero_agentes;
    }

    /**
     * Encontra o retalho atual do agente
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public Retalho meu_retalho() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        Retalho retalho = GerenciadorInterface.getInstance().get_retalho(agenteAtual.retornar_coordenada_X(), agenteAtual.retornar_coordenada_Y(), agenteAtual.retornar_altura_agente(), agenteAtual.retornar_largura_agente());

        return retalho;
    }

    /**
     * Define qual é o agente atual da simulação
     *
     * @param agente
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void definir_agente_atual(Object agente) throws ErroExecucaoBiblioteca, InterruptedException {
        agenteAtual = (IAgente) agente;
        UtilSimulador.setLog("Agente atual: " + agenteAtual.retornar_id());
    }

    /**
     * Retorna o agente atual que está executando o processo
     *
     * @return
     */
    public IAgente getAgenteAtual() {
        return agenteAtual;
    }

    /**
     * Atribui o plugin atual para utilização, caso necessário, em outras
     * classes do projeto
     *
     * @param plugin
     */
    public void setPlugin(Plugin plugin) {
        GerenciadorExecucao.plugin = plugin;
    }

    /**
     * Retorna o plugin atual
     *
     * @return
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Equivale ao método "morrer" do agente, removendo-o da lista de agentes da
     * simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void remover_agente_simulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        agenteAtual.definir_visibilidade(false);
        UtilSimulador.setLog("Agente: " + agenteAtual.retornar_id() + "removido com sucesso");
    }

    /**
     * Define se a simulação está executando ou não
     *
     * @return
     */
    public boolean isExecutando() {
        return GerenciadorExecucao.executando;
    }

    /**
     * Define o status atual da simulação (Executando ou parada)
     *
     * @param executando
     */
    public void setExecutando(boolean executando) {
        GerenciadorExecucao.executando = executando;
    }

    /**
     * Define a instância do programa para iniciá-lo quando o usuário clicar em
     * iniciar a simulação
     *
     * @param simuladorPrograma
     */
    public void setSimuladorPrograma(SimuladorPrograma simuladorPrograma) {
        GerenciadorExecucao.simuladorPrograma = simuladorPrograma;
    }

    /**
     * Inicia a simulação a partir da instância gerada do programa
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    public void iniciar_simulacao() throws ErroExecucao, InterruptedException {
        try {
            if (GerenciadorExecucao.simuladorPrograma != null) {
                GerenciadorExecucao.simuladorPrograma.simular(false);
            }

            this.setExecutando(true);
        } catch (ErroExecucao | InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Verifica se a simulação ainda está visível
     *
     * @return
     */
    public boolean simulacao_visivel() {
        return true;
    }
    
    /**
     * Retorna o número de ticks da simulação
     * @return 
     */
    public int getTicks() {
        return GerenciadorExecucao.ticks;
    }

    /**
     * Chamada para definir o número de ticks da simulação
     * @param ticks 
     */
    public void setTicks(int ticks) {
        GerenciadorExecucao.ticks = ticks;
    }

    /**
     * Retorna para verificação se o ambiente da simulação já foi inicializado
     * @return 
     */
    public boolean isAmbienteInicializado() {
        return ambienteInicializado;
    }

}
