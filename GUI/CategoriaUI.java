package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import BDCrud.CategoriaCRUD;
import Conexiones.ConexionBD;

public class CategoriaUI extends JPanel {
    private final JTable tablaCategorias;
    private final JTextField nombreField;
    private final JTextField idCategoriaField;
    private CategoriaCRUD categoriaCRUD;
    private ProductoUI productoUI;

    ConexionBD conexion = new ConexionBD();

    public CategoriaUI(ProductoUI productoUI) {
        this.productoUI = productoUI;

        setSize(700, 600);

        categoriaCRUD = new CategoriaCRUD(conexion);

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(5, 2));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("ID Categoría (para actualizar/eliminar):"));
        idCategoriaField = new JTextField();
        formularioPanel.add(idCategoriaField);

        JButton agregarButton = new JButton("Agregar Categoría");
        formularioPanel.add(agregarButton);

        JButton actualizarButton = new JButton("Actualizar Categoría");
        formularioPanel.add(actualizarButton);

        JButton eliminarButton = new JButton("Eliminar Categoría");
        formularioPanel.add(eliminarButton);

        tablaCategorias = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaCategorias);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"IdCategoria", "Nombre"}, 0);
        tablaCategorias.setModel(model);

        // método para agregar una categoría
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                if (!nombre.isEmpty()) {
                    categoriaCRUD.agregarCategoria(nombre);
                    cargarCategorias();
                    productoUI.cargarCategorias();
                    JOptionPane.showMessageDialog(null, "Categoría agregada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la categoría.");
                }
            }
        });

        // método para actualizar una categoría
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String idcategoriaText = idCategoriaField.getText();
                if (!nombre.isEmpty() && !idcategoriaText.isEmpty()) {
                    try {
                        int idcategoria = Integer.parseInt(idcategoriaText);
                        categoriaCRUD.actualizarCategoria(nombre, idcategoria);
                        cargarCategorias();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de categoría válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese tanto el nombre como el ID de la categoría.");
                }
            }
        });

        // método para eliminar una categoría
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idCategoriaText = idCategoriaField.getText();
                if (!idCategoriaText.isEmpty()) {
                    try {
                        int idCategoria = Integer.parseInt(idCategoriaText);

                        if (confirmarEliminacion(idCategoria)) {
                            categoriaCRUD.eliminarCategoria(idCategoria);
                            cargarCategorias();
                            productoUI.cargarCategorias();
                            JOptionPane.showMessageDialog(null, "Categoría eliminada con éxito.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de categoría válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID de la categoría que desea eliminar.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(formularioPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        cargarCategorias();
    }

    // método para confirmar la eliminación de la categoría
    private boolean confirmarEliminacion(int idCategoria) {
        Object[] options = { "Sí", "No" };
        int respuesta = JOptionPane.showOptionDialog(
                null,
                "¿Estás seguro de eliminar esta categoría?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        return respuesta == 0;
    }

    // método para cargar las categorías desde la base de datos
    private void cargarCategorias() {
        DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();
        model.setRowCount(0);

        try (Connection conn = conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categoria")) {

            while (rs.next()) {
                int idCategoria = rs.getInt("idcategoria");
                String nombre = rs.getString("nombre");
                model.addRow(new Object[]{idCategoria, nombre});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "[!] Error al cargar las categorías desde la base de datos.");
        }
    }
}
