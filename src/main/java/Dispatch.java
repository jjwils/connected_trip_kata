import java.util.List;

public class Dispatch {
    private final List<Taxi> taxis;

    public Dispatch(List<Taxi> taxis) {

        this.taxis = taxis;
    }

    public void move(String command) {

       taxis.forEach(taxi -> taxi.actuallyMove(command));
    }
}
