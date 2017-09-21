package br.simulador.plugin.acoes;

import br.simulador.ui.PainelSimulacao;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.ps.plugins.base.Plugin;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Douglas
 */
public class AcaoEstatica extends AbstractAction {

    private JFrame frame;

    public AcaoEstatica(Plugin plugin) {
//        super("Ação personalizada estática", carregarIcone());
        super("Teste novo caminho", carregarIcone());
//        carregar_painel_simulacao(plugin);
    }

    private void carregar_painel_simulacao(Plugin plugin) {

        SwingUtilities.invokeLater(() -> {

            frame = new JFrame();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1, 1));
            try {
                frame.getContentPane().add(new PainelSimulacao(plugin));
            } catch (ErroExecucaoBiblioteca ex) {
                Logger.getLogger(AcaoEstatica.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(AcaoEstatica.class.getName()).log(Level.SEVERE, null, ex);
            }

            frame.setVisible(true);

        });
    }

    private static Icon carregarIcone() {
        try {
            String caminho = "icone_32x32.png";
            Image imagem = ImageIO.read(AcaoEstatica.class.getClassLoader().getResourceAsStream(caminho));

            return new ImageIcon(imagem);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Teste caminho!!", "Nem me viu", JOptionPane.INFORMATION_MESSAGE);
    }

}
