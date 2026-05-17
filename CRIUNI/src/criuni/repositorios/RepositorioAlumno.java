/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.repositorios;

import criuni.modelos.Alumno;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/** Guarda todos los alumnos del sistema en un HashMap.
 * El ID del alumno es la clave y el objeto alumno es el valor.
 */
/**
 *
 * @author Asus
 */
public class RepositorioAlumno {
    private Map<Integer, Alumno> alumnos = new HashMap<>();
    private int contadorID= 1;

    /** Guarda un alumno en el Map recibiendo los datos minimos necesarios
     * y luego incrementa el Id para evitar repeticiones.
     */
    public Alumno guardar(String nombre, String nroCI, String email, String celular, LocalDate fechaNacim, String facultad){
        Alumno alumno = new Alumno(contadorID, nombre, nroCI, email, celular, fechaNacim, facultad);
        alumnos.put(alumno.getId(), alumno);
        contadorID++;
        return alumno;
    }

    /** Elimina por id, retorna al alumno eliminado o null si no existe.*/
    public Alumno eliminar (int id){ return alumnos.remove(id); }

    /** Recibe datos y busca por id en el Map al alumno, settea
     * los datos y retorna true si lo encuentra, si no existe
     * retorna false.
     */
    public boolean editar(int id, String nombre, String nroCI, String email, String celular, LocalDate fechaNacim, String facultad ){
        Alumno alumno = alumnos.get(id);
        if (alumno == null) return false;
        alumno.setNombreCompleto(nombre);
        alumno.setNroCi(nroCI);
        alumno.setEmail(email);
        alumno.setCelular(celular);
        alumno.setFechaNacimiento(fechaNacim);
        alumno.setFacultadProcedencia(facultad);
        return true;
    }

    /** Retorna el nombre de los Alumnos dentro del Map en forma de collecion
     * de objetos almacenados.
     */
    public Collection<Alumno> listarAlumnos (){ return alumnos.values(); }

    /** Busca por id y retorna al alumno encontrado.
     * Retorna null si no existe.*/
    public Alumno buscar(int id){
        return alumnos.get(id);
    }
}
