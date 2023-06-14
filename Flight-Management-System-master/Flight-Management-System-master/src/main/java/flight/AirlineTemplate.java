package flight;

public abstract class AirlineTemplate {
    public abstract void setLayOver(Flight flight);
    public abstract void setNoOfStops(Flight flight);
    public abstract void setFlightType(Flight flight);

    public final void setAirline(Flight flight){
        setLayOver(flight);
        setNoOfStops(flight);
        setFlightType(flight);
    }
}
