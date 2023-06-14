package flight;

public class Lufthansa extends AirlineTemplate{

    @Override
    public void setLayOver(Flight flight) {
        flight.setLayover_Time("7");
    }

    @Override
    public void setNoOfStops(Flight flight) {
        flight.setNo_of_stops(2);
    }

    @Override
    public void setFlightType(Flight flight) {
        flight.setFlightType("Non-stop");
    }
}
