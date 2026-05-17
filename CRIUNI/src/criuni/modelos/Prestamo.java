/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.modelos;

import criuni.modelos.Libro;
import criuni.modelos.Alumno;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Asus
 */
public class Prestamo {
    public static final double MULTA_DIARIA= 2500.0;

    private int id;
    private Alumno alumno;
    private List<Libro> libros;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucion;

    /** Constructor */
    public Prestamo(int id, Alumno alumno, List<Libro> libros, LocalDate fechaPrestamo,LocalDate fechaDevolucionEsperada) {
        this.id = id;
        this.alumno = alumno;
        this.libros = libros;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.fechaDevolucion = null;
    }

    /** Getters */
    public int getId(){ return id; }
    public Alumno getAlumno(){ return alumno; }
    public List<Libro> getLibros(){ return libros; }
    public LocalDate getFechaPrestamo(){ return fechaPrestamo; }
    public LocalDate getFechaDevolucionEsperada(){ return fechaDevolucionEsperada; }
    public LocalDate getFechaDevolucion(){ return fechaDevolucion; }

    /** @return  true si el prestamo fue devuelto.*/
    public boolean estaDevuelto(){ return fechaDevolucion!=null; }

    /** Indica el prestamo como devuelto en cierta fecha establecida, y
     * repone el stock de el/los libro/s devuelto/s.
     */
    public void marcarDevuelto(LocalDate fechaEntreg){
        this.fechaDevolucion=fechaEntreg;
        for(Libro libro : libros) {
            libro.devolverStock();
        }
    }

    /** Verifica si la entrega del libro es tardio a la fecha
     * en la que se deberia entregar y lanza true si es asi.
     */
    public boolean estaVencido(){
        if (estaDevuelto()) return false;
        return LocalDate.now().isAfter(fechaDevolucionEsperada);
    }

    /** Verifica la fecha de entrega del prestamo y calcula la
     * multa de entrega tardia en el caso que eso ocurra.
     */
    public double calcularMulta(){
        LocalDate fechaCondicion;
        if (estaDevuelto()) {
            fechaCondicion=fechaDevolucion;
        } else {
            fechaCondicion= LocalDate.now();
        }
        if (!fechaCondicion.isAfter(fechaDevolucionEsperada)){
            return 0;
        }
        int diasDmora= fechaCondicion.getDayOfMonth()-fechaDevolucionEsperada.getDayOfMonth();
        return diasDmora*MULTA_DIARIA;
    }

    @Override
    public String toString(){
        String estado;
        if (estaDevuelto()) {
            estado= "Devuelto";
        } else {
            estado= "Activo (No devuelto)";
        }
        return "[ Prestamo #" +id+ " | Alumno= " + alumno.getNombreCompleto() + " | Libros= " +libros.size()+ " | Estado= " +estado+ " ]";
    }
}
