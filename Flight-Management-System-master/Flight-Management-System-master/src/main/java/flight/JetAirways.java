package flight;

public class JetAirways extends AirlineTemplate{


    @Override
    public void setLayOver(Flight flight) {
        flight.setLayover_Time("4");
    }

    @Override
    public void setNoOfStops(Flight flight) {
        flight.setNo_of_stops(1);
    }

    @Override
    public void setFlightType(Flight flight) {
        flight.setFlightType("Non-stop");
    }
}
