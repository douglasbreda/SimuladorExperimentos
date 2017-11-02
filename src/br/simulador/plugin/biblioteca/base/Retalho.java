package br.simulador.plugin.biblioteca.base;

/**
 *
 * @author Douglas
 */
public class Retalho {
    
    private int cor;
    private int coordenadaX;
    private int coordenadaY;
    
    //Define se o retalho é uma parede ou não
    private boolean parede;
    
    //Define se o retalho é uma parede na posição X ou Y
    private TipoParede tipoParede;
    
    //Identificado único do retalho
    private final int id;
    
    /**
     * Construtor padrão dos retalhos
     * 
     * @param id
     * @param cor 
     */
    public Retalho(int id, int cor) {
        this.id = id;
        this.cor = cor;
    }

    public void set_cor(int cor){
        this.cor = cor;
    }
    
    public int retornar_cor_retalho() {
        return cor;
//        return layout.getColor(this.linha, this.coluna).getRGB();
    }
    
    /**
     * Atribui as coordenadas equivalentes da tela aos agentes
     * 
     * @param x
     * @param y 
     */
    public void definirCoordenadas(int x, int y) {
        this.coordenadaX = x;
        this.coordenadaY = y;
    }

    /**
     * Retorna a coordenada x do retalho
     * 
     * @return 
     */
    public int getCoordenadaX() {
        return coordenadaX;
    }

    /**
     * Retorna a coordenada Y do retalho
     * 
     * @return 
     */
    public int getCoordenadaY() {
        return coordenadaY;
    }

    /**
     * Retorna se o retalho faz parte de uma parede (para tratamento de colisões)
     * 
     * @return 
     */
    public boolean ehParede(){
        return parede;
    }
    
    /**
     * Define se o retalho será uma parede (para tratamento de colisões)
     * 
     * @param eh_parede 
     * @param tipo_parede 
     */
    public void definirComoParede(boolean eh_parede, TipoParede tipo_parede){
        this.parede = eh_parede;
        this.tipoParede = tipo_parede;
    }

    /**
     * Retorna o identificador único do retalho
     * 
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna qual é o tipo da parede do retalho (X ou Y)
     * 
     * @return 
     */
    public TipoParede getTipoParede() {
        return tipoParede;
    }
}
