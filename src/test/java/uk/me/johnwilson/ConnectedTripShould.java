package uk.me.johnwilson;

import uk.me.johnwilson.Taxi;
import uk.me.johnwilson.Taxis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ConnectedTripShould {

    private Taxis taxis;
    private Taxi taxiA;
    private Taxi taxiB;
    private Taxi taxiC;

    private HashMap<String, Taxi> taxiMap;

    private List<Taxi> taxiList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        taxiA = new Taxi("A","5,3");
        taxiB = new Taxi("B","2,2");
        taxiC = new Taxi("C","1,1");

        taxiA.canMoveOnNextStep(true);
        taxiB.canMoveOnNextStep(true);
        taxiC.canMoveOnNextStep(true);


        taxiMap = new HashMap<>();
        taxiMap.put("A", taxiA);
        taxiMap.put("B", taxiB);
        taxiMap.put("C", taxiC);

        taxis = new Taxis(taxiMap);

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
        assertEquals("5,3", taxis.taxiLocation("A"));
        assertEquals("2,2", taxis.taxiLocation("B"));
        assertEquals("1,1", taxis.taxiLocation("C"));

    }

    @Test
    public void taxi_should_move_up_one_cell () throws Exception{
        taxiA.move("U", taxiMap);
        assertEquals("5,2",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }

    @Test
    public void taxi_should_move_down_one_cell () throws Exception{
        taxiA.move("D");
        assertEquals("5,4",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }

    @Test
    public void taxi_should_left_up_one_cell () throws Exception{
        taxiA.move("L", taxiMap);
        assertEquals("4,3",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }

    @Test
    public void taxi_should_move_right_one_cell () throws Exception{
        taxiA.move("R", taxiMap);
        assertEquals("6,3",taxiA.gridref());
        assertEquals(1,taxiA.cellometer());

    }



    @Test
    public void taxi_multiple_moves () throws Exception{


        taxiA.move("LL", taxiMap);

        assertEquals("3,3",taxiA.gridref());
    }



    @Test
    public void taxis_should_know_about_all_taxis () throws Exception{
        //arrange
        //act
        Taxis taxis = new Taxis(taxiList);
        //assert
        assertEquals(taxiList, taxis.taxis());
    }






}


