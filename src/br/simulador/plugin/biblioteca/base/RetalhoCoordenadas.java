package br.simulador.plugin.biblioteca.base;

/**
 *
 * @author Douglas
 */
public class RetalhoCoordenadas
{
    private int coordenadaX = 0;
    private int coordenadaY = 0;

    public RetalhoCoordenadas(int coordenadaX, int coordenadaY)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public int getCoordenadaX()
    {
        return coordenadaX;
    }

    public int getCoordenadaY()
    {
        return coordenadaY;
    }
}
