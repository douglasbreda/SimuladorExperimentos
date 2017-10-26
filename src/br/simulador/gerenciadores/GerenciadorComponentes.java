/**
 * Classe para criar o componente de acordo com o seu tipo
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.componentes.Componente;
import br.simulador.plugin.biblioteca.componentes.Interruptor;
import br.simulador.plugin.biblioteca.componentes.Monitor;
import br.simulador.plugin.biblioteca.componentes.Slider;
import br.simulador.plugin.biblioteca.componentes.TipoComponente;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Douglas
 */
public final class GerenciadorComponentes {

    private static List<Componente> listaComponentes = new ArrayList<>();

    /**
     * Cria um componente do tipo monitor
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param yFinal
     * @param nome
     * @param valorAtual
     * @param altura
     * @param largura
     * @param titulo
     * @throws br.univali.portugol.nucleo.mensagens.ErroExecucao
     */
    public static void criarMonitor(int x1, int x2, int y1, int y2, int yFinal, String nome, String valorAtual, int altura, int largura, String titulo) throws ErroExecucao {
        if (!componenteJaAdicionado(nome)) {
            Monitor monitor = new Monitor();

            monitor.setValor_atual(valorAtual);

            adicionarComponenteLista(monitor.criar(x1, x2, y1, y2, yFinal, nome, altura, largura, titulo));
        }
    }

    /**
     * Cria um componente do tipo Slider
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param yFinal
     * @param nome
     * @param valorPadrao
     * @param valorMaximo
     * @param valorMinimo
     * @param altura
     * @param largura
     * @param titulo
     * @param valorDisplay
     * @return
     * @throws br.univali.portugol.nucleo.mensagens.ErroExecucao
     */
    public static boolean criarSlider(int x1, int x2, int y1, int y2, int yFinal, String nome, double valorPadrao, double valorMaximo, double valorMinimo, int altura, int largura, String titulo, double valorDisplay) throws ErroExecucao {

        if (!componenteJaAdicionado(nome)) {
            Slider slider = new Slider();

            slider.setValor_maximo(valorMaximo);

            slider.setValor_minimo(valorMinimo);

            slider.setValor_atual(valorPadrao);
            
            slider.setValor_display(valorDisplay);

            adicionarComponenteLista(slider.criar(x1, x2, y1, y2, yFinal, nome, altura, largura, titulo));

            return true;
        }

        return false;
    }

    /**
     * Verifica se o componente já foi adicionado a lista
     *
     * @param nome_componente
     * @return
     */
    public static boolean componenteJaAdicionado(String nome_componente) {
        return listaComponentes.stream().filter(x -> x.get_nome().equalsIgnoreCase(nome_componente)).count() > 0;
    }

    /**
     * Cria um componente do tipo Interruptor
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param yFinal
     * @param nome
     * @param valorPadrao
     * @param altura
     * @param largura
     * @param titulo
     */
    public static void criarInterruptor(int x1, int x2, int y1, int y2, int yFinal, String nome, boolean valorPadrao, int altura, int largura, String titulo) {

        if (!componenteJaAdicionado(nome)) {
            Interruptor interruptor = new Interruptor();

            if (valorPadrao) {
                interruptor.ligar();
            } else {
                interruptor.desligar();
            }

            adicionarComponenteLista(interruptor.criar(x1, x2, y1, y2, yFinal, nome, altura, largura, titulo));
        }
    }

    /**
     * Adiciona um componente na lista de controle de componentes
     *
     * @param componente
     */
    private static void adicionarComponenteLista(Componente componente) {
        if (listaComponentes == null) {
            listaComponentes = new ArrayList<>();
        }

        listaComponentes.add(componente);
    }

    /**
     * Verifica se algum componente já foi adicionado na lista de componentes
     *
     * @return
     */
    public static boolean listaTemRegistro() {
        return listaComponentes != null && listaComponentes.size() > 0;
    }

    /**
     * Retorna o último do registro da lista para saber as próximas posições do
     * novo componente
     *
     * @return
     */
    public static Componente getUltimoComponente() {
        return listaComponentes.get(listaComponentes.size() - 1);
    }

