/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.vista;

import criuni.controladores.ControladorAlumno;
import criuni.controladores.ControladorLibro;
import criuni.controladores.ControladorPrestamo;
import criuni.util.Consola;

/**
 *
 * @author Asus
 */
public class MenuPrincipal {
    private static final String NOMBRE_SISTEMA = "CRIUNI - Sistema de Biblioteca";
    private static final String NOMBRE_INSTITUCION = "Universidad Nacional de Itapua (UNI)";
    private final ControladorLibro libroController;
    private final ControladorAlumno alumnoController;
    private final ControladorPrestamo prestamoController;

    public MenuPrincipal(ControladorLibro libroController, ControladorAlumno alumnoController, ControladorPrestamo prestamoController) {
        this.libroController = libroController;
        this.alumnoController = alumnoController;
        this.prestamoController = prestamoController;
    }

    /** Inicia el programa principal. */
    public void iniciar() {
        mostrarBienvenida();
        boolean ejecutando = true;
        while (ejecutando) {
            mostrarMenuPrincipal();
            int opcion = Consola.leerEntero(" Seleccione una opcion: ", 0, 3);
            switch (opcion) {
                case 1:
                    ejecutarMenuLibros();
                    break;
                case 2:
                    ejecutarMenuAlumnos();
                    break;
                case 3:
                    ejecutarMenuPrestamos();
                    break;
                case 0:
                    ejecutando = false;
                    mostrarDespedida();
                    break;
            }
        }
    }

    private void mostrarMenuPrincipal() {
        Consola.separar();
        System.out.printf("  %-66s%n", NOMBRE_SISTEMA);
        System.out.printf("  %-66s%n", NOMBRE_INSTITUCION);
        Consola.separar();
        System.out.println("  MENU PRINCIPAL");
        Consola.separar();
        System.out.println("  [ 1 ] - Gestion de Libros");
        System.out.println("  [ 2 ] - Gestion de Alumnos");
        System.out.println("  [ 3 ] - Gestion de Prestamos");
        System.out.println("  [ 0 ] - Salir");
        Consola.separar();
    }

    private void ejecutarMenuLibros() {
        boolean enMenu = true;
        while (enMenu) {
            Consola.separar();
            System.out.println("  GESTION DE LIBROS");
            Consola.separar();
            System.out.println("  [ 1 ] - Crear un Libro");
            System.out.println("  [ 2 ] - Editar un Libro");
            System.out.println("  [ 3 ] - Borrar un Libro");
            System.out.println("  [ 4 ] - Listar Libros");
            System.out.println("  [ 0 ] - Volver al Menu Principal");
            Consola.separar();
            int opcion = Consola.leerEntero("  Seleccione una opcion: ", 0, 4);
            switch (opcion) {
                case 1:
                    libroController.crearLibro();
                    Consola.esperarEnter();
                    break;
                case 2:
                    libroController.editarLibro();
                    Consola.esperarEnter();
                    break;
                case 3:
                    libroController.borrarLibro();
                    Consola.esperarEnter();
                    break;
                case 4:
                    libroController.listarLibros();
                    Consola.esperarEnter();
                    break;
                case 0:
                    enMenu = false;
                    break;
            }
        }
    }

    private void ejecutarMenuAlumnos() {
        boolean enMenu = true;
        while (enMenu) {
            Consola.separar();
            System.out.println("  GESTION DE ALUMNOS");
            Consola.separar();
            System.out.println("  [ 1 ] - Registrar un Alumno");
            System.out.println("  [ 2 ] - Editar un Alumno");
            System.out.println("  [ 3 ] - Borrar un Alumno");
            System.out.println("  [ 4 ] - Listar Alumnos");
            System.out.println("  [ 0 ] - Volver al Menu Principal");
            Consola.separar();
            int opcion = Consola.leerEntero("  Seleccione una opcion: ", 0, 4);
            switch (opcion) {
                case 1:
                    alumnoController.crearAlumno();
                    Consola.esperarEnter();
                    break;
                case 2:
                    alumnoController.editarAlumno();
                    Consola.esperarEnter();
                    break;
                case 3:
                    alumnoController.borrarAlumno();
                    Consola.esperarEnter();
                    break;
                case 4:
                    alumnoController.listarAlumnos();
                    Consola.esperarEnter();
                    break;
                case 0:
                    enMenu = false;
                    break;
            }
        }
    }

    private void ejecutarMenuPrestamos() {
        boolean enMenu = true;
        while (enMenu) {
            Consola.separar();
            System.out.println("  GESTION DE PRESTAMOS");
            Consola.separar();
            System.out.println("  [ 1 ] - Crear un Prestamo");
            System.out.println("  [ 2 ] - Borrar un Prestamo");
            System.out.println("  [ 3 ] - Listar Prestamos");
            System.out.println("  [ 4 ] - Devolver un Prestamo");
            System.out.println("  [ 5 ] - Informe de Prestamos Vencidos");
            System.out.println("  [ 0 ] - Volver al Menu Principal");
            Consola.separar();
            int opcion = Consola.leerEntero("  Seleccione una opcion: ", 0, 5);
            switch (opcion) {
                case 1:
                    prestamoController.crearPrestamo();
                    Consola.esperarEnter();
                    break;
                case 2:
                    prestamoController.borrarPrestamo();
                    Consola.esperarEnter();
                    break;
                case 3:
                    prestamoController.listarPrestamos();
                    Consola.esperarEnter();
                    break;
                case 4:
                    prestamoController.devolverPrestamo();
                    Consola.esperarEnter();
                    break;
                case 5:
                    prestamoController.informeVencidos();
                    Consola.esperarEnter();
                    break;
                case 0:
                    enMenu = false;
                    break;
            }
        }
    }

    private void mostrarBienvenida() {
        Consola.separar();
        System.out.println("  Bienvenido al Sistema de Biblioteca CRIUNI");
        System.out.println("  " + NOMBRE_INSTITUCION);
        Consola.separar();
        Consola.esperarEnter();
    }
    private void mostrarDespedida() {
        Consola.separar();
        System.out.println("  Gracias por usar el Sistema de Biblioteca CRIUNI.");
        System.out.println("  Hasta pronto!");
        Consola.separar();
    }
}
