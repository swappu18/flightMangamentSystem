package flight;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private Socket socket;
    private DBManager dbManager;

    public ClientHandler(Socket socket, DBManager dbManager){
        this.socket = socket;
        this.dbManager = dbManager;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected!");
            dbManager.connect( );
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream( ));
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while(true){
                ArrayList <City> cities = dbManager.getCityList();
                ArrayList <Flight> flights = dbManager.getFlightList();
                ArrayList <Passenger> passengers = dbManager.getPassengerList();
                ArrayList <Ticket> tickets = dbManager.getTicketList();
                ArrayList <Airline> airlines = dbManager.getAirlinesList();
                PackageData data = (PackageData) inputStream.readObject();
                System.out.println(flights.toString());
                if(data.getOperation().equals("LIST_OF_CITIES")){
                    outputStream.writeObject(cities);
                }else if(data.getOperation().equals("FIND_FLIGHTS")){
                    outputStream.writeObject(flights);
                }else if(data.getOperation().equals("ONLINE_BOARD")){
                    outputStream.writeObject(flights);
                }else if(data.getOperation().equals("COUNT_PASSENGER")){
                    outputStream.writeObject(passengers.size());
                    outputStream.writeObject(flights);
                }else if(data.getOperation().equals("ADD_PASSENGER")){
                    dbManager.addNewPassenger(data.getPassenger());
                    dbManager.addNewTicket(data.getTicket());
                }else if(data.getOperation().equals("LIST_OF_PASSENGERS")){
                    passengers.toString();
                    outputStream.writeObject(passengers);
                }else if (data.getOperation().equals("LIST_OF_TICKETS")) {
                    outputStream.writeObject(tickets);
                }else if(data.getOperation().equals("UPDATE_OPTIONS")){
                    dbManager.updateOptions(data.getPassenger(), data.getOptions());
                }else if(data.getOperation().equals("SEND_MESSAGE")){
                    outputStream.writeObject(flights);
                    outputStream.writeObject(passengers);
                }else if(data.getOperation().equals("CHANGE_MESSAGE")){
                    dbManager.updateMessage(data.getPassengers());
                }else if(data.getOperation().equals("CHANGE_STATUS")){
                    dbManager.updateStatus(data.getFlight());
                }else if(data.getOperation().equals("CHANGE_AIRLINE")){
                    outputStream.writeObject(airlines);
                }else if(data.getOperation().equals("UPDATE_AIRLINE")){
                    dbManager.updateAirline(data.getFlight(), data.getAirline_id());
                }
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
