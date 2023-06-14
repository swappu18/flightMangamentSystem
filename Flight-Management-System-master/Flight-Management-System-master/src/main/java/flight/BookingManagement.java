package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class BookingManagement extends JPanel {
    private MainFrame parent;
    Passenger passenger = new Passenger();
    private String[] ans = {"Yes", "No"};
    private String[] meals = {"Standard", "Asian", "Child", "Diabetic", "Fruit", "Hindu", "Kosher"};
    private Integer[] bag_num = new Integer[11];

    public BookingManagement(MainFrame parent){
        for(Integer i = 0; i <= 10; i++){
            bag_num[i] = i;
        }

        setSize(800, 800);
        setLayout(null);

        this.parent = parent;

        JLabel wifi = new JLabel("WIFI: ");
        wifi.setLocation(100, 200);
        wifi.setSize(200, 30);
        add(wifi);

        JComboBox ansBox = new JComboBox(ans);
        ansBox.setLocation(400, 200);
        ansBox.setSize(200, 30);
        add(ansBox);

        JLabel meal = new JLabel("NUTRITION: ");
        meal.setLocation(100, 300);
        meal.setSize(200, 30);
        add(meal);

        JComboBox mealBox = new JComboBox(meals);
        mealBox.setLocation(400, 300);
        mealBox.setSize(200, 30);
        add(mealBox);

        JLabel baggage = new JLabel("ADDITION BAGGAGE: ");
        baggage.setLocation(100, 400);
        baggage.setSize(200, 30);
        add(baggage);

        JComboBox baggageBox = new JComboBox(bag_num);
        baggageBox.setLocation(400, 400);
        baggageBox.setSize(200, 30);
        add(baggageBox);

        JLabel message = new JLabel("NEW MESSAGE: ");
        message.setLocation(100, 450);
        message.setSize(200, 30);
        add(message);

        JTextArea area = new JTextArea();
        area.setLocation(400, 450);
        area.setSize(200, 30);
        add(area);

        JButton update = new JButton("UPDATE");
        update.setLocation(650, 450);
        update.setSize(100, 30);
        add(update);

        update.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.append(passenger.getMessage());
            }
        });

        JButton save = new JButton("SAVE");
        save.setLocation(500, 500);
        save.setSize(100, 30);
        add(save);

        save.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("LIST_OF_TICKETS");
                    outputStream.writeObject(pd);
                    ArrayList<Ticket> tickets = (ArrayList<Ticket>)inputStream.readObject();

                    int price = 0;
                    Ticket ticket = new Ticket();
                    for(Ticket t: tickets){
                        if(t.getPid() == passenger.getPid()){
                            price = t.getPrice();
                            ticket = t;
                            break;
                        }
                    }
                    Options options = new BaggageOption(new MealOption(new WifiOption(new OptionsImpl(price), (String) ansBox.getSelectedItem()), (String) mealBox.getSelectedItem()), (Integer) baggageBox.getSelectedItem());
                    PackageData pd1 = new PackageData("UPDATE_OPTIONS", passenger, options);
                    outputStream.writeObject(pd1);
                    parent.getBookingManagement(passenger).setVisible(false);
                    parent.getClientMenu().setVisible(true);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setLocation(200, 500);
        back.setSize(100, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getBookingManagement(passenger).setVisible(false);
                parent.getClientMenu().setVisible(true);
            }
        });
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
