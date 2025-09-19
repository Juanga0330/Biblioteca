import java.util.*;
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

       
        libros.add(new Libro(1, "El Quijote", "Miguel de Cervantes", 3));
        libros.add(new Libro(2, "Cien Años de Soledad", "Gabriel García Márquez", 5));
        libros.add(new Libro(3, "1984", "George Orwell", 2));
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== BIBLIOTECA ===");
            System.out.println("1. Listar libros disponibles");
            System.out.println("2. Realizar préstamo");
            System.out.println("3. Mostrar préstamos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: listarLibros(); break;
                case 2: realizarPrestamo(); break;
                case 3: mostrarPrestamos(); break;
            }
        } while (opcion != 0);
    }

    private void listarLibros() {
        System.out.println("\n=== LIBROS DISPONIBLES ===");
        for (Libro libro : libros) {
            if (libro.getStock() > 0) {
                System.out.println(libro);
            }
        }
    }

    private void realizarPrestamo() {
        System.out.print("Nombre usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, cedula);
        usuarios.add(usuario);

        listarLibros();
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();

        for (Libro libro : libros) {
            if (libro.getId() == idLibro && libro.getStock() > 0) {
                Prestamo prestamo = new Prestamo(usuario, libro);
                prestamos.add(prestamo);
                usuario.agregarPrestamo(prestamo);
                libro.setStock(libro.getStock() - 1);
                System.out.println("Préstamo realizado: " + prestamo);
                return;
            }
        }
        System.out.println("Libro no disponible");
    }

    private void mostrarPrestamos() {
        System.out.println("\n=== PRÉSTAMOS ===");
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }
    }

    public static void main(String[] args) {
        new SistemaBiblioteca().mostrarMenu();
    }
}
