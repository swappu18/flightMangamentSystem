package flight;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Flight implements Serializable, Subject{
    private String flight_code;
    private String source;
    private String destination;
    private String arrival;
    private String departure;
    private String status;
    private String duration;
    private String flightType;
    private String layover_Time;
    private int no_of_stops;
    private String airline;

    private ArrayList<Observer>observers = new ArrayList<>(  );
    private String message;
    private boolean changed;

    public Flight(String flight_code, String source, String destination, String arrival, String departure, String status, String duration, String flightType, String layover_Time, int no_of_stops, String airline) {
        this.flight_code = flight_code;
        this.source = source;
        this.destination = destination;
        this.arrival = arrival;
        this.departure = departure;
        this.status = status;
        this.duration = duration;
        this.flightType = flightType;
        this.layover_Time = layover_Time;
        this.no_of_stops = no_of_stops;
        this.airline = airline;
    }

    public Flight(String flight_code, String source, String destination, String arrival, String departure, String status, String duration, String flightType, String layover_Time, int no_of_stops, String airline, ArrayList<Observer> observers) {
        this.flight_code = flight_code;
        this.source = source;
        this.destination = destination;
        this.arrival = arrival;
        this.departure = departure;
        this.status = status;
        this.duration = duration;
        this.flightType = flightType;
        this.layover_Time = layover_Time;
        this.no_of_stops = no_of_stops;
        this.airline = airline;
        this.observers = observers;
    }

    public String getFlight_code() {
        return flight_code;
    }

    public void setFlight_code(String flight_code) {
        this.flight_code = flight_code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getLayover_Time() {
        return layover_Time;
    }

    public void setLayover_Time(String layover_Time) {
        this.layover_Time = layover_Time;
    }

    public int getNo_of_stops() {
        return no_of_stops;
    }

    public void setNo_of_stops(int no_of_stops) {
        this.no_of_stops = no_of_stops;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flight_code='" + flight_code + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departure='" + departure + '\'' +
                ", status='" + status + '\'' +
                ", duration='" + duration + '\'' +
                ", flightType='" + flightType + '\'' +
                ", layover_Time='" + layover_Time + '\'' +
                ", no_of_stops=" + no_of_stops +
                ", airline=" + airline +
                '}';
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    @Override
    public void register(Observer obs) {
        if(obs == null)throw  new NullPointerException("Null Observer");
        if(!observers.contains(obs))
            this.observers.add(obs);
    }

    @Override
    public void unregister(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for(Observer obs: this.observers){
            obs.update();
        }
    }

    @Override
    public Object getUpdate(Observer obs) {
        return this.message;
    }

    public void postMessage(String msg) throws IOException {
        this.message = msg;
        notifyObservers();
    }
}
