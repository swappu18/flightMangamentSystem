package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMenu extends JPanel {
    private MainFrame parent;

    public ClientMenu(MainFrame parent){
        this.parent = parent;

        setSize(800, 800);
        setLayout(null);

        JButton button1 = new JButton("PURCHASE");
        button1.setLocation(300, 300);
        button1.setSize(200, 30);
        add(button1);

        button1.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getClientMenu().setVisible(false);
                parent.getPurchasePage().setVisible(true);
            }
        });

        JButton button2 = new JButton("BOOKING MANAGEMENT");
        button2.setLocation(300, 400);
        button2.setSize(200, 30);
        add(button2);

        button2.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getClientMenu().setVisible(false);
                parent.getpLoginPage().setVisible(true);
            }
        });

        JButton button3 = new JButton("ONLINE BOARD");
        button3.setLocation(300, 500);
        button3.setSize(200, 30);
        add(button3);

        button3.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("ONLINE_BOARD");
                    outputStream.writeObject(pd);

                    ArrayList <Flight> flights = (ArrayList<Flight>) inputStream.readObject();
                    parent.getOnlineBoard().generateTable(flights);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
                parent.getClientMenu().setVisible(false);
                parent.getOnlineBoard().setVisible(true);
            }
        });

        JButton back = new JButton("BACK");
        back.setLocation(300, 630);
        back.setSize(200, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getClientMenu().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });
    }
}
