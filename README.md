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
│ ├─ Ejercicio2_2.java
│ └─ Ejercicio2_3.java
├─ README.md
└─ ConexionTiendaPc.iml

pgsql
Copiar código

---

## 🧠 Código Java – Ejercicio 2.2

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

sql
Copiar código
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
```
## ✍️ Autor

Santiago Lafuente Hernández

Acceso a Datos - 2º DAM

(Desarrollado y documentado con la ayuda de ChatGPT para redacción técnica y guía práctica.)

# **💾 Ejercicio 2.3 – Inserción de datos en la tabla productos**

Descripción:
Continuando con el ejercicio anterior (Ejercicio 2.2), en esta práctica se amplía el programa para insertar registros en la tabla productos de la base de datos TiendaPc.
El objetivo es comprobar que la conexión y la inserción de datos funcionan correctamente desde una aplicación Java mediante JDBC.

🧠 Código Java – Ejercicio 2.3

```java
// Creamos una clase para introducir los datos en la tabla 'productos' creada en el ejercicio anterior.
// Importamos lo necesario para manejar BBDD MySQL.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Ejercicio2_3 {
    public static void main(String[] args) {

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
            pstmt.setInt(1, 1); // id
            pstmt.setString(2, "Portátil Gamer XYZ"); // nombreProduct
            pstmt.setString(3, "MSI"); // fabricante
            pstmt.executeUpdate(); // Ejecutamos la inserción
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

            // Cerramos la conexión y el PreparedStatement
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o insertar datos: " + e.getMessage());
        }
    }
}
✅ Resultado esperado
En la consola aparecerá:

Copiar código
✅ Conexión establecida correctamente.
✅ Producto 1 insertado correctamente.
✅ Producto 2 insertado correctamente.
✅ Producto 3 insertado correctamente.
Y en MySQL Workbench, si ejecutas:


SELECT * FROM productos;


Obtendrás:

id	nombreProduct	fabricante
1	Portátil Gamer XYZ	MSI
2	Monitor UltraWide 34"	LG
3	Teclado Mecánico RGB	Corsair

```

📘 Notas importantes
El PreparedStatement permite usar parámetros ? para evitar errores de formato o inyecciones SQL.

Si los IDs ya existen, se producirá un error de clave duplicada (Duplicate entry).
Puedes cambiar los IDs o limpiar la tabla con:

TRUNCATE TABLE productos;
El cierre de conexión (close()) es obligatorio para liberar recursos.

El código puede ejecutarse varias veces sin dañar la base de datos si cambias los IDs.

## 📸**Foto de MySQL WorkBench**
<p aling="center">
<img src="Captura de pantalla 2025-11-07 123158.png" alt= "Vista tabla productos en MySQL Workbench" width="300"/>
</p>

## ✍️ Autor

Santiago Lafuente Hernández

Acceso a Datos - 2º DAM

(Desarrollo y documentación realizada con la ayuda de ChatGPT para guía técnica y redacción profesional.)

