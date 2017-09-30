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
        definirCoordenadasIniciais();
    }

    
    public void testarMovimentacaoComponentes() {
        
//        Random random = new Random(10);
//        
//        for (Formas forma : lstFormas) {
//            forma.setPoint(new Point(forma.getPoint().x + random.nextInt(10), forma.getPoint().y + random.nextInt(10)));
//        }
//        
//        repaint();
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

    //Define uma coordenada inicial para instanciar o agente
    public void definirCoordenadasIniciais() {
        Random randomX = new Random(32);
        Random randomY = new Random(32);
        
        RetalhoCoordenadas c = new RetalhoCoordenadas(randomX.nextInt()+ 1, randomY.nextInt()+ 1);
        
        this.coordenadaX = c.getCoordenadaX();
        this.coordenadaY = c.getCoordenadaY();
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
