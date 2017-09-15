package br.simulador.inicializador;

import br.simulador.ui.PainelSimulacao;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Douglas
 */
public class Inicializador {

    JFrame frame;

    public static void main(String[] args) {
        new Inicializador().run();
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {

            PainelSimulacao painel = null;
            frame = new JFrame();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1, 1));
            
            
            try {
                painel = new PainelSimulacao(null);
                frame.getContentPane().add(painel);
            } catch (ErroExecucaoBiblioteca ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.setVisible(true);
            painel.criarPaineis();
        });
    }
}
