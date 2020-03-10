import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Prom.java
 * @version 1.0
 * @since 2020-02-14
 * @author Ethan Kwan and Jasmine Chu
 * Class for the JFrame Prom.
 */

class Prom extends JFrame {

    private final int MAX_X = (int)getToolkit().getScreenSize().getWidth();
    private final int MAX_Y = (int)getToolkit().getScreenSize().getHeight();
    private JTabbedPane mainPanel;
    private TicketingSystem ticketingPanel;
    private FloorPlanSystem floorPlanPanel;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();

    //Prom class constructor
    Prom() throws FileNotFoundException {
        super("Prom Frame");

        //create the panels
        mainPanel = new JTabbedPane();
        ticketingPanel = new TicketingSystem(students, tables);
        floorPlanPanel = new FloorPlanSystem(tables);

        //create different tabs
        mainPanel.addTab("Ticketing", ticketingPanel);
        mainPanel.addTab("Floor Plan", floorPlanPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //set frame dimensions
        this.setSize(900, 700);

        //make the frame active and visible
        this.requestFocusInWindow();
        this.setVisible(true);

        //add the panel to the frame
        this.add(mainPanel);


    }

}