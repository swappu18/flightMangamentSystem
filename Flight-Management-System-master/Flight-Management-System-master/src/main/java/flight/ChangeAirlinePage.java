package flight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ChangeAirlinePage extends JPanel {
    private MainFrame parent;
    private JTable table;
    private String[] header = {"Index", "Code", "Source", "Dest", "Arr", "Dep", "Status", "Dur", "Type", "Stops", "Airline"};
    private String[] airlines = {"Jet Airways", "American Airlines", "Air India Limited", "British Airways", "Emirates", "Etihad Airways", "Lufthansa", "Qatar Airways"};

    public ChangeAirlinePage(MainFrame parent){
        setSize(800, 800);
        setLayout(null);

        this.parent = parent;

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setLocation(0, 0);
        scrollPane.setSize(800, 500);
        add(scrollPane);

        JLabel choose = new JLabel("CHOOSE FLIGHT BY INDEX: ");
        choose.setLocation(100, 550);
        choose.setSize(200, 30);
        add(choose);

        JTextField chooseT = new JTextField();
        chooseT.setLocation(400, 550);
        chooseT.setSize(100, 30);
        add(chooseT);

        JLabel setStatus = new JLabel("Set Airline");
        setStatus.setLocation(100, 600);
        setStatus.setSize(200, 30);
        add(setStatus);

        JComboBox ansBox = new JComboBox(airlines);
        ansBox.setLocation(400, 600);
        ansBox.setSize(200, 30);
        add(ansBox);

        JButton change = new JButton("CHANGE");
        change.setLocation(500, 650);
        change.setSize(100, 30);
        add(change);

        change.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    System.out.println("!!!5");
                    PackageData pd = new PackageData("FIND_FLIGHTS");
                    outputStream.writeObject(pd);
                    ArrayList<Flight>flights = (ArrayList<Flight>)inputStream.readObject();
                    System.out.println("!!!3");
                    String ans = (String)ansBox.getSelectedItem();
                    AirlineTemplate airlineTemplate = null;
                    if(ans.equals("Jet Airways")){
                        airlineTemplate = new JetAirways();
                    }else if(ans.equals("American Airlines")){
                        airlineTemplate = new AmericanAirlines();
                    }else if(ans.equals("Air India Limited")){
                        airlineTemplate = new AirIndiaLimited();
                    }else if(ans.equals("British Airways")){
                        airlineTemplate = new BritishAirways();
                    }else if(ans.equals("Emirates")){
                        airlineTemplate = new Emirates();
                    }else if(ans.equals("Etihad Airways")){
                        airlineTemplate = new EtihadAirways();
                    }else if(ans.equals("Lufthansa")){
                        airlineTemplate = new Lufthansa();
                    }else if(ans.equals("Qatar Airways")){
                        airlineTemplate = new QatarAirways();
                    }
                    System.out.println("!!!4");
                    PackageData pd1 = new PackageData("CHANGE_AIRLINE");
                    outputStream.writeObject(pd1);
                    ArrayList <Airline> airlines1 = (ArrayList<Airline>)inputStream.readObject();
                    System.out.println("!!!1");
                    String airline_id = "";
                    for(Airline a: airlines1){
                        if(a.getAl_name().equals(ans)){
                            airline_id = a.getAirline_id();
                            break;
                        }
                    }
                    System.out.println("!!!2");
                    airlineTemplate.setAirline(flights.get(Integer.parseInt(chooseT.getText()) - 1));

                    PackageData pd2 = new PackageData("UPDATE_AIRLINE", flights.get(Integer.parseInt(chooseT.getText()) - 1), airline_id);
                    outputStream.writeObject(pd2);

                    parent.getChangeAirlinePage().setVisible(false);
                    parent.getAdminMenu().setVisible(true);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setLocation(200, 650);
        back.setSize(100, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getChangeAirlinePage().setVisible(false);
                parent.getAdminMenu().setVisible(true);
            }
        });

    }

    public void generateTable(ArrayList<Flight> flights){
        Object[][] data = new Object[flights.size()][11];

        for(int i = 0; i < flights.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = flights.get(i).getFlight_code();
            data[i][2] = flights.get(i).getSource();
            data[i][3] = flights.get(i).getDestination();
            data[i][4] = flights.get(i).getArrival();
            data[i][5] = flights.get(i).getDeparture();
            data[i][6] = flights.get(i).getStatus();
            data[i][7] = flights.get(i).getDuration();
            data[i][8] = flights.get(i).getFlightType();
            data[i][9] = flights.get(i).getNo_of_stops();
            data[i][10] = flights.get(i).getAirline();
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);
    }
}
