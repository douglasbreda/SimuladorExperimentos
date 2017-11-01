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
import br.simulador.util.UtilSimulador;
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
import javax.swing.SwingUtilities;

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

    private int endereco_imagem_switch_on = 0;
    private int endereco_imagem_switch_off = 0;

    Retalho[][] retalhos = new Retalho[ALTURA][LARGURA];
    int[] cores = {0xFFFFFF, 0xE4E4E4, 0x888888, 0x222222, 0xFFA7D1, 0xE50000, 0xE59500, 0xA06A42, 0xE5D900, 0x94E044, 0x02BE01, 0x00D3DD, 0x0083C7, 0x0000EA, 0xCF6EE4, 0x820080};

    Graficos g = new Graficos();
    Mouse m = new Mouse();
    Matematica math = new Matematica();
    Teclado t = new Teclado();
    private final int posicaoYInicial = 10;// Posição de start da criação do primeiro componente
    private final int posicaoYFinal = 60; // Posição de final da criação do primeiro componente
    private final int posicaoXInicial = 15;
    private final int posicaoXFinal = 15;
    private final String imagem_switch_on = "switch_on_32.png";
    private final String imagem_switch_off = "switch_off_32.png";
    private int altura_rodape = 24;
    private final int COR_ESCURA_LINHA_DIVISORIA = 0x111111; 	// RGB = 17,17,17
    private final int COR_CLARA_LINHA_DIVISORIA = 0x4C4C4C; 	// RGB = 76,76,76

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
            t.inicializar(ProgramaVazio.novaInstancia(), lista);

            //Inicializa os retalhos
            iniciar_retalhos();
            g.iniciar_modo_grafico(false);
