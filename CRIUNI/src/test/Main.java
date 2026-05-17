/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import criuni.controladores.ControladorAlumno;
import criuni.controladores.ControladorLibro;
import criuni.controladores.ControladorPrestamo;
import criuni.repositorios.RepositorioAlumno;
import criuni.repositorios.RepositorioLibros;
import criuni.repositorios.RepositorioPrestamo;
import criuni.vista.MenuPrincipal;

/**
 *
 * @author Asus
 */
public class Main {
    public static void main(String[] args) {
        RepositorioLibros libroRepo    = new RepositorioLibros();
        RepositorioAlumno alumnoRepo   = new RepositorioAlumno();
        RepositorioPrestamo prestamoRepo = new RepositorioPrestamo();

        ControladorLibro    libroCtrl    = new ControladorLibro(libroRepo);
        ControladorAlumno   alumnoCtrl   = new ControladorAlumno(alumnoRepo);
        ControladorPrestamo prestamoCtrl = new ControladorPrestamo(prestamoRepo, alumnoRepo, libroRepo);

        MenuPrincipal menu = new MenuPrincipal(libroCtrl, alumnoCtrl, prestamoCtrl);
        menu.iniciar();
    }
}
