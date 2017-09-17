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

    private JFrame frame;
    private PainelSimulacao painel = null;

    public static void main(String[] args) {
        new Inicializador().run();
    }

    /**
     * Inicializa um novo frame e adiciona o painel do plugin
     */
    public void run() {
        SwingUtilities.invokeLater(() -> {

            
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
            try {
                painel.criarPaineis();
            } catch (ErroExecucaoBiblioteca ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
