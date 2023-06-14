package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class AdminMenu extends JPanel {
    private MainFrame parent;

    public AdminMenu(MainFrame parent){
        this.parent = parent;

        setSize(800, 800);
        setLayout(null);

        JButton button1 = new JButton("SEND MESSAGE");
        button1.setLocation(300, 200);
        button1.setSize(200, 30);
        add(button1);

        button1.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("ONLINE_BOARD");
                    outputStream.writeObject(pd);
                    ArrayList<Flight> flights = (ArrayList<Flight>)inputStream.readObject();
                    parent.getSendMessagePage().generateTable(flights);
                    parent.getAdminMenu().setVisible(false);
                    parent.getSendMessagePage().setVisible(true);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        JButton button2 = new JButton("CHANGE STATUS");
        button2.setLocation(300, 300);
        button2.setSize(200, 30);
        add(button2);

        button2.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("FIND_FLIGHTS");
                    outputStream.writeObject(pd);
                    ArrayList<Flight> flights = (ArrayList<Flight>)inputStream.readObject();

                    parent.getChangeStatusPage( ).generateTable(flights);
                    parent.getAdminMenu( ).setVisible(false);
                    parent.getChangeStatusPage( ).setVisible(true);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        JButton button3 = new JButton("CHANGE AIRLINE");
        button3.setLocation(300, 400);
        button3.setSize(200, 30);
        add(button3);

        button3.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("FIND_FLIGHTS");
                    outputStream.writeObject(pd);
                    ArrayList<Flight> flights = (ArrayList<Flight>)inputStream.readObject();

                    parent.getChangeAirlinePage().generateTable(flights);
                    parent.getAdminMenu( ).setVisible(false);
                    parent.getChangeAirlinePage( ).setVisible(true);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        JButton button4 = new JButton("");
        button4.setLocation(300, 500);
        button4.setSize(200, 30);
        add(button4);

        button4.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton back = new JButton("BACK");
        back.setLocation(300, 600);
        back.setSize(200, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getAdminMenu().setVisible(false);
                parent.getAdminLogin().setVisible(true);
            }
        });
    }
}
