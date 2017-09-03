/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.plugin.biblioteca.base;

/**
 *
 * @author Douglas
 */
public class RetalhoCoordenadas
{
    private double coordenadaX = 0;
    private double coordenadaY = 0;

    public RetalhoCoordenadas(double coordenadaX, double coordenadaY)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public double getCoordenadaX()
    {
        return coordenadaX;
    }

    public double getCoordenadaY()
    {
        return coordenadaY;
    }
}
