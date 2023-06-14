package flight;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class DBManager implements Serializable {
    private Connection connection;

    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airlines_db?useUnicode=true&serverTimezone=UTC","root", "");
            System.out.println("CONNECTED");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public ArrayList<City> getCityList(){
        ArrayList <City> cities = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM city");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String cname = rs.getString("CNAME");
                String state = rs.getString("STATE");
                String country = rs.getString("COUNTRY");
                String shname = rs.getString("SHNAME");

                cities.add(new City(cname, state, country, shname));
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }

        return cities;
    }

    public ArrayList<Flight> getFlightList(){
        ArrayList<Flight> flights = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT FLIGHT_CODE, SOURCE, DESTINATION, ARRIVAL, DEPARTURE, STATUS, DURATION, FLIGHTTYPE, LAYOVER_TIME, NO_OF_STOPS, AL_NAME FROM flight INNER JOIN airline ON flight.AIRLINEID = airline.AIRLINEID");

            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                String flight = rs.getString("FLIGHT_CODE");
                String source = rs.getString("SOURCE");
                String destination = rs.getString("DESTINATION");
                String arrival = rs.getString("ARRIVAL");
                String departure = rs.getString("DEPARTURE");
                String status = rs.getString("STATUS");
                String duration = rs.getString("DURATION");
                String flightType = rs.getString("FLIGHTTYPE");
                String layoverTime = rs.getString("LAYOVER_TIME");
                int no_of_stops = rs.getInt("NO_OF_STOPS");
                String airline = rs.getString("AL_NAME");

                flights.add(new Flight(flight, source, destination, arrival, departure, status, duration, flightType, layoverTime, no_of_stops, airline));
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return flights;
    }

    public ArrayList<Passenger> getPassengerList(){
        ArrayList<Passenger> passengers = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM passenger");

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int pid = rs.getInt("PID");
                String passport = rs.getString("PASSPORTNO");
                String fname = rs.getString("FNAME");
                String lname = rs.getString("LNAME");
                String address = rs.getString("ADDRESS");
                int phone = rs.getInt("PHONE");
                int age = rs.getInt("AGE");
                String sex = rs.getString("SEX");
                String flight_code = rs.getString("FLIGHT_CODE");
                String options = rs.getString("OPTIONS");
                String message = rs.getString("MESSAGE");

                passengers.add(new Passenger(pid, passport, fname, lname, address, phone, age, sex, flight_code, options, message));
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return passengers;
    }

    public void addNewPassenger(Passenger passenger){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO passenger(PID, PASSPORTNO, FNAME, LNAME, ADDRESS, PHONE, AGE, SEX, FLIGHT_CODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            st.setInt(1, passenger.getPid());
            st.setString(2, passenger.getPassport());
            st.setString(3, passenger.getFname());
            st.setString(4, passenger.getLname());
            st.setString(5, passenger.getAddress());
            st.setInt(6, passenger.getPhone());
            st.setInt(7, passenger.getAge());
            st.setString(8, passenger.getSex());
            st.setString(9, passenger.getFlight_code());

            st.executeUpdate();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public ArrayList<Ticket> getTicketList(){
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM ticket");

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                String ticketNO = rs.getString("TICKET_NUMBER");
                String source = rs.getString("SOURCE");
                String destination = rs.getString("DESTINATION");
                Date booking_date = rs.getDate("DATE_OF_BOOKING");
                Date travel_date = rs.getDate("DATE_OF_TRAVEL");
                String seatNO = rs.getString("SEATNO");
                int price = rs.getInt("PRICE");
                int pid = rs.getInt("PID");

                tickets.add(new Ticket(ticketNO, source, destination, booking_date, travel_date, seatNO, price, pid));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return tickets;
    }

    public void addNewTicket(Ticket ticket){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO ticket(TICKET_NUMBER, SOURCE, DESTINATION, DATE_OF_BOOKING, DATE_OF_TRAVEL, SEATNO, PRICE, PID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

            st.setString(1, ticket.getTicketNO());
            st.setString(2, ticket.getSource());
            st.setString(3, ticket.getDestination());
            st.setDate(4, new java.sql.Date(ticket.getBooking_date().getTime()));
            st.setDate(5, new java.sql.Date(ticket.getTravel_date().getTime()));
            st.setString(6, ticket.getSeatNO());
            st.setInt(7, ticket.getPrice());
            st.setInt(8, ticket.getPid());

            st.executeUpdate();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void updateOptions(Passenger passenger, Options options){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE passenger SET options = ? WHERE PID = ?");

            st.setString(1, options.getDescription());
            st.setInt(2, passenger.getPid());

            st.executeUpdate();

            PreparedStatement st1 = connection.prepareStatement("UPDATE ticket SET price = ? WHERE PID = ?");

            st1.setInt(1, options.getPrice());
            st1.setInt(2, passenger.getPid());

            st1.executeUpdate();

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void updateMessage(ArrayList <Observer> observers){
        try {
            for(Observer obs: observers){
                PreparedStatement st = connection.prepareStatement("UPDATE passenger SET MESSAGE = ? WHERE PID = ?");

                st.setString(1, obs.getMessage());
                st.setInt(2, obs.getPid());

                st.executeUpdate();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void updateStatus(Flight flight){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE flight SET STATUS = ? WHERE FLIGHT_CODE = ?");

            st.setString(1, flight.getStatus());
            st.setString(2, flight.getFlight_code());

            st.executeUpdate();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public ArrayList<Airline> getAirlinesList(){
        ArrayList<Airline>airlines = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM airline");

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                String airline_id = rs.getString("AIRLINEID");
                String al_name = rs.getString("AL_NAME");
                String TDC = rs.getString("THREE_DIGIT_CODE");

                airlines.add(new Airline(airline_id, al_name, TDC));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return airlines;
    }

    public void updateAirline(Flight flight, String airline_id){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE flight SET FLIGHTTYPE = ?, LAYOVER_TIME = ?, NO_OF_STOPS = ?, AIRLINEID = ? WHERE FLIGHT_CODE = ?");

            st.setString(1, flight.getFlightType());
            st.setString(2, flight.getLayover_Time());
            st.setInt(3, flight.getNo_of_stops());
            st.setString(4, airline_id);
            st.setString(5, flight.getFlight_code());

            st.executeUpdate();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
