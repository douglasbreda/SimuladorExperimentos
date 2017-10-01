/**
 * Classe para testes dos métodos dos agentes
 */
package br.simulador.testes;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.gerenciadores.GerenciadorInterface;
import br.simulador.plugin.biblioteca.base.IAgente;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;

/**
 *
 * @author Douglas
 */
public class ExperimentosAgenteTest {
    
    public static void main(String[] args) throws InterruptedException, ErroExecucaoBiblioteca {
        new ExperimentosAgenteTest().testarFuncionalidades();
    }
    
    public void testarFuncionalidades() throws InterruptedException, ErroExecucaoBiblioteca{
        GerenciadorInterface.getInstance().inicializarTela();
        GerenciadorInterface.getInstance().definir_bordas(3);
        
        Thread.sleep(1000);//Para esperar a tela ser criada, pois ocorreu null pointer ao buscar componentes que ainda não foram criados
        
        GerenciadorExecucao.getInstance().criar_agentes(1, true);
        
        GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_X");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_Y");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_id");
        
        GerenciadorExecucao.getInstance().executarMetodo("criar_atributo", 1, "velocidade");
        GerenciadorExecucao.getInstance().executarMetodo("retornar_atributo_cadeia", 1, "velocidade");

        System.out.println("Máximo X: " + GerenciadorInterface.getInstance().retorna_limite_maximo_borda_X());
        System.out.println("Máximo Y: " + GerenciadorInterface.getInstance().retorna_limite_maximo_borda_Y());
        System.out.println("Mínimo X: " + GerenciadorInterface.getInstance().retorna_limite_minimo_borda_X());
        System.out.println("Mínimo Y: " + GerenciadorInterface.getInstance().retorna_limite_minimo_borda_Y());
        
        while(GerenciadorInterface.getInstance().esta_executando()){
            int limiteX = GerenciadorInterface.getInstance().retorna_limite_maximo_borda_X();
            int limiteY = GerenciadorInterface.getInstance().retorna_limite_maximo_borda_Y();
            for (IAgente agente : GerenciadorExecucao.getInstance().getListaAgentes()) {
                agente.ir_ate(UtilSimulador.getNumeroRandomico(limiteX), UtilSimulador.getNumeroRandomico(limiteY));
    
//                  posicaoX = agente.retornar_coordenada_X() + 1;
//                  posicaoY = agente.retornar_coordenada_Y() + 1;
//                  agente.ir_ate(posicaoX, posicaoY);
//                  System.out.println(posicaoX);
//                  System.out.println(posicaoY);
                  
            }
        }
        
        System.exit(0);
    }
}
