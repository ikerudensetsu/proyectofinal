/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PagoTest {

    private Pago pagoNuevo; // Pago sin ID asignado (nuevo registro)
    private Pago pagoExistente; // Pago con ID asignado (obtenido de la base de datos)

    @BeforeEach
    public void setUp() {
        // Inicializamos las instancias para las pruebas
        pagoNuevo = new Pago(101, "Tarjeta", 150.75, "Pendiente");
        pagoExistente = new Pago(1, 101, "Efectivo", 120.50, "Completado");
    }

    @Test
    public void testConstructorSinIdPago() {
        assertEquals(101, pagoNuevo.getPedidoId());
        assertEquals("Tarjeta", pagoNuevo.getMetodoPago());
        assertEquals(150.75, pagoNuevo.getMonto());
        assertEquals("Pendiente", pagoNuevo.getEstadoPago());
    }

    @Test
    public void testConstructorConIdPago() {
        assertEquals(1, pagoExistente.getIdPago());
        assertEquals(101, pagoExistente.getPedidoId());
        assertEquals("Efectivo", pagoExistente.getMetodoPago());
        assertEquals(120.50, pagoExistente.getMonto());
        assertEquals("Completado", pagoExistente.getEstadoPago());
    }

    @Test
    public void testSettersYGetters() {
        Pago pago = new Pago(202, "Transferencia", 200.00, "Pendiente");
        pago.setIdPago(10);
        pago.setPedidoId(102);
        pago.setMetodoPago("Tarjeta");
        pago.setMonto(180.00);
        pago.setEstadoPago("Procesando");

        assertEquals(10, pago.getIdPago());
        assertEquals(102, pago.getPedidoId());
        assertEquals("Tarjeta", pago.getMetodoPago());
        assertEquals(180.00, pago.getMonto());
        assertEquals("Procesando", pago.getEstadoPago());
    }

    
    @Test
    public void testMetodoPagoInvalido() {
        pagoNuevo.setMetodoPago("Cheque");
        assertEquals("Cheque", pagoNuevo.getMetodoPago(), 
            "El método de pago debería permitir 'Cheque' como entrada.");
    }

    @Test
    public void testEstadoPagoValido() {
        pagoExistente.setEstadoPago("Rechazado");
        assertEquals("Rechazado", pagoExistente.getEstadoPago(), 
            "El estado del pago debería cambiar a 'Rechazado'.");
    }
}

