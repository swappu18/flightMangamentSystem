package flight;

public class BoardingState implements State{
    @Override
    public void action(Flight flight) {
        flight.setStatus("Boarding");
    }
}
