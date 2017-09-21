/**
 * Gerenciador para controlar a execução das Threads para cada agente
 */
package br.simulador.gerenciadores;

import br.simulador.plugin.biblioteca.base.Agente;
import br.simulador.plugin.biblioteca.base.IAgente;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public class GerenciadorExecucao {

    private static ArrayList<IAgente> listaAgentes = null;

    /**
     * Executa os métodos para todos os agentes da lista através de reflection
     *
     * @param nome_metodo
     * @param parametros
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void executarMetodo(String nome_metodo, Object... parametros) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        if (listaAgentes != null) {

            Object classe = Class.forName(Agente.class.getName()).newInstance();

            for (IAgente agente : listaAgentes) {

                Method m = classe.getClass().getMethod(nome_metodo);
                System.out.println(m.invoke(agente, parametros));
            }
        }
    }

    public static void setListaAgentes(ArrayList<IAgente> listaAgentes) {
        GerenciadorExecucao.listaAgentes = listaAgentes;
    }
}
