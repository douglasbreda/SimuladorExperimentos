/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.testes;

import br.simulador.plugin.biblioteca.Experimentos;
import br.univali.portugol.nucleo.SimuladorPrograma;
import br.univali.portugol.nucleo.bibliotecas.Tipos;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class GasLabJava {

    int medios = 0;
    int lentos = 0;
    int rapidos = 0;
    int tick_delta = 0;
    int ultima_colisao = 0;
    double percentual_medios = 0.0;
    double percentual_lentos = 0.0;
    double percentual_rapidos = 0.0;
    double media_velocidade = 0.0;
    double media_energia = 0.0;
    Experimentos exp = new Experimentos();
    Tipos tipo = new Tipos();

    private Object agenteAtual;

    public static void main(String[] args) throws ErroExecucao, InterruptedException {
        GasLabJava gas = new GasLabJava();
        gas.configurar();
//        gas.simular();
    }

    public Object getAgenteAtual() {
        return agenteAtual;
    }

    private void inicio() throws InterruptedException, ErroExecucao, ErroExecucaoBiblioteca {
        exp.definir_forma_agentes(0);
        exp.criar_agentes(1, true);
        exp.executar_sempre(true);
        exp.definir_bordas(-256);
        exp.criar_monitor("lentos", "Lento", tipo.inteiro_para_cadeia(lentos, 10));
        exp.criar_monitor("medios", "Médios", tipo.inteiro_para_cadeia(medios, 10));
        exp.criar_monitor("rapidos", "Rápidos", tipo.inteiro_para_cadeia(rapidos, 10));
        exp.criar_monitor("media_v", "Média de velocidade", tipo.real_para_cadeia(media_velocidade));
        exp.criar_monitor("media_e", "Rápidos", tipo.real_para_cadeia(media_energia));
        exp.criar_slider("slider_1", "Teste", 0, 100, 25);
        exp.criar_atributo("velocidade", "10");
        atualizar_variaveis();
//        simular();
    }

    private List<?> listaAgentes;

    public void setListaAgentes(List<?> listaAgentes) {
        this.listaAgentes = listaAgentes;
    }

    public void configurar() throws ErroExecucao, InterruptedException {
        System.out.println("Vai chamar o método de simular");
        exp.simular();
        System.out.println("Vai definir o programa atual");
//        Experimentos.definir_programa_atual(this);
        System.out.println("Vai definir a lista de agentes");
        setListaAgentes(exp.retornar_lista_agentes());
        if (listaAgentes == null) {
            System.out.println("A lista de agentes está nula");
        } else {
            System.out.println("Número de agentes" + listaAgentes.size());
        }
        System.out.println("Vai iniciar");
        inicio();
        exp.atualizar_tela();
        exp.definir_valor_atributo("velocidade", String.valueOf(lentos));
    }

    private void atualizar_variaveis() throws ErroExecucaoBiblioteca, InterruptedException {
        medios = exp.agentes_com_cor(-16711936); //Cor verde como no exemplo do netlogo
        lentos = exp.agentes_com_cor(-65536); //Cor azul como no exemplo do netlogo
        rapidos = exp.agentes_com_cor(-16776961); //Cor vermelha como no exemplo do netlogo

        percentual_medios = (medios / (exp.contar_agentes())) * 100;
        percentual_lentos = (lentos / (exp.contar_agentes())) * 100;
        percentual_rapidos = (rapidos / (exp.contar_agentes())) * 100;

        media_velocidade = exp.media("velocidade");
        media_energia = exp.media("energia");

        exp.atualizar_valor_monitor("lentos", tipo.inteiro_para_cadeia(lentos, 10));
        exp.atualizar_valor_monitor("medios", tipo.inteiro_para_cadeia(medios, 10));
        exp.atualizar_valor_monitor("rapidos", tipo.inteiro_para_cadeia(rapidos, 10));
        exp.atualizar_valor_monitor("media_v", tipo.real_para_cadeia(media_velocidade));
        exp.atualizar_valor_monitor("media_e", tipo.real_para_cadeia(media_energia));

        System.out.println("Valor do slider: " + exp.retornar_valor_atual_slider("slider_1"));

    }

    public void simular() {
        exp.definir_programa_execucao(true);
        try {
            int ticks = 0;
            do {
                setListaAgentes(exp.retornar_lista_agentes());
                for (Object agente : listaAgentes) {
                    this.agenteAtual = agente;
                    exp.definir_agente_atual(agente);
                    atualizar_variaveis();
                    pular();
                    mover();

                    exp.atualizar_tela();
                }

                exp.atualizar_ticks(++ticks);

            } while (true);
        } catch (ErroExecucaoBiblioteca ex) {
            Logger.getLogger(GasLabJava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GasLabJava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroExecucao ex) {
            Logger.getLogger(GasLabJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //funcao pular(inteiro turtle)
    private void pular() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        int coordenadaX = exp.retornar_coordenadaX();
        int valorMaximoBordaX = exp.retornar_valor_max_bordaX();
        int valorMinimoBordaX = exp.retornar_valor_min_bordaX();
        int coordenadaY = exp.retornar_coordenadaY();
        int valorMaximoBordaY = exp.retornar_valor_max_bordaY();
        int valorMinimoBordaY = exp.retornar_valor_min_bordaY();

        boolean colidindoX = exp.colidiu_com_parede_X();
        boolean colidindoY = exp.colidiu_com_parede_Y();
//        boolean colidindoX = coordenadaX >= valorMaximoBordaX
//                || coordenadaX <= valorMinimoBordaX;

//        boolean colidindoY = coordenadaY >= valorMaximoBordaY
//                || coordenadaY <= valorMinimoBordaY; //Se bateu em alguma parede na esquerda ou direita, reflete a orientação em torno do eixo X
        if (colidindoX) {

//            System.out.println("----------------Colidiu X--------------------------------");
//            System.out.println("ID: " + exp.retornar_id());
//            System.out.println("X: " + coordenadaX);
//            System.out.println("Máx X: " + valorMaximoBordaX);
//            System.out.println("Min X: " + valorMinimoBordaX);
//            System.out.println("Y: " + coordenadaY);
//            System.out.println("Máx Y: " + valorMaximoBordaY);
//            System.out.println("Min Y: " + valorMinimoBordaY);
            int orientacao = exp.retornar_orientacao();
//            System.out.println("Orientação atual: " + orientacao);
            int novaOrientacao = orientacao * (-1);
            exp.definir_orientacao(novaOrientacao);
//            System.out.println("Nova orientação: " + novaOrientacao);
//            System.out.println("-----------------------------------------------------\n\n");
            exp.inverter_sentido();

        }
        if (colidindoY) {

//            System.out.println("----------------Colidiu Y--------------------------------");
//            System.out.println("ID: " + exp.retornar_id());
//            System.out.println("X: " + coordenadaX);
//            System.out.println("Máx X: " + valorMaximoBordaX);
//            System.out.println("Min X: " + valorMinimoBordaX);
//            System.out.println("Y: " + coordenadaY);
//            System.out.println("Máx Y: " + valorMaximoBordaY);
//            System.out.println("Min Y: " + valorMinimoBordaY);
            int orientacao = exp.retornar_orientacao();
//            System.out.println("Orientação atual: " + orientacao);
            int novaOrientacao = 180 - orientacao;
            exp.definir_orientacao(novaOrientacao);
//            System.out.println("Nova orientação: " + novaOrientacao);
//            System.out.println("-----------------------------------------------------\n\n");
            exp.inverter_sentido();
        }
    }

    private void mover() throws ErroExecucaoBiblioteca, InterruptedException {
        //se(exp.retornar_id_retalho(exp.retornar_atributo_inteiro("velocidade") * tick_delta) != exp.meu_retalho())
        //	ultima_colisao = exp.retornar_id()

        exp.mover(3);
    }
}
