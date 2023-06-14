package flight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorPage extends JPanel {
    MainFrame parent;
    public ErrorPage(MainFrame parent){
        this.parent = parent;

        setSize(800, 800);
        setLayout(null);

        JLabel label = new JLabel("ENTERED INFORMATION IS NOT RIGHT");
        label.setLocation(300, 350);
        label.setSize(300, 30);
        add(label);

        JButton back = new JButton("BACK");
        back.setLocation(300, 420);
        back.setSize(200, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getErrorPage().setVisible(false);
                parent.getAdminLogin().setVisible(true);
            }
        });
    }

}
