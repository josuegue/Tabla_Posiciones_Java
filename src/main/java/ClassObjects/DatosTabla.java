/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassObjects;

/**
 *
 * @author Josue_Guevara
 */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class DatosTabla {
    private static final Scanner input = new Scanner(System.in);
    private static final String ubicacion_descarga="C:\\Users\\click\\Downloads\\Archivo_datos.json";
    private static final String ubicacion_archivo="C:\\Users\\click\\Documents\\TablaEquipos.json";
    
    //Metodo que me extrae una matriz de un archivo JSON
    public String [][] cargar_datosJson(){
        List<TablaPosiciones> listaPosiciones = null;
        String [][] matriz = null;
        File archivo = new File (ubicacion_archivo);
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader fr = new FileReader(archivo);
            Object obj = jsonParser.parse(fr);
            JSONArray lista = (JSONArray) obj;
            matriz = new String [lista.size()][9];
            for ( int i = 0; i<lista.size(); i++){

                TablaPosiciones dt = new TablaPosiciones();
                Object objetoTabla = lista.get(i);
                JSONObject tabla = (JSONObject) objetoTabla;
               
                matriz[i][0] = (String) tabla.get("nombreEquipo");
                matriz[i][1] = ""+ tabla.get("jornada");
                matriz[i][2] = ""+ tabla.get("puntos");
                matriz[i][3] = ""+ tabla.get("ganados");
                matriz[i][4] = ""+ tabla.get("perdidos");
                matriz[i][5] = ""+ tabla.get("empates");
                matriz[i][6] = ""+ tabla.get("goles_favor");
                matriz[i][7] = ""+ tabla.get("goles_contra");
                matriz[i][8] = ""+ tabla.get("df_goles");

            }
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        return matriz;
        
    }
    
    //Metodo para ver la matriz
    public String [][] ordenar(String [][] matriz){
        String [][] mat_aux = new String [matriz.length][matriz[0].length];
        for(int i =0; i<matriz.length; i++){
            for(int j=0; j<matriz[0].length; j++){
                if(Integer.parseInt(matriz[i][1]) < Integer.parseInt(matriz[j][1])){
                    mat_aux[i][0]= matriz[i][0];
                    mat_aux[i][1]= matriz[i][1];
                    mat_aux[i][2]= matriz[i][2];
                    mat_aux[i][3]= matriz[i][3];
                    mat_aux[i][4]= matriz[i][4];
                    mat_aux[i][5]= matriz[i][5];
                    mat_aux[i][6]= matriz[i][6];
                    mat_aux[i][7]= matriz[i][7];
                    mat_aux[i][8]= matriz[i][8];
                    
                    matriz[i][0]=matriz[j][0];
                    matriz[i][1]=matriz[j][1];
                    matriz[i][2]=matriz[j][2];
                    matriz[i][3]=matriz[j][3];
                    matriz[i][4]=matriz[j][4];
                    matriz[i][5]=matriz[j][5];
                    matriz[i][6]=matriz[j][6];
                    matriz[i][7]=matriz[j][7];
                    matriz[i][8]=matriz[j][8];
                    
                    matriz[j][0] = mat_aux[i][0];
                    matriz[j][1] = mat_aux[i][1];
                    matriz[j][2] = mat_aux[i][2];
                    matriz[j][3] = mat_aux[i][3];
                    matriz[j][4] = mat_aux[i][4];
                    matriz[j][5] = mat_aux[i][5];
                    matriz[j][6] = mat_aux[i][6];
                    matriz[j][7] = mat_aux[i][7];
                    matriz[j][8] = mat_aux[i][8];    
                }
            }
        }
        return matriz;
    }
    
    public void ver_matriz(String [][] matriz){
        for (int i= 0; i<matriz.length; i++){
            for(int j =0; j<matriz[0].length; j++){
                System.out.print(matriz[i][j]+" | ");  
            }  
          System.out.println();
        }  
    }
    
    public void modificar(String nombre, String [][] matriz){
        for (int i = 0; i<matriz.length; i++){
            if(nombre.equals(matriz[i][0])){
                System.out.print("Ingrese su opcion.. 'si', 'no' o 'empate': ");
                String op = input.nextLine();
                if(op.toLowerCase().endsWith("si")){
                    int jornada = Integer.parseInt( matriz[i][1]) +1;
                    int puntos = Integer.parseInt(matriz[i][2]) +3;
                    int ganados = Integer.parseInt(matriz[i][3]) + 1;
                    
                    System.out.print("Goles a favor: ");
                    int goles_f=Integer.parseInt(input.nextLine());
                    System.out.print("Goles en contra: ");
                    int goles_c=Integer.parseInt(input.nextLine());
                    
                    goles_f = Integer.parseInt(matriz[i][6]) + goles_f;
                    goles_c = Integer.parseInt(matriz[i][7]) + goles_c;
                    
                    int df_goles = goles_f - goles_c;
                    
                    matriz[i][1]=""+jornada;
                    matriz[i][2]=""+puntos;
                    matriz[i][3]=""+ganados;
                    matriz[i][6]=""+goles_f;
                    matriz[i][7]=""+goles_c;
                    matriz[i][8]=""+df_goles;
                    
                }
                else if(op.toLowerCase().equals("no")){
                     int jornada = Integer.parseInt( matriz[i][1]) +1;
                    int perdidos = Integer.parseInt(matriz[i][4]) + 1;
                    
                    System.out.print("Goles a favor: ");
                    int goles_f=Integer.parseInt(input.nextLine());
                    System.out.print("Goles en contra: ");
                    int goles_c=Integer.parseInt(input.nextLine());
                    
                    goles_f = Integer.parseInt(matriz[i][6]) + goles_f;
                    goles_c = Integer.parseInt(matriz[i][7]) + goles_c;
                    
                    int df_goles = goles_f - goles_c;
                    
                    matriz[i][1]=""+jornada;
                    matriz[i][4]=""+perdidos;
                    matriz[i][6]=""+goles_f;
                    matriz[i][7]=""+goles_c;
                    matriz[i][8]=""+df_goles;
                    
                }
                else if(op.toLowerCase().equals("empate")){
                    int jornada = Integer.parseInt( matriz[i][1]) +1;
                    int empates = Integer.parseInt(matriz[i][5]) + 1;
                    int puntos = Integer.parseInt(matriz[i][2])+1;
                    
                    System.out.print("Goles a favor: ");
                    int goles_f=Integer.parseInt(input.nextLine());
                    System.out.print("Goles en contra: ");
                    int goles_c=Integer.parseInt(input.nextLine());
                    
                    goles_f = Integer.parseInt(matriz[i][6]) + goles_f;
                    goles_c = Integer.parseInt(matriz[i][7]) + goles_c;
                    
                    int df_goles = goles_f - goles_c;
                    
                    matriz[i][1]=""+jornada;
                    matriz[i][2]=""+puntos;
                    matriz[i][5]=""+empates;
                    matriz[i][6]=""+goles_f;
                    matriz[i][7]=""+goles_c;
                    matriz[i][8]=""+df_goles;
                    
                    
                }
                else{
                    System.out.println("Opcion inexistente");
                    break;
                }
            }
        }
        
    }
    
    public void generar_Json(String [][] matriz){
        TablaPosiciones equipos;
        FileWriter fw = null;
        try {
            JSONObject objeto = new JSONObject();
            JSONArray lista = new JSONArray();
            for(int i=0; i<matriz.length; i++){
                
                equipos = new TablaPosiciones();
                
                
                equipos.setNombreEquipo(matriz[i][0]);
                equipos.setJornada(Integer.parseInt(matriz[i][1]));
                equipos.setPuntos(Integer.parseInt(matriz[i][2]));
                equipos.setGanados(Integer.parseInt(matriz[i][3]));
                equipos.setPerdidos(Integer.parseInt(matriz[i][4]));
                equipos.setEmpates(Integer.parseInt(matriz[i][5]));
                equipos.setGoles_f(Integer.parseInt(matriz[i][6]));
                equipos.setGoles_c(Integer.parseInt(matriz[i][7]));
                equipos.setDf_goles(Integer.parseInt(matriz[i][8]));
                
                objeto.put("nombreEquipo", equipos.getNombreEquipo());
                objeto.put("jornada", equipos.getJornada());
                objeto.put("puntos", equipos.getPuntos());
                objeto.put("ganados", equipos.getGanados());
                objeto.put("perdidos", equipos.getPerdidos());
                objeto.put("empates", equipos.getEmpates());
                objeto.put("goles_favor", equipos.getGoles_f());
                objeto.put("goles_contra", equipos.getGoles_c());
                objeto.put("df_goles", equipos.getDf_goles());
                lista.add(objeto);
                
            }
            fw = new FileWriter(ubicacion_descarga);
            for(var listado: lista){
                fw.write(listado.toString());
            }
            fw.close();

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    

}
