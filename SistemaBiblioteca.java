import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Modelo.Libro;
import Modelo.Usuario;
import Modelo.Prestamo;

public class SistemaBiblioteca {

    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    private Scanner scanner;

    public SistemaBiblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
        scanner = new Scanner(System.in);

        
        cargarLibrosDesdeCSV("libros.csv");
    }

    private void cargarLibrosDesdeCSV(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            br.readLine(); 

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                int id = Integer.parseInt(datos[0]);
                String titulo = datos[1];
                String autor = datos[2];
                int stock = Integer.parseInt(datos[3]);

                libros.add(new Libro(id, titulo, autor, stock));
            }
            System.out.println("Libros cargados correctamente desde " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== Menú Biblioteca ===");
            System.out.println("1. Listar libros disponibles");
            System.out.println("2. Pedir prestado un libro");
            System.out.println("3. Mostrar préstamos realizados");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listarLibros();
                    break;
                case 2:
                    pedirPrestamo();
                    break;
                case 3:
                    mostrarPrestamos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void listarLibros() {
        System.out.println("\n=== Libros Disponibles ===");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    private void pedirPrestamo() {
        System.out.println("\nIngrese su nombre: ");
        String nombreUsuario = scanner.nextLine();
        Usuario usuario = new Usuario(nombreUsuario);
        usuarios.add(usuario);

        listarLibros();
        System.out.print("Ingrese el ID del libro a prestar: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();

        Libro libroSeleccionado = null;
        for (Libro libro : libros) {
            if (libro.getId() == idLibro) {
                libroSeleccionado = libro;
                break;
            }
        }

        if (libroSeleccionado != null && libroSeleccionado.getStock() > 0) {
            Prestamo prestamo = new Prestamo(usuario, libroSeleccionado);
            prestamos.add(prestamo);
            libroSeleccionado.setStock(libroSeleccionado.getStock() - 1);
            System.out.println("Préstamo realizado con éxito. Fecha devolución: " + prestamo.getFechaDevolucion());
        } else {
            System.out.println("El libro no está disponible.");
        }
    }

    private void mostrarPrestamos() {
        System.out.println("\n=== Préstamos Realizados ===");
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }
    }

    public static void main(String[] args) {
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        sistema.mostrarMenu();
    }
}