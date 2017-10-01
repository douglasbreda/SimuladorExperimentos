/*
Classe para desenhar os componentes visuais
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.univali.portugol.nucleo.ProgramaVazio;
import br.univali.portugol.nucleo.bibliotecas.Graficos;
import br.univali.portugol.nucleo.bibliotecas.Mouse;
import br.univali.portugol.nucleo.bibliotecas.base.Biblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class GerenciadorDesenho {

    final int LARGURA = 32;
    final int ALTURA = 32;
    final int tile = 20;
    int cor_atual = 3;

    Retalho[][] retalhos = new Retalho[ALTURA][LARGURA];
    int[] cores = {0xFFFFFF, 0xE4E4E4, 0x888888, 0x222222, 0xFFA7D1, 0xE50000, 0xE59500, 0xA06A42, 0xE5D900, 0x94E044, 0x02BE01, 0x00D3DD, 0x0083C7, 0x0000EA, 0xCF6EE4, 0x820080};

    Graficos g = new Graficos();
    Mouse m = new Mouse();

    /**
     * Inicia a tela onde será executada a simulação
     */
    public void inicializar_tela() {
        try {
            g.inicializar(new ProgramaVazio(), null);
            ArrayList<Biblioteca> lista = new ArrayList<>();
            lista.add(g);
            m.inicializar(new ProgramaVazio(), lista);
            for (int i = 0; i < ALTURA; i++) {
                for (int j = 0; j < LARGURA; j++) {
                    retalhos[i][j] = new Retalho();
                }
            }

            g.iniciar_modo_grafico(true);
            g.definir_dimensoes_janela(LARGURA * tile, (ALTURA + 2) * tile);
            rodar();
        } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
            Logger.getLogger(GerenciadorInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Dispara a thread necessária por ficar renderizando a tela
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void rodar() throws ErroExecucaoBiblioteca, InterruptedException {

        new Thread(() -> {
            while (esta_executando()) {
                try {
                    desenhar();
                    controle();
                    g.renderizar();
                } catch (ErroExecucaoBiblioteca ex) {
                    Logger.getLogger(GerenciadorInterface.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GerenciadorInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    /**
     * Responsável por desenhar e renderizar os componentes visuais
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    private void desenhar() throws ErroExecucaoBiblioteca, InterruptedException {
        g.definir_titulo_janela("Simulador de Experimentos");
        int x = 0;
        int y = 0;
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                g.definir_cor(cores[retalhos[i][j].retornar_cor_retalho()]);
                x = j * tile;
                y = i * tile;
                g.desenhar_retangulo(x, y, tile, tile, false, true);
                retalhos[i][j].definir_coordenadas(x, y);
            }
        }

        ArrayList<IAgente> listaAgente = GerenciadorExecucao.getInstance().getListaAgentes();

        if (listaAgente != null && listaAgente.size() > 0) {
            for (IAgente agente : listaAgente) {
                g.definir_cor(agente.retornar_cor_agente());
                g.desenhar_elipse(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y(), 10, 10, true);
            }
        }

        int t = m.posicao_y() / tile;
        int j = m.posicao_x() / tile;
        if (t < ALTURA) {
            g.definir_cor(cores[cor_atual]);
            g.definir_opacidade(128);
            g.desenhar_elipse(j * tile, t * tile, tile, tile, true);
            g.definir_opacidade(255);
        }

        for (int i = 0; i < 16; i++) {
            g.definir_cor(cores[i]);
            g.desenhar_retangulo(i * 2 * tile, ALTURA * tile, 2 * tile, 2 * tile, false, true);
        }

        g.definir_cor(0x222222);
        g.desenhar_retangulo(0, ALTURA * tile, LARGURA * tile, 3, false, true);
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
            if (i < ALTURA && j < LARGURA) {
                retalhos[i][j].set_cor(cor_atual);
            } else {
                cor_atual = j / 2;
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
        return g.get_janela().estaVisivel();
    }

    /**
     * Retorna a altura da janela de simulação
     *
     * @return
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
        for (int i = 0; i < ALTURA; i++) {
            retalhos[0][i].set_cor(cor);
            retalhos[0][i].set_parede(true);
        }

        //Borda inferior
        for (int i = 0; i < ALTURA; i++) {
            retalhos[ALTURA - 1][i].set_cor(cor);
            retalhos[ALTURA - 1][i].set_parede(true);
        }

        //Borda esquerda
        for (int i = 0; i < LARGURA; i++) {
            retalhos[i][0].set_cor(cor);
            retalhos[i][0].set_parede(true);
        }

        //Borda direita
        for (int i = 0; i < LARGURA; i++) {
            retalhos[i][LARGURA - 1].set_cor(cor);
            retalhos[i][LARGURA - 1].set_parede(true);
        }
    }

    /**
     * Retorna a coordenada minima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_minimo_borda_X() {
        return retalhos[0][0].getCoordenadaX();
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_minimo_borda_Y() {
        return retalhos[ALTURA - 1][0].getCoordenadaY();
    }

    /**
     * Retorna a coordenada máxima de X possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_maximo_borda_X() {
        return retalhos[0][LARGURA - 1].getCoordenadaX();
    }

    /**
     * Retorna a coordenada mínima de Y possível de acessar para controle de
     * colisões
     *
     * @return
     */
    public int retorna_valor_maximo_borda_Y() {
        return retalhos[ALTURA - 1][0].getCoordenadaY();
    }
}
