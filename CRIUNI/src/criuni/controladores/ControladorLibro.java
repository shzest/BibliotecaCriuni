/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.controladores;

import criuni.modelos.Libro;
import criuni.repositorios.RepositorioLibros;
import criuni.util.Consola;

import java.util.Collection;

/**
 * Controlador que maneja la lógica de gestiones para los Libros.
 * Coordina entre la Consola y el repositorio.
 */
/**
 *
 * @author Asus
 */
public class ControladorLibro {
    private final RepositorioLibros repositorio;

    public ControladorLibro(RepositorioLibros repositorio) {
        this.repositorio = repositorio;
    }

    /** Solicita datos al usuario y crea un nuevo libro. */
    public void crearLibro() {
        Consola.separar();
        System.out.println("  CREAR LIBRO");
        Consola.separar();
        String titulo = Consola.leerTexto("  Titulo       : ");
        String autor = Consola.leerTexto("  Autor        : ");
        String editorial = Consola.leerTexto("  Editorial    : ");
        int anho = Consola.leerEntero("  Anho de pub.  : ", 1000, 2100);
        int stock = Consola.leerEntero("  Stock inicial: ", 0, 9999);
        Libro nuevo = repositorio.guardar(titulo, editorial, anho, autor, stock);
        System.out.printf("%n Libro creado con ID #%d.%n", nuevo.getId());
    }

    /** Solicita el ID y permite editar un libro existente. */
    public void editarLibro() {
        Consola.separar();
        System.out.println("  EDITAR LIBRO");
        Consola.separar();
        int id= Consola.leerEnteroPositivo("  ID del libro : ");
        Libro libro= repositorio.buscar(id);
        if (libro==null) {
            System.out.printf(" No existe un libro con ID #%d.%n", id);
            return;
        }
        System.out.printf("  Libro actual: %s - %s (%d) | Stock: %d%n", libro.getTitulo(), libro.getAutor(), libro.getAnhoPublicacion(), libro.getStock());
        System.out.println("  (Presione Enter para mantener el valor actual)");
        Consola.separar();
        String titulo = Consola.leerTextoOpcional("  Titulo       : ", libro.getTitulo());
        String autor = Consola.leerTextoOpcional("  Autor        : ", libro.getAutor());
        String editorial = Consola.leerTextoOpcional("  Editorial    : ", libro.getEditorial());
        int anho = Consola.leerEnteroOpcional("  Anho de pub.  : ", libro.getAnhoPublicacion(), 1000, 2100);
        int stock= Consola.leerEnteroOpcional("  Stock        : ", libro.getStock(), 0, 9999);
        repositorio.editar(id, titulo, editorial, anho, autor, stock);
        System.out.println("\n Libro editado correctamente.");
    }

    /** Solicita el ID y borra el libro si existe. */
    public void borrarLibro() {
        Consola.separar();
        System.out.println("  BORRAR LIBRO");
        Consola.separar();
        int id = Consola.leerEnteroPositivo("  ID del libro : ");
        Libro eliminado = repositorio.eliminar(id);
        if (eliminado == null) {
            System.out.printf(" No existe un libro con ID #%d.%n", id);
        } else {
            System.out.printf(" Libro \"%s\" eliminado correctamente.%n", eliminado.getTitulo());
        }
    }

    /** Muestra todos los libros en formato tabla. */
    public void listarLibros() {
        Consola.separar();
        System.out.println("  LISTADO DE LIBROS");
        Consola.separar();
        Collection<Libro> lista = repositorio.listarLibros();
        if (lista.isEmpty()) {
            System.out.println("  No hay libros registrados.");
            return;
        }
        System.out.printf("  %-4s  %-30s  %-20s  %-18s  %4s  %5s%n", "ID", "Titulo", "Autor", "Editorial", "Anho", "Stock");
        Consola.separar();
        for (Libro l : lista) {
            System.out.printf("  %-4d  %-30s  %-20s  %-18s  %4d  %5d%n", l.getId(), Consola.truncar(l.getTitulo(), 30), Consola.truncar(l.getAutor(), 20), Consola.truncar(l.getEditorial(), 18), l.getAnhoPublicacion(), l.getStock());
        }
        Consola.separar();
        System.out.printf("  Total de libros: %d%n", lista.size());
    }

    public RepositorioLibros getRepositorio() { return repositorio; }
}
