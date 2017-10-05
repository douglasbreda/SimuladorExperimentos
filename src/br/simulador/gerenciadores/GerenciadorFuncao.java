/**
 * Classe para receber por parâmetro uma função e buscar na ASA do Portugol Studio qual o método a ser executado
 */
package br.simulador.gerenciadores;

import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.asa.NoDeclaracaoFuncao;
import br.univali.portugol.nucleo.asa.VisitanteNulo;

/**
 *
 * @author Douglas
 */
public class GerenciadorFuncao extends VisitanteNulo{

    private final ASAPrograma asa;
    private StringBuilder funcao;
    private String nomeMetodo;
    
    public GerenciadorFuncao(ASAPrograma asa) {
        this.asa = asa;
    }
    
    /**
     * Método que buscar a declaração de uma função pelo seu nome
     * @param nome_metodo
     * @return
     * @throws ExcecaoVisitaASA 
     */
    public String buscar_declaracao_metodo(String nome_metodo) throws ExcecaoVisitaASA{
        this.nomeMetodo = nome_metodo;
        asa.aceitar(this);
        return funcao.toString();
    }

    @Override
    public Object visitar(NoDeclaracaoFuncao declaracaoFuncao) throws ExcecaoVisitaASA {
        
        if(declaracaoFuncao.getNome().equalsIgnoreCase(nomeMetodo))
            funcao.append(declaracaoFuncao.getNome());
        
        return null;
    }
    
    
}
