package br.simulador.plugin.biblioteca.base;

import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Douglas
 */
public abstract class BaseAgente implements IAgente {

    
    private final Map<String, Object> lista_parametros = new ConcurrentHashMap<>();
    private int coordenadaX = 0;
    private int coordenadaY = 0;
    private int id = 0;
    private int cor;
    private int orientacao = 0;
    private boolean esta_visivel = true;
    private int altura_agente = 10;
    private int largura_agente = 10;

    /**
     * Construtor padrão que recebe as coordenadas e um identificador para cada agente criado
     * @param coordenadaX
     * @param coordenadaY
     * @param id 
     */
    public BaseAgente(int coordenadaX, int coordenadaY, int id) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.id = id;
        this.cor = UtilSimulador.corRandomica();
        this.orientacao = UtilSimulador.getNumeroRandomico(360);
        definir_log("Agente " + id + " inicializado com sucesso.");
    }

    /**
     * Adiciona um parâmetro a lista de parâmetros do agente
     * @param nome 
     */
    private void adicionar_parametro_lista(String nome) {
        if (!lista_parametros.containsKey(nome)) {
            lista_parametros.put(nome, "");
            definir_log("Parâmetro " + nome + " adicionado ao agente " + id);
        }
    }

    @Override
    public void criar_atributo(String nome_atributo) {
        adicionar_parametro_lista(nome_atributo);
    }

    @Override
    public void definir_cor_agente(int cor) {
        this.cor = cor;
    }

    @Override
    public void definir_orientacao(int graus) {
        this.orientacao = graus;
    }

    @Override
    public void definir_valor_atributo(String nome_atributo, String valor, int id_agente) {
        if (verificar_atributo_existe(nome_atributo)) {
            definir_log("Parâmetro " + nome_atributo + " valor atual: " + lista_parametros.get(nome_atributo));
            lista_parametros.replace(nome_atributo, valor);
            definir_log("Parâmetro " + nome_atributo + " valor atualizado: " + lista_parametros.get(nome_atributo));
        }
    }

    @Override
    public void girar_direita(int graus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void girar_esquerda(int graus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void morrer() {
        throw new UnsupportedOperationException("Método implementado diretamente na classe GerenciadorExecucao, "
                + "pois entende-se como a morte de um agente apenas a sua remoção da lista de agentes."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ir_ate(int nova_coordenadaX, int nova_coordenadaY) {
        UtilSimulador.setLog("Agente " + this.id + " foi da posição X: " + this.coordenadaX + " até " + nova_coordenadaX + 
                "e de Y: " + this.coordenadaY + " até " + nova_coordenadaY);        
        
        this.coordenadaX = nova_coordenadaX;
        this.coordenadaY = nova_coordenadaY;

        return true;//VERIFICAR ESTE RETORNO
    }

    @Override
    public void mover_frente(int quantidade) {
        this.coordenadaX += quantidade;
    }

    @Override
    public String retornar_atributo_cadeia(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificar_atributo_existe(nome_atributo)) {
            String retorno = ((String) lista_parametros.get(nome_atributo));
            UtilSimulador.setLog("Valor Cadeia retornado: " + retorno);
            return retorno;
        } else {
            return "";
        }
    }

    @Override
    public char retornar_atributo_caracter(String nome_atributo) throws ErroExecucaoBiblioteca {

        if (verificar_atributo_existe(nome_atributo)) {
            return ((char) lista_parametros.get(nome_atributo));
        } else {
            return ' ';
        }
    }

    @Override
    public int retornar_atributo_inteiro(String nome_atributo) {
        if (verificar_atributo_existe(nome_atributo)) {
            return (UtilSimulador.toInt(lista_parametros.get(nome_atributo).toString()));
        } else {
            return 0;
        }
    }

    @Override
    public boolean retornar_atributo_logico(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificar_atributo_existe(nome_atributo)) {
            return (UtilSimulador.toBoolean(lista_parametros.get(nome_atributo).toString()));
        } else {
            return false;
        }
    }

    @Override
    public double retornar_atributo_real(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificar_atributo_existe(nome_atributo)) {
            return (UtilSimulador.toDouble(lista_parametros.get(nome_atributo).toString()));
        } else {
            return 0;
        }
    }

    @Override
    public int retornar_coordenada_X() {
        return this.coordenadaX;
    }

    @Override
    public int retornar_coordenada_Y() {
        return this.coordenadaY;
    }

    @Override
    public int retornar_cor_agente() {
        return this.cor;
    }

    @Override
    public int retornar_id() {
        return this.id;
    }

    @Override
    public int retornar_orientacao() {
        return this.orientacao;
    }

    @Override
    public void voltar(int quantidade) {
        this.coordenadaX -=  quantidade;
    }

    @Override
    public boolean colidiu_com_parede() throws ErroExecucaoBiblioteca, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean colidiu_borda_X() throws ErroExecucaoBiblioteca, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean colidiu_borda_Y() throws ErroExecucaoBiblioteca, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void definir_visibilidade(boolean visivel) {
        this.esta_visivel = visivel;
    }

    @Override
    public boolean esta_visivel() {
        return esta_visivel;
    }

    @Override
    public void definir_altura_agente(int altura) {
        this.altura_agente = altura;
    }

    @Override
    public void definir_largura_agente(int largura) {
        this.largura_agente = largura;
    }

    @Override
    public int retornar_altura_agente() {
        return this.altura_agente;
    }

    @Override
    public int retornar_largura_agente() {
        return this.largura_agente;
    }
    
    private boolean verificar_atributo_existe(String nome) {
        return lista_parametros.containsKey(nome);
    }

    public void definir_log(String mensagem) {
        UtilSimulador.setLog(mensagem);
    }
}
