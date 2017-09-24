/**
 * Classe de testes para as funções da biblioteca
 */
package br.simulador.testes;

import java.awt.Color;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class ExperimentosTest {
    
    @Test
    public void testarFuncionalidades(){
        
    }
    
    /**
     * Chama os métodos que retornam quais as cores e os agentes em uma
     * determinada posição na tabela (Apenas para testes) ----- Remover
     */
//    public void get_cores() {
//        layout.showColors();
//        layout.objectsHere(points);
//        layout.objectsInRowColumn(1, 1, "agents");
//        layout.setColor(1, 1, Color.BLUE);
//        layout.getMaxBorderX(1, 0);
//        layout.getMaxBorderX(1, 1);
//        layout.getMaxBorderY(0, 0);
//        layout.getMaxBorderY(1, 1);
//        this.mostrarRetalhos();
//        repaint();
//    }
//
//    private void mostrarRetalhos() {
//        StringBuilder sbRetalho = new StringBuilder();
//        listaRetalhos.forEach((retalho) -> {
//            sbRetalho.append("Id: ").append(retalho.getId())
//                    .append("\n")
//                    .append("Linha: ")
//                    .append(retalho.getLinha())
//                    .append("\n")
//                    .append("Coluna: ")
//                    .append(retalho.getColuna())
//                    .append("\n");
//
//        });
//
//        System.out.println("-------------------------------");
//        System.out.println(sbRetalho.toString());
//        System.out.println("-------------------------------");
//    }
    
//    private void testarComponentes() throws InterruptedException {
//
//        int i = 0;
//        for (int j = 0; j < 10; j++) {
//
//            if (isExecutando) {
//                i++;
//                if (!interruptor.esta_ligado()) {
//                    interruptor.ativar();
//                } else {
//                    interruptor.desativar();
//                }
//                slider.definir_titulo("Slider " + i);
//                slider.definir_valor_padrao(i);
//                interruptor.definir_titulo("Interruptor " + i);
//                monitor.definir_titulo("Monitor " + i);
//                String sEstado = interruptor.esta_ligado() ? " Ligado" : " Desligado";
//                monitor.definir_valor(String.valueOf(i) + " - " + sEstado);
//
//                slider.revalidate();
//                interruptor.revalidate();
//                monitor.revalidate();
//                pnlSaidas.revalidate();
//                Thread.sleep(3000);
//            }
//        }
//    }
}
