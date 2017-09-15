package br.simulador.plugin.biblioteca.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author Douglas
 */
public class Retalho extends JLabel {
    
    private int cor;
    private final int id;
    private JLabel label = null;
    private static final long serialVersionUID = 1L;
    private ArrayList<Point> points;
    private ArrayList<Formas> lstFormas;
    
    public Retalho(int id) {
        super();
        
        this.id = id;
        
        inicializar();
    }
    
    private void inicializar() {
        
//        this.setSize(4, 2);
//        
        Random random = new Random();
        
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        Color randomColor = new Color(r, g, b);

        LineBorder border = new LineBorder(randomColor);

        this.setBorder(border);
        
        points = new ArrayList<Point>();
        lstFormas = new ArrayList<>();
        setBackground(Color.WHITE);
//        setBackground(Color.WHITE);
//        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
//                    points.add(new Point(e.getX(), e.getY()));
                    lstFormas.add(new Formas(new Point(e.getX(), e.getY())));
                    repaint();
                } else {
                    mostrarId();
                }
            }
            
        });
    }
    
    private void mostrarId() {
        
        label = new JLabel("");
        
        label.setFont(new Font(label.getFont().getName(), 0, 8));

//            this.label.setText(String.valueOf(this.id));
        this.label.setText("X: " + this.getX());
        
        this.add(label);
        
        this.validate();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.red);
        for (Formas forma : lstFormas) {
            g2.fillOval(forma.getPoint().x, forma.getPoint().y, 20, 20);
        }
    }
    
    public void testarMovimentacaoComponentes() {
        
        Random random = new Random(10);
        
        for (Formas forma : lstFormas) {
            forma.setPoint(new Point(forma.getPoint().x + random.nextInt(10), forma.getPoint().y + random.nextInt(10)));
        }
        
        repaint();
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
