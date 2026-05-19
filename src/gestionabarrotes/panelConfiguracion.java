/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gestionabarrotes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author JUAN JOSE
 */
public class panelConfiguracion extends javax.swing.JPanel {

    /**
     * Creates new form panelConfiguracion
     */
    public panelConfiguracion() throws IOException {
        initComponents();
        cargarConfiguraciones();
        
       costoPedidoTxt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        costoMantenimientoTxt.requestFocus();
                }    
            }
            
            });
        
        costoMantenimientoTxt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        tiempoEntregaTxt.requestFocus();
                }    
            }
            
            });
        
        tiempoEntregaTxt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        guardarConfiguraciones();
                }    
            }
            
            });
        
    }

    public void cargarConfiguraciones() throws FileNotFoundException, IOException{
            File archivo = new File("assets/configuraciones.csv");

            Reader reader = new FileReader(archivo);
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader("costoPedido","costoMantenimiento","TiempoEntrega")
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = format.parse(reader);

            for (CSVRecord record : records) {
                String costoPedido = record.get("costoPedido");
                double costoP = Double.parseDouble(costoPedido);
                costoPedidoTxt.setText(String.format("%.2f",costoP));

                String costoMantenimiento = record.get("costoMantenimiento");
                double costoM = Double.parseDouble(costoMantenimiento);
                costoMantenimientoTxt.setText(String.format("%.2f",costoM));

                String tiempoEntrega = record.get("TiempoEntrega");
                tiempoEntregaTxt.setText(tiempoEntrega);
            }
    }
    
    public void guardarConfiguraciones(){
        
        double costoPedido = 0;
        double costoMantenimiento = 0;
        int tiempoEntrega = 0;
                 
        try{
            costoPedido = Double.parseDouble(costoPedidoTxt.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Solo puedes ingresar valores númericos");
            costoPedidoTxt.requestFocus();
            return;
        }
         try{
            costoMantenimiento = Double.parseDouble(costoMantenimientoTxt.getText());
         }catch(NumberFormatException e){
             JOptionPane.showMessageDialog(null, "Solo puedes ingresar valores númericos");
             costoMantenimientoTxt.requestFocus();
            return;     
         }
         try{
             tiempoEntrega = Integer.parseInt(tiempoEntregaTxt.getText());
         }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Solo puedes ingresar valores enteros");
            tiempoEntregaTxt.requestFocus();
            return;     
         }
        
            
         
        if(costoPedido > 0 && costoMantenimiento > 0 && tiempoEntrega > 0){
            File archivo = new File("assets/configuraciones.csv");
        
            try (Writer writer = new FileWriter(archivo);
                CSVPrinter printer = new CSVPrinter(writer,
                    CSVFormat.DEFAULT.builder()
                        .setHeader("costoPedido","costoMantenimiento","TiempoEntrega")
                        .build())) {

                   printer.printRecord(costoPedido,costoMantenimiento,tiempoEntrega);

                   JOptionPane.showMessageDialog(null, "Configuración guardada correctamente");

           } catch (IOException e) {
               e.printStackTrace();
           }
        }else{
            JOptionPane.showMessageDialog(null,"Verifica que los valores sean mayores a 0");
        }
        
        

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        costoPedidoTxt = new javax.swing.JTextField();
        costoMantenimientoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tiempoEntregaTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        guardarConfiguracionesBt = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(145, 199, 87));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Configuraciones");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Costo por pedido");

        costoPedidoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        costoMantenimientoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Costo de mantenimiento");

        tiempoEntregaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tiempo de entrega");

        guardarConfiguracionesBt.setBackground(new java.awt.Color(145, 199, 87));
        guardarConfiguracionesBt.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        guardarConfiguracionesBt.setForeground(new java.awt.Color(0, 0, 0));
        guardarConfiguracionesBt.setText("Guardar");
        guardarConfiguracionesBt.setBorderPainted(false);
        guardarConfiguracionesBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarConfiguracionesBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costoPedidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(costoMantenimientoTxt)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tiempoEntregaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(guardarConfiguracionesBt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(costoPedidoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(costoMantenimientoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(tiempoEntregaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
                .addComponent(guardarConfiguracionesBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guardarConfiguracionesBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarConfiguracionesBtActionPerformed
        guardarConfiguraciones();
        try {
            cargarConfiguraciones();
        } catch (IOException ex) {
            Logger.getLogger(panelConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardarConfiguracionesBtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField costoMantenimientoTxt;
    private javax.swing.JTextField costoPedidoTxt;
    private javax.swing.JButton guardarConfiguracionesBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tiempoEntregaTxt;
    // End of variables declaration//GEN-END:variables
}
