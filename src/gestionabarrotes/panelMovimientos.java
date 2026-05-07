
package gestionabarrotes;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class panelMovimientos extends javax.swing.JPanel {

    public ArrayList<producto> listaProductos = new ArrayList<>();
    public ArrayList<objetoCategoria> listaCategoria = new ArrayList<>();
    DefaultTableModel modeloStockActual;
    public Color[] colores;
    
    public panelMovimientos(Color[] colores) throws IOException {
        initComponents();
        SpinnerDateModel modeloFecha = new SpinnerDateModel();
        spinnerFecha.setModel(modeloFecha);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
        spinnerFecha.setEditor(editor);
            
        String[] columnasStock = {"Codigo","Nombre","Stock Actual","Stock Minimo","Tipo"};
        modeloStockActual = new DefaultTableModel(columnasStock,0); 
       
        tablaStockActual.setModel(modeloStockActual);
        
        inicializar();
        
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        spinnerFecha = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel2.setText("Tipo de movimiento");

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel3.setText("Fecha de movimiento");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
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
            .addGap(0, 8, Short.MAX_VALUE)
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

        añadirMovimientoBt.setText("Añadir");

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

        finalizarMovimientoBt.setText("Finalizar");

        cancelarMovimientoBt.setText("Cancelar");

        javax.swing.GroupLayout panelRegistroMovimientoLayout = new javax.swing.GroupLayout(panelRegistroMovimiento);
        panelRegistroMovimiento.setLayout(panelRegistroMovimientoLayout);
        panelRegistroMovimientoLayout.setHorizontalGroup(
            panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroMovimientoLayout.createSequentialGroup()
                        .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistroMovimientoLayout.createSequentialGroup()
                                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(cantidadProductoMovimientoTxt)
                                    .addComponent(jLabel7)
                                    .addComponent(codigoProductoMovimientoTxt)
                                    .addComponent(jLabel8)
                                    .addComponent(nombreProductoMovimientoTxt)
                                    .addComponent(añadirMovimientoBt, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroMovimientoLayout.createSequentialGroup()
                                        .addComponent(cancelarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(finalizarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(spinnerFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23))
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(229, 229, 229))))
        );
        panelRegistroMovimientoLayout.setVerticalGroup(
            panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
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
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRegistroMovimientoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadProductoMovimientoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistroMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(añadirMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finalizarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarMovimientoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
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

        jButton1.setText("Consultar Movimiento");

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addGroup(panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelTablaLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
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
                .addComponent(panelRegistroMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 419, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public void inicializar() throws IOException{
        cargarCategorias();
        cargarProductos();
        llenarTablaStock();
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
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel movimientosRealizadosLb;
    private javax.swing.JTextField nombreProductoMovimientoTxt;
    private javax.swing.JPanel panelRegistroMovimiento;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JSpinner spinnerFecha;
    private javax.swing.JTable tablaHistorialMovimientos;
    private javax.swing.JTable tablaMovimientos;
    private javax.swing.JTable tablaStockActual;
    // End of variables declaration//GEN-END:variables
}
