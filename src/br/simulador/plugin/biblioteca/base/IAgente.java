package br.simulador.plugin.biblioteca.base;

import br.simulador.plugin.biblioteca.componentes.TipoForma;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;

/**
 *
 * @author Douglas
 */
public interface IAgente
{
    public int retornarCoordenadaX() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public int retornarCoordenadaY() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void moverFrente(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void voltar(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void girarEsquerda(int graus) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void girarDireita(int graus) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void definirOrientacao(int graus) throws ErroExecucaoBiblioteca, InterruptedException;;

    public void morrer() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void criarAtributo(String nome_atributo, String valor_padrao) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void atualizarValorAtributo(String nome_atributo, String valor) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public void definirCorAgente(int cor) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public String retornarAtributoCadeia(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public char retornarAtributoCaracter(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornarAtributoInteiro(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public boolean retornarAtributoLogico(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public double retornarAtributoReal(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornarCorAgente() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornarOrientacao() throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public boolean irAte(int coordenadaX, int coordenadaY) throws ErroExecucaoBiblioteca, InterruptedException;;
    
    public int retornarId() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public boolean colidiuComParede() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public boolean colidiuBordaX() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public boolean colidiuBordaY() throws ErroExecucaoBiblioteca, InterruptedException;
    
    public void definirVisibilidade(boolean visivel);
    
    public boolean estaVisivel();
    
    public void definirAlturaAgente(int altura);
    
    public int retornarAlturaAgente();
    
    public void definirLarguraAgente(int largura);
    
    public int retornarLarguraAgente();
    
    public void definirFormaAgente(int forma);
    
    public TipoForma retornarFormaAgente();
        
    public void inverterSentido();
}
