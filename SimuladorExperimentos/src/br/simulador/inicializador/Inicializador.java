package br.simulador.inicializador;

import br.simulador.ui.PainelSimulacao;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.awt.GridLayout;
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

            frame = new JFrame();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1, 1));

            try {
                frame.getContentPane().add(new PainelSimulacao(null));
            } catch (ErroExecucaoBiblioteca ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.setVisible(true);

        });
    }
}
