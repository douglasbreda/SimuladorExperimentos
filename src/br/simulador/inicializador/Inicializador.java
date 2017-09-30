package br.simulador.inicializador;

import br.simulador.gerenciadores.GerenciadorInicializacao;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;

/**
 *
 * @author Douglas
 */
public class Inicializador {

    public static void main(String[] args) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorInicializacao.getInstance().inicializarTela();
        //System.exit(0);
    }
}
