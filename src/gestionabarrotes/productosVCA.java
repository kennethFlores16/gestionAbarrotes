
package gestionabarrotes;

public class productosVCA {
    private producto p;
    private double vca;
    private double porcentaje;
    
    public productosVCA(producto p, double vca, double porcentaje){
         this.p = p;
         this.vca = vca;
         this.porcentaje = porcentaje;
    }
    
    public producto getP() {
        return p;
    }

    public void setP(producto p) {
        this.p = p;
    }

    public double getVca() {
        return vca;
    }

    public void setVca(double vca) {
        this.vca = vca;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
}
