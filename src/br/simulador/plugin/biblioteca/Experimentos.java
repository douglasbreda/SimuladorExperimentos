package br.simulador.plugin.biblioteca;

import br.simulador.gerenciadores.GerenciadorExecucao;
import br.simulador.gerenciadores.GerenciadorInterface;
import br.univali.portugol.nucleo.bibliotecas.base.Biblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import static br.univali.portugol.nucleo.bibliotecas.base.TipoBiblioteca.COMPARTILHADA;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.Autor;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.DocumentacaoBiblioteca;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.DocumentacaoFuncao;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.DocumentacaoParametro;
import br.univali.portugol.nucleo.bibliotecas.base.anotacoes.PropriedadesBiblioteca;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

@PropriedadesBiblioteca(tipo = COMPARTILHADA)
@DocumentacaoBiblioteca(
        descricao = "Esta biblioteca contém uma série de funcionalidades que permitem a criação de simulações de experimentos físicos ou naturais.",
        versao = "1.0")
public final class Experimentos extends Biblioteca {

    @DocumentacaoFuncao(
            descricao = "Cria n agentes para simulação.",
            parametros
            = {
                @DocumentacaoParametro(nome = "numeroAgentes", descricao = "o número de agentes a ser criado")
                ,
                @DocumentacaoParametro(nome = "aleatorio", descricao = "Os agentes serão criados em lugares aleatórios ou todos na mesma posição")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_agentes(int numeroAgentes, boolean aleatorio) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().criar_agentes(numeroAgentes, aleatorio);
    }

    @DocumentacaoFuncao(
            descricao = "Executa o método principal da simulação.",
            //            parametros =
            //            {
            //                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade em graus para rotacionar"),
            //            },
            retorno = "Sem retorno",
            //            referencia = "Sem referência",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void simular() throws ErroExecucaoBiblioteca, InterruptedException {
        try {
            GerenciadorExecucao.getInstance().inicializar_ambiente();
        } catch (ErroExecucaoBiblioteca | InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(Experimentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DocumentacaoFuncao(
            descricao = "Permite a criação de variáveis para controle e uso durante a simulação.",
            parametros
            = {
                //Verificar se será criada a opção para passar um tipo ou valor default
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Define a identificação do atributo.")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_atributo(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().executarMetodo("criar_parametro", 1, nome_atributo);
    }

    @DocumentacaoFuncao(
            descricao = "Permite controlar a média de uma característica de um agente. Por exemplo, "
            + "exibir em um monitor a média de energia ou velocidade dos agentes.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Define a identificação do atributo.")
            },
            retorno = "Retorna o valor da média de um determinado atributo",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double media(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().media(nome_atributo);
    }

    @DocumentacaoFuncao(
            descricao = "Define um valor a um determinado atributo.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que terá o valor atribuído")
                ,
                @DocumentacaoParametro(nome = "valor", descricao = "Novo valor do atributo")
                ,
                @DocumentacaoParametro(nome = "id_agente", descricao = "Id do agente que terá o valor atualizado")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_valor_atributo(String nome_atributo, String valor, int id_agente) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorExecucao.getInstance().definir_valor_atributo_por_agente(nome_atributo, valor, id_agente);
    }

    @DocumentacaoFuncao(
            descricao = "Mata o agente atual da simulação e o remove do ambiente de simulação.",
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void morrer() throws ErroExecucaoBiblioteca, InterruptedException {
        //Talvez precise do id do agente para saber qual deve morrer
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a coordenada X do agente atual.",
            retorno = "Coordenada X do agente",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double retornar_coordenadaX() throws ErroExecucaoBiblioteca, InterruptedException {
        return (Double) GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_X");
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a coordenada Y do agente atual.",
            retorno = "Coordeanda Y do agente",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double retornar_coordenadaY() throws ErroExecucaoBiblioteca, InterruptedException {
        return (Double) GerenciadorExecucao.getInstance().executarMetodo("retornar_coordenada_Y");
    }

    @DocumentacaoFuncao(
            descricao = "Move o agente n passos(verificar explicação para os passos) para a frente.",
            parametros
            = {
                @DocumentacaoParametro(nome = "quantidade", descricao = "Quantidade de passos para mover")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void mover_frente(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Faz com que o agente retorne n passos.",
            parametros
            = {
                @DocumentacaoParametro(nome = "quantidade", descricao = "Quantidade de passos para retornar")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void voltar(int quantidade) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Gira o agente n graus para a esquerda.",
            parametros
            = {
                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade de graus para o giro")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void girar_esquerda(int graus) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Gira o agente n graus para a direita.",
            parametros
            = {
                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade de graus para o giro")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void girar_direita(int graus) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Move o agente de acordo com a quantidade de graus passada por parâmetro. "
            + "É considerado um posicionamento da tela, ou seja, é igual para todos os agentes.",
            parametros
            = {
                @DocumentacaoParametro(nome = "graus", descricao = "Quantidade de graus para o giro")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_orientacao(int graus) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Move o agente para uma determinada coordenada X e Y.",
            parametros
            = {
                @DocumentacaoParametro(nome = "coordenadaX", descricao = "Qual a coordenada X para pular")
                ,
                @DocumentacaoParametro(nome = "coordenadaY", descricao = "Qual a coordenada Y para pular"),},
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void pular_para_XY(int coordenadaX, int coordenadaY) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Define uma cor ao agente.",
            parametros
            = {
                @DocumentacaoParametro(nome = "cor", descricao = "Cor para o agente. Utilize a biblioteca Gráficos que já possui "
                        + "cores pré-definidas")
            },
            retorno = "Sem retorno",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_cor_agente(int cor) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato de cadeia de caracteres.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para cadeia de caracteres",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public String retornar_atributo_cadeia(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return "";
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato de caracter.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para caractere",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public char retornar_atributo_caracter(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return ' ';
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato inteiro.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para número inteiro",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_atributo_inteiro(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato lógico.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para lógico",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public boolean retornar_atributo_logico(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return false;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna um parâmetro no formato real.",
            parametros
            = {
                @DocumentacaoParametro(nome = "nome_atributo", descricao = "Nome do atributo que será retornado")
            },
            retorno = "Retorna o parâmetro convertido para número real",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double retornar_atributo_real(String nome_atributo) throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a cor do agente atual.",
            retorno = "O código inteiro referente a cor",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_cor_agente() throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a orientação do agente atual.",
            retorno = "A posição em graus referente a orientação",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_orientacao() throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Move o agente até uma posição específica no retalho.",
            parametros
            = {
                @DocumentacaoParametro(nome = "coordenadaX", descricao = "Nova coordenada X")
                ,
                @DocumentacaoParametro(nome = "coordeandaY", descricao = "Nova coordenada Y")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void ir_ate(int coordenadaX, int coordenadaY) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Retorna o ID único do agente atual.",
            retorno = "ID do agente",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_id() throws ErroExecucaoBiblioteca, InterruptedException {
        return (int) GerenciadorExecucao.getInstance().executarMetodo("retornar_id");
    }

    @DocumentacaoFuncao(
            descricao = "Conta todos os agentes presentes na simulação.",
            retorno = "Número de agentes da simulação",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int contar_agentes() throws ErroExecucaoBiblioteca, InterruptedException {
        return GerenciadorExecucao.getInstance().contar_agentes();
    }

    @DocumentacaoFuncao(
            descricao = "Realiza a atualização dos componentes visuais caso necessário.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void atualizar_tela() throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorInterface.getInstance().atualizar_tela();
    }

    @DocumentacaoFuncao(
            descricao = "Limpa o ambiente de simulação, voltando para a configuração inicial(Verificar).",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void limpar_tudo() throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorInterface.getInstance().limpar_tudo();
    }

    @DocumentacaoFuncao(
            descricao = "Permite a criação de um componente de slider para alteração de configurações durante a simulação",
            parametros
            = {
                @DocumentacaoParametro(nome = "titulo", descricao = "Título do componente")
                ,
                @DocumentacaoParametro(nome = "minimo", descricao = "Valor mínimo permitido do componente")
                ,
                @DocumentacaoParametro(nome = "máximo", descricao = "Valor máximo permitido do componente")
                ,
                @DocumentacaoParametro(nome = "valor_padrao", descricao = "Valor padrão para definir ao criar o componente")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_slider(String titulo, double minimo, double maximo, double valor_padrao) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Cria um componente do tipo Monitor para exibição de alguma variável durante a execução.",
            parametros
            = {
                @DocumentacaoParametro(nome = "titulo", descricao = "Título do componente")
                ,
                @DocumentacaoParametro(nome = "minimo", descricao = "Valor padrão do componente"),},
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_monitor(String titulo, double valor) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Cria um componente do tipo Interruptor que permite ao usuário ligar ou desligar determinada funcionalidade ou característica de uma agente ou ambiente.",
            parametros
            = {
                @DocumentacaoParametro(nome = "titulo", descricao = "Título do componente")
                ,
                @DocumentacaoParametro(nome = "valor_padrao", descricao = "Se inicia com verdadeiro ou falso"),},
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void criar_interruptor(String titulo, boolean valor_padrao) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Retorna quantos agentes possuem uma determinada cor.",
            parametros
            = {
                @DocumentacaoParametro(nome = "cor", descricao = "Define o código inteiro da cor a ser atribuída ao agente")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int agentes_com_cor(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Define os limites das bordas e passa aceita um parâmetro com a cor. Neste caso a borda já é destacada com a cor passada por parâmetro.",
            parametros
            = {
                @DocumentacaoParametro(nome = "cor", descricao = "Define o código inteiro da cor a ser atribuída ao agente")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_bordas(int cor) throws ErroExecucaoBiblioteca, InterruptedException {
        GerenciadorInterface.getInstance().definir_bordas(cor);
    }

    @DocumentacaoFuncao(
            descricao = "Retorna a quantidade de agentes em um determinado patch de acordo com as coordenadas passadas por parâmetro.",
            parametros = {
                @DocumentacaoParametro(nome = "coordenadaX", descricao = "Qual a coordenada X desejada")
                ,
                @DocumentacaoParametro(nome = "coordenadaY", descricao = "Qual a coordenada Y desejada")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void agentes_em_XY(double coordenadaX, double coordenadaY) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Define a cor do retalho atual.",
            parametros = {
                @DocumentacaoParametro(nome = "cor", descricao = "Código inteiro da cor")
            },
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public void definir_cor_retalho(int cor) throws ErroExecucaoBiblioteca, InterruptedException {

    }

    @DocumentacaoFuncao(
            descricao = "Retorna a cor atual do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_cor_retalho() throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna qual é o limite máximo da coordenada X do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double retornar_valor_max_bordaX() throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna qual é o limite máximo da coordenada Y do retalho.",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public double retornar_valor_max_bordaY() throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna se o agente irá colidir com uma parede (Para auxiliar no tratamento de colisões",
            autores
            = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public boolean colidiu_com_parede() throws ErroExecucaoBiblioteca, InterruptedException {
        return false;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna se o agente colidiu com alguma parede lateral (direita ou esquerda)",
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public boolean colidiu_borda_X() throws ErroExecucaoBiblioteca, InterruptedException {
        return false;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna se o agente colidiu com alguma parede superior ou inferior",
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public boolean colidiu_borda_Y() throws ErroExecucaoBiblioteca, InterruptedException {
        return false;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna o id de um retalho partindo do retalho atual e andando algumas posições",
            parametros = {
                @DocumentacaoParametro(nome = "numero_retalho_a_frente", descricao = "Número de passos a andar")
            },
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int retornar_id_retalho(int numero_retalho_a_frente) throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }

    @DocumentacaoFuncao(
            descricao = "Retorna o id atual do retalho em que o agente está posicionado",
            autores = {
                @Autor(nome = "Douglas Breda", email = "bredadouglas@gmail.com")
            }
    )
    public int meu_retalho() throws ErroExecucaoBiblioteca, InterruptedException {
        return 0;
    }
}
