/**
 * Classe de teste para exibir o código fonte buscado
 */
package br.simulador.ui;

import javax.swing.SwingUtilities;

/**
 *
 * @author Douglas
 */
public class JanelaCodigoFonte extends javax.swing.JDialog {

    /**
     * Creates new form CodigoFonte
     */
    public JanelaCodigoFonte() {
        initComponents();
        setModal(true);
    }
    
    public void atribuir_codigo_fonte(String codigo_fonte){
        txtCodigoFonte.setText(codigo_fonte);
        
//          SwingUtilities.invokeLater(() -> {
//              pnlRolagem.getVerticalScrollBar().setValue(0);
//              pnlRolagem.getHorizontalScrollBar().setValue(0);
//        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRolagem = new javax.swing.JScrollPane();
        txtCodigoFonte = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtCodigoFonte.setColumns(20);
        txtCodigoFonte.setRows(5);
        pnlRolagem.setViewportView(txtCodigoFonte);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRolagem, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRolagem, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane pnlRolagem;
    private javax.swing.JTextArea txtCodigoFonte;
    // End of variables declaration//GEN-END:variables
}