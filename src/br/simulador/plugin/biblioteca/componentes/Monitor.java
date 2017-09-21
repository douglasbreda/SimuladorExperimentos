package br.simulador.plugin.biblioteca.componentes;

/**
 *
 * @author Douglas
 */
public class Monitor extends javax.swing.JPanel implements IComponenteSimulacao{

    public Monitor(String titulo, String valor){
        initComponents();
        definir_titulo(titulo);
        definir_valor(valor);
    }
    
    public Monitor() {
        initComponents();
    }
    
    @Override
    public void definir_titulo(String titulo) {
        lblTitulo.setText(titulo);
        setLog("Título definido para: " + titulo);
    }

    /**
     * Retorna o valor atual sendo exibido no monitor
     * @return 
     */
    public String retornar_valor(){
        return this.txtInformacao.getText();
    }
    
    /**
     * Atualiza o valor do monitor
     * @param titulo Qual o novo título do componente
     * @param valor  Qual o novo valor do componente
     */
    public void atualizar_valor(String titulo, String valor){
        this.definir_titulo(titulo);
        this.definir_valor(valor);
    }
    
    /**
     * Define um valor para o monitor
     * @param valor Valor a ser atribuído
     */
    public void definir_valor(String valor){
        this.txtInformacao.setText(valor);
        setLog("Valor definido para: " + valor);
    }
    
    private void setLog(String mensagem){
        System.out.println(mensagem);
        txtInformacao.validate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMonitor = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtInformacao = new javax.swing.JTextField();

        pnlMonitor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        lblTitulo.setText("Título");

        txtInformacao.setEditable(false);
        txtInformacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInformacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMonitorLayout = new javax.swing.GroupLayout(pnlMonitor);
        pnlMonitor.setLayout(pnlMonitorLayout);
        pnlMonitorLayout.setHorizontalGroup(
            pnlMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonitorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(txtInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMonitorLayout.setVerticalGroup(
            pnlMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonitorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtInformacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInformacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInformacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlMonitor;
    private javax.swing.JTextField txtInformacao;
    // End of variables declaration//GEN-END:variables

}
