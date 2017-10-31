/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.testes.documentacao;

import br.simulador.plugin.biblioteca.Experimentos;
import br.simulador.testes.GasLabJava;
import br.univali.portugol.nucleo.bibliotecas.Tipos;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class Teste2 {
    Experimentos exp = new Experimentos();
    Tipos tipo = new Tipos();

    private Object agenteAtual;

    public static void main(String[] args) throws ErroExecucao, InterruptedException {
        Teste2 gas = new Teste2();
        gas.configurar();
//        gas.simular();
    }

    public Object getAgenteAtual() {
        return agenteAtual;
    }

    private void inicio() throws InterruptedException, ErroExecucao, ErroExecucaoBiblioteca {
        exp.definir_forma_agentes(0);
        exp.criar_agentes(10, true);
        exp.executar_sempre(true);
//        exp.definir_bordas(-256);
    }

    private List<?> listaAgentes;

    public void setListaAgentes(List<?> listaAgentes) {
        this.listaAgentes = listaAgentes;
    }

    public void configurar() throws ErroExecucao, InterruptedException {
        exp.simular();
        setListaAgentes(exp.retornar_lista_agentes());
        inicio();
        exp.atualizar_tela();
    }
}
