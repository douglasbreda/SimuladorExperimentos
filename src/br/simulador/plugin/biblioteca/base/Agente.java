package br.simulador.plugin.biblioteca.base;

import br.simulador.util.UtilSimulador;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Douglas
 */
public class Agente extends BaseAgente
{
    private JPanel painelAgente = null;

    public Agente() {
        super(0, 0, 0, 0);
    }
    
    public Agente(int coordenadaX, int coordenadaY, int id, int velocidade)
    {
        super(coordenadaX, coordenadaY, id, velocidade);
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
        g.fillOval(UtilSimulador.toInt(this.retornar_coordenada_X()),
                   UtilSimulador.toInt(this.retornar_coordenada_Y()),
                   100, 50);
    }

    @Override
    public JPanel getPainel() {
        return painelAgente;
    }
}
