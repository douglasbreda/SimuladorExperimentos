package br.simulador.plugin.biblioteca.base;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class Ambiente
{
    private List<Agente> listaAgentes = null;
    private Retalho retalho = null;
    private static int id = 0;

    /**
     * Controla qual é agente atual da simulação
     */
    private Agente agenteAtual = null;
    
    public Agente getAgenteAtual()
    {

        return listaAgentes.get(0);//Remover
//        return agenteAtual;
    }
    

    public Ambiente()
    {
        inicializarAmbiente();
    }

    private void inicializarAmbiente()
    {
        listaAgentes = new ArrayList<>();
//        retalho = new Retalho(1);
    }

    public int contar_agentes()
    {
        return 0;
    }

    public void atualizar_tela()
    {

    }

    public void executar(boolean sempre)
    {
        
    }

    public void limpar_tudo()
    {

    }
  
    public void criar_agentes(int numero_agentes, boolean aleatorio)
    {

    }

    public void criar_slider(String titulo, double minimo, double maximo, double valor_padrao)
    {

    }

    public void criar_monitor(String titulo, double valor)
    {

    }

    public void criar_interruptor(String titulo, boolean valor_padrao)
    {

    }

    public double media(String nome_parametro) throws ErroExecucaoBiblioteca, InterruptedException
    {
        return GerenciadorExecucao.getInstance().media(nome_parametro);
    }

    public int agentes_com_cor(int cor)
    {
        return 0;
    }

    public void definir_bordas(int cor)
    {

    }

    /**
     * Adiciona um parâmetro a todos os agentes
     * @param nome 
     */
    public void adicionar_atributo_agentes(String nome) throws ErroExecucaoBiblioteca, InterruptedException
    {
//        GerenciadorExecucao.getInstance().executarMetodo("criar_parametro", null);
    }
    
    /**
     * Aplica o processo de exclusão do agente do ambiente de simulação
     */
    public void matar_agente(){
        agenteAtual.morrer();
        listaAgentes.remove(agenteAtual);
        agenteAtual = null;
    }
}
