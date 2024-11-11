package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BDCrud.EmpleadoCRUD;
import Conexiones.ConexionBD;

public class EmpleadoUI extends JPanel {
    private final JTable tablaEmpleados;
    private final JTextField nombreField;
    private final JTextField apellidoField;
    private final JTextField puestoField;
    private final JTextField telefonoField;
    private EmpleadoCRUD empleadoCRUD;
    ConexionBD conexion = new ConexionBD();

    public EmpleadoUI() {
        empleadoCRUD = new EmpleadoCRUD(conexion);

        setSize(700, 600);

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridLayout(5, 2));

        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);

        formularioPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formularioPanel.add(apellidoField);

        formularioPanel.add(new JLabel("Puesto:"));
        puestoField = new JTextField();
        formularioPanel.add(puestoField);

        formularioPanel.add(new JLabel("Telefono:"));
        telefonoField = new JTextField();
        formularioPanel.add(telefonoField);

        JButton agregarButton = new JButton("Agregar Empleado");
        formularioPanel.add(agregarButton);

        JButton eliminarButton = new JButton("Eliminar Empleado");
        formularioPanel.add(eliminarButton);

        tablaEmpleados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"idEmpleado", "Nombre", "Apellido", "Puesto", "Telefono"}, 0);
        tablaEmpleados.setModel(model);

        // método para agregar un empleado
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String puesto = puestoField.getText();
                String telefono = telefonoField.getText();

                if (nombre.isEmpty() || apellido.isEmpty() || puesto.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.");
                } else {
                    empleadoCRUD.agregarEmpleados(nombre, apellido, puesto, telefono);
                    cargarEmpleados();
                    JOptionPane.showMessageDialog(null, "Empleado agregado con éxito.");
                }
            }
        });

        // método para eliminar un empleado
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaEmpleados.getSelectedRow();
                if (selectedRow >= 0) {
                    int idempleado = Integer.parseInt(tablaEmpleados.getModel().getValueAt(selectedRow, 0).toString());
                    if (confirmarEliminacion(idempleado)) {
                        empleadoCRUD.eliminarEmpleados(idempleado);
                        cargarEmpleados();
                        JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado de la tabla para eliminarlo.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(formularioPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        cargarEmpleados();
    }

    // método para cargar los empleados en la tabla desde la base de datos
    private void cargarEmpleados() {
        DefaultTableModel model = (DefaultTableModel) tablaEmpleados.getModel();
        model.setRowCount(0);

        List<String[]> empleados = obtenerEmpleados();
        for (String[] empleado : empleados) {
            model.addRow(empleado);
        }
    }

    // método para obtener los empleados desde la base de datos
    private List<String[]> obtenerEmpleados() {
        List<String[]> empleados = new ArrayList<>();
        String query = "SELECT * FROM empleado";
        try (Connection conn = conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("idempleado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String puesto = rs.getString("puesto");
                String telefono = rs.getString("telefono");
                empleados.add(new String[]{String.valueOf(id), nombre, apellido, puesto, telefono});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    // método para confirmar la eliminación de un empleado
    private boolean confirmarEliminacion(int idempleado) {
        Object[] options = { "Sí", "No" };
        int respuesta = JOptionPane.showOptionDialog(
                null,
                "¿Estás seguro de eliminar este empleado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        return respuesta == 0;
    }
}
