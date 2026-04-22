package gestionabarrotes;
import java.awt.CardLayout;
import java.awt.Color;

public class Index extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private panelCatalogo panelCatalogo;
    private panelDashboard panelDashboard;
    
    public Index() {
        initComponents();
        //reinicarColoresBotones();
        
        //Declaración de CardLayout y se añade el Layout al panel que contendrá todos
        cardLayout = new CardLayout();
        contGeneral.setLayout(cardLayout);
        
        panelCatalogo = new panelCatalogo();
        panelDashboard = new panelDashboard();
        
        contGeneral.add(panelDashboard,"Dashboard");
        contGeneral.add(panelCatalogo,"Catalogo");
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cambioCatalogoBt = new javax.swing.JButton();
        cambioMovimientoBt = new javax.swing.JButton();
        cambioDashboardBt = new javax.swing.JButton();
        cambioAnalisisBt = new javax.swing.JButton();
        cambioReporteBt = new javax.swing.JButton();
        cambioConfiguracionBt = new javax.swing.JButton();
        contGeneral = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(145, 199, 87));

        cambioCatalogoBt.setText("Catalogo");
        cambioCatalogoBt.setBorder(null);
        cambioCatalogoBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cambioCatalogoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioCatalogoBtActionPerformed(evt);
            }
        });

        cambioMovimientoBt.setText("Movimientos Inventario");
        cambioMovimientoBt.setBorder(null);
        cambioMovimientoBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        cambioDashboardBt.setText("Dashboard");
        cambioDashboardBt.setBorder(null);
        cambioDashboardBt.setBorderPainted(false);
        cambioDashboardBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cambioDashboardBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioDashboardBtActionPerformed(evt);
            }
        });

        cambioAnalisisBt.setText("Análisis de Inventario");
        cambioAnalisisBt.setBorder(null);
        cambioAnalisisBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        cambioReporteBt.setText("Reportes");
        cambioReporteBt.setBorder(null);
        cambioReporteBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        cambioConfiguracionBt.setText("Configuración");
        cambioConfiguracionBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioConfiguracionBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cambioMovimientoBt, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(cambioCatalogoBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cambioDashboardBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cambioAnalisisBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cambioReporteBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cambioConfiguracionBt)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(cambioDashboardBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cambioCatalogoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cambioMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cambioAnalisisBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cambioReporteBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cambioConfiguracionBt)
                .addContainerGap())
        );

        contGeneral.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout contGeneralLayout = new javax.swing.GroupLayout(contGeneral);
        contGeneral.setLayout(contGeneralLayout);
        contGeneralLayout.setHorizontalGroup(
            contGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 859, Short.MAX_VALUE)
        );
        contGeneralLayout.setVerticalGroup(
            contGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(contGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cambioCatalogoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioCatalogoBtActionPerformed
        reinicarColoresBotones();
        cambioCatalogoBt.setBackground(new Color(0,0,0));
        cardLayout.show(contGeneral, "Catalogo");
    }//GEN-LAST:event_cambioCatalogoBtActionPerformed

    private void cambioConfiguracionBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioConfiguracionBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cambioConfiguracionBtActionPerformed

    private void cambioDashboardBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioDashboardBtActionPerformed
        reinicarColoresBotones();
        cambioDashboardBt.setBackground(new Color(0,0,0));
        cardLayout.show(contGeneral,"Dashboard");
        
    }//GEN-LAST:event_cambioDashboardBtActionPerformed

    public void reinicarColoresBotones(){
        cambioDashboardBt.setBackground(new Color(145,199,87));
        cambioCatalogoBt.setBackground(new Color(145,199,87));
        cambioMovimientoBt.setBackground(new Color(145,199,87));
        cambioAnalisisBt.setBackground(new Color(145,199,87));
        cambioReporteBt.setBackground(new Color(145,199,87));        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cambioAnalisisBt;
    private javax.swing.JButton cambioCatalogoBt;
    private javax.swing.JButton cambioConfiguracionBt;
    private javax.swing.JButton cambioDashboardBt;
    private javax.swing.JButton cambioMovimientoBt;
    private javax.swing.JButton cambioReporteBt;
    private javax.swing.JPanel contGeneral;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
