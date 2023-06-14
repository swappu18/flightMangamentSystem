package flight;

public class CheckInState implements State{
    @Override
    public void action(Flight flight) {
        flight.setStatus("CheckIn");
    }
}
