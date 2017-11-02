package br.simulador.plugin.biblioteca.base;

import br.simulador.gerenciadores.GerenciadorInterface;
import br.simulador.plugin.biblioteca.componentes.TipoForma;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Douglas
 */
public abstract class BaseAgente implements IAgente {

    private final Map<String, Object> listaParametros = new ConcurrentHashMap<>();
    private int coordenadaX = 0;
    private int coordenadaY = 0;
    private int id = 0;
    private int cor;
    private int orientacao = 0;
    private boolean estaVisivel = true;
    private int alturaAgente = 10;
    private int larguraAgente = 10;
    private int forma;
    private boolean moverFrente = true;

    /**
     * Construtor padrão que recebe as coordenadas e um identificador para cada
     * agente criado
     *
     * @param coordenadaX
     * @param coordenadaY
     * @param id
     * @param forma
     */
    public BaseAgente(int coordenadaX, int coordenadaY, int id, int forma) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.id = id;
        this.cor = UtilSimulador.corRandomica();
        this.orientacao = UtilSimulador.getNumeroRandomico(360);
        this.forma = forma;
    }

    /**
     * Adiciona um parâmetro a lista de parâmetros do agente
     *
     * @param nome
     */
    private void adicionarParametroLista(String nome, String valor_padrao) {
        if (!listaParametros.containsKey(nome)) {
            listaParametros.put(nome, valor_padrao);
        }
    }

    @Override
    public void criarAtributo(String nome_atributo, String valor_padrao) {
        adicionarParametroLista(nome_atributo, valor_padrao);
    }

    @Override
    public void definirCorAgente(int cor) {
        this.cor = cor;
    }

    @Override
    public void definirOrientacao(int graus) {
        this.orientacao = graus;
    }

    @Override
    public void definirValorAtributo(String nome_atributo, String valor) {
        if (verificar_atributo_existe(nome_atributo)) {
            listaParametros.replace(nome_atributo, valor);
        }
    }

    /**
     * Rotaciona o agente para a direita Caso o valor seja maior que zero, o
     * valor é subtraído de 360 para ver a diferença e transformá-la na nova
     * orientação
     *
     * @param graus
     */
    @Override
    public void girarDireita(int graus) {
        int valor = orientacao + graus;

        if (valor > 360) {
            orientacao = (valor - 360);
        } else {
            orientacao = valor;
        }
    }

    /**
     * Rotaciona o agente para a esquerda. Caso o valor seja menor que zero, o
     * resto do valor é descontado de 360 para descobrir a nova orientação
     *
     * @param graus
     */
    @Override
    public void girarEsquerda(int graus) {
        int valor = orientacao - graus;

        if (valor < 0) {
            valor *= -1;
            orientacao = 360 - valor;
        } else {
            orientacao = valor;
        }
    }

    @Override
    public void morrer() {
        throw new UnsupportedOperationException("Método implementado diretamente na classe GerenciadorExecucao, "
                + "pois entende-se como a morte de um agente apenas a sua remoção da lista de agentes."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean irAte(int nova_coordenadaX, int nova_coordenadaY) {
//        UtilSimulador.setLog("Agente " + this.id + " foi da posição X: " + this.coordenadaX + " até " + nova_coordenadaX
//                + "e de Y: " + this.coordenadaY + " até " + nova_coordenadaY);

        this.coordenadaX = nova_coordenadaX;
        this.coordenadaY = nova_coordenadaY;

        return true;//VERIFICAR ESTE RETORNO
    }

    @Override
    public void moverFrente(int quantidade) {
        if (moverFrente) {
            //Verifica os totais para comparar caso sejam menor que zero
            int quantidadeX = (int)(quantidade * Math.cos(orientacao));
            int quantidadeY = (int)(quantidade * Math.sin(orientacao));
            
            int prox_coordenadaX = (int) (coordenadaX + (quantidadeX > 0 ? quantidadeX : 1) );

            if (prox_coordenadaX > GerenciadorInterface.getInstance().getLarguraMaximaPainelSimulacao()) {
                coordenadaX = GerenciadorInterface.getInstance().retornarValorMinBordaX() + 1;
                return;
            }
//
            int prox_coordenadaY = (int) (coordenadaY + (quantidadeY > 0 ? quantidadeY : 1));

            if (prox_coordenadaY > GerenciadorInterface.getInstance().getAlturaMaximaPainelSimulacao()) {
                coordenadaY = GerenciadorInterface.getInstance().retornarValorMinBordaY() + 1;
                return;
            }

            this.coordenadaX += quantidadeX > 0 ? quantidadeX : 1;
            this.coordenadaY += quantidadeY > 0 ? quantidadeY : 1;

        } else {
            voltar(quantidade);
        }
    }

    @Override
    public String retornarAtributoCadeia(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificar_atributo_existe(nome_atributo)) {
            String retorno = ((String) listaParametros.get(nome_atributo));
            UtilSimulador.setLog("Valor Cadeia retornado: " + retorno);
            return retorno;
        } else {
            return "";
        }
    }

    @Override
    public char retornarAtributoCaracter(String nome_atributo) throws ErroExecucaoBiblioteca {

        if (verificar_atributo_existe(nome_atributo)) {
            return ((char) listaParametros.get(nome_atributo));
        } else {
            return ' ';
        }
    }

    @Override
    public int retornarAtributoInteiro(String nome_atributo) {
        if (verificar_atributo_existe(nome_atributo)) {
            return (UtilSimulador.toInt(listaParametros.get(nome_atributo).toString()));
        } else {
            return 0;
        }
    }

    @Override
    public boolean retornarAtributoLogico(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificar_atributo_existe(nome_atributo)) {
            return (UtilSimulador.toBoolean(listaParametros.get(nome_atributo).toString()));
        } else {
            return false;
        }
    }

    @Override
    public double retornarAtributoReal(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificar_atributo_existe(nome_atributo)) {
            return (UtilSimulador.toDouble(listaParametros.get(nome_atributo).toString()));
        } else {
            return 0;
        }
    }

    @Override
    public int retornarCoordenadaX() {
        return this.coordenadaX;
    }

    @Override
    public int retornarCoordenadaY() {
        return this.coordenadaY;
    }

    @Override
    public int retornarCorAgente() {
        return this.cor;
    }

    @Override
    public int retornarId() {
        return this.id;
    }

    @Override
    public int retornarOrientacao() {
        return this.orientacao;
    }

    @Override
    public void voltar(int quantidade) {
        int prox_coordenadaX = (int) (coordenadaX - (quantidade * Math.cos(orientacao)));
        if (prox_coordenadaX < GerenciadorInterface.getInstance().getLarguraMinimaPainelSimulacao()) {
            coordenadaX = GerenciadorInterface.getInstance().retornarValorMaxBordaX() - 1;
            return;
        }

        int prox_coordenadaY = (int) (coordenadaY - (quantidade * Math.sin(orientacao)));
        if (prox_coordenadaY < GerenciadorInterface.getInstance().getAlturaMinimaPainelSimulacao()) {
            coordenadaY = GerenciadorInterface.getInstance().retornarValorMaxBordaY() - 1;            
            return;
        }

        int quantidadeX = (int)(quantidade * Math.cos(orientacao));
        int quantidadeY = (int)(quantidade * Math.sin(orientacao));
        
        this.coordenadaX -= quantidadeX > 0 ? quantidadeX : 1;
        this.coordenadaY -= quantidadeY > 0 ? quantidadeY : 1;
    }

    @Override
    public boolean colidiuComParede() throws ErroExecucaoBiblioteca, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean colidiuBordaX() throws ErroExecucaoBiblioteca, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean colidiuBordaY() throws ErroExecucaoBiblioteca, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void definirVisibilidade(boolean visivel) {
        this.estaVisivel = visivel;
    }

    @Override
    public boolean estaVisivel() {
        return estaVisivel;
    }

    @Override
    public void definirAlturaAgente(int altura) {
        this.alturaAgente = altura;
    }

    @Override
    public void definirLarguraAgente(int largura) {
        this.larguraAgente = largura;
    }

    @Override
    public int retornarAlturaAgente() {
        return this.alturaAgente;
    }

    @Override
    public int retornarLarguraAgente() {
        return this.larguraAgente;
    }

    private boolean verificar_atributo_existe(String nome) {
        return listaParametros.containsKey(nome);
    }

    public void definir_log(String mensagem) {
        UtilSimulador.setLog(mensagem);
    }

    @Override
    public void definirFormaAgente(int forma) {
        this.forma = forma;
    }

    @Override
    public TipoForma retornarFormaAgente() {
        switch (forma) {
            case 0:
                return TipoForma.circulo;
            case 1:
                return TipoForma.linha;
        }

        return TipoForma.circulo;
    }

    @Override
    public void inverterSentido() {
        this.moverFrente = !moverFrente;
    }

}
