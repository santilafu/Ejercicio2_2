// Creamos una clase para introducir los datos en la tabla 'productos' creada en el ejercicio anterior.
//Importamos lo necesario para manejar BBDD MySQL.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class Ejercicio2_3 {
    public static void  main (String[] args) {

        // Datos de la conexión a la base de datos MySQL
        String url = "jdbc:mysql://localhost:3306/TiendaPc";
        String user = "root";
        String password = "B@se1234Datos";

        // Intentamos conectar
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión establecida correctamente.");
            // Preparamos la sentencia SQL para insertar datos en la tabla 'productos'
            String sql = "INSERT INTO productos (id, nombreProduct, fabricante) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Insertamos varios productos
            pstmt.setInt(1, 1);// id
            pstmt.setString(2, "Portátil Gamer XYZ");// nombreProduct
            pstmt.setString(3, "MSI");// fabricante
            pstmt.executeUpdate();// Ejecutamos la inserción
            System.out.println("✅ Producto 1 insertado correctamente.");
            pstmt.setInt(1, 2);
            pstmt.setString(2, "Monitor UltraWide 34\"");
            pstmt.setString(3, "LG");
            pstmt.executeUpdate();
            System.out.println("✅ Producto 2 insertado correctamente.");
            pstmt.setInt(1, 3);
            pstmt.setString(2, "Teclado Mecánico RGB");
            pstmt.setString(3, "Corsair");
            pstmt.executeUpdate();
            System.out.println("✅ Producto 3 insertado correctamente.");

            //Cerramos la conexión y el PreparedStatement
            pstmt.close();
            con.close();
        }catch (SQLException e){
            System.out.println("❌ Error al conectar o insertar datos: " + e.getMessage());
        }
    }
}
