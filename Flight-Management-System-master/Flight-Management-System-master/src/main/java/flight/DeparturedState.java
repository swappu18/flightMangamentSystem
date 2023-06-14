package flight;

public class DeparturedState implements State{
    @Override
    public void action(Flight flight) {
        flight.setStatus("Departure");
    }
}
