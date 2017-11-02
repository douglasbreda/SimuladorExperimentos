package br.simulador.inicializador;

import br.simulador.gerenciadores.GerenciadorInterface;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Douglas
 */
public class Inicializador {

    public static void main(String[] args) throws ErroExecucaoBiblioteca, InterruptedException, InvocationTargetException, ErroExecucao {
        GerenciadorInterface.getInstance().inicializarTela();
        GerenciadorInterface.getInstance().renderizarTela();
    }
}