//            g.definir_dimensoes_janela(LARGURA * tile, (ALTURA + 2) * tile);
            g.definir_dimensoes_janela(810, 560);
            configurar();
            //Inicialização das variáveis (Separar)
            rodar_simples();
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
                retalhos[i][j] = new Retalho(++id, Graficos.COR_BRANCO);
            }
        }
    }

    /**
     * Chamada do método para criar os componentes, e continuar com as
     * configurações iniciais Depois, esse método fica rodando em loop enquanto
     * o usuário não iniciar a simulação
     */
    private void rodar_simples() {
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
//        new Thread(() -> {
        //Just for tests
//        criar_monitor("monitor_teste", "Monitor 1", "Teste 1");
//        criar_slider("slider_teste", "Slider 1", 0, 12.5, 50);
//        criar_slider("slider_teste", "Slider 1", 0, 10, 100);
//        criar_monitor("monitor_outro", "Monitor 2", "Teste 2");
//        criar_monitor("monitor_mais_um", "Monitor 3", "Teste 2");
//        criar_interruptor("interruptor_1", "Teste Switch", false);
//        criar_slider("slider_outro", "Slider 1", 0, 12.5, 50);
//        definir_cor_retalho(45, Graficos.COR_AZUL);
//        desenhar_retalhos();
//            definir_cor_fundo(Graficos.COR_VERDE);
        boolean isExecutando = GerenciadorExecucao.getInstance().isExecutando();
        do {
            rodar_simples();
        } while (!isExecutando);

        //rodar_controle_botoes();
    }

    private boolean executarThread = false;

    private void rodar_controle_botoes() {
        if (!executarThread) {
            executarThread = true;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!t.tecla_pressionada(Teclado.TECLA_ESC)) {
//                            try {
                            System.out.println("");
//                            desenhar_botoes();
////                            int imagem_nova = g.renderizar_imagem(810, 560);
////                            g.liberar_imagem(imagem_nova);
//                            } catch (ErroExecucao ex) {
//                                Logger.getLogger(GerenciadorDesenho.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(GerenciadorDesenho.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                        }
                        System.out.println("Saiu");
                    } catch (ErroExecucaoBiblioteca ex) {
                        Logger.getLogger(GerenciadorDesenho.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GerenciadorDesenho.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }

    /**
     * Configurações iniciais necessárias para a simulação
     */
    private void configurar() throws ErroExecucaoBiblioteca, InterruptedException {
        imagem_fundo = g.carregar_imagem(PASTA_DE_IMAGENS + "fundo_exato.png");
        carregar_imagens_botao("play", INDICE_IMAGEM_BOTAO_INICIAR_0, INDICE_IMAGEM_BOTAO_INICIAR_HOVER_1);
        carregar_imagens_botao("stop", INDICE_IMAGEM_BOTAO_PARAR_2, INDICE_IMAGEM_BOTAO_PARAR_HOVER_3);

        endereco_imagem_switch_on = g.carregar_imagem(PASTA_DE_IMAGENS + imagem_switch_on);
        endereco_imagem_switch_off = g.carregar_imagem(PASTA_DE_IMAGENS + imagem_switch_off);

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

//        UtilSimulador.setLog("X: " + mouse_x);
//        UtilSimulador.setLog("Y: " + mouse_y);
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

//        g.definir_cor(cor_predominante);
//        g.definir_opacidade(200);
//        g.desenhar_retangulo(0, altura_imagem_fundo, LARGURA_DA_TELA, ALTURA_DA_TELA - altura_imagem_fundo, false, true);
    }

    /**
     * Desenha as informações que ficam no rodapé da simulação
     *
     * @throws ErroExecucao
     * @throws InterruptedException
     */
    private void desenhar_informacoes_rodape() throws ErroExecucao, InterruptedException {

        g.definir_cor(Graficos.COR_BRANCO);
        g.definir_estilo_texto(false, true, false);

        int totalAgentes = 0;

        if (GerenciadorExecucao.getInstance().getListaAgentes() != null) {
            totalAgentes = GerenciadorExecucao.getInstance().getListaAgentes().size();
        }

        g.desenhar_texto(10, altura_imagem_fundo - altura_rodape, "Total de Agentes: " + totalAgentes);
        int largura_total_agentes = g.largura_texto("Total de Agentes: " + totalAgentes);
        int largura_status = 0;

        if (status == 0) {
            g.desenhar_texto(10 + largura_total_agentes + 50, altura_imagem_fundo - altura_rodape, "Status: Parada");
            largura_status = g.largura_texto("Status: Parada");

        } else {
            g.desenhar_texto(10 + largura_total_agentes + 50, altura_imagem_fundo - altura_rodape, "Status: Executando");
            largura_status = g.largura_texto("Status: Executando");
        }

        int total_ticks = GerenciadorExecucao.getInstance().getTicks();
        g.desenhar_texto(10 + largura_total_agentes + largura_status + 100, altura_imagem_fundo - altura_rodape, "Ticks: " + total_ticks);
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
            GerenciadorExecucao.getInstance().iniciar_simulacao();
            status = 1;
            ___sw_break___1 = true;

        }

        if (!___sw_break___1 && botao_clicado == REFS_INT[INDICE_IMAGEM_BOTAO_PARAR_2]) {
            UtilSimulador.setLog("Parou\n");
            GerenciadorExecucao.getInstance().setExecutando(false);
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

//        desenhar_rodape();
        desenhar_informacoes_rodape();

        desenhar_agentes();

        desenhar_componentes();

        desenhar_linha();

        tratar_cliques();
    }

    /**
     * Desenha as linhas divisórias da tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_linha() throws ErroExecucaoBiblioteca, InterruptedException {

        //As linhas desenhadas duas vezes dão efeito de profundidade (Extraído do exemplo da bateria do Portugol Studio)
        //Linha divisória vertical 
        g.definir_cor(COR_ESCURA_LINHA_DIVISORIA);
        g.desenhar_linha(largura_painel_componentes - 10, altura_painel_botoes - 10, largura_painel_componentes - 10, altura_imagem_fundo - altura_rodape - 10);

        g.definir_cor(COR_CLARA_LINHA_DIVISORIA);
        g.desenhar_linha(largura_painel_componentes - 9, altura_painel_botoes - 10, largura_painel_componentes - 9, altura_imagem_fundo - altura_rodape - 10);

        //Linha divisória horizontal superior
        g.definir_cor(COR_ESCURA_LINHA_DIVISORIA);
        g.desenhar_linha(0, altura_painel_botoes - 10, LARGURA_DA_TELA, altura_painel_botoes - 10);

        g.definir_cor(COR_CLARA_LINHA_DIVISORIA);
        g.desenhar_linha(0, altura_painel_botoes - 9, LARGURA_DA_TELA, altura_painel_botoes - 9);

        //Linha divisória horizontal inferior
        g.definir_cor(COR_ESCURA_LINHA_DIVISORIA);
        g.desenhar_linha(0, altura_imagem_fundo - altura_rodape - 10, LARGURA_DA_TELA, altura_imagem_fundo - altura_rodape - 10);

        g.definir_cor(COR_CLARA_LINHA_DIVISORIA);
        g.desenhar_linha(0, altura_imagem_fundo - altura_rodape - 9, LARGURA_DA_TELA, altura_imagem_fundo - altura_rodape - 9);
    }

    /**
     * Método que aplica as cores conforme o usuário clica em uma posição da
     * tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void controle() throws ErroExecucaoBiblioteca, InterruptedException {
        controle_mouse_slider();
        controle_mouse_switch();
//        int i = m.posicao_y() / tile;
//        int j = m.posicao_x() / tile;
//        if (m.botao_pressionado(m.BOTAO_ESQUERDO)) {
//            if (i > 1 && i < ALTURA && j < LARGURA) {
//                retalhos[i][j].set_cor(cor_atual);
//            }
//        }
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
//    private void desenhar_retalhos() throws ErroExecucaoBiblioteca, InterruptedException {
//        desenhar_retalhos(-1);
//    }
    /**
     * Desenha os retalhos "chão" da simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_retalhos() throws ErroExecucaoBiblioteca, InterruptedException {
        int x = 0;
        int y = 0;
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {

//                if (cor == -1) {
//                    g.definir_cor(Graficos.COR_BRANCO);
//                    g.definir_cor(cores[retalhos[i][j].retornar_cor_retalho()]);
//                } else {
//                    retalhos[i][j].set_cor(cor);
                g.definir_cor(retalhos[i][j].retornar_cor_retalho());
//                }

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
                g.definir_rotacao(agente.retornar_orientacao());
                desenhar_forma(agente);
//                g.desenhar_elipse(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), agente.retornar_largura_agente(), agente.retornar_altura_agente(), true);
//                g.desenhar_linha(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), agente.retornar_coordenada_X() + 10, agente.retornar_coordenada_Y() + 10);
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
    private void desenhar_forma(IAgente agente) throws ErroExecucaoBiblioteca, InterruptedException {
        switch (agente.retornar_forma_agente()) {
            case circulo:
                g.desenhar_elipse(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), agente.retornar_largura_agente(), agente.retornar_altura_agente(), true);
                break;
            case linha:
                g.desenhar_linha(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), agente.retornar_coordenada_X() + 30, agente.retornar_coordenada_Y() + 10);
                break;
            default:
                g.desenhar_elipse(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), agente.retornar_largura_agente(), agente.retornar_altura_agente(), true);
                break;
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

        desenhar_borda_superior(cor);

        desenhar_borda_inferior(cor);

        desenhar_borda_esquerda(cor);

        desenhar_borda_direita(cor);
    }

    /**
     * Desenha a borda superior da simulação
     * 
     * @param cor
     */
    public void desenhar_borda_superior(int cor) {
        //Borda superior
        for (int i = 0; i < LARGURA; i++) {
            retalhos[0][i].set_cor(cor);
            retalhos[0][i].definir_como_parede(true, TipoParede.parede_y);
        }
    }

    /**
     * Desenha a borda inferior da simulação
     *
     * @param cor
     */
    public void desenhar_borda_inferior(int cor) {
        //Borda inferior
        for (int i = 0; i < LARGURA; i++) {
            retalhos[ALTURA - 1][i].set_cor(cor);
            retalhos[ALTURA - 1][i].definir_como_parede(true, TipoParede.parede_y);
        }
    }

    /**
     * Desenha a borda direita da simulação
     *
     * @param cor
     */
    public void desenhar_borda_direita(int cor) {
        //Borda direita
        for (int i = 0; i < ALTURA; i++) {
            retalhos[i][LARGURA - 1].set_cor(cor);
            retalhos[i][LARGURA - 1].definir_como_parede(true, TipoParede.parede_x);
        }
    }

    /**
     * Desenha a borda esquerda da simulação
     *
     * @param cor
     */
    public void desenhar_borda_esquerda(int cor) {

        //Borda esquerda
        for (int i = 0; i < ALTURA; i++) {
            retalhos[i][0].set_cor(cor);
            retalhos[i][0].definir_como_parede(true, TipoParede.parede_x);
        }
    }

    /**
     * Verifica se a borda que colidiu é uma borda do lado esquerdo
     * @param id
     * @return
     */
    public boolean verificar_borda_esquerda(int id) {
        //Borda esquerda
        for (int i = 0; i < ALTURA; i++) {
            if (retalhos[i][0].get_id() == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se uma borda que colidiu é uma borda do lado direito
     * @param id
     * @return 
     */
    public boolean verificar_borda_direita(int id){
        //Borda direita
        for (int i = 0; i < ALTURA; i++) {
            if(retalhos[i][LARGURA - 1].get_id() == id)
                return true;
        }
        
        return false;
    }
    
    /**
     * Verifica se uma borda que colidiu é uma borda na parte superior
     * @param id
     * @return 
     */
    public boolean verificar_borda_superior(int id){
        //Borda superior
        for (int i = 0; i < LARGURA; i++) {
            if(retalhos[0][i].get_id() == id)
                return true;
        }
        return false;
    }
    
    /**
     * Verifica se uma borda que colidiu é uma borda na parte inferior
     * @param id
     * @return 
     */
    public boolean verificar_borda_inferior(int id){
        //Borda inferior
        for (int i = 0; i < LARGURA; i++) {
            if(retalhos[ALTURA - 1][i].get_id() == id)
                return true;
        }
        
        return false;
    }
    

    /**
     * Retorna a coordenada minima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_minimo_borda_X() {
        return retalhos[0][0].get_coordenadaX() + tile;
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_minimo_borda_Y() {
        return retalhos[0][0].get_coordenadaY() + tile;
    }

    /**
     * Retorna a coordenada máxima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_maximo_borda_X() {
        return retalhos[0][LARGURA - 1].get_coordenadaX() - tile;
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_maximo_borda_Y() {
        return retalhos[ALTURA - 1][0].get_coordenadaY() - tile;
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
                if ((x > retalhos[i][j].get_coordenadaX() && x < retalhos[i][j].get_coordenadaX() + tile)
                        && (y > retalhos[i][j].get_coordenadaY() && y < retalhos[i][j].get_coordenadaY() + tile)) {
                    int diferenca_X = (x + largura) - (retalhos[i][j].get_coordenadaX() + tile);
                    int diferenca_Y = (y + altura) - (retalhos[i][j].get_coordenadaY() + tile);

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
                        if (indiceI < ALTURA && indiceJ < LARGURA) {
                            retalho_retorno = retalhos[indiceI][indiceJ];
                        }
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
            if ((agente.retornar_coordenada_X() > retalho.get_coordenadaX()
                    && agente.retornar_coordenada_X() < retalho.get_coordenadaX() + tile)
                    && (agente.retornar_coordenada_Y() > retalho.get_coordenadaY()
                    && agente.retornar_coordenada_Y() < retalho.get_coordenadaY() + tile)) {
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
     * Executa apenas uma atualização da tela e não trava a execução do resto da
     * simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    public void renderizar_parcial() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        rodar_simples();
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
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                retalhos[i][j].set_cor(cor);
            }
        }

        desenhar_retalhos();
    }

    /**
     * Desenha o componente de monitor na tela
     *
     * @param titulo
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar_monitor(String nome, String titulo, int yInicial, int yFinal, String valor_atual) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        int posicaoYi = yInicial;
        int posicaoYf = yFinal;

//        g.definir_cor(g.COR_BRANCO);
        g.definir_cor(Graficos.COR_BRANCO);
//        g.definir_opacidade(200);

        //Borda esquerda
        g.desenhar_linha(posicaoXInicial, posicaoYi, posicaoXFinal, posicaoYf);

        //Borda inferior
        g.desenhar_linha(posicaoXInicial, posicaoYf, largura_painel_componentes - posicaoXFinal, posicaoYf);

        //Borda direita
        g.desenhar_linha(largura_painel_componentes - posicaoXInicial, posicaoYf, largura_painel_componentes - posicaoXFinal, posicaoYi);

        //Desenha o título
        g.desenhar_texto(posicaoXInicial + 5, (posicaoYi - 5), titulo);

        //Desenha o resto da linha após o título
        int largura_texto = g.largura_texto(titulo);
        g.desenhar_linha(posicaoXInicial + largura_texto + 10, posicaoYi, largura_painel_componentes - posicaoXFinal, posicaoYi);

        g.desenhar_retangulo(posicaoXInicial + 13, (posicaoYi + 10), largura_painel_componentes - 60, 33, false, false);

//        desenhar_monitor_informacao(valor_atual);
        //Desenha as informações do monitor
//        g.definir_cor(g.COR_BRANCO);
        int x_inicial = ((largura_painel_componentes - 50) / 2);
        String texto = valor_atual;

        g.desenhar_texto(x_inicial, posicaoYi + 20, texto);

        GerenciadorComponentes.criarMonitor(posicaoXInicial, posicaoXInicial, posicaoYi, posicaoYf, posicaoYf, nome, valor_atual, (posicaoYf - posicaoYi), (posicaoXFinal - posicaoXFinal), titulo);
    }

    private void desenhar_slider(String nome, String titulo, int yInicial, int yFinal, double valor_minimo, double valor_atual, double valor_maximo, double valor_display) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {

        int cor_lateral = g.criar_cor(180, 180, 180);
        int yplay = yInicial + 7;
        int larg = largura_painel_componentes - 130;
        int cor_botao = g.criar_cor(255, 255, 255);
        int padding_size = 8;
        int posicaoYi = yInicial;
        int posicaoYf = yFinal;

        //Desenha a parte externa do retângulo do slider
        g.definir_cor(Graficos.COR_BRANCO);
//        g.definir_opacidade(200);

        //Borda esquerda
        g.desenhar_linha(posicaoXInicial, posicaoYi, posicaoXFinal, posicaoYf);

        //Borda inferior
//        g.desenhar_linha(posicaoXInicial, posicaoYf + 33, largura_painel_componentes - posicaoXFinal, posicaoYf + 33);
        g.desenhar_linha(posicaoXInicial, posicaoYf, largura_painel_componentes - posicaoXFinal, posicaoYf);

        //Borda direita
//        g.desenhar_linha(largura_painel_componentes - posicaoXInicial, posicaoYf + 33, largura_painel_componentes - posicaoXFinal, posicaoYi);
        g.desenhar_linha(largura_painel_componentes - posicaoXInicial, posicaoYf, largura_painel_componentes - posicaoXFinal, posicaoYi);
        g.desenhar_texto(posicaoXInicial + 5, (posicaoYi - 5), titulo);//Desenha o título

        //Desenha a borda superior após o título
        int largura_texto = g.largura_texto(titulo);
        g.desenhar_linha(posicaoXInicial + largura_texto + 10, posicaoYi, largura_painel_componentes - posicaoXFinal, posicaoYi);

//        g.definir_opacidade(255);
//        g.definir_cor(cor_lateral);
        g.desenhar_retangulo(posicaoXInicial + 10, yplay + 15, larg, 4, false, true);
//        g.definir_cor(cor_botao);

        //Desenha o retangulo sobre o slider
//        g.desenhar_retangulo((int) (posicaoXInicial + valor_atual - (padding_size / 2)), yplay + 15 - (padding_size / 4), padding_size, padding_size, false, true);
        g.desenhar_retangulo((int) valor_atual, yplay + 15 - (padding_size / 4), padding_size, padding_size, false, true);

        //Desenha a informação dos valores do slider
//        g.definir_cor(cor_botao);
//        g.definir_estilo_texto(false, true, false);
        g.desenhar_texto(largura_painel_componentes - 100, yplay + 10, "" + valor_display + "/" + valor_maximo);
//        g.desenhar_texto(alt + 10, ALTURA_DA_TELA - (alt + 15), "Teste Slider");//Desenha o valor do

//        if (GerenciadorComponentes.criarSlider(posicaoXInicial + 10, posicaoXInicial + 10 + larg, posicaoYi, posicaoYf + 33, posicaoYf + 33, nome, valor_atual, valor_maximo, valor_minimo, posicaoYf + 33, larg, titulo)) {
        if (GerenciadorComponentes.criarSlider(posicaoXInicial + 10, posicaoXInicial + 10 + larg, posicaoYi, posicaoYf, posicaoYf, nome, valor_atual, valor_maximo, valor_minimo, posicaoYf - posicaoYi, larg, titulo, valor_display)) {
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
    private void desenhar_interruptor(String nome, String titulo, int yInicial, int yFinal, boolean valor) throws ErroExecucaoBiblioteca, InterruptedException {
        int altura_imagem = g.altura_imagem(endereco_imagem_switch_on);
        int largura_imagem = g.largura_imagem(endereco_imagem_switch_off);
        int larg = largura_painel_componentes - (posicaoXInicial * 2);

        g.definir_cor(Graficos.COR_BRANCO);
//        g.definir_opacidade(200);

        //Desenha o retângulo em volta da imagem
        g.desenhar_retangulo(posicaoXInicial, yInicial, larg, altura_imagem + 10, false, false);

        //Desenha o título
        g.desenhar_texto(posicaoXInicial + largura_imagem + 10, yInicial + (altura_imagem / 2), titulo);

//        UtilSimulador.setLog("Vai mudar o estado para: " + valor);
        if (valor) {
            g.desenhar_imagem(posicaoXInicial + 5, yInicial + 5, endereco_imagem_switch_on);
        } else {
            g.desenhar_imagem(posicaoXInicial + 5, yInicial + 5, endereco_imagem_switch_off);
        }

        GerenciadorComponentes.criarInterruptor(posicaoXInicial + 5, posicaoXInicial + largura_imagem, yInicial, yFinal + 33, yFinal + 33, nome, valor, 33, larg, titulo);
    }

    /**
     * Cria um novo componente do tipo monitor considerando as posições dos
     * outros componentes que já foram adicionados
     *
     * @param nome
     * @param titulo
     * @param valor_atual
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void criar_monitor(String nome, String titulo, String valor_atual) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        if (GerenciadorComponentes.listaTemRegistro()) {
            Componente ultimo_componente = GerenciadorComponentes.getUltimoComponente();

            desenhar_monitor(nome, titulo, ultimo_componente.get_proximo_y(), ultimo_componente.getProxima_posicao_y2(), valor_atual);
        } else {
            desenhar_monitor(nome, titulo, altura_painel_botoes + posicaoYInicial, altura_painel_botoes + posicaoYFinal, valor_atual);
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
    public void criar_slider(String nome, String titulo, double valor_minimo, double valor_padrao, double valor_maximo) throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        if (GerenciadorComponentes.listaTemRegistro()) {
            Componente ultimoComponente = GerenciadorComponentes.getUltimoComponente();

            desenhar_slider(nome, titulo, ultimoComponente.get_proximo_y(), ultimoComponente.getProxima_posicao_y2(), valor_minimo, valor_padrao, valor_maximo, valor_padrao);
        } else {
            desenhar_slider(nome, titulo, altura_painel_botoes + posicaoYInicial, altura_painel_botoes + posicaoYFinal, valor_minimo, valor_padrao, valor_maximo, valor_padrao);
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
    public void criar_interruptor(String nome, String titulo, boolean valor) throws ErroExecucaoBiblioteca, InterruptedException {
        if (GerenciadorComponentes.listaTemRegistro()) {
            Componente ultimoComponente = GerenciadorComponentes.getUltimoComponente();

            desenhar_interruptor(nome, titulo, ultimoComponente.get_proximo_y(), ultimoComponente.getProxima_posicao_y2(), valor);
        } else {
            desenhar_interruptor(nome, titulo, altura_painel_botoes + posicaoYInicial, altura_painel_botoes + posicaoYFinal, valor);
        }
    }

    /**
     * Método para atualizar os componentes que já estão na lista na tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     * @throws ErroExecucao
     */
    private void desenhar_componentes() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        for (Componente componente : GerenciadorComponentes.getListaComponentes()) {
            switch (componente.get_tipo_componente()) {
                case monitor:
                    Monitor monitor = (Monitor) componente;
                    desenhar_monitor(monitor.get_nome(), monitor.get_titulo(), monitor.get_y1(), monitor.get_y2(), monitor.getValor_atual());
                    break;
                case slider:
                    Slider slider = (Slider) componente;
                    desenhar_slider(slider.get_nome(), slider.get_titulo(), slider.get_y1(), slider.get_y2(), slider.getValor_minimo(), slider.getValor_atual(), slider.getValor_maximo(), slider.getValor_display());
                    break;
                case interruptor:
                    Interruptor interruptor = (Interruptor) componente;
                    desenhar_interruptor(interruptor.get_nome(), interruptor.get_titulo(), interruptor.get_y1(), interruptor.get_y2(), interruptor.isLigado());
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
    private void controle_mouse_slider() throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorComponentes.verificarMouseDentroSlider(m.posicao_x(), m.posicao_y(), m.algum_botao_pressionado());
    }

    /**
     * Controla as ações do mouse para o componente de switch
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void controle_mouse_switch() throws ErroExecucaoBiblioteca, InterruptedException {
//        UtilSimulador.setLog("X: " + m.posicao_x() + " / Y: " + m.posicao_y());
        GerenciadorComponentes.verificarMouseDentroInterruptor(m.posicao_x(), m.posicao_y(), m.algum_botao_pressionado());
    }

    /**
     * Procura por um retalho na lista e, pelo seu id, atribui a cor passada por
     * parâmetro
     *
     * @param id
     * @param cor
     */
    public void definir_cor_retalho(int id, int cor) {
        Retalho retalho_encontrado = null;

        //Busca o retalho na lista
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                if (retalhos[i][j].get_id() == id) {
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
    public TipoParede verificar_retalho_eh_parede(int id) {
        //Busca o retalho na lista
        TipoParede tipo_parede = null;
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                if (retalhos[i][j].get_id() == id) {
                    if (retalhos[i][j].eh_parede()) {
                        tipo_parede = retalhos[i][j].getTipo_parede();
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
    public int get_altura_maxima_painel_simulacao(){
        return ALTURA_DA_TELA - altura_rodape - 20;
    }
    
    /**
     * Retorna a altura mínima (y) do painel onde ocorre a simulação
     * 
     * @return 
     */
    public int get_altura_minima_painel_simulacao(){
        return altura_painel_botoes;
        
    }
    
    /**
     * Retorna a largura máxima (x) do painel de simulação 
     * 
     * @return 
     */
    public int get_largura_maxima_painel_simulacao(){
        return LARGURA_DA_TELA - 1;
    }
    
    /**
     * Retorna a largura mínima (x) do painel de simulação
     * 
     * @return 
     */
    public int get_largura_minima_painel_simulacao(){
        return largura_painel_componentes;
    }
}
