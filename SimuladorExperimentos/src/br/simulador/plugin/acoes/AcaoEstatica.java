/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.plugin.acoes;

import br.simulador.ui.PainelSimulacao;
import br.univali.ps.plugins.base.Plugin;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
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
public class AcaoEstatica extends AbstractAction{
    
    private JFrame frame;
    public AcaoEstatica(Plugin plugin)
    {
        super("Ação personalizada estática", carregarIcone());
        carregar_painel_simulacao(plugin);
    }
    
    private void carregar_painel_simulacao(Plugin plugin){
        
        SwingUtilities.invokeLater(() ->{

            frame = new JFrame();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1, 1));
            frame.getContentPane().add(new PainelSimulacao(plugin));
                   
            frame.setVisible(true);
                    
        });
    }

    private static Icon carregarIcone()
    {
        try
        {
            String caminho = "icone_32x32.png";
            Image imagem = ImageIO.read(AcaoEstatica.class.getClassLoader().getResourceAsStream(caminho));

            return new ImageIcon(imagem);
        }
        catch (IOException ex)
        {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane.showMessageDialog(null, "Funciona ow merda!!", "Nem me viu", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
