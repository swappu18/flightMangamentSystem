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

public class ChangeStatusPage extends JPanel {
    private MainFrame parent;
    private JTable table;
    private String[] header = {"Index", "Code", "Source", "Dest", "Arr", "Dep", "Status", "Dur", "Type", "Stops", "Airline"};
    private String[] statuses = {"Boarding", "Cancelled", "CheckInn", "Departure", "HeadingOff"};

    public ChangeStatusPage(MainFrame parent){
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

        JLabel setStatus = new JLabel("Set Status");
        setStatus.setLocation(100, 600);
        setStatus.setSize(200, 30);
        add(setStatus);

        JComboBox ansBox = new JComboBox(statuses);
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
                try {
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("FIND_FLIGHTS");
                    outputStream.writeObject(pd);
                    ArrayList<Flight> flights = (ArrayList<Flight>)inputStream.readObject();

                    String choose = (String)ansBox.getSelectedItem();
                    if(choose.equals("Boarding")){
                        BoardingState boardingState = new BoardingState();
                        boardingState.action(flights.get(Integer.parseInt(chooseT.getText()) - 1));
                    }else if(choose.equals("Cancelled")){
                        CancelledState cancelledState = new CancelledState();
                        cancelledState.action(flights.get(Integer.parseInt(chooseT.getText()) - 1));
                    }else if(choose.equals("CheckInn")){
                        CheckInState checkInState = new CheckInState();
                        checkInState.action(flights.get(Integer.parseInt(chooseT.getText()) - 1));
                    }else if(choose.equals("Departure")){
                        DeparturedState departuredState = new DeparturedState();
                        departuredState.action(flights.get(Integer.parseInt(chooseT.getText()) - 1));
                    }else if(choose.equals("HeadingOff")){
                        HeadingOffState headingOffState = new HeadingOffState();
                        headingOffState.action(flights.get(Integer.parseInt(chooseT.getText()) - 1));
                    }

                    PackageData pd1 = new PackageData("CHANGE_STATUS", flights.get(Integer.parseInt(chooseT.getText()) - 1));
                    outputStream.writeObject(pd1);
                }catch(Exception exception){
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
                parent.getChangeStatusPage().setVisible(false);
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
