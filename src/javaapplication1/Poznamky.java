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

    public Poznamky(String file) {
        this.file = new File(file);
    }

    public void pridejPoznamku(Poznamka poznamka) {
        JSONParser parser = new JSONParser();

        try {
            FileReader reader = new FileReader(file);
            JSONArray array = (JSONArray) parser.parse(reader);
            array.add(poznamka.toJSON());

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(array.toJSONString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Poznamka> getPoznamkaByDatum(String datum) {
        ArrayList<Poznamka> poznamky = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(file);

            JSONArray employeeList = (JSONArray) parser.parse(reader);

            //Iterate over employee array
            employeeList.forEach(new Consumer() {
                @Override
                public void accept(Object emp) {
                    JSONObject ob = (JSONObject) emp;
                    if (ob.get("datum") == datum) {
                        poznamky.add(new Poznamka((String) ob.get("nazev"), (String) ob.get("obsah"), (String) ob.get("datum")));
                    }
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Poznamky.class.getName()).log(Level.SEVERE, null, ex);
        }
        return poznamky;
    }

}
