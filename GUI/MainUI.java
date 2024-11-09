package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel panelPrincipal;

    public MainUI() {
        // Configuración básica de la ventana principal
        setTitle("Gestión de CRUDs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el CardLayout para manejar los diferentes paneles
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Crear los paneles para cada CRUD
        ProductoUI productoUI = new ProductoUI();
        CategoriaUI categoriaUI = new CategoriaUI(productoUI);  // Pasamos productoUI a categoriaUI
        ClienteUI clienteUI = new ClienteUI();
        ProveedorUI proveedorUI = new ProveedorUI();
        EmpleadoUI empleadoUI = new EmpleadoUI();

        // Agregar los paneles al panel principal con un nombre único
        panelPrincipal.add(clienteUI, "Clientes");
        panelPrincipal.add(categoriaUI, "Categorias");
        panelPrincipal.add(proveedorUI, "Proveedores");
        panelPrincipal.add(productoUI, "Productos");
        panelPrincipal.add(empleadoUI, "Empleados");

        // Panel con los botones para seleccionar el CRUD
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(5, 1));  // Ajustar la cantidad de filas a 5 para incluir "Empleados"

        // Botón para mostrar clientes
        JButton clientesButton = new JButton("Clientes");
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Clientes");
            }
        });
        botonesPanel.add(clientesButton);

        // Botón para mostrar categorías
        JButton categoriasButton = new JButton("Categorias");
        categoriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Categorias");
            }
        });
        botonesPanel.add(categoriasButton);

        // Botón para mostrar proveedores
        JButton proveedoresButton = new JButton("Proveedores");
        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Proveedores");
            }
        });
        botonesPanel.add(proveedoresButton);

        // Botón para mostrar productos
        JButton productosButton = new JButton("Productos");
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Productos");
            }
        });
        botonesPanel.add(productosButton);

        // Botón para mostrar empleados
        JButton empleadosButton = new JButton("Empleados");
        empleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Empleados");
            }
        });
        botonesPanel.add(empleadosButton);  // Agregar el botón de "Empleados"

        // Layout de la ventana principal
        setLayout(new BorderLayout());
        add(botonesPanel, BorderLayout.WEST);  // Panel con los botones en el lado izquierdo
        add(panelPrincipal, BorderLayout.CENTER);  // Panel donde cambian los CRUDs

        setVisible(true);
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainUI();
        });
    }
}
