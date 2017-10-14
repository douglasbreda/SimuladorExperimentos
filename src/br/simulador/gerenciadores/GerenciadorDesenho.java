/*
Classe para desenhar os componentes visuais
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.plugin.biblioteca.componentes.Componente;
import br.simulador.plugin.biblioteca.componentes.TipoComponente;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.ProgramaVazio;
import br.univali.portugol.nucleo.bibliotecas.Graficos;
import br.univali.portugol.nucleo.bibliotecas.Matematica;
import br.univali.portugol.nucleo.bibliotecas.Mouse;
import br.univali.portugol.nucleo.bibliotecas.base.Biblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class GerenciadorDesenho {

    final int LARGURA = 31;
    final int ALTURA = 26;
    final int tile = 18;
    int cor_atual = 0;

    private final String PASTA_DE_IMAGENS = "D:/Desenvolvimento/TTC II/Plugin Experimentos/SimuladorExperimentos/SimuladorExperimentos/src/br/simulador/imagens/";
    private int LARGURA_DA_TELA;
    private int ALTURA_DA_TELA;
    private int imagem_fundo;
    private int altura_imagem_fundo;
    private boolean clicou;
    private int cor_predominante;
    private final int LARGURA_DO_BOTAO = 28;
    private final int ALTURA_DO_BOTAO = 28;
    private int altura_rodape;
    private int ultimo_x;
    private int ultimo_y;
    private int status;
    private int largura_painel_componentes = 250;
    private int altura_painel_botoes = 50;
    private final int[] REFS_INT = new int[4];

    private final int INDICE_IMAGEM_BOTAO_INICIAR_0 = 0;
    private final int INDICE_IMAGEM_BOTAO_INICIAR_HOVER_1 = 1;
    private final int INDICE_IMAGEM_BOTAO_PARAR_2 = 2;
    private final int INDICE_IMAGEM_BOTAO_PARAR_HOVER_3 = 3;

    Retalho[][] retalhos = new Retalho[ALTURA][LARGURA];
    int[] cores = {0xFFFFFF, 0xE4E4E4, 0x888888, 0x222222, 0xFFA7D1, 0xE50000, 0xE59500, 0xA06A42, 0xE5D900, 0x94E044, 0x02BE01, 0x00D3DD, 0x0083C7, 0x0000EA, 0xCF6EE4, 0x820080};

    Graficos g = new Graficos();
    Mouse m = new Mouse();
    Matematica math = new Matematica();
    private List<Componente> listaComponentes = new ArrayList<>();

    /**
     * Inicia a tela onde será executada a simulação
     *
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     */
    public void inicializar_tela() throws InterruptedException, InvocationTargetException, ErroExecucaoBiblioteca, ErroExecucao {

        try {
            g = new Graficos();
            UtilSimulador.setLog("Instanciou gráficos");
            m = new Mouse();
            UtilSimulador.setLog("Instanciou mouse");
            g.inicializar(ProgramaVazio.novaInstancia(), null);
            UtilSimulador.setLog("Inicializou gráficos");
            ArrayList<Biblioteca> lista = new ArrayList<>();
            lista.add(g);
            m.inicializar(ProgramaVazio.novaInstancia(), lista);

            //Inicializa os retalhos
            iniciar_retalhos();
            g.iniciar_modo_grafico(false);
//            g.definir_dimensoes_janela(LARGURA * tile, (ALTURA + 2) * tile);
            g.definir_dimensoes_janela(810, 550);
            configurar();
            //Inicialização das variáveis (Separar)
            rodar();
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa os retalhos da simulação (O "chão" da simulação)
     */
    private void iniciar_retalhos() {
        int id = 0;
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                retalhos[i][j] = new Retalho(++id);
            }
        }
    }

    /**
     * Dispara a thread necessária por ficar renderizando a tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void rodar() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
//        new Thread(() -> {
//            while (true) {
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
//            }
//        }).start();
    }

    /**
     * Configurações iniciais necessárias para a simulação
     */
    private void configurar() throws ErroExecucaoBiblioteca, InterruptedException {
        imagem_fundo = g.carregar_imagem(PASTA_DE_IMAGENS + "fundo.jpg");
        carregar_imagens_botao("play", INDICE_IMAGEM_BOTAO_INICIAR_0, INDICE_IMAGEM_BOTAO_INICIAR_HOVER_1);
        carregar_imagens_botao("stop", INDICE_IMAGEM_BOTAO_PARAR_2, INDICE_IMAGEM_BOTAO_PARAR_HOVER_3);

//        REFS_INT[INDICE_IMAGEM_BOTAO_INICIAR_0] = 
        LARGURA_DA_TELA = g.largura_tela();
        ALTURA_DA_TELA = g.altura_tela();
        altura_imagem_fundo = g.altura_imagem(imagem_fundo);
    }

    /**
     * Retorna se o mouse está sobre algum dos botões (Play ou Stop)
     *
     * @param x_do_botao
     * @param y_do_botao
     * @return
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private boolean mouse_esta_em_cima_do_botao(int x_do_botao, int y_do_botao) throws ErroExecucao, InterruptedException {
        int mouse_x = m.posicao_x();
        int mouse_y = m.posicao_y();

        UtilSimulador.setLog("X: " + mouse_x);
        UtilSimulador.setLog("Y: " + mouse_y);

        boolean mouse_dentro_do_botao_na_horizontal = (mouse_x >= x_do_botao
                && mouse_x <= x_do_botao + LARGURA_DO_BOTAO);
        boolean mouse_dentro_do_botao_na_vertical = (mouse_y >= y_do_botao && mouse_y <= y_do_botao + ALTURA_DO_BOTAO);
        return mouse_dentro_do_botao_na_horizontal && mouse_dentro_do_botao_na_vertical;

    }

    private boolean desenhar_botao(int x_do_botao, int y_do_botao, int botao_normal, int botao_hover) throws ErroExecucao, InterruptedException {
//        if (this.interrupcaoSolicitada || Thread.currentThread().isInterrupted()) {
//            throw new InterruptedException();
//        }

        boolean mouse_em_cima_do_botao = mouse_esta_em_cima_do_botao(x_do_botao, y_do_botao);
        if (mouse_em_cima_do_botao) {
            g.desenhar_imagem(x_do_botao, y_do_botao, botao_hover);

        } else {
            g.desenhar_imagem(x_do_botao, y_do_botao, botao_normal);

        }

        if (mouse_em_cima_do_botao && clicou) {
            clicou = false;
            executar_acao(botao_normal);

        }

        return false;

    }

    /**
     * Desenha o rodapé da simulação
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void desenhar_rodape() throws ErroExecucao, InterruptedException {
//        if (this.interrupcaoSolicitada || Thread.currentThread().isInterrupted()) {
//            throw new InterruptedException();
//        }

        g.definir_cor(cor_predominante);
        g.definir_opacidade(200);
        g.desenhar_retangulo(0, altura_imagem_fundo, LARGURA_DA_TELA, ALTURA_DA_TELA - altura_imagem_fundo, false, true);

    }

    /**
     * Desenha as informações que ficam no rodapé da simulação
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void desenhar_informacoes_rodape() throws ErroExecucao, InterruptedException {
//        if (this.interrupcaoSolicitada || Thread.currentThread().isInterrupted()) {
//            throw new InterruptedException();
//        }

        g.definir_cor(Graficos.COR_BRANCO);
        g.definir_estilo_texto(false, true, false);

        int totalAgentes = 0;

        if (GerenciadorExecucao.getInstance().getListaAgentes() != null) {
            totalAgentes = GerenciadorExecucao.getInstance().getListaAgentes().size();
        }

        g.desenhar_texto(10, altura_imagem_fundo + 12, "Total de Agentes: " + totalAgentes);
        int largura_total_agentes = g.largura_texto("Total de Agentes: " + totalAgentes);

        if (status == 0) {
            g.desenhar_texto(10 + largura_total_agentes + 50, altura_imagem_fundo + 12, "Status: Parada");

        } else {
            g.desenhar_texto(10 + largura_total_agentes + 50, altura_imagem_fundo + 12, "Status: Executando");

        }
    }

    /**
     * Executa a ação de um botão
     *
     * @param botao_clicado
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void executar_acao(int botao_clicado) throws ErroExecucao, InterruptedException {
//        if (this.interrupcaoSolicitada || Thread.currentThread().isInterrupted()) {
//            throw new InterruptedException();
//        }

        boolean ___sw_break___1 = false;
        if (!___sw_break___1 && botao_clicado == REFS_INT[INDICE_IMAGEM_BOTAO_INICIAR_0]) {
            UtilSimulador.setLog("Iniciou\n");
            status = 1;
            ___sw_break___1 = true;

        }

        if (!___sw_break___1 && botao_clicado == REFS_INT[INDICE_IMAGEM_BOTAO_PARAR_2]) {
            UtilSimulador.setLog("Parou\n");
            status = 0;
            ___sw_break___1 = true;

        }

    }

    /**
     * Desenha os botões de play e stop
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void desenhar_botoes() throws ErroExecucao, InterruptedException {
//        if (this.interrupcaoSolicitada || Thread.currentThread().isInterrupted()) {
//            throw new InterruptedException();
//        }

        int margem = 10;
        int espaco_entre_botoes = LARGURA_DO_BOTAO + 3;
        int x_do_botao = margem;
        desenhar_botao(x_do_botao, margem, REFS_INT[INDICE_IMAGEM_BOTAO_INICIAR_0],
                REFS_INT[INDICE_IMAGEM_BOTAO_INICIAR_HOVER_1]);
        x_do_botao = x_do_botao + espaco_entre_botoes;
        desenhar_botao(x_do_botao, margem, REFS_INT[INDICE_IMAGEM_BOTAO_PARAR_2],
                REFS_INT[INDICE_IMAGEM_BOTAO_PARAR_HOVER_3]);
        x_do_botao = x_do_botao + espaco_entre_botoes;

    }

    /**
     * Carrega as imagens referente aos botões de inicio e fim da simulação
     *
     * @param nome_imagem
     * @param imagem_normal
     * @param imagem_hover
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void carregar_imagens_botao(String nome_imagem, int imagem_normal, int imagem_hover) throws ErroExecucaoBiblioteca, InterruptedException {
        String nome_imagem_normal = nome_imagem + ".png";
        String nome_imagem_hover = nome_imagem + "_hover.png";
        REFS_INT[imagem_normal] = g.carregar_imagem(PASTA_DE_IMAGENS + nome_imagem_normal);
        REFS_INT[imagem_hover] = g.carregar_imagem(PASTA_DE_IMAGENS + nome_imagem_hover);
    }

    /**
     * Responsável por desenhar e renderizar os componentes visuais
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {

        g.definir_titulo_janela("Simulador de Experimentos - Gas in a box");

        g.desenhar_imagem(0, 0, imagem_fundo);

        desenhar_retalhos();

        desenhar_botoes();

        desenhar_rodape();

        desenhar_informacoes_rodape();

        desenhar_agentes();

        desenhar_linha();

        desenhar_monitor("Monitor 1");
        
        desenhar_monitor_informacao("Teste 1");

//        desenhar_painel_superior();
//        desenhar_painel_inferior();
        tratar_cliques();

//        atualizar_status_simulacao(false);
//
//        atualizar_total_agentes(0);
    }

    /**
     * Desenha as linhas divisórias da tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_linha() throws ErroExecucaoBiblioteca, InterruptedException {

        g.definir_cor(g.COR_AMARELO);
        g.desenhar_linha(largura_painel_componentes, altura_painel_botoes, largura_painel_componentes, altura_imagem_fundo);
        g.definir_cor(g.COR_AMARELO);
        g.desenhar_linha(0, altura_painel_botoes, LARGURA_DA_TELA, altura_painel_botoes);
    }

    /**
     * Método que aplica as cores conforme o usuário clica em uma posição da
     * tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void controle() throws ErroExecucaoBiblioteca, InterruptedException {
        int i = m.posicao_y() / tile;
        int j = m.posicao_x() / tile;
        if (m.botao_pressionado(m.BOTAO_ESQUERDO)) {
            if (i > 1 && i < ALTURA && j < LARGURA) {
                retalhos[i][j].set_cor(cor_atual);
            }
        }
    }

    /**
     * Verifica os cliques do usuário para saber se clicou em algum botão
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void tratar_cliques() throws ErroExecucao, InterruptedException {
//        if (this.interrupcaoSolicitada || Thread.currentThread().isInterrupted()) {
//            throw new InterruptedException();
//        }

        if (m.algum_botao_pressionado()) {
            if (ultimo_x <= 0 && ultimo_y <= 0) {
                ultimo_x = m.posicao_x();
                ultimo_y = m.posicao_y();

            }

        } else {
            if (ultimo_x >= 0 && ultimo_y >= 0) {
                int delta_x = m.posicao_x() - ultimo_x;
                int delta_y = m.posicao_y() - ultimo_y;
                if (delta_x >= 0 && delta_x <= LARGURA_DO_BOTAO && delta_y >= 0 && delta_y <= ALTURA_DO_BOTAO) {
                    clicou = true;

                }

            }

            ultimo_x = -1;
            ultimo_y = -1;

        }

    }

    /**
     * Desenha o painel inferior com informações relevantes a simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_painel_inferior() throws ErroExecucaoBiblioteca, InterruptedException {
        g.definir_cor(0xFA3332);
        g.desenhar_retangulo(0, ALTURA * tile, LARGURA * tile, ALTURA + 8, false, true);
    }

    /**
     * Desenha o painel superior com as opções de iniciar e parar a execução
     */
    private void desenhar_painel_superior() throws ErroExecucaoBiblioteca, InterruptedException {
        g.definir_cor(0x222222);
        g.desenhar_retangulo(0, 0, LARGURA * tile, ALTURA, false, false);
    }

    /**
     * Sobrecarga para colorir os retalhos com a cor default
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_retalhos() throws ErroExecucaoBiblioteca, InterruptedException {
        desenhar_retalhos(-1);
    }

    /**
     * Desenha os retalhos "chão" da simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_retalhos(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        int x = 0;
        int y = 0;
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {

                if (cor == -1) {
                    g.definir_cor(cores[retalhos[i][j].retornar_cor_retalho()]);
                } else {
                    retalhos[i][j].set_cor(cor);
                    g.definir_cor(cor);
                }

                x = (j * tile) + 250;
                y = (i * tile) + 50;
                g.desenhar_retangulo(x, y, tile, tile, false, true);
                retalhos[i][j].definir_coordenadas(x, y);
            }
        }
    }

    /**
     * Desenha os "agentes" na tela
     */
    private void desenhar_agentes() throws ErroExecucaoBiblioteca, InterruptedException {
        ArrayList<IAgente> listaAgente = GerenciadorExecucao.getInstance().getListaAgentes();

        if (listaAgente != null && listaAgente.size() > 0) {
            for (IAgente agente : listaAgente) {
                g.definir_cor(agente.retornar_cor_agente());
                g.desenhar_elipse(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), agente.retornar_largura_agente(), agente.retornar_altura_agente(), true);
            }
        }
    }

    /**
     * Verifica se a simulação ainda está executando para que possa parar as
     * threads caso ela seja finalizada
     *
     * @return
     */
    public boolean esta_executando() {
        return false;
//        return g.get_janela().estaVisivel();
    }

    /**
     * Retorna a altura da janela de simulação
     *
     * @return
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public int get_altura_janela() throws ErroExecucaoBiblioteca, InterruptedException {
        return g.altura_janela();
    }

    /**
     * Retorna a largura da janela
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int get_largura_janela() throws ErroExecucaoBiblioteca, InterruptedException {
        return g.largura_janela();
    }

    /**
     * Desenha os limites(bordas) da janela
     *
     * @param cor
     */
    public void desenhar_bordas(int cor) {
        //Borda superior
        for (int i = 0; i < LARGURA; i++) {
            retalhos[0][i].set_cor(cor);
            retalhos[0][i].definir_como_parede(true);
        }

        //Borda inferior
        for (int i = 0; i < LARGURA; i++) {
            retalhos[ALTURA - 1][i].set_cor(cor);
            retalhos[ALTURA - 1][i].definir_como_parede(true);
        }

        //Borda esquerda
        for (int i = 0; i < ALTURA; i++) {
//            UtilSimulador.setLog("Definida cor de borda para " + i + "/0");
            retalhos[i][0].set_cor(cor);
            retalhos[i][0].definir_como_parede(true);
        }

        //Borda direita
        for (int i = 0; i < ALTURA; i++) {
            retalhos[i][LARGURA - 1].set_cor(cor);
            retalhos[i][LARGURA - 1].definir_como_parede(true);
        }
    }

    /**
     * Retorna a coordenada minima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_minimo_borda_X() {
        return retalhos[0][0].getCoordenadaX() + tile;
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_minimo_borda_Y() {
        return retalhos[2][0].getCoordenadaY() + tile;
    }

    /**
     * Retorna a coordenada máxima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_maximo_borda_X() {
        return retalhos[0][LARGURA - 1].getCoordenadaX() - tile;
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_maximo_borda_Y() {
        return retalhos[ALTURA - 1][0].getCoordenadaY() - tile;
    }

    /**
     * Reset do ambiente de simulação
     */
    public void limpar_tudo() {
        retalhos = new Retalho[ALTURA][LARGURA];
        //inicializar_tela();
    }

    /**
     * Força a rendererização da tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void atualizar_tela() throws ErroExecucaoBiblioteca, InterruptedException {
        g.renderizar();
    }

    /**
     * Retorna o retalho de uma determinada posição
     *
     * @param x
     * @param y
     * @return
     */
    public Retalho get_retalho(int x, int y, int altura, int largura) throws ErroExecucaoBiblioteca, InterruptedException {
        Retalho retalho_retorno = null;
        int fator_diferenca = 8;
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                if ((x > retalhos[i][j].getCoordenadaX() && x < retalhos[i][j].getCoordenadaX() + tile)
                        && (y > retalhos[i][j].getCoordenadaY() && y < retalhos[i][j].getCoordenadaY() + tile)) {
                    int diferenca_X = (x + largura) - (retalhos[i][j].getCoordenadaX() + tile);
                    int diferenca_Y = (y + altura) - (retalhos[i][j].getCoordenadaY() + tile);

                    UtilSimulador.setLog("X do agente: " + x);
                    UtilSimulador.setLog("Y do agente: " + y);
                    UtilSimulador.setLog("Diferenca X: " + diferenca_X);
                    UtilSimulador.setLog("Diferenca Y: " + diferenca_Y);

                    if (diferenca_X > 0 && diferenca_X < fator_diferenca || diferenca_Y > 0 && diferenca_Y < fator_diferenca) {
                        retalho_retorno = retalhos[i][j];
                    } else {

                        int indiceI = i;
                        int indiceJ = j;

                        if (math.valor_absoluto(diferenca_Y) > fator_diferenca) {
                            indiceI = i + 1;
                        } else if (math.valor_absoluto(diferenca_X) > fator_diferenca) {
                            indiceJ = j + 1;
                        }
//                        }else if((diferenca_X + diferenca_Y) < (fator_diferenca * (-1))){
//                            indiceI = i - 1;
//                            indiceJ = j - 1;
//                        }
//                        if(diferenca_X > fator_diferenca)
//                            indiceI = i + 1;
//                        else if(diferenca_X < 0 && diferenca_X < (fator_diferenca * (-1)))
//                            indiceI = i - 1;
//                            
//                        if(diferenca_Y > fator_diferenca)
//                            indiceJ = j + 1;
//                        else if(diferenca_Y < 0 && diferenca_Y < (fator_diferenca * (-1)))
//                            indiceJ = j + 1;

                        retalho_retorno = retalhos[indiceI][indiceJ];
                    }
//                    break;
                }
            }
        }

        return retalho_retorno;
    }

    /**
     * Retorna o número de agentes que está em um determinado retalho
     *
     * @param retalho
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int buscar_agentes_no_retalho(Retalho retalho) throws ErroExecucaoBiblioteca, InterruptedException {
        int numero_agentes = 0;
        for (IAgente agente : GerenciadorExecucao.getInstance().getListaAgentes()) {
            if ((agente.retornar_coordenada_X() > retalho.getCoordenadaX()
                    && agente.retornar_coordenada_X() < retalho.getCoordenadaX() + tile)
                    && (agente.retornar_coordenada_Y() > retalho.getCoordenadaY()
                    && agente.retornar_coordenada_Y() < retalho.getCoordenadaY() + tile)) {
                numero_agentes++;
            }
        }

        return numero_agentes;
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
     * Redesenha novamente os agentes para não atualizar a tela toda novamente
     *
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void renderizar_agentes() throws ErroExecucaoBiblioteca, InterruptedException {
        desenhar_agentes();
        desenhar_retalhos();
        g.renderizar();
    }

    /**
     * Define a cor de fundo para todos os retalhos
     *
     * @param cor
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void definir_cor_fundo(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        desenhar_retalhos(cor);
    }

    /**
     * Desenha o componente de monitor na tela
     *
     * @param titulo
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_monitor(String titulo) throws ErroExecucaoBiblioteca, InterruptedException {
        int posicaoYi = 10;
        int posicaoYf = 60;

        g.definir_cor(g.COR_BRANCO);
        g.desenhar_linha(25, altura_painel_botoes + posicaoYi, 25, altura_painel_botoes + posicaoYf); //Borda esquerda
        g.desenhar_linha(25, altura_painel_botoes + posicaoYf, largura_painel_componentes - 25, altura_painel_botoes + posicaoYf); //Borda direita
        g.desenhar_linha(largura_painel_componentes - 25, altura_painel_botoes + posicaoYf, largura_painel_componentes - 25, altura_painel_botoes + posicaoYi);//Desenha o resto da linha superior
        g.desenhar_texto(30, altura_painel_botoes + (posicaoYi / 2), titulo);//Desenha o título
        int largura_texto = g.largura_texto(titulo);

        g.desenhar_linha(25 + largura_texto + 10, altura_painel_botoes + posicaoYi, largura_painel_componentes - 25, altura_painel_botoes + posicaoYi); //g.desenhar_retangulo(25, 10, largura_janela - 50, 50, falso, falso)
        g.desenhar_retangulo(38, altura_painel_botoes + (posicaoYi * 2), largura_painel_componentes - 75, (posicaoYi * 3), false, false);
        
        listaComponentes.add(Componente.criar(25, altura_painel_botoes + posicaoYi, 25, altura_painel_botoes + posicaoYf, TipoComponente.monitor));
    }

    /**
     * Desenha a informação do componente de monitor
     * @param informacao
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    private void desenhar_monitor_informacao(String informacao) throws ErroExecucaoBiblioteca, InterruptedException {
        g.definir_cor(g.COR_BRANCO);
        int x_inicial = ((largura_painel_componentes - 75) / 2);
        String texto = informacao;
        //Criar uma lista com os componentes para poder controlar onde começa um e termina o outro
        g.desenhar_texto(x_inicial, altura_painel_botoes + 30, texto);
    }
}
