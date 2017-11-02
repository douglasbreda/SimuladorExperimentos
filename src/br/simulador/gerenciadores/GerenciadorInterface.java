/**
 * Classe para controle das telas e inicialiação do plugin
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.plugin.biblioteca.base.TipoParede;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Douglas
 */
public final class GerenciadorInterface {

    private static GerenciadorInterface instance = null;

    private static GerenciadorDesenho desenho = null;

    //Define se o ambiente já está inicializado para evitar disparar diversas threads caso o usuário coloque num 
    //laço enquanto por exemplo
    private static boolean ambienteInicialiazdo = false;

    /**
     * Instância da classe estática que controla a inicialização da simulação
     *
     * @return
     */
    public static GerenciadorInterface getInstance() {

        if (instance == null) {
            instance = new GerenciadorInterface();
            desenho = new GerenciadorDesenho();
            ambienteInicialiazdo = false;
        }

        return instance;
    }

    /**
     * Chama a tela de inicialização do Simulador
     *
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public void inicializarTela() throws ErroExecucaoBiblioteca, InterruptedException, InvocationTargetException, InvocationTargetException, ErroExecucao {
        if (!ambienteInicialiazdo) {
            UtilSimulador.setLog("Vai inicializar tela");
            desenho.inicializarTela();
            ambienteInicialiazdo = true;
            //Thread.sleep(1000);//Adicionado pois não estava carregando os componentes no tempo suficiente para utilização em outros métodos
        }
    }

    /**
     * Retorna se a janela ainda está visível e executando
     *
     * @return
     */
    public boolean estaExecutando() {
        return desenho.estaExecutando();
    }

    /**
     * Retorna a altura da janela de simulação
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int getAlturaSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.getAlturaJanela();
    }

    /**
     * Retorna a largura da janela de simulação
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int getLarguraSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.getLarguraJanela();
    }

    /**
     * Método que vai desenhar os limites(bordas) da tela
     *
     * @param cor
     */
    public void definirBordas(int cor) {
        desenho.desenharBordas(cor);
    }

    /**
     * Desenha a borda esquerda com a cor passada
     *
     * @param cor
     */
    public void definirBordaEsquerda(int cor) {
        desenho.desenharBordaEsquerda(cor);
    }

    /**
     * Desenha a borda direita com a cor passada
     *
     * @param cor
     */
    public void definirBordaDireita(int cor) {
        desenho.desenharBordaDireita(cor);
    }

    /**
     * Desenha a borda superior com a cor passada
     *
     * @param cor
     */
    public void definirBordaSuperior(int cor) {
        desenho.desenharBordaSuperior(cor);
    }

    /**
     * Desenha a borda inferior com a cor passada
     *
     * @param cor
     */
    public void definirBordaInferior(int cor) {
        desenho.desenharBordaInferior(cor);
    }

    /**
     * Reseta as informações da simulação e configura a tela com a posição
     * inicial
     */
    public void limparTudo() {
        desenho.limparTudo();
    }

    /**
     * Método que "força" uma renderização da tela
     *
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void atualizarTela() throws ErroExecucaoBiblioteca, InterruptedException {
        desenho.atualizarTela();
    }

    /**
     * Retorna o retalho de uma determinada posição X e Y
     *
     * @param coordenadaX
     * @param coordenadaY
     * @param altura
     * @param largura
     * @return
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public Retalho getRetalho(int coordenadaX, int coordenadaY, int altura, int largura) throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.getRetalho(coordenadaX, coordenadaY, altura, largura);
    }

    /**
     * Busca o número de agentes que estão em um determinado retalho
     *
     * @param retalho
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int buscarNumeroAgentes(Retalho retalho) throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.buscarAgentesRetalho(retalho);
    }

    /**
     * Força a atualização dos componentes visuais
     *
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void renderizarTela() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        desenho.renderizar();
    }

    /**
     * Força uma atualização dos componentes, porém sem ficar em loop, apenas um
     * "refresh"
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public void renderizarTelaParcial() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        desenho.renderizarParcial();
    }

    /**
     * Renderiza somente os agentes para evitar atualizações de tela
     * desnecessárias
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void renderizarAgentes() throws ErroExecucaoBiblioteca, InterruptedException {
        desenho.renderizarAgentes();
    }

    /**
     * Define a cor de fundo de todo o ambiente de simulação
     *
     * @param cor
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void definirCorFundo(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        desenho.definirCorFundo(cor);
    }

    /**
     * Cria um novo componente na tela do tipo slider
     *
     * @param nome
     * @param titulo
     * @param minimo
     * @param maximo
     * @param valor_padrao
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public void criarSlider(String nome, String titulo, double minimo, double maximo, double valor_padrao) throws InterruptedException, ErroExecucao {
        desenho.criarSlider(nome, titulo, minimo, valor_padrao, maximo);
    }

    /**
     * Cria um novo componente do tipo monitor
     *
     * @param nome
     * @param titulo
     * @param valor_atual
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public void criarMonitor(String nome, String titulo, String valor_atual) throws InterruptedException, ErroExecucao {
        desenho.criarMonitor(nome, titulo, valor_atual);
    }

    /**
     * Cria um novo componente do tipo interruptor
     *
     * @param nome
     * @param titulo
     * @param valor_padrao
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void criarInterruptor(String nome, String titulo, boolean valor_padrao) throws ErroExecucaoBiblioteca, InterruptedException {
        desenho.criarInterruptor(nome, titulo, valor_padrao);
    }

    /**
     * Atribuir a cor a um retalho procurando pelo seu id
     *
     * @param id
     * @param cor
     */
    public void definirCorRetalho(int id, int cor) {
        desenho.definirCorRetalho(id, cor);
    }

