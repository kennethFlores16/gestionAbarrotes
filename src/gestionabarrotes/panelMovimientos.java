
package gestionabarrotes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class panelMovimientos extends javax.swing.JPanel {

    public ArrayList<producto> listaProductos = new ArrayList<>();
    public ArrayList<objetoCategoria> listaCategoria = new ArrayList<>();
    private ArrayList<movimientoEncabezado> listaMovimientoEncabezado = new ArrayList<>();
    private ArrayList<movimientoDetalle> listaMovimientoDetalle = new ArrayList<>();
    private ArrayList<movimientoDetalle> listaMovimientoDetalleTemporal  = new ArrayList<>();
    DefaultTableModel modeloMovimientosAgregar,modeloStockActual;
    public Color[] colores;
    private int contador,identificadorEn,filaSeleccionada;
    private String codigoDetalleTemp;
    private movimientoEncabezado encabezado;
    
    public panelMovimientos(Color[] colores) throws IOException {
        initComponents();
        SpinnerDateModel modeloFecha = new SpinnerDateModel();
        spinnerFecha.setModel(modeloFecha);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
        spinnerFecha.setEditor(editor);
            
        String[] columnasMovimientoAgregar = {"Movimiento","Codigo","Nombre","Cantidad"};
        modeloMovimientosAgregar = new DefaultTableModel(columnasMovimientoAgregar,0);
        tablaMovimientos.setModel(modeloMovimientosAgregar);
        
        String[] columnasStock = {"Codigo","Nombre","Stock Actual","Stock Minimo","Tipo"};
        modeloStockActual = new DefaultTableModel(columnasStock,0); 
        tablaStockActual.setModel(modeloStockActual);
        
        inicializar();
        cargarMovimientoEncabezado();
        cargarMovimientoDetalle();
        
        contador = listaMovimientoEncabezado.size();
        contadorMovimientosLb.setText(String.valueOf(contador));
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                Object estado = tablaStockActual.getValueAt(row, 4);

                if("Bajo".equals(estado)){
                    c.setBackground(new Color(219,213,33));
                    c.setForeground(Color.black);                    
                }else if("Agotado".equals(estado)){
                    c.setBackground(Color.red);
                    c.setForeground(Color.black);  
                }else if("Sobreinventario".equals(estado)){
                    c.setBackground(Color.DARK_GRAY);
                    c.setForeground(Color.white);
                }else{
                    c.setBackground(Color.white);
                    c.setForeground(Color.black);  
                }

                if (column >= 2 && column <= 3) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                }else{
                    setHorizontalAlignment(SwingConstants.LEFT);
                }    
                
                return c;
            }
        };

        for (int i = 0; i < tablaStockActual.getColumnCount(); i++) {
            tablaStockActual.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        
        tablaMovimientos.getSelectionModel().addListSelectionListener(e -> {
                // Habilitar solo si hay una fila seleccionada
                int fila = tablaMovimientos.getSelectedRow();
                if (fila != -1) {
                    eliminarDetalleBt.setEnabled(true);
                    codigoDetalleTemp = tablaMovimientos.getValueAt(fila,1).toString();
                }else {
                    eliminarDetalleBt.setEnabled(false);
                }
            });
 
        codigoProductoMovimientoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String codigo = codigoProductoMovimientoTxt.getText().trim();
                    boolean existe = false;
                    
                        for(producto p : listaProductos){
                            if(p.getCodigo().equals(codigo)){
                                existe = true;
                                    nombreProductoMovimientoTxt.setText(p.getNombre());
                                    cantidadProductoMovimientoTxt.setText("1");
                                    cantidadProductoMovimientoTxt.requestFocus();
                                    
                                    break;
                            }   
                        }
                        if(!existe){
                            JOptionPane.showMessageDialog(null,"Este producto no existe");
                        }

                }
                
            }
        });
        
        cantidadProductoMovimientoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    añadirMovimiento();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        movimientosRealizadosLb = new javax.swing.JLabel();
        contadorMovimientosLb = new javax.swing.JLabel();
        panelRegistroMovimiento = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tipoMovimientoCombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        spinnerFecha = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        motivoMovimientoTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        codigoProductoMovimientoTxt = new javax.swing.JTextField();
        nombreProductoMovimientoTxt = new javax.swing.JTextField();
        cantidadProductoMovimientoTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        añadirMovimientoBt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMovimientos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        finalizarMovimientoBt = new javax.swing.JButton();
        cancelarMovimientoBt = new javax.swing.JButton();
        eliminarDetalleBt = new javax.swing.JButton();
        abritHistorialesBt = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaStockActual = new javax.swing.JTable();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setBackground(new java.awt.Color(255, 255, 255));

        movimientosRealizadosLb.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        movimientosRealizadosLb.setText("Movimientos Realizados:");

        contadorMovimientosLb.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        contadorMovimientosLb.setText("jLabel2");

        panelRegistroMovimiento.setBackground(new java.awt.Color(145, 199, 87));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel1.setText("Registrar Movimiento");

        tipoMovimientoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Salida", "Ajuste" }));

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel2.setText("Tipo de movimiento");

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel3.setText("Fecha de movimiento");

        motivoMovimientoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motivoMovimientoTxtActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel4.setText("Motivo de Movimiento");

        jLabel6.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel6.setText("Detalle de Movimiento");

        codigoProductoMovimientoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoProductoMovimientoTxtActionPerformed(evt);
            }
        });

        nombreProductoMovimientoTxt.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel7.setText("Codigo Producto");

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel8.setText("Nombre");

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel9.setText("Cantidad");

        añadirMovimientoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        añadirMovimientoBt.setText("Añadir");
        añadirMovimientoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirMovimientoBtActionPerformed(evt);
            }
        });

        tablaMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaMovimientos);

        jLabel10.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel10.setText("Movimientos por Agregar");

        finalizarMovimientoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        finalizarMovimientoBt.setText("Finalizar");
        finalizarMovimientoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarMovimientoBtActionPerformed(evt);
            }
        });

        cancelarMovimientoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        cancelarMovimientoBt.setText("Cancelar");
        cancelarMovimientoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarMovimientoBtActionPerformed(evt);
            }
        });

        eliminarDetalleBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        eliminarDetalleBt.setText("Eliminar Detalle");
        eliminarDetalleBt.setEnabled(false);
        eliminarDetalleBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarDetalleBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRegistroMovimientoLayout = new javax.swing.GroupLayout(panelRegistroMovimiento);
        panelRegistroMovimiento.setLayout(panelRegistroMovimientoLayout);
        panelRegistroMovimientoLayout.setHorizontalGroup(
            panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(tipoMovimientoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(spinnerFecha)))
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(eliminarDetalleBt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(cancelarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(finalizarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(añadirMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cantidadProductoMovimientoTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                .addComponent(codigoProductoMovimientoTxt, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel7))
                        .addGap(20, 20, 20)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(nombreProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(motivoMovimientoTxt))
                .addGap(20, 20, 20))
        );
        panelRegistroMovimientoLayout.setVerticalGroup(
            panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoMovimientoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(motivoMovimientoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantidadProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(añadirMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finalizarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarDetalleBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        abritHistorialesBt.setBackground(new java.awt.Color(219, 213, 33));
        abritHistorialesBt.setForeground(new java.awt.Color(0, 0, 0));
        abritHistorialesBt.setText("Historial");
        abritHistorialesBt.setBorderPainted(false);
        abritHistorialesBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abritHistorialesBtActionPerformed(evt);
            }
        });

        tablaStockActual.setAutoCreateRowSorter(true);
        tablaStockActual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tablaStockActual);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(abritHistorialesBt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(movimientosRealizadosLb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contadorMovimientosLb)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelRegistroMovimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movimientosRealizadosLb)
                    .addComponent(contadorMovimientosLb)
                    .addComponent(abritHistorialesBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRegistroMovimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void motivoMovimientoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motivoMovimientoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motivoMovimientoTxtActionPerformed

    private void añadirMovimientoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirMovimientoBtActionPerformed
        
                añadirMovimiento();
    }//GEN-LAST:event_añadirMovimientoBtActionPerformed

    private void cancelarMovimientoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarMovimientoBtActionPerformed
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar el movimiento?",
            "Confirmar activación", JOptionPane.YES_NO_OPTION);
            if(opcion == JOptionPane.YES_OPTION){
                encabezado = null;
                contador = contador - 1;
                listaMovimientoDetalleTemporal.clear();
                limpiarCampos();
            }
    }//GEN-LAST:event_cancelarMovimientoBtActionPerformed

    private void finalizarMovimientoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarMovimientoBtActionPerformed
       if(!listaMovimientoDetalleTemporal.isEmpty()){
                if(tipoMovimientoCombo.getSelectedItem().toString().equals("Ajuste")){
                        if(!motivoMovimientoTxt.getText().isEmpty()){
                            encabezado = new movimientoEncabezado(
                                        contador,
                                        tipoMovimientoCombo.getSelectedItem().toString(),
                                        spinnerFecha.getValue().toString(),
                                        motivoMovimientoTxt.getText()
                                );
                           for(movimientoDetalle detalle : listaMovimientoDetalleTemporal){
                                    detalle.setEncabezado(encabezado);
                           }
                           finalizarGuardado();
                        }else{
                            JOptionPane.showMessageDialog(null, "Para hacer un ajuste  es necesario añadir un motivo");
                        }
            } else{
                encabezado = new movimientoEncabezado(
                                contador,
                                tipoMovimientoCombo.getSelectedItem().toString(),
                                spinnerFecha.getValue().toString(),
                                motivoMovimientoTxt.getText()
                        );
                  for(movimientoDetalle detalle : listaMovimientoDetalleTemporal){
                        detalle.setEncabezado(encabezado);
                   }
                  finalizarGuardado();
            }
       }else{
           JOptionPane.showMessageDialog(null, "No hay detalles que añadir");
       }
         
    }//GEN-LAST:event_finalizarMovimientoBtActionPerformed

    private void codigoProductoMovimientoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoProductoMovimientoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoProductoMovimientoTxtActionPerformed

    private void eliminarDetalleBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarDetalleBtActionPerformed
        listaMovimientoDetalleTemporal.removeIf(
                movDe -> movDe.getCodigoProducto().equals(codigoDetalleTemp)
        );
        modeloMovimientosAgregar.removeRow(filaSeleccionada);
    }//GEN-LAST:event_eliminarDetalleBtActionPerformed

    private void abritHistorialesBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abritHistorialesBtActionPerformed
        try {
            cargarMovimientoEncabezado();
            cargarMovimientoDetalle();
        } catch (IOException ex) {
            Logger.getLogger(panelMovimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
        verHistoriales interfaz = new verHistoriales(parent
            ,true
            ,listaProductos
            ,listaMovimientoEncabezado
            ,listaMovimientoDetalle);
        
        System.out.println("Encabezados: " + listaMovimientoEncabezado.size());
System.out.println("Detalles: " + listaMovimientoDetalle.size());

        interfaz.setLocationRelativeTo(null);
        interfaz.setVisible(true);
    }//GEN-LAST:event_abritHistorialesBtActionPerformed

    public void inicializar() throws IOException{
        cargarCategorias();
        cargarProductos();
        llenarTablaStock();
    }
    public void limpiarCampos(){
        tipoMovimientoCombo.setSelectedIndex(0);
        spinnerFecha.setValue(new Date());
        motivoMovimientoTxt.setText("");
        codigoProductoMovimientoTxt.setText("");
        nombreProductoMovimientoTxt.setText("");
        cantidadProductoMovimientoTxt.setText("");
        
        modeloMovimientosAgregar.setRowCount(0);
    }
    private void añadirMovimiento(){
        if(!codigoProductoMovimientoTxt.getText().isEmpty()
                         && !nombreProductoMovimientoTxt.getText().isEmpty()
                         && !cantidadProductoMovimientoTxt.getText().isEmpty()){
                
                          listaMovimientoDetalleTemporal.add(new movimientoDetalle(
                                           null,
                                           codigoProductoMovimientoTxt.getText(),
                                           Integer.parseInt(cantidadProductoMovimientoTxt.getText())
                  ));                      
                           modeloMovimientosAgregar.addRow(new Object[]{
                                                        contador,
                                                        listaMovimientoDetalleTemporal.getLast().getCodigoProducto(),
                                                        nombreProductoMovimientoTxt.getText(),
                                                        listaMovimientoDetalleTemporal.getLast().getCantidad()});

                          codigoProductoMovimientoTxt.setText("");
                          nombreProductoMovimientoTxt.setText("");
                          cantidadProductoMovimientoTxt.setText("");
                          codigoProductoMovimientoTxt.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null, "Debes llenar todas los campos de detalle");
                }
    }
    public void guardarMovimientoEncabezado(movimientoEncabezado movEn){
        System.out.println(contador);
        File archivo = new File("assets/movimientoEncabezado.csv");

        boolean append = archivo.exists() && archivo.length() > 0;  // si ya existe, agregamos

            try (Writer writer = new FileWriter(archivo, true);
                 CSVPrinter printer = append
                     ? new CSVPrinter(writer, CSVFormat.DEFAULT) // sin encabezado
                     : new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                             .setHeader("identificador","tipoMovimiento","fecha","motivo")
                             .build())) {

                // Escribir registros usando tu método toCSV()
                printer.printRecord((Object[]) movEn.toCSV());
                listaMovimientoEncabezado.add(movEn);

                contador = listaMovimientoEncabezado.size();
                contadorMovimientosLb.setText(String.valueOf(contador));
                System.out.println(contador);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void guardarMovimientoDetalle(ArrayList<movimientoDetalle> lista){
        File archivo = new File("assets/movimientoDetalle.csv");

        boolean append = archivo.exists() && archivo.length() > 0; // si ya existe, agregamos

            try (Writer writer = new FileWriter(archivo, true);
                 CSVPrinter printer = append
                     ? new CSVPrinter(writer, CSVFormat.DEFAULT) // sin encabezado
                     : new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                             .setHeader("movimientoEncabezado","codigoProducto","cantidad")
                             .build())) {

                for(movimientoDetalle mov : lista){
                // Escribir registros usando tu método toCSV()
                printer.printRecord((Object[]) mov.toCSV());
                
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    private void finalizarGuardado(){
        if(encabezado != null && !listaMovimientoDetalleTemporal.isEmpty()){
            boolean avanzar = procesarMovimiento(encabezado,listaMovimientoDetalleTemporal);
            if(avanzar){guardarMovimientoEncabezado(encabezado);
                guardarMovimientoDetalle(listaMovimientoDetalleTemporal);
                listaMovimientoDetalleTemporal.clear();
                limpiarCampos();
                encabezado = null;
                JOptionPane.showMessageDialog(null,"Datos almacenados correctamente");

                try {
                    llenarTablaStock();
                } catch (IOException ex) {
                    Logger.getLogger(panelMovimientos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"No hay registro que guardar aún");
        }
    }
    public void cargarMovimientoEncabezado() throws FileNotFoundException, IOException{;
        listaMovimientoEncabezado.clear();
        
        File archivo = new File("assets/movimientoEncabezado.csv");
        
        Reader reader = new FileReader(archivo);
        CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader("identificador","tipoMovimiento","fecha","motivo")
                    .setSkipHeaderRecord(true)
                    .build();
        
        Iterable<CSVRecord> records = format.parse(reader);
        
        for (CSVRecord record : records) {
                int identificador = Integer.parseInt(record.get("identificador"));
                String tipoMovimiento = record.get("tipoMovimiento");
                String fecha = record.get("fecha");
                String motivo = record.get("motivo");
                listaMovimientoEncabezado.add(new movimientoEncabezado(
                                                    identificador
                                                    ,tipoMovimiento
                                                    ,fecha
                                                    ,motivo));
            }
        
    }
    public void cargarMovimientoDetalle() throws FileNotFoundException, IOException{
        File archivo = new File("assets/movimientoDetalle.csv");
        
        Reader reader = new FileReader(archivo);
        CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader("movimientoEncabezado","codigoProducto","cantidad")
                    .setSkipHeaderRecord(true)
                    .build();
        
        Iterable<CSVRecord> records = format.parse(reader);
        
        for (CSVRecord record : records) {
                int movimientoEncabezado = Integer.parseInt(record.get("movimientoEncabezado"));
                String codigoProducto = record.get("codigoProducto");
                int cantidad = Integer.parseInt(record.get("cantidad"));
   
                movimientoEncabezado movEn = listaMovimientoEncabezado.get(movimientoEncabezado);

                listaMovimientoDetalle.add(new movimientoDetalle(
                                            movEn
                                            ,codigoProducto
                                            ,cantidad
                                            ));
            }
        
    }
    public void cargarCategorias() throws FileNotFoundException, IOException{
            File archivo = new File("assets/abarrotes.csv");

            Reader reader = new FileReader(archivo);
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader("Codigo","Nombre")
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = format.parse(reader);

            for (CSVRecord record : records) {
                String codigo = record.get("Codigo");
                String nombre = record.get("Nombre");
                listaCategoria.add(new objetoCategoria(codigo,nombre));
            }
    }
    private boolean procesarMovimiento(movimientoEncabezado movEn,ArrayList<movimientoDetalle> lista){
        if(movEn.getTipoMovimiento().equalsIgnoreCase("Entrada")){
            for(movimientoDetalle movDe : lista){
                String codigo = movDe.getCodigoProducto();
                for(producto p : listaProductos){
                    if(p.getCodigo().equalsIgnoreCase(codigo)){
                        if(p.isActivo()){
                            int stockActual = p.getStockActual();
                        p.setStockActual(stockActual + movDe.getCantidad());
                        break;
                        }else{
                            JOptionPane.showMessageDialog(null, "Solo puede realizar ajustes a productos desactivados");
                            return false;
                        }
                        
                    }
                }
            }
            guardarMovimientos(listaProductos);
            return true;
        }else if(movEn.getTipoMovimiento().equalsIgnoreCase("Salida")){
            for(movimientoDetalle movDe : lista){
                String codigo = movDe.getCodigoProducto();
                for(producto p : listaProductos){
                    if(p.getCodigo().equalsIgnoreCase(codigo)){
                        if(p.isActivo()){
                            int stockActual = p.getStockActual();
                            if(stockActual >= movDe.getCantidad()){
                                p.setStockActual(stockActual - movDe.getCantidad());
                                break;
                            }else{
                               JOptionPane.showMessageDialog(null,"Comprueba que el stock de los productos sea suficiente");
                               return false;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Solo puede realizar ajustes a productos desactivados");
                            return false;
                        }
                       
                    }
                }
            }
            guardarMovimientos(listaProductos);
            return true;
        }else if(movEn.getTipoMovimiento().equalsIgnoreCase("Ajuste")){
            for(movimientoDetalle movDe : lista){
                String codigo = movDe.getCodigoProducto();
                for(producto p : listaProductos){
                    if(p.getCodigo().equalsIgnoreCase(codigo)){
                        p.setStockActual(movDe.getCantidad());
                        break;
                    }
                }
            }
            guardarMovimientos(listaProductos);
            return true;
        }
        return false;
    }
    public void cargarProductos() throws FileNotFoundException, IOException{
        File archivo = new File("assets/productos.csv");
        listaMovimientoDetalle.clear();
        
        Reader reader = new FileReader(archivo);
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("codigo", "nombre", "categoria","costo","precio"
                ,"stockActual","stockMinimo","tiempoEntrega","estimacionDemanda","activo")
                .setSkipHeaderRecord(true)
                .build();
        
        Iterable<CSVRecord> records = format.parse(reader);

        for (CSVRecord record : records) {
            String codigo = record.get("codigo");
            String nombre = record.get("nombre");
            String categoria = record.get("categoria");
            double costo = Double.parseDouble(record.get("costo"));
            double precio = Double.parseDouble(record.get("precio"));
            int stockActual = Integer.parseInt(record.get("stockActual"));
            int stockMinimo = Integer.parseInt(record.get("stockMinimo"));
            int tiempoEntrega = Integer.parseInt(record.get("tiempoEntrega"));
            int estimacionDemanda = Integer.parseInt(record.get("estimacionDemanda"));
            boolean activo = Boolean.parseBoolean(record.get("activo"));
            
            objetoCategoria categoriaProducto = null;
            
            for (objetoCategoria cat : listaCategoria) {
                if (cat.getCodigo().equals(categoria)) {
                    categoriaProducto = cat;
                    break;
                }
            }
            
            listaProductos.add(new producto(codigo,nombre,categoriaProducto,costo,
            precio,stockActual,stockMinimo,tiempoEntrega,estimacionDemanda,activo));
          
        }
    }
    public void llenarTablaStock() throws IOException{
        modeloStockActual.setRowCount(0);
        listaProductos.clear();
        cargarProductos();
        
        for(producto p : listaProductos){
            String stock = "";
            
            if(p.getStockMinimo() > p.getStockActual() && p.getStockActual()> 0){
                stock = "Bajo";
            }else if(p.getStockActual() == 0){
                stock = "Agotado";
            }else if(p.getStockActual() >= (p.getStockMinimo()*3)){
                stock = "Sobreinventario";
            }else{
                stock = "Normal";
            }
            
                modeloStockActual.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getStockActual(),
                    p.getStockMinimo(),
                    stock
                });
            
        }
        
    }
    public void guardarMovimientos(ArrayList<producto> lista){
        
        File archivo = new File("assets/productos.csv");
        
        try (Writer writer = new FileWriter(archivo);
            CSVPrinter printer = new CSVPrinter(writer,
                CSVFormat.DEFAULT.builder()
                    .setHeader("codigo","nombre","categoria","costo","precio",
                               "stockActual","stockMinimo","tiempoEntrega","estimacionDemanda","activo")
                    .build())) {

           // Escribir todos los productos
           for (producto p : lista) {
               printer.printRecord((Object[]) p.toCSV());
           }
           limpiarCampos();

       } catch (IOException e) {
           e.printStackTrace();
       }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abritHistorialesBt;
    private javax.swing.JButton añadirMovimientoBt;
    private javax.swing.JButton cancelarMovimientoBt;
    private javax.swing.JTextField cantidadProductoMovimientoTxt;
    private javax.swing.JTextField codigoProductoMovimientoTxt;
    private javax.swing.JLabel contadorMovimientosLb;
    private javax.swing.JButton eliminarDetalleBt;
    private javax.swing.JButton finalizarMovimientoBt;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField motivoMovimientoTxt;
    private javax.swing.JLabel movimientosRealizadosLb;
    private javax.swing.JTextField nombreProductoMovimientoTxt;
    private javax.swing.JPanel panelRegistroMovimiento;
    private javax.swing.JSpinner spinnerFecha;
    private javax.swing.JTable tablaMovimientos;
    private javax.swing.JTable tablaStockActual;
    private javax.swing.JComboBox<String> tipoMovimientoCombo;
    // End of variables declaration//GEN-END:variables
}
