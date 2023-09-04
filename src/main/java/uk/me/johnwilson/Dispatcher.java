package uk.me.johnwilson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Dispatcher {
    private final Taxis taxis;

    public Dispatcher(Taxis allTheTaxis) {
        this.taxis = allTheTaxis;
    }


    public void dispatch(List<TaxiCommand> taxiCommands) {
        int maxLength = taxiCommands.stream()
                .mapToInt(taxiCommand -> taxiCommand.command().length())
                .max()
                .orElse(0);

        for (int i = 0; i < maxLength; i++) {
            assessNextSteps(taxis.taxis());
            for (TaxiCommand taxiCommand : taxiCommands) {
                String commandString = taxiCommand.command();
                if (i < commandString.length()) {
                    char commandChar = commandString.charAt(i);

                    taxiCommand.taxi().move(String.valueOf(commandChar));
                }
            }
        }
    }

    public void dispatch(TaxiCommand taxiCommand) {
        taxiCommand.taxi().actuallyMove(taxiCommand.command());

    }

    public void assessNextSteps(List<Taxi> taxisInput) {
        //assume cant move for all to start
        taxisInput.forEach(taxi -> taxi.canMoveOnNextStep(false));

        //take a copy of the list
        List<Taxi> taxis = new ArrayList<>(taxisInput);

        //highest priority taxi can always move regardless
        Taxi highestPriorityTaxi = highestPriorityTaxi(taxis);
        highestPriorityTaxi.canMoveOnNextStep(true);

        //if all the taxis are in different grids everyone can move
        if (allTaxisInDifferentGrids(taxis)) {
                taxis.forEach(taxi -> taxi.canMoveOnNextStep(true));
        }

        else {
            //takeaway the highest priority taxi
            taxis.remove(highestPriorityTaxi);

            List<Taxi> taxisToRemove = new ArrayList<>();
            //takeaway any taxi that is in the same grid as the hpt
            taxis.forEach(taxi -> {
                if (taxi.gridref().equals(highestPriorityTaxi.gridref())){
                    taxisToRemove.add(taxi);
                }
            });

            taxis.removeAll(taxisToRemove);

            //any taxis left call ourselves recursively
            if (!taxis.isEmpty()){
                assessNextSteps(taxis);
            }

        }


    }

    private boolean allTaxisInDifferentGrids(List taxis) {
        int gridsInUse = ((List<Taxi>) taxis).stream().map(taxi -> taxi.gridref()).collect(Collectors.toSet())
                .size();
        return gridsInUse == taxis.size();
    }

    private Taxi highestPriorityTaxi(List<Taxi> taxis) {
        return taxis.stream()
                .min(Comparator.comparing(taxi -> taxi.getTaxiName()))
                .orElse(null);
    }
}

