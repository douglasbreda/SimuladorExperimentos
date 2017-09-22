package br.simulador.inicializador;

import br.simulador.gerenciadores.GerenciadorInicializacao;

/**
 *
 * @author Douglas
 */
public class Inicializador {

    public static void main(String[] args) {
        GerenciadorInicializacao.getInstance().inicializarTela();
    }
}
