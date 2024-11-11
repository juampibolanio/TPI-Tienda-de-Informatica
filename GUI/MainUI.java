package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel panelPrincipal;

    public MainUI() {
        // Configuración de la ventana principal
        setTitle("Gestión de CRUDs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout para manejar paneles
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Paneles de cada CRUD
        ProductoUI productoUI = new ProductoUI();
        CategoriaUI categoriaUI = new CategoriaUI(productoUI);
        ClienteUI clienteUI = new ClienteUI();
        ProveedorUI proveedorUI = new ProveedorUI();
        EmpleadoUI empleadoUI = new EmpleadoUI();

        // Agregado de los paneles al panel principal
        panelPrincipal.add(clienteUI, "Clientes");
        panelPrincipal.add(categoriaUI, "Categorias");
        panelPrincipal.add(proveedorUI, "Proveedores");
        panelPrincipal.add(productoUI, "Productos");
        panelPrincipal.add(empleadoUI, "Empleados");

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(5, 1));

        JButton clientesButton = new JButton("Clientes");
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Clientes");
            }
        });
        botonesPanel.add(clientesButton);

        JButton categoriasButton = new JButton("Categorias");
        categoriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Categorias");
            }
        });
        botonesPanel.add(categoriasButton);

        JButton proveedoresButton = new JButton("Proveedores");
        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Proveedores");
            }
        });
        botonesPanel.add(proveedoresButton);

        JButton productosButton = new JButton("Productos");
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Productos");
            }
        });
        botonesPanel.add(productosButton);

        JButton empleadosButton = new JButton("Empleados");
        empleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Empleados");
            }
        });
        botonesPanel.add(empleadosButton);

        setLayout(new BorderLayout());
        add(botonesPanel, BorderLayout.WEST);
        add(panelPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }

    // método para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainUI();
        });
    }
}
