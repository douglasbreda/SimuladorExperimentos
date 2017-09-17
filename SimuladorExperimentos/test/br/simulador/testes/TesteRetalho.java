/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.testes;

import br.simulador.inicializador.Inicializador;
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
public class TesteRetalho {

    private JFrame frame;

    public static void main(String[] args) {
        new TesteRetalho().run();
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
            } catch (ErroExecucaoBiblioteca | InterruptedException ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.setVisible(true);
            try {
                painel.criarPaineis();
            } catch (ErroExecucaoBiblioteca ex) {
                Logger.getLogger(TesteRetalho.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(TesteRetalho.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
