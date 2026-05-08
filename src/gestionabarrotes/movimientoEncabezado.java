
package gestionabarrotes;

public class movimientoEncabezado {
   private int identificador;
   private String tipoMovimiento;
   private String fecha;
   private String motivo;

    public movimientoEncabezado(int identificador, String tipoMovimiento, String fecha, String motivo) {
        this.identificador = identificador;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.motivo = motivo;
    }
   public String[] toCSV(){
       return new String[]{
           String.valueOf(identificador),
           tipoMovimiento,
           fecha,
           motivo
       };
   } 

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "movimientoEncabezado{" + "identificador=" + identificador + ", tipoMovimiento=" + tipoMovimiento + ", fecha=" + fecha + ", motivo=" + motivo + '}';
    }
    
   
}
