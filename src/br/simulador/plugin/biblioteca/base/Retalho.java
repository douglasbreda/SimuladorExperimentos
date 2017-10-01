package br.simulador.plugin.biblioteca.base;

import java.util.Random;

/**
 *
 * @author Douglas
 */
public class Retalho {
    
    private int cor;
    private int coordenadaX;
    private int coordenadaY;
    private boolean parede;

    /*
        Construtor padrão
    */
    public Retalho() {
        
    }

    public void set_cor(int cor){
        this.cor = cor;
    }

    //Verificar o tipo de retorno deste método
    public void agentes_aqui() {
        
    }

    //Verificar Retorno
    public void agentes_em_XY(double coordenadaX, double coordenadaY) {
        
    }
    
    public void definir_cor_retalho(int cor) {
//        layout.setColor(cor, coluna, Color.yellow);//Verificar coloração 
    }
    
    public int retornar_cor_retalho() {
        return cor;
//        return layout.getColor(this.linha, this.coluna).getRGB();
    }
    
    public double retornar_max_borda_x() {
        return 0;
    }
    
    public double retornar_max_borda_y() {
        return 0;
    }

    /**
     * Atribui as coordenadas equivalentes da tela aos agentes
     * @param x
     * @param y 
     */
    public void definir_coordenadas(int x, int y) {
        this.coordenadaX = x;
        this.coordenadaY = y;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public boolean is_parede() {
        return parede;
    }

    public void set_parede(boolean parede) {
        this.parede = parede;
    }
    
    
}
