package br.simulador.plugin.biblioteca.base;

/**
 *
 * @author Douglas
 */
public class Agente extends BaseAgente
{
    /**
     * Construtor vazio
     */
    public Agente() {
        super(0, 0, 0, 0);
    }
    
    /**
     * Construtor que já recebe as configurações do agente
     * @param coordenadaX
     * @param coordenadaY
     * @param id
     * @param forma 
     */
    public Agente(int coordenadaX, int coordenadaY, int id, int forma)
    {
        super(coordenadaX, coordenadaY, id, forma);
    }
}
