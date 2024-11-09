package BDCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexiones.ConexionBD;

public class ClienteCRUD {
    private ConexionBD conexion;

    public ClienteCRUD(ConexionBD conexion){
        this.conexion = conexion;
    }

    public void agregarCliente(String nombre, String apellido, String email, String telefono){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "INSERT INTO cliente (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, email);
            pstm.setString(4, telefono);
            int rowCount = pstm.executeUpdate();
            System.out.println("Cliente agregado exitosamente: " + rowCount + " filas afectadas.");
        }catch(SQLException e){
            System.out.println("Error al conectar la base de datos. " + e.getMessage());
        }
    }

    public void mostrarCliente() {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT * FROM cliente";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            System.out.printf("%-20s%-20s%-30s%-15s%n", "Nombre", "Apellido", "Email", "Teléfono");
            System.out.println("--------------------------------------------------------------------------");


            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                System.out.printf("%-20s%-20s%-30s%-15s%n", nombre, apellido, email, telefono);
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }


    public void actualizarCliente(String nombre, String apellido, String email, String telefono, int idcliente){
        try {
            Connection conn = conexion.conectarBD();
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE idcliente = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setString(2, apellido);
            psmt.setString(3, email);
            psmt.setString(4, telefono);
            psmt.setInt(5, idcliente);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch(SQLException e){
            System.out.println("Error al conectar con la base de datos: "+ e.getMessage());
        }
    }

    public void eliminarCliente(int idcliente){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "DELETE FROM cliente WHERE idcliente = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, idcliente);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos: "+ e.getMessage());
        }
    }

}
