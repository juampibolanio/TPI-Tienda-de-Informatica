package BDCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexiones.ConexionBD;

public class ProductoCRUD {
    private ConexionBD conexion;

    public ProductoCRUD(ConexionBD conexion) {
        this.conexion = conexion;
    }

    public void agregarProducto(String nombre, double precio, int stock, String marca, int idcategoria) {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "INSERT INTO producto (nombre, precio, stock, marca, idcategoria) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setDouble(2, precio);
            psmt.setInt(3, stock);
            psmt.setString(4, marca);
            psmt.setInt(5, idcategoria);
            int rowCount = psmt.executeUpdate();
            System.out.println("Producto agregado exitosamente: " + rowCount + " filas afectadas.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void mostrarProductos() {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT p.idproducto, p.nombre, p.precio, p.stock, p.marca, c.nombre " +
                    "FROM producto p " +
                    "JOIN categoria c ON p.idcategoria = c.idcategoria";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            System.out.println(String.format("%-10s%-20s%-10s%-10s%-20s%-20s", "IdProducto", "Nombre", "Precio", "Stock", "Marca", "Categoría"));
            while (rs.next()) {
                int idproducto = rs.getInt("idproducto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                String marca = rs.getString("marca");
                String categoria = rs.getString("nombre");
                System.out.println(String.format("%-10d%-20s%-10.2f%-10d%-20s%-20s", idproducto, nombre, precio, stock, marca, categoria));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    public void actualizarProducto(int idproducto, String nombre, double precio, int stock, String marca, int idcategoria){
        try {
            Connection conn = conexion.conectarBD();
            String sql = "UPDATE producto SET nombre = ?, precio = ?, stock = ?, marca = ?, idcategoria = ? WHERE idproducto = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setDouble(2, precio);
            psmt.setInt(3, stock);
            psmt.setString(4, marca);
            psmt.setInt(5, idcategoria);
            psmt.setInt(6, idproducto);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch(SQLException e){
            System.out.println("Error al conectar con la base de datos: "+ e.getMessage());
        }
    }

    public void eliminarProducto(int idproducto){
        try{
            Connection conn = conexion.conectarBD();
            String sql = "DELETE FROM producto WHERE idproducto = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, idproducto);
            int rowCount = psmt.executeUpdate();
            System.out.println("Número de filas afectadas: " + rowCount);
        }catch (SQLException e){
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}