package br.simulador.gerador;

import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import br.univali.portugol.nucleo.SimuladorPrograma;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.util.List;
import br.simulador.plugin.biblioteca.Experimentos;

public class ProgramaTeste extends SimuladorPrograma {

     private final Experimentos Experimentos = new Experimentos();

    private int medios;
    private int lentos;
    private int rapidos;
    private int tick_delta;
    private int ultima_colisao;
    private double percentual_medios;
    private double percentual_lentos;
    private double percentual_rapidos;
    private double media_velocidade;
    private double media_energia;
    private boolean colidindo;

    public ProgramaTeste() throws ErroExecucao, InterruptedException {
    }

    @Override
    protected void inicializar() throws ErroExecucao, InterruptedException {
        medios = 0;
        lentos = 0;
        rapidos = 0;
        tick_delta = 0;
        ultima_colisao = 0;
        percentual_medios = 0.0;
        percentual_lentos = 0.0;
        percentual_rapidos = 0.0;
        media_velocidade = 0.0;
        media_energia = 0.0;
        colidindo = Experimentos.colidiu_com_parede();
    }

    private void inicio() throws ErroExecucao, InterruptedException {
        ;

    }

    private void atualizar_variaveis() throws ErroExecucao, InterruptedException {
        medios = Experimentos.agentes_com_cor(0);
        lentos = Experimentos.agentes_com_cor(1);
        rapidos = Experimentos.agentes_com_cor(2);
        percentual_medios = (medios / Experimentos.contar_agentes()) * 100;
        percentual_lentos = (lentos / Experimentos.contar_agentes()) * 100;
        percentual_rapidos = (rapidos / Experimentos.contar_agentes()) * 100;
        media_velocidade = Experimentos.media("velocidade");
        media_energia = Experimentos.media("energia");

    }

    private Object agenteAtual;

    @Override
    public Object getAgenteAtual() {
        return agenteAtual;
    }

    @Override
    public void simular(boolean sempre, List<?> agentes) throws ErroExecucao, InterruptedException {
        System.out.println("Executando método simular.");
        try {
            Experimentos.simular();
            do {
                System.out.println("Vai iniciar o laço.");
                for (Object agente : agentes) {
                    criar();
                    atualizar_variaveis();
                    pular();
                    mover();

                }
            } while (sempre);
        } catch (ErroExecucaoBiblioteca ex) {
        } catch (InterruptedException ex) {
        } catch (ErroExecucao ex) {
        }
    }

    private void criar() throws ErroExecucao, InterruptedException {
        Experimentos.criar_agentes(10, true);

    }

    private void pular() throws ErroExecucao, InterruptedException {
        boolean colidindo = Experimentos.colidiu_com_parede();
        if (colidindo) {
            return;

        }

        if (Experimentos.colidiu_borda_X()) {
            Experimentos.definir_orientacao(Experimentos.retornar_orientacao() * -1);

        } else {
            if (Experimentos.colidiu_borda_Y()) {
                Experimentos.definir_orientacao(180 - Experimentos.retornar_orientacao());

            }

        }

    }

    private void mover() throws ErroExecucao, InterruptedException {
        if (Experimentos.retornar_id_retalho(
                Experimentos.retornar_atributo_inteiro("velocidade") * tick_delta) != Experimentos.meu_retalho()) {
            ultima_colisao = Experimentos.retornar_id();

        }

        Experimentos.mover_frente(Experimentos.retornar_atributo_inteiro("velocidade") * tick_delta);

    }


}
