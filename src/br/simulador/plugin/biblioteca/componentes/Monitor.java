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
    public Componente criar(int x1, int x2, int y1, int y2, String nome) {
        this.setX1(x1);
        this.setX2(x2);
        this.setY1(y1);
        this.setY2(y2);
        this.setTipoComponente(TipoComponente.monitor);
        this.setDistancia(10);
        this.setNome(nome);

        return this;
    }

    public String getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(String valor_atual) {
        this.valor_atual = valor_atual;
    }
}
