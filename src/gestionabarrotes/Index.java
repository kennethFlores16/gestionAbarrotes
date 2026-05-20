package gestionabarrotes;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Index extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private panelCatalogo panelCatalogo;
    private panelDashboard panelDashboard;
    private panelMovimientos panelMovimientos;
    private panelConfiguracion panelConfiguracion;
    private panelAnalisisInventario panelAnalisis;
    public Color[] colores = {
                new Color(145,199,87), //Primario
                Color.WHITE, //Fondo
                new Color(219,213,33), //Resalto
                Color.BLACK //Letras
            };
    public Index() throws IOException {
        initComponents();
        //Declaración de CardLayout y se añade el Layout al panel que contendrá todos
        cardLayout = new CardLayout();
        contGeneral.setLayout(cardLayout);
        
        panelDashboard = new panelDashboard();
        panelCatalogo = new panelCatalogo(colores);
        panelMovimientos = new panelMovimientos(colores);
        panelConfiguracion = new panelConfiguracion();
        panelAnalisis = new panelAnalisisInventario();
        
        contGeneral.add(panelDashboard,"Dashboard");
        contGeneral.add(panelCatalogo,"Catalogo");
        contGeneral.add(panelMovimientos,"Movimientos");
        contGeneral.add(panelAnalisis,"Analisis");
        contGeneral.add(panelConfiguracion,"Configuracion");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOpciones = new javax.swing.JPanel();
        cambioCatalogoBt = new javax.swing.JButton();
        cambioMovimientoBt = new javax.swing.JButton();
        cambioDashboardBt = new javax.swing.JButton();
        cambioAnalisisBt = new javax.swing.JButton();
        cambioReporteBt = new javax.swing.JButton();
        cambioConfiguracionBt = new javax.swing.JButton();
        modosToggleBt = new javax.swing.JToggleButton();
        contGeneral = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelOpciones.setBackground(new java.awt.Color(145, 199, 87));

        cambioCatalogoBt.setBackground(new java.awt.Color(145, 199, 87));
        cambioCatalogoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        cambioCatalogoBt.setText("Catalogo");
        cambioCatalogoBt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        cambioCatalogoBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cambioCatalogoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioCatalogoBtActionPerformed(evt);
            }
        });

        cambioMovimientoBt.setBackground(new java.awt.Color(145, 199, 87));
        cambioMovimientoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        cambioMovimientoBt.setText("Movimientos Inventario");
        cambioMovimientoBt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        cambioMovimientoBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cambioMovimientoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioMovimientoBtActionPerformed(evt);
            }
        });

        cambioDashboardBt.setBackground(new java.awt.Color(145, 199, 87));
        cambioDashboardBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        cambioDashboardBt.setText("Dashboard");
        cambioDashboardBt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        cambioDashboardBt.setBorderPainted(false);
        cambioDashboardBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cambioDashboardBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioDashboardBtActionPerformed(evt);
            }
        });

        cambioAnalisisBt.setBackground(new java.awt.Color(145, 199, 87));
        cambioAnalisisBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        cambioAnalisisBt.setText("Análisis de Inventario");
        cambioAnalisisBt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        cambioAnalisisBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cambioAnalisisBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAnalisisBtActionPerformed(evt);
            }
        });

        cambioReporteBt.setBackground(new java.awt.Color(145, 199, 87));
        cambioReporteBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        cambioReporteBt.setText("Reportes");
        cambioReporteBt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        cambioReporteBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cambioReporteBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioReporteBtActionPerformed(evt);
            }
        });

        cambioConfiguracionBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestionabarrotes/ajustes-deslizadores.png"))); // NOI18N
        cambioConfiguracionBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioConfiguracionBtActionPerformed(evt);
            }
        });

        modosToggleBt.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        modosToggleBt.setText("Modo Claro");
        modosToggleBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modosToggleBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcionesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(modosToggleBt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cambioConfiguracionBt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(cambioAnalisisBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cambioMovimientoBt, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
            .addComponent(cambioCatalogoBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cambioDashboardBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cambioReporteBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
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
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cambioConfiguracionBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modosToggleBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        contGeneral.setBackground(new java.awt.Color(204, 204, 255));
        contGeneral.setRequestFocusEnabled(false);

        javax.swing.GroupLayout contGeneralLayout = new javax.swing.GroupLayout(contGeneral);
        contGeneral.setLayout(contGeneralLayout);
        contGeneralLayout.setHorizontalGroup(
            contGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1051, Short.MAX_VALUE)
        );
        contGeneralLayout.setVerticalGroup(
            contGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cambioCatalogoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioCatalogoBtActionPerformed
        reinicarColoresBotones();
        cambioCatalogoBt.setBackground(colores[1]);
        cardLayout.show(contGeneral, "Catalogo");
        try {
            panelCatalogo.inicializar();
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cambioCatalogoBtActionPerformed

    private void cambioConfiguracionBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioConfiguracionBtActionPerformed
        cardLayout.show(contGeneral, "Configuracion");
    }//GEN-LAST:event_cambioConfiguracionBtActionPerformed

    private void cambioDashboardBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioDashboardBtActionPerformed
        reinicarColoresBotones();
        cambioDashboardBt.setBackground(colores[1]);
        cardLayout.show(contGeneral,"Dashboard");
        
    }//GEN-LAST:event_cambioDashboardBtActionPerformed

    private void cambioMovimientoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioMovimientoBtActionPerformed
        reinicarColoresBotones();
        cambioMovimientoBt.setBackground(colores[1]);
        try {
            panelMovimientos.inicializar();
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        cardLayout.show(contGeneral, "Movimientos");
    }//GEN-LAST:event_cambioMovimientoBtActionPerformed

    private void cambioAnalisisBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAnalisisBtActionPerformed
        reinicarColoresBotones();
        cambioAnalisisBt.setBackground(colores[1]);
        try {
            panelAnalisis.inicializar();
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        cardLayout.show(contGeneral, "Analisis");
    }//GEN-LAST:event_cambioAnalisisBtActionPerformed

    private void cambioReporteBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioReporteBtActionPerformed
       reinicarColoresBotones();
       cambioReporteBt.setBackground(colores[1]);
    }//GEN-LAST:event_cambioReporteBtActionPerformed

    private void modosToggleBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modosToggleBtActionPerformed
        if (modosToggleBt.isSelected()) {
            modosToggleBt.setText("Modo Oscuro");
            Color[] modoOscuro = {
                new Color(11,20,1), //Primario
                new Color(24,51,1), //fondos
                new Color(122, 122, 57),//Resalto
                Color.WHITE //Letras
            };
            colores = modoOscuro;
            aplicarColores();
            panelCatalogo.obtenerColor(colores);
            panelCatalogo.cambioModo();
        }else {
            modosToggleBt.setText("Modo Claro");
            Color[] modoClaro = {
                new Color(145,199,87), //Primario
                Color.WHITE, //Fondo
                new Color(219,213,33), //Resalto
                Color.BLACK //Letras
            };
            colores = modoClaro;
            aplicarColores();
            panelCatalogo.obtenerColor(colores);
            panelCatalogo.cambioModo();
            
        }
            
    }//GEN-LAST:event_modosToggleBtActionPerformed

    private void aplicarColores() {
        panelDashboard.setBackground(colores[1]);
        panelCatalogo.setBackground(colores[1]);
        panelOpciones.setBackground(colores[0]);
        reinicarColoresBotones();
        // aquí agregas todos los componentes que quieras actualizar
    }
    public void reinicarColoresBotones(){
        cambioDashboardBt.setBackground(colores[0]);
            cambioDashboardBt.setForeground(colores[3]);
        cambioCatalogoBt.setBackground(colores[0]);
            cambioCatalogoBt.setForeground(colores[3]);
        cambioMovimientoBt.setBackground(colores[0]);
            cambioMovimientoBt.setForeground(colores[3]);
        cambioAnalisisBt.setBackground(colores[0]);
            cambioAnalisisBt.setForeground(colores[3]);
        cambioReporteBt.setBackground(colores[0]);
            cambioReporteBt.setForeground(colores[3]);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cambioAnalisisBt;
    private javax.swing.JButton cambioCatalogoBt;
    private javax.swing.JButton cambioConfiguracionBt;
    private javax.swing.JButton cambioDashboardBt;
    private javax.swing.JButton cambioMovimientoBt;
    private javax.swing.JButton cambioReporteBt;
    private javax.swing.JPanel contGeneral;
    private javax.swing.JToggleButton modosToggleBt;
    private javax.swing.JPanel panelOpciones;
    // End of variables declaration//GEN-END:variables
}
