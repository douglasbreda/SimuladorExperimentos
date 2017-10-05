/**
 * Classe para receber por parâmetro uma função e buscar na ASA do Portugol Studio qual o método a ser executado
 */
package br.simulador.gerenciadores;

import br.simulador.gerador.GeradorCodigoJavaSimulador;
import br.univali.portugol.nucleo.asa.ASA;
import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.asa.NoDeclaracaoFuncao;
import br.univali.portugol.nucleo.asa.VisitanteASA;
import br.univali.portugol.nucleo.asa.VisitanteNulo;
import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public class GerenciadorFuncao extends VisitanteNulo {

    private final ASAPrograma asa;
    private StringBuilder funcao;
    private String nomeMetodo;
    private ASAPrograma asaGerada;
    private ArrayList<NoDeclaracaoFuncao> listaMetodos;

    public GerenciadorFuncao(ASAPrograma asa) {
        this.asa = asa;
        asaGerada = new ASAPrograma();
        listaMetodos = new ArrayList<>();
    }

    /**
     * Método que buscar a declaração de uma função pelo seu nome
     *
     * @param nome_metodo
     * @return
     * @throws ExcecaoVisitaASA
     */
    public ASAPrograma buscar_declaracao_metodo(String nome_metodo) throws ExcecaoVisitaASA {
        this.nomeMetodo = nome_metodo;
        asa.aceitar(this);

        if (asaGerada.getListaDeclaracoesGlobais() == null) {
            asaGerada.setListaDeclaracoesGlobais(new ArrayList<>());
        }

        asaGerada.getListaDeclaracoesGlobais().addAll(listaMetodos);

        GeradorCodigoJavaSimulador gerador = new GeradorCodigoJavaSimulador();
        gerador.gerar_codigo_java(asaGerada);

        return asaGerada;
    }

    @Override
    public Object visitar(NoDeclaracaoFuncao declaracaoFuncao) throws ExcecaoVisitaASA {

        if (declaracaoFuncao.getNome().equalsIgnoreCase(nomeMetodo)) {
            if (!listaMetodos.contains(declaracaoFuncao)) {
                listaMetodos.add(declaracaoFuncao);
            }
        }

        return null;
    }

}
