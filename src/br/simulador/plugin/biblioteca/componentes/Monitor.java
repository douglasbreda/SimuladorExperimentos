/**
 * Classe que representa as características de um componente do tipo monitor
 */
package br.simulador.plugin.biblioteca.componentes;

/**
 *
 * @author Douglas
 */
public class Monitor extends Componente{

    private String valorAtual;
    
    @Override
    public Componente criar(int x1, int x2, int y1, int y2, int yFinal, String nome, int altura, int largura, String titulo) {
        this.setX1(x1);
        this.setX2(x2);
        this.setY1(y1);
        this.setY2(y2);
        this.setTipoComponente(TipoComponente.monitor);
        this.setDistancia(10);
        this.setNome(nome);
        this.setAltura(altura);
        this.setLargura(largura);
        this.setTitulo(titulo);
        this.setYFinal(yFinal);

        return this;
    }

    public String getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(String valorAtual) {
        this.valorAtual = valorAtual;
    }
}
