/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.plugin.biblioteca.erro;

import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;

/**
 *
 * @author Douglas
 */
 public class ErroExecucaoSimulador extends ErroExecucaoBiblioteca
    {
        public ErroExecucaoSimulador()
        {
            super("A função \"simular\" não foi implementada");
        }
        
        public ErroExecucaoSimulador(String erro){
            super(erro);
        }
    }
