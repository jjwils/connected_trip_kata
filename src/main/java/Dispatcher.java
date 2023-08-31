public class Dispatcher {
    private final Taxis taxis;

    public Dispatcher(Taxis allTheTaxis) {

        this.taxis = allTheTaxis;
    }

    public void dispatch(String command) {
       taxis.move("R");
    }
}
