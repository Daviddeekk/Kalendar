package javaapplication1;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
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
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void pridejPoznamku(Poznamka poznamka) {
        JSONParser parser = new JSONParser();
        System.out.println(file.getAbsolutePath());
        try {
            /*     FileReader reader = new FileReader(file);
            JSONArray array = (JSONArray) parser.parse(reader);
            array.add(poznamka.toJSON());
             */
            JSONArray array = new JSONArray();
            JSONObject js = new JSONObject();
            js.put("Datum", poznamka.getDatum());
            js.put("Nazev", poznamka.getNazev());
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
            System.out.println("x:" + this.file.getAbsolutePath());
            FileReader reader = new FileReader(this.file);
            BufferedReader br = new BufferedReader(reader);
            poznamky.removeAll(poznamky);

            String obj = br.readLine();
            while ((obj != null)) {
                JSONObject jsobj = (JSONObject) parser.parse(obj);

                //System.out.println("Načetl jsem poznánmku");
                obj = br.readLine();
                Poznamka p = new Poznamka("Název:" + jsobj.get("Nazev").toString(), "Obsah:" + jsobj.get("Obsah").toString(), "Datum:" + jsobj.get("Datum").toString());

                // System.out.println(jsobj.get("Datum").equals("13. December  2021")+" "+jsobj.get("Datum").toString()+" "+ jsobj.get("Nazev").toString()+" "+jsobj.get("Obsah"));
                poznamky.add(p);
                //System.out.println(poznamky);
             /*   String name = "list.pdf";
                PdfWriter pdfwriter = new PdfWriter(name);
                PdfDocument pdfdocument = new PdfDocument(pdfwriter);
                Document document = new Document(pdfdocument);
            */

            }

            //Iterate over employee array
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println(
                    "failed to add the list to file due to "
                    + e);
        }
        return poznamky;
    }

    public ArrayList<Poznamka> getPoznamkyByDatum(String celeDatum) {

        JSONParser parser = new JSONParser();
        try {
           
            FileReader reader = new FileReader(this.file);
            BufferedReader br = new BufferedReader(reader);
             poznamky.removeAll(poznamky);
            String obj = br.readLine();
            while ((obj != null)) {
                 
                JSONObject jsobj = (JSONObject) parser.parse(obj);
//
                //System.out.println("Načetl jsem poznánmku");
                obj = br.readLine();

                //System.out.println(jsobj.get("Nazev").equals(obj));
                if (jsobj.get("Datum").equals(celeDatum)) {
                    
                    Poznamka p = new Poznamka(jsobj.get("Nazev").toString(), jsobj.get("Obsah").toString(), jsobj.get("Datum").toString());

                    poznamky.add(p);
                    //System.out.println(poznamky);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        }
        //;
         
        return poznamky;
       
    }

}
