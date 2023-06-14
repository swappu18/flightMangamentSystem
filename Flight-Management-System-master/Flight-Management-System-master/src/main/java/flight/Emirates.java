package flight;

public class Emirates extends AirlineTemplate{

    @Override
    public void setLayOver(Flight flight) {
        flight.setLayover_Time("5");
    }

    @Override
    public void setNoOfStops(Flight flight) {
        flight.setNo_of_stops(1);
    }

    @Override
    public void setFlightType(Flight flight) {
        flight.setFlightType("Connecting");
    }
}
