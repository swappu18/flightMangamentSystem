package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FillInfoPage extends JPanel {
    private MainFrame parent;
    private int choose;
    private Date date;
    private String[] sexes = {"F", "M"};

    public FillInfoPage(MainFrame parent){
        setSize(800, 800);
        setLayout(null);

        this.parent = parent;

        JLabel name = new JLabel("NAME: ");
        name.setLocation(200, 100);
        name.setSize(200, 30);
        add(name);

        JTextField nameT = new JTextField();
        nameT.setLocation(500, 100);
        nameT.setSize(200, 30);
        add(nameT);

        JLabel surname = new JLabel("SURNAME: ");
        surname.setLocation(200, 150);
        surname.setSize(200, 30);
        add(surname);

        JTextField surnameT = new JTextField();
        surnameT.setLocation(500, 150);
        surnameT.setSize(200, 30);
        add(surnameT);

        JLabel passport = new JLabel("PASSPORT NO: ");
        passport.setLocation(200, 200);
        passport.setSize(200, 30);
        add(passport);

        JTextField passportT = new JTextField();
        passportT.setLocation(500, 200);
        passportT.setSize(200, 30);
        add(passportT);

        JLabel address = new JLabel("ADDRESS: ");
        address.setLocation(200, 250);
        address.setSize(200, 30);
        add(address);

        JTextField addressT = new JTextField();
        addressT.setLocation(500, 250);
        addressT.setSize(200, 30);
        add(addressT);

        JLabel age = new JLabel("AGE: ");
        age.setLocation(200, 300);
        age.setSize(200, 30);
        add(age);

        JTextField ageT = new JTextField();
        ageT.setLocation(500, 300);
        ageT.setSize(200, 30);
        add(ageT);

        JLabel sex = new JLabel("SEX: ");
        sex.setLocation(200, 350);
        sex.setSize(200, 30);
        add(sex);

        JComboBox sexBox = new JComboBox(sexes);
        sexBox.setLocation(500, 350);
        sexBox.setSize(200, 30);
        add(sexBox);

        JLabel phone = new JLabel("PHONE: ");
        phone.setLocation(200, 400);
        phone.setSize(200, 30);
        add(phone);

        JTextField phoneT = new JTextField();
        phoneT.setLocation(500, 400);
        phoneT.setSize(200, 30);
        add(phoneT);

        JLabel seat = new JLabel("SEAT: ");
        seat.setLocation(200, 450);
        seat.setSize(200, 30);
        add(seat);

        JTextField seatT = new JTextField();
        seatT.setLocation(500, 450);
        seatT.setSize(200, 30);
        add(seatT);

        JButton buy = new JButton("BUY");
        buy.setLocation(500, 500);
        buy.setSize(200, 30);
        add(buy);

        buy.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    String name = nameT.getText();
                    String surname = surnameT.getText();
                    String passport = passportT.getText();
                    String address = addressT.getText();
                    int age = Integer.parseInt(ageT.getText());
                    String sex = (String) sexBox.getSelectedItem();
                    int phone = Integer.parseInt(phoneT.getText());

                    PackageData pd = new PackageData("COUNT_PASSENGER");
                    outputStream.writeObject(pd);
                    int id = (Integer) inputStream.readObject();

                    ArrayList <Flight> flights = (ArrayList<Flight>) inputStream.readObject();

                    Passenger passenger = new Passenger(id + 1, passport, name, surname, address, phone, age, sex, flights.get(choose - 1).getFlight_code());

                    Ticket ticket = new Ticket(shuffle(), flights.get(choose - 1).getSource(), flights.get(choose - 1).getDestination(), new Date(), date, seatT.getText(), 120000, id + 1);

                    PackageData pd1 = new PackageData("ADD_PASSENGER", passenger, ticket);
                    outputStream.writeObject(pd1);
                    outputStream.reset();
                    parent.getFillInfoPage(choose, date).setVisible(false);
                    parent.getClientMenu().setVisible(true);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });


        JButton back = new JButton("BACK");
        back.setLocation(200, 500);
        back.setSize(200, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getFillInfoPage(choose, date).setVisible(false);
                parent.getFindFlightPage(date).setVisible(true);
            }
        });
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String shuffle(){
        char[] s = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '1', '0'};
        Random rnd = ThreadLocalRandom.current();
        for(int i = s.length - 1; i > 0; i--){
            int index = rnd.nextInt(i + 1);
            char a = s[index];
            s[index] = s[i];
            s[i] = a;
        }
        return new String(s);
    }
}
