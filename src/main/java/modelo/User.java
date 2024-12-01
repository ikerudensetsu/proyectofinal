/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jacs2
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private String email;
    private String password;
    private String role;
    

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Método para validar las credenciales en la base de datos
    public boolean authenticate() {
        boolean isAuthenticated = false;

        String url = "jdbc:mysql://localhost:3306/deliverydb";
        String userDB = "root"; // Cambia a tu usuario de MySQL
        String passDB = "@12Juan202005"; // Cambia a tu contraseña de MySQL

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión con la base de datos
            Connection conn = DriverManager.getConnection(url, userDB, passDB);

            // Consulta SQL para validar el usuario
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.email);
            stmt.setString(2, this.password);

            // Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery();

            // Si hay un resultado, autenticamos al usuario
            if (rs.next()) {
                this.role = rs.getString("role");
                isAuthenticated = true;
            }

            // Cerramos la conexión
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }
}