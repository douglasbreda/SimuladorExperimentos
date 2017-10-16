package br.simulador.plugin.acoes;

import br.simulador.gerenciadores.GerenciadorFuncao;
import br.simulador.plugin.SimuladorExperimentos;
import br.simulador.plugin.biblioteca.erro.ErroExecucaoSimulador;
import br.simulador.ui.JanelaCodigoFonte;
import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.Portugol;
import br.univali.portugol.nucleo.Programa;
import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Douglas
 */
public class AcaoEstatica extends AbstractAction {

    private SimuladorExperimentos plugin;

    public AcaoEstatica(SimuladorExperimentos plugin) {
        super("Ação para buscar os códigos da função", carregarIcone());
        this.plugin = plugin;
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

        new Thread(() -> {
            try {
                final Programa programa = Portugol.compilarParaAnalise(plugin.getUtilizadorPlugins().obterCodigoFonteUsuario());
                ASAPrograma asa = plugin.getUtilizadorPlugins().obterASAProgramaAnalisado();
                GerenciadorFuncao gerenciadorFuncao = new GerenciadorFuncao(asa);
                gerenciadorFuncao.buscar_declaracao_metodo("simular");
            } catch (ExcecaoVisitaASA | ErroExecucao | InterruptedException | ErroCompilacao ex) {
            }
        }).start();

    }

}
