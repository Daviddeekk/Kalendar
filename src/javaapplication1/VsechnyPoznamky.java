/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication1.Dialog.CrunchifyLog;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Danek_david
 */

public class VsechnyPoznamky {
    Poznamk p;
    public List<JSONObject>  poznamky ;
     
    JSONArray array = new JSONArray();
    String readJson = null;
     JSONParser parser = new JSONParser();
    public VsechnyPoznamky() {
        
        poznamky = new ArrayList();
    }
    
    public void nactiPoznamky(File f)
    {
       
        
    }

        
         
    
    
    /**
     * Uloží posledí přidanou poznamku do souboru
     * @param f 
     */
    public void uloz(JSONObject novy)
    {
       
            // Constructs a FileWriter given a file name, using the platform's default charset
           File f = new File("poznamky.json");
            
            FileWriter file = null;
        try {
            file = new FileWriter(f,true);
            poznamky.add(novy);
           
            PrintWriter pw = new PrintWriter(file);
            System.out.println("Souábot "+ f.getAbsolutePath());
          
            
            
        JSONObject obj = new JSONObject();
           file.write(obj.toJSONString());
            pw.write(novy.toJSONString()+"\r\n");
            System.out.println(poznamky.toString());
            
            /* File f2 = new File("poznamky2.txt");
       FileOutputStream fos = new FileOutputStream(f2);
        ObjectOutputStream ois = new ObjectOutputStream(fos);
        ois.writeObject(obj);
        */
        
           
            CrunchifyLog("Successfully Copied JSON Object to File...");
            CrunchifyLog("\nJSON Object: " + novy);
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
               file.flush();
               
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
         
    }
    
}
