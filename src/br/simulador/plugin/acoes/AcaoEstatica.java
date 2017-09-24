package br.simulador.plugin.acoes;

import br.univali.ps.plugins.base.Plugin;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class AcaoEstatica extends AbstractAction {

    public AcaoEstatica(Plugin plugin) {
//        super("Ação personalizada estática", carregarIcone());
        super("Teste novo caminho", carregarIcone());
//        carregar_painel_simulacao(plugin);
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
