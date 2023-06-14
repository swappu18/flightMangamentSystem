package flight;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private String ticketNO;
    private String source;
    private String destination;;
    private Date booking_date;
    private Date travel_date;
    private String seatNO;
    private int price;
    private int pid;

    public Ticket() {
    }

    public Ticket(String ticketNO, String source, String destination, Date booking_date, Date travel_date, String seatNO, int price, int pid) {
        this.ticketNO = ticketNO;
        this.source = source;
        this.destination = destination;
        this.booking_date = booking_date;
        this.travel_date = travel_date;
        this.seatNO = seatNO;
        this.price = price;
        this.pid = pid;
    }

    public String getTicketNO() {
        return ticketNO;
    }

    public void setTicketNO(String ticketNO) {
        this.ticketNO = ticketNO;
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

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public Date getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(Date travel_date) {
        this.travel_date = travel_date;
    }

    public String getSeatNO() {
        return seatNO;
    }

    public void setSeatNO(String seatNO) {
        this.seatNO = seatNO;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNO='" + ticketNO + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", booking_date=" + booking_date +
                ", travel_date=" + travel_date +
                ", seatNO='" + seatNO + '\'' +
                ", price=" + price +
                ", pid=" + pid +
                '}';
    }
}
