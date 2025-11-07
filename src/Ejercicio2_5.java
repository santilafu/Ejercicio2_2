/* Juan desea automatizar la obtención de la información de su clientela, incluyendo su NIF, nombre y teléfono.
Para lograrlo, busca implementar un procedimiento que le permita obtener esta información con una sola llamada,
agilizando así el proceso de obtención de datos de su clientela.
 */
// Importamos lo necesario para manejar BBDD MySQL.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class Ejercicio2_5 {
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
            // Preparamos la sentencia SQL para llamar al procedimiento almacenado
            String sql = "CALL listadoClientes()";
            // Ejecutamos la consulta y obtenemos el ResultSet
            ResultSet rs = st.executeQuery(sql);
            // Recorremos el ResultSet y mostramos los datos de cada cliente
            System.out.println("Lista de clientela:");
            while (rs.next()) {
                String nif = rs.getString("NIF");
                String nombre = rs.getString("Nombre");
                String telefono = rs.getString("Telefono");
                System.out.println("NIF: " + nif + ", Nombre: " + nombre + ", Teléfono: " + telefono);
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
