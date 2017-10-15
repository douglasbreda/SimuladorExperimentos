/**
 * Classe para criar o componente de acordo com o seu tipo
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.componentes.Componente;
import br.simulador.plugin.biblioteca.componentes.Interruptor;
import br.simulador.plugin.biblioteca.componentes.Monitor;
import br.simulador.plugin.biblioteca.componentes.Slider;

/**
 *
 * @author Douglas
 */
public final class GerenciadorComponentes {
    
    /**
     * Cria um componente do tipo monitor
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param nome
     * @param valorAtual
     * @return 
     */
    public static Componente criarMonitor(int x1, int x2, int y1, int y2, String nome, String valorAtual ){
        
        Monitor monitor = new Monitor();
        
        monitor.setValor_atual(valorAtual);
        
        return monitor.criar(x1, x2, y1, y2, nome);
    }
    
    /**
     * Cria um componente do tipo Slider
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param nome
     * @param valorPadrao
     * @param valorMaximo
     * @param valorMinimo
     * @return 
     */
    public static Componente criarSlider(int x1, int x2, int y1, int y2, String nome, double valorPadrao, double valorMaximo, double valorMinimo){
        
        Slider slider = new Slider();
        
        slider.setValor_atual(valorPadrao);
        
        slider.setValor_maximo(valorMaximo);
        
        slider.setValor_minimo(valorMinimo);
        
        return slider.criar(x1, x2, y1, y2, nome);
    }
    
    /**
     * Cria um componente do tipo Interruptor
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param nome
     * @param valorPadrao
     * @return 
     */
    public static Componente criarInterruptor(int x1, int x2, int y1, int y2, String nome, boolean valorPadrao){
        
        Interruptor interruptor = new Interruptor();
        
        if(valorPadrao)
            interruptor.ligar();
        else
            interruptor.desligar();
        
        return interruptor.criar(x1, x2, y1, y2, nome);
    }
}
