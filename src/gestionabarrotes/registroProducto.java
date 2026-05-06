
package gestionabarrotes;
import java.awt.Color;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class registroProducto extends javax.swing.JDialog {

    public ArrayList<objetoCategoria> listaCategoria = new ArrayList<>();
    public ArrayList<producto> listaProductos = new ArrayList<>();
    public DefaultTableModel modeloProductos;
    public DefaultComboBoxModel<objetoCategoria> modeloCategorias = new DefaultComboBoxModel<>();
    private javax.swing.JComboBox<objetoCategoria> categoriaProductoCombo;
    public String funcionBotonRegistrar;
    public Color[] colores;
    
    public boolean estado = true;
    
    public registroProducto(java.awt.Frame parent, boolean modal, boolean botonDesactivar,
            String funcionBotonRegistrar
            ,DefaultTableModel modeloProductos
            ,ArrayList<producto> listaProductos
            ,ArrayList<objetoCategoria> listaCategoria
            ,DefaultComboBoxModel<objetoCategoria> modeloCategorias
            ,Color[] colores) throws IOException {
        
        super(parent, modal);
        
        this.colores = colores;
        initComponents();
        categoriaProductoCombo = new javax.swing.JComboBox<>();
           
        this.modeloCategorias = modeloCategorias;
        this.modeloProductos = modeloProductos;
        this.listaProductos = listaProductos;
        this.listaCategoria = listaCategoria;
        this.funcionBotonRegistrar = funcionBotonRegistrar;
        
        desactivarProductoBt.setVisible(botonDesactivar);
        if(botonDesactivar){
            tituloVentanaLb.setText("Editar Producto");
            registarProductoBt.setText("Guardar Cambios");
        }else{
            tituloVentanaLb.setText("Registrar Producto");
            registarProductoBt.setText("Registrar Producto");
        }
        codigoProductoTxt.setEditable(!botonDesactivar);
        
        categoriaProductoCombo.setModel(modeloCategorias);
        
        categoriaProductoCombo.setBounds(20, 126, 200, 40);
        add(categoriaProductoCombo);
        jPanel2.add(categoriaProductoCombo);
        
        System.out.println(funcionBotonRegistrar);
        
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        tituloVentanaLb = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        codigoProductoTxt = new javax.swing.JTextField();
        codigoProductoLb = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        costoProductoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        precioProductoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        stockActualProductoTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        stockMinimoProductoTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tiempoEntregaProductoTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        estimacionDemandaProductoTxt = new javax.swing.JTextField();
        registarProductoBt = new javax.swing.JButton();
        desactivarProductoBt = new javax.swing.JButton();
        nombreProductoTxt = new javax.swing.JTextField();
        nombreProductoLb = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(145, 199, 87));

        tituloVentanaLb.setFont(new java.awt.Font("Poppins SemiBold", 0, 36)); // NOI18N
        tituloVentanaLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloVentanaLb.setText("Registrar Producto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(tituloVentanaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(tituloVentanaLb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        codigoProductoLb.setText("Codigo del Producto");

        jLabel2.setText("Categoría del Producto");

        costoProductoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel3.setText("Costo");

        jLabel4.setText("Precio");

        precioProductoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel5.setText("Stock Actual");

        stockActualProductoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("Stock Minimo");

        stockMinimoProductoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setText("Tiempo de Entrega (En dias)");

        tiempoEntregaProductoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setText("Estimación de Demanda Actual");

        estimacionDemandaProductoTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        registarProductoBt.setBackground(new java.awt.Color(219, 213, 33));
        registarProductoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        registarProductoBt.setText("Registrar Producto");
        registarProductoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registarProductoBtActionPerformed(evt);
            }
        });

        desactivarProductoBt.setBackground(new java.awt.Color(255, 0, 0));
        desactivarProductoBt.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        desactivarProductoBt.setForeground(new java.awt.Color(255, 255, 255));
        desactivarProductoBt.setText("Desactivar Producto");
        desactivarProductoBt.setBorderPainted(false);
        desactivarProductoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desactivarProductoBtActionPerformed(evt);
            }
        });

        nombreProductoLb.setText("Nombre del Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(desactivarProductoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registarProductoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(stockActualProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(stockMinimoProductoTxt)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costoProductoTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(tiempoEntregaProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(estimacionDemandaProductoTxt)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigoProductoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreProductoTxt)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nombreProductoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoProductoLb)
                    .addComponent(nombreProductoLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombreProductoTxt)
                    .addComponent(codigoProductoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(costoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(precioProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockActualProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockMinimoProductoTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tiempoEntregaProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estimacionDemandaProductoTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desactivarProductoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registarProductoBt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registarProductoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registarProductoBtActionPerformed

        
        boolean validacionVacio = validarCampos();
        
        if(validacionVacio){
            boolean validacionNumerico = validarCamposNumericos();
            if(validacionNumerico){
                
                boolean existe = false;
                
                String codigo = codigoProductoTxt.getText().trim();
                String nombre = nombreProductoTxt.getText();
                objetoCategoria seleccionado = (objetoCategoria) categoriaProductoCombo.getSelectedItem();
                double costo = Double.parseDouble(costoProductoTxt.getText());
                double precio = Double.parseDouble(precioProductoTxt.getText());
                int stockActual = Integer.parseInt(stockActualProductoTxt.getText());
                int stockMinimo = Integer.parseInt(stockMinimoProductoTxt.getText());
                int tiempoEntrega = Integer.parseInt(tiempoEntregaProductoTxt.getText());
                int estimacionDemanda = Integer.parseInt(estimacionDemandaProductoTxt.getText());

                if(funcionBotonRegistrar.equals("a")){
                    
                    for(producto p : listaProductos){
                        if(p.getCodigo().equals(codigo)){
                            existe = true;
                            break;
                        }
                    }  
                    if(existe){
                        JOptionPane.showMessageDialog(null,"El codigo: " + codigo + " ya existe, pruebe otro");
                    }else{
                        
                        if (seleccionado == null) {
                            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoria");
                            return;
                        }
                        
                        if (costo <= 0) {
                            JOptionPane.showMessageDialog(this, "El costo debe ser mayor a 0");
                            return;
                        }

                        if (precio <= 0) {
                            JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0");
                            return;
                        }

                        if (costo >= precio) {
                            int opcion = JOptionPane.showConfirmDialog(null, "El costo es mayor que el precio de venta ¿Esta seguro que desea registrar este precio?",
                            "Confirmar registro", JOptionPane.YES_NO_OPTION);
                            if(opcion == JOptionPane.NO_OPTION){
                                return;
                            }
                        }    
                           

                        if (stockMinimo <= 0) {
                            JOptionPane.showMessageDialog(this, "El stock mínimo debe ser mayor a 0");
                            return;
                        }

                        if (tiempoEntrega < 1) {
                            JOptionPane.showMessageDialog(this, "Los días de entrega deben ser al menos 1");
                            return;
                        }
                        
                        if (estimacionDemanda <= 0) {
                            JOptionPane.showMessageDialog(this, "La Estimacion de la demanda debe ser mayor a 0");
                            return;
                        }
                        
                        listaProductos.add(new producto(codigo,nombre,seleccionado,costo,precio,stockActual,
                                       stockMinimo,tiempoEntrega,estimacionDemanda,true));
                        guardarDatos(listaProductos.getLast());
                        }
                }else{ 
                    if (seleccionado == null) {
                            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoria");
                            return;
                        }
                        
                        if (costo <= 0) {
                            JOptionPane.showMessageDialog(this, "El costo debe ser mayor a 0");
                            return;
                        }

                        if (precio <= 0) {
                            JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0");
                            return;
                        }

                        if (costo >= precio) {
                            int opcion = JOptionPane.showConfirmDialog(null, "El costo es mayor que el precio de venta ¿Esta seguro que desea registrar este precio?",
                            "Confirmar registro", JOptionPane.YES_NO_OPTION);
                            if(opcion == JOptionPane.NO_OPTION){
                                return;
                            }
                            
                        }

                        if (stockMinimo <= 0) {
                            JOptionPane.showMessageDialog(this, "El stock mínimo debe ser mayor a 0");
                            return;
                        }

                        if (tiempoEntrega < 1) {
                            JOptionPane.showMessageDialog(this, "Los días de entrega deben ser al menos 1");
                            return;
                        }
                        
                        if (estimacionDemanda <= 0) {
                            JOptionPane.showMessageDialog(this, "La Estimacion de la demanda debe ser mayor a 0");
                            return;
                        }
                        
                    for(producto p : listaProductos){
                            if(p.getCodigo().equals(codigo)){
                                p.setCodigo(codigo);
                                p.setNombre(nombre);
                                p.setCategoria(seleccionado);
                                p.setCosto(costo);
                                p.setPrecio(precio);
                                p.setStockActual(stockActual);
                                p.setStockMinimo(stockMinimo);
                                p.setTiempoEntrega(tiempoEntrega);
                                p.setEstimacionDemanda(estimacionDemanda);
                            }        
                        }
                    JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente");
                    actualizarDato(listaProductos);
                    dispose();
                }
            }
        }   
    }//GEN-LAST:event_registarProductoBtActionPerformed

    private void desactivarProductoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desactivarProductoBtActionPerformed
        
        if(estado){
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea desactivar?",
            "Confirmar desactivación", JOptionPane.YES_NO_OPTION);
            if(opcion == JOptionPane.YES_OPTION){
                for(producto p : listaProductos){
                    if(p.getCodigo().equals(codigoProductoTxt.getText().trim())){
                        System.out.println(p.getCodigo());
                        p.setActivo(false);
                    }
                }
               actualizarDato(listaProductos);
               dispose(); 
            }
        }else{
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea activar?",
            "Confirmar activación", JOptionPane.YES_NO_OPTION);
            if(opcion == JOptionPane.YES_OPTION){
                for(producto p : listaProductos){
                    if(p.getCodigo().equals(codigoProductoTxt.getText().trim())){
                        System.out.println(p.getCodigo());
                        p.setActivo(true);
                    }
                }
               actualizarDato(listaProductos);
               dispose(); 
            }
        }

    }//GEN-LAST:event_desactivarProductoBtActionPerformed
    
    public void guardarDatos(producto p){
        File archivo = new File("assets/productos.csv");

        boolean append = archivo.exists(); // si ya existe, agregamos

            try (Writer writer = new FileWriter(archivo, true);
                 CSVPrinter printer = append
                     ? new CSVPrinter(writer, CSVFormat.DEFAULT) // sin encabezado
                     : new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                             .setHeader("codigo","nombre","categoria","costo","precio",
                                        "stockActual","stockMinimo","tiempoEntrega","estimacionDemanda","activo")
                             .build())) {

                // Escribir registros usando tu método toCSV()
                printer.printRecord((Object[]) p.toCSV());

                // Agregar también a la tabla
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
                JOptionPane.showMessageDialog(null, "Producto regisrado exitosamente");
                limpiarCampos();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    public void actualizarDato(ArrayList<producto> lista){
        
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

    
    public void limpiarCampos(){
        codigoProductoTxt.setText("");
        nombreProductoTxt.setText("");
        categoriaProductoCombo.setSelectedIndex(0);
        costoProductoTxt.setText("");
        precioProductoTxt.setText("");
        stockActualProductoTxt.setText("");
        stockMinimoProductoTxt.setText("");
        tiempoEntregaProductoTxt.setText("");
        estimacionDemandaProductoTxt.setText("");
    }
    
    public void llenarCamposProducto(producto p){
        codigoProductoTxt.setText(p.getCodigo());
        nombreProductoTxt.setText(p.getNombre());
        categoriaProductoCombo.setSelectedItem(p.getCategoria());
        costoProductoTxt.setText(String.format("%.2f",p.getCosto()));
        precioProductoTxt.setText(String.format("%.2f",p.getPrecio()));
        stockActualProductoTxt.setText(String.valueOf(p.getStockActual()));
        stockMinimoProductoTxt.setText(String.valueOf(p.getStockMinimo()));
        tiempoEntregaProductoTxt.setText(String.valueOf(p.getTiempoEntrega()));
        estimacionDemandaProductoTxt.setText(String.valueOf(p.getEstimacionDemanda()));
        
        estado = p.isActivo();
        
        if(estado){
            desactivarProductoBt.setText("Desactivar Producto");
            desactivarProductoBt.setBackground(Color.RED);
        }else{
            desactivarProductoBt.setText("Activar Producto");
            desactivarProductoBt.setBackground(colores[0]);
            
        }
        
    }
    private boolean validarCampos() {
        // Validar que no estén vacíos
        if (codigoProductoTxt.getText().isEmpty() ||
            nombreProductoTxt.getText().isEmpty() ||
            categoriaProductoCombo == null ||
            costoProductoTxt.getText().isEmpty() ||
            precioProductoTxt.getText().isEmpty() ||
            stockActualProductoTxt.getText().isEmpty() ||
            stockMinimoProductoTxt.getText().isEmpty() ||
            tiempoEntregaProductoTxt.getText().isEmpty() ||
            estimacionDemandaProductoTxt.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        return true; // todo válido
    }
    
    private boolean validarCamposNumericos(){
        // Validar numéricos
        if (!costoProductoTxt.getText().matches("\\d+(\\.\\d+)?") ||
            !precioProductoTxt.getText().matches("\\d+(\\.\\d+)?") ||
            !stockActualProductoTxt.getText().matches("\\d+(\\.\\d+)?") ||
            !stockMinimoProductoTxt.getText().matches("\\d+(\\.\\d+)?") ||
            !tiempoEntregaProductoTxt.getText().matches("\\d+(\\.\\d+)?")||
            !estimacionDemandaProductoTxt.getText().matches("\\d+(\\.\\d+)?")) {

            JOptionPane.showMessageDialog(this, "Los campos numéricos deben contener solo números");
            return false;
        }
        return true;
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel codigoProductoLb;
    private javax.swing.JTextField codigoProductoTxt;
    private javax.swing.JTextField costoProductoTxt;
    private javax.swing.JButton desactivarProductoBt;
    private javax.swing.JTextField estimacionDemandaProductoTxt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombreProductoLb;
    private javax.swing.JTextField nombreProductoTxt;
    private javax.swing.JTextField precioProductoTxt;
    private javax.swing.JButton registarProductoBt;
    private javax.swing.JTextField stockActualProductoTxt;
    private javax.swing.JTextField stockMinimoProductoTxt;
    private javax.swing.JTextField tiempoEntregaProductoTxt;
    private javax.swing.JLabel tituloVentanaLb;
    // End of variables declaration//GEN-END:variables
}