    public static Slider verificarMouseDentroSlider(int posicaoXMouse, int posicaoYMouse, boolean botaoPressionado) {
        List<Componente> listaSliders = listaComponentes.stream().filter(x -> x.get_tipo_componente() == TipoComponente.slider).collect(Collectors.toList());

        if (listaSliders != null && listaSliders.size() > 0) {
            for (Componente slider : listaSliders) {
                if (posicaoXMouse >= slider.get_x1() && posicaoXMouse <= (slider.get_x1()) + slider.get_largura()
                        && posicaoYMouse >= slider.get_y1() + 21 && posicaoYMouse <= slider.get_yFinal() - 16) {
                    UtilSimulador.setLog("está dentro do slider");
                    if (botaoPressionado) {
                        
                        Slider slider_atual = (Slider) slider;

                        if (slider_atual != null) {
                            slider_atual.calcular_valores_slider(posicaoXMouse);
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Verifica se o mouse está dentro de alguma posição do Switch
     *
     * @param posicaoXMouse
     * @param posicaoYMouse
     * @param botao_pressionado
     * @return
     */
    public static boolean verificarMouseDentroInterruptor(int posicaoXMouse, int posicaoYMouse, boolean botao_pressionado) {
        List<Componente> listaInterruptor = listaComponentes.stream().filter(x -> x.get_tipo_componente() == TipoComponente.interruptor).collect(Collectors.toList());
        Interruptor interruptor_atual = null;

        for (Componente interruptor : listaInterruptor) {
            interruptor_atual = (Interruptor) interruptor;

//            UtilSimulador.setLog("Y1 = " + interruptor.get_y1());
//            UtilSimulador.setLog("Y2 = " + interruptor.get_y2());
            
//            if (!interruptor_atual.isLigado()) {
            if (posicaoXMouse >= (interruptor.get_x1() + 10) && posicaoXMouse <= interruptor.get_x2() - 10
                    && posicaoYMouse >= (interruptor.get_y1() + 7) && posicaoYMouse < (interruptor.get_y1() + 16)) {
                UtilSimulador.setLog("Entrou Ligado");
//                UtilSimulador.setLog(String.valueOf(botao_pressionado));
                if (botao_pressionado) {
                    interruptor_atual.desligar();
                    return true;
                }
            }

            if (posicaoXMouse >= (interruptor.get_x1() + 10) && posicaoXMouse <= interruptor.get_x2() - 10
                    && posicaoYMouse >= ((interruptor.get_y2() - 46)  - 17) && posicaoYMouse < ((interruptor.get_y2() - 46) - 7)) {
                UtilSimulador.setLog("Entrou desligado");
                if (botao_pressionado) {
                    interruptor_atual.ligar();
                    return true;
                }
            }
        }

        return false;
    }

    public static List<Componente> getListaComponentes() {
        return listaComponentes;
    }

    /**
     * Atribui um novo valor a um componente já criado
     *
     * @param nome
     * @param novo_valor
     */
    public static void atualizarValorMonitor(String nome, String novo_valor) {
        Optional<Componente> monitor = listaComponentes.stream().filter(x -> x.get_tipo_componente() == TipoComponente.monitor && x.get_nome().equalsIgnoreCase(nome)).findAny();

        if (monitor != null) {
            Monitor monitorAtualizar = (Monitor) monitor.get();

            monitorAtualizar.setValor_atual(novo_valor);
        }
    }
    
    /**
     * Retorna o valor atual de um componente slider consultando pelo seu nome
     * @param nome_slider
     * @return 
     */
    public static double buscarValorAtualSlider(String nome_slider){
        Optional<Componente> slider = listaComponentes.stream().filter(x -> x.get_tipo_componente() == TipoComponente.slider && x.get_nome().equalsIgnoreCase(nome_slider)).findAny();
        
        if(slider != null){
            Slider sliderEncontrado = (Slider) slider.get();
            
            return sliderEncontrado.getValor_display();
        }
        
        return 0.0;
    }
    
    /**
     * Retorna o valor de um interruptor consultando pelo seu nome
     * @param nome_interruptor
     * @return 
     */
    public static boolean buscarValorAtualInterruptor(String nome_interruptor){
        Optional<Componente> interruptor = listaComponentes.stream().filter(x -> x.get_tipo_componente() == TipoComponente.slider && x.get_nome().equalsIgnoreCase(nome_interruptor)).findAny();
        
        if(interruptor != null){
            Interruptor interruptorEncontrado = (Interruptor) interruptor.get();
            
            return interruptorEncontrado.isLigado();
        }
        
        return false;
    }
}
