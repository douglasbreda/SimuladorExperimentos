package br.simulador.plugin.biblioteca.base;

import java.util.Random;
import layout.TableLayout;

/**
 *
 * @author Douglas
 */
public class Retalho {
    
    private int cor;
    private final int id;
    private final int linha;
    private final int coluna;
    private final TableLayout layout;
    
    /*
        Construtor padrão
    */
    public Retalho(int id, int linha, int coluna, TableLayout layout) {
        super();
        this.id = id;
        this.linha = linha;
        this.coluna = coluna;
        this.layout = layout;
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

    //Verificar o tipo de retorno deste método
    public void agentes_aqui() {
        
    }

    //Verificar Retorno
    public void agentes_em_XY(double coordenadaX, double coordenadaY) {
        
    }
    
    public void definir_cor_retalho(int cor) {
        
    }
    
    public int retornar_cor_retalho() {
        return 0;
    }
    
    public double retornar_max_borda_x() {
        return 0;
    }
    
    public double retornar_max_borda_y() {
        return 0;
    }

    //Define uma coordenada inicial para instanciar o agente
    public RetalhoCoordenadas definirCoordenadasIniciais() {
        Random randomX = new Random(800);
        Random randomY = new Random(600);
        
        return new RetalhoCoordenadas(randomX.nextDouble() + 1, randomY.nextDouble() + 1);
    }
}
