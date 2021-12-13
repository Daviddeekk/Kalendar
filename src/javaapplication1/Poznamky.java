package javaapplication1;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Poznamky {

    private File file;
     ArrayList<Poznamka> poznamky = new ArrayList<>();
    
    
    public Poznamky(String file) {
        this.file = new File(file);
   /*     if(!this.file.exists())
        {
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
    
    

    public void pridejPoznamku(Poznamka poznamka) {
        JSONParser parser = new JSONParser();
       System.out.println( file.getAbsolutePath());
        try {
       /*     FileReader reader = new FileReader(file);
            JSONArray array = (JSONArray) parser.parse(reader);
            array.add(poznamka.toJSON());
*/
            JSONArray array = new JSONArray();
            JSONObject js = new JSONObject();
            js.put("Datum", poznamka.getDatum());
            js.put("Nazev", poznamka.getObsah());
            js.put("Obsah", poznamka.getObsah());
          //  array.add(js);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(js.toJSONString());
            fileWriter.write("\r\n");
            fileWriter.flush();
            poznamky.add(poznamka);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public ArrayList<Poznamka> getPoznamkyAll() {
       
        JSONParser parser = new JSONParser();
        try {
            System.out.println("x:"+this.file.getAbsolutePath());
            FileReader reader = new FileReader(this.file);
            BufferedReader br = new BufferedReader(reader);
          
           
            String obj = br.readLine();
            while((obj !=null))
            {
             JSONObject jsobj  = (JSONObject) parser.parse(obj);
             poznamky.add(new Poznamka(jsobj.get("Nazev").toString(), jsobj.get("Obsah").toString(), jsobj.get("Datum").toString()));
             System.out.println("Načetl jsem poznánmku");
             obj = br.readLine();
             System.out.println(jsobj.get("Datum").toString()+" "+ jsobj.get("Nazev").toString());
            
            }
            //Iterate over employee array
            
           

      
      
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        }
  return poznamky;
}
    
}
