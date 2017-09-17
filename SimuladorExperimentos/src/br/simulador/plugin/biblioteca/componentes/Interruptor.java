package br.simulador.plugin.biblioteca.componentes;

import javax.swing.AbstractButton;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Douglas
 */
public class Interruptor extends javax.swing.JPanel implements IComponenteSimulacao {

    public Interruptor(String titulo, boolean valor_padrao) {
        initComponents();
        definir_titulo(titulo);
        definir_estado_botao(valor_padrao);
    }

    public Interruptor() {
        initComponents();
    }

    /**
     * Define o botão como ativo/ligado
     */
    public void ativar() {
        setLog("Interruptor ligado");
        this.btnInterruptor.setSelected(true);
        this.btnInterruptor.setText("ON");
    }

    /**
     * Define o botão como desativado/desligado
     */
    public void desativar() {
        setLog("Interruptor desligado");
        this.btnInterruptor.setSelected(false);
        this.btnInterruptor.setText("OFF");
    }

    /**
     * Retorna o status atual do botão (ligado/desligado)
     * @return 
     */
    public boolean esta_ligado() {
        return btnInterruptor.isSelected();
    }

    @Override
    public void definir_titulo(String titulo) {
        ((TitledBorder) pnlInterruptor.getBorder()).setTitle(titulo);
    }

    private void setLog(String mensagem) {
        System.out.println(mensagem);
        this.revalidate();
    }

    /**
     * Permite a alteração do estado do botão
     * @param estado Estado a ser atualizado
     */
    private void definir_estado_botao(boolean estado) {
        if (estado) {
            ativar();
        } else {
            desativar();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInterruptor = new javax.swing.JPanel();
        btnInterruptor = new javax.swing.JToggleButton();

        pnlInterruptor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)), "Interruptor"));
        pnlInterruptor.setPreferredSize(new java.awt.Dimension(175, 61));

        btnInterruptor.setText("OFF");
        btnInterruptor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInterruptorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInterruptorLayout = new javax.swing.GroupLayout(pnlInterruptor);
        pnlInterruptor.setLayout(pnlInterruptorLayout);
        pnlInterruptorLayout.setHorizontalGroup(
            pnlInterruptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInterruptorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInterruptor, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInterruptorLayout.setVerticalGroup(
            pnlInterruptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInterruptorLayout.createSequentialGroup()
                .addComponent(btnInterruptor)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInterruptor, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInterruptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInterruptorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInterruptorActionPerformed
        AbstractButton botao = (AbstractButton) evt.getSource();
        boolean ligado = botao.getModel().isSelected();
        definir_estado_botao(ligado);
    }//GEN-LAST:event_btnInterruptorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnInterruptor;
    private javax.swing.JPanel pnlInterruptor;
    // End of variables declaration//GEN-END:variables

}
