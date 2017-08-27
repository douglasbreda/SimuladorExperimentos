/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.plugin;

import br.simulador.plugin.acoes.AcaoEstatica;
import br.simulador.ui.PainelSimulacao;
import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import br.univali.ps.plugins.base.VisaoPlugin;

/**
 *
 * @author Douglas
 */
public final class SimuladorExperimentos extends Plugin{

    private final VisaoPlugin visaoPlugin = new PainelSimulacao(this);
    private UtilizadorPlugins utilizadorPlugins;

    @Override
    protected void inicializar(UtilizadorPlugins utilizador) {
        this.utilizadorPlugins = utilizador;
        this.utilizadorPlugins.instalarAcaoPlugin(this, new AcaoEstatica());
        super.inicializar(utilizador);
    }
    
    
    
    @Override
    public VisaoPlugin getVisao() {
        return visaoPlugin;
    }

    public UtilizadorPlugins getUtilizadorPlugins() {
        return utilizadorPlugins;
    }
    
    
}
