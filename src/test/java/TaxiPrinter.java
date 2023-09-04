import uk.me.johnwilson.Taxi;

import java.util.List;

public class TaxiPrinter {

    final static String GRID = """
                    0    1    2    3    4    5
                        
                0 [   ][   ][   ][   ][   ][   ]
                
                1 [   ][   ][   ][   ][   ][   ]
                
                2 [   ][   ][   ][   ][   ][   ]
                
                3 [   ][   ][   ][   ][   ][   ]
                """;
    private static StringBuilder map;

    static void printTaxi(Taxi taxi) {
        int originOffset = 35;
        int colMultiplier = 5;
        int rowMultiplier = 34;
        int taxiOffset = 0;

        if (taxi.getTaxiName().equals("B")){
            taxiOffset = 1;
        } else if (taxi.getTaxiName().equals("C")){
            taxiOffset = 2;
        }

        int offset = originOffset + taxi.getXgrid() * colMultiplier + taxi.getYgrid() * rowMultiplier + taxiOffset;
        map.replace(offset, offset + 1, taxi.getTaxiName());
    }

    static String plotTaxis(List<Taxi> listOfTaxis) {
        map = new StringBuilder(GRID);
        listOfTaxis.forEach(TaxiPrinter::printTaxi);
        return map.toString();
    }
}