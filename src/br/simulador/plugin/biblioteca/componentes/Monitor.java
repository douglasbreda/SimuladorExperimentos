/**
 * Classe que representa as características de um componente do tipo monitor
 */
package br.simulador.plugin.biblioteca.componentes;

/**
 *
 * @author Douglas
 */
public class Monitor extends Componente{

    private String valor_atual;
    
    @Override
    public Componente criar(int x1, int x2, int y1, int y2, int yFinal, String nome, int altura, int largura, String titulo) {
        this.set_x1(x1);
        this.set_x2(x2);
        this.set_y1(y1);
        this.set_y2(y2);
        this.set_tipo_componente(TipoComponente.monitor);
        this.set_distancia(10);
        this.set_nome(nome);
        this.set_altura(altura);
        this.set_largura(largura);
        this.set_titulo(titulo);
        this.set_yFinal(yFinal);

        return this;
    }

    public String getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(String valor_atual) {
        this.valor_atual = valor_atual;
    }
}