    /**
     * Retorna o valor máximo no eixo X. Ou seja, onde começa a parede que
     * define as bordas no lado direito
     *
     * @return
     */
    public int retornarValorMaxBordaX() {
        return desenho.retornarValorMaximoBordaX();
    }

    /**
     * Retorna o valor máximo no eixo Y. Ou seja, onde começa a parede que
     * define as bordas na parte superior
     *
     * @return
     */
    public int retornarValorMaxBordaY() {
        return desenho.retornarValorMaximoBordaY();
    }

    /**
     * Retorna o valor mínimo no eixo X. Ou seja, onde começa a parede que
     * define as bordas no lado esquerdo
     *
     * @return
     */
    public int retornarValorMinBordaX() {
        return desenho.retornarValorMinimoBordaX();
    }

    /**
     * Retorna o valor máximo no eixo Y. Ou seja, onde começa a parede que
     * define as bordas na parte inferior
     *
     * @return
     */
    public int retornarValorMinBordaY() {
        return desenho.retornarValorMinimoBordaY();
    }

    /**
     * Retorna se o retalho é uma parede lateral
     *
     * @param id
     * @return
     */
    public boolean verificarRetalhoEhParedeX(int id) {
        TipoParede tipoParede = desenho.verificarRetalhoEhParede(id);

        if (tipoParede != null) {
            return tipoParede == TipoParede.paredeX;
        }

        return false;
    }

    /**
     * Verifica se o retalho definido é uma parede inferior ou superior
     *
     * @param id
     * @return
     */
    public boolean verificarRetalhoEhParedeY(int id) {
        TipoParede tipo_parede = desenho.verificarRetalhoEhParede(id);

        if (tipo_parede != null) {
            return tipo_parede == TipoParede.paredeY;
        }

        return false;
    }

    /**
     * Verifica se colidiu com a parede esquerda
     *
     * @param id
     * @return
     */
    public boolean verificarColidiuParedeEsquerda(int id) {
        if (verificarRetalhoEhParedeX(id)) {
            return desenho.verificarBordaEsquerda(id);
        }

        return false;
    }

    /**
     * Verifica se colidiu com a parede direita
     *
     * @param id
     * @return
     */
    public boolean verificarColidiuParedeDireita(int id) {
        if (verificarRetalhoEhParedeX(id)) {
            return desenho.verificarBordaDireita(id);
        }

        return false;
    }

    /**
     * Verifica se colidiu com a parede superior
     *
     * @param id
     * @return
     */
    public boolean verificarColidiuParedeSuperior(int id) {
        if (verificarRetalhoEhParedeY(id)) {
            return desenho.verificarBordaSuperior(id);
        }

        return false;
    }

    /**
     * Verifica se colidiu com a parede inferior
     *
     * @param id
     * @return
     */
    public boolean verificarColidiuParedeInferior(int id) {
        if (verificarRetalhoEhParedeY(id)) {
            return desenho.verificarBordaSuperior(id);
        }

        return false;
    }

    /**
     * Retorna a altura máxima do painel onde está ocorrendo a simulação
     *
     * @return
     */
    public int getAlturaMaximaPainelSimulacao() {
        return desenho.getAlturaMaximaPainelSimulacao();
    }

    /**
     * Retorna a altura mínima do painel onde está ocorrendo a simulação
     *
     * @return
     */
    public int getAlturaMinimaPainelSimulacao() {
        return desenho.getAlturaMinimaPainelSimulacao();
    }

    /**
     * Retorna a largua máxima do painel onde está ocorrendo a simulação
     *
     * @return
     */
    public int getLarguraMaximaPainelSimulacao() {
        return desenho.getLarguraMaximaPainelSimulacao();
    }

    /**
     * Retorna a largura mínima do painel onde está ocorrendo a simulação
     *
     * @return
     */
    public int getLarguraMinimaPainelSimulacao() {
        return desenho.getLarguraMinimaPainelSimulacao();
    }

    /**
     * Retorna a instância do objeto retalho buscando pelo seu ID
     *
     * @param id
     * @return
     */
    public Retalho buscarRetalhoPorId(int id) {
        return desenho.buscarRetalhoPorId(id);
    }

    /**
     * Retorna o id do agente que se encontra no mesmo retalho do agente atual
     * da simulação
     *
     * @param retalho
     * @param idAgenteAtual
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int buscarIdAgenteRetalho(Retalho retalho, int idAgenteAtual) throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.buscarIdAgenteRetalho(retalho, idAgenteAtual);
    }
    
    /**
     * Retorna se a janela onde roda a simulação ainda está aberta
     * 
     * @return 
     */
    public boolean janelaSimulacaoVisivel(){
        return desenho.janelaVisivel();
    }
    
    /**
     * Define qual será o título apresentando na parte superior da janela da simulação
     * 
     * @param titulo 
     */
    public void definirTituloSimulacao(String titulo){
        desenho.setTituloSimulacao(titulo);
    }
}
