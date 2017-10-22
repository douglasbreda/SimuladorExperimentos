/**
 * Classe que contém as configurações de cada componente criado para saber onde deve iniciar o próximo
 */
package br.simulador.plugin.biblioteca.componentes;

/**
 *
 * @author Douglas
 */
public abstract class Componente {

    //Posição inicial do x do componente
    private int x1;

    //Posição final do x do componente
    private int x2;

    //Posição inicial do y do componente
    private int y1;

    //Posição final do y do componente
    private int y2;

    //Define o tipo do componente
    private TipoComponente tipo_componente;

    //Define uma distância fixa para o próximo componente que está sendo criado
    private int distancia;
    
    //Define um identificador para o componente
    private String nome;
    
    //Armazena a largura do componente
    private int largura;
    
    //Armazena a altura do componente
    private int altura;
    
    //Armazena o título do componente
    private String titulo;
    
    //Armazena o último y para saber onde começa o próximo componente
    private int yFinal;

    public int get_x1() {
        return x1;
    }

    public void set_x1(int x1) {
        this.x1 = x1;
    }

    public int get_x2() {
        return x2;
    }

    public void set_x2(int x2) {
        this.x2 = x2;
    }

    public int get_y1() {
        return y1;
    }

    public void set_y1(int y1) {
        this.y1 = y1;
    }

    public int get_y2() {
        return y2;
    }

    public void set_y2(int y2) {
        this.y2 = y2;
    }

    public TipoComponente get_tipo_componente() {
        return tipo_componente;
    }

    public void set_tipo_componente(TipoComponente tipo_componente) {
        this.tipo_componente = tipo_componente;
    }

    public void set_distancia(int distancia) {
        this.distancia = distancia;
    }

    public int get_distancia() {
        return distancia;
    }

    public int getProxima_posicao_y1() {
        return y1 + distancia;
    }

    public int getProxima_posicao_y2() {
        return y2 + distancia + altura;
    }

    public String get_nome() {
        return nome;
    }
    
    public int get_proximo_y(){
        return yFinal + distancia;
    }

    public void set_nome(String nome) {
        this.nome = nome;
    }

    public int get_largura() {
        return largura;
    }

    public void set_largura(int largura) {
        this.largura = largura;
    }

    public int get_altura() {
        return altura;
    }

    public void set_altura(int altura) {
        this.altura = altura;
    }

    public String get_titulo() {
        return titulo;
    }

    public void set_titulo(String titulo) {
        this.titulo = titulo;
    }

    public int get_yFinal() {
        return yFinal;
    }

    public void set_yFinal(int yFinal) {
        this.yFinal = yFinal;
    }
    
    

    /**
     * Cria uma nova instância das características de um compoenente para
     * controle na tela
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param yFinal
     * @param nome
     * @param altura
     * @param largura
     * @param titulo
     * @return
     */
    public abstract Componente criar(int x1, int x2, int y1, int y2, int yFinal, String nome, int altura, int largura, String titulo);
}
