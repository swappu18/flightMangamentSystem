package flight;

import java.io.Serializable;

public class Airport implements Serializable {
    private String ap_name;
    private String state;
    private String country;
    private String cname;

    public Airport(String ap_name, String state, String country, String cname) {
        this.ap_name = ap_name;
        this.state = state;
        this.country = country;
        this.cname = cname;
    }

    public String getAp_name() {
        return ap_name;
    }

    public void setAp_name(String ap_name) {
        this.ap_name = ap_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "ap_name='" + ap_name + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
