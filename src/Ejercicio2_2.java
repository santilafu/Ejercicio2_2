// Importamos las librerías necesarias para la conexión a la base de datos MySQL
import java.sql.*;

public class Ejercicio2_2 {
    public static void main(String[] args) {

        // Datos de la conexión a la base de datos MySQL
        String url = "jdbc:mysql://localhost:3306/TiendaPc";
        String user = "root";
        String password = "B@se1234Datos";

        // Intentamos conectar y crear la tabla
        try {
            //  Establecemos la conexión
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión establecida correctamente.");

            //  Creamos el objeto Statement para ejecutar sentencias SQL
            Statement st = con.createStatement();

            // Creamos la tabla 'productos' si no existe
            String sql = "CREATE TABLE IF NOT EXISTS productos (" +
                    "id INT PRIMARY KEY, " +
                    "nombreProduct VARCHAR(40), " +
                    "fabricante VARCHAR(30)" +
                    ")";

            // Ejecutamos la sentencia SQL
            st.executeUpdate(sql);
            System.out.println("✅ Tabla 'productos' creada correctamente.");

            // Cerramos la conexión y el statement
            st.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o crear la tabla: " + e.getMessage());
        }
    }
}
