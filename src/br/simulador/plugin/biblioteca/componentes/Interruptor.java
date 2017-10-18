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
        this.setX1(x1);
        this.setX2(x2);
        this.setY1(y1);
        this.setY2(y2);
        this.setTipoComponente(TipoComponente.interruptor);
        this.setDistancia(3);
        this.setNome(nome);
        this.setAltura(altura);
        this.setLargura(largura);
        this.setTitulo(titulo);
        this.setyFinal(yFinal);

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
