/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;

    @BeforeEach
    public void setUp() {
        // Inicializar instancias de Usuario para pruebas
        usuario1 = new Usuario("john.doe@example.com", "password123");
        usuario2 = new Usuario("Jane Doe", "jane.doe@example.com", "pass456", "Calle Falsa 123", "987654321");
        usuario3 = new Usuario(1, "Alice", "alice@example.com", "Av. Siempre Viva", "912345678");
    }

    @Test
    public void testConstructorEmailPassword() {
        assertEquals("john.doe@example.com", usuario1.getEmail());
        assertEquals("password123", usuario1.getPassword());
    }

    @Test
    public void testConstructorCompleto() {
        assertEquals("Jane Doe", usuario2.getNombre());
        assertEquals("jane.doe@example.com", usuario2.getEmail());
        assertEquals("pass456", usuario2.getPassword());
        assertEquals("Calle Falsa 123", usuario2.getDireccion());
        assertEquals("987654321", usuario2.getTelefono());
    }

    @Test
    public void testConstructorConID() {
        assertEquals(1, usuario3.getId());
        assertEquals("Alice", usuario3.getNombre());
        assertEquals("alice@example.com", usuario3.getEmail());
        assertEquals("Av. Siempre Viva", usuario3.getDireccion());
        assertEquals("912345678", usuario3.getTelefono());
    }

    @Test
    public void testSettersYGetters() {
        Usuario usuario = new Usuario();

        usuario.setId(10);
        usuario.setNombre("Carlos Perez");
        usuario.setEmail("carlos.perez@example.com");
        usuario.setPassword("securePass");
        usuario.setDireccion("Av. Los Pinos 234");
        usuario.setTelefono("9988776655");
        usuario.setRol("ADMIN");

        assertEquals(10, usuario.getId());
        assertEquals("Carlos Perez", usuario.getNombre());
        assertEquals("carlos.perez@example.com", usuario.getEmail());
        assertEquals("securePass", usuario.getPassword());
        assertEquals("Av. Los Pinos 234", usuario.getDireccion());
        assertEquals("9988776655", usuario.getTelefono());
        assertEquals("ADMIN", usuario.getRol());
    }

    @Test
    public void testSetEmailInvalido() {
        Usuario usuario = new Usuario();
        usuario.setEmail("email_invalido");
        assertNotEquals("email_valido@example.com", usuario.getEmail(), 
                "El email no debería ser considerado válido");
    }

    @Test
    public void testRol() {
        usuario1.setRol("USER");
        assertEquals("USER", usuario1.getRol());

        usuario2.setRol("ADMIN");
        assertEquals("ADMIN", usuario2.getRol());
    }

   

    @Test
    public void testDireccionVacia() {
        usuario1.setDireccion("");
        assertEquals("", usuario1.getDireccion(), 
                "Dirección del usuario vacía debería dar un campo vacío");
    }

}
