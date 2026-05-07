
package gestionabarrotes;

public class movimientoDetalle {
    private movimientoEncabezado encabezado;
    private String codigoProducto;
    private int cantidad;
    
    public movimientoDetalle(movimientoEncabezado encabezado,
            String codigoProducto, int cantidad){
        this.encabezado = encabezado;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }

    public movimientoEncabezado getEncabezado() {
        return encabezado;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setEncabezado(movimientoEncabezado encabezado) {
        this.encabezado = encabezado;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
