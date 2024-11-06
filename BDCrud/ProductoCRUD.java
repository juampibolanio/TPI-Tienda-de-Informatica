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
            psmt.setInt(5, idcategoria);//hacer la conexion entre producto y categoria (las fk)
            int rowCount = psmt.executeUpdate();
            System.out.println("Producto agregado exitosamente: " + rowCount + " filas afectadas.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de dato: " + e.getMessage());
        }
    }

    public void mostrarProductos() {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT idproducto, nombre, precio, stock, marca, nombre_categoria FROM producto";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                int idproducto = rs.getInt("idproducto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                String marca = rs.getString("marca");
                String categoria = rs.getString("nombre_categoria");
                System.out.println("IdProducto" + "/t" + "Nombre" + "/t" + "Precio" + "/t" + "Stock" + "/t" + "Marca" + "/t" + "Categoría");
                System.out.println(idproducto + "/t" + nombre + "/t" + precio + "/t" + stock + "/t" + marca + "/t" + categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    public void actualizarProducto() {

    }

    public void eliminarProducto() {

    }
}
