/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.repositorios;

import criuni.modelos.Alumno;
import criuni.modelos.Libro;
import criuni.modelos.Prestamo;

import java.time.LocalDate;
import java.util.*;

/** Guarda todos los prestamos del sistema en un HashMap.
 * El ID del prestamo es la clave y el objeto Prestamo es el valor.
 */
/**
 *
 * @author Asus
 */
public class RepositorioPrestamo {
    private final Map<Integer, Prestamo> prestamos = new HashMap<>();
    private int contadorId = 1;

    /** Crea y guarda un nuevo prestamo.
     * Descuenta el stock de cada libro.
     */
    public Prestamo guardar(Alumno alumno, List<Libro> libros, LocalDate fechaPrestamo, LocalDate fechaDevolucionEsperada) {
        /** Descontar stock de cada libro.*/
        for (Libro libro : libros) {
            libro.descontarStock();
        }
        Prestamo prestamo = new Prestamo(contadorId++, alumno, libros, fechaPrestamo, fechaDevolucionEsperada);
        prestamos.put(prestamo.getId(), prestamo);
        return prestamo;
    }
    
    /**
     * Marca un préstamo como devuelto en la fecha indicada.
     * Retorna el préstamo actualizado, o null si no existe.
     */
    public Prestamo marcarDevuelto(int id, LocalDate fechaReal) {
        Prestamo prestamo = prestamos.get(id);
        if (prestamo == null || prestamo.estaDevuelto()) return null;
        prestamo.marcarDevuelto(fechaReal);
        return prestamo;
    }

    /** Elimina por id, retorna el prestamo eliminado o null si no existe.*/
    public Prestamo eliminar(int id) {return prestamos.remove(id); }

    /** Busca por id y retorna el prestamo encontrado.
     * Retorna null si no existe.*/
    public Prestamo buscar(int id) {return prestamos.get(id);}

    /** Retorna los prestamos almacenados dentro del map en forma de coleccion
     * de objetos.
     */
    public Collection<Prestamo> listarPrestamos(){
        return prestamos.values();
    }

    /** Retorna únicamente los préstamos que están vencidos (activos y fuera de fecha). */
    public List<Prestamo> listarVencidos() {
        List<Prestamo> vencidos = new ArrayList<>();
        for (Prestamo prestamo : prestamos.values()) {
            if (prestamo.estaVencido()) {
                vencidos.add(prestamo);
            }
        }
        return vencidos;
    }
}
