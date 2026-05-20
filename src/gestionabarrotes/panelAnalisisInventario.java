
package gestionabarrotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class panelAnalisisInventario extends javax.swing.JPanel {

    DefaultTableModel modeloABC = new DefaultTableModel();
    public ArrayList<producto> listaProductos = new ArrayList<>();
    public ArrayList<productosVCA> listaVCA = new ArrayList<>();
    public ArrayList<objetoCategoria> listaCategoria = new ArrayList<>();
    private ArrayList<movimientoEncabezado> listaMovimientoEncabezado = new ArrayList<>();
    private ArrayList<movimientoDetalle> listaMovimientoDetalle = new ArrayList<>();
    private double consumoTotal;
    
    public panelAnalisisInventario() throws IOException {
        initComponents();
        
        String[] titulosABC = {"Grupo","Codigo","Nombre","Categoria",
                                           "Precio","Stock Actual","Punto Reorden","EQQ"};
        modeloABC.setColumnIdentifiers(titulosABC);
        tablaABC.setModel(modeloABC);

        inicializar();
    }

    public void inicializar() throws IOException{
        listaProductos.clear();
        listaCategoria.clear();
        listaMovimientoEncabezado.clear();
        listaMovimientoDetalle.clear();
        cargarCategorias();
        cargarProductos();
        cargarMovimientoEncabezado();
        cargarMovimientoDetalle();
        calculoConsumoTotal();
        llenarVCA();
        
        resultadoConsumoLb.setText(String.format("$ %.2f", consumoTotal));     
    }
    public void calculoConsumoTotal(){
        consumoTotal = 0;
       
        for(movimientoEncabezado movEn : listaMovimientoEncabezado){
                  if(movEn.getTipoMovimiento().equals("Salida")){
                          for(movimientoDetalle movDe : listaMovimientoDetalle){
                                   if(movDe.getEncabezado().getIdentificador() == movEn.getIdentificador()){
                                           System.out.println(movDe.getCodigoProducto());
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
    public void llenarVCA(){
        listaVCA.clear();
        modeloABC.setRowCount(0);
        for(producto p : listaProductos){
                double consumoProducto = p.getEstimacionDemanda() * p.getPrecio();
                double porcentaje = consumoProducto/consumoTotal;
                listaVCA.add(new productosVCA(p,consumoProducto,porcentaje));
         }
        listaVCA.sort(Comparator.comparingDouble(productosVCA::getVca).reversed());
        
        for(productosVCA pVca : listaVCA){
                 modeloABC.addRow(new Object[] {pVca.getVca()
                                                                         ,pVca.getP().getCodigo()
                                                                         ,pVca.getP().getNombre()
                                                                         ,pVca.getP().getCategoria().getNombre()
                                                                         ,pVca.getP().getPrecio()
                                                                         ,pVca.getP().getStockActual()
                                                                         ,"",""});
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
        jPanel1 = new javax.swing.JPanel();
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

        jPanel1.setBackground(new java.awt.Color(145, 199, 87));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Consumo Total:");

        resultadoConsumoLb.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        resultadoConsumoLb.setForeground(new java.awt.Color(0, 0, 0));
        resultadoConsumoLb.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultadoConsumoLb)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(resultadoConsumoLb))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(308, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel resultadoConsumoLb;
    private javax.swing.JTable tablaABC;
    // End of variables declaration//GEN-END:variables
}
