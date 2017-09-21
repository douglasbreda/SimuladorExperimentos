package br.simulador.plugin.biblioteca.componentes;

import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Douglas
 */
public class Slider extends javax.swing.JPanel implements IComponenteSimulacao{

    public Slider(String titulo, int valor_minimo, int valor_maximo, int valor_padrao){
        initComponents();
        adicionarEvento();
        definir_valores(titulo, valor_minimo, valor_maximo, valor_padrao);
    }
    
    public Slider() {
        initComponents();
        adicionarEvento();
        definir_valores("Slider", 0, 100, 50);
    }
   
    /**
     * Define os valores padrões ou não dos componentes
     * @param titulo Título do componente
     * @param valor_minimo Valor mínimo do Slider
     * @param valor_maximo Valor máximo do Slider
     * @param valor_padrao Valor padrão (inicial)
     */
    private void definir_valores(String titulo, int valor_minimo, int valor_maximo, int valor_padrao){
        this.definir_titulo(titulo);
        this.definir_valor_maximo(valor_maximo);
        this.definir_valor_minimo(valor_minimo);
        this.definir_valor_padrao(valor_padrao);
    }

    private void adicionarEvento() {
        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jSlider = (JSlider) e.getSource();

                if (!jSlider.getValueIsAdjusting()) {
                    txtValor.setText(String.valueOf(slider.getValue()));
                }
            }
        });
    }
    
     @Override
    public void definir_titulo(String titulo) {
        ((TitledBorder) this.pnlSlider.getBorder()).setTitle(titulo);
        this.setLog("Título definido para: " + titulo);
    }
    
    /**
     * Define o valor mínimo do Slider
     * @param valor_minimo Valor mínimo a ser atribuído
     */
    public void definir_valor_minimo(int valor_minimo){
        this.slider.setMinimum(valor_minimo);
        this.setLog("Valor mínimo definido para: " + valor_minimo);
    }
    
    /**
     * Define o valor máximo do Slider
     * @param valor_maximo Valor máximo a ser atribuído
     */
    public void definir_valor_maximo(int valor_maximo){
        this.slider.setMaximum(valor_maximo);
        this.setLog("Valor máximo definido para: " + valor_maximo);
    }
    
    /**
     * Define o valor padrão do Slider
     * @param valor_padrao Valor padrão a ser atribuído
     */
    public void definir_valor_padrao(int valor_padrao){
        this.slider.setValue(valor_padrao);
        this.txtValor.setText(String.valueOf(valor_padrao));
        this.setLog("Valor padrão definido para: " + valor_padrao);
        
    }
    
    /**
     * Retorna o valor mínimo setado no Slider
     * @return 
     */
    public int retornar_valor_minimo(){
        return this.slider.getMinimum();
    }
    
    /**
     * Retorna o valor máximo setado no Slider
     * @return 
     */
    public int retornar_valor_maximo(){
        return this.slider.getMaximum();
    }
    
    /**
     * Retorna qual é o valor atual do Slider
     * @return 
     */
    public int retornar_valor_atual(){
        return this.slider.getValue();
    }

    private void setLog(String mensagem){
        System.out.println(mensagem);
        slider.validate();
    }
    
    public void atualizar_valor(String titulo, int valor){
        //Verificar esse método
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSlider = new javax.swing.JPanel();
        slider = new javax.swing.JSlider();
        txtValor = new javax.swing.JLabel();

        pnlSlider.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)), "Teste"));
        pnlSlider.setPreferredSize(new java.awt.Dimension(175, 54));

        txtValor.setText("0");

        javax.swing.GroupLayout pnlSliderLayout = new javax.swing.GroupLayout(pnlSlider);
        pnlSlider.setLayout(pnlSliderLayout);
        pnlSliderLayout.setHorizontalGroup(
            pnlSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSliderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor)
                .addContainerGap())
        );
        pnlSliderLayout.setVerticalGroup(
            pnlSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSliderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValor)
                    .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlSlider;
    private javax.swing.JSlider slider;
    private javax.swing.JLabel txtValor;
    // End of variables declaration//GEN-END:variables

   
}
