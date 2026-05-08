
package gestionabarrotes;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
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
    DefaultTableModel modeloMovimientosAgregar,modeloMovimientosHistorial,modeloStockActual;
    public Color[] colores;
    private int contador;
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
        
        String[] columnasMovimientoHistorial = {"Identificador","Tipo","Fecha","Motivo"};
        modeloMovimientosHistorial = new DefaultTableModel(columnasMovimientoHistorial,0);
        tablaHistorialMovimientos.setModel(modeloMovimientosHistorial);
        
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
        
        codigoProductoMovimientoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String codigo = codigoProductoMovimientoTxt.getText();
                    
                    boolean encontrado = false;

                        for(producto p : listaProductos){
                            if(p.getCodigo().equalsIgnoreCase(codigo)){
                                nombreProductoMovimientoTxt.setText(p.getNombre());
                                cantidadProductoMovimientoTxt.setText("1");
                                encontrado = true;
                                cantidadProductoMovimientoTxt.requestFocus();
                                break;
                            }
                        }

                        if(!encontrado){
                            JOptionPane.showMessageDialog(null,"Este producto no existe");
                            codigoProductoMovimientoTxt.requestFocus();
                            
                        }

                }
            }
        });
        
        motivoMovimientoTxt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    encabezado = new movimientoEncabezado(
                            contador,
                            tipoMovimientoCombo.getSelectedItem().toString(),
                            spinnerFecha.getValue().toString(),
                            motivoMovimientoTxt.getText()
                    );
                    codigoProductoMovimientoTxt.requestFocus();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
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
        jPanel2 = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorialMovimientos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaStockActual = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel6.setText("Detalle de Movimiento");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRegistroMovimientoLayout = new javax.swing.GroupLayout(panelRegistroMovimiento);
        panelRegistroMovimiento.setLayout(panelRegistroMovimientoLayout);
        panelRegistroMovimientoLayout.setHorizontalGroup(
            panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(tipoMovimientoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(spinnerFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(motivoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9)
                                .addComponent(cantidadProductoMovimientoTxt)
                                .addComponent(jLabel7)
                                .addComponent(codigoProductoMovimientoTxt)
                                .addComponent(jLabel8)
                                .addComponent(nombreProductoMovimientoTxt)
                                .addComponent(añadirMovimientoBt, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroMovimientoLayout.createSequentialGroup()
                                        .addComponent(cancelarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(finalizarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroMovimientoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(231, 231, 231)))))
                .addGap(23, 23, 23))
        );
        panelRegistroMovimientoLayout.setVerticalGroup(
            panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
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
                                .addComponent(tipoMovimientoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(motivoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantidadProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(añadirMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finalizarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        panelTabla.setBackground(new java.awt.Color(204, 204, 204));

        tablaHistorialMovimientos.setAutoCreateRowSorter(true);
        tablaHistorialMovimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaHistorialMovimientos);

        jLabel5.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel5.setText("Historial de Movimientos");

        jButton1.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jButton1.setText("Consultar Movimiento");

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addGroup(panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTablaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTablaLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel5)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelTablaLayout.setVerticalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

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

        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel11.setText("Stock Actual");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(236, 236, 236))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(movimientosRealizadosLb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contadorMovimientosLb))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRegistroMovimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movimientosRealizadosLb)
                    .addComponent(contadorMovimientosLb))
                .addGap(12, 12, 12)
                .addComponent(panelRegistroMovimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void motivoMovimientoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motivoMovimientoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motivoMovimientoTxtActionPerformed

    private void añadirMovimientoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirMovimientoBtActionPerformed
        
        if(encabezado !=null && !motivoMovimientoTxt.getText().isEmpty()){
            listaMovimientoDetalleTemporal.add(new movimientoDetalle(
                                   encabezado,
                                   codigoProductoMovimientoTxt.getText(),
                                   Integer.parseInt(cantidadProductoMovimientoTxt.getText())
            ));
            modeloMovimientosAgregar.addRow(new Object[]{
                                            listaMovimientoDetalleTemporal.getLast().getEncabezado().getIdentificador(),
                                            listaMovimientoDetalleTemporal.getLast().getCodigoProducto(),
                                            nombreProductoMovimientoTxt.getText(),
                                            listaMovimientoDetalleTemporal.getLast().getCantidad()});
            codigoProductoMovimientoTxt.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null,"Necesita crear un movimiento antes de añadir un producto");
        }
        
    }//GEN-LAST:event_añadirMovimientoBtActionPerformed

    private void cancelarMovimientoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarMovimientoBtActionPerformed
        
        if(encabezado != null && !motivoMovimientoTxt.getText().isEmpty()){
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar el movimiento?",
            "Confirmar activación", JOptionPane.YES_NO_OPTION);
        if(opcion == JOptionPane.YES_OPTION){
            if(encabezado != null){
                encabezado = null;
                contador = contador - 1;
            }else{
                JOptionPane.showMessageDialog(null, "No existe un encabezado que eliminar");
            }
            listaMovimientoDetalleTemporal.clear();
            limpiarCampos();
            }
        }else{
            JOptionPane.showMessageDialog(null, "No existe un encabezado que eliminar");
        }
        
        
    }//GEN-LAST:event_cancelarMovimientoBtActionPerformed

    private void finalizarMovimientoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarMovimientoBtActionPerformed
        guardarMovimientoEncabezado(encabezado);
        guardarMovimientoDetalle(listaMovimientoDetalleTemporal);
        listaMovimientoDetalleTemporal.clear();
        limpiarCampos();
    }//GEN-LAST:event_finalizarMovimientoBtActionPerformed

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

                // Agregar también a la tabla
                modeloMovimientosHistorial.addRow(new Object[]{
                    movEn.getIdentificador(),
                    movEn.getTipoMovimiento(),
                    movEn.getFecha(),
                    movEn.getMotivo()
                });
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
    public void cargarMovimientoEncabezado() throws FileNotFoundException, IOException{
        modeloMovimientosHistorial.setRowCount(0);
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
                
                modeloMovimientosHistorial.addRow(new Object[]{
                                                    identificador
                                                    ,tipoMovimiento
                                                    ,fecha
                                                    ,motivo       
                                                    });
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
    
    public void cargarProductos() throws FileNotFoundException, IOException{
        File archivo = new File("assets/productos.csv");
        
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
            
            if(p.isActivo()){
                modeloStockActual.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getStockActual(),
                    p.getStockMinimo(),
                    stock
                });
            }
            
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton añadirMovimientoBt;
    private javax.swing.JButton cancelarMovimientoBt;
    private javax.swing.JTextField cantidadProductoMovimientoTxt;
    private javax.swing.JTextField codigoProductoMovimientoTxt;
    private javax.swing.JLabel contadorMovimientosLb;
    private javax.swing.JButton finalizarMovimientoBt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField motivoMovimientoTxt;
    private javax.swing.JLabel movimientosRealizadosLb;
    private javax.swing.JTextField nombreProductoMovimientoTxt;
    private javax.swing.JPanel panelRegistroMovimiento;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JSpinner spinnerFecha;
    private javax.swing.JTable tablaHistorialMovimientos;
    private javax.swing.JTable tablaMovimientos;
    private javax.swing.JTable tablaStockActual;
    private javax.swing.JComboBox<String> tipoMovimientoCombo;
    // End of variables declaration//GEN-END:variables
}
