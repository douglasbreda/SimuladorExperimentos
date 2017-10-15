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
     * @return 
     * @throws br.univali.portugol.nucleo.mensagens.ErroExecucao
     */
    public static boolean criarSlider(int x1, int x2, int y1, int y2, int yFinal, String nome, double valorPadrao, double valorMaximo, double valorMinimo, int altura, int largura, String titulo) throws ErroExecucao {

        if (!componenteJaAdicionado(nome)) {
            Slider slider = new Slider();

            slider.setValor_maximo(valorMaximo);

            slider.setValor_minimo(valorMinimo);
            
            slider.setValor_atual(valorPadrao);

            adicionarComponenteLista(slider.criar(x1, x2, y1, y2, yFinal, nome, altura, largura, titulo));
            
            return true;
        }
        
        return false;
//        } else {
//            throw new ErroExecucao() {
//                @Override
//                protected String construirMensagem() {
//                    return "Existem dois componentes com o nome " + nome;
//                }
//            };
//        }
    }

    /**
     * Verifica se o componente já foi adicionado a lista
     *
     * @param nome_componente
     * @return
     */
    public static boolean componenteJaAdicionado(String nome_componente) {
        return listaComponentes.stream().filter(x -> x.getNome().equalsIgnoreCase(nome_componente)).count() > 0;
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

        Interruptor interruptor = new Interruptor();

        if (valorPadrao) {
            interruptor.ligar();
        } else {
            interruptor.desligar();
        }

        adicionarComponenteLista(interruptor.criar(x1, x2, y1, y2, yFinal, nome, altura, largura, titulo));
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
        List<Componente> listaSliders = listaComponentes.stream().filter(x -> x.getTipoComponente() == TipoComponente.slider).collect(Collectors.toList());

        if (listaSliders != null && listaSliders.size() > 0) {
            for (Componente slider : listaSliders) {
//                UtilSimulador.setLog("X: " + posicaoXMouse + "\n");
//                UtilSimulador.setLog("Y: " + posicaoYMouse + "\n");
//                UtilSimulador.setLog("X1: " + slider.getX1() + "\n");
//                UtilSimulador.setLog("X2: " + slider.getX2() + "\n");
//                UtilSimulador.setLog("Y1: " + slider.getY1() + "\n");
//                UtilSimulador.setLog("Y2: " + slider.getY2() + "\n");

                if (posicaoXMouse > slider.getX1() && posicaoXMouse < (slider.getX2())
                        && posicaoYMouse > slider.getyFinal() && posicaoYMouse < slider.getyFinal() + 4) {
                    if (botaoPressionado) {
//                        UtilSimulador.setLog("Achou em X:" + posicaoXMouse + "\n");
//                        UtilSimulador.setLog("Achou em Y:" + posicaoYMouse + "\n");
                        return (Slider) slider;
                    }
                }
            }
        }

        return null;
    }

    public static List<Componente> getListaComponentes() {
        return listaComponentes;
    }

}
