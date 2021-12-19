package javaapplication1;

import org.json.simple.JSONObject;

public class Poznamka {
    private String nazev;
    private String obsah;
    private String datum;

    public Poznamka(String nazev, String obsah, String datum) {
        this.nazev = nazev;
        this.obsah = obsah;
        this.datum = datum;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getObsah() {
        return obsah;
    }

    public void setObsah(String obsah) {
        this.obsah = obsah;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("nazev", nazev);
        obj.put("obsah", obsah);
        obj.put("datum", datum);

        return obj;
    }
    @Override
    public String toString (){
        return ""+this.nazev+" "+this.obsah+ " "+this.datum;
    
    }
}