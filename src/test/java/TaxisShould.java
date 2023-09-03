import domain.Dispatcher;
import domain.Taxi;
import domain.TaxiCommand;
import domain.Taxis;
import org.approvaltests.Approvals;
import org.approvaltests.reporters.Junit5Reporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@UseReporter(Junit5Reporter.class)
public class TaxisShould {


    @Test
    public void start_in_correct_grid()
    {
        Taxi taxiA = new Taxi("A","0,0");
        Taxi taxiB = new Taxi("B","0,1");
        Taxi taxiC = new Taxi("C","2,3");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);


        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));


    }


    @Test
    public void all_move_right_one_grid()
    {
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
        dispatcher.dispatch(taxiCommands);

        //assert
        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));


    }

    @Test
    public void all_complete_two_non_blocking_moves()
    {
        Taxi taxiA = new Taxi("A","0,0");
        Taxi taxiB = new Taxi("B","2,2");
        Taxi taxiC = new Taxi("C","4,3");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);


        List<TaxiCommand> taxiCommands = new ArrayList<>();
        taxiCommands.add(new TaxiCommand(taxiA, "RD"));
        taxiCommands.add( new TaxiCommand(taxiB, "LU"));
        taxiCommands.add(new TaxiCommand(taxiC, "LU"));


        //act
        dispatcher.dispatch(taxiCommands);

        //assert
        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));


    }

    @Test
    public void only_a_move()
    {
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
        dispatcher.dispatch(taxiCommands);

        //assert
        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));


    }

    @Test
    public void only_a_and_b_move()
    {
        Taxi taxiA = new Taxi("A","0,0");
        Taxi taxiB = new Taxi("B","1,0");
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
        dispatcher.dispatch(taxiCommands);

        //assert
        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));


    }

    @Test
    public void only_a_and_c_move()
    {
        Taxi taxiA = new Taxi("A","0,0");
        Taxi taxiB = new Taxi("B","0,0");
        Taxi taxiC = new Taxi("C","1,0");

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
        dispatcher.dispatch(taxiCommands);

        //assert
        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));


    }

    @Test
    public void a_block_b()
    {
        Taxi taxiA = new Taxi("A","0,0");
        Taxi taxiB = new Taxi("B","2,0");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);


        List<TaxiCommand> taxiCommands = new ArrayList<>();
        taxiCommands.add(new TaxiCommand(taxiA, "RD"));
        taxiCommands.add( new TaxiCommand(taxiB, "LD"));


        //act
        dispatcher.dispatch(taxiCommands);

        //assert
        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));


    }





    @Test
    void all_arrive_at_airport_5_2(){
        //arrange

        Taxi taxiA = new Taxi("A","5,3");
        Taxi taxiB = new Taxi("B","2,2");
        Taxi taxiC = new Taxi("C","1,1");

        List<Taxi> listOfTaxis = new ArrayList<>();
        listOfTaxis.add(taxiA);
        listOfTaxis.add(taxiB);
        listOfTaxis.add(taxiC);

        Taxis taxis = new Taxis(listOfTaxis);
        Dispatcher dispatcher = new Dispatcher(taxis);


        List<TaxiCommand> taxiCommands = new ArrayList<>();
        taxiCommands.add(new TaxiCommand(taxiA, "LLUURRD"));
        taxiCommands.add( new TaxiCommand(taxiB, "URRRD"));
        taxiCommands.add(new TaxiCommand(taxiC, "RRRRD"));


        //act
        dispatcher.dispatch(taxiCommands);



        //assert
        Approvals.verify(TaxiPrinter.plotTaxis(listOfTaxis));
    }





}
