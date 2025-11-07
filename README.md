# 💻 Unidad 2 – Acceso a Datos
## Ejercicio 2.2 – Conexión a MySQL y creación de tabla

**Descripción:**  
Aplicación Java que establece conexión con una base de datos MySQL llamada **`TiendaPc`**  
y crea una tabla **`productos`** con los siguientes campos:

- `id` → INT (clave primaria)
- `nombreProduct` → VARCHAR(40)
- `fabricante` → VARCHAR(30)

Este ejercicio forma parte de la Unidad 2 de *Acceso a Datos* y su objetivo es aprender a conectar una aplicación Java con una base de datos MySQL utilizando JDBC y el conector adecuado.

---

## 🧰 Tecnologías utilizadas

| Herramienta                   | Uso principal |
|-------------------------------|----------------|
| **Java 24**                   | Lenguaje de programación utilizado. |
| **IntelliJ IDEA Ultimate**    | Entorno de desarrollo (IDE). |
| **MySQL Workbench 9.5**       | Creación y gestión de la base de datos. |
| **MySQL Connector/J (9.5.0)** | Conector JDBC utilizado para la conexión desde Java. |

---

## 📂 Estructura del proyecto

ConexionTiendaPc/
├─ lib/
│ └─ mysql-connector-j-9.5.0.jar
├─ src/
│ └─ Ejercicio2_2.java
├─ README.md
└─ ConexionTiendaPc.iml



---

## 🧠 Código Java principal

```java
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
            // 1️⃣ Establecemos la conexión
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión establecida correctamente.");

            // 2️⃣ Creamos el objeto Statement para ejecutar sentencias SQL
            Statement st = con.createStatement();

            // 3️⃣ Creamos la tabla 'productos' si no existe
            String sql = "CREATE TABLE IF NOT EXISTS productos (" +
                         "id INT PRIMARY KEY, " +
                         "nombreProduct VARCHAR(40), " +
                         "fabricante VARCHAR(30)" +
                         ")";

            // 4️⃣ Ejecutamos la sentencia SQL
            st.executeUpdate(sql);
            System.out.println("✅ Tabla 'productos' creada correctamente.");

            // 5️⃣ Cerramos la conexión y el statement
            st.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o crear la tabla: " + e.getMessage());
        }
    }
}
🧩 Script SQL – TiendaPc.sql
Este script está incluido en este mismo archivo para simplificar la entrega.
Copia y ejecuta las siguientes sentencias en MySQL Workbench para crear la base de datos manualmente si es necesario:

-- Crear la base de datos (si no existe)
CREATE DATABASE IF NOT EXISTS TiendaPc;
USE TiendaPc;

-- Crear la tabla productos
CREATE TABLE IF NOT EXISTS productos (
    id INT PRIMARY KEY,
    nombreProduct VARCHAR(40),
    fabricante VARCHAR(30)
);
📘 Notas importantes
No se sube la base de datos real a GitHub, solo el script SQL (incluido arriba).

El código utiliza CREATE TABLE IF NOT EXISTS, por lo que no fallará si la tabla ya existe.

El servidor MySQL debe estar activo antes de ejecutar el programa.

✍️ Autor
Santiago Lafuente Hernández
Acceso a Datos - 2ºDAM
(Desarrollado y documentado con la ayuda de ChatGPT para redacción técnica y guía práctica.)


---