/**
 * Classe que contém as carac
 */
package br.simulador.plugin.biblioteca.componentes;

/**
 *
 * @author Douglas
 */
public class Interruptor extends Componente{

    public boolean ligado;
    
    @Override
    public Componente criar(int x1, int x2, int y1, int y2, String nome) {
        this.setX1(x1);
        this.setX2(x2);
        this.setY1(y1);
        this.setY2(y2);
        this.setTipoComponente(TipoComponente.monitor);
        this.setDistancia(3);
        this.setNome(nome);

        return this;
    }
    
    public void ligar(){
        this.ligado = true;
    }
    
    public void desligar(){
        this.ligado = false;
    }
}
