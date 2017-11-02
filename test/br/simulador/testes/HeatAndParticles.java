/**
 * Classe para testar o experimento que mostra o movimento das partículas de gás
 * conforme a temperatura
 */
package br.simulador.testes;

import br.simulador.plugin.biblioteca.Experimentos;
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
public class HeatAndParticles {

    Experimentos exp = new Experimentos();
    Tipos tipo = new Tipos();
    double temperatura = 0;

    private Object agenteAtual;

    public static void main(String[] args) throws ErroExecucao, InterruptedException {
        HeatAndParticles gas = new HeatAndParticles();
        gas.configurar();
        gas.simular();
    }

    public Object getAgenteAtual() {
        return agenteAtual;
    }

    private void inicio() throws InterruptedException, ErroExecucao, ErroExecucaoBiblioteca {
        exp.definir_forma_agentes(0);
        exp.criar_agentes(10, true);
        exp.executar_sempre(true);
        exp.definir_bordas(-256);
        exp.definir_borda_esquerda(-65536);
        exp.criar_slider("temperatura", "Temperatura", 0, 100, 25);
        exp.criar_monitor("mediaEnergia", "Média Energia", "0");
        exp.criar_monitor("mediaVelocidade", "Média Velocidade", "0");
        exp.criar_monitor("mediaMassa", "Média Massa", "0");
        exp.criar_atributo("massa", "1");
        exp.criar_atributo("velocidade", "5");
        exp.definir_titulo_simulacao("Teste título");
        double energia = 5;//(0.5 * 1 * 10 * 10);//(0.5 * mass * speed * speed)
        exp.criar_atributo("energia", String.valueOf(energia));
        atualizar_variaveis();
        simular();
    }

    private List<?> listaAgentes;

    public void setListaAgentes(List<?> listaAgentes) {
        this.listaAgentes = listaAgentes;
    }

    public void configurar() throws ErroExecucao, InterruptedException {
        exp.simular();
//        Experimentos.definir_programa_atual(this);
        setListaAgentes(exp.retornar_lista_agentes());
        inicio();
        exp.atualizar_tela();
    }

    private void atualizar_variaveis() throws ErroExecucaoBiblioteca, InterruptedException {

        double mediaEnergia = exp.media("energia");
        double mediaVelocidade = exp.media("velocidade");
        double mediaMassa = exp.media("energia");
        exp.atualizar_valor_monitor("mediaEnergia", tipo.real_para_cadeia(mediaEnergia));
        exp.atualizar_valor_monitor("mediaVelocidade", tipo.real_para_cadeia(mediaVelocidade));
        exp.atualizar_valor_monitor("mediaMassa", tipo.real_para_cadeia(mediaMassa));
//        System.out.println(exp.retornar_valor_atual_slider("temperatura"));
    }

    public void simular() {
        exp.definir_programa_execucao(true);
        try {
            int ticks = 0;
            temperatura = exp.retornar_valor_atual_slider("temperatura");
            do {
                setListaAgentes(exp.retornar_lista_agentes());
                for (Object agente : listaAgentes) {
                    this.agenteAtual = agente;
                    exp.definir_agente_atual(agente);

                    recolorir_agentes(exp.retornar_id());
                    atualizar_variaveis();
                    pular();
                    mover();

//                 exp.atualizar_tela();
                }

                exp.atualizar_tela();
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
        boolean colidindoX = exp.colidiu_com_parede_X();
        boolean colidindoY = exp.colidiu_com_parede_Y();
        boolean colidindoEsquerda = exp.colidiu_com_parede_esquerda();
        int idAgenteColidindo = exp.retornar_agente_aqui();

        if (idAgenteColidindo > 0) {
            int orientacaoOutroAgente = exp.retornar_orientacao_por_agente(idAgenteColidindo);
            
//            exp.definir_orientacao(orientacaoOutroAgente);
            
            exp.inverter_sentido();

        } else {

            
            if (colidindoEsquerda) {
                //Pega os atributos atuais do agente
                double energia_atual = exp.retornar_atributo_real("energia");
                double velocidade_atual = exp.retornar_atributo_real("velocidade");
                double massa = exp.retornar_atributo_real("massa");

                energia_atual = ((energia_atual + temperatura) / 2);
                velocidade_atual = Math.sqrt(2 * energia_atual / massa);

                exp.atualizar_valor_atributo("energia", String.valueOf(energia_atual));
                exp.atualizar_valor_atributo("velocidade", String.valueOf(velocidade_atual));

                recolorir_agentes(exp.retornar_id());

                int orientacao = exp.retornar_orientacao();
                int novaOrientacao = orientacao * (-1);
                exp.definir_orientacao(novaOrientacao);
                exp.inverter_sentido();

            } else if (colidindoX) {
                int orientacao = exp.retornar_orientacao();
                int novaOrientacao = orientacao * (-1);
                exp.definir_orientacao(novaOrientacao);
                exp.inverter_sentido();
            }
            if (colidindoY) {
                int orientacao = exp.retornar_orientacao();
                int novaOrientacao = 180 - orientacao;
                exp.definir_orientacao(novaOrientacao);
                exp.inverter_sentido();
            }
        }
    }

    private void recolorir_agentes(double velocidade) throws ErroExecucaoBiblioteca, InterruptedException {
        if (velocidade < 5) {
            exp.definir_cor_agente(-16776961);
        } else if (velocidade > 8) {
            exp.definir_cor_agente(-65536);
        } else {
            exp.definir_cor_agente(-16711936);
        }
    }

    private void mover() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao {
        int velocidade = tipo.real_para_inteiro(exp.retornar_atributo_real("velocidade"));
//        System.out.println(velocidade);
        exp.mover(3);
//        exp.definir_cor_retalho(-256);
    }

}
