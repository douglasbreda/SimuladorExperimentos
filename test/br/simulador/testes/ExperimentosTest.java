/**
 * Classe de testes para as funções da biblioteca
 */
package br.simulador.testes;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.gerenciadores.GerenciadorInicializacao;
import br.simulador.plugin.biblioteca.base.IAgente;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;


/**
 *
 * @author Douglas
 */
public class ExperimentosTest {


    public static void main(String[] args) throws Exception {
        ExperimentosTest experimentoTest = new ExperimentosTest();
        experimentoTest.testarCriarAgentes();
        experimentoTest.testarContarAgentes();
        experimentoTest.testarMedia();
    }
    
    
    /**
     * Testa a função de criação de agentes
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    public void testarCriarAgentes() throws ErroExecucaoBiblioteca, InterruptedException{
        GerenciadorInicializacao.getInstance().inicializarTela();
        
        Thread.sleep(1000);//Para esperar a tela ser criada, pois ocorreu null pointer ao buscar componentes que ainda não foram criados
        
        GerenciadorExecucao.getInstance().criar_agentes(10, true);
    }
    
    /**
     * Testa a função de contar agentes
     */
    public void testarContarAgentes(){
        System.out.println("A simulação contém " + GerenciadorExecucao.getInstance().contar_agentes() + " agentes.");
    }
    
    public void testarMedia() throws ErroExecucaoBiblioteca, InterruptedException{
        
        //Verificar se isso é necessário mesmo
        
        GerenciadorExecucao.getInstance().adicionar_atributo_agentes("velocidade");
        
        GerenciadorExecucao.getInstance().definir_valor_atributo("velocidade", "10", 1);
        
        GerenciadorExecucao.getInstance().definir_valor_atributo("velocidade", "20", 1);
        
        GerenciadorExecucao.getInstance().definir_valor_atributo("velocidade", "15", 3);
        
        GerenciadorExecucao.getInstance().definir_valor_atributo("velocidade", "17", 5);
        
        //Adicionar chamadas para os métodos de retornar um valor por agente
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
    
//    private void adicionarComponentes() {
//
//        this.slider = new Slider();
//        this.interruptor = new Interruptor();
//        this.monitor = new Monitor();
//
//        pnlComponentes.add(slider);
//        pnlComponentes.add(interruptor);
//        pnlComponentes.add(monitor);
//    }
}
