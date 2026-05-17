/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.modelos;


/**
 *
 * @author Asus
 */
public class Libro {
    private int id;
    private String titulo;
    private String editorial;
    private int anhoPublicacion;
    private String autor;
    private int stock;

    /** Constructor */
    public Libro(int id, String titulo, String editorial, int anhoPublicacion, String autor, int stock) {
        this.id=id;
        this.titulo = titulo;
        this.editorial = editorial;
        this.anhoPublicacion = anhoPublicacion;
        this.autor= autor;
        this.stock = stock;
    }

    /** Getters */
    public int getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public String getEditorial(){ return editorial; }
    public int getAnhoPublicacion(){ return anhoPublicacion; }
    public String getAutor(){ return autor; }
    public int getStock(){ return stock; }

    /** Setters */
    public void setTitulo(String titulo){ this.titulo = titulo; }
    public void setEditorial(String editorial){ this.editorial = editorial; }
    public void setAnhoPublicacion(int anho){ this.anhoPublicacion = anho; }
    public void setAutor(String autor){ this.autor = autor; }
    public void setStock(int stock){ this.stock = stock; }

    /** Descuenta el stock al prestar el libro */
    public void descontarStock() {
        if(this.stock>0){
            this.stock--;
        }
    }

    /** Aumenta el stock al devolver el libro */
    public void devolverStock() {
        this.stock++;

    }

    @Override
    public String toString(){
        return "[ Libro #" +id+ " | titulo= " +titulo+ " | stock= " +stock+ " ]";
    }
}
