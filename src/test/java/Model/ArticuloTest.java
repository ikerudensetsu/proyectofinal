/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArticuloTest {

    private Articulo articulo;

    @BeforeEach
    public void setUp() {
        // Inicializamos un objeto de prueba
        articulo = new Articulo(101, 5);
    }

    @Test
    public void testConstructor() {
        assertEquals(101, articulo.getCodigoProducto(), "El código del producto debería ser 101.");
        assertEquals(5, articulo.getCantidad(), "La cantidad debería ser 5.");
    }

    @Test
    public void testSettersYGetters() {
        articulo.setCodigoProducto(202);
        articulo.setCantidad(10);

        assertEquals(202, articulo.getCodigoProducto(), "El código del producto debería ser 202.");
        assertEquals(10, articulo.getCantidad(), "La cantidad debería ser 10.");
    }

    @Test
    public void testSetCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            articulo.setCantidad(-3);
        }, "Se esperaba una excepción para una cantidad negativa.");
    }

    @Test
    public void testSetCantidadCero() {
        articulo.setCantidad(0);
        assertEquals(0, articulo.getCantidad(), "La cantidad debería poder establecerse en 0.");
    }
}
