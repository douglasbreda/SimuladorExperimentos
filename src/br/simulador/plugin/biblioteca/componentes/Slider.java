/**
 * Classe que representa as características do componente "Slider"
 */
package br.simulador.plugin.biblioteca.componentes;

/**
 *
 * @author Douglas
 */
public class Slider extends Componente{

    private double valor_minimo = 0;
    private double valor_atual = 0;
    private double valor_maximo = 0;
    
    @Override
    public Componente criar(int x1, int x2, int y1, int y2, int yFinal, String nome, int altura, int largura, String titulo) {
        this.setX1(x1);
        this.setX2(x2);
        this.setY1(y1);
        this.setY2(y2);
        this.setTipoComponente(TipoComponente.slider);
        this.setDistancia(10);
        this.setNome(nome);
        this.setAltura(altura);
        this.setLargura(largura);
        this.setTitulo(titulo);
        this.setyFinal(yFinal);

        return this;
    }

    public double getValor_minimo() {
        return valor_minimo;
    }

    public void setValor_minimo(double valor_minimo) {
        this.valor_minimo = valor_minimo;
    }

    public double getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(double valor_atual) {
        this.valor_atual = valor_atual;
    }

    public double getValor_maximo() {
        return valor_maximo;
    }

    public void setValor_maximo(double valor_maximo) {
        this.valor_maximo = valor_maximo;
    }
}
