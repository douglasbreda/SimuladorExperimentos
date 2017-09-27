package br.simulador.plugin.biblioteca.componentes;

import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.plugin.biblioteca.base.Retalho;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import layout.TableLayout;

/**
 *
 * @author Douglas
 */
public class PainelBase extends javax.swing.JPanel {

    private ArrayList<Point2D> points;
    private TableLayout layout = null;
    private ArrayList<Retalho> listaRetalhos = null;
    private ArrayList<IAgente> listaAgentes = null;

    /**
     * Construtor padrão
     */
    public PainelBase() {
        initComponents();
        inicializar();
    }

    /**
     * Inicializa as propriedades necessárias para a execução
     */
    private void inicializar() {
        points = new ArrayList<Point2D>();
        setBackground(Color.WHITE);

        double size[][] = {
            {0.25, 0.25, 0.25, 0.25},
            {50, TableLayout.FILL, 40, 40, 40}};

        layout = new TableLayout(size);
        this.setLayout(layout);
        this.inicializar_lista_retalhos();
    }

    /**
     * Inicializa a lista de retalhos da simulação
     */
    private void inicializar_lista_retalhos() {
        listaRetalhos = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < layout.getRow().length; i++) {
            for (int j = 0; j < layout.getColumn().length; j++) {
//                listaRetalhos.add(new Retalho(++id, i, j, layout));
                  listaRetalhos.add(new Retalho());
            }
        }
    }

    /**
     * Sobrecarga do método de paint para desenhar os agentes na tela
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        layout.drawGrid(this, g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.red);
        for (Point2D point : points) {
            Ellipse2D.Double shape = new Ellipse2D.Double(point.getX(), point.getY(), 20, 30);
            g2.draw(shape);
//            g2.fillOval(point.x, point.y, 20, 20);
        }

        layout.updatePoints(points);
        
        this.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
    }

    /**
     * Adiciona um agente a lista de agentes
     *
     * @param agente agente a ser adicionado
     */
    public void adicionar_agente_lista(IAgente agente) {
        if (listaAgentes == null) {
            listaAgentes = new ArrayList<>();
        }

        listaAgentes.add(agente);
    }

    /**
     * Cria a lista de Points a partir das posições dos agentes
     */
    public void criar_posicoes_agentes() throws ErroExecucaoBiblioteca, InterruptedException {

        if (points == null) {
            points = new ArrayList<>();
        }

        for (IAgente agente : listaAgentes) {
            points.add(new Point2D.Double(agente.retornar_coordenada_X(), agente.retornar_coordenada_Y()));
        }
        repaint();
    }

    public TableLayout getLayout() {
        return layout;
    }

    public ArrayList<IAgente> getListaAgentes() {
        return listaAgentes;
    }
    
    /**
     * Limpa os agentes da simulação
     */
    public void limpar_tudo(){
        listaAgentes.clear();
        points = new ArrayList<>();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
