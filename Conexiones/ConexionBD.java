package Conexiones;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class ConexionBD {
    private String url;
    private String user;
    private String password;
    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;

    public ConexionBD() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("config/database.properties")) {
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Error al cargar las propiedades: " + e.getMessage());
        }
    }

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
            if (psmt != null) psmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}



