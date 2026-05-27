
package gestionabarrotes;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class panelAnalisisInventario extends javax.swing.JPanel {

    DefaultTableModel modeloABC = new DefaultTableModel();
    DefaultTableModel modeloHistorial = new DefaultTableModel();
    public ArrayList<producto> listaProductos = new ArrayList<>();
    public ArrayList<productosVCA> listaVCA = new ArrayList<>();
    public ArrayList<objetoCategoria> listaCategoria = new ArrayList<>();
    private ArrayList<movimientoEncabezado> listaMovimientoEncabezado = new ArrayList<>();
    private ArrayList<movimientoDetalle> listaMovimientoDetalle = new ArrayList<>();
    private double consumoTotal;
    private String codigoDetalleTemp;
    public double costoPedido,costoMantenimiento;
    public int tiempoEntrega;
    
    public panelAnalisisInventario() throws IOException {
        initComponents();
        
        String[] titulosABC = {"Grupo","Codigo","Nombre","Categoria",
                                           "Precio","Stock Actual","Punto Reorden","EQQ","Estado"};
        modeloABC.setColumnIdentifiers(titulosABC);
        tablaABC.setModel(modeloABC);
        
        String[] titulosHistorial = {"Codigo","Nombre","Categoria","Precio","Movimiento","Cantidad"};
        modeloHistorial.setColumnIdentifiers(titulosHistorial);
        tablaHistorial.setModel(modeloHistorial);

        inicializar();
        
        tablaABC.getSelectionModel().addListSelectionListener(e ->{
            int seleccion = tablaABC.getSelectedRow();
            if (seleccion != -1) {
                     codigoDetalleTemp = tablaABC.getValueAt(seleccion,1).toString();
                      busquedaHistorial(codigoDetalleTemp);
                }
        });
        
        busquedaProductoTxt.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) { filtrarTablaVCA();
                }
                @Override
                public void removeUpdate(DocumentEvent e) { filtrarTablaVCA();
                }
                @Override
                public void changedUpdate(DocumentEvent e) { filtrarTablaVCA();
                }       
            });
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                Object estado = tablaABC.getValueAt(row, 8);
                
                if("Punto de Reorden".equals(estado)){
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

                if (column >= 4 && column <= 7) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                }else{
                    setHorizontalAlignment(SwingConstants.LEFT);
                }    
                
                return c;
            }
        };
        
        for(int i = 0; i < tablaABC.getColumnCount();i++){
                  tablaABC.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public void inicializar() throws IOException{
        listaProductos.clear();
        listaCategoria.clear();
        listaMovimientoEncabezado.clear();
        listaMovimientoDetalle.clear();
        cargarConfiguraciones();
        cargarCategorias();
        cargarProductos();
        cargarMovimientoEncabezado();
        cargarMovimientoDetalle();
        calculoConsumoTotal();
        crearObjetoVCA();
        llenarTablaVCA();
        generarGrafica();
    
        resultadoConsumoLb.setText(String.format("$ %.2f", consumoTotal));     
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
                String costoP = record.get("costoPedido");
                costoPedido = Double.parseDouble(costoP);

                String costoM = record.get("costoMantenimiento");
                 costoMantenimiento = Double.parseDouble(costoM);

                String tiempoE = record.get("TiempoEntrega");
                tiempoEntrega = Integer.parseInt(tiempoE);
            }
    }
    private void calculoConsumoTotal(){
        consumoTotal = 0;
        for(movimientoEncabezado movEn : listaMovimientoEncabezado){
                  if(movEn.getTipoMovimiento().equals("Salida")){
                          for(movimientoDetalle movDe : listaMovimientoDetalle){
                                   if(movDe.getEncabezado().getIdentificador() == movEn.getIdentificador()){
                                           for(producto p : listaProductos){
                                                    if(p.getCodigo().equals(movDe.getCodigoProducto())){
                                                        consumoTotal += p.getPrecio() * movDe.getCantidad();
                                                    }
                                           }
                                   }
                          }
                  }
        }
    }
    private void crearObjetoVCA(){
        /*
        Dentro de este método se calcula el consumo total de un producto,
        despues se crea un objeto auxiliar de productoVCA para almacenar 
        los datos VCA y a que grupo pertenece.
        
        Estos objetos se añaden a un arreglo que despues es organizado, 
        dejando el mayor al principio, para que cuando se añadan a la tabla
        estos datos llegen organizados.
        */
        listaVCA.clear();
        modeloABC.setRowCount(0);
        
        for(producto p : listaProductos){
                 double consumoProducto = 0;
                  for(movimientoEncabezado movEn : listaMovimientoEncabezado){
                          if(movEn.getTipoMovimiento().equals("Salida")){
                                   for(movimientoDetalle movDe : listaMovimientoDetalle){
                                           if(movDe.getEncabezado().getIdentificador() == movEn.getIdentificador()
                                                    && movDe.getCodigoProducto().equals(p.getCodigo())){
                                                            consumoProducto += movDe.getCantidad() * p.getPrecio();                                         
                                            }
                                   }
                           }
                  }
                  int puntoReorden = (p.getEstimacionDemanda()/365)*tiempoEntrega;
                  double eqq = Math.sqrt((2*(p.getEstimacionDemanda()*costoPedido))/costoMantenimiento);
                  if(consumoProducto == 0){
                          listaVCA.add(new productosVCA(p,consumoProducto,"Producto sin salidas",puntoReorden,eqq,""));
                  }else{
                      listaVCA.add(new productosVCA(p,consumoProducto,"T",puntoReorden,eqq,""));
                  }
                  
         }
        listaVCA.sort(Comparator.comparingDouble(productosVCA::getVca).reversed());
        
        double acumulacion = 0;
        for(productosVCA pVca : listaVCA){
                if(!pVca.getGrupo().equals("Producto sin salidas")){
                        acumulacion += pVca.getVca()/consumoTotal;
                        String grupo = (acumulacion < 0.8) ? "A" : (acumulacion < 0.95) ? "B" : "C";
                        pVca.setGrupo(grupo);
                }
                
                if(pVca.getP().getStockActual() == 0){
                           pVca.setEstado("Agotado");
                }else if(pVca.getP().getStockActual() < pVca.getPuntoReorden()){
                          pVca.setEstado("Punto de Reorden");
                }else if(pVca.getP().getStockActual( ) >= (pVca.getP().getStockMinimo()*3)){
                          pVca.setEstado("Sobreinventario");
                }else{
                          pVca.setEstado("Normal");
                }
        }
    }
    private void llenarTablaVCA(){
        modeloABC.setRowCount(0);
        for(productosVCA pVca : listaVCA){
                 modeloABC.addRow(new Object[] {pVca.getGrupo()
                                                                         ,pVca.getP().getCodigo()
                                                                         ,pVca.getP().getNombre()
                                                                         ,pVca.getP().getCategoria().getNombre()
                                                                         ,String.format("$ %.2f",pVca.getP().getPrecio())
                                                                         ,pVca.getP().getStockActual()
                                                                         ,pVca.getPuntoReorden()
                                                                         ,String.format("$ %.2f",pVca.getEqq())
                                                                         ,pVca.getEstado()});
        }
    }
    private void filtrarTablaVCA(){
        String parametro = busquedaProductoTxt.getText();
        if(parametro.isEmpty()){
                llenarTablaVCA();
        }else{
                 modeloABC.setRowCount(0);
                 for(productosVCA pVca : listaVCA){
                          if(pVca.getP().getCodigo().contains(parametro)
                                  || pVca.getP().getNombre().contains(parametro)
                                  || pVca.getP().getCategoria().getNombre().contains(parametro)
                                  || pVca.getP().getCategoria().getCodigo().contains(parametro)
                           ){
                                   modeloABC.addRow(new Object[] {pVca.getGrupo()
                                                                         ,pVca.getP().getCodigo()
                                                                         ,pVca.getP().getNombre()
                                                                         ,pVca.getP().getCategoria().getNombre()
                                                                         ,pVca.getP().getPrecio()
                                                                         ,pVca.getP().getStockActual()
                                                                         ,pVca.getPuntoReorden()
                                                                         ,pVca.getEqq()
                                                                         ,pVca.getEstado()});
                          
                          }
                  }
         }
       
    }
    private void generarGrafica(){
        //Creamos un conjunto de datos para la gráfica
        DefaultPieDataset datos = new DefaultPieDataset();
        
        for(productosVCA pVca : listaVCA){
            if(!pVca.getGrupo().equals("Producto sin salidas")){
                  datos.setValue(pVca.getP().getNombre(), pVca.getVca());
            }
        }
        JFreeChart graficaCircular = ChartFactory.createPieChart(
               "Total de Ventas",      //Titulo de la gráfica
               datos,                   //Conjunto de datos a graficar
               true,                    //Mostrar leyenda con colores
               true,                    //Mostrar tooltips
               false                    //No genera URLs
        );
        ChartPanel panelGraficoVentas = new ChartPanel(graficaCircular);
        panelGraficoVentas.setMouseWheelEnabled(false);
        panelGrafica.removeAll();
        panelGrafica.setLayout(new java.awt.BorderLayout());
        panelGrafica.add(panelGraficoVentas,java.awt.BorderLayout.CENTER);
        panelGrafica.revalidate();
        panelGrafica.repaint();
        
    }
    private void cargarMovimientoEncabezado() throws FileNotFoundException, IOException{;
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
    private void cargarMovimientoDetalle() throws FileNotFoundException, IOException{
        listaMovimientoDetalle.clear();
        
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
    private void cargarCategorias() throws FileNotFoundException, IOException{
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
    private void cargarProductos() throws FileNotFoundException, IOException{
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
    private void busquedaHistorial(String codigo){
        modeloHistorial.setRowCount(0);
        for(producto p : listaProductos){
                 if(p.getCodigo().equals(codigo)){
                           for(movimientoEncabezado movEn : listaMovimientoEncabezado){
                                    if(movEn.getTipoMovimiento().equals("Salida")){
                                           for(movimientoDetalle movDe : listaMovimientoDetalle){
                                                    if(movDe.getEncabezado().getIdentificador() == movEn.getIdentificador()
                                                    && movDe.getCodigoProducto().equals(codigo)){
                                                            modeloHistorial.addRow(new Object[]{p.getCodigo()
                                                                                                                         ,p.getNombre()
                                                                                                                         ,p.getCategoria()
                                                                                                                         ,p.getPrecio()
                                                                                                                         ,movEn.getIdentificador() + ": " + movEn.getTipoMovimiento()
                                                                                                                         ,movDe.getCantidad()}
                                                                                                    );
                                                    }
                                           }
                                   }
                           }
                  }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaABC = new javax.swing.JTable();
        busquedaProductoTxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        panelGrafica = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        resultadoConsumoLb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tablaABC.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaABC);

        jPanel2.setBackground(new java.awt.Color(145, 199, 87));
        jPanel2.setForeground(new java.awt.Color(153, 153, 153));

        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaHistorial);

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Historial de Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        panelGrafica.setForeground(new java.awt.Color(153, 153, 153));
        panelGrafica.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Buscar producto");

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Consumo Total:");

        resultadoConsumoLb.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        resultadoConsumoLb.setForeground(new java.awt.Color(0, 0, 0));
        resultadoConsumoLb.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultadoConsumoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(busquedaProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(resultadoConsumoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(busquedaProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busquedaProductoTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JLabel resultadoConsumoLb;
    private javax.swing.JTable tablaABC;
    private javax.swing.JTable tablaHistorial;
    // End of variables declaration//GEN-END:variables
}
