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

public class SendMessagePage extends JPanel {
    private MainFrame parent;
    private JTable table;
    private String[] header = {"Index", "Code", "Source", "Dest", "Arr", "Dep", "Status", "Dur", "Type", "Stops", "Airline"};

    public SendMessagePage(MainFrame parent){
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

        JLabel write = new JLabel("WRITE A MESSAGE: ");
        write.setLocation(100, 600);
        write.setSize(200, 30);
        add(write);

        JTextField writeT = new JTextField();
        writeT.setLocation(400, 600);
        writeT.setSize(300, 50);
        add(writeT);

        JButton send = new JButton("SEND");
        send.setLocation(500, 650);
        send.setSize(100, 30);
        add(send);

        send.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("SEND_MESSAGE");
                    outputStream.writeObject(pd);

                    ArrayList<Flight> flights = (ArrayList<Flight>)inputStream.readObject();
                    ArrayList<Passenger> passengers = (ArrayList<Passenger>)inputStream.readObject();

                    String code = flights.get(Integer.parseInt(chooseT.getText()) - 1).getFlight_code();
                    System.out.println(code);
                    for(Passenger p: passengers) {
                        if (p.getFlight_code( ).equals(code)) {
                            System.out.println(p.toString() );
                            flights.get(Integer.parseInt(chooseT.getText( )) - 1).register(p);
                            p.setSubject(flights.get(Integer.parseInt(chooseT.getText()) - 1));
                        }
                    }
                    flights.get(Integer.parseInt(chooseT.getText()) - 1).postMessage(writeT.getText());
                    PackageData pd1 = new PackageData("CHANGE_MESSAGE", flights.get(Integer.parseInt(chooseT.getText()) - 1).getObservers());
                    outputStream.writeObject(pd1);
                    parent.getSendMessagePage().setVisible(false);
                    parent.getAdminMenu().setVisible(true);
                } catch (Exception Exception) {
                    Exception.printStackTrace( );
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
                parent.getSendMessagePage().setVisible(false);
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
