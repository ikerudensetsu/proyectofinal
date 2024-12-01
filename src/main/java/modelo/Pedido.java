/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Map;

public class Pedido {
    private int id;
    private String email;
    private Map<Producto, Integer> productos;
    private double total;

    public Pedido(String email, Map<Producto, Integer> productos, double total) {
        this.email = email;
        this.productos = productos;
        this.total = total;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }
}
