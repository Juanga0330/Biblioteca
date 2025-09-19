package Modelo;
import java.util.*;

public class Usuario {
    private String nombre;
    private String cedula;
    private List<Prestamo> prestamos;
    
    public Usuario(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.prestamos = new ArrayList<>();
    }
    
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public List<Prestamo> getPrestamos() { return prestamos; }
    
    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }
}
