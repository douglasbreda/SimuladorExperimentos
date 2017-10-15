package br.simulador.ui;

import br.simulador.plugin.biblioteca.componentes.Interruptor;
import br.simulador.plugin.biblioteca.componentes.Monitor;
import br.simulador.plugin.biblioteca.componentes.PainelBase;
import br.simulador.plugin.biblioteca.componentes.Slider;
import br.simulador.util.UtilSimulador;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.VisaoPlugin;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Douglas
 */
public class PainelSimulacao extends VisaoPlugin {

    private PainelBase painelSimulacao = null;

    private ArrayList<Slider> listaSliders = null;
    private ArrayList<Interruptor> listaInterruptores = null;
    private ArrayList<Monitor> listaMonitores = null;

    /**
     * Construtor padrão do plugin
     *
     * @param plugin
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public PainelSimulacao(Plugin plugin) throws ErroExecucaoBiblioteca, InterruptedException {
        super(plugin);
        initComponents();
        inicializar_atributos();
    }

    /**
     * Inicializa os atributos necessários para a simulação
     */
    private void inicializar_atributos() {

        listaInterruptores = new ArrayList<>();
        listaMonitores = new ArrayList<>();
        listaSliders = new ArrayList<>();
    }

    /**
     * Cria o painel onde será executada a simulação
     *
     * @throws ErroExecucaoBiblioteca
     * @throws InterruptedException
     */
    public void criar_paineis() throws ErroExecucaoBiblioteca, InterruptedException {

        pnlExecucao.setLayout(null);

        painelSimulacao = new PainelBase();

        pnlExecucao.add(painelSimulacao);

        redefinirTamanhoPainel();

        pnlExecucao.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redefinirTamanhoPainel();
            }
        });
    }

    /**
     * Redefine o tamanho do painel conforme a tela tem seu tamanho alterado
     */
    public void redefinirTamanhoPainel() {
        painelSimulacao.setBounds(10, 20,
                pnlExecucao.getWidth() - 20,
                pnlExecucao.getHeight() - 30);
    }

    /**
     * Retorna a instância do painel onde está ocorrendo a simulação
     *
     * @return
     */
    public PainelBase getPainelSimulacao() {
        return painelSimulacao;
    }

    /**
     * Adiciona um componente de slide ao painel de componentes
     *
     * @param titulo
     * @param valor_minimo
     * @param valor_maximo
     * @param valor_padrao
     */
    public void criar_slider(String titulo, int valor_minimo, int valor_maximo, int valor_padrao) {
//        Slider slider = new Slider(titulo, valor_minimo, valor_maximo, valor_padrao);
//        adicionar_componente_painel(slider);
//        slider.revalidate();
//        listaSliders.add(slider);

    }

    /**
     * Adiciona um componente do tipo monitor ao painel de componentes
     *
     * @param titulo
     * @param valor
     */
    public void criar_monitor(String titulo, String valor) {
//        Monitor monitor = new Monitor(titulo, valor);
//        adicionar_componente_painel(monitor);
//        monitor.revalidate();
//        listaMonitores.add(monitor);
    }

    /**
     * Adiciona um componente do tipo interruptor ao painel de componentes
     *
     * @param titulo
     * @param valor_padrao
     */
    public void criar_interruptor(String titulo, boolean valor_padrao) {
//        Interruptor interruptor = new Interruptor(titulo, valor_padrao);
//        adicionar_componente_painel(interruptor);
//        interruptor.revalidate();
//        listaInterruptores.add(interruptor);
    }

    /**
     * Adiciona um painel (Monitor, Interruptor ou Slider) ao painel de
     * componentes
     *
     * @param componente
     */
    private void adicionar_componente_painel(JPanel componente) {
        pnlComponentes.add(componente);
    }

    /**
     * Método para forçar uma atualização dos componentes da tela
     */
    public void atualizar_tela(Container container) {
        executar_atualizar_tela(container);
    }

    /**
     * Método recursivo que pega todos os componentes da tela e atualiza
     * @param container 
     */
    private void executar_atualizar_tela(Container container) {
        
        for (Component c : container.getComponents()) {

            UtilSimulador.setLog("Atualizando componente " + c.toString());
            
            if (c instanceof Container) {
                executar_atualizar_tela((Container) c);
            }
            
            c.revalidate();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        pnlBotoes = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        pnlSaidas = new javax.swing.JPanel();
        pnlComponentes = new javax.swing.JPanel();
        pnlExecucao = new javax.swing.JPanel();
        pnlStatus = new javax.swing.JPanel();
        txtTotalAgentes = new javax.swing.JLabel();
        txtNumeroAgentes = new javax.swing.JLabel();
        lblStatusAtual = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblTicks = new javax.swing.JLabel();
        lblNumeroTicks = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 3));

        pnlTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        pnlBotoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnIniciar.setText("Iniciar");

        btnParar.setText("Parar");

        jButton1.setText("Testar");

        javax.swing.GroupLayout pnlBotoesLayout = new javax.swing.GroupLayout(pnlBotoes);
        pnlBotoes.setLayout(pnlBotoesLayout);
        pnlBotoesLayout.setHorizontalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoesLayout.createSequentialGroup()
                .addComponent(btnIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnParar)
                .addGap(135, 135, 135)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlBotoesLayout.setVerticalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar)
                    .addComponent(btnParar)
                    .addComponent(jButton1)))
        );

        pnlSaidas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Saídas"));

        javax.swing.GroupLayout pnlSaidasLayout = new javax.swing.GroupLayout(pnlSaidas);
        pnlSaidas.setLayout(pnlSaidasLayout);
        pnlSaidasLayout.setHorizontalGroup(
            pnlSaidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSaidasLayout.setVerticalGroup(
            pnlSaidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaidasLayout.createSequentialGroup()
                .addComponent(pnlComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlExecucao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Execução"));

        javax.swing.GroupLayout pnlExecucaoLayout = new javax.swing.GroupLayout(pnlExecucao);
        pnlExecucao.setLayout(pnlExecucaoLayout);
        pnlExecucaoLayout.setHorizontalGroup(
            pnlExecucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );
        pnlExecucaoLayout.setVerticalGroup(
            pnlExecucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );

        pnlStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTotalAgentes.setText("Total Agentes:");
        txtTotalAgentes.setToolTipText("");

        txtNumeroAgentes.setText("0");

        lblStatusAtual.setText("Status: ");

        lblStatus.setText("Parado");

        lblTicks.setText("Ticks: ");

        lblNumeroTicks.setText("0");

        javax.swing.GroupLayout pnlStatusLayout = new javax.swing.GroupLayout(pnlStatus);
        pnlStatus.setLayout(pnlStatusLayout);
        pnlStatusLayout.setHorizontalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotalAgentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroAgentes)
                .addGap(52, 52, 52)
                .addComponent(lblStatusAtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addGap(57, 57, 57)
                .addComponent(lblTicks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroTicks)
                .addContainerGap(309, Short.MAX_VALUE))
        );
        pnlStatusLayout.setVerticalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalAgentes)
                    .addComponent(txtNumeroAgentes)
                    .addComponent(lblStatusAtual)
                    .addComponent(lblStatus)
                    .addComponent(lblTicks)
                    .addComponent(lblNumeroTicks))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlExecucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSaidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlExecucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnParar;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNumeroTicks;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusAtual;
    private javax.swing.JLabel lblTicks;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlComponentes;
    private javax.swing.JPanel pnlExecucao;
    private javax.swing.JPanel pnlSaidas;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JLabel txtNumeroAgentes;
    private javax.swing.JLabel txtTotalAgentes;
    // End of variables declaration//GEN-END:variables
}
