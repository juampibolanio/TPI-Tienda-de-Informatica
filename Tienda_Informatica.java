import Conexiones.ConexionBD;
import BDCrud.CategoriaCRUD;
import BDCrud.ClienteCRUD;
import BDCrud.EmpleadoCRUD;
import BDCrud.ProductoCRUD;
import BDCrud.ProveedorCRUD;

import java.util.InputMismatchException;
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
            try {
                System.out.print("\n=========================");
                System.out.print("\n     Menú Principal ");
                System.out.println("\n=========================");
                System.out.println("1- Gestionar Categorías");
                System.out.println("2- Gestionar Clientes");
                System.out.println("3- Gestionar Empleados");
                System.out.println("4- Gestionar Productos");
                System.out.println("5- Gestionar Proveedores");
                System.out.print("6- Salir");
                System.out.println("\n=========================");
                System.out.print("[*] Seleccione una opción: ");


                int opcion = scanner.nextInt();
                scanner.nextLine();

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
                        menuProductos(scanner, productoCRUD, categoriaCRUD);
                        break;
                    case 5:
                        menuProveedores(scanner, proveedorCRUD);
                        break;
                    case 6:
                        System.out.println("[*] Ha salido del sistema. Vuelva pronto.");
                        conexionBD.cerrarConexionBD();
                        return;
                    default:
                        System.out.println("[!] Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[!] Error: Ingrese un carácter valido.");
                scanner.nextLine();
            }
        }
    }

    // Menú para gestionar categorías
    private static void menuCategorias(Scanner scanner, CategoriaCRUD categoriaCRUD) {

            while (true) {
                System.out.print("\n=============================");
                System.out.print("\n   Gestión de Categorías");
                System.out.println("\n=============================");
                System.out.println("1- Agregar Categoría");
                System.out.println("2- Mostrar Categorías");
                System.out.println("3- Actualizar Categoría");
                System.out.println("4- Eliminar Categoría");
                System.out.print("5- Volver al Menú Principal");
                System.out.println("\n============================");
                System.out.print("[*] Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("----- Lista de categorías actuales -----");
                        categoriaCRUD.mostrarCategoria();
                        System.out.print("- Ingrese el nombre de la categoría: ");
                        String nombreCategoria = scanner.nextLine();
                        if (categoriaCRUD.esNombreDuplicado(nombreCategoria)) {
                            System.out.println("[!] Error: el nombre de la categoría ya existe. Ingrese un nombre diferente.");
                            break;
                        }
                        categoriaCRUD.agregarCategoria(nombreCategoria);
                        break;
                    case 2:
                        categoriaCRUD.mostrarCategoria();
                        break;
                    case 3:
                        boolean repetir = true; // Bandera para mantener el ciclo hasta que la entrada sea correcta
                        while (repetir) {
                            try {
                                System.out.println("----- Lista de categorías actuales -----");
                                categoriaCRUD.mostrarCategoria();
                                System.out.print("- Ingrese el ID de la categoría a actualizar: ");
                                int idCategoriaActualizar = scanner.nextInt();
                                scanner.nextLine();
                                if (!categoriaCRUD.existeCategoria(idCategoriaActualizar)) {
                                    System.out.println("[!] Error, la categoría con la ID " + idCategoriaActualizar + " no existe.");
                                    continue;
                                }
                                System.out.print("- Ingrese el nuevo nombre de la categoría: ");
                                String nuevoNombreCategoria = scanner.nextLine();
                                if (categoriaCRUD.esNombreDuplicado(nuevoNombreCategoria)) {
                                    System.out.println("[!] Error, el nombre de la categoría ya existe. Ingrese un nombre diferente.");
                                    break;
                                }

                                categoriaCRUD.actualizarCategoria(nuevoNombreCategoria, idCategoriaActualizar);
                                repetir = false;
                            } catch (InputMismatchException e) {
                                System.out.println("[!] Error: Ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                        break;
                    case 4:
                        boolean repetir2 = true; // Bandera para mantener el ciclo hasta que la entrada sea correcta
                        while (repetir2) {
                            try {
                                System.out.println("----- Lista de categorías actuales -----");
                                categoriaCRUD.mostrarCategoria();
                                System.out.print("- Ingrese el ID de la categoría a eliminar: ");
                                int idCategoriaEliminar = scanner.nextInt();
                                scanner.nextLine();
                                if (!categoriaCRUD.existeCategoria(idCategoriaEliminar)) {
                                    System.out.println("[!] Error: la categoría con la ID " + idCategoriaEliminar + " no existe.");
                                    break;
                                }
                                categoriaCRUD.eliminarCategoria(idCategoriaEliminar);
                                repetir2 = false;
                            } catch (InputMismatchException e) {
                                System.out.println("[!] Error: Ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                        break;

                    case 5:
                        return;
                    default:
                        System.out.println("[!] Error: Opción no válida.");
                }
            }

    }

    // Menú para gestionar clientes
    private static void menuClientes(Scanner scanner, ClienteCRUD clienteCRUD) {
            while (true) {
                System.out.print("\n============================");
                System.out.print("\n     Gestión de Clientes ");
                System.out.println("\n============================");
                System.out.println("1- Agregar Cliente");
                System.out.println("2- Mostrar Clientes");
                System.out.println("3- Actualizar Cliente");
                System.out.println("4- Eliminar Cliente");
                System.out.print("5- Volver al Menú Principal");
                System.out.println("\n============================");
                System.out.print("[*] Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar buffer de scanner

                switch (opcion) {
                    case 1:
                        System.out.println("----- Lista actual de clientes -----");
                        clienteCRUD.mostrarCliente();
                        System.out.print("- Ingrese el nombre del cliente: ");
                        String nombreCliente = scanner.nextLine();
                        System.out.print("- Ingrese el apellido del cliente: ");
                        String apellidoCliente = scanner.nextLine();
                        System.out.print("- Ingrese el email del cliente: ");
                        String emailCliente = scanner.nextLine();
                        if (clienteCRUD.esEmailDuplicado(emailCliente)) {
                            System.out.println("[!] Error: El email ingresado ya está registrado. Ingrese un email diferente.");
                            break;
                        }
                        System.out.print("- Ingrese el teléfono del cliente: ");
                        String telefonoCliente = scanner.nextLine();
                        clienteCRUD.agregarCliente(nombreCliente, apellidoCliente, emailCliente, telefonoCliente);
                        break;
                    case 2:
                        clienteCRUD.mostrarCliente();
                        break;
                    case 3:
                        boolean repetir3 = true;
                        while (repetir3) {
                            System.out.println("----- Lista actual de clientes -----");
                            clienteCRUD.mostrarCliente();
                            try {
                                System.out.print("- Ingrese el ID del cliente a actualizar: ");
                                int idClienteActualizar = scanner.nextInt();
                                scanner.nextLine();
                                if (!clienteCRUD.existeCliente(idClienteActualizar)) {
                                    System.out.println("[!] Error: El cliente con ID " + idClienteActualizar + " no existe.");
                                    continue;
                                }
                                System.out.print("- Ingrese el nuevo nombre del cliente: ");
                                String nuevoNombreCliente = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo apellido del cliente: ");
                                String nuevoApellidoCliente = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo email del cliente: ");
                                String nuevoEmailCliente = scanner.nextLine();
                                if (clienteCRUD.esEmailDuplicado(nuevoEmailCliente)) {
                                    System.out.println("[!] Error: El email ingresado ya está registrado. Ingrese un email diferente.");
                                    continue;
                                }
                                System.out.print("- Ingrese el nuevo teléfono del cliente: ");
                                String nuevoTelefonoCliente = scanner.nextLine();
                                clienteCRUD.actualizarCliente(nuevoNombreCliente, nuevoApellidoCliente, nuevoEmailCliente, nuevoTelefonoCliente, idClienteActualizar);
                                repetir3 = false;
                            } catch (InputMismatchException e) {
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                            break;
                    case 4:
                        boolean repetir4 = true;
                        while (repetir4) {
                            try {
                                System.out.println("----- Lista actual de clientes -----");
                                clienteCRUD.mostrarCliente();
                                System.out.print("- Ingrese el ID del cliente a eliminar: ");
                                int idClienteEliminar = scanner.nextInt();
                                scanner.nextLine();
                                if (!clienteCRUD.existeCliente(idClienteEliminar)) {
                                    System.out.println("[!] Error: El cliente con ID " + idClienteEliminar + " no existe.");
                                    break;
                                }
                                clienteCRUD.eliminarCliente(idClienteEliminar);
                                repetir4 = false;
                            }catch (InputMismatchException e){
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                            break;
                    case 5:
                        return;
                    default:
                        System.out.println("[!] Error: Opción no válida.");
                }
            }
    }

    // Menú para gestionar empleados
    private static void menuEmpleados(Scanner scanner, EmpleadoCRUD empleadoCRUD) {
            while (true) {
                System.out.print("\n============================");
                System.out.print("\n    Gestión de Empleados ");
                System.out.println("\n============================");
                System.out.println("1- Agregar Empleado");
                System.out.println("2- Mostrar Empleados");
                System.out.println("3- Actualizar Empleado");
                System.out.println("4- Eliminar Empleado");
                System.out.print("5- Volver al Menú Principal");
                System.out.println("\n============================");
                System.out.print("[*] Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("----- Lista de empleados actuales -----");
                        empleadoCRUD.mostrarEmpleados();
                        System.out.print("- Ingrese el nombre del empleado: ");
                        String nombreEmpleado = scanner.nextLine();
                        System.out.print("- Ingrese el apellido del empleado: ");
                        String apellidoEmpleado = scanner.nextLine();
                        System.out.print("- Ingrese el puesto del empleado: ");
                        String puestoEmpleado = scanner.nextLine();
                        System.out.print("- Ingrese el teléfono del empleado: ");
                        String telefonoEmpleado = scanner.nextLine();
                        if (empleadoCRUD.esTelefonoDuplicado(telefonoEmpleado)) {
                            System.out.println("[!] Error: El teléfono ingresado ya está registrado. Ingrese un teléfono diferente.");
                            break;
                        }
                        empleadoCRUD.agregarEmpleados(nombreEmpleado, apellidoEmpleado, puestoEmpleado, telefonoEmpleado);
                        break;
                    case 2:
                        empleadoCRUD.mostrarEmpleados();
                        break;
                    case 3:
                        boolean repetir5 = true;
                        while (repetir5) {
                            try {
                                System.out.println("----- Lista de empleados actuales -----");
                                empleadoCRUD.mostrarEmpleados();
                                System.out.print("- Ingrese el ID del empleado a actualizar: ");
                                int idEmpleadoActualizar = scanner.nextInt();
                                scanner.nextLine();
                                if (!empleadoCRUD.existeEmpleado(idEmpleadoActualizar)) {
                                    System.out.println("[!] Error: El empleado con ID " + idEmpleadoActualizar + " no existe.");
                                    continue;
                                }
                                System.out.print("- Ingrese el nuevo nombre del empleado: ");
                                String nuevoNombreEmpleado = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo apellido del empleado: ");
                                String nuevoApellidoEmpleado = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo puesto del empleado: ");
                                String nuevoPuestoEmpleado = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo teléfono del empleado: ");
                                String nuevoTelefonoEmpleado = scanner.nextLine();
                                if (empleadoCRUD.esTelefonoDuplicado(nuevoTelefonoEmpleado)) {
                                    System.out.println("[!] Error: El teléfono ingresado ya está registrado. Ingrese un teléfono diferente.");
                                    continue;
                                }
                                empleadoCRUD.actualizarEmpleados(nuevoNombreEmpleado, nuevoApellidoEmpleado, nuevoPuestoEmpleado, nuevoTelefonoEmpleado, idEmpleadoActualizar);
                                repetir5 = false;
                            }catch (InputMismatchException e){
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                            break;
                    case 4:
                        boolean repetir6 = true;
                        while (repetir6) {
                            try {
                                System.out.println("----- Lista de empleados actuales -----");
                                empleadoCRUD.mostrarEmpleados();
                                System.out.print("- Ingrese el ID del empleado a eliminar: ");
                                int idEmpleadoEliminar = scanner.nextInt();
                                scanner.nextLine();
                                if (!empleadoCRUD.existeEmpleado(idEmpleadoEliminar)) {
                                    System.out.println("[!] Error: El empleado con ID " + idEmpleadoEliminar + " no existe.");
                                    continue;
                                }
                                empleadoCRUD.eliminarEmpleados(idEmpleadoEliminar);
                                repetir6 = false;
                            }catch (InputMismatchException e){
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }

                            break;

                    case 5:
                        return;
                    default:
                        System.out.println("[!] Error: Opción no válida.");
                }
            }
    }

    // Menú para gestionar productos
    private static void menuProductos(Scanner scanner, ProductoCRUD productoCRUD, CategoriaCRUD categoriaCRUD) {

            while (true) {
                System.out.print("\n============================");
                System.out.print("\n    Gestión de Productos ");
                System.out.println("\n============================");
                System.out.println("1- Agregar Producto");
                System.out.println("2- Mostrar Productos");
                System.out.println("3- Actualizar Producto");
                System.out.println("4- Eliminar Producto");
                System.out.print("5- Volver al Menú Principal");
                System.out.println("\n============================");
                System.out.print("[*] Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                            System.out.println("----- Lista actual de productos -----");
                            productoCRUD.mostrarProductos();
                            System.out.print("- Ingrese el nombre del producto: ");
                            String nombreProducto = scanner.nextLine();
                            System.out.print("- Ingrese el precio del producto: ");
                            double precioProducto = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("- Ingrese el stock del producto: ");
                            int stockProducto = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("- Ingrese la marca del producto: ");
                            String marcaProducto = scanner.nextLine();
                            System.out.println("- Selecciona una categoría para el producto: ");
                            categoriaCRUD.mostrarCategoria();
                            System.out.print("Ingrese el ID de la categoría del producto: ");
                            int idCategoriaProducto = scanner.nextInt();
                            scanner.nextLine();
                            if (!productoCRUD.existeCategoria(idCategoriaProducto)) {
                                System.out.println("[!] Error: La categoría con ID " + idCategoriaProducto + " no existe.");
                                break;
                            }
                            productoCRUD.agregarProducto(nombreProducto, precioProducto, stockProducto, marcaProducto, idCategoriaProducto);
                            break;
                    case 2:
                        productoCRUD.mostrarProductos();
                        break;
                    case 3:
                        boolean repetir7 = true;
                        while (repetir7) {
                            try {
                                System.out.println("----- Lista actual de productos -----");
                                productoCRUD.mostrarProductos();
                                System.out.print("- Ingrese el ID del producto a actualizar: ");
                                int idProductoActualizar = scanner.nextInt();
                                scanner.nextLine();
                                if (!productoCRUD.existeProducto(idProductoActualizar)) {
                                    System.out.println("[!] Error: El producto con ID " + idProductoActualizar + " no existe.");
                                    continue;
                                }
                                System.out.print("- Ingrese el nuevo nombre del producto: ");
                                String nuevoNombreProducto = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo precio del producto: ");
                                double nuevoPrecioProducto = scanner.nextDouble();
                                System.out.print("- Ingrese el nuevo stock del producto: ");
                                int nuevoStockProducto = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("- Ingrese la nueva marca del producto: ");
                                String nuevaMarcaProducto = scanner.nextLine();
                                System.out.println("- Selecciona una categoría para el producto: ");
                                categoriaCRUD.mostrarCategoria();
                                System.out.print("- Ingrese el nuevo ID de la categoría del producto: ");
                                int nuevoIdCategoriaProducto = scanner.nextInt();
                                scanner.nextLine();
                                if (!productoCRUD.existeCategoria(nuevoIdCategoriaProducto)) {
                                    System.out.println("[!] Error: La categoría con ID " + nuevoIdCategoriaProducto + " no existe.");
                                    break;
                                }
                                productoCRUD.actualizarProducto(idProductoActualizar, nuevoNombreProducto, nuevoPrecioProducto, nuevoStockProducto, nuevaMarcaProducto, nuevoIdCategoriaProducto);
                                repetir7 = false;
                            }catch (InputMismatchException e){
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                            break;
                    case 4:
                        boolean repetir8 = true;
                        while (repetir8) {
                            try {
                                System.out.println("----- Lista actual de productos -----");
                                productoCRUD.mostrarProductos();
                                System.out.print("- Ingrese el ID del producto a eliminar: ");
                                int idProductoEliminar = scanner.nextInt();
                                scanner.nextLine();
                                if (!productoCRUD.existeProducto(idProductoEliminar)) {
                                    System.out.println("[!] Error: El producto con ID " + idProductoEliminar + " no existe.");
                                    continue;
                                }
                                productoCRUD.eliminarProducto(idProductoEliminar);
                                repetir8 = false;
                            }catch (InputMismatchException e) {
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                            break;
                    case 5:
                        return;
                    default:
                        System.out.println("[!] Opción no válida. Intente nuevamente");
                }
            }
    }

    // Menú para gestionar proveedores
    private static void menuProveedores(Scanner scanner, ProveedorCRUD proveedorCRUD) {
        try {
            while (true) {
                System.out.print("\n============================");
                System.out.print("\n    Gestión de Proveedores ");
                System.out.println("\n============================");
                System.out.println("1- Agregar Proveedor");
                System.out.println("2- Mostrar Proveedores");
                System.out.println("3- Actualizar Proveedor");
                System.out.println("4- Eliminar Proveedor");
                System.out.print("5- Volver al Menú Principal");
                System.out.println("\n============================");
                System.out.print("[*] Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("----- Lista de proveedores actuales -----");
                        proveedorCRUD.mostrarProveedor();
                        System.out.print("- Ingrese el nombre del proveedor: ");
                        String nombreProveedor = scanner.nextLine();
                        System.out.print("- Ingrese el teléfono del proveedor: ");
                        String telefonoProveedor = scanner.nextLine();
                        System.out.print("- Ingrese el email del proveedor: ");
                        String emailProveedor = scanner.nextLine();
                        if (nombreProveedor.isEmpty() || telefonoProveedor.isEmpty() || emailProveedor.isEmpty()) {
                            System.out.println("[!] Error: Todos los campos son obligatorios.");
                            break;
                        }
                        proveedorCRUD.agregarProveedor(nombreProveedor, telefonoProveedor, emailProveedor);
                        break;
                    case 2:
                        proveedorCRUD.mostrarProveedor();
                        break;
                    case 3:
                        boolean repetir9 = true;
                        while (repetir9) {
                            try {
                                System.out.println("----- Lista de proveedores actuales -----");
                                proveedorCRUD.mostrarProveedor();
                                System.out.print("- Ingrese el ID del proveedor a actualizar: ");
                                int idProveedorActualizar = scanner.nextInt();
                                scanner.nextLine();
                                if (!proveedorCRUD.existeProveedor(idProveedorActualizar)) {
                                    System.out.println("[!] Error: El proveedor con ID " + idProveedorActualizar + " no existe.");
                                    continue;
                                }
                                System.out.print("- Ingrese el nuevo nombre del proveedor: ");
                                String nuevoNombreProveedor = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo teléfono del proveedor: ");
                                String nuevoTelefonoProveedor = scanner.nextLine();
                                System.out.print("- Ingrese el nuevo email del proveedor: ");
                                String nuevoEmailProveedor = scanner.nextLine();
                                if (nuevoNombreProveedor.isEmpty() || nuevoTelefonoProveedor.isEmpty() || nuevoEmailProveedor.isEmpty()) {
                                    System.out.println("[!] Error: Todos los campos son obligatorios.");
                                    continue;
                                }
                                proveedorCRUD.actualizarProveedor(nuevoNombreProveedor, nuevoTelefonoProveedor, nuevoEmailProveedor, idProveedorActualizar);
                                repetir9 = false;
                            }catch (InputMismatchException e){
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                            break;
                    case 4:
                        boolean repetir10 = true;
                        while (repetir10) {
                            try {
                                System.out.println("----- Lista de proveedores actuales -----");
                                proveedorCRUD.mostrarProveedor();
                                System.out.print("- Ingrese el ID del proveedor a eliminar: ");
                                int idProveedorEliminar = scanner.nextInt();
                                scanner.nextLine();
                                if (!proveedorCRUD.existeProveedor(idProveedorEliminar)) {
                                    System.out.println("[!] Error: El proveedor con ID " + idProveedorEliminar + " no existe.");
                                    continue;
                                }
                                proveedorCRUD.eliminarProveedor(idProveedorEliminar);
                                repetir10 = false;
                            }catch (InputMismatchException e){
                                System.out.println("[!] Error: ingrese un número válido.");
                                scanner.nextLine();
                            }
                        }
                            break;
                    case 5:
                        return;
                    default:
                        System.out.println("[!] Error: Opción no válida. Intente nuevamente");
                }
            }
        }catch (InputMismatchException e){
            System.out.println("[!] Por favor, ingrese un carácter válido.");
        }
    }
}