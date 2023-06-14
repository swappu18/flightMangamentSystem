package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PLoginPage extends JPanel {
    private MainFrame parent;

    public PLoginPage(MainFrame parent){
        setSize(800, 800);
        setLayout(null);

        this.parent = parent;

        JLabel surname = new JLabel("SURNAME: ");
        surname.setLocation(200, 200);
        surname.setSize(100, 30);
        add(surname);

        JTextField surnameT = new JTextField();
        surnameT.setLocation(400, 200);
        surnameT.setSize(200, 30);
        add(surnameT);

        JLabel passport = new JLabel("PASSPORT NO: ");
        passport.setLocation(200, 300);
        passport.setSize(100, 30);
        add(passport);

        JTextField passportT = new JTextField();
        passportT.setLocation(400, 300);
        passportT.setSize(200, 30);
        add(passportT);

        JButton back = new JButton("BACK");
        back.setLocation(200, 400);
        back.setSize(150, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getpLoginPage().setVisible(false);
                parent.getClientMenu().setVisible(true);
            }
        });

        JButton submit = new JButton("SUBMIT");
        submit.setLocation(450, 400);
        submit.setSize(150, 30);
        add(submit);

        submit.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Socket socket = new Socket("localhost", 1234);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                    PackageData pd = new PackageData("LIST_OF_PASSENGERS");
                    outputStream.writeObject(pd);
                    ArrayList <Passenger> passengers = (ArrayList<Passenger>)inputStream.readObject();
                    boolean flag = false;
                    Passenger passenger = new Passenger();
                    for(Passenger p: passengers){
                        if(p.getLname().equals(surnameT.getText()) && p.getPassport().equals(passportT.getText())){
                            passenger = p;
                            flag = true;
                        }
                    }
                    if(flag){
                        parent.getpLoginPage().setVisible(false);
                        parent.getBookingManagement(passenger).setVisible(true);
                    }else{
                        parent.getpLoginPage().setVisible(false);
                        parent.getClientMenu().setVisible(true);
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }
}
