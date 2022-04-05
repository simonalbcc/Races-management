package View;


import javax.swing.*;
import java.awt.*;
import java.net.NoRouteToHostException;

public class ShowAccidents extends JPanel {
    private JLabel subtitle, dayLabel, monthLabel, yearLabel;
    private JSpinner day, month, year;
    private JPanel spinnerPanel;
    public ShowAccidents() {
        this.setLayout(new BorderLayout());
        spinnerPanel = new JPanel();
        spinnerPanel.setLayout(new GridLayout(3,2,2,2));
        spinnerPanel.setPreferredSize(new Dimension(50,50));

        subtitle = new JLabel("<html> <h3> Select the starting date of the accident : </h3> </html>");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        day = new JSpinner();
        month = new JSpinner();
        year = new JSpinner();
        day.setBounds(50, 80, 70, 100);
        month.setBounds(50, 80, 70, 100);
        year.setBounds(50, 80, 70, 100);

        dayLabel = new JLabel("Select the day");
        monthLabel = new JLabel("Select the month");
        yearLabel = new JLabel("Select the year");

        spinnerPanel.add(dayLabel);
        spinnerPanel.add(day);

        this.add(subtitle, BorderLayout.NORTH);
        this.add(spinnerPanel, BorderLayout.CENTER);


    }
}