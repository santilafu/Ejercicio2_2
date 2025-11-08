// Ejemplo de uso de commit y rollback en una base de datos MySQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio2_6 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/asesoria";
        String user = "root";
        String password = "B@se1234Datos";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            System.out.println("✅ Conexión establecida correctamente.");

            // Desactivamos el autocommit para manejar la transacción manualmente
            con.setAutoCommit(false);

            try {
                // 1️⃣ Insertamos un nuevo cliente
                String sqlCliente = "INSERT INTO clientes (nif, nombre) VALUES (?, ?)";
                PreparedStatement psCliente = con.prepareStatement(sqlCliente);
                psCliente.setString(1, "88888888Z");
                psCliente.setString(2, "Fernando López");
                psCliente.executeUpdate();

                // 2️⃣ Insertamos su factura
                String sqlFactura = "INSERT INTO facturas (nif_cliente, importe) VALUES (?, ?)";
                PreparedStatement psFactura = con.prepareStatement(sqlFactura);
                psFactura.setString(1, "88888888Z");
                psFactura.setDouble(2, 250.75);
                psFactura.executeUpdate();

                // Si todo sale bien, confirmamos los cambios
                con.commit();
                System.out.println("💾 Transacción confirmada correctamente.");

            } catch (SQLException e) {
                // Si algo falla, revertimos todos los cambios
                con.rollback();
                System.out.println("❌ Error detectado, transacción revertida.");
                System.out.println("Detalles: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
