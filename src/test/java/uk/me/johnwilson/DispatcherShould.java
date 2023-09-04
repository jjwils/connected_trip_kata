package uk.me.johnwilson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class DispatcherShould {

    @Test
    void on_next_step_move_a_not_b_and_c(){

        Taxi taxiA = new Taxi("A","0,0");
        Taxi taxiB = new Taxi("B","0,0");
        Taxi taxiC = new Taxi("C","0,0");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);


        List<TaxiCommand> taxiCommands = new ArrayList<>();
        taxiCommands.add(new TaxiCommand(taxiA, "R"));
        taxiCommands.add( new TaxiCommand(taxiB, "R"));
        taxiCommands.add(new TaxiCommand(taxiC, "R"));


        //act
        dispatcher.assessNextSteps(listOfTaxis);

        //assert
        assertTrue(taxiA.canMoveOnNextStep());
        assertFalse(taxiB.canMoveOnNextStep());
        assertFalse(taxiC.canMoveOnNextStep());


    }

    @Test
    void on_next_step_move_all(){

        Taxi taxiA = new Taxi("A","1,0");
        Taxi taxiB = new Taxi("B","1,1");
        Taxi taxiC = new Taxi("C","2,0");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);


        List<TaxiCommand> taxiCommands = new ArrayList<>();
        taxiCommands.add(new TaxiCommand(taxiA, "R"));
        taxiCommands.add( new TaxiCommand(taxiB, "R"));
        taxiCommands.add(new TaxiCommand(taxiC, "R"));


        //act
        dispatcher.assessNextSteps(listOfTaxis);

        //assert
        assertTrue(taxiA.canMoveOnNextStep());
        assertTrue(taxiB.canMoveOnNextStep());
        assertTrue(taxiC.canMoveOnNextStep());


    }

    @Test
    void on_next_step_move_a_and_b_not_c(){

        Taxi taxiA = new Taxi("A","1,0");
        Taxi taxiB = new Taxi("B","0,0");
        Taxi taxiC = new Taxi("C","0,0");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);


        List<TaxiCommand> taxiCommands = new ArrayList<>();
        taxiCommands.add(new TaxiCommand(taxiA, "R"));
        taxiCommands.add( new TaxiCommand(taxiB, "R"));
        taxiCommands.add(new TaxiCommand(taxiC, "R"));


        //act
        dispatcher.assessNextSteps(listOfTaxis);

        //assert
        assertTrue(taxiA.canMoveOnNextStep());
        assertTrue(taxiB.canMoveOnNextStep());
        assertFalse(taxiC.canMoveOnNextStep());

    }

}