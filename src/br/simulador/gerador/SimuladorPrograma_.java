/**
 * Classe abstrata que representa a classe gerada para a execução da simulação
 */
package br.simulador.gerador;

import br.simulador.plugin.biblioteca.base.IAgente;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.util.List;

/**
 *
 * @author Douglas
 */
public abstract class SimuladorPrograma_ {

    public SimuladorPrograma_() {
    }

    protected void inicializar() throws ErroExecucao, InterruptedException {
    }

    public void simular(boolean sempre, List<IAgente> agentes) throws ErroExecucao, InterruptedException {
    }
}
