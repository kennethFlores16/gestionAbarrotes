
package gestionabarrotes;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

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
        
        String[] titulos = {"Codigo","Nombre","Tipo Movimiento","Cantidad"};
        modeloMovProductos.setColumnIdentifiers(titulos);
        
        movimientosPorProductoTxt.setModel(modeloMovProductos);
        
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
            for(producto p : listaProductos){
                if(p.getCodigo().contains(detalle.getCodigoProducto())){
                    modeloMovProductos.addRow(new Object[]{
                                                                                detalle.getCodigoProducto()
                                                                                ,p.getNombre()
                                                                                ,detalle.getEncabezado().getTipoMovimiento()
                                                                                ,detalle.getCantidad()});
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
                    if(p.getCodigo().contains(detalle.getCodigoProducto())){
                        if(p.getNombre().contains(parametro) || detalle.getCodigoProducto().contains(parametro)){
                            modeloMovProductos.addRow(new Object[]{
                                                                                    detalle.getCodigoProducto()
                                                                                    ,p.getNombre()
                                                                                    ,detalle.getEncabezado().getTipoMovimiento()
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
        movimientosPorProductoTxt = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel1.setText("Buscar un producto");

        movimientosPorProductoTxt.setAutoCreateRowSorter(true);
        movimientosPorProductoTxt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(movimientosPorProductoTxt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(campoDeBusquedaTxt)))
                .addContainerGap(20, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(234, 234, 234))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
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
    private javax.swing.JTable movimientosPorProductoTxt;
    // End of variables declaration//GEN-END:variables
}
