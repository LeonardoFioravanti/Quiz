package ejeintegrador;

import java.util.Scanner;

enum TipoProducto {
    ALIMENTOS,
    BEBIDAS,
    HIGIENE,
    LIMPIEZA
}

class Producto {
    private String codigo;
    private String nombre;
    private String nombreEmpleado;
    private String cedulaEmpleado;
    private int cantidad;
    private double precioBase;
    private TipoProducto tipoProducto;
    private double precioBruto;
    private double gananciaEsperada;

    public Producto(String codigo, String nombre, String nombreEmpleado, String cedulaEmpleado,
                    int cantidad, double precioBase, TipoProducto tipoProducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreEmpleado = nombreEmpleado;
        this.cedulaEmpleado = cedulaEmpleado;
        this.cantidad = cantidad;
        this.precioBase = precioBase;
        this.tipoProducto = tipoProducto;
        calcularPrecioBruto();
        calcularGananciaEsperada();
    }

    private void calcularPrecioBruto() {
        switch (tipoProducto) {
            case ALIMENTOS:
                precioBruto = precioBase * 0.20 + precioBase;
                break;
            case BEBIDAS:
                precioBruto = precioBase * 0.30 + precioBase;
                break;
            case HIGIENE:
                precioBruto = precioBase * 0.25 + precioBase + 500;
                break;
            case LIMPIEZA:
                precioBruto = precioBase * 0.19 + precioBase * 0.04 + 1000 + precioBase;
                break;
        }
    }

    private void calcularGananciaEsperada() {
        gananciaEsperada = (precioBruto - precioBase) * cantidad;
    }

    public double getPrecioBruto() {
        return precioBruto;
    }

    public double getGananciaEsperada() {
        return gananciaEsperada;
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Empleado que agrego el producto: " + nombreEmpleado);
        System.out.println("Cedula del empleado: " + cedulaEmpleado);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio base: " + precioBase);
        System.out.println("Tipo de producto: " + tipoProducto);
        System.out.println("Precio bruto: " + precioBruto);
        System.out.println("Ganancia esperada: " + gananciaEsperada);
    }
}

class Supermercado {
    private String nombre;
    private String codigo;

    public Supermercado(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre del supermercado: " + nombre);
        System.out.println("Codigo del supermercado: " + codigo);
    }
}

public class Ejeintegrador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del supermercado:");
        String nombreSupermercado = scanner.nextLine();
        System.out.println("Ingrese el codigo del supermercado:");
        String codigoSupermercado = scanner.nextLine();

        Supermercado supermercado = new Supermercado(nombreSupermercado, codigoSupermercado);

        System.out.println("Ingrese el nombre del empleado:");
        String nombreEmpleado = scanner.nextLine();
        System.out.println("Ingrese la cedula del empleado:");
        String cedulaEmpleado = scanner.nextLine();

        System.out.println("Ingrese la cantidad de productos a agregar:");
        int cantidadProductos = scanner.nextInt();
        scanner.nextLine();
        
        Producto[] productos = new Producto[cantidadProductos];

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println("Ingrese el codigo del producto " + (i + 1) + ":");
            String codigoProducto = scanner.nextLine();
            System.out.println("Ingrese el nombre del producto " + (i + 1) + ":");
            String nombreProducto = scanner.nextLine();
            System.out.println("Ingrese la cantidad del producto " + (i + 1) + ":");
            int cantidad = scanner.nextInt();
            System.out.println("Ingrese el precio base del producto " + (i + 1) + ":");
            double precioBase = scanner.nextDouble();
            System.out.println("Ingrese el tipo de producto " + (i + 1) + " (ALIMENTOS, BEBIDAS, HIGIENE, LIMPIEZA):");
            TipoProducto tipoProducto = TipoProducto.valueOf(scanner.next().toUpperCase());
            scanner.nextLine();

            productos[i] = new Producto(codigoProducto, nombreProducto, nombreEmpleado, cedulaEmpleado,
                    cantidad, precioBase, tipoProducto);
        }
        supermercado.mostrarInformacion();
        System.out.println("\nProductos agregados:");
        double totalGanancia = 0;
        for (Producto producto : productos) {
            producto.mostrarInformacion();
            totalGanancia += producto.getGananciaEsperada();
            System.out.println();
        }
        System.out.println("Monto total de ganancia esperada: " + totalGanancia);
    }
}
