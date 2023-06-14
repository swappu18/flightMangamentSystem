package flight;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operation;
    private Passenger passenger;
    private Ticket ticket;
    private Options options;
    private ArrayList<Observer>passengers;
    private Flight flight;
    private String airline_id;

    public PackageData(String operation) {
        this.operation = operation;
    }

    public PackageData(String operation, Passenger passenger, Ticket ticket) {
        this.operation = operation;
        this.passenger = passenger;
        this.ticket = ticket;
    }

    public PackageData(String operation, Flight flight, String airline_id) {
        this.operation = operation;
        this.flight = flight;
        this.airline_id = airline_id;
    }

    public PackageData(String operation, Flight flight) {
        this.operation = operation;
        this.flight = flight;
    }

    public PackageData(String operation, ArrayList<Observer> passengers) {
        this.operation = operation;
        this.passengers = passengers;
    }

    public PackageData(String operation, Passenger passenger, Options options) {
        this.operation = operation;
        this.passenger = passenger;
        this.options = options;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getAirline_id() {
        return airline_id;
    }

    public ArrayList<Observer> getPassengers() {
        return passengers;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "PackageData{" +
                "operation='" + operation + '\'' +
                '}';
    }
}
