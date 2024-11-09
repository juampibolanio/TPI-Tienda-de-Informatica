import Conexiones.ConexionBD;
import BDCrud.CategoriaCRUD;
import BDCrud.ClienteCRUD;
import BDCrud.EmpleadoCRUD;
import BDCrud.ProductoCRUD;
import BDCrud.ProveedorCRUD;

import java.util.Scanner;

public class Tienda_Informatica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConexionBD conexionBD = new ConexionBD();

        CategoriaCRUD categoriaCRUD = new CategoriaCRUD(conexionBD);
        ClienteCRUD clienteCRUD = new ClienteCRUD(conexionBD);
        EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD(conexionBD);
        ProductoCRUD productoCRUD = new ProductoCRUD(conexionBD);
        ProveedorCRUD proveedorCRUD = new ProveedorCRUD(conexionBD);

        while (true) {
            System.out.println("\n===== Menú Principal =====");
            System.out.println("1. Gestionar Categorías");
            System.out.println("2. Gestionar Clientes");
            System.out.println("3. Gestionar Empleados");
            System.out.println("4. Gestionar Productos");
            System.out.println("5. Gestionar Proveedores");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer de scanner

            switch (opcion) {
                case 1:
                    menuCategorias(scanner, categoriaCRUD);
                    break;
                case 2:
                    menuClientes(scanner, clienteCRUD);
                    break;
                case 3:
                    menuEmpleados(scanner, empleadoCRUD);
                    break;
                case 4:
                    menuProductos(scanner, productoCRUD);
                    break;
                case 5:
                    menuProveedores(scanner, proveedorCRUD);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    conexionBD.cerrarConexionBD();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú para gestionar categorías
    private static void menuCategorias(Scanner scanner, CategoriaCRUD categoriaCRUD) {
        while (true) {
            System.out.println("\n===== Gestión de Categorías =====");
            System.out.println("1. Agregar Categoría");
            System.out.println("2. Mostrar Categorías");
            System.out.println("3. Actualizar Categoría");
            System.out.println("4. Eliminar Categoría");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer de scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la categoría: ");
                    String nombreCategoria = scanner.nextLine();
                    categoriaCRUD.agregarCategoria(nombreCategoria);
                    break;
                case 2:
                    categoriaCRUD.mostrarCategoria();
                    break;
                case 3:
                    System.out.print("Ingrese el ID de la categoría a actualizar: ");
                    int idCategoriaActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre de la categoría: ");
                    String nuevoNombreCategoria = scanner.nextLine();
                    categoriaCRUD.actualizarCategoria(nuevoNombreCategoria, idCategoriaActualizar);
                    break;
                case 4:
                    System.out.print("Ingrese el ID de la categoría a eliminar: ");
                    int idCategoriaEliminar = scanner.nextInt();
                    categoriaCRUD.eliminarCategoria(idCategoriaEliminar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú para gestionar clientes
    private static void menuClientes(Scanner scanner, ClienteCRUD clienteCRUD) {
        while (true) {
            System.out.println("\n===== Gestión de Clientes =====");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer de scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Ingrese el apellido del cliente: ");
                    String apellidoCliente = scanner.nextLine();
                    System.out.print("Ingrese el email del cliente: ");
                    String emailCliente = scanner.nextLine();
                    System.out.print("Ingrese el teléfono del cliente: ");
                    String telefonoCliente = scanner.nextLine();
                    clienteCRUD.agregarCliente(nombreCliente, apellidoCliente, emailCliente, telefonoCliente);
                    break;
                case 2:
                    clienteCRUD.mostrarCliente();
                    break;
                case 3:
                    System.out.print("Ingrese el ID del cliente a actualizar: ");
                    int idClienteActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del cliente: ");
                    String nuevoNombreCliente = scanner.nextLine();
                    System.out.print("Ingrese el nuevo apellido del cliente: ");
                    String nuevoApellidoCliente = scanner.nextLine();
                    System.out.print("Ingrese el nuevo email del cliente: ");
                    String nuevoEmailCliente = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono del cliente: ");
                    String nuevoTelefonoCliente = scanner.nextLine();
                    clienteCRUD.actualizarCliente(nuevoNombreCliente, nuevoApellidoCliente, nuevoEmailCliente, nuevoTelefonoCliente, idClienteActualizar);
                    break;
                case 4:
                    System.out.print("Ingrese el ID del cliente a eliminar: ");
                    int idClienteEliminar = scanner.nextInt();
                    clienteCRUD.eliminarCliente(idClienteEliminar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
