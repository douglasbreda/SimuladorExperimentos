/**
 * Classe para controle das telas e inicialiação do plugin
 */
package br.simulador.gerenciadores;

import br.simulador.inicializador.Inicializador;
import br.simulador.plugin.biblioteca.componentes.PainelBase;
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
public final class GerenciadorInicializacao {

    public static GerenciadorInicializacao instance = null;

    public static Inicializador inicializador = null;

    private static JFrame frame = null;

    private PainelSimulacao ambienteSimulacao = null;

    /**
     * Instância da classe estática que controla a inicialização da simulação
     * @return 
     */
    public static GerenciadorInicializacao getInstance() {

        if (instance == null) {
            instance = new GerenciadorInicializacao();
        }

        return instance;
    }

    
    /**
     * Chama a tela de inicialização do Simulador
     */
    public void inicializarTela() {
        SwingUtilities.invokeLater(() -> {

            frame = new JFrame();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1, 1));

            try {
                ambienteSimulacao = new PainelSimulacao(null);
                frame.getContentPane().add(ambienteSimulacao);
            } catch (ErroExecucaoBiblioteca ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Inicializador.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.setVisible(true);
            
            try {
                ambienteSimulacao.criar_paineis();
            } catch (ErroExecucaoBiblioteca ex) {
                Logger.getLogger(GerenciadorInicializacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GerenciadorInicializacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Retorna a instância do ambiente onde está ocorrendo a simulação
     * @return 
     */
    public PainelBase getAmbienteSimulacao() {
        return ambienteSimulacao.getPainelSimulacao();
    }
}
