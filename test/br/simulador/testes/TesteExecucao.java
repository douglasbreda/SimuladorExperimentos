/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.testes;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.plugin.biblioteca.base.Agente;
import br.simulador.plugin.biblioteca.base.IAgente;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class TesteExecucao {

    private ArrayList<IAgente> listaAgentes = null;

    public static void main(String[] args) {

        try {
            new TesteExecucao().executarTeste();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TesteExecucao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void executarTeste() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        iniciarLista();
        GerenciadorExecucao.getInstance().executarMetodo("retornar_id", null, new Object[0]);
    }

    private void iniciarLista() {
        for (int i = 0; i < 5; i++) {
            GerenciadorExecucao.getInstance().addAgente(new Agente(10, 10, i));
        }
    }
}
