package br.simulador.plugin;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.plugin.acoes.AcaoEstatica;
import br.simulador.plugin.biblioteca.Experimentos;
import br.simulador.ui.PainelSimulacao;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import br.univali.ps.plugins.base.VisaoPlugin;

/**
 *
 * @author Douglas
 */
public final class SimuladorExperimentos extends Plugin {
    
    private final VisaoPlugin visaoPlugin;
    private UtilizadorPlugins utilizadorPlugins;

    public SimuladorExperimentos() throws ErroExecucaoBiblioteca, InterruptedException {
        this.visaoPlugin = new PainelSimulacao(this);
    }
    
    @Override
    protected void inicializar(UtilizadorPlugins utilizador) {
        this.utilizadorPlugins = utilizador;
        this.utilizadorPlugins.instalarAcaoPlugin(this, new AcaoEstatica(this));
        this.utilizadorPlugins.registrarBiblioteca(Experimentos.class);        
        super.inicializar(utilizador);
        GerenciadorExecucao.getInstance().setPlugin(this);
    }
    
    @Override
    public VisaoPlugin getVisao() {
        return visaoPlugin;
    }
    
    @Override
    public UtilizadorPlugins getUtilizadorPlugins() {
        return utilizadorPlugins;
    }
}
