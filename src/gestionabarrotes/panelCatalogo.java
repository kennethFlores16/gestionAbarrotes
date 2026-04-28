
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class panelCatalogo extends javax.swing.JPanel {

    public DefaultTableModel modeloProductos;
    public ArrayList<producto> listaProductos = new ArrayList<>();
    public ArrayList<objetoCategoria> listaCategoria = new ArrayList<>();
    public DefaultComboBoxModel<objetoCategoria> modeloCategorias = new DefaultComboBoxModel<>();
    public Color[] colores = new Color[4];
    
    public String codigoProducto;
    public boolean soloActivos = true;
    
    public panelCatalogo(Color[] colores) throws IOException {
        initComponents();
        this.colores = colores;
        
        setBackground(colores[1]);
        
        String[] columnas = {"Codigo", "Nombre", "Categoria","Costo","Precio"
                ,"Stock Actual","Stock Minimo","Entrega","Estimación"};
        modeloProductos = new DefaultTableModel(columnas, 0);
        productosTabla.setModel(modeloProductos);
        
        cargarCategorias();
        modeloCategorias.addElement(null);
          
        for(objetoCategoria cat : listaCategoria){
            modeloCategorias.addElement(cat);
        }
        
        llenarTabla(soloActivos);
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
                
            busquedaProductoTxt.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) { try {
                    filtrarTabla();
                    } catch (IOException ex) {
                        Logger.getLogger(panelCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
}
                @Override
                public void removeUpdate(DocumentEvent e) { try {
                    filtrarTabla();
                    } catch (IOException ex) {
                        Logger.getLogger(panelCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
}
                @Override
                public void changedUpdate(DocumentEvent e) { try {
                    filtrarTabla();
                    } catch (IOException ex) {
                        Logger.getLogger(panelCatalogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        modeloProductos.setRowCount(0);

        listaProductos.clear();
        cargarProductos();
    
        for(producto p : listaProductos){
            if(soloActivos){
                if(p.isActivo()){
                    modeloProductos.addRow(new Object[]{
                        p.getCodigo(),
                        p.getNombre(),
                        p.getCategoria().getNombre(),  // nombre visible
                        String.format("%.2f",p.getCosto()),
                        String.format("%.2f",p.getPrecio()),
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
                        String.format("%.2f",p.getCosto()),
                        String.format("%.2f",p.getPrecio()),
                        p.getStockActual(),
                        p.getStockMinimo(),
                        p.getTiempoEntrega(),
                        p.getEstimacionDemanda()
                    });
            }
            
        }
    }
    
    public ArrayList<producto> buscarProductos(String codigo, boolean soloActivos){
      
        ArrayList<producto> resultados = new ArrayList<>();
        
        for (producto p : listaProductos) {
            boolean coincide = true;

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
    
    private void filtrarTabla() throws IOException {
    String texto = busquedaProductoTxt.getText();
    
    modeloProductos.setRowCount(0);
    
    if (texto.isEmpty()) {
        // mostrar todos
        llenarTabla(soloActivos);
    } else {
        ArrayList<producto> filtrados = new ArrayList<>();
        for (producto p : listaProductos) {
            if (p.getCodigo().contains(texto)
                || p.getNombre().contains(texto)
                || p.getCategoria().getCodigo().contains(texto)
                || p.getCategoria().getNombre().contains(texto)){
                filtrados.add(p);
            }
        }
        for (producto p : filtrados) {
            modeloProductos.addRow(new Object[]{
                        p.getCodigo(),
                        p.getNombre(),
                        p.getCategoria().getNombre(),  // nombre visible
                        String.format("%.2f",p.getCosto()),
                        String.format("%.2f",p.getPrecio()),
                        p.getStockActual(),
                        p.getStockMinimo(),
                        p.getTiempoEntrega(),
                        p.getEstimacionDemanda()
                    });
            }
        
    }
}
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        añadirProductoBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        productosTabla = new javax.swing.JTable();
        panelBusquedaProducto = new javax.swing.JPanel();
        busquedaProductoTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
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

        productosTabla.setAutoCreateRowSorter(true);
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

        busquedaProductoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaProductoTxtActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Codigo, Nombre o Categoría de Producto");

        javax.swing.GroupLayout panelBusquedaProductoLayout = new javax.swing.GroupLayout(panelBusquedaProducto);
        panelBusquedaProducto.setLayout(panelBusquedaProductoLayout);
        panelBusquedaProductoLayout.setHorizontalGroup(
            panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaProductoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(busquedaProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        panelBusquedaProductoLayout.setVerticalGroup(
            panelBusquedaProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaProductoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(busquedaProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verProductosDesactivadosToggleBt)
                            .addComponent(panelBusquedaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(añadirProductoBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(31, Short.MAX_VALUE))
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
            modeloProductos.setRowCount(0);
            llenarTabla(soloActivos);
        } catch (IOException ex) {
            Logger.getLogger(panelCatalogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_verProductosDesactivadosToggleBtActionPerformed

    private void busquedaProductoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaProductoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busquedaProductoTxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton añadirProductoBt;
    private javax.swing.JTextField busquedaProductoTxt;
    private javax.swing.JButton editarProductoBt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBusquedaProducto;
    private javax.swing.JTable productosTabla;
    private javax.swing.JToggleButton verProductosDesactivadosToggleBt;
    // End of variables declaration//GEN-END:variables
}
