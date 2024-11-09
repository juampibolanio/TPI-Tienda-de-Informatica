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

    // Menú para gestionar empleados
    private static void menuEmpleados(Scanner scanner, EmpleadoCRUD empleadoCRUD) {
        while (true) {
            System.out.println("\n===== Gestión de Empleados =====");
            System.out.println("1. Agregar Empleado");
            System.out.println("2. Mostrar Empleados");
            System.out.println("3. Actualizar Empleado");
            System.out.println("4. Eliminar Empleado");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer de scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del empleado: ");
                    String nombreEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el apellido del empleado: ");
                    String apellidoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el puesto del empleado: ");
                    String puestoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el teléfono del empleado: ");
                    String telefonoEmpleado = scanner.nextLine();
                    empleadoCRUD.agregarEmpleados(nombreEmpleado, apellidoEmpleado, puestoEmpleado, telefonoEmpleado);
                    break;
                case 2:
                    empleadoCRUD.mostrarEmpleados();
                    break;
                case 3:
                    System.out.print("Ingrese el ID del empleado a actualizar: ");
                    int idEmpleadoActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del empleado: ");
                    String nuevoNombreEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el nuevo apellido del empleado: ");
                    String nuevoApellidoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el nuevo puesto del empleado: ");
                    String nuevoPuestoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono del empleado: ");
                    String nuevoTelefonoEmpleado = scanner.nextLine();
                    empleadoCRUD.actualizarEmpleados(nuevoNombreEmpleado, nuevoApellidoEmpleado, nuevoPuestoEmpleado, nuevoTelefonoEmpleado, idEmpleadoActualizar);
                    break;
                case 4:
                    System.out.print("Ingrese el ID del empleado a eliminar: ");
                    int idEmpleadoEliminar = scanner.nextInt();
                    empleadoCRUD.eliminarEmpleados(idEmpleadoEliminar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú para gestionar productos
    private static void menuProductos(Scanner scanner, ProductoCRUD productoCRUD) {
        while (true) {
            System.out.println("\n===== Gestión de Productos =====");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer de scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombreProducto = scanner.nextLine();
                    System.out.print("Ingrese el precio del producto: ");
                    double precioProducto = scanner.nextDouble();
                    System.out.print("Ingrese el stock del producto: ");
                    int stockProducto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la marca del producto: ");
                    String marcaProducto = scanner.nextLine();
                    System.out.print("Ingrese el ID de la categoría del producto: ");
                    int idCategoriaProducto = scanner.nextInt();
                    productoCRUD.agregarProducto(nombreProducto, precioProducto, stockProducto, marcaProducto, idCategoriaProducto);
                    break;
                case 2:
                    productoCRUD.mostrarProductos();
                    break;
                case 3:
                    System.out.print("Ingrese el ID del producto a actualizar: ");
                    int idProductoActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del producto: ");
                    String nuevoNombreProducto = scanner.nextLine();
                    System.out.print("Ingrese el nuevo precio del producto: ");
                    double nuevoPrecioProducto = scanner.nextDouble();
                    System.out.print("Ingrese el nuevo stock del producto: ");
                    int nuevoStockProducto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la nueva marca del producto: ");
                    String nuevaMarcaProducto = scanner.nextLine();
                    System.out.print("Ingrese el nuevo ID de la categoría del producto: ");
                    int nuevoIdCategoriaProducto = scanner.nextInt();
                    productoCRUD.actualizarProducto(idProductoActualizar, nuevoNombreProducto, nuevoPrecioProducto, nuevoStockProducto, nuevaMarcaProducto, nuevoIdCategoriaProducto);
                    break;
                case 4:
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    int idProductoEliminar = scanner.nextInt();
                    productoCRUD.eliminarProducto(idProductoEliminar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú para gestionar proveedores
    private static void menuProveedores(Scanner scanner, ProveedorCRUD proveedorCRUD) {
        while (true) {
            System.out.println("\n===== Gestión de Proveedores =====");
            System.out.println("1. Agregar Proveedor");
            System.out.println("2. Mostrar Proveedores");
            System.out.println("3. Actualizar Proveedor");
            System.out.println("4. Eliminar Proveedor");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer de scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del proveedor: ");
                    String nombreProveedor = scanner.nextLine();
                    System.out.print("Ingrese el teléfono del proveedor: ");
                    String telefonoProveedor = scanner.nextLine();
                    System.out.print("Ingrese el email del proveedor: ");
                    String emailProveedor = scanner.nextLine();
                    proveedorCRUD.agregarProveedor(nombreProveedor, telefonoProveedor, emailProveedor);
                    break;
                case 2:
                    proveedorCRUD.mostrarProveedor();
                    break;
                case 3:
                    System.out.print("Ingrese el ID del proveedor a actualizar: ");
                    int idProveedorActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del proveedor: ");
                    String nuevoNombreProveedor = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono del proveedor: ");
                    String nuevoTelefonoProveedor = scanner.nextLine();
                    System.out.print("Ingrese el nuevo email del proveedor: ");
                    String nuevoEmailProveedor = scanner.nextLine();
                    proveedorCRUD.actualizarProveedor(nuevoNombreProveedor, nuevoTelefonoProveedor, nuevoEmailProveedor, idProveedorActualizar);
                    break;
                case 4:
                    System.out.print(" -Ingrese el ID del proveedor a eliminar: ");
                    int idProveedorEliminar = scanner.nextInt();
                    proveedorCRUD.eliminarProveedor(idProveedorEliminar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("- Opción no válida.");
            }
        }
    }
}
