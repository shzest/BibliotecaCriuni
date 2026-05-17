/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criuni.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Esta clase esta diseñada para leer al usuario desde la consola y manejar
 * las validaciones y reintentos de entradas invalidas.
 */
/**
 *
 * @author Asus
 */
public class Consola {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Privatiza al constructor para evitar crear un objeto. */
    private Consola(){}
    
    /** Separador visual */
    public static final String LINEA = "=".repeat(70);
    public static void separar(){ System.out.println(LINEA); }

    /** Espera hasta que el usuario presione enter.*/
    public static void esperarEnter() {
        System.out.print("\n Pulse enter para continuar.");
        // Scanner.nextLine() permite que el programa se "pause" hasta que reciba algun dato y continuar.
        SCANNER.nextLine();
    }

    /** Formatea monto a Guaranies.*/
    public static String formatearGs(double monto) {
        return String.format("Gs. %,.0f", monto);
    }

    /** Lee un entero dentro del rango establecido y repite
     * hasta obtener un valor valido.
     */
    public static int leerEntero(String dato, int valorMin, int valorMax){
     while(true) {
         System.out.print(dato);
         String line = SCANNER.nextLine().trim();
         try {
             int valor = Integer.parseInt(line);
             if(valor>= valorMin && valor<= valorMax) return valor;
             System.out.printf("Ingrese un numero entre %d y %d.%n", valorMin, valorMax);
         } catch (NumberFormatException e){
             System.out.println(" Entrada invalida. Ingrese un numero entero.");
         }
     }
    }

    /**
     * Lee un entero con valor por defecto (si el usuario presiona Enter sin escribir).
     */
    public static int leerEnteroOpcional(String dato, int valorActual, int min, int max) {
        System.out.print(dato);
        String lee = SCANNER.nextLine().trim();
        if (lee.isEmpty()) return valorActual;
        try {
            int valor = Integer.parseInt(lee);
            if (valor >= min && valor <= max) return valor;
        } catch (NumberFormatException ignored) {}
        System.out.printf(" Valor invalido. Se usa el valor actual (%d).%n", valorActual);
        return valorActual;
    }

    /** Lee un entero positivo en un rango de mayor o igual a 1 hasta 2^31 -1 (numero entero positivo de 32 bits
     * mas grande que se puede almacenar en un int).
     */
    public static int leerEnteroPositivo(String dato) { return leerEntero(dato,1,Integer.MAX_VALUE); }

    /** Lee una cadena y verifica que no quede vacio hasta que se cumpla.*/
    public static String leerTexto(String dato){
        while (true){
            System.out.print(dato);
            String valor= SCANNER.nextLine().trim();
            //Se utiliza !valor.isEmpty porque .isEmpty da true si la cadena esta vacia.
            if(!valor.isEmpty()) return valor;
            System.out.println(" Entrada invalida, la entrada no puede quedar vacia.");
        }
    }

    /** Lee un texto verificando si esta vacio o no y retorna un valor
     * por defecto si el usuario no escribe algo.
     */

    public static String leerTextoOpcional(String dato, String datoPorDefecto){
        System.out.print(dato);
        String valor= SCANNER.nextLine().trim();
        if(!valor.isEmpty()){
            return valor;
        } else {
            return datoPorDefecto;
        }
    }

    /** Lee una fecha y verifica que este correctamente formateada en dd/mm/aaaa
     *  hasta que se cumpla.
     */

    public static LocalDate leerFecha(String dato){
        while (true){
            System.out.print(dato);
            String valor= SCANNER.nextLine().trim();
            try {
                return LocalDate.parse(valor,FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println(" Formato invalido, siga este formato dd/MM/yyyy. ");
            }
        }
    }
    
    /** Lee una fecha y verifica que este correctamente formateada en dd/MM/yyyy,
     * si no lo esta se utiliza la fecha actual.
     */
    public static LocalDate leerFechaOpcional(String etiqueta, LocalDate valorActual) {
        System.out.print(etiqueta);
        String valor = SCANNER.nextLine().trim();
        if (valor.isEmpty()) return valorActual;
        try {
            return LocalDate.parse(valor, Consola.FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println(" Formato invalido. Se usa la fecha actual.");
            return valorActual;
        }
    }
    
    /** Trunca una cadena al máximo de caracteres indicado para ajustar a la tabla. */
    public static String truncar(String texto, int max) {
        if (texto == null) {
            return "";
        }
        if (texto.length() <= max) {
            return texto;
        }
        return texto.substring(0, max - 1) + "...";
    }
}
