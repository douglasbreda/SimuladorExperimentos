/**
 * Classe que representa as características do componente "Slider"
 */
package br.simulador.plugin.biblioteca.componentes;

/**
 *
 * @author Douglas
 */
public class Slider extends Componente {

    private double valor_minimo = 0;
    private double valor_atual = 0;
    private double valor_maximo = 0;
    private double valor_display = 0;

    //Equivale ao cem por cento da barra, ou seja, as posições possíveis desde o ínicio até o final do slider
    int cem_por_cento = 0;

    //A diferença é para que seja considerada somente a parte interna do slider, ou seja
    // se a barra do slider começa na posição x=23 e termina na posição x=154
    // o valor da variável cem_por_cento será 131 (154-23)=131.
    //Logo, se o usuário clicar no final da barra (x=154), ao descontar a diferença (154-131)=23
    //Teremos 154-23=131, logo, saberemos que o usuário chegou no final da barra
    int diferenca = 0;

    @Override
    public Componente criar(int x1, int x2, int y1, int y2, int yFinal, String nome, int altura, int largura, String titulo) {
        this.set_x1(x1);
        this.set_x2(x2);
        this.set_y1(y1);
        this.set_y2(y2);
        this.set_tipo_componente(TipoComponente.slider);
        this.set_distancia(10);
        this.set_nome(nome);
        this.set_altura(altura);
        this.set_largura(largura);
        this.set_titulo(titulo);
        this.set_yFinal(yFinal);
        this.cem_por_cento = (this.get_x1() + this.get_largura()) - this.get_x1();
        this.diferenca = (this.get_x1() + this.get_largura()) - cem_por_cento;
        this.calcular_valor_inicial(this.valor_display);

        return this;
    }

    /**
     * Calcula a posição inicial do cursor no slider
     */
//    private void calcularValorPadraoInicialSlider() {
//        this.valor_atual = ((valor_atual * 100) / valor_maximo);
//
//        valor_atual = ((valor_atual * this.get_largura()) / 100);
//    }
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

    public double getValor_display() {
        return valor_display;
    }

    public void setValor_display(double valor_display) {
        this.valor_display = valor_display;
    }

    /**
     * Calcula os valores para atribuir a posição ao slider
     *
     * @param posicaoXAtual
     */
    public void calcular_valores_slider(int posicaoXAtual) {

        int posicao_real = posicaoXAtual - diferenca;

        this.valor_atual = ((posicao_real * 100) / cem_por_cento) + diferenca;
        this.valor_display = ((valor_atual - diferenca) * this.valor_maximo) / 100;

//        if (slider.getValor_atual() == (slider.get_largura() - 1)) {
//            slider.setValor_atual(slider.getValor_maximo());
//        } else if (slider.getValor_atual() == 1.0) {
//            slider.setValor_atual(0.0);
//        } else {
//            slider.setValor_atual(Math.round(((slider.getValor_atual() * 100) / slider.get_largura())));
//            slider.setValor_atual(Math.round((slider.getValor_atual() * slider.getValor_maximo()) / 100));
//        }
    }

    /**
     * Calcula um valor inicial que vem setado por padrão pelo usuário
     *
     * @param valor_inicial
     */
    public void calcular_valor_inicial(double valor_inicial) {
        this.valor_atual = ((valor_inicial * cem_por_cento) / 100) + diferenca;
        this.valor_display = valor_inicial;
    }
}
