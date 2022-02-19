/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.tabla_pocisiones;

import ClassObjects.DatosTabla;
import ClassObjects.TablaPosiciones;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Josue_Guevara
 */
public class Main {

    /**
     * @param args the command line arguments

     */
    private static final Scanner input = new Scanner (System.in);
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        DatosTabla dt = new DatosTabla ();
        String [][] matriz = dt.cargar_datosJson();
        matriz = dt.ordenar(matriz);
        boolean validacion = true;
        int opcion;
        
        while(validacion){
            System.out.println();
            System.out.println("\n 0. Salir "
                    + "\n 1. Ver matriz"
                + "\n 2. Modificar datos"
                + "\n 3. Generar archivo JSON"
                + "\n Ingrese la opcion: ");
            opcion = Integer.parseInt(input.nextLine());
  
            switch(opcion){
                case 0:
                    System.out.println("Gracias por usar nuestros servicios <3");
                    validacion = false;
                    break;
                case 1:
                    System.out.println("------- Matriz --------");
                    dt.ver_matriz(matriz);
                    System.out.println("--------- FIN ----------");
                    validacion = true;
                    break;
                case 2:
                    
                    System.out.println("Ingrese nombre. ");
                    String nombre = input.nextLine();
                    if(nombre.matches("[+-]?\\d*(\\.\\d+)?")){
                        System.out.println("----- Modificar datos -------");
                        dt.modificar(nombre, matriz);
                        System.out.println("------- FIN --------");
                    }
                    else {
                        System.out.println("No es una cadena");
                    }
                    
                    
                    validacion = true;
                    break;
                case 3:
                    System.out.println("--------- Archivo generado en descargas ------");
                    dt.generar_Json(matriz);
                    System.out.println("-------- FIN ------------");
                    validacion = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    validacion = true;
                    break;
            }
        }
        
        
        
        
    }
    
}
