//en esta clase harmeos consulta a la tabla productos y mostraremos por consola los datos de todos los productos almacenados en la tabla.
//Importamos lo necesario para manejar BBDD MySQL.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class Ejercicio2_4 {
    public static void main (String[] args) {

        // Datos de la conexión a la base de datos MySQL
        String url = "jdbc:mysql://localhost:3306/TiendaPc";
        String user = "root";
        String password = "B@se1234Datos";

        // Intentamos conectar
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión establecida correctamente.");
            // Creamos el objeto Statement para ejecutar sentencias SQL
            Statement st = con.createStatement();
            // Preparamos la sentencia SQL para consultar todos los productos
            String sql = "SELECT * FROM productos";
            // Ejecutamos la consulta y obtenemos el ResultSet
            ResultSet rs = st.executeQuery(sql);
            // Recorremos el ResultSet y mostramos los datos de cada producto
            System.out.println("Lista de productos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreProduct = rs.getString("nombreProduct");
                String fabricante = rs.getString("fabricante");
                System.out.println("ID: " + id + ", Nombre: " + nombreProduct + ", Fabricante: " + fabricante);
            }
            // Cerramos el ResultSet, el Statement y la conexión
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o consultar datos: " + e.getMessage());
        }
    }
}
