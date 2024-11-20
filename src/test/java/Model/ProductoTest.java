/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        // Inicializar instancias de Producto para pruebas
        producto1 = new Producto();
        producto2 = new Producto(1, "Ceviche", "Comida", 35.50);
    }

    @Test
    public void testConstructorSinParametros() {
        assertEquals(0, producto1.getId());
        assertNull(producto1.getNombre());
        assertNull(producto1.getTipo());
        assertEquals(0.0, producto1.getPrecio());
    }

    @Test
    public void testConstructorConParametros() {
        assertEquals(1, producto2.getId());
        assertEquals("Ceviche", producto2.getNombre());
        assertEquals("Comida", producto2.getTipo());
        assertEquals(35.50, producto2.getPrecio());
    }

    @Test
    public void testSettersYGetters() {
        producto1.setId(2);
        producto1.setNombre("Tiradito");
        producto1.setTipo("Comida");
        producto1.setPrecio(28.00);

        assertEquals(2, producto1.getId());
        assertEquals("Tiradito", producto1.getNombre());
        assertEquals("Comida", producto1.getTipo());
        assertEquals(28.00, producto1.getPrecio());
    }

   

    @Test
    public void testNombreNoVacio() {
        producto1.setNombre("   ");
        assertTrue(producto1.getNombre().trim().isEmpty(), 
                   "El nombre del producto debería ser considerado vacío");
    }

    @Test
    public void testTipoValido() {
        producto1.setTipo("Postre");
        assertEquals("Postre", producto1.getTipo());

        producto1.setTipo("Bebida");
        assertEquals("Bebida", producto1.getTipo());
    }
}
