package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JPanel {
    MainFrame parent;
    public AdminLogin(MainFrame parent){
        this.parent = parent;

        setSize(800, 800);
        setLayout(null);

        JLabel login = new JLabel("Login: ");
        login.setLocation(200, 200);
        login.setSize(100, 30);
        add(login);

        JTextField loginText = new JTextField();
        loginText.setLocation(400, 200);
        loginText.setSize(200, 30);
        add(loginText);

        JLabel password = new JLabel("Password: ");
        password.setLocation(200, 300);
        password.setSize(100, 30);
        add(password);

        JTextField passwordText = new JTextField();
        passwordText.setLocation(400, 300);
        passwordText.setSize(200, 30);
        add(passwordText);

        JButton back = new JButton("BACK");
        back.setLocation(200, 400);
        back.setSize(150, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getAdminLogin().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });

        JButton submit = new JButton("SUBMIT");
        submit.setLocation(450, 400);
        submit.setSize(150, 30);
        add(submit);

        submit.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginText.getText().equals("admin") && passwordText.getText().equals("12345")){
                    parent.getAdminLogin().setVisible(false);
                    parent.getAdminMenu().setVisible(true);
                }else{
                    parent.getAdminLogin().setVisible(false);
                    parent.getErrorPage().setVisible(true);
                }
                loginText.setText("");
                passwordText.setText("");
            }
        });
    }
}
