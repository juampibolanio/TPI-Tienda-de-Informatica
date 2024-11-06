package BDCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexiones.ConexionBD;

public class CategoriaCRUD {
    private ConexionBD conexion;

    public CategoriaCRUD(ConexionBD conexion) {
        this.conexion = conexion;
    }

    public void agregarCategoria(String nombre) {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "INSERT INTO categoria (nombre) VALUES (?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            int rowCount = psmt.executeUpdate();
            System.out.println("Categoria agregada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        }
    }

    public void mostrarCategoria() {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT * FROM categoria";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                int idcategoria = rs.getInt("idcategoria");
                String nombre = rs.getString("nombre");
                System.out.println(idcategoria + "/t" + nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        }
    }

    public void actualizarCategoria(String nombre, int idcategoria){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "UPDATE empleado SET nombre = ? WHERE idcategoria = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setInt(2, idcategoria);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void eliminarCategoria(int idcategoria){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "DELETE FROM categoria WHERE idcategoria = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, idcategoria);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
