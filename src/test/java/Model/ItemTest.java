/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item item;

    @BeforeEach
    public void setUp() {
        // Inicializamos un objeto de prueba
        item = new Item("Ceviche", 20.50, 2);
    }

    @Test
    public void testConstructor() {
        assertEquals("Ceviche", item.getNombre());
        assertEquals(20.50, item.getPrecio());
        assertEquals(2, item.getCantidad());
    }

    @Test
    public void testSettersYGetters() {
        item.setNombre("Lomo Saltado");
        item.setPrecio(30.00);
        item.setCantidad(3);

        assertEquals("Lomo Saltado", item.getNombre());
        assertEquals(30.00, item.getPrecio());
        assertEquals(3, item.getCantidad());
    }

    @Test
    public void testGetTotal() {
        double total = item.getTotal();
        assertEquals(41.00, total, 0.01, "El cálculo del total no es correcto.");
    }

    
    
    @Test
    public void testSetCantidadCero() {
        item.setCantidad(0);
        assertEquals(0, item.getCantidad(), "La cantidad debería poder establecerse en 0.");
    }
}
