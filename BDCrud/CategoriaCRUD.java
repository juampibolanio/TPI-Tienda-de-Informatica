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
            System.out.println("Categoría agregada exitosamente: " + rowCount + " filas afectadas.");
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


            System.out.printf("%-15s%-30s%n", "IdCategoria", "Nombre");
            System.out.println("--------------------------------------------------");


            while (rs.next()) {
                int idcategoria = rs.getInt("idcategoria");
                String nombre = rs.getString("nombre");
                System.out.printf("%-15d%-30s%n", idcategoria, nombre);
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        }
    }


    public void actualizarCategoria(String nombre, int idcategoria){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "UPDATE categoria SET nombre = ? WHERE idcategoria = ?";
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

    public boolean existeCategoria(int idCategoria) {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT COUNT(*) FROM categoria WHERE idcategoria = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, idCategoria);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es mayor a 0, existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean esNombreDuplicado(String nombreCategoria) {
        try  {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT COUNT(*) FROM categoria WHERE nombre = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombreCategoria);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es mayor a 0, el nombre está duplicado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
