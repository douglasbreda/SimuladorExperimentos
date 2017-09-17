package br.simulador.plugin.biblioteca.base;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Douglas
 */
public class Agente extends BaseAgente
{
    private JPanel painelAgente = null;
    
    public Agente(int coordenadaX, int coordenadaY, int id)
    {
        super(coordenadaX, coordenadaY, id);
        criarFormaAgente();
    }
    
    public void criarFormaAgente(){
        painelAgente = new JPanel(){
            @Override
            public void paint(Graphics g) {
                desenharCirculo(g);
            }
        };
    }
    
    private void desenharCirculo(Graphics g){
        g.fillOval(Integer.parseInt(String.valueOf(this.retornar_coordenada_X())),
                   Integer.parseInt(String.valueOf(this.retornar_coordenada_Y())),
                   100, 50);
    }

    @Override
    public JPanel getPainel() {
        return painelAgente;
    }
    
    
}
