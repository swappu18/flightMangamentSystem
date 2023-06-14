package flight;

public class QatarAirways extends AirlineTemplate{

    @Override
    public void setLayOver(Flight flight) {
        flight.setLayover_Time("3");
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
