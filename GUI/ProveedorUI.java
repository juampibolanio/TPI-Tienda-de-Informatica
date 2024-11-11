package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BDCrud.ProveedorCRUD;
import Conexiones.ConexionBD;

public class ProveedorUI extends JPanel {
    private final JTable tablaProveedores;
    private final JTextField nombreField;
    private final JTextField telefonoField;
    private final JTextField emailField;
    private ProveedorCRUD proveedorCRUD;
    ConexionBD conexion = new ConexionBD();

    public ProveedorUI() {
        proveedorCRUD = new ProveedorCRUD(conexion);

        setSize(700, 600);

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(4, 2));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        formularioPanel.add(telefonoField);

        formularioPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formularioPanel.add(emailField);

        JButton agregarButton = new JButton("Agregar Proveedor");
        formularioPanel.add(agregarButton);

        JButton eliminarButton = new JButton("Eliminar Proveedor");
        formularioPanel.add(eliminarButton);

        tablaProveedores = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaProveedores);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"IdProveedor", "Nombre", "Teléfono", "Email"}, 0);
        tablaProveedores.setModel(model);

        // método para agregar un proveedor
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();

                if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
                } else {
                    proveedorCRUD.agregarProveedor(nombre, telefono, email);
                    cargarProveedores();
                    JOptionPane.showMessageDialog(null, "Proveedor agregado con éxito.");
                }
            }
        });

        // método para eliminar un proveedor
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaProveedores.getSelectedRow();
                if (selectedRow >= 0) {
                    String idProveedorStr = (String) tablaProveedores.getModel().getValueAt(selectedRow, 0);
                    try {
                        int idProveedor = Integer.parseInt(idProveedorStr);
                        if (confirmarEliminacion(idProveedor)) {
                            proveedorCRUD.eliminarProveedor(idProveedor);
                            cargarProveedores();
                            JOptionPane.showMessageDialog(null, "Proveedor eliminado con éxito.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID del proveedor no es válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor de la tabla para eliminarlo.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(formularioPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        cargarProveedores();
    }

    // método para cargar los proveedores en la tabla desde la base de datos
    private void cargarProveedores() {
        DefaultTableModel model = (DefaultTableModel) tablaProveedores.getModel();
        model.setRowCount(0);

        List<String[]> proveedores = obtenerProveedores();
        for (String[] proveedor : proveedores) {
            model.addRow(proveedor);
        }
    }

    // método para obtener los proveedores desde la base de datos
    private List<String[]> obtenerProveedores() {
        List<String[]> proveedores = new ArrayList<>();
        String query = "SELECT idproveedor, nombre, telefono, email FROM proveedor";
        try (Connection conn = conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int idproveedor = rs.getInt("idproveedor");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                proveedores.add(new String[]{String.valueOf(idproveedor), nombre, telefono, email});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    // método para confirmar la eliminación de un proveedor
    private boolean confirmarEliminacion(int idproveedor) {
        Object[] opciones = {"Sí", "No"};
        int respuesta = JOptionPane.showOptionDialog(
                null,
                "¿Estás seguro de eliminar este proveedor?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        return respuesta == JOptionPane.YES_OPTION;
    }
}
