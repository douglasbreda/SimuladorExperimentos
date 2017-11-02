/**
 * Gerenciador para controlar a execução das simulação
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.Agente;
import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.plugin.biblioteca.componentes.TipoForma;
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

    private static TipoForma formaAgente = TipoForma.circulo;

    //Define os passos da simulação
    //Ou seja, cada vez que um ciclo de execução é completado
    private static int ticks = 0;

    //Define se vai rodar a simulação dentro de um while(true) ou não
    private static boolean executar_sempre = false;

    /**
     * Inicializa o ambiente da simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws InvocationTargetException
     * @throws ErroExecucao
     */
    public void inicializarAmbiente() throws ErroExecucaoBiblioteca, InterruptedException, InvocationTargetException, ErroExecucao {
        ambienteInicializado = true;
        GerenciadorInterface.getInstance().inicializarTela();
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
     * @return
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
     * @param numeroAgentes
     * @param aleatorio
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void criarAgentes(int numeroAgentes, boolean aleatorio) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {

        int coordenadaX = 0;
        int coordenadaY = 0;
        int id = 0;
        int minX = GerenciadorInterface.getInstance().retornarValorMinBordaX();
        int minY = GerenciadorInterface.getInstance().retornarValorMinBordaY();
        int maxX = GerenciadorInterface.getInstance().retornarValorMaxBordaX();
        int maxY = GerenciadorInterface.getInstance().retornarValorMaxBordaY();
        int valor_fixo_x = (maxX / 2);
        int valor_fixo_y = (maxY / 2);

        for (int i = 0; i < numeroAgentes; i++) {

            if (aleatorio) {
                coordenadaX = UtilSimulador.getNumeroRandomico(minX, maxX);
                coordenadaY = UtilSimulador.getNumeroRandomico(minY, maxY);
            } else {
                coordenadaX = valor_fixo_x;
                coordenadaY = valor_fixo_y;
            }

            IAgente agente = new Agente(coordenadaX, coordenadaY, ++id, formaAgente.ordinal());

            addAgente(agente);
            GerenciadorInterface.getInstance().renderizarTelaParcial();
        }
    }

    /**
     * Retorna o número de agentes da simulação
     *
     * @return
     */
    public int contarAgentes() {
        return getListaAgentes().size();
    }

    /**
     * Retorna a média de uma parâmetro do agente
     *
     * @param nomeParametro
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public double media(String nomeParametro) throws ErroExecucaoBiblioteca, InterruptedException {

        double media = 0;

        if (listaAgentes.size() > 0) {
            for (IAgente agente : listaAgentes) {
                media += agente.retornarAtributoReal(nomeParametro);
            }

            media = media / listaAgentes.size();
        }

        UtilSimulador.setLog("A média é: " + media);
        return Math.round(media);
    }

    /**
     * Adiciona atributos/parametros a todos os agentes da lista
     *
     * @param nome
     * @param valorPadrao
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void adicionarAtributoAgente(String nome, String valorPadrao) throws ErroExecucaoBiblioteca, InterruptedException {
        for (IAgente agente : listaAgentes) {
            agente.criarAtributo(nome, valorPadrao);
        }
    }

    /**
     * Define um valor a um atributo por agente
     *
     * @param nomeAtributo
     * @param valor
     * @param id ID do agente que terá o atributo alterado
     */
    public void definirValorAtributoPorAgente(String nomeAtributo, String valor, int id) {
        try {
            for (IAgente agente : listaAgentes) {

                if (agente.retornarId() == id) {
//                    agente.atualizarValorAtributo(nome_atributo, valor, id);
                }
            }
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Limpa a lista de agentes
     * 
     */
    public void limpar_tudo() {
        listaAgentes.clear();
    }

    /**
     * Retorna a lista de agentes da aplicação que estão visiveis
     *
     * @return
     */
    public ArrayList<IAgente> getListaAgentes() {
        ArrayList<IAgente> listaRetorno = null;

        if (listaAgentes != null) {
            List<IAgente> listaNova = listaAgentes.stream().filter(x -> x.estaVisivel()).collect(Collectors.toList());

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
    public int agentesComCor(int cor) {
        int numeroAgentes = 0;
        if (listaAgentes != null) {
            numeroAgentes = UtilSimulador.toInt(listaAgentes.stream()
                    .filter(agente -> compararCorAgente(agente, cor))
                    .count());
        }

        return numeroAgentes;
    }

    /**
     * Método criado para disparar exceção dentro de um filter
     *
     * @param agente
     * @param cor
     * @return
     */
    private boolean compararCorAgente(IAgente agente, int cor) {
        try {
            return agente.retornarCorAgente() == cor;
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorExecucao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int agentesEm(int coordenadaX, int coordenadaY) throws InterruptedException, ErroExecucao {
        return agentesEm(coordenadaX, coordenadaY, agenteAtual.retornarAlturaAgente(), agenteAtual.retornarLarguraAgente());
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
    public int agentesEm(int coordenadaX, int coordenadaY, int altura, int largura) throws InterruptedException, ErroExecucao {
        int numeroAgentes = 0;

        Retalho retalho = GerenciadorInterface.getInstance().getRetalho(coordenadaX, coordenadaY, altura, largura);

        if (retalho != null) {
            retalho.set_cor(2);
            numeroAgentes = GerenciadorInterface.getInstance().buscarNumeroAgentes(retalho);
            GerenciadorInterface.getInstance().renderizarTela();
        }

        return numeroAgentes;
    }

    /**
     * Encontra o retalho atual do agente
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public Retalho meuRetalho() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        Retalho retalho = GerenciadorInterface.getInstance().getRetalho(agenteAtual.retornarCoordenadaX(), agenteAtual.retornarCoordenadaY(), agenteAtual.retornarAlturaAgente(), agenteAtual.retornarLarguraAgente());

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
    public void definirAgenteAtual(Object agente) throws ErroExecucaoBiblioteca, InterruptedException {
        agenteAtual = (IAgente) agente;
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
    public void removerAgenteSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        agenteAtual.definirVisibilidade(false);
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
    public void iniciarSimulacao() throws ErroExecucao, InterruptedException {
        try {
            if (GerenciadorExecucao.simuladorPrograma != null) {
                GerenciadorExecucao.simuladorPrograma.simular(executar_sempre);
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
    public boolean simulacaoVisivel() {
        return GerenciadorInterface.getInstance().janelaSimulacaoVisivel();
    }

    /**
     * Retorna o número de ticks da simulação
     *
     * @return
     */
    public int getTicks() {
        return GerenciadorExecucao.ticks;
    }

    /**
     * Chamada para definir o número de ticks da simulação
     *
     * @param ticks
     */
    public void setTicks(int ticks) {
        GerenciadorExecucao.ticks = ticks;
    }

    /**
     * Retorna para verificação se o ambiente da simulação já foi inicializado
     *
     * @return
     */
    public boolean isAmbienteInicializado() {
        return ambienteInicializado;
    }

    /**
     * Define qual é o formato dos desenhos dos agentes
     *
     * @param forma
     */
    public void setFormaAgente(int forma) {
        switch (forma) {
            case 0:
                formaAgente = TipoForma.circulo;
                break;
            case 1:
                formaAgente = TipoForma.linha;
                break;
        }
    }

    /**
     * Busca se a simulação irá executar em um while(true) ou não
     *
     * @return
     */
    public boolean isExecutarSempre() {
        return GerenciadorExecucao.executar_sempre;
    }

    /**
     * Define se a simulação irá executar em um while(true)
     *
     * @param executar_sempre
     */
    public void setExecutarSempre(boolean executar_sempre) {
        GerenciadorExecucao.executar_sempre = executar_sempre;
    }

    /**
     * Retorna o agente que está no mesmo retalho que o agente atual
     *
     * @param idRetalho
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int buscarAgentesAqui(int idRetalho) throws ErroExecucaoBiblioteca, InterruptedException {
        Retalho retalho = GerenciadorInterface.getInstance().buscarRetalhoPorId(idRetalho);

        if (retalho != null) {
            return GerenciadorInterface.getInstance().buscarIdAgenteRetalho(retalho, getAgenteAtual().retornarId());
        }

        return 0;
    }

    /**
     * Retorna a orientação de um agente de acordo com o seu id
     *
     * @param id
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int getOrientacaoPorId(int id) throws ErroExecucaoBiblioteca, InterruptedException {
        for (IAgente agente : listaAgentes) {
            if (agente.retornarId() == id) {
                return agente.retornarOrientacao();
            }
        }

        return getAgenteAtual().retornarOrientacao();
    }
}
