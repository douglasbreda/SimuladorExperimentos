package br.simulador.plugin.biblioteca.base;

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

    //Controla qual é agente atual da simulação
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
        retalho = new Retalho();
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

    public void definir_tamanho_tela(int x, int y)
    {

    }

    public void criar_agentes(int numero_agentes, boolean aleatorio)
    {
        RetalhoCoordenadas coordenadas = retalho.definirCoordenadasIniciais();

        for (int i = 0; i < numero_agentes; i++)
        {
            if (aleatorio)
            {
                coordenadas = retalho.definirCoordenadasIniciais();
            }

            double coordenadaX = coordenadas.getCoordenadaX();
            double coordenadaY = coordenadas.getCoordenadaY();
            
            IAgente agente = new Agente(coordenadaX, coordenadaY, ++id);
            listaAgentes.add((Agente) agente);
        }
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

    public double media(String nome_parametro) throws ErroExecucaoBiblioteca
    {
        double media = 0;
        
        if (listaAgentes.size() > 0)
        {
            for (Agente agente : listaAgentes)
            {
                media += agente.retornar_atributo_real(nome_parametro);
            }
            
            media = media / listaAgentes.size();
        }
        
        return media;
    }

    public int agentes_com_cor(int cor)
    {
        return 0;
    }

    public void definir_bordas(int cor)
    {

    }

    //Adiciona um parâmetro a todos os agentes
    public void adicionarAtributoAgentes(String nome)
    {
        for (Agente agente : listaAgentes)
        {
            agente.criar_parametro(nome);
        }
    }
    
    //Aplica o processo de exclusão do agente do ambiente de simulação
    public void matarAgente(){
        agenteAtual.morrer();
        listaAgentes.remove(agenteAtual);
        agenteAtual = null;
    }
}
