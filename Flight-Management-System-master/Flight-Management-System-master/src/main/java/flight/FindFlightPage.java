package flight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class FindFlightPage extends JPanel {
    private MainFrame parent;
    private String[] header = {"INDEX", "FLIGHT_CODE", "SOURCE", "DESTINATION", "DEPARTURE", "ARRIVAL", "DURATION", "AIRLINE", "LAYOVER_TIME", "NO_OF_STOPS"};
    private JTable table;
    private Date date;

    public FindFlightPage(MainFrame parent){
        setSize(800, 800);
        setLayout(null);

        this.parent = parent;

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(800, 500);
        scrollPane.setLocation(0, 0);
        add(scrollPane);

        JLabel choose = new JLabel("CHOOSE FLIGHT BY INDEX");
        choose.setLocation(200, 600);
        choose.setSize(200, 30);
        add(choose);

        JTextField number = new JTextField();
        number.setLocation(500, 600);
        number.setSize(100, 30);
        add(number);

        JButton button1 = new JButton("CHOOSE");
        button1.setLocation(500, 700);
        button1.setSize(100, 30);
        add(button1);

        button1.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choose = Integer.parseInt(number.getText());
                parent.getFindFlightPage(date).setVisible(false);
                parent.getFillInfoPage(choose, date).setVisible(true);
            }
        });

        JButton back = new JButton("BACK");
        back.setLocation(200, 700);
        back.setSize(100, 30);
        add(back);

        back.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getFindFlightPage(date).setVisible(false);
                parent.getPurchasePage().setVisible(true);
            }
        });
    }

    public void generateTable(ArrayList <Flight> flights, String from, String to){
        Object data[][] = new Object[flights.size()][10];

        for(int i = 0; i < flights.size(); i++){
            if(flights.get(i).getSource().equals(from) && flights.get(i).getDestination().equals(to)) {
                data[i][0] = i + 1;
                data[i][1]= flights.get(i).getFlight_code( );
                data[i][2] = flights.get(i).getSource( );
                data[i][3] = flights.get(i).getDestination( );
                data[i][4] = flights.get(i).getDeparture( );
                data[i][5] = flights.get(i).getArrival( );
                data[i][6] = flights.get(i).getDuration( );
                data[i][7] = flights.get(i).getAirline( );
                data[i][8] = flights.get(i).getLayover_Time( );
                data[i][9] = flights.get(i).getNo_of_stops( );
            }
        }
        DefaultTableModel model = new DefaultTableModel(data,header);
        table.setModel(model);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
