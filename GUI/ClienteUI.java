package GUI;

import Conexiones.ConexionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteUI extends JPanel {
    private final JTable tablaClientes;
    private final JTextField nombreField;
    private final JTextField apellidoField;
    private final JTextField emailField;
    private final JTextField telefonoField;
    ConexionBD conexion = new ConexionBD();

    // Constructor de la interfaz gráfica
    public ClienteUI() {

        setSize(700, 600);


        // Panel de formulario
        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(5, 3));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formularioPanel.add(apellidoField);

        formularioPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formularioPanel.add(emailField);

        formularioPanel.add(new JLabel("Telefono:"));
        telefonoField = new JTextField();
        formularioPanel.add(telefonoField);

        JButton agregarButton = new JButton("Agregar Cliente");
        formularioPanel.add(agregarButton);

        JButton eliminarButton = new JButton("Eliminar Cliente");
        formularioPanel.add(eliminarButton);

        // Tabla para mostrar los clientes
        tablaClientes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"idCliente", "Nombre", "Apellido", "Email", "Telefono"}, 0);
        tablaClientes.setModel(model);

        // Acción para eliminar un cliente
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaClientes.getSelectedRow();
                if (selectedRow >= 0) {
                    String idCliente = (String) tablaClientes.getModel().getValueAt(selectedRow, 0);
                    if (confirmarEliminacion(idCliente)) {
                        if (eliminarCliente(idCliente)) {
                            JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito.");
                            cargarClientes();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente de la tabla para eliminarlo.");
                }
            }
        });

        // Acción para agregar un cliente
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String email = emailField.getText();
                String telefono = telefonoField.getText();
                if (agregarCliente(nombre, apellido, email, telefono)) {
                    JOptionPane.showMessageDialog(null, "Cliente agregado con éxito.");
                    cargarClientes();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar el cliente.");
                }
            }
        });

        // Layout de la ventana
        setLayout(new BorderLayout());
        add(formularioPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        cargarClientes();
    }

    // Método para cargar los clientes en la tabla desde la base de datos
    private void cargarClientes() {
        DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
        model.setRowCount(0);

        List<String[]> clientes = obtenerClientes();
        for (String[] cliente : clientes) {
            model.addRow(cliente);
        }
    }

    // Método para obtener los clientes desde la base de datos
    private List<String[]> obtenerClientes() {
        List<String[]> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (Connection conn = conexion.conectarBD()) {
            if (conn == null) {
                throw new SQLException("No se pudo establecer la conexión con la base de datos.");
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                clientes.add(new String[]{String.valueOf(id), nombre, apellido, email, telefono});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener los clientes desde la base de datos.");
        }
        return clientes;
    }



    // Método para agregar un nuevo cliente a la base de datos
    private boolean agregarCliente(String nombre, String apellido, String email, String telefono) {
        String query = "INSERT INTO cliente (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, telefono);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // Método para eliminar un cliente de la base de datos
    private boolean eliminarCliente(String idcliente) {
        String query = "DELETE FROM cliente WHERE idcliente = ?";
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idcliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para confirmar la eliminación de un cliente
    private boolean confirmarEliminacion(String idcliente) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

}