package br.simulador.plugin.biblioteca.base;

import br.simulador.plugin.biblioteca.componentes.TipoForma;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;

/**
 *
 * @author Douglas
 */
public interface IAgente
{
    public int retornar_coordenada_X() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public int retornar_coordenada_Y() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void mover_frente(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void voltar(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void girar_esquerda(int graus) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void girar_direita(int graus) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void definir_orientacao(int graus) throws ErroExecucaoBiblioteca, InterruptedException;;

    public void morrer() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void criar_atributo(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void definir_valor_atributo(String nome_atributo, String valor, int id_agente) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void definir_cor_agente(int cor) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public String retornar_atributo_cadeia(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public char retornar_atributo_caracter(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornar_atributo_inteiro(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public boolean retornar_atributo_logico(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public double retornar_atributo_real(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornar_cor_agente() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornar_orientacao() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public boolean ir_ate(int coordenadaX, int coordenadaY) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornar_id() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public boolean colidiu_com_parede() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public boolean colidiu_borda_X() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public boolean colidiu_borda_Y() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public void definir_visibilidade(boolean visivel);
    
    public boolean esta_visivel();
    
    public void definir_altura_agente(int altura);
    
    public int retornar_altura_agente();
    
    public void definir_largura_agente(int largura);
    
    public int retornar_largura_agente();
    
    public void definir_forma_agente(int forma);
    
    public TipoForma retornar_forma_agente();
        
    public void inverter_sentido();
}
