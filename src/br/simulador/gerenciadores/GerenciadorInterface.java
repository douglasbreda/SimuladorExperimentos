/**
 * Classe para controle das telas e inicialiação do plugin
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.Retalho;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Douglas
 */
public final class GerenciadorInterface {

    private static GerenciadorInterface instance = null;

    private static GerenciadorDesenho desenho = null;

    //Define se o ambiente já está inicializado para evitar disparar diversas threads caso o usuário coloque num 
    //laço enquanto por exemplo
    private static boolean ambienteInicialiazdo = false;

    /**
     * Instância da classe estática que controla a inicialização da simulação
     *
     * @return
     */
    public static GerenciadorInterface getInstance() {

        if (instance == null) {
            instance = new GerenciadorInterface();
            desenho = new GerenciadorDesenho();
            ambienteInicialiazdo = false;
        }

        return instance;
    }

    /**
     * Chama a tela de inicialização do Simulador
     */
    public void inicializarTela() throws ErroExecucaoBiblioteca, InterruptedException, InvocationTargetException, InvocationTargetException, ErroExecucao {
        if (!ambienteInicialiazdo) {
            UtilSimulador.setLog("Vai inicializar tela");
            desenho.inicializar_tela();
            ambienteInicialiazdo = true;
            //Thread.sleep(1000);//Adicionado pois não estava carregando os componentes no tempo suficiente para utilização em outros métodos
        }
    }

    /**
     * Retorna se a janela ainda está visível e executando
     *
     * @return
     */
    public boolean esta_executando() {
        return desenho.esta_executando();
    }

    /**
     * Retorna a altura da janela de simulação
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int getAlturaSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.get_altura_janela();
    }

    /**
     * Retorna a largura da janela de simulação
     *
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public int getLarguraSimulacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.get_largura_janela();
    }

    /**
     * Método que vai desenhar os limites(bordas) da tela
     *
     * @param cor
     */
    public void definir_bordas(int cor) {
        desenho.desenhar_bordas(cor);
    }

    /**
     * Retorna o limite mínimo para X
     *
     * @return
     */
    public int retorna_limite_minimo_borda_X() {
        return desenho.retorna_valor_minimo_borda_X();
    }

    /**
     * Retorna o limite mínimo para Y
     *
     * @return
     */
    public int retorna_limite_minimo_borda_Y() {
        return desenho.retorna_valor_minimo_borda_Y();
    }

    /**
     * Retorna o limite máximo para X
     *
     * @return
     */
    public int retorna_limite_maximo_borda_X() {
        return desenho.retorna_valor_maximo_borda_X();
    }

    /**
     * Retorna o limite máximo para Y
     *
     * @return
     */
    public int retorna_limite_maximo_borda_Y() {
        return desenho.retorna_valor_maximo_borda_Y();
    }

    /**
     * Reseta as informações da simulação e configura a tela com a posição
     * inicial
     */
    public void limpar_tudo() {
        desenho.limpar_tudo();
    }

    /**
     * Método que "força" uma renderização da tela
     *
     * @throws
     * br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void atualizar_tela() throws ErroExecucaoBiblioteca, InterruptedException {
        desenho.atualizar_tela();
    }

    /**
     * Retorna o retalho de uma determinada posição X e Y
     *
     * @param coordenadaX
     * @param coordenadaY
     * @param altura
     * @param largura
     * @return
     * @throws br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public Retalho get_retalho(int coordenadaX, int coordenadaY, int altura, int largura) throws ErroExecucaoBiblioteca, InterruptedException {
        return desenho.get_retalho(coordenadaX, coordenadaY, altura, largura);
    }
    
    /**
     * Busca o número de agentes que estão em um determinado retalho
     * @param retalho
     * @return
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    public int buscar_numero_agentes(Retalho retalho) throws ErroExecucaoBiblioteca, InterruptedException{
        return desenho.buscar_agentes_no_retalho(retalho);
    }
    
    /**
     * Atualiza o label de número de agentes da simulação
     * @param total_agentes 
     * @throws br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca 
     * @throws java.lang.InterruptedException 
     */
//    public void atualizar_total_agentes(int total_agentes) throws ErroExecucaoBiblioteca, InterruptedException{
//        desenho.atualizar_total_agentes(total_agentes);
//    }
    
    /**
     * Atualiza o label que informa se a simulação está executando
     * @param executando 
     * @throws br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca 
     * @throws java.lang.InterruptedException 
     */
//    public void atualizar_status_simulacao(boolean executando) throws ErroExecucaoBiblioteca, InterruptedException{
//        desenho.atualizar_status_simulacao(executando);
//    }
    
    /**
     * Força a atualização dos componentes visuais
     * @throws br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca
     * @throws java.lang.InterruptedException
     */
    public void renderizar_tela() throws ErroExecucaoBiblioteca, InterruptedException, ErroExecucao{
        desenho.renderizar();
    }
    
    /**
     * Renderiza somente os agentes para evitar atualizações de tela desnecessárias
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    public void renderizar_agentes() throws ErroExecucaoBiblioteca, InterruptedException{
        desenho.renderizar_agentes();
    }
    
    /**
     * Define a cor de fundo de todo o ambiente de simulação
     * @param cor
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException 
     */
    public void definir_cor_fundo(int cor) throws ErroExecucaoBiblioteca, InterruptedException{
        desenho.definir_cor_fundo(cor);
    }
}
