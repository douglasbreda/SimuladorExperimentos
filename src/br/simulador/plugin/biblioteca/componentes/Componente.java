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
    private TipoComponente tipoComponente;

    //Define uma distância fixa para o próximo componente que está sendo criado
    private int distancia;
    
    //Define um identificador para o componente
    private String nome;

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public TipoComponente getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(TipoComponente tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getProxima_posicao_y1() {
        return y1 + distancia;
    }

    public int getProxima_posicao_y2() {
        return y2 + distancia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Cria uma nova instância das características de um compoenente para
     * controle na tela
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param nome
     * @return
     */
    public abstract Componente criar(int x1, int x2, int y1, int y2, String nome);
}
