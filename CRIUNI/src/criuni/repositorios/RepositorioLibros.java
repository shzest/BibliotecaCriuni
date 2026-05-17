/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.repositorios;

import criuni.modelos.Libro;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** Guarda todos los libros del sistema en un HashMap.
 * El ID del libro es la clave y el objeto libro es el valor.
 */
/**
 *
 * @author Asus
 */
public class RepositorioLibros {
    private Map<Integer, Libro> libros= new HashMap<>();
    private int contadorId=1;

    /** Guarda un libro en el Map recibiendo los datos minimos necesarios
     * y luego incrementa el Id para evitar repeticiones.
     */
    public Libro guardar(String titulo, String editorial, int anhoPublicacion, String autor, int stock){
        Libro libro = new Libro(contadorId, titulo, editorial, anhoPublicacion, autor, stock);
        libros.put(libro.getId(), libro);
        contadorId++;
        return libro;
    }

    /** Elimina por id, retorna al libro eliminado o null si no existe.*/
    public Libro eliminar(int id) {return libros.remove(id); }

    /** Recibe datos y busca por id en el Map el libro, settea
     * los datos y retorna true si lo encuentra, si no existe
     * retorna false.
     */
    public boolean editar(int id,String titulo, String editorial, int anhoPublicacion, String autor, int stock){
        Libro libro = libros.get(id);
        if(libro==null) return false;
        libro.setTitulo(titulo);
        libro.setEditorial(editorial);
        libro.setAnhoPublicacion(anhoPublicacion);
        libro.setAutor(autor);
        libro.setStock(stock);
        return true;
    }

    /** Retorna los libros almacenados dentro del Map en forma de collecion
     * de objetos.
     */
    public Collection<Libro> listarLibros (){ return libros.values(); }

    /** Busca por id y retorna el libro encontrado.
     * Retorna null si no existe.*/
    public Libro buscar(int id){
        return libros.get(id);
    }
}
