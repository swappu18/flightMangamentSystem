package flight;

public class EtihadAirways extends AirlineTemplate{

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
