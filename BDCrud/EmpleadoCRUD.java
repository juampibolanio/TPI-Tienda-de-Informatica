package BDCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexiones.ConexionBD;


public class EmpleadoCRUD {
    private ConexionBD conexion;

    public EmpleadoCRUD(ConexionBD conexion){
        this.conexion = conexion;
    }

    public void agregarEmpleados(String nombre, String apellido, String puesto, String telefono){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "INSERT INTO empleado (nombre, apellido, puesto, telefono) VALUES (?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, puesto);
            pstm.setString(4, telefono);
            int rowCount = pstm.executeUpdate();
            System.out.println("Empleado agregado exitosamente: " + rowCount + " filas afectadas.");
        }catch (SQLException e){
            System.out.println("Error al conectar con la base de datos. " + e.getMessage());
        }
    }

    public void mostrarEmpleados() {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT * FROM empleado";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();


            System.out.printf("%-20s%-20s%-20s%-20s%-15s%n", "IDEmpleado", "Nombre", "Apellido", "Puesto", "Teléfono");
            System.out.println("-----------------------------------------------------------------------------------------");


            while (rs.next()) {
                int idempleado = rs.getInt("idempleado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String puesto = rs.getString("puesto");
                String telefono = rs.getString("telefono");
                System.out.printf("%-20s%-20s%-20s%-20s%-15s%n", idempleado, nombre, apellido, puesto, telefono);
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }


    public void actualizarEmpleados(String nombre, String apellido, String puesto, String telefono, int idempleado){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "UPDATE empleado SET nombre = ?, apellido = ?, puesto = ?, telefono = ? WHERE idempleado = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setString(2, apellido);
            psmt.setString(3, puesto);
            psmt.setString(4, telefono);
            psmt.setInt(5, idempleado);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void eliminarEmpleados(int idempleado){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "DELETE FROM empleado WHERE idempleado = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, idempleado);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public boolean existeEmpleado(int idEmpleado) {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT COUNT(*) FROM empleado WHERE idempleado = ?"; //fijarse en la "idempleado"
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, idEmpleado);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean esTelefonoDuplicado(String telefono) {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT COUNT(*) FROM empleado WHERE telefono = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, telefono);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}