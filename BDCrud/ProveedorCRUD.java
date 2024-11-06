package BDCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexiones.ConexionBD;


public class ProveedorCRUD {
    private ConexionBD conexion;

    public ProveedorCRUD(ConexionBD conexion){
        this.conexion = conexion;
    }

    public void agregarProveedor(String nombre, String telefono, String email){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "INSERT INTO proveedor (nombre, telefono, email) VALUES (?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nombre);
            pstm.setString(2, telefono);
            pstm.setString(3, email);
            int rowCount = pstm.executeUpdate();
            System.out.println("Empleado agregado exitosamente: " + rowCount + "filas afectadas.");
        }catch(SQLException e){
            System.out.println("Error al conectar la base de datos. " + e.getMessage());
        }
    }

    public void mostrarProveedor(){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "SELECT * FROM proveedor";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()){
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                System.out.println(nombre + "/t" + telefono + "/t" + email);
            }
        }catch (SQLException e){
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    public void actualizarCliente(String nombre, String telefono, String email, int idproveedor){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "UPDATE proveedor SET nombre = ?, telefono = ?, email = ? WHERE idproveedor = ?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setString(2, telefono);
            psmt.setString(3, email);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

    }

    public void eliminarProveedor(int idproveedor){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "DELETE FROM proveedor WHERE idproveedor = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, idproveedor);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

}
