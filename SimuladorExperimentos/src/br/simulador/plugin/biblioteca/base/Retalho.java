package br.simulador.plugin.biblioteca.base;

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Douglas
 */
public class Retalho extends JPanel {

    private int cor;
    private final int id;
    private JLabel label = null;

    public Retalho(int id) {
        super();

        this.id = id;

        inicializar();
    }

    private void inicializar() {
        
        this.setSize(4, 2);

        Random random = new Random();

        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        Color randomColor = new Color(r, g, b);

        LineBorder border = new LineBorder(randomColor);

        this.setBorder(border);
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
        Random randomX = new Random();
        Random randomY = new Random();

        return new RetalhoCoordenadas(randomX.nextDouble(), randomY.nextDouble());
    }

}
