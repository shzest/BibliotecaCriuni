/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.controladores;

import criuni.modelos.Alumno;
import criuni.modelos.Libro;
import criuni.modelos.Prestamo;
import criuni.repositorios.RepositorioAlumno;
import criuni.repositorios.RepositorioLibros;
import criuni.repositorios.RepositorioPrestamo;
import criuni.util.Consola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Controlador que maneja la lógica de gestion para los Préstamos.
 * Permite crear, borrar, listar, devolver préstamos e informar vencidos.
 */
/**
 *
 * @author Asus
 */
public class ControladorPrestamo {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final RepositorioPrestamo repositorioPrestamo;
    private final RepositorioAlumno repositorioAlumno;
    private final RepositorioLibros repositorioLibro;
    public ControladorPrestamo(RepositorioPrestamo repositorioPrestamo, RepositorioAlumno repositorioAlumno, RepositorioLibros repositorioLibro) {
        this.repositorioPrestamo = repositorioPrestamo;
        this.repositorioAlumno = repositorioAlumno;
        this.repositorioLibro = repositorioLibro;
    }

    /**
     * Solicita al usuario el alumno, uno o varios libros con stock disponible,
     * y las fechas para crear el préstamo.
     */
    public void crearPrestamo() {
        Consola.separar();
        System.out.println("  CREAR PRESTAMO");
        Consola.separar();
        int idAlumno = Consola.leerEnteroPositivo("  ID del alumno    : ");
        Alumno alumno = repositorioAlumno.buscar(idAlumno);
        if (alumno == null) {
            System.out.printf(" No existe un alumno con ID #%d.%n", idAlumno);
            return;
        }
        System.out.printf("  Alumno: %s%n", alumno.getNombreCompleto());
        List<Libro> librosSeleccionados = new ArrayList<>();
        System.out.println("\n  Seleccione los libros a prestar (ingrese 0 para terminar):");
        while (true) {
            int idLibro = Consola.leerEntero("  ID del libro (0 para terminar): ", 0, Integer.MAX_VALUE);
            if (idLibro == 0) {
                if (librosSeleccionados.isEmpty()) {
                    System.out.println(" Debe seleccionar al menos un libro.");
                    continue;
                }
                break;
            }
            Libro libro = repositorioLibro.buscar(idLibro);
            if (libro == null) {
                System.out.printf(" No existe un libro con ID #%d.%n", idLibro);
                continue;
            }
            if (libro.getStock() <= 0) {
                System.out.printf(" El libro \"%s\" no tiene stock disponible.%n", libro.getTitulo());
                continue;
            }
            if (librosSeleccionados.contains(libro)) {
                System.out.println(" Ese libro ya fue agregado al prestamo.");
                continue;
            }
            librosSeleccionados.add(libro);
            System.out.printf(" \"%s\" agregado. (Stock restante: %d)%n", libro.getTitulo(), libro.getStock() - 1 );
        }
        LocalDate hoy = LocalDate.now();
        LocalDate fechaDevolucion = Consola.leerFecha("  Fecha de devolucion (dd/MM/yyyy): ");
        if (!fechaDevolucion.isAfter(hoy)) {
            System.out.println(" La fecha de devolucion debe ser posterior a hoy. Operacion cancelada.");
            return;
        }
        for (Libro libro : librosSeleccionados) {
            libro.descontarStock();
        }
        Prestamo nuevo = repositorioPrestamo.guardar(alumno, librosSeleccionados, hoy, fechaDevolucion);
        System.out.printf("%n Prestamo #%d creado exitosamente.%n", nuevo.getId());
        System.out.printf("  Alumno: %s | Libros: %d | Devolver antes del: %s%n", alumno.getNombreCompleto(), librosSeleccionados.size(), fechaDevolucion.format(FMT));
    }

    /** Elimina un préstamo por ID (solo si no fue devuelto aún, para proteger el stock).*/
    public void borrarPrestamo() {
        Consola.separar();
        System.out.println("  BORRAR PRESTAMO");
        Consola.separar();
        int id = Consola.leerEnteroPositivo("  ID del prestamo : ");
        Prestamo prestamo = repositorioPrestamo.buscar(id);
        if (prestamo == null) {
            System.out.printf(" No existe un prestamo con ID #%d.%n", id);
            return;
        }
        if (!prestamo.estaDevuelto()) {
            for (Libro libro : prestamo.getLibros()) {
                libro.devolverStock();
            }
        }
        repositorioPrestamo.eliminar(id);
        System.out.printf(" Prestamo #%d eliminado correctamente.%n", id);
    }

