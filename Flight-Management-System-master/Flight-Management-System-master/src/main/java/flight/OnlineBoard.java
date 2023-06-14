package flight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OnlineBoard extends JPanel {
    private MainFrame parent;
    private JTable table;
    private String[] header = {"FLIGHT_CODE", "SOURCE", "DESTINATION", "DEPARTURE", "ARRIVAL", "STATUS", "AIRLINE"};
    public OnlineBoard(MainFrame parent){
        this.parent = parent;

        setSize(800, 800);
        setLayout(null);

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setLocation(0, 0);
        scrollPane.setSize(800, 500);
        add(scrollPane);

        JButton back = new JButton("BACK");
        back.setLocation(350, 600);
        back.setSize(100, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getOnlineBoard().setVisible(false);
                parent.getClientMenu().setVisible(true);
            }
        });
    }

    public void generateTable(ArrayList<Flight> flights){
        Object data[][] = new Object[flights.size()][7];

        for(int i = 0; i < flights.size(); i++){
                data[i][0] = flights.get(i).getFlight_code( );
                data[i][1] = flights.get(i).getSource( );
                data[i][2] = flights.get(i).getDestination( );
                data[i][3] = flights.get(i).getDeparture( );
                data[i][4] = flights.get(i).getArrival( );
                data[i][5] = flights.get(i).getStatus( );
                data[i][6] = flights.get(i).getAirline( );
        }
        DefaultTableModel model = new DefaultTableModel(data,header);
        table.setModel(model);
    }
}
