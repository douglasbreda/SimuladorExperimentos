package br.simulador.inicializador;

import br.simulador.ui.PainelSimulacao;
import java.awt.GridLayout;
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

            frame.getContentPane().add(new PainelSimulacao(null));

            frame.setVisible(true);

        });
    }
}
