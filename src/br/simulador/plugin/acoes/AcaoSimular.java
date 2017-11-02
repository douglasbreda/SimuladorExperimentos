package br.simulador.plugin.acoes;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.gerenciadores.GerenciadorFuncao;
import br.simulador.plugin.SimuladorExperimentos;
import br.simulador.plugin.biblioteca.erro.ErroExecucaoSimulador;
import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.Portugol;
import br.univali.portugol.nucleo.Programa;
import br.univali.portugol.nucleo.SimuladorPrograma;
import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
public class AcaoSimular extends AbstractAction {

    //Instância do plugin
    private SimuladorExperimentos plugin;

//    private final ExecutorService servico = Executors.newSingleThreadExecutor();
//    private Future<Object> tarefaSimulacao;

    /**
     * Construtor da ação
     * 
     * @param plugin 
     */
    public AcaoSimular(SimuladorExperimentos plugin) {
        super("Ação para buscar os códigos da função", carregarIcone());
        this.plugin = plugin;
    }

    /**
     * Carrega o ícone do plugin para ser exibido no Portugol Studio
     * 
     */
    private static Icon carregarIcone() {
        try {
            String caminho = "icone_32x32.png";
            Image imagem = ImageIO.read(AcaoSimular.class.getClassLoader().getResourceAsStream(caminho));

            return new ImageIcon(imagem);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Método chamada para dar início ao processo de simulação
     * 
     * @throws ErroCompilacao
     * @throws ExcecaoVisitaASA
     * @throws ErroExecucao
     * @throws InterruptedException 
     */
    private void executar() throws ErroCompilacao, ExcecaoVisitaASA, ErroExecucao, InterruptedException {
        final Programa programa = Portugol.compilarParaAnalise(plugin.getUtilizadorPlugins().obterCodigoFonteUsuario());
        ASAPrograma asa = plugin.getUtilizadorPlugins().obterASAProgramaAnalisado();
        GerenciadorFuncao gerenciadorFuncao = new GerenciadorFuncao(asa);
        gerenciadorFuncao.buscar_declaracao_metodo("simular");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        new Thread(() -> {
            try {
                executar();
            } catch (ErroCompilacao | ExcecaoVisitaASA | ErroExecucao | InterruptedException ex) {
                Logger.getLogger(AcaoSimular.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
//        try {
//        if (tarefaSimulacao == null) {
//            executarSimulacao();
//        }
//
//        while (!tarefaSimulacao.isDone()) {
////                Thread.sleep(50);
//        }
//
////            tarefaSimulacao.get().simular(false);
////        } catch (InterruptedException ex) {
//        }).start();
    }

//    private void executarSimulacao() {
//        if (tarefaSimulacao != null) {
//            tarefaSimulacao.cancel(true);
//        }
//
//        tarefaSimulacao = servico.submit(() -> {
//
//            final Programa programa = Portugol.compilarParaAnalise(plugin.getUtilizadorPlugins().obterCodigoFonteUsuario());
//            ASAPrograma asa = plugin.getUtilizadorPlugins().obterASAProgramaAnalisado();
//            GerenciadorFuncao gerenciadorFuncao = new GerenciadorFuncao(asa);
//            SimuladorPrograma simulador = gerenciadorFuncao.buscar_declaracao_metodo("simular");
//            simulador.simular(false);
//
//            while (GerenciadorExecucao.getInstance().simulacaoVisivel()) {
//
//            }
//            return new Object();
//        });
//    }

}
