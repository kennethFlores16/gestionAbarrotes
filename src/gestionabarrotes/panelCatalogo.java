
package gestionabarrotes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class panelCatalogo extends javax.swing.JPanel {

    public DefaultTableModel modeloProductos;
    public ArrayList<producto> listaProductos = new ArrayList<>();
    public ArrayList<objetoCategoria> listaCategoria = new ArrayList<>();
    public DefaultComboBoxModel<objetoCategoria> modeloCategorias = new DefaultComboBoxModel<>();
    private javax.swing.JComboBox<objetoCategoria> categoriaProductoCombo;
    public Color[] colores = new Color[4];
    
    public String codigoProducto;
    public boolean soloActivos = true;
    
    public panelCatalogo(Color[] colores) throws IOException {
        initComponents();
        
        categoriaProductoCombo = new javax.swing.JComboBox<>();
        this.colores = colores;
        
        setBackground(colores[1]);
        
        String[] columnas = {"Codigo", "Nombre", "Categoria de Producto","Costo","Precio"
                ,"Stock Actual","Stock Minimo","Tiempo de Entrega","Estimación de Demanda Actual"};
        modeloProductos = new DefaultTableModel(columnas, 0);
        productosTabla.setModel(modeloProductos);
        
        cargarCategorias();
        
        categoriaProductoCombo.setModel(modeloCategorias);
        modeloCategorias.addElement(null);
        
        categoriaProductoCombo.setBounds(420, 45, 180, 30);
        add(categoriaProductoCombo);
        panelBusquedaProducto.add(categoriaProductoCombo);
          
        for(objetoCategoria cat : listaCategoria){
            modeloCategorias.addElement(cat);
        }
        
        llenarTabla(soloActivos);
        if(!soloActivos){
            
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Obtener el producto correspondiente a la fila
                producto p = listaProductos.get(table.convertRowIndexToModel(row));

                    if (!p.isActivo()) {
                        // Producto desactivado → gris
                        c.setBackground(Color.LIGHT_GRAY);
                        c.setForeground(Color.DARK_GRAY);
                    } else {
                        // Producto activo → colores normales
                        if (isSelected) {
                            c.setBackground(table.getSelectionBackground());
                            c.setForeground(table.getSelectionForeground());
                        } else {
                            c.setBackground(Color.WHITE);
                            c.setForeground(Color.BLACK);
                        }
                    }

                    return c;
                    }
                };

            // Aplicar el renderer a todas las columnas
            for (int i = 0; i < productosTabla.getColumnCount(); i++) {
                productosTabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        }
            //Se añade un listener a la tabla, se utiliza una operación lambda para simplificar
                productosTabla.getSelectionModel().addListSelectionListener(e -> {
                    // Habilitar solo si hay una fila seleccionada
                    int fila = productosTabla.getSelectedRow();
                    if (fila != -1) {
                        editarProductoBt.setEnabled(true);
                        codigoProducto = productosTabla.getValueAt(fila,0).toString();
                    }else {
                        editarProductoBt.setEnabled(false);
                    }
            });

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
                System.out.println(listaCategoria.getLast().toString());
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
    
    public void llenarTabla(boolean soloActivos) throws IOException{
        cargarProductos();
    
        for(producto p : listaProductos){
            if(soloActivos){
                if(p.isActivo()){
                    modeloProductos.addRow(new Object[]{
                        p.getCodigo(),
                        p.getNombre(),
                        p.getCategoria().getNombre(),  // nombre visible
                        p.getCosto(),
                        p.getPrecio(),
                        p.getStockActual(),
                        p.getStockMinimo(),
                        p.getTiempoEntrega(),
                        p.getEstimacionDemanda()
                    });
                }
            }else{
                modeloProductos.addRow(new Object[]{
                        p.getCodigo(),
                        p.getNombre(),
                        p.getCategoria().getNombre(),  // nombre visible
                        p.getCosto(),
                        p.getPrecio(),
                        p.getStockActual(),
                        p.getStockMinimo(),
                        p.getTiempoEntrega(),
                        p.getEstimacionDemanda()
                    });
            }
            
        }
    }
    
    public ArrayList<producto> buscarProductos(String codigo, String nombre, objetoCategoria categoria){
        boolean hayFiltro = (codigo != null && !codigo.isEmpty()) ||
                        (nombre != null && !nombre.isEmpty()) ||
                        (categoria != null);

        if (!hayFiltro) {
            return null; // no se pasó ningún criterio, no buscar
        } 
        
        ArrayList<producto> resultados = new ArrayList<>();
        
        for (producto p : listaProductos) {
            boolean coincide = true;

            // Si el parámetro no está vacío, se compara
            if (codigo != null && !codigo.isEmpty()) {
                coincide = coincide && p.getCodigo().equalsIgnoreCase(codigo);
            }
            if (nombre != null && !nombre.isEmpty()) {
                coincide = coincide && p.getNombre().equalsIgnoreCase(nombre);
            }
            if (categoria != null) {
                coincide = coincide && p.getCategoria().getCodigo().equalsIgnoreCase(categoria.getCodigo());
            }
            if (coincide) {
                resultados.add(p);
            }
        }
        return resultados; // si no se encuentra
        
    }
    
    public void obtenerColor(Color[] colores){
        this.colores = colores;
    }
    public void cambioModo(){
        panelBusquedaProducto.setBackground(colores[0]);
        productosTabla.setBackground(colores[0]);
        productosTabla.setForeground(colores[3]);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        añadirProductoBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        productosTabla = new javax.swing.JTable();
        panelBusquedaProducto = new javax.swing.JPanel();
        codigoProductoTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nombreProductoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        buscarProductoBt = new javax.swing.JButton();
        editarProductoBt = new javax.swing.JButton();
        verProductosDesactivadosToggleBt = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(946, 627));

        añadirProductoBt.setBackground(new java.awt.Color(219, 213, 33));
        añadirProductoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        añadirProductoBt.setText("Añadir");
        añadirProductoBt.setBorderPainted(false);
        añadirProductoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirProductoBtActionPerformed(evt);
            }
        });

        productosTabla.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        productosTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(productosTabla);

        panelBusquedaProducto.setBackground(new java.awt.Color(145, 199, 87));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Codigo de Producto");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setText("Nombre de Producto");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setText("Categoria de producto");

        buscarProductoBt.setBackground(new java.awt.Color(219, 213, 33));
        buscarProductoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        buscarProductoBt.setText("Buscar");
        buscarProductoBt.setBorderPainted(false);
        buscarProductoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBusquedaProductoLayout = new javax.swing.GroupLayout(panelBusquedaProducto);
        panelBusquedaProducto.setLayout(panelBusquedaProductoLayout);
        panelBusquedaProductoLayout.setHorizontalGroup(
            panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaProductoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(buscarProductoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelBusquedaProductoLayout.setVerticalGroup(
            panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaProductoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelBusquedaProductoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(36, 36, 36))
                    .addGroup(panelBusquedaProductoLayout.createSequentialGroup()
                        .addGroup(panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombreProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigoProductoTxt)))
                    .addComponent(buscarProductoBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        editarProductoBt.setBackground(new java.awt.Color(219, 213, 33));
        editarProductoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        editarProductoBt.setText("Editar");
        editarProductoBt.setBorderPainted(false);
        editarProductoBt.setEnabled(false);
        editarProductoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarProductoBtActionPerformed(evt);
            }
        });

        verProductosDesactivadosToggleBt.setText("Incluir Productos Desactivados");
        verProductosDesactivadosToggleBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verProductosDesactivadosToggleBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBusquedaProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(verProductosDesactivadosToggleBt)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(añadirProductoBt, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(editarProductoBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(verProductosDesactivadosToggleBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(panelBusquedaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(añadirProductoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editarProductoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void añadirProductoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirProductoBtActionPerformed
    Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
    registroProducto registro;
        try {
            registro = new registroProducto(parent, true,false,"a", modeloProductos,
                    listaProductos,listaCategoria,modeloCategorias,colores);
            registro.setLocationRelativeTo(null);
            registro.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(panelCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_añadirProductoBtActionPerformed

    private void buscarProductoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProductoBtActionPerformed
        
            String codigo = codigoProductoTxt.getText().trim();
            String nombre = nombreProductoTxt.getText();
            objetoCategoria categoria =  (objetoCategoria) categoriaProductoCombo.getSelectedItem();
         if(!codigo.isEmpty() || !nombre.isEmpty() || categoria != null){   
            ArrayList<producto> resultados = buscarProductos(codigo, nombre, categoria);

            if (!resultados.isEmpty()) {
            modeloProductos.setRowCount(0); // limpiar tabla

            for (producto p : resultados) {
                modeloProductos.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getCategoria().getNombre(),
                    p.getCosto(),
                    p.getPrecio(),
                    p.getStockActual(),
                    p.getStockMinimo(),
                    p.getTiempoEntrega(),
                    p.getEstimacionDemanda()
                });
            }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron productos");
            }
        }else{
             JOptionPane.showMessageDialog(null, "Ingrese un dato para la busqueda");
         }
    }//GEN-LAST:event_buscarProductoBtActionPerformed

    private void editarProductoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarProductoBtActionPerformed
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
        
        producto editar = null;
        for(producto p : listaProductos){
            if(p.getCodigo().equals(codigoProducto)){
                editar = p;
            }
        }
        
        registroProducto registro;
        try {
            registro = new registroProducto(parent, true,true,"e", modeloProductos,
                    listaProductos,listaCategoria,modeloCategorias,colores);
            registro.llenarCamposProducto(editar);
            registro.setLocationRelativeTo(null);
            registro.setVisible(true);
            
            modeloProductos.setRowCount(0);
            listaProductos.clear();
            llenarTabla(soloActivos);
            
        } catch (IOException ex) {
            Logger.getLogger(panelCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editarProductoBtActionPerformed

    private void verProductosDesactivadosToggleBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verProductosDesactivadosToggleBtActionPerformed
        soloActivos = !verProductosDesactivadosToggleBt.isSelected();
        
       modeloProductos.setRowCount(0);
       listaProductos.clear();
        
       try {
            llenarTabla(soloActivos);
        } catch (IOException ex) {
            Logger.getLogger(panelCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_verProductosDesactivadosToggleBtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton añadirProductoBt;
    private javax.swing.JButton buscarProductoBt;
    private javax.swing.JTextField codigoProductoTxt;
    private javax.swing.JButton editarProductoBt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreProductoTxt;
    private javax.swing.JPanel panelBusquedaProducto;
    private javax.swing.JTable productosTabla;
    private javax.swing.JToggleButton verProductosDesactivadosToggleBt;
    // End of variables declaration//GEN-END:variables
}
