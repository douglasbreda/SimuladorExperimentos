/**
 * Classe que contém as carac
 */
package br.simulador.plugin.biblioteca.componentes;

import br.simulador.util.UtilSimulador;

/**
 *
 * @author Douglas
 */
public class Interruptor extends Componente{

    private boolean ligado;
    
    @Override
    public Componente criar(int x1, int x2, int y1, int y2, int yFinal, String nome,int altura, int largura, String titulo) {
        this.set_x1(x1);
        this.set_x2(x2);
        this.set_y1(y1);
        this.set_y2(y2);
        this.set_tipo_componente(TipoComponente.interruptor);
        this.set_distancia(3);
        this.set_nome(nome);
        this.set_altura(altura);
        this.set_largura(largura);
        this.set_titulo(titulo);
        this.set_yFinal(yFinal);

        return this;
    }
    
    public void ligar(){
        this.ligado = true;
        UtilSimulador.setLog("Veja só, ligou :)");
    }
    
    public void desligar(){
        this.ligado = false;
        UtilSimulador.setLog("It is off baby");
    }

    public boolean isLigado() {
        return ligado;
    }
}
