import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TaxisMovement {


    @Test
    public void testList()
    {
        Taxi taxiA = new Taxi("A","1,0");
        Taxi taxiB = new Taxi("B","0,1");
        Taxi taxiC = new Taxi("C","1,0");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);

        //act
        dispatcher.dispatch("R");

        Approvals.verifyAll("", taxis.locations());



    }
}
