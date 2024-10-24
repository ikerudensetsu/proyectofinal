/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private Map<Producto, Integer> productos = new HashMap<>();
    
public void eliminarProducto(int productoId) {
    productos.entrySet().removeIf(entry -> entry.getKey().getId() == productoId);
}

    // Método para agregar productos al carrito
    public void agregarProducto(Producto producto, int cantidad) {
        if (productos.containsKey(producto)) {
            productos.put(producto, productos.get(producto) + cantidad);
        } else {
            productos.put(producto, cantidad);
        }
    }
    

    // Método para calcular el total del carrito
    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }
    

    // Getter para los productos
    public Map<Producto, Integer> getProductos() {
        return productos;
    }
}