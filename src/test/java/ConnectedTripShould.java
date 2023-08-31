import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ConnectedTripShould {

    private ConnectedTrip connectedTrip;
    private Taxi taxiA;
    private Taxi taxiB;
    private Taxi taxiC;

    private HashMap<String, Taxi> taxis;

    private List<Taxi> taxiList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        taxiA = new Taxi("A","5,3");
        taxiB = new Taxi("B","2,2");
        taxiC = new Taxi("C","1,1");

        taxis = new HashMap<>();
        taxis.put("A", taxiA);
        taxis.put("B", taxiB);
        taxis.put("C", taxiC);

        connectedTrip = new ConnectedTrip(taxis);

        taxiList.add(taxiA);
        taxiList.add(taxiB);
        taxiList.add(taxiC);





    }

    @Test
    public void start_cellometer_at_zero() {
        //act
        int cellometer = taxiA.cellometer();

        //assert
        assertEquals(0, cellometer);
    }

    @Test
    public void taxis_at_correct_start_location() {

        //assert
        assertEquals("5,3", connectedTrip.taxiLocation("A"));
        assertEquals("2,2", connectedTrip.taxiLocation("B"));
        assertEquals("1,1", connectedTrip.taxiLocation("C"));

    }

    @Test
    public void taxi_should_move_up_one_cell () throws Exception{
        taxiA.move("U", taxis);
        assertEquals("5,2",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }

    @Test
    public void taxi_should_move_down_one_cell () throws Exception{
        taxiA.move("D", taxis);
        assertEquals("5,4",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }

    @Test
    public void taxi_should_left_up_one_cell () throws Exception{
        taxiA.move("L", taxis);
        assertEquals("4,3",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }

    @Test
    public void taxi_should_move_right_one_cell () throws Exception{
        taxiA.move("R", taxis);
        assertEquals("6,3",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }



    @Test
    public void taxi_multiple_moves () throws Exception{


        taxiA.move("LL", taxis);

        assertEquals("3,3",taxiA.gridref());
    }


//    @Test
//    public void taxi_should_stay_if_in_grid_with_another_taxi () throws Exception{
//
//        //arrange
//
//        //act
//        taxiC.move("DRUDLRU");
//
//        //assert
//        assertEquals("2,2",taxiC.gridref());
//    }

//    @Test
//    public void move_only_taxiA() {
//        taxiA = new Taxi("A","0,0");
//        taxiB = new Taxi("B","0,0");
//        taxiC = new Taxi("C","0,0");
//
//        taxis = new HashMap<>();
//        taxis.put("A", taxiA);
//        taxis.put("B", taxiB);
//        taxis.put("C", taxiC);
//
//        connectedTrip = new ConnectedTrip(taxis);
//
//        taxiA.move("R",taxis);
//        taxiB.move("R",taxis);
//        taxiC.move("R",taxis);
//
//        assertEquals("1,0",taxiA.gridref());
//        assertEquals("0,0",taxiB.gridref());
//        assertEquals("0,0",taxiC.gridref());
//    }


//    @Test
//    public void tick () throws Exception{
//
//        taxiA = new Taxi("A","0,0");
//        taxiB = new Taxi("B","0,0");
//        taxiC = new Taxi("C","0,0");
//
//        taxiList.add(taxiA);
//        taxiList.add(taxiB);
//        taxiList.add(taxiC);
//
//        Dispatch dispatch = new Dispatch(taxiList);
//
//        dispatch.move("R");
//
//
//        assertEquals("1,0",taxiA.gridref());
//        assertEquals("0,0",taxiB.gridref());
//        assertEquals("0,0",taxiC.gridref());
//    }
    
    @Mock
    Taxis allTheTaxis;

    @Test
    public void dispatcher_should_move_all_taxis () throws Exception{
        //given
        Dispatcher dispatcher = new Dispatcher(allTheTaxis);
        //when
        dispatcher.dispatch("R");
        //then
        verify(allTheTaxis).move("R");
    }


    @Test
    public void taxis_should_know_about_all_taxis () throws Exception{
        //arrange
        //act
        Taxis taxis = new Taxis(taxiList);
        //assert
        assertEquals(taxiList, taxis.taxis());
    }


    @Test
    public void dispatcher_should_only_move_one_taxi () throws Exception{
        //arrange

        taxiList.clear();


        taxiA = new Taxi("A","0,0");
        taxiB = new Taxi("B","0,0");
        taxiC = new Taxi("C","0,0");

        taxiList.add(taxiA);
        taxiList.add(taxiB);
        taxiList.add(taxiC);

        Dispatcher dispatcher = new Dispatcher(new Taxis(taxiList));

        //act
        dispatcher.dispatch("R");

        //assert
        assertNotEquals("0,0", taxiA.gridref());
        assertEquals("0,0", taxiB.gridref());
        //assertEquals("0,0", taxiC.gridref());


    }

    @Test
    public void dispatcher_should_only_move_one_taxi_b () throws Exception{
        //arrange

        taxiList.clear();


        taxiA = new Taxi("A","1,0");
        taxiB = new Taxi("B","0,0");
        taxiC = new Taxi("C","0,0");

        taxiList.add(taxiA);
        taxiList.add(taxiB);
        taxiList.add(taxiC);

        Dispatcher dispatcher = new Dispatcher(new Taxis(taxiList));

        //act
        dispatcher.dispatch("R");

        //assert
        assertEquals("2,0", taxiA.gridref());
        assertEquals("1,0", taxiB.gridref());
        assertEquals("0,0", taxiC.gridref());


    }

    @Test
    public void dispatcher_should_move_all_the_taxis () throws Exception{
        //arrange

        taxiList.clear();

        taxiA = new Taxi("A","1,0");
        taxiB = new Taxi("B","0,1");
        taxiC = new Taxi("C","2,0");

        taxiList.add(taxiA);
        taxiList.add(taxiB);
        taxiList.add(taxiC);

        Dispatcher dispatcher = new Dispatcher(new Taxis(taxiList));

        //act
        dispatcher.dispatch("R");

        //assert
        assertEquals("2,0", taxiA.gridref());
        assertEquals("1,1", taxiB.gridref());
        assertEquals("3,0", taxiC.gridref());


    }

    @Test
    public void dispatcher_should_move_two_taxis_not_A () throws Exception{
        //arrange

        taxiList.clear();

        taxiA = new Taxi("A","1,0");
        taxiB = new Taxi("B","0,1");
        taxiC = new Taxi("C","2,0");

        taxiList.add(taxiA);
        taxiList.add(taxiB);
        taxiList.add(taxiC);

        Dispatcher dispatcher = new Dispatcher(new Taxis(taxiList));

        //act
        dispatcher.dispatch("R");

        //assert
        assertEquals("2,0", taxiA.gridref());
        assertEquals("1,1", taxiB.gridref());
        assertEquals("3,0", taxiC.gridref());


    }




}


