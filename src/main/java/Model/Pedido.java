package Model;

public class Pedido {
    private int id;
    private int idUsuario;
    private double total;
    private String fecha;
    private String estado;

    public Pedido(int idUsuario, double total) {
        this.idUsuario = idUsuario;
        this.total = total;
    }

    public Pedido(int id, int idUsuario, double total, String fecha, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.total = total;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
