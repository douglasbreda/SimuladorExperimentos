/*
Classe para desenhar os componentes visuais
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.plugin.biblioteca.base.TipoParede;
import br.simulador.plugin.biblioteca.componentes.Componente;
import br.simulador.plugin.biblioteca.componentes.Interruptor;
import br.simulador.plugin.biblioteca.componentes.Monitor;
import br.simulador.plugin.biblioteca.componentes.Slider;
import br.univali.portugol.nucleo.ProgramaVazio;
import br.univali.portugol.nucleo.bibliotecas.Graficos;
import br.univali.portugol.nucleo.bibliotecas.Matematica;
import br.univali.portugol.nucleo.bibliotecas.Mouse;
import br.univali.portugol.nucleo.bibliotecas.Teclado;
import br.univali.portugol.nucleo.bibliotecas.base.Biblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class GerenciadorDesenho {

    final int largura = 31;
    final int altura = 26;
    final int tile = 18;
    int cor_atual = 0;
    private final String pastaImagens = "D:/Desenvolvimento/TTC II/Plugin Experimentos/SimuladorExperimentos/SimuladorExperimentos/src/br/simulador/imagens/";
    private int larguraTela;
    private int alturaTela;
    private int imagemFundo;
    private int alturaImagemFundo;
    private boolean clicou;
    private final int larguraBotao = 28;
    private final int alturaBotao = 28;
    private int ultimoX;
    private int ultimoY;
    private int status;
    private int larguraPainelComponentes = 250;
    private int alturaPainelComponentes = 50;
    private final int[] referencias = new int[4];

    private final int indiceImagemBotaoIniciar = 0;
    private final int indiceImagemBotaoIniciarHover = 1;
    private final int indiceImagemBotaParar = 2;
    private final int indiceImagemBotaoPararHover = 3;

    private int enderecoImagemSwitchOn = 0;
    private int enderecoImagemSwitchOff = 0;

    Retalho[][] retalhos = new Retalho[altura][largura];
    int[] cores = {0xFFFFFF, 0xE4E4E4, 0x888888, 0x222222, 0xFFA7D1, 0xE50000, 0xE59500, 0xA06A42, 0xE5D900, 0x94E044, 0x02BE01, 0x00D3DD, 0x0083C7, 0x0000EA, 0xCF6EE4, 0x820080};

    Graficos g = new Graficos();
    Mouse m = new Mouse();
    Matematica math = new Matematica();
    Teclado t = new Teclado();
    private final int posicaoYInicial = 10;// Posição de start da criação do primeiro componente
    private final int posicaoYFinal = 60; // Posição de final da criação do primeiro componente
    private final int posicaoXInicial = 15;
    private final int posicaoXFinal = 15;
    private final String imagemSwitchOn = "switch_on_32.png";
    private final String imagemSwitchOff = "switch_off_32.png";
    private int alturaRodape = 24;
    private final int corEscuraLinhaDivisoria = 0x111111; 	// RGB = 17,17,17
    private final int corClaraLinhaDivisoria = 0x4C4C4C; 	// RGB = 76,76,76
    private String tituloSimulacao = "";

    /**
     * Inicia a tela onde será executada a simulação
     *
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     */
    public void inicializarTela() throws InterruptedException, InvocationTargetException, ErroExecucaoBiblioteca, ErroExecucao {

        try {
            inicializarAmbienteGrafico();
            configurar();
            rodarSimples();
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa as classes necessárias para o ambiente gráfico da simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void inicializarAmbienteGrafico() throws ErroExecucaoBiblioteca, InterruptedException {
        g = new Graficos();
        m = new Mouse();
        g.inicializar(ProgramaVazio.novaInstancia(), null);
        ArrayList<Biblioteca> lista = new ArrayList<>();
        lista.add(g);
        m.inicializar(ProgramaVazio.novaInstancia(), lista);
        t.inicializar(ProgramaVazio.novaInstancia(), lista);

        //Inicializa os retalhos
        iniciarRetalhos();
        g.iniciar_modo_grafico(false);
        g.definir_dimensoes_janela(810, 560);
    }

    /**
     * Inicializa os retalhos da simulação (O "chão" da simulação)
     *
     */
    private void iniciarRetalhos() {
        int id = 0;
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                retalhos[i][j] = new Retalho(++id, Graficos.COR_BRANCO);
            }
        }
    }

    /**
     * Chamada do método para criar os componentes, e continuar com as
     * configurações iniciais Depois, esse método fica rodando em loop enquanto
     * o usuário não iniciar a simulação
     *
     */
    private void rodarSimples() {
        try {
            desenhar();
            controle();
            g.renderizar();

        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorInterface.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ErroExecucao ex) {
            Logger.getLogger(GerenciadorDesenho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Dispara a thread necessária por ficar renderizando a tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void rodar() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        boolean isExecutando = GerenciadorExecucao.getInstance().isExecutando();
        do {
            rodarSimples();
        } while (!isExecutando);
    }

    /**
     * Configurações iniciais necessárias para a simulação
     *
     */
    private void configurar() throws ErroExecucaoBiblioteca, InterruptedException {
        imagemFundo = g.carregar_imagem(pastaImagens + "fundo_exato.png");
        carregarImagensBotao("play", indiceImagemBotaoIniciar, indiceImagemBotaoIniciarHover);
        carregarImagensBotao("stop", indiceImagemBotaParar, indiceImagemBotaoPararHover);

        enderecoImagemSwitchOn = g.carregar_imagem(pastaImagens + imagemSwitchOn);
        enderecoImagemSwitchOff = g.carregar_imagem(pastaImagens + imagemSwitchOff);

        larguraTela = g.largura_tela();
        alturaTela = g.altura_tela();
        alturaImagemFundo = g.altura_imagem(imagemFundo);
    }

    /**
     * Retorna se o mouse está sobre algum dos botões (Play ou Stop)
     *
     * @param xBotao
     * @param yBotao
     * @return
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private boolean mouseEmCimaBotao(int xBotao, int yBotao) throws ErroExecucao, InterruptedException {
        int mouseX = m.posicao_x();
        int mouseY = m.posicao_y();

        boolean mouseDentroBotaoNaHorizontal = (mouseX >= xBotao
                && mouseX <= xBotao + larguraBotao);
        boolean mouseDentroBotaoNaVertical = (mouseY >= yBotao && mouseY <= yBotao + alturaBotao);
        return mouseDentroBotaoNaHorizontal && mouseDentroBotaoNaVertical;

    }

    /**
     * Desenha os botões de play e pause
     *
     * @param xBotao
     * @param yBotao
     * @param botaoNormal
     * @param botaoHover
     * @return
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private boolean desenharBotao(int xBotao, int yBotao, int botaoNormal, int botaoHover) throws ErroExecucao, InterruptedException {
        boolean mouseEmCimaBotao = mouseEmCimaBotao(xBotao, yBotao);
        if (mouseEmCimaBotao) {
            g.desenhar_imagem(xBotao, yBotao, botaoHover);
        } else {
            g.desenhar_imagem(xBotao, yBotao, botaoNormal);
        }

        if (mouseEmCimaBotao && clicou) {
            clicou = false;
            executar_acao(botaoNormal);
        }

        return false;
    }

    /**
     * Desenha as informações que ficam no rodapé da simulação
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void desenharInformacoesRodape() throws ErroExecucao, InterruptedException {

        g.definir_cor(Graficos.COR_BRANCO);
        g.definir_estilo_texto(false, true, false);

        int totalAgentes = 0;

        if (GerenciadorExecucao.getInstance().getListaAgentes() != null) {
            totalAgentes = GerenciadorExecucao.getInstance().getListaAgentes().size();
        }

        g.desenhar_texto(10, alturaImagemFundo - alturaRodape, "Total de Agentes: " + totalAgentes);
        int larguraTotalAgentes = g.largura_texto("Total de Agentes: " + totalAgentes);
        int larguraStatus = 0;

        if (status == 0) {
            g.desenhar_texto(10 + larguraTotalAgentes + 50, alturaImagemFundo - alturaRodape, "Status: Parada");
            larguraStatus = g.largura_texto("Status: Parada");

        } else {
            g.desenhar_texto(10 + larguraTotalAgentes + 50, alturaImagemFundo - alturaRodape, "Status: Executando");
            larguraStatus = g.largura_texto("Status: Executando");
        }

        int totalTicks = GerenciadorExecucao.getInstance().getTicks();
        g.desenhar_texto(10 + larguraTotalAgentes + larguraStatus + 100, alturaImagemFundo - alturaRodape, "Ticks: " + totalTicks);
    }

    /**
     * Executa a ação de um botão
     *
     * @param botaoClicado
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void executar_acao(int botaoClicado) throws ErroExecucao, InterruptedException {
        boolean pausar = false;

        if (!pausar && botaoClicado == referencias[indiceImagemBotaoIniciar]) {
            GerenciadorExecucao.getInstance().iniciarSimulacao();
            status = 1;
            pausar = true;
        }

        if (!pausar && botaoClicado == referencias[indiceImagemBotaParar]) {
            GerenciadorExecucao.getInstance().setExecutando(false);
            status = 0;
            pausar = true;
        }
    }

    /**
     * Desenha os botões de play e stop
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void desenharBotoes() throws ErroExecucao, InterruptedException {
        int margem = 10;
        int espacoEntreBotoes = larguraBotao + 3;
        int xBotao = margem;
        desenharBotao(xBotao, margem, referencias[indiceImagemBotaoIniciar],
                referencias[indiceImagemBotaoIniciarHover]);
        xBotao = xBotao + espacoEntreBotoes;
        desenharBotao(xBotao, margem, referencias[indiceImagemBotaParar],
                referencias[indiceImagemBotaoPararHover]);
        xBotao = xBotao + espacoEntreBotoes;
    }

    /**
     * Carrega as imagens referente aos botões de inicio e fim da simulação
     *
     * @param nomeImagem
     * @param imagemNormal
     * @param imagemHover
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void carregarImagensBotao(String nomeImagem, int imagemNormal, int imagemHover) throws ErroExecucaoBiblioteca, InterruptedException {
        String nomeImagemNormal = nomeImagem + ".png";
        String nomeImagemHover = nomeImagem + "_hover.png";
        referencias[imagemNormal] = g.carregar_imagem(pastaImagens + nomeImagemNormal);
        referencias[imagemHover] = g.carregar_imagem(pastaImagens + nomeImagemHover);
    }

    /**
     * Responsável por desenhar e renderizar os componentes visuais
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {

        desenharTituloSimulacao(tituloSimulacao);
        
        g.desenhar_imagem(0, 0, imagemFundo);

        desenharRetalhos();

        desenharBotoes();

        desenharInformacoesRodape();

        desenharAgentes();

        desenharComponentes();

        desenharLinhas();

        tratarCliques();
    }

    /**
     * Desenha o título do experimento
     * 
     * @param titulo
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    private void desenharTituloSimulacao(String titulo) throws ErroExecucaoBiblioteca, InterruptedException {
        tituloSimulacao = titulo;
        if (titulo.isEmpty()) {
            g.definir_titulo_janela("Simulador de Experimentos");
        } else {
            g.definir_titulo_janela("Simulador de Experimentos - " + titulo);
        }
    }

    /**
     * Desenha as linhas divisórias da tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenharLinhas() throws ErroExecucaoBiblioteca, InterruptedException {

        //As linhas desenhadas duas vezes dão efeito de profundidade (Extraído do exemplo da bateria do Portugol Studio)
        //Linha divisória vertical 
        g.definir_cor(corEscuraLinhaDivisoria);
        g.desenhar_linha(larguraPainelComponentes - 10, alturaPainelComponentes - 10, larguraPainelComponentes - 10, alturaImagemFundo - alturaRodape - 10);

        g.definir_cor(corClaraLinhaDivisoria);
        g.desenhar_linha(larguraPainelComponentes - 9, alturaPainelComponentes - 10, larguraPainelComponentes - 9, alturaImagemFundo - alturaRodape - 10);

        //Linha divisória horizontal superior
        g.definir_cor(corEscuraLinhaDivisoria);
        g.desenhar_linha(0, alturaPainelComponentes - 10, larguraTela, alturaPainelComponentes - 10);

        g.definir_cor(corClaraLinhaDivisoria);
        g.desenhar_linha(0, alturaPainelComponentes - 9, larguraTela, alturaPainelComponentes - 9);

        //Linha divisória horizontal inferior
        g.definir_cor(corEscuraLinhaDivisoria);
        g.desenhar_linha(0, alturaImagemFundo - alturaRodape - 10, larguraTela, alturaImagemFundo - alturaRodape - 10);

        g.definir_cor(corClaraLinhaDivisoria);
        g.desenhar_linha(0, alturaImagemFundo - alturaRodape - 9, larguraTela, alturaImagemFundo - alturaRodape - 9);
    }

    /**
     * Método que aplica as cores conforme o usuário clica em uma posição da
     * tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void controle() throws ErroExecucaoBiblioteca, InterruptedException {
        controleMouseSlider();
        controleMouseSwitch();
    }

    /**
     * Verifica os cliques do usuário para saber se clicou em algum botão
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void tratarCliques() throws ErroExecucao, InterruptedException {
        if (m.algum_botao_pressionado()) {
            if (ultimoX <= 0 && ultimoY <= 0) {
                ultimoX = m.posicao_x();
                ultimoY = m.posicao_y();
            }
        } else {
            if (ultimoX >= 0 && ultimoY >= 0) {
                int delta_x = m.posicao_x() - ultimoX;
                int delta_y = m.posicao_y() - ultimoY;
                if (delta_x >= 0 && delta_x <= larguraBotao && delta_y >= 0 && delta_y <= alturaBotao) {
                    clicou = true;
                }
            }
            ultimoX = -1;
            ultimoY = -1;
        }

    }

    /**
     * Desenha os retalhos "chão" da simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenharRetalhos() throws ErroExecucaoBiblioteca, InterruptedException {
        int x = 0;
        int y = 0;
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                g.definir_cor(retalhos[i][j].retornar_cor_retalho());
                x = (j * tile) + 250;
                y = (i * tile) + 50;
                g.desenhar_retangulo(x, y, tile, tile, false, true);
                retalhos[i][j].definirCoordenadas(x, y);
            }
        }
    }

    /**
     * Desenha os agentes da simulação
     *
     */
    private void desenharAgentes() throws ErroExecucaoBiblioteca, InterruptedException {
        ArrayList<IAgente> listaAgente = GerenciadorExecucao.getInstance().getListaAgentes();

        if (listaAgente != null && listaAgente.size() > 0) {
            for (IAgente agente : listaAgente) {
                g.definir_cor(agente.retornarCorAgente());
                g.definir_rotacao(agente.retornarOrientacao());
                desenharForma(agente);
                g.definir_rotacao(0);
            }
        }
    }

    /**
     * Desenha as formas de acordo com o tipo definido pelo usuário
     *
     * @param agente
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenharForma(IAgente agente) throws ErroExecucaoBiblioteca, InterruptedException {
        switch (agente.retornarFormaAgente()) {
            case circulo:
                g.desenhar_elipse(agente.retornarCoordenadaX(), agente.retornarCoordenadaY(), agente.retornarLarguraAgente(), agente.retornarAlturaAgente(), true);
                break;
            case linha:
                g.desenhar_linha(agente.retornarCoordenadaX(), agente.retornarCoordenadaY(), agente.retornarCoordenadaX() + 30, agente.retornarCoordenadaY() + 10);
                break;
            default:
                g.desenhar_elipse(agente.retornarCoordenadaX(), agente.retornarCoordenadaY(), agente.retornarLarguraAgente(), agente.retornarAlturaAgente(), true);
                break;
        }
    }

    /**
     * Verifica se a simulação ainda está executando para que possa parar as
     * threads caso ela seja finalizada
     *
     * @return
     */
    public boolean estaExecutando() {
        return false;
    }

    /**
     * Retorna a altura da janela de simulação
     *
     * @return
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public int getAlturaJanela() throws ErroExecucaoBiblioteca, InterruptedException {
        return g.altura_janela();
    }

    /**
     * Retorna a largura da janela
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int getLarguraJanela() throws ErroExecucaoBiblioteca, InterruptedException {
        return g.largura_janela();
    }

    /**
     * Desenha os limites(bordas) da janela
     *
     * @param cor
     */
    public void desenharBordas(int cor) {

        desenharBordaSuperior(cor);

        desenharBordaInferior(cor);

        desenharBordaEsquerda(cor);

        desenharBordaDireita(cor);
    }

    /**
     * Desenha a borda superior da simulação
     *
     * @param cor
     */
    public void desenharBordaSuperior(int cor) {
        //Borda superior
        for (int i = 0; i < largura; i++) {
            retalhos[0][i].set_cor(cor);
            retalhos[0][i].definirComoParede(true, TipoParede.paredeY);
        }
    }

    /**
     * Desenha a borda inferior da simulação
     *
     * @param cor
     */
    public void desenharBordaInferior(int cor) {
        //Borda inferior
        for (int i = 0; i < largura; i++) {
            retalhos[altura - 1][i].set_cor(cor);
            retalhos[altura - 1][i].definirComoParede(true, TipoParede.paredeY);
        }
    }

    /**
     * Desenha a borda direita da simulação
     *
     * @param cor
     */
    public void desenharBordaDireita(int cor) {
        //Borda direita
        for (int i = 0; i < altura; i++) {
            retalhos[i][largura - 1].set_cor(cor);
            retalhos[i][largura - 1].definirComoParede(true, TipoParede.paredeX);
        }
    }

    /**
     * Desenha a borda esquerda da simulação
     *
     * @param cor
     */
    public void desenharBordaEsquerda(int cor) {

        //Borda esquerda
        for (int i = 0; i < altura; i++) {
            retalhos[i][0].set_cor(cor);
            retalhos[i][0].definirComoParede(true, TipoParede.paredeX);
        }
    }

    /**
     * Verifica se a borda que colidiu é uma borda do lado esquerdo
     *
     * @param id
     * @return
     */
    public boolean verificarBordaEsquerda(int id) {
        //Borda esquerda
        for (int i = 0; i < altura; i++) {
            if (retalhos[i][0].getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se uma borda que colidiu é uma borda do lado direito
     *
     * @param id
     * @return
     */
    public boolean verificarBordaDireita(int id) {
        //Borda direita
        for (int i = 0; i < altura; i++) {
            if (retalhos[i][largura - 1].getId() == id) {
                return true;
            }
        }

        return false;
    }

    /**
     * Verifica se uma borda que colidiu é uma borda na parte superior
     *
     * @param id
     * @return
     */
    public boolean verificarBordaSuperior(int id) {
        //Borda superior
        for (int i = 0; i < largura; i++) {
            if (retalhos[0][i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se uma borda que colidiu é uma borda na parte inferior
     *
     * @param id
     * @return
     */
    public boolean verificarBordaInferior(int id) {
        //Borda inferior
        for (int i = 0; i < largura; i++) {
            if (retalhos[altura - 1][i].getId() == id) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retorna a coordenada minima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retornarValorMinimoBordaX() {
        return retalhos[0][0].getCoordenadaX() + tile;
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retornarValorMinimoBordaY() {
        return retalhos[0][0].getCoordenadaY() + tile;
    }

    /**
     * Retorna a coordenada máxima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retornarValorMaximoBordaX() {
        return retalhos[0][largura - 1].getCoordenadaX() - tile;
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retornarValorMaximoBordaY() {
        return retalhos[altura - 1][0].getCoordenadaY() - tile;
    }

    /**
     * Reset do ambiente de simulação
     *
     */
    public void limparTudo() {
        retalhos = new Retalho[altura][largura];
    }

    /**
     * Força a rendererização da tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void atualizarTela() throws ErroExecucaoBiblioteca, InterruptedException {
        g.renderizar();
    }

    /**
     * Retorna o retalho de uma determinada posição
     *
     * @param x
     * @param y
     * @param altura
     * @param largura
     * @return
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public Retalho getRetalho(int x, int y, int altura, int largura) throws ErroExecucaoBiblioteca, InterruptedException {
        Retalho retalhoRetorno = null;
        int fatorDiferenca = 8;
        for (int i = 0; i < this.altura; i++) {
            for (int j = 0; j < this.largura; j++) {
                if ((x > retalhos[i][j].getCoordenadaX() && x < retalhos[i][j].getCoordenadaX() + tile)
                        && (y > retalhos[i][j].getCoordenadaY() && y < retalhos[i][j].getCoordenadaY() + tile)) {
                    int diferenca_X = (x + largura) - (retalhos[i][j].getCoordenadaX() + tile);
                    int diferenca_Y = (y + altura) - (retalhos[i][j].getCoordenadaY() + tile);

                    if (diferenca_X > 0 && diferenca_X < fatorDiferenca || diferenca_Y > 0 && diferenca_Y < fatorDiferenca) {
                        retalhoRetorno = retalhos[i][j];
                    } else {

                        int indiceI = i;
                        int indiceJ = j;

                        if (math.valor_absoluto(diferenca_Y) > fatorDiferenca) {
                            indiceI = i + 1;
                        } else if (math.valor_absoluto(diferenca_X) > fatorDiferenca) {
                            indiceJ = j + 1;
                        }
                        if (indiceI < this.altura && indiceJ < this.largura) {
                            retalhoRetorno = retalhos[indiceI][indiceJ];
                        }
                    }
                }
            }
        }

        return retalhoRetorno;
    }

    /**
     * Retorna o número de agentes que está em um determinado retalho
     *
     * @param retalho
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int buscarAgentesRetalho(Retalho retalho) throws ErroExecucaoBiblioteca, InterruptedException {
        int numeroAgentes = 0;
        for (IAgente agente : GerenciadorExecucao.getInstance().getListaAgentes()) {
            if ((agente.retornarCoordenadaX() > retalho.getCoordenadaX()
                    && agente.retornarCoordenadaX() < retalho.getCoordenadaX() + tile)
                    && (agente.retornarCoordenadaY() > retalho.getCoordenadaY()
                    && agente.retornarCoordenadaY() < retalho.getCoordenadaY() + tile)) {
                numeroAgentes++;
            }
        }

        return numeroAgentes;
    }

    /**
     * Retorna o id do primeiro agente encontrado naquele retalho que seja
     * diferente do agente atual da simulação
     *
     * @param retalho
     * @param idAgenteAtual
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int buscarIdAgenteRetalho(Retalho retalho, int idAgenteAtual) throws ErroExecucaoBiblioteca, InterruptedException {

        for (IAgente agente : GerenciadorExecucao.getInstance().getListaAgentes()) {
            if ((agente.retornarCoordenadaX() > retalho.getCoordenadaX()
                    && agente.retornarCoordenadaX() < retalho.getCoordenadaX() + tile)
                    && (agente.retornarCoordenadaY() > retalho.getCoordenadaY()
                    && agente.retornarCoordenadaY() < retalho.getCoordenadaY() + tile)) {
                if (idAgenteAtual != agente.retornarId()) {
                    return agente.retornarId();
                }
            }
        }

        return 0;
    }

    /**
     * Chama o método que desenha os componentes
     *
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void renderizar() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        rodar();
    }

    /**
     * Executa apenas uma atualização da tela e não trava a execução do resto da
     * simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public void renderizarParcial() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        rodarSimples();
    }

    /**
     * Redesenha novamente os agentes para não atualizar a tela toda novamente
     *
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void renderizarAgentes() throws ErroExecucaoBiblioteca, InterruptedException {
        desenharAgentes();
        desenharRetalhos();
        g.renderizar();
    }

    /**
     * Define a cor de fundo para todos os retalhos
     *
     * @param cor
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void definirCorFundo(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                retalhos[i][j].set_cor(cor);
            }
        }

        desenharRetalhos();
    }

    /**
     * Desenha o componente de monitor na tela
     *
     * @param titulo
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenharMonitor(String nome, String titulo, int yInicial, int yFinal, String valorAtual) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        int posicaoYi = yInicial;
        int posicaoYf = yFinal;

        g.definir_cor(Graficos.COR_BRANCO);

        //Borda esquerda
        g.desenhar_linha(posicaoXInicial, posicaoYi, posicaoXFinal, posicaoYf);

        //Borda inferior
        g.desenhar_linha(posicaoXInicial, posicaoYf, larguraPainelComponentes - posicaoXFinal, posicaoYf);

        //Borda direita
        g.desenhar_linha(larguraPainelComponentes - posicaoXInicial, posicaoYf, larguraPainelComponentes - posicaoXFinal, posicaoYi);

        //Desenha o título
        g.desenhar_texto(posicaoXInicial + 5, (posicaoYi - 5), titulo);

        //Desenha o resto da linha após o título
        int largura_texto = g.largura_texto(titulo);
        g.desenhar_linha(posicaoXInicial + largura_texto + 10, posicaoYi, larguraPainelComponentes - posicaoXFinal, posicaoYi);

        g.desenhar_retangulo(posicaoXInicial + 13, (posicaoYi + 10), larguraPainelComponentes - 60, 33, false, false);

        //Desenha as informações do monitor
        int x_inicial = ((larguraPainelComponentes - 50) / 2);
        String texto = valorAtual;

        g.desenhar_texto(x_inicial, posicaoYi + 20, texto);

        GerenciadorComponentes.criarMonitor(posicaoXInicial, posicaoXInicial, posicaoYi, posicaoYf, posicaoYf, nome, valorAtual, (posicaoYf - posicaoYi), (posicaoXFinal - posicaoXFinal), titulo);
    }

    private void desenharSlider(String nome, String titulo, int yInicial, int yFinal, double valorMinimo, double valor_atual, double valorMaximo, double valorDisplay) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {

        int yplay = yInicial + 7;
        int larg = larguraPainelComponentes - 130;
        int paddingSize = 8;
        int posicaoYi = yInicial;
        int posicaoYf = yFinal;

        //Desenha a parte externa do retângulo do slider
        g.definir_cor(Graficos.COR_BRANCO);

        //Borda esquerda
        g.desenhar_linha(posicaoXInicial, posicaoYi, posicaoXFinal, posicaoYf);

        //Borda inferior
        g.desenhar_linha(posicaoXInicial, posicaoYf, larguraPainelComponentes - posicaoXFinal, posicaoYf);

        //Borda direita
        g.desenhar_linha(larguraPainelComponentes - posicaoXInicial, posicaoYf, larguraPainelComponentes - posicaoXFinal, posicaoYi);
        g.desenhar_texto(posicaoXInicial + 5, (posicaoYi - 5), titulo);//Desenha o título

        //Desenha a borda superior após o título
        int largura_texto = g.largura_texto(titulo);
        g.desenhar_linha(posicaoXInicial + largura_texto + 10, posicaoYi, larguraPainelComponentes - posicaoXFinal, posicaoYi);

        g.desenhar_retangulo(posicaoXInicial + 10, yplay + 15, larg, 4, false, true);

        //Desenha o retangulo sobre o slider
        g.desenhar_retangulo((int) valor_atual, yplay + 15 - (paddingSize / 4), paddingSize, paddingSize, false, true);

        //Desenha a informação dos valores do slider
        g.desenhar_texto(larguraPainelComponentes - 100, yplay + 10, "" + valorDisplay + "/" + valorMaximo);

        if (GerenciadorComponentes.criarSlider(posicaoXInicial + 10, posicaoXInicial + 10 + larg, posicaoYi, posicaoYf, posicaoYf, nome, valor_atual, valorMaximo, valorMinimo, posicaoYf - posicaoYi, larg, titulo, valorDisplay)) {
            ((Slider) GerenciadorComponentes.getUltimoComponente()).calcular_valor_inicial(valor_atual);
        }
    }

    /**
     * Desenha o componente do tipo Interruptor
     *
     * @param nome
     * @param titulo
     * @param yInicial
     * @param yFinal
     * @param valor
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenharInterruptor(String nome, String titulo, int yInicial, int yFinal, boolean valor) throws ErroExecucaoBiblioteca, InterruptedException {
        int alturaImagem = g.altura_imagem(enderecoImagemSwitchOn);
        int larguraImagem = g.largura_imagem(enderecoImagemSwitchOff);
        int larg = larguraPainelComponentes - (posicaoXInicial * 2);

        g.definir_cor(Graficos.COR_BRANCO);

        //Desenha o retângulo em volta da imagem
        g.desenhar_retangulo(posicaoXInicial, yInicial, larg, alturaImagem + 10, false, false);

        //Desenha o título
        g.desenhar_texto(posicaoXInicial + larguraImagem + 10, yInicial + (alturaImagem / 2), titulo);

//        UtilSimulador.setLog("Vai mudar o estado para: " + valor);
        if (valor) {
            g.desenhar_imagem(posicaoXInicial + 5, yInicial + 5, enderecoImagemSwitchOn);
        } else {
            g.desenhar_imagem(posicaoXInicial + 5, yInicial + 5, enderecoImagemSwitchOff);
        }

        GerenciadorComponentes.criarInterruptor(posicaoXInicial + 5, posicaoXInicial + larguraImagem, yInicial, yFinal + 33, yFinal + 33, nome, valor, 33, larg, titulo);
    }

    /**
     * Cria um novo componente do tipo monitor considerando as posições dos
     * outros componentes que já foram adicionados
     *
     * @param nome
     * @param titulo
     * @param valorAtual
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void criarMonitor(String nome, String titulo, String valorAtual) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        if (GerenciadorComponentes.listaTemRegistro()) {
            Componente ultimo_componente = GerenciadorComponentes.getUltimoComponente();

            desenharMonitor(nome, titulo, ultimo_componente.getProximoY(), ultimo_componente.getProximaPosicaoY2(), valorAtual);
        } else {
            desenharMonitor(nome, titulo, alturaPainelComponentes + posicaoYInicial, alturaPainelComponentes + posicaoYFinal, valorAtual);
        }
    }

    /**
     * Cria um novo componente do tipo slider considerando as posições dos
     * outros componentes que já foram adicionados
     *
     * @param nome
     * @param titulo
     * @param valor_minimo
     * @param valor_padrao
     * @param valor_maximo
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void criarSlider(String nome, String titulo, double valor_minimo, double valor_padrao, double valor_maximo) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        if (GerenciadorComponentes.listaTemRegistro()) {
            Componente ultimoComponente = GerenciadorComponentes.getUltimoComponente();

            desenharSlider(nome, titulo, ultimoComponente.getProximoY(), ultimoComponente.getProximaPosicaoY2(), valor_minimo, valor_padrao, valor_maximo, valor_padrao);
        } else {
            desenharSlider(nome, titulo, alturaPainelComponentes + posicaoYInicial, alturaPainelComponentes + posicaoYFinal, valor_minimo, valor_padrao, valor_maximo, valor_padrao);
        }
    }

    /**
     * Cria um componente do tipo interruptor considerando os outros componentes
     * que já foram adicionados
     *
     * @param nome
     * @param titulo
     * @param valor
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void criarInterruptor(String nome, String titulo, boolean valor) throws ErroExecucaoBiblioteca, InterruptedException {
        if (GerenciadorComponentes.listaTemRegistro()) {
            Componente ultimoComponente = GerenciadorComponentes.getUltimoComponente();

            desenharInterruptor(nome, titulo, ultimoComponente.getProximoY(), ultimoComponente.getProximaPosicaoY2(), valor);
        } else {
            desenharInterruptor(nome, titulo, alturaPainelComponentes + posicaoYInicial, alturaPainelComponentes + posicaoYFinal, valor);
        }
    }

    /**
     * Método para atualizar os componentes que já estão na lista na tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    private void desenharComponentes() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        for (Componente componente : GerenciadorComponentes.getListaComponentes()) {
            switch (componente.getTipoComponente()) {
                case monitor:
                    Monitor monitor = (Monitor) componente;
                    desenharMonitor(monitor.getNome(), monitor.getTitulo(), monitor.getY1(), monitor.getY2(), monitor.getValorAtual());
                    break;
                case slider:
                    Slider slider = (Slider) componente;
                    desenharSlider(slider.getNome(), slider.getTitulo(), slider.getY1(), slider.getY2(), slider.getValor_minimo(), slider.getValor_atual(), slider.getValor_maximo(), slider.getValor_display());
                    break;
                case interruptor:
                    Interruptor interruptor = (Interruptor) componente;
                    desenharInterruptor(interruptor.getNome(), interruptor.getTitulo(), interruptor.getY1(), interruptor.getY2(), interruptor.isLigado());
                    break;
            }
        }
    }

    /**
     * Verifica as posições do mouse para saber se foi clicado dentro de um
     * slider
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void controleMouseSlider() throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorComponentes.verificarMouseDentroSlider(m.posicao_x(), m.posicao_y(), m.algum_botao_pressionado());
    }

    /**
     * Controla as ações do mouse para o componente de switch
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void controleMouseSwitch() throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorComponentes.verificarMouseDentroInterruptor(m.posicao_x(), m.posicao_y(), m.algum_botao_pressionado());
    }

    /**
     * Procura por um retalho na lista e, pelo seu id, atribui a cor passada por
     * parâmetro
     *
     * @param id
     * @param cor
     */
    public void definirCorRetalho(int id, int cor) {
        Retalho retalho_encontrado = null;

        //Busca o retalho na lista
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                if (retalhos[i][j].getId() == id) {
                    retalho_encontrado = retalhos[i][j];
                    break;
                }
            }

            if (retalho_encontrado != null) {
                break;
            }
        }

        //Atribui a cor a ele
        if (retalho_encontrado != null) {
            retalho_encontrado.set_cor(cor);
        }
    }

    /**
     * Retorna qual é o tipo da parede do retalho (X ou Y)
     *
     * @param id
     * @return
     */
    public TipoParede verificarRetalhoEhParede(int id) {
        //Busca o retalho na lista
        TipoParede tipo_parede = null;
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                if (retalhos[i][j].getId() == id) {
                    if (retalhos[i][j].ehParede()) {
                        tipo_parede = retalhos[i][j].getTipoParede();
                        break;
                    }
                }
            }
        }

        return tipo_parede;
    }

    /**
     * Retorna a altura máxima (y) do painel onde ocorre a simulação
     *
     * @return
     */
    public int getAlturaMaximaPainelSimulacao() {
        return alturaTela - alturaRodape - 20;
    }

    /**
     * Retorna a altura mínima (y) do painel onde ocorre a simulação
     *
     * @return
     */
    public int getAlturaMinimaPainelSimulacao() {
        return alturaPainelComponentes;
    }

    /**
     * Retorna a largura máxima (x) do painel de simulação
     *
     * @return
     */
    public int getLarguraMaximaPainelSimulacao() {
        return larguraTela - 1;
    }

    /**
     * Retorna a largura mínima (x) do painel de simulação
     *
     * @return
     */
    public int getLarguraMinimaPainelSimulacao() {
        return larguraPainelComponentes;
    }

    /**
     * Retorna uma instância de um retalho de acordo com o seu id
     *
     * @param id
     * @return
     */
    public Retalho buscarRetalhoPorId(int id) {

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                if (retalhos[i][j].getId() == id) {
                    return retalhos[i][j];
                }
            }
        }

        return null;
    }

    /**
     * Define quando a janela da simulação está visível para liberar o Portugol
     * Studio OBS: Quando era aberto sem isso, era possível abrir várias
     * instâncias da simulação
     *
     * @return
     */
    public boolean janelaVisivel() {
        return g.esta_visivel();
    }

    /**
     * Define qual será o título do experimento da simulação
     * 
     * @param tituloSimulacao 
     */
    public void setTituloSimulacao(String tituloSimulacao) {
        this.tituloSimulacao = tituloSimulacao;
    }
}
