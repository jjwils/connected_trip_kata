import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Taxis {
    private final List<Taxi> taxiList;

    public Taxis(List<Taxi> taxiList) {

        this.taxiList = taxiList;
    }

    public void move(String command) {

        Taxi topTaxi = getTopTaxi(taxiList);

        int gridsInUse = numberOfGridsInUse(taxiList);

        if (gridsInUse == 3) {
            taxiList.forEach(taxi -> taxi.actuallyMove(command));
        }

        else {

            //lets keep a handle on where the top taxi currently is
            String topTaxiGridref = topTaxi.gridref();

            //top taxi always gets to move regardless
            topTaxi.actuallyMove(command);
            taxiList.remove(topTaxi);

            //with the top taxi no longer counted then if we have two everyone can move
            //unless they were in the same grid as the top taxi
            gridsInUse = numberOfGridsInUse(taxiList);

            if (gridsInUse == 2){
                taxiList.forEach(taxi -> {
                    if (!taxi.gridref().equals(topTaxiGridref))
                    taxi.actuallyMove(command);

                });
            }
            //otherwise the remaining two must be in the same grid so only allow the new toptaxi to move
            //again only if they were not in the same grid as the original top taxi
            else{
                Taxi newTopTaxi = getTopTaxi(taxiList);

                if (!newTopTaxi.gridref().equals(topTaxiGridref))
                {
                    newTopTaxi.actuallyMove(command);
                }
            }

        }
    }

    private int numberOfGridsInUse(List<Taxi> taxis) {
        return taxis.stream().map(taxi -> taxi.gridref()).collect(Collectors.toSet())
                .size();
    }

    private Taxi getTopTaxi(List<Taxi> taxis) {
        return taxis.stream()
                .min(Comparator.comparing(taxi -> taxi.getTaxiName()))
                .orElse(null);
    }

    public List<Taxi> taxis() {
        return taxiList;
    }
}
