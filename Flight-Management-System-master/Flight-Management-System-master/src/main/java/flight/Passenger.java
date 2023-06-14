package flight;

import java.io.Serializable;

public class Passenger implements Observer, Serializable {
    private int pid;
    private String passport;
    private String fname;
    private String lname;
    private String address;
    private int phone;
    private int age;
    private String sex;
    private String flight_code;
    private String options;

    private Subject flight;
    private String message;

    public Passenger() {
    }

    public Passenger(int pid, String passport, String fname, String lname, String address, int phone, int age, String sex, String flight_code, String options, String message) {
        this.pid = pid;
        this.passport = passport;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.flight_code = flight_code;
        this.options = options;
        this.message = message;
    }

    public Passenger(int pid, String passport, String fname, String lname, String address, int phone, int age, String sex, String flight_code) {
        this.pid = pid;
        this.passport = passport;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.flight_code = flight_code;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFlight_code() {
        return flight_code;
    }

    public void setFlight_code(String flight_code) {
        this.flight_code = flight_code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "pid=" + pid +
                ", passport='" + passport + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", flight_code='" + flight_code + '\'' +
                '}';
    }

    @Override
    public void update() {
        String msg = (String)flight.getUpdate(this);
        if(msg == null)
            this.message = "No new message";
        else
            this.message = msg;
    }

    @Override
    public void setSubject(Subject sub) {
        this.flight = sub;
    }
}
