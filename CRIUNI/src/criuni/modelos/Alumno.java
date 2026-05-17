/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.modelos;

import java.time.LocalDate;

/**
 *
 * @author Asus
 */
public class Alumno {
    private int id;
    private String nombreCompleto;
    private String nroCi;
    private String email;
    private String celular;
    private LocalDate fechaNacimiento;
    private String facultadProcedencia;

    public Alumno(int id, String nombreCompleto, String nroCi,String email, String celular, LocalDate fechaNacimiento, String facultadProcedencia) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.nroCi = nroCi;
        this.email = email;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.facultadProcedencia = facultadProcedencia;
    }

    /** Getters */
    public int getId(){ return id; }
    public String getNombreCompleto(){ return nombreCompleto; }
    public String getNroCi(){ return nroCi; }
    public String getEmail(){ return email; }
    public String getCelular(){ return celular; }
    public LocalDate getFechaNacimiento(){ return fechaNacimiento; }
    public String getFacultadProcedencia(){ return facultadProcedencia; }

    /** Setters */
    public void setNombreCompleto(String nombreCompleto){ this.nombreCompleto = nombreCompleto; }
    public void setNroCi(String nroCi){this.nroCi = nroCi;}
    public void setEmail(String email){ this.email = email; }
    public void setCelular(String celular){ this.celular = celular; }
    public void setFechaNacimiento(LocalDate fechaNacimiento){ this.fechaNacimiento = fechaNacimiento; }
    public void setFacultadProcedencia(String facultadProcedencia){ this.facultadProcedencia = facultadProcedencia; }

    @Override
    public String toString(){
        return "[ Alumno #" +id+ " | Nombre= " +nombreCompleto+ " | C.I= " +nroCi+ " ]";
    }
}
