import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Taxis {
    private final List<Taxi> taxis;

    public Taxis(List<Taxi> taxiList) {

        this.taxis = taxiList;
    }

    public void move(String command) {

        if (allTaxisInDifferentGrids()) {
            moveAllTaxis(command);
        }

        else {

            //lets keep a handle on where the highest priority taxi currently is
            String highestPriorityTaxiGridref = highestPriorityTaxi().gridref();

            //top taxi always gets to move regardless
            highestPriorityTaxi().actuallyMove(command);

            //with the top taxi no longer counted then if we have two everyone can move
            //unless they were in the same grid as the top taxi
            taxis.remove(highestPriorityTaxi());

            if (allTaxisInDifferentGrids()){
                moveEachTaxiIfNotInSameGridAsHigherPriorityTaxi(command, highestPriorityTaxiGridref);
            }

            //otherwise the remaining two must be in the same grid so only allow the new highest priority
            // taxi to move again only if they were not in the same grid as the original top taxi
            else{

                if (newHighestPriorityTaxiNotInSameGridAsPreviousHighestPriorityTaxi(highestPriorityTaxiGridref))
                {
                    highestPriorityTaxi().actuallyMove(command);
                }
            }

        }
    }

    private Taxi highestPriorityTaxi() {
        return highestPriorityTaxi(taxis);
    }

    private void moveAllTaxis(String command) {
        taxis.forEach(taxi -> taxi.actuallyMove(command));
    }

    private void moveEachTaxiIfNotInSameGridAsHigherPriorityTaxi(String command, String highestPriorityTaxiGridref) {
        taxis.forEach(taxi -> {
            if (!taxi.gridref().equals(highestPriorityTaxiGridref))
            taxi.actuallyMove(command);

        });
    }

    private boolean newHighestPriorityTaxiNotInSameGridAsPreviousHighestPriorityTaxi(String highestPriorityTaxiGridref) {
        return !highestPriorityTaxi().gridref().equals(highestPriorityTaxiGridref);
    }

    private boolean allTaxisInDifferentGrids() {
        return taxisInDifferentGrids(numberOfGridsInUse(taxis));
    }

    private boolean taxisInDifferentGrids(int gridsInUse) {
        return gridsInUse == taxis.size();
    }

    private int numberOfGridsInUse(List<Taxi> taxis) {
        return taxis.stream().map(taxi -> taxi.gridref()).collect(Collectors.toSet())
                .size();
    }

    private Taxi highestPriorityTaxi(List<Taxi> taxis) {
        return taxis.stream()
                .min(Comparator.comparing(taxi -> taxi.getTaxiName()))
                .orElse(null);
    }

    public List<Taxi> taxis() {
        return taxis;
    }

    public String[] locations() {
        return taxis.stream().map(taxi-> taxi.gridref()).toArray(String[]::new);
    }
}
