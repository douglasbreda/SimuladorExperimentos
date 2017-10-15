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

    
    private final Map<String, Object> listaParametros = new ConcurrentHashMap<>();
    private int coordenadaX = 0;
    private int coordenadaY = 0;
    private int id = 0;
    private int cor;
    private int orientacao = 0;
    private boolean esta_visivel = true;
    private int altura_agente = 10;
    private int largura_agente = 10;

    //Construtor padrão
    public BaseAgente(int coordenadaX, int coordenadaY, int id) {
        inicializar();
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.id = id;
        this.cor = UtilSimulador.corRandomica();
        definirLog("Agente " + id + " inicializado com sucesso.");
    }

    private void inicializar() {
//        listaParametros = new HashMap<>();
    }

    private void adicionarParametroLista(String nome) {
        if (!listaParametros.containsKey(nome)) {
            listaParametros.put(nome, "");
            definirLog("Parâmetro " + nome + " adicionado ao agente " + id);
        }
    }

    @Override
    public void criar_atributo(String nome_atributo) {
        adicionarParametroLista(nome_atributo);
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
        if (verificarAtributoExiste(nome_atributo)) {
            definirLog("Parâmetro " + nome_atributo + " valor atual: " + listaParametros.get(nome_atributo));
            listaParametros.replace(nome_atributo, valor);
            definirLog("Parâmetro " + nome_atributo + " valor atualizado: " + listaParametros.get(nome_atributo));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String retornar_atributo_cadeia(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificarAtributoExiste(nome_atributo)) {
            String retorno = ((String) listaParametros.get(nome_atributo));
            UtilSimulador.setLog("Valor Cadeia retornado: " + retorno);
            return retorno;
        } else {
            return "";
        }
    }

    @Override
    public char retornar_atributo_caracter(String nome_atributo) throws ErroExecucaoBiblioteca {

        if (verificarAtributoExiste(nome_atributo)) {
            return ((char) listaParametros.get(nome_atributo));
        } else {
            return ' ';
        }
    }

    @Override
    public int retornar_atributo_inteiro(String nome_atributo) {
        if (verificarAtributoExiste(nome_atributo)) {
            return (UtilSimulador.toInt(listaParametros.get(nome_atributo).toString()));
        } else {
            return 0;
        }
    }

    @Override
    public boolean retornar_atributo_logico(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificarAtributoExiste(nome_atributo)) {
            return (UtilSimulador.toBoolean(listaParametros.get(nome_atributo).toString()));
        } else {
            return false;
        }

    }

    @Override
    public double retornar_atributo_real(String nome_atributo) throws ErroExecucaoBiblioteca {
        if (verificarAtributoExiste(nome_atributo)) {
            return (UtilSimulador.toDouble(listaParametros.get(nome_atributo).toString()));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
//    @Override
//    protected void paintComponent(Graphics g)
//    {
//        //Cria aqui os desenhos (as formas)
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//    }
    private boolean verificarAtributoExiste(String nome) {
        return listaParametros.containsKey(nome);
    }

    public void definirLog(String mensagem) {
//        LOGGER.log(Level.INFO, mensagem);
        UtilSimulador.setLog(mensagem);
    }
}
