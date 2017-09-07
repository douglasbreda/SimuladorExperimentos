
package br.simulador.ui;

import br.simulador.plugin.SimuladorExperimentos;
import br.simulador.plugin.biblioteca.Experimentos;
import br.simulador.plugin.biblioteca.componentes.Interruptor;
import br.simulador.plugin.biblioteca.componentes.Monitor;
import br.simulador.plugin.biblioteca.componentes.Slider;
import br.univali.portugol.nucleo.bibliotecas.base.ErroExecucaoBiblioteca;
import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.VisaoPlugin;

/**
 *
 * @author Douglas
 */
public class PainelSimulacao extends VisaoPlugin {

    private SimuladorExperimentos simuladorPlugin;
    
    public PainelSimulacao(Plugin plugin) throws ErroExecucaoBiblioteca, InterruptedException{
        super(plugin);
        simuladorPlugin = ((SimuladorExperimentos) plugin);
        initComponents();
        adicionarComponentes();
        testarFuncao();
    }
    
    private void adicionarComponentes(){
        pnlComponentes.add(new Slider());
        pnlComponentes.add(new Interruptor());
        pnlComponentes.add(new Monitor());
    }
    
    private void testarFuncao() throws ErroExecucaoBiblioteca, InterruptedException{
        Experimentos oExperimentos = new Experimentos();
        oExperimentos.criar_agentes(10, true);
        oExperimentos.criar_atributo("velocidade");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        pnlBotoes = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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

        jButton1.setText("Iniciar");

        jButton2.setText("Parar");

        javax.swing.GroupLayout pnlBotoesLayout = new javax.swing.GroupLayout(pnlBotoes);
        pnlBotoes.setLayout(pnlBotoesLayout);
        pnlBotoesLayout.setHorizontalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoesLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlBotoesLayout.setVerticalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
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
                .addComponent(pnlComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlExecucao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Execução"));

        javax.swing.GroupLayout pnlExecucaoLayout = new javax.swing.GroupLayout(pnlExecucao);
        pnlExecucao.setLayout(pnlExecucaoLayout);
        pnlExecucaoLayout.setHorizontalGroup(
            pnlExecucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlExecucaoLayout.setVerticalGroup(
            pnlExecucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
