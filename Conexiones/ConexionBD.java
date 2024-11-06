package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexionBD {
    String url = "jdbc:mysql://localhost:3306/tienda_informatica";
    String user = "root";
    String password = "root";
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;

    public Connection conectarBD() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            conn = null;
        }
        return conn;
    }


    public void cerrarConexionBD() {
        try {
            if (rs != null) rs.close();
            if (psmt != null) rs.close();
            if (conn != null) rs.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }
}