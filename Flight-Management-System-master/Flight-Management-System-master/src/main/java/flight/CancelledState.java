package flight;

public class CancelledState implements State{
    @Override
    public void action(Flight flight) {
        flight.setStatus("Cancelled");
    }
}
