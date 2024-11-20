package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/deliverydb"; // Cambia "tu_base_de_datos"
    private static final String USER = "root";  // Cambia "tu_usuario"
    private static final String PASSWORD = "@12Juan202005";  // Cambia "tu_contraseña"
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Asegúrate de usar el conector correcto

    // Método para obtener una conexión
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(DRIVER); // Cargar el driver de MySQL
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // Establecer conexión
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
            throw e; // Lanza la excepción para ser manejada en otros lugares
        }
        return conn;
    }

    // Método para cerrar la conexión y el PreparedStatement
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
