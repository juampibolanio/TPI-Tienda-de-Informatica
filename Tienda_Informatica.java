
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
            System.out.print("\n============================");
            System.out.print("\n    Gestión de Productos ");
            System.out.println("\n============================");
            System.out.println("1- Agregar Producto");
            System.out.println("2- Mostrar Productos");
            System.out.println("3- Actualizar Producto");
            System.out.println("4- Eliminar Producto");
            System.out.print("5- Volver al Menú Principal");
            System.out.println("\n============================");
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
            System.out.print("\n============================");
            System.out.print("\n    Gestión de Proveedores ");
            System.out.println("\n============================");
            System.out.println("1- Agregar Proveedor");
            System.out.println("2- Mostrar Proveedores");
            System.out.println("3- Actualizar Proveedor");
            System.out.println("4- Eliminar Proveedor");
            System.out.print("5- Volver al Menú Principal");
            System.out.println("\n============================");
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
                    System.out.print("Ingrese el ID del proveedor a eliminar: ");
                    int idProveedorEliminar = scanner.nextInt();
                    proveedorCRUD.eliminarProveedor(idProveedorEliminar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
