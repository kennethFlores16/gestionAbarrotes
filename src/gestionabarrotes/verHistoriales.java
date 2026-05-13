
package gestionabarrotes;

import java.util.List;
import java.util.ArrayList;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class verHistoriales extends javax.swing.JDialog {

    DefaultTableModel modeloMovProductos = new DefaultTableModel();
    public ArrayList<producto> listaProductos = new ArrayList<>();
    private ArrayList<movimientoEncabezado> listaMovEn= new ArrayList<>();
    private ArrayList<movimientoDetalle> listaMovDe = new ArrayList<>();
    
    public verHistoriales(java.awt.Frame parent, boolean modal,
                                     ArrayList<producto> listaProductos,
                                     ArrayList<movimientoEncabezado> listaMovEn,
                                     ArrayList<movimientoDetalle> listaMovDe) {
        super(parent, modal);
       
        this.listaProductos = listaProductos;
        this.listaMovEn = listaMovEn;
        this.listaMovDe = listaMovDe;
        
        initComponents();
        
        String[] titulos = {"Fecha","Encabezado","Codigo","Nombre","Tipo Movimiento","Cantidad"};
        modeloMovProductos.setColumnIdentifiers(titulos);
        
        movimientosPorProductoTabla.setModel(modeloMovProductos);
        TableRowSorter<DefaultTableModel> sorter  = new TableRowSorter<>(modeloMovProductos);
        movimientosPorProductoTabla.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        llenarTabla();
        
        campoDeBusquedaTxt.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) { filtrarTabla();
                }
                @Override
                public void removeUpdate(DocumentEvent e) { filtrarTabla();
                }
                @Override
                public void changedUpdate(DocumentEvent e) { filtrarTabla();
                }       
            });
    }
    
    public void llenarTabla(){
        modeloMovProductos.setRowCount(0);
        for(movimientoDetalle detalle : listaMovDe){
            System.out.println(detalle.toString());
            for(producto p : listaProductos){
                if(p.getCodigo().trim().equals(detalle.getCodigoProducto().trim())){
                    String tipoMovimiento = detalle.getEncabezado().getTipoMovimiento() + ": " + detalle.getEncabezado().getMotivo();
                    modeloMovProductos.addRow(new Object[]{detalle.getEncabezado().getFecha()
                                                                                ,detalle.getEncabezado().getIdentificador()
                                                                                ,detalle.getCodigoProducto()
                                                                                ,p.getNombre()
                                                                                ,tipoMovimiento
                                                                                ,detalle.getCantidad()});
                    break;
                }
            }
            
        }
    }
   public void filtrarTabla(){
       String parametro = campoDeBusquedaTxt.getText();
       
       if(parametro.isEmpty()){
           llenarTabla();
       }else{
           modeloMovProductos.setRowCount(0);
            for(movimientoDetalle detalle : listaMovDe){
                for(producto p : listaProductos){
                   if(p.getCodigo().trim().equals(detalle.getCodigoProducto().trim())){
                        if(p.getNombre().trim().contains(parametro) || detalle.getCodigoProducto().trim().contains(parametro)){
                             String tipoMovimiento = "";
                            if(detalle.getEncabezado().getTipoMovimiento().equals("Ajuste")){
                                tipoMovimiento = detalle.getEncabezado().getTipoMovimiento() + ": " + detalle.getEncabezado().getMotivo();
                            }else{
                                tipoMovimiento = detalle.getEncabezado().getTipoMovimiento();
                            }
                            modeloMovProductos.addRow(new Object[]{detalle.getEncabezado().getFecha()
                                                                                        ,detalle.getEncabezado().getIdentificador()
                                                                                        ,detalle.getCodigoProducto()
                                                                                        ,p.getNombre()
                                                                                        ,tipoMovimiento
                                                                                        ,detalle.getCantidad()});
                        } 
                    }
                }

            }
       }
       
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoDeBusquedaTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        movimientosPorProductoTabla = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel1.setText("Buscar un producto");

        movimientosPorProductoTabla.setAutoCreateRowSorter(true);
        movimientosPorProductoTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(movimientosPorProductoTabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeBusquedaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDeBusquedaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(145, 199, 87));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel3.setText("Historial de Movimientos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoDeBusquedaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable movimientosPorProductoTabla;
    // End of variables declaration//GEN-END:variables
}
