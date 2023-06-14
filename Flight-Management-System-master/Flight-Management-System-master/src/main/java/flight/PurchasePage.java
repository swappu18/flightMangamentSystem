package flight;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class PurchasePage extends JPanel {
    private MainFrame parent;
    private ArrayList <City> cities1;
    private String []cities = new String[250];

    public PurchasePage(MainFrame parent){
        try{
            Socket socket = new Socket("localhost", 1234);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            PackageData pd = new PackageData("LIST_OF_CITIES");
            outputStream.writeObject(pd);
            outputStream.reset();
            cities1 = (ArrayList<City>) inputStream.readObject();
        }catch(Exception exception){
            exception.printStackTrace();
        }
        for(int i = 0; i < cities1.size(); i++){
            cities[i] = cities1.get(i).getCname() + ", " + cities1.get(i).getCountry();
        }
        this.parent = parent;

        setSize(800, 800);
        setLayout(null);

        JLabel from = new JLabel("FROM: ");
        from.setLocation(200, 200);
        from.setSize(100, 30);
        add(from);

        JComboBox cityBox = new JComboBox(cities);
        cityBox.setLocation(350, 200);
        cityBox.setSize(200, 30);
        add(cityBox);

        JLabel to = new JLabel("TO: ");
        to.setLocation(200, 300);
        to.setSize(100, 30);
        add(to);

        JComboBox cityBox2 = new JComboBox(cities);
        cityBox2.setLocation(350, 300);
        cityBox2.setSize(200, 30);
        add(cityBox2);

        JLabel date1 = new JLabel("DEPARTURE: ");
        date1.setLocation(200, 400);
        date1.setSize(100, 30);
        add(date1);

        UtilDateModel model1 = new UtilDateModel();
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        datePicker1.setLocation(350, 400);
        datePicker1.setSize(200, 30);
        add(datePicker1);

        JButton find = new JButton("FIND");
        find.setLocation(500, 600);
        find.setSize(100, 30);
        add(find);

        find.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    String city1 = (String) cityBox.getSelectedItem();
                    String city2 = (String) cityBox2.getSelectedItem();
                    Date date1 = (Date) datePicker1.getModel().getValue();

                    PackageData pd = new PackageData("FIND_FLIGHTS");
                    outputStream.writeObject(pd);
                    ArrayList <Flight> flights = (ArrayList<Flight>) inputStream.readObject();

                    String shname1 = "", shname2 = "";
                    for(int i = 0; i < cities1.size(); i++){
                        if(city1.equals(cities1.get(i).getCname() + ", " + cities1.get(i).getCountry())){
                            shname1 = cities1.get(i).getShname();
                        }
                        if(city2.equals(cities1.get(i).getCname() + ", " + cities1.get(i).getCountry())){
                            shname2 = cities1.get(i).getShname();
                        }
                    }
                    parent.getFindFlightPage(date1).generateTable(flights, shname1, shname2);
                    parent.getPurchasePage().setVisible(false);
                    parent.getFindFlightPage(date1).setVisible(true);

                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setLocation(200, 600);
        back.setSize(100, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getPurchasePage().setVisible(false);
                parent.getClientMenu().setVisible(true);
            }
        });
    }
}
