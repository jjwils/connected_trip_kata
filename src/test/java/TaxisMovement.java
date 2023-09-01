import org.approvaltests.Approvals;
import org.approvaltests.reporters.Junit5Reporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@UseReporter(Junit5Reporter.class)
public class TaxisMovement {


    private static StringBuilder map = new StringBuilder();

    @Test
    public void testList()
    {
        Taxi taxiA = new Taxi("A","0,0");
        Taxi taxiB = new Taxi("B","0,1");
        Taxi taxiC = new Taxi("C","2,3");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);

        //act
       // dispatcher.dispatch("R");


         map.append("""
                    0    1    2    3    4    5
                        
                0 [   ][   ][   ][   ][   ][   ]
                
                1 [   ][   ][   ][   ][   ][   ]
                
                2 [   ][   ][   ][   ][   ][   ]
                
                3 [   ][   ][   ][   ][   ][   ]
                """);


        printTaxi(taxiA);
        printTaxi(taxiB);
        printTaxi(taxiC);


        Approvals.verify(map.toString());

    }

    private static void printTaxi(Taxi taxi) {
        int colMultiplier = 5;
        int rowMultiplier = 34;
        int originOffset = 35;
        map.replace(originOffset + taxi.getXgrid()*colMultiplier + taxi.getYgrid()*rowMultiplier, originOffset + taxi.getXgrid()*colMultiplier + taxi.getYgrid()*rowMultiplier +1, taxi.getTaxiName());
    }
}
