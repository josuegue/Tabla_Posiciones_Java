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
import java.io.IOException;
import static java.lang.System.in;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class DatosTabla {
    
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
    public void ver_matriz(String [][] matriz){
        for(int i =0; i<matriz.length; i++){
            for(int j=0; j<matriz[0].length; j++){
                System.out.println(matriz[i][j]);
            }
            System.out.println();
            
        }
    }
    
    
    

}
