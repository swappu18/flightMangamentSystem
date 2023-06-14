package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel{
    private MainFrame parent;
    public MainMenu(MainFrame parent){
        this.parent = parent;

        setSize(800,800);
        setLayout(null);

        JButton admin = new JButton("ADMIN");
        admin.setLocation(300, 300);
        admin.setSize(200, 30);
        add(admin);

        admin.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getAdminLogin().setVisible(true);
            }
        });


        JButton client = new JButton("PASSENGER");
        client.setLocation(300, 400);
        client.setSize(200, 30);
        add(client);

        client.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e){
                parent.getMainMenu().setVisible(false);
                parent.getClientMenu().setVisible(true);
            }
        });

        JButton exit = new JButton("EXIT");
        exit.setLocation(300, 500);
        exit.setSize(200, 30);
        add(exit);

        exit.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
