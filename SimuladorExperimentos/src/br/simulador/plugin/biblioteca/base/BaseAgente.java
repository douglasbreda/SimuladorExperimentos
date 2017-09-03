package br.simulador.plugin.biblioteca.base;

import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class BaseAgente implements IAgente
{
    //Lista com os parâmetros do agente
    private final Map<String, Object> listaParametros = new ConcurrentHashMap<>();
    private double coordenadaX = 0;
    private double coordenadaY = 0;
    private int id = 0;
    private static final Logger LOGGER = Logger.getLogger(BaseAgente.class.getName());

    //Construtor padrão
    public BaseAgente(double coordenadaX, double coordenadaY, int id)
    {
        inicializar();
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.id = id;

        definirLog("Agente " + id + " inicializado com sucesso.");
    }

    private void inicializar()
    {
//        listaParametros = new HashMap<>();
    }

    private void adicionarParametroLista(String nome)
    {
        if (!listaParametros.containsKey(nome))
        {
            listaParametros.put(nome, "");
            definirLog("Parâmetro " + nome + " adicionado ao agente " + id);
        }
    }

    @Override
    public void criar_parametro(String nome_atributo)
    {
        adicionarParametroLista(nome_atributo);
    }

    @Override
    public void definir_cor_agente(int cor)
    {

    }

    @Override
    public void definir_orientacao(int graus)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void definir_valor_atributo(String nome_atributo, String valor)
    {
        if (verificarAtributoExiste(nome_atributo))
        {
            definirLog("Parâmetro " + nome_atributo + " valor atual: " + listaParametros.get(nome_atributo));
            listaParametros.replace(nome_atributo, valor);
            definirLog("Parâmetro " + nome_atributo + " valor atualizado: " + listaParametros.get(nome_atributo));
        }
    }

    @Override
    public void girar_direita(int graus)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void girar_esquerda(int graus)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void morrer()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ir_ate(double coordenadaX, double coordenadaY)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mover_frente(int quantidade)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pular_para_XY(double coordenadaX, double coordenadaY)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String retornar_atributo_cadeia(String nome_atributo) throws ErroExecucaoBiblioteca
    {
        if (verificarAtributoExiste(nome_atributo))
        {
            return ((String) listaParametros.get(nome_atributo));
        }
        else
        {
            throw new ErroExecucaoBiblioteca("O parâmetro " + nome_atributo + " não existe.");
        }
    }

    @Override
    public char retornar_atributo_caracter(String nome_atributo) throws ErroExecucaoBiblioteca
    {

        if (verificarAtributoExiste(nome_atributo))
        {
            return ((char) listaParametros.get(nome_atributo));
        }
        else
        {
            throw new ErroExecucaoBiblioteca("O parâmetro " + nome_atributo + " não existe.");
        }
    }

    @Override
    public int retornar_atributo_inteiro(String nome_atributo)
    {
        if (verificarAtributoExiste(nome_atributo))
        {
            return (Integer.parseInt(listaParametros.get(nome_atributo).toString()));
        }
        else
        {
            return 0;
        }
    }

    @Override
    public boolean retornar_atributo_logico(String nome_atributo) throws ErroExecucaoBiblioteca
    {
        if (verificarAtributoExiste(nome_atributo))
        {
            return (Boolean.parseBoolean(listaParametros.get(nome_atributo).toString()));
        }
        else
        {
            throw new ErroExecucaoBiblioteca("O parâmetro " + nome_atributo + " não existe");
        }

    }

    @Override
    public double retornar_atributo_real(String nome_atributo) throws ErroExecucaoBiblioteca
    {
        if (verificarAtributoExiste(nome_atributo))
        {
            return (Double.parseDouble(listaParametros.get(nome_atributo).toString()));
        }
        else
        {
            throw new ErroExecucaoBiblioteca("O parâmetro " + nome_atributo + " não existe");
        }
    }

    @Override
    public double retornar_coordenada_X()
    {
        return this.coordenadaX;
    }

    @Override
    public double retornar_coordenada_Y()
    {
        return this.coordenadaY;
    }

    @Override
    public int retornar_cor_agente()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int retornar_id()
    {
        return this.id;
    }

    @Override
    public int retornar_orientacao()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void voltar(int quantidade)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    protected void paintComponent(Graphics g)
//    {
//        //Cria aqui os desenhos (as formas)
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//    }
    private boolean verificarAtributoExiste(String nome)
    {
        return listaParametros.containsKey(nome);
    }

    public void definirLog(String mensagem)
    {
//        LOGGER.log(Level.INFO, mensagem);
        System.out.println(mensagem);
    }

}
