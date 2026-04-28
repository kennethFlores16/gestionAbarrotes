
package gestionabarrotes;

public class producto {
    private String codigo;
    private String nombre;
    private objetoCategoria categoria;
    private double costo;
    private double precio;
    private int stockActual;
    private int stockMinimo;
    private int tiempoEntrega;
    private int estimacionDemanda;
    private boolean activo;

    public producto(String codigo, String nombre, objetoCategoria categoria, double costo,
                    double precio, int stockActual, int stockMinimo, int tiempoEntrega, int estimacionDemanda, boolean activo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.costo = costo;
        this.precio = precio;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.tiempoEntrega = tiempoEntrega;
        this.estimacionDemanda = estimacionDemanda;
        this.activo = activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public objetoCategoria getCategoria() {
        return categoria;
    }

    public double getCosto() {
        return costo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    public int getEstimacionDemanda() {
        return estimacionDemanda;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(objetoCategoria categoria) {
        this.categoria = categoria;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void setTiempoEntrega(int tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public void setEstimacionDemanda(int estimacionDemanda) {
        this.estimacionDemanda = estimacionDemanda;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String[] toCSV() {
        return new String[]{
            codigo,
            nombre,
            categoria.getCodigo(),
            String.valueOf(costo),
            String.valueOf(precio),
            String.valueOf(stockActual),
            String.valueOf(stockMinimo),
            String.valueOf(tiempoEntrega),
            String.valueOf(estimacionDemanda),
            String.valueOf(activo)
        };
    }

    

}
