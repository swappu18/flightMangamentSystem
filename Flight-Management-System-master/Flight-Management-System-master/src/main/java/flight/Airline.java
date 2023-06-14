package flight;

import java.io.Serializable;

public class Airline implements Serializable {
    private String airline_id;
    private String al_name;
    private String TDC;

    public Airline(String airline_id, String al_name, String TDC) {
        this.airline_id = airline_id;
        this.al_name = al_name;
        this.TDC = TDC;
    }

    public String getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(String airline_id) {
        this.airline_id = airline_id;
    }

    public String getAl_name() {
        return al_name;
    }

    public void setAl_name(String al_name) {
        this.al_name = al_name;
    }

    public String getTDC() {
        return TDC;
    }

    public void setTDC(String TDC) {
        this.TDC = TDC;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airline_id='" + airline_id + '\'' +
                ", al_name='" + al_name + '\'' +
                ", TDC='" + TDC + '\'' +
                '}';
    }
}
