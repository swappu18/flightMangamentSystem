package flight;

public class HeadingOffState implements State{

    @Override
    public void action(Flight flight) {
        flight.setStatus("HeadingOff");
    }
}
