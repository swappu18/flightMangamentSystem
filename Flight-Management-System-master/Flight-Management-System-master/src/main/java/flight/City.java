package flight;

import java.io.Serializable;

public class City implements Serializable {
    private String cname;
    private String state;
    private String country;
    private String shname;

    public City(String cname, String state, String country, String shname) {
        this.cname = cname;
        this.state = state;
        this.country = country;
        this.shname = shname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    public String getShname() {
        return shname;
    }

    public void setShname(String shname) {
        this.shname = shname;
    }

    @Override
    public String toString() {
        return "City{" +
                "cname='" + cname + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", shname='" + shname + '\'' +
                '}';
    }
}
