/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CarritoTest {

    private Carrito carrito;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        // Inicializamos el carrito y algunos ítems de prueba
        carrito = new Carrito();
        item1 = new Item("Ceviche", 20.00, 2); // Total: 40.00
        item2 = new Item("Lomo Saltado", 30.00, 1); // Total: 30.00
    }

    @Test
    public void testAgregarItem() {
        carrito.agregarItem(item1);
        carrito.agregarItem(item2);

        List<Item> items = carrito.getItems();
        assertEquals(2, items.size(), "El carrito debería contener 2 ítems.");
        assertTrue(items.contains(item1), "El carrito debería contener el ítem 'Ceviche'.");
        assertTrue(items.contains(item2), "El carrito debería contener el ítem 'Lomo Saltado'.");
    }

    @Test
    public void testEliminarItem() {
        carrito.agregarItem(item1);
        carrito.agregarItem(item2);

        carrito.eliminarItem("Ceviche");

        List<Item> items = carrito.getItems();
        assertEquals(1, items.size(), "El carrito debería contener 1 ítem tras la eliminación.");
        assertFalse(items.contains(item1), "El ítem 'Ceviche' debería haber sido eliminado.");
    }

    @Test
    public void testGetTotalCarrito() {
        carrito.agregarItem(item1);
        carrito.agregarItem(item2);

        double totalCarrito = carrito.getTotalCarrito();
        assertEquals(70.00, totalCarrito, 0.01, "El total del carrito debería ser 70.00.");
    }

    @Test
    public void testEliminarItemInexistente() {
        carrito.agregarItem(item1);

        carrito.eliminarItem("Lomo Saltado"); // No existe en el carrito

        List<Item> items = carrito.getItems();
        assertEquals(1, items.size(), "El carrito debería seguir conteniendo 1 ítem.");
        assertTrue(items.contains(item1), "El ítem 'Ceviche' debería permanecer en el carrito.");
    }

    @Test
    public void testCarritoVacio() {
        assertEquals(0, carrito.getItems().size(), "El carrito debería estar vacío inicialmente.");
        assertEquals(0.00, carrito.getTotalCarrito(), 0.01, "El total del carrito vacío debería ser 0.00.");
    }
}

