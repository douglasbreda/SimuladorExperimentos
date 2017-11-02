/**
 * Classe para receber por parâmetro uma função e buscar na ASA do Portugol Studio qual o método a ser executado
 */
package br.simulador.gerenciadores;

import br.simulador.gerador.GeradorCodigoJavaSimulador;
import br.simulador.plugin.biblioteca.Experimentos;
import br.simulador.plugin.biblioteca.erro.ErroExecucaoSimulador;
import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.SimuladorPrograma;
import br.univali.portugol.nucleo.asa.ASAPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.asa.NoBloco;
import br.univali.portugol.nucleo.asa.NoChamadaFuncao;
import br.univali.portugol.nucleo.asa.NoDeclaracao;
import br.univali.portugol.nucleo.asa.NoDeclaracaoFuncao;
import br.univali.portugol.nucleo.asa.NoDeclaracaoVariavel;
import br.univali.portugol.nucleo.asa.NoInclusaoBiblioteca;
import br.univali.portugol.nucleo.asa.VisitanteNulo;
import br.univali.portugol.nucleo.mensagens.ErroExecucao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class GerenciadorFuncao extends VisitanteNulo {

    private final ASAPrograma asa;
    private String nomeMetodo;
    private final ASAPrograma asaGerada;
    private final ArrayList<NoDeclaracaoFuncao> listaMetodos;
    private final ArrayList<NoInclusaoBiblioteca> listaLibs;
    private final ArrayList<String> listaFuncoesUtilizadas;
    private final ArrayList<NoDeclaracaoVariavel> listaVariaveisDeclaradas;
    private List<NoDeclaracao> listaDeclaracoesGlobais;

    public GerenciadorFuncao(ASAPrograma asa) {
        this.asa = asa;
        asaGerada = new ASAPrograma();
        listaMetodos = new ArrayList<>();
        listaLibs = new ArrayList<>();
        listaFuncoesUtilizadas = new ArrayList<>();
        listaVariaveisDeclaradas = new ArrayList<>();
    }

    /**
     * Método que buscar a declaração de uma função pelo seu nome
     *
     * @param nome_metodo
     * @return
     * @throws ExcecaoVisitaASA
     * @throws br.univali.portugol.nucleo.ErroCompilacao
     * @throws br.univali.portugol.nucleo.mensagens.ErroExecucao
     * @throws java.lang.InterruptedException
     * @throws br.simulador.plugin.biblioteca.erro.ErroExecucaoSimulador
     */
    public SimuladorPrograma buscar_declaracao_metodo(String nome_metodo) throws ExcecaoVisitaASA, ErroCompilacao, ErroExecucao, InterruptedException, ErroExecucaoSimulador {
        this.nomeMetodo = nome_metodo;

        listaDeclaracoesGlobais = asa.getListaDeclaracoesGlobais();

        asa.aceitar(this);

        if (listaMetodos.stream().filter(x -> x.getNome().equalsIgnoreCase(nomeMetodo)).count() > 0) {
            asaGerada.setListaDeclaracoesGlobais(new ArrayList<>());

            asaGerada.setListaInclusoesBibliotecas(new ArrayList<>());

            asaGerada.getListaDeclaracoesGlobais().addAll(listaMetodos);

            asaGerada.getListaInclusoesBibliotecas().addAll(listaLibs);

            asaGerada.getListaDeclaracoesGlobais().addAll(listaVariaveisDeclaradas);

            GeradorCodigoJavaSimulador gerador = new GeradorCodigoJavaSimulador();

            SimuladorPrograma simulador_programa = gerador.gerar_codigo_java(asaGerada);

            if (simulador_programa != null) {
                simulador_programa.configurar();
            }

            GerenciadorExecucao.getInstance().setSimuladorPrograma(simulador_programa);
        } else {
            throw new ErroExecucaoSimulador();
        }
        
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoFuncao declaracaoFuncao) throws ExcecaoVisitaASA {

        if (!listaMetodos.contains(declaracaoFuncao)) {

            for (NoBloco bloco : declaracaoFuncao.getBlocos()) {
                if (declaracaoFuncao.getNome().equalsIgnoreCase(nomeMetodo)) {
                    if (bloco instanceof NoChamadaFuncao) {
                        listaFuncoesUtilizadas.add(((NoChamadaFuncao) bloco).getNome());
                    }
                }

                bloco.aceitar(this);
            }

            listaMetodos.add(declaracaoFuncao);
        }

        return null;
    }

    @Override
    public Object visitar(NoInclusaoBiblioteca noInclusaoBiblioteca) throws ExcecaoVisitaASA {

        if (!listaLibs.contains(noInclusaoBiblioteca)) {
            listaLibs.add(noInclusaoBiblioteca);
        }

        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoVariavel no) throws ExcecaoVisitaASA {

        if (listaDeclaracoesGlobais.contains(no)) {
            listaVariaveisDeclaradas.add(no);
        }
        return null;
    }
}
