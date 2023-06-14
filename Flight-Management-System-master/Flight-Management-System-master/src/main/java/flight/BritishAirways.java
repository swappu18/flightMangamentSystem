package flight;

public class BritishAirways extends AirlineTemplate{
    @Override
    public void setLayOver(Flight flight) {
        flight.setLayover_Time("10");
    }

    @Override
    public void setNoOfStops(Flight flight) {
        flight.setNo_of_stops(2);
    }

    @Override
    public void setFlightType(Flight flight) {
        flight.setFlightType("Connecting");
    }
}
