
package gestionabarrotes;

public class productosVCA {
    private producto p;
    private double vca;
    private String grupo;
    private int puntoReorden;
    private double eqq;
    private String estado;

    public productosVCA(producto p, double vca, String grupo, int puntoReorden, double eqq, String estado) {
        this.p = p;
        this.vca = vca;
        this.grupo = grupo;
        this.puntoReorden = puntoReorden;
        this.eqq = eqq;
        this.estado = estado;
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
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getPuntoReorden() {
        return puntoReorden;
    }

    public void setPuntoReorden(int puntoReorden) {
        this.puntoReorden = puntoReorden;
    }

    public double getEqq() {
        return eqq;
    }

    public void setEqq(double eq) {
        this.eqq = eqq;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
