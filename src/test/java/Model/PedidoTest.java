/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    private Pedido pedido1;
    private Pedido pedido2;

    @BeforeEach
    public void setUp() {
        // Crear instancias de Pedido antes de cada prueba
        pedido1 = new Pedido(1, 100.50);
        pedido2 = new Pedido(2, 1, 250.75, "2024-11-18", "ENTREGADO");
    }

    @Test
    public void testConstructorSinID() {
        assertEquals(1, pedido1.getIdUsuario());
        assertEquals(100.50, pedido1.getTotal());
        assertNull(pedido1.getFecha());
        assertNull(pedido1.getEstado());
    }

    @Test
    public void testConstructorCompleto() {
        assertEquals(2, pedido2.getId());
        assertEquals(1, pedido2.getIdUsuario());
        assertEquals(250.75, pedido2.getTotal());
        assertEquals("2024-11-18", pedido2.getFecha());
        assertEquals("ENTREGADO", pedido2.getEstado());
    }

    @Test
    public void testSettersYGetters() {
        pedido1.setId(5);
        pedido1.setTotal(150.25);
        pedido1.setFecha("2024-12-01");
        pedido1.setEstado("CANCELADO");

        assertEquals(5, pedido1.getId());
        assertEquals(150.25, pedido1.getTotal());
        assertEquals("2024-12-01", pedido1.getFecha());
        assertEquals("CANCELADO", pedido1.getEstado());
    }

   

    @Test
    public void testEstadoValido() {
        pedido1.setEstado("PENDIENTE");
        assertEquals("PENDIENTE", pedido1.getEstado());

        pedido1.setEstado("EN_PROCESO");
        assertEquals("EN_PROCESO", pedido1.getEstado());
    }

   
}
