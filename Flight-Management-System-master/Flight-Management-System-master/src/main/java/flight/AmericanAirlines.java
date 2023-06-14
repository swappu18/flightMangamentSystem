package flight;

public class AmericanAirlines extends AirlineTemplate{

    @Override
    public void setLayOver(Flight flight) {
        flight.setLayover_Time("0");
    }

    @Override
    public void setNoOfStops(Flight flight) {
        flight.setNo_of_stops(0);
    }

    @Override
    public void setFlightType(Flight flight) {
        flight.setFlightType("Direct");
    }
}
