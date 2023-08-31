import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Taxi {

    private final String taxi;
    private final String location;

    private int xgrid;

    private int ygrid;

    private int cellometer;



    public String getTaxiName() {
        return taxi;
    }
    public Taxi(String taxi, String location) {
        this.taxi = taxi;
        this.location = location;
        this.xgrid = Integer.parseInt(location.split(",")[0]);
        this.ygrid = Integer.parseInt(location.split(",")[1]);
    }

    public String gridref() {
        return "" + this.xgrid + "," + this.ygrid;
    }

    public void move(String move, HashMap<String, Taxi> taxis) {
        boolean canIMove = true;

        for(Map.Entry<String, Taxi> entry : taxis.entrySet()) {
            String taxiName = entry.getKey();
            String otherGridref = entry.getValue().gridref();


            if(!taxiName.equals(this.getTaxiName()) &&
                    this.gridref().equals(otherGridref)
                    ) {
                canIMove = false;
            }
        }

        if(canIMove) {
            actuallyMove(move);
        }
    }

    public void actuallyMove(String command) {
            move(command);
    }

    private void move(String move) {
        move.chars().forEach(movement->{
            cellometer++;
            if (movement == 'U') {
                ygrid--;
            } else if(movement == 'D') {
                ygrid++;
            } else if(movement == 'L') {
                xgrid--;
            } else if(movement == 'R') {
                xgrid++;
            }
        });
    }

    public int cellometer() {
        return cellometer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi1 = (Taxi) o;
        return Objects.equals(taxi, taxi1.taxi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxi);
    }
}
