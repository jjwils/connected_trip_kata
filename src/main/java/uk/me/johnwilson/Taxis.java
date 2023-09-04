package uk.me.johnwilson;

import java.util.HashMap;
import java.util.List;

public class Taxis {
    private  List<Taxi> taxis;

    private  HashMap<String,Taxi> taxiLocations;

    public Taxis(HashMap<String, Taxi> taxiLocations){
        this.taxiLocations = taxiLocations;

    }

    public Taxis(List<Taxi> taxiList) {
        this.taxis = taxiList;
    }


    public List<Taxi> taxis() {
        return taxis;
    }


    public String taxiLocation(String taxiName) {
        return taxiLocations.get(taxiName).gridref();
    }
}
