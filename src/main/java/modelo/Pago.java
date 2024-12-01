/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



public class Pago {
    private int idPago;
    private int pedidoId;
    private String metodoPago;
    private double monto;
    private String estadoPago;

    // Constructor para cuando el ID del pago no es necesario (al insertar un nuevo pago)
    public Pago(int pedidoId, String metodoPago, double monto, String estadoPago) {
        this.pedidoId = pedidoId;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
    }

    // Constructor completo con ID del pago (cuando se obtiene de la base de datos)
    public Pago(int idPago, int pedidoId, String metodoPago, double monto, String estadoPago) {
        this.idPago = idPago;
        this.pedidoId = pedidoId;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
    }

    // Getters y Setters
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