    /** Muestra todos los préstamos registrados en formato tabla. */
    public void listarPrestamos() {
        Consola.separar();
        System.out.println("  LISTADO DE PRESTAMOS");
        Consola.separar();
        Collection<Prestamo> lista = repositorioPrestamo.listarPrestamos();
        if (lista.isEmpty()) {
            System.out.println("  No hay prestamos registrados.");
            return;
        }
        imprimirEncabezadoTabla();
        for (Prestamo p : lista) {
            imprimirFilaPrestamo(p);
        }
        Consola.separar();
        System.out.printf("  Total de prestamos: %d%n", lista.size());
    }

    /** Marca un préstamo como devuelto e informa si hay multa. */
    public void devolverPrestamo() {
        Consola.separar();
        System.out.println("  DEVOLVER PRESTAMO");
        Consola.separar();
        int id = Consola.leerEnteroPositivo("  ID del prestamo : ");
        Prestamo prestamo = repositorioPrestamo.buscar(id);

        if (prestamo == null) {
            System.out.printf(" No existe un prestamo con ID #%d.%n", id);
            return;
        }
        if (prestamo.estaDevuelto()) {
            System.out.printf(" El prestamo #%d ya fue devuelto el %s.%n",
                    id, prestamo.getFechaDevolucion().format(FMT));
            return;
        }
        LocalDate hoy = LocalDate.now();
        Prestamo actualizado = repositorioPrestamo.marcarDevuelto(id, hoy);
        System.out.printf("%n Prestamo #%d marcado como devuelto (%s).%n", id, hoy.format(FMT));
        double multa = actualizado.calcularMulta();
        if (multa > 0) {
            System.out.printf(" MULTA POR MORA: %s%n", Consola.formatearGs(multa));
        } else {
            System.out.println("  Sin multa. Devuelto a tiempo.");
        }
    }

    /** Genera un informe de préstamos vencidos con el monto de multa acumulado. */
    public void informeVencidos() {
        Consola.separar();
        System.out.println("  INFORME DE PRESTAMOS VENCIDOS");
        Consola.separar();
        List<Prestamo> vencidos = repositorioPrestamo.listarVencidos();
        if (vencidos.isEmpty()) {
            System.out.println(" No hay prestamos vencidos actualmente.");
            return;
        }
        System.out.printf("  %-5s  %-22s  %-12s  %-12s  %-10s  %s%n", "ID", "Alumno", "Vto. Esperado", "Hoy", "Dias mora", "Multa");
        Consola.separar();
        double totalMultas = 0;
        for (Prestamo p : vencidos) {
            int diasMora =
                    LocalDate.now().getDayOfMonth()
                            - p.getFechaDevolucionEsperada().getDayOfMonth();
            double multa = p.calcularMulta();
            totalMultas += multa;
            System.out.printf("  %-5d  %-22s  %-12s  %-12s  %-10d  %s%n", p.getId(), Consola.truncar(p.getAlumno().getNombreCompleto(), 22), p.getFechaDevolucionEsperada().format(FMT), LocalDate.now().format(FMT), diasMora, Consola.formatearGs(multa));
        }
        Consola.separar();
        System.out.printf("  Préstamos vencidos: %d  |  Total multas: %s%n", vencidos.size(), Consola.formatearGs(totalMultas));
    }

    private void imprimirEncabezadoTabla() {
        System.out.printf("  %-5s  %-22s  %-20s  %-12s  %-12s  %-10s  %s%n", "ID", "Alumno", "Libro(s)", "Prestamo", "Vto. Esp.", "Estado", "Multa");
        Consola.separar();
    }

    private void imprimirFilaPrestamo(Prestamo p) {
        String librosStr = "";
        for (Libro libro : p.getLibros()) {
            librosStr += libro.getTitulo() + ", ";
        }
        if (!librosStr.isEmpty()) {
            librosStr = librosStr.substring(0, librosStr.length() - 2);
        }
        String estado;
        if (p.estaDevuelto()) {
            estado = "Devuelto";
        } else if (p.estaVencido()) {
            estado = "VENCIDO";
        } else {
            estado = "Activo";
        }
        double multa = p.calcularMulta();
        String multaStr;
        if (multa > 0) {
            multaStr = Consola.formatearGs(multa);
        } else {
            multaStr = "-";
        }
        System.out.printf("  %-5d  %-22s  %-20s  %-12s  %-12s  %-10s  %s%n", p.getId(), Consola.truncar(p.getAlumno().getNombreCompleto(), 22), Consola.truncar(librosStr, 20), p.getFechaPrestamo().format(FMT), p.getFechaDevolucionEsperada().format(FMT), estado, multaStr);
    }

}
