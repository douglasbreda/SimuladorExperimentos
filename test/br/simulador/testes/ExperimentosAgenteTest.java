/**
 * Classe para testes dos métodos dos agentes
 */
package br.simulador.testes;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.gerenciadores.GerenciadorInterface;
import br.simulador.plugin.biblioteca.base.IAgente;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Douglas
 */
public class ExperimentosAgenteTest {

    public static void main(String[] args) throws InterruptedException, ErroExecucaoBiblioteca, InvocationTargetException, ErroExecucao {
        new ExperimentosAgenteTest().testarFuncionalidades();
    }

    public void testarFuncionalidades() throws InterruptedException, ErroExecucaoBiblioteca, InvocationTargetException, ErroExecucao {
        GerenciadorInterface.getInstance().inicializarTela();

        Thread.sleep(1000);//Para esperar a tela ser criada, pois ocorreu null pointer ao buscar componentes que ainda não foram criados
        
        GerenciadorInterface.getInstance().definirCorFundo(3);

        GerenciadorInterface.getInstance().definirBordas(3);
        
        GerenciadorExecucao.getInstance().criarAgentes(5, true);

        GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_X");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_Y");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_id");

        GerenciadorExecucao.getInstance().executarMetodo("criar_atributo", 1, "velocidade");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_atributo_cadeia", 1, "velocidade");

        System.out.println("Máximo X: " + GerenciadorInterface.getInstance().retornarValorMaxBordaX());
        System.out.println("Máximo Y: " + GerenciadorInterface.getInstance().retornarValorMaxBordaY());
        System.out.println("Mínimo X: " + GerenciadorInterface.getInstance().retornarValorMinBordaX());
        System.out.println("Mínimo Y: " + GerenciadorInterface.getInstance().retornarValorMinBordaY());

        int iContador = 0;
        for (IAgente agente : GerenciadorExecucao.getInstance().getListaAgentes()) {
            if (iContador < 2) {
                agente.definirCorAgente(0xE59500);
            } else if (iContador > 2 && iContador < 5) {
                agente.definirCorAgente(0x00D3DD);
            } else {
                agente.definirCorAgente(0x820080);
            }
            iContador++;
        }

        System.out.println("Agentes com a cor 0xE59500: " + GerenciadorExecucao.getInstance().agentesComCor(0xE59500));
        System.out.println("Agentes com a cor 0x00D3DD: " + GerenciadorExecucao.getInstance().agentesComCor(0x00D3DD));
        System.out.println("Agentes com a cor 0x820080: " + GerenciadorExecucao.getInstance().agentesComCor(0x820080));

        int posicaoX = 0;
        int posicaoY = 0;
        
        
//        while (GerenciadorInterface.getInstance().estaExecutando()) {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            for (IAgente agente : GerenciadorExecucao.getInstance().getListaAgentes()) {
                posicaoX = agente.retornarCoordenadaX() + 5;
                posicaoY = agente.retornarCoordenadaY() + 5;
                agente.irAte(posicaoX, posicaoY);
                System.out.println("Posição X:" + posicaoX);
                System.out.println("Posição Y:" + posicaoY);
                GerenciadorInterface.getInstance().renderizarTela();
//                GerenciadorInterface.getInstance().atualizarTela();
            }
        }
        
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            for (IAgente agente : GerenciadorExecucao.getInstance().getListaAgentes()) {
                posicaoX = agente.retornarCoordenadaX() - 7;
                posicaoY = agente.retornarCoordenadaY() - 3;
                agente.irAte(posicaoX, posicaoY);
                System.out.println("Posição X:" + posicaoX);
                System.out.println("Posição Y:" + posicaoY);
                GerenciadorInterface.getInstance().renderizarTela();
//                GerenciadorInterface.getInstance().atualizarTela();
            }
        }
        //}

        System.exit(0);
    }
}
