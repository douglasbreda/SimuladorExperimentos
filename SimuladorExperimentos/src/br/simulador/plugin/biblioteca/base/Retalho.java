/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.simulador.plugin.biblioteca.base;

import java.util.Random;

/**
 *
 * @author Douglas
 */
public class Retalho
{
    private int cor;

    //Verificar o tipo de retorno deste método
    public void agentes_aqui()
    {

    }

    //Verificar Retorno
    public void agentes_em_XY(double coordenadaX, double coordenadaY)
    {

    }

    public void definir_cor_retalho(int cor)
    {

    }

    public int retornar_cor_retalho()
    {
        return 0;
    }

    public double retornar_max_borda_x()
    {
        return 0;
    }

    public double retornar_max_borda_y()
    {
        return 0;
    }

    //Define uma coordenada inicial para instanciar o agente
    public RetalhoCoordenadas definirCoordenadasIniciais()
    {
        Random randomX = new Random();
        Random randomY = new Random();

        return new RetalhoCoordenadas(randomX.nextDouble(), randomY.nextDouble());
    }

}
