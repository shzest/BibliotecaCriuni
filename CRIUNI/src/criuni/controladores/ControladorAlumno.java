/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.controladores;
import criuni.modelos.Alumno;
import criuni.repositorios.RepositorioAlumno;
import criuni.util.Consola;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Controlador que maneja la lógica de gestiones para los Alumnos.
 */
/**
 *
 * @author Asus
 */
public class ControladorAlumno {
    private final RepositorioAlumno repositorio;

    public ControladorAlumno(RepositorioAlumno repositorio) {
        this.repositorio = repositorio;
    }

    /** Solicita datos al usuario y registra un nuevo alumno. */
    public void crearAlumno() {
        Consola.separar();
        System.out.println("  REGISTRAR ALUMNO");
        Consola.separar();
        String nombre = Consola.leerTexto("  Nombre completo  : ");
        String nroCi = Consola.leerTexto("  Nro. de documento: ");
        String email= Consola.leerTexto("  Email            : ");
        String celular  = Consola.leerTexto("  Celular          : ");
        LocalDate fechaNac = Consola.leerFecha("  Fecha nacimiento (dd/MM/yyyy): ");
        String facultadProcedencia  = Consola.leerTexto("  Facultad         : ");
        Alumno nuevo = repositorio.guardar(nombre, nroCi, email, celular, fechaNac, facultadProcedencia);
        System.out.printf("%n  Alumno registrado con ID #%d.%n", nuevo.getId());
    }

    /** Solicita el ID y permite editar los datos de un alumno. */
    public void editarAlumno() {
        Consola.separar();
        System.out.println("  EDITAR ALUMNO");
        Consola.separar();
        int id = Consola.leerEnteroPositivo("  ID del alumno : ");
        Alumno alumno = repositorio.buscar(id);
        if (alumno == null) {
            System.out.printf(" No existe un alumno con ID #%d.%n", id);
            return;
        }

        System.out.printf("  Alumno actual: %s | Doc: %s%n", alumno.getNombreCompleto(), alumno.getNroCi());
        System.out.println("  (Presione Enter para mantener el valor actual)");
        Consola.separar();
        String nombre= Consola.leerTextoOpcional("  Nombre completo : ", alumno.getNombreCompleto());
        String nroCi = Consola.leerTextoOpcional("  Nro. de documento: ", alumno.getNroCi());
        String email = Consola.leerTextoOpcional("  Email           : ", alumno.getEmail());
        String celular = Consola.leerTextoOpcional("  Celular         : ", alumno.getCelular());
        LocalDate fechaNac = Consola.leerFechaOpcional("  Fecha nacimiento (dd/MM/yyyy): ", alumno.getFechaNacimiento());
        String facultad = Consola.leerTextoOpcional("  Facultad        : ", alumno.getFacultadProcedencia());
        repositorio.editar(id, nombre, nroCi , email, celular, fechaNac, facultad);
        System.out.println("\n Alumno editado correctamente.");
    }

    /** Solicita el ID y elimina el alumno si existe.*/
    public void borrarAlumno() {
        Consola.separar();
        System.out.println("  BORRAR ALUMNO");
        Consola.separar();
        int id = Consola.leerEnteroPositivo("  ID del alumno : ");
        Alumno eliminado = repositorio.eliminar(id);
        if (eliminado == null) {
            System.out.printf(" No existe un alumno con ID #%d.%n"+ id);
        } else {
            System.out.printf(" Alumno \"%s\" eliminado correctamente.%n", eliminado.getNombreCompleto());
        }
    }

    /** Muestra todos los alumnos en formato tabla. */
    public void listarAlumnos() {
        Consola.separar();
        System.out.println("  LISTADO DE ALUMNOS");
        Consola.separar();
        Collection<Alumno> lista = repositorio.listarAlumnos();
        if (lista.isEmpty()) {
            System.out.println("  No hay alumnos registrados.");
            return;
        }
        System.out.printf("  %-4s  %-25s  %-10s  %-22s  %-12s  %-20s%n", "ID", "Nombre", "Documento", "Email", "Celular", "Facultad");
        Consola.separar();
        for (Alumno a : lista) {
            System.out.printf("  %-4d  %-25s  %-10s  %-22s  %-12s  %-20s%n", a.getId(), Consola.truncar(a.getNombreCompleto(), 25), a.getNroCi(), Consola.truncar(a.getEmail(), 22), a.getCelular(), Consola.truncar(a.getFacultadProcedencia(), 20));
        }
        Consola.separar();
        System.out.printf("  Total de alumnos: %d%n", lista.size());
    }

    public RepositorioAlumno getRepositorio() { return repositorio; }

}
