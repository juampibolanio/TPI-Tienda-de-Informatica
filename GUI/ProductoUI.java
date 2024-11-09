package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import BDCrud.ProductoCRUD;
import Conexiones.ConexionBD;

public class ProductoUI extends JPanel {
    private final JTable tablaProductos;
    private final JTextField nombreField;
    private final JTextField precioField;
    private final JTextField stockField;
    private final JTextField marcaField;
    private final JComboBox<String> categoriaComboBox;
    private ProductoCRUD productoCRUD;
    ConexionBD conexion = new ConexionBD();

    public ProductoUI() {
        productoCRUD = new ProductoCRUD(conexion);

        setSize(700, 600);

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(6, 2));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Precio:"));
        precioField = new JTextField();
        formularioPanel.add(precioField);

        formularioPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        formularioPanel.add(stockField);

        formularioPanel.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        formularioPanel.add(marcaField);

        formularioPanel.add(new JLabel("Categoría:"));
        categoriaComboBox = new JComboBox<>();
        formularioPanel.add(categoriaComboBox);

        JButton agregarButton = new JButton("Agregar Producto");
        formularioPanel.add(agregarButton);

        JButton eliminarButton = new JButton("Eliminar Producto");
        formularioPanel.add(eliminarButton);

        tablaProductos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"IdProducto", "Nombre", "Precio", "Stock", "Marca", "Categoría"}, 0);
        tablaProductos.setModel(model);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                double precio;
                int stock;
                String marca = marcaField.getText();
                String categoriaSeleccionada = (String) categoriaComboBox.getSelectedItem();

                // Verifica que se haya seleccionado una categoría válida
                if (categoriaSeleccionada == null || categoriaSeleccionada.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una categoría.");
                    return;
                }

                int idcategoria = obtenerIdCategoria(categoriaSeleccionada);

                if (idcategoria == -1) {
                    JOptionPane.showMessageDialog(null, "La categoría seleccionada no existe en la base de datos.");
                    return;
                }

                try {
                    precio = Double.parseDouble(precioField.getText());
                    stock = Integer.parseInt(stockField.getText());

                    if (nombre.isEmpty() || marca.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
                    } else {
                        productoCRUD.agregarProducto(nombre, precio, stock, marca, idcategoria);
                        cargarProductos();
                        JOptionPane.showMessageDialog(null, "Producto agregado con éxito."); // Mensaje de éxito
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Precio y Stock deben ser números válidos.");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaProductos.getSelectedRow();
                if (selectedRow >= 0) {
                    Object idProductoObj = tablaProductos.getModel().getValueAt(selectedRow, 0);
                    int idProducto = -1;

                    if (idProductoObj instanceof String) {
                        try {
                            idProducto = Integer.parseInt((String) idProductoObj);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error al obtener el ID del producto.");
                        }
                    } else if (idProductoObj instanceof Integer) {
                        idProducto = (Integer) idProductoObj;
                    }

                    if (idProducto != -1) {
                        // Mostrar mensaje de confirmación con botones en español
                        Object[] options = { "Sí", "No" };
                        int confirm = JOptionPane.showOptionDialog(
                                null,
                                "¿Estás seguro de que quieres eliminar este producto?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]  // El primer botón ("Sí")
                        );

                        // Si el usuario hace clic en "Sí"
                        if (confirm == JOptionPane.YES_OPTION) {
                            productoCRUD.eliminarProducto(idProducto);
                            cargarProductos();  // Recargar los productos después de eliminar
                            JOptionPane.showMessageDialog(null, "Producto eliminado con éxito."); // Mensaje de éxito
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un producto válido para eliminar.");
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        add(formularioPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        cargarProductos();
        cargarCategorias();  // Cargar categorías al inicio
    }

    private void cargarProductos() {
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setRowCount(0);

        try (Connection conn = conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.idproducto, p.nombre, p.precio, p.stock, p.marca, c.nombre as categoria " +
                     "FROM producto p JOIN categoria c ON p.idcategoria = c.idcategoria")) {

            while (rs.next()) {
                String[] producto = {
                        String.valueOf(rs.getInt("idproducto")),
                        rs.getString("nombre"),
                        String.valueOf(rs.getDouble("precio")),
                        String.valueOf(rs.getInt("stock")),
                        rs.getString("marca"),
                        rs.getString("categoria")
                };
                model.addRow(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los productos desde la base de datos.");
        }
    }

    public void cargarCategorias() {
        categoriaComboBox.removeAllItems();
        try (Connection conn = conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idcategoria, nombre FROM categoria")) {

            while (rs.next()) {
                categoriaComboBox.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las categorías.");
        }
    }

    // Método para obtener el idcategoria a partir del nombre de la categoría
    private int obtenerIdCategoria(String nombreCategoria) {
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement("SELECT idcategoria FROM categoria WHERE nombre = ?")) {
            stmt.setString(1, nombreCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idcategoria");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Si no se encuentra la categoría, retornamos -1
    }
}
