package br.simulador.inicializador;

import br.simulador.gerenciadores.GerenciadorInterface;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Douglas
 */
public class Inicializador {

    public static void main(String[] args) throws ErroExecucaoBiblioteca, InterruptedException, InvocationTargetException {
        GerenciadorInterface.getInstance().inicializarTela();
        //System.exit(0);
    }
}
