import java.util.HashMap;

public class ConnectedTrip {
    private final HashMap<String,Taxi> taxiLocation;

    public ConnectedTrip(HashMap<String, Taxi> taxiLocations){
        this.taxiLocation = taxiLocations;

    }

    public String taxiLocation(String taxi) {
        return taxiLocation.get(taxi).gridref();

    }
}
