import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/**
 * TicketingSystem.java
 * @version 1.0
 * @since 2020-02-14
 * @author Ethan Kwan and Jasmine Chu
 * Class for the JPanel TicketingSystem.
 */

class TicketingSystem extends JPanel {

    ArrayList<Student> students;
    ArrayList<Table> tables;
    private JButton addButton, removeButton, editButton, addBackButton, addSubmitButton, removeBackButton,
            removeSubmitButton, editBackButton, editSubmitButton, sortButton;
    private JLabel nameText, idText, partnerNameText, partnerIdText;
    private JTextField nameField, idField, partnerNameField, partnerIdField;
    private SeatingAssignmentSystem seatingAssigner;

    private File studentFile = new File("students.txt");
    private Scanner fileReader = new Scanner(studentFile);
    private PrintWriter fileWriter = new PrintWriter(studentFile);



    TicketingSystem(ArrayList<Student> students, ArrayList<Table> tables) throws FileNotFoundException {
        this.setLayout(null);
        this.students = students;
        this.tables = tables;

        //MAIN TICKETING INTERFACE
        //Buttons
        addButton = new JButton("Add Student");
        addButton.setBounds(50, 100, 200, 50);
        removeButton = new JButton("Remove Student");
        removeButton.setBounds(350, 100, 200, 50);
        editButton = new JButton("Edit Partners");
        editButton.setBounds(650, 100, 200, 50);
        sortButton = new JButton("Update Tables");
        sortButton.setBounds(350, 200, 200, 50);

        //UNIVERSAL COMPONENTS
        //Text
        nameText = new JLabel("Name (Ex: John Smith)");
        nameText.setBounds(50, 50, 300, 30);
        idText = new JLabel("ID (Ex: 666420696)");
        idText.setBounds(50, 150, 300, 30);
        partnerNameText = new JLabel("Partner Names (Ex: John Smith, Smith John)");
        partnerNameText.setBounds(50, 250, 500, 30);
        partnerIdText = new JLabel("Partner IDs (Ex: 666420696, 696420666)");
        partnerIdText.setBounds(50, 350, 500, 30);
        //Text Fields
        nameField = new JTextField();
        nameField.setBounds(50, 100, 300, 30);
        idField = new JTextField();
        idField.setBounds(50, 200, 300, 30);
        partnerNameField = new JTextField();
        partnerNameField.setBounds(50, 300, 500, 30);
        partnerIdField = new JTextField();
        partnerIdField.setBounds(50, 400, 500, 30);

        //ADDING A STUDENT
        //Buttons
        addBackButton = new JButton("Back");
        addBackButton.setBounds(50, 500, 100, 50);
        addSubmitButton = new JButton("Submit");
        addSubmitButton.setBounds(200, 500, 100, 50);

        //REMOVING A STUDENT
        //Buttons
        removeBackButton = new JButton("Back");
        removeBackButton.setBounds(50, 500, 100, 50);
        removeSubmitButton = new JButton("Submit");
        removeSubmitButton.setBounds(200, 500, 100, 50);

        //EDITING A STUDENT'S PARTNERS
        //Buttons
        editBackButton = new JButton("Back");
        editBackButton.setBounds(50, 500, 100, 50);
        editSubmitButton = new JButton("Submit");
        editSubmitButton.setBounds(200, 500, 100, 50);

        //Add buttons to main interface
        this.add(addButton);
        this.add(removeButton);
        this.add(editButton);
        this.add(sortButton);

        //Action listeners
        addButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Opens the 'Add Student' layout from the 'Ticketing System' layout
             * @param e, ActionEvent from pressing addButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed add");
                removeMainButtons();
                add(addBackButton);
                add(addSubmitButton);
                add(nameText);
                add(idText);
                add(partnerNameText);
                add(partnerIdText);
                add(nameField);
                add(idField);
                add(partnerNameField);
                add(partnerIdField);
            }
        });

        removeButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Opens the 'Remove Student' layout from the 'Ticketing System' layout
             * @param e, ActionEvent from pressing removeButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed remove");
                removeMainButtons();
                add(removeBackButton);
                add(removeSubmitButton);
                add(nameText);
                add(idText);
                add(nameField);
                add(idField);
            }
        });

        editButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Opens the 'Edit Partners' layout from the 'Ticketing System' layout
             * @param e, ActionEvent from pressing editButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed edit");
                removeMainButtons();
                add(editBackButton);
                add(editSubmitButton);
                add(nameText);
                add(idText);
                add(partnerNameText);
                add(partnerIdText);
                add(nameField);
                add(idField);
                add(partnerNameField);
                add(partnerIdField);
            }
        });

        sortButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Sorts the students in ArrayList<Student> into tables
             * @param e, ActionEvent from pressing editButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed update tables");
                placeStudents();
            }
        });

        editBackButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Returns to the main layout from the 'Edit Partners' layout
             * @param e, ActionEvent from pressing editBackButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("went back to main menu");
                addMainButtons();
                remove(editBackButton);
                remove(editSubmitButton);
                remove(nameText);
                remove(idText);
                remove(partnerNameText);
                remove(partnerIdText);
                remove(nameField);
                remove(idField);
                remove(partnerNameField);
                remove(partnerIdField);
            }
        });

        editSubmitButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Gets the text from text fields to edit a student's partners
             * @param e, ActionEvent from pressing editSubmitButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("edited partners");
                ArrayList<Student> partners = separatePartners(partnerNameField.getText(), partnerIdField.getText());
                editPartners(nameField.getText(), idField.getText(), partners);
                nameField.setText(null); //reset textfields
                idField.setText(null);
                partnerNameField.setText(null);
                partnerIdField.setText(null);

            }
        });

        addBackButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Returns to the main layout from the 'Add Student' layout
             * @param e, ActionEvent from pressing addBackButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("went back to main menu");
                addMainButtons();
                remove(addBackButton);
                remove(addSubmitButton);
                remove(nameText);
                remove(idText);
                remove(partnerNameText);
                remove(partnerIdText);
                remove(nameField);
                remove(idField);
                remove(partnerNameField);
                remove(partnerIdField);
            }
        });

        addSubmitButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Gets the text from text fields to create a student
             * @param e, ActionEvent from pressing addSubmitButton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("submitted a student");
                String name = nameField.getText();
                String id = idField.getText();
                ArrayList<Student> partners = separatePartners(partnerNameField.getText(), partnerIdField.getText());
                if (name.isEmpty() || id.isEmpty()) { //Check if the name or ID field was left empty
                    NoticeFrame error = new NoticeFrame("Empty field!");
                } else {
                    addStudent(new Student(name, id, partners));
                    nameField.setText(null);
                    idField.setText(null);
                    partnerNameField.setText(null);
                    partnerIdField.setText(null);
                }

            }
        });

        removeBackButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Returns to the main layout from the 'Remove Student' layout
             * @param e, ActionEvent from pressing removeBackButton
             * @return void
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("went back to main menu");
                addMainButtons();
                remove(removeBackButton);
                remove(removeSubmitButton);
                remove(nameText);
                remove(idText);
                remove(nameField);
                remove(idField);
            }
        });

        removeSubmitButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed
             * Calls the removeStudent function, given the text from nameField and idField
             * @param e, ActionEvent from pressing removeSubmitButton
             * @return void
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("removed a student");
                removeStudent(nameField.getText(), idField.getText());
                nameField.setText(null); //reset textfields
                idField.setText(null);
            }
        });

    }

    /**
     * paintComponent
     * This panel's paintcomponent method
     * @param g, the 2D Graphics object used to draw with
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setDoubleBuffered(true);

        //Draw
        repaint();
    }

    /**
     * addStudent
     * Adds a student to the ArrayList<Student> students if student does not yet exist
     * @param student, Student object to be added to ArrayList<Student> students
     * @return void
     */
    private void addStudent(Student student) {
        if (!studentExists(student)) { //if student does not exist in the master list yet
            System.out.println("added");
            students.add(student);
            JFrame notice = new NoticeFrame("Student added.");
        } else { //otherwise, student already exists and cannot be added twice
            JFrame error = new NoticeFrame("Student already exists!");
        }
    }

    /**
     * studentExists
     * Checks if a student with same ID exists in ArrayList<Student> students
     * @param student, Student object to be compared with ArrayList<Student> students
     * @return boolean, true if student with same ID exists in ArrayList<Student> students, else false
     */
    private boolean studentExists(Student student) {
        for (int i = 0; i < students.size(); i++) { //goes through each student in the list matching ids
            if (student.getId().equals(students.get(i).getId())) {
                return true; //if student already exists in the list
            }
        }
        return false; //if student does not yet exist in the list
    }

    /**
     * removeStudent
     * Removes a student from ArrayList<Student> students, given a name and id
     * @param name, String to be compared with names of objects in ArrayList<Student> students
     * @param id, String to be compared with IDs of objects in ArrayList<Student> students
     * @return boolean, true if a corresponding student in ArrayList<Student> students exists and is removed, else false
     */
    private boolean removeStudent(String name, String id) {
        for (int i = 0; i < students.size(); i++) { //checks each student if their name and id match up
            if ((students.get(i).getName().compareTo(name) == 0)&&(students.get(i).getId().compareTo(id) == 0)) {
                students.remove(i);
                JFrame notice = new NoticeFrame("Student removed.");
                return true;
            }
        } //otherwise student does not exist in the list and cannot be removed
        JFrame error = new NoticeFrame("Student does not exist!");
        return false;
    }

    /**
     * editPartners
     * Edits the partners of a student in ArrayList<Student> students, given a name, id and the new partners
     * @param name, String to be compared with names of objects in ArrayList<Student> students
     * @param id, String to be compared with IDs of objects in ArrayList<Student> students
     * @param partners, ArrayList<Student> of the new partners
     * @return boolean, true if a corresponding student in ArrayList<Student> students exists and is edited, else false
     */
    private boolean editPartners(String name, String id, ArrayList<Student> partners) {
        for (int i = 0; i < students.size(); i++) { //goes through the list checking for the requested student
            if ((students.get(i).getName().compareTo(name) == 0)&&(students.get(i).getId().compareTo(id) == 0)) {
                students.get(i).setPartners(partners);
                JFrame notice = new NoticeFrame("Partners edited.");
                return true;
            }
        } //otherwise student does not exist and cannot have their partners edited
        JFrame error = new NoticeFrame("Student does not exist!");
        return false;
    }

    /**
     * separatePartners
     * Separates the names and IDs of the partners to create Student object for
     * @param names, String names of all partners as collected from JTextField, separated by ","
     * @param ids, String IDs of all partners as collected from JTextField, separated by ","
     * @return ArrayList<Student>, of partners
     */
    public ArrayList<Student> separatePartners(String names, String ids) {
        int end; //index of the first comma separating items in the string
        ArrayList<String> separatedNames = new ArrayList<>(); //lists to hold the separated names and ids
        ArrayList<String> separatedIds = new ArrayList<>();
        while (!(names.isEmpty())) { //split names first
            end = names.indexOf(',');
            if (end == -1) { //there is one item in the string
                separatedNames.add(names); //add it to the list
                names = "";
            } else {
                separatedNames.add(names.substring(0, end)); //add the item and the item only
                names = names.substring(end + 1).trim(); //take the rest of the items, rid of whitespace
            }
        }
        while (!(ids.isEmpty())) { //split ids after, same logic as names
            end = ids.indexOf(',');
            if (end == -1) {
                separatedIds.add(ids);
                ids = "";
            } else {
                separatedIds.add(ids.substring(0, end));
                ids = ids.substring(end + 1).trim();
            }
        }
        if (separatedIds.size() != separatedNames.size()) { //check if ids and names match in number
            NoticeFrame error = new NoticeFrame("IDs and Names do not match!");
            return null;
        }
        ArrayList<Student> partners = new ArrayList<>(separatedNames.size()); //if they do, make the partners
        for (int i = 0; i < separatedNames.size(); i++) {
            for (int n = 0; n < students.size(); n++) { //reference the partners to the actual student, if existent
                if ((students.get(n).getName() == separatedNames.get(i)) && (students.get(n).getId() == separatedIds.get(i))) {
                    partners.add(students.get(n));
                }
            }
        }
        return partners;
    }

    /**
     * removeMainButtons
     * Removes all the buttons from the main layout
     */
    private void removeMainButtons() {
        remove(addButton);
        remove(removeButton);
        remove(editButton);
        remove(sortButton);
    }

    /**
     * addMainButtons
     * Removes all the buttons from the main layout
     */
    private void addMainButtons() {
        add(addButton);
        add(removeButton);
        add(editButton);
        add(sortButton);
    }


    /**
     * NoticeFrame.java
     * @version 1.0
     * @since 2020-03-02
     * @author Ethan Kwan and Jasmine Chu
     * Inner class for a notice window that tells the user a task has been accomplished
     * or that an error has occured.
     */
    private class NoticeFrame extends JFrame {

        private JButton okButton = new JButton("OK");
        private JLabel messageLabel;

        //constructor for NoticeFrame class
        public NoticeFrame(String message) {
            super("Notice");
            this.messageLabel = new JLabel(message); //the message the error window will display

            okButton.setBounds(160,120,80,40);
            messageLabel.setBounds(50, 50, 300, 20);

            this.setResizable(false);

            //Set frame dimensions
            this.setSize(400 , 200);

            //Make the frame active and visible
            this.requestFocusInWindow();
            this.setVisible(true);

            this.setLayout(null);

            //adding components
            this.add(messageLabel);
            this.add(okButton);

            //okButton ActionListener
            okButton.addActionListener(new ActionListener() {

                @Override
                /**
                 * actionPerformed
                 * Method closes this notice window when the button is pressed
                 * @param e, ActionEvent from pressing okButton
                 */
                public void actionPerformed(ActionEvent e) {
                    System.out.println("closed notice window");
                    closeWindow();
                }
            });

        }

        /**
         * closeWindow
         * Method closes the NoticeFrame window when called from inside an actionlistener
         */
        private void closeWindow() {
            this.setVisible(false);
        }

    }

    /**
     * placeStudents
     * Method assigns tables to students when called from inside an actionlistener
     */
    private void placeStudents() {
        tables = seatingAssigner.assignTables(students, 100, 10);
    }

    /**
     * readStudents
     * Method reads students from a text file and saves them in the student array.
     */
    public void readStudents() {
        String name, id, partnerNames, partnerIds;
        while (fileReader.hasNext()) {
            name = fileReader.nextLine();
            id = fileReader.nextLine();
            partnerNames = fileReader.nextLine();
            partnerIds = fileReader.nextLine();
            students.add(new Student(name, id, separatePartners(partnerNames,  partnerIds)));
        }
        fileReader.close();
    }

    /**
     * writesStudent
     * Method saves array of students to a text file.
     */
    private void writeStudents() {
        ArrayList<Student> partners;
        for (int i = 0; i < students.size(); i++) {
            partners = students.get(i).getPartners();
            fileWriter.println(students.get(i).getName());
            fileWriter.println(students.get(i).getId());
            for (int j = 0; j < partners.size(); j++) {
                fileWriter.print(partners.get(j).getName());
                if (j < partners.size() - 1) { fileWriter.print(","); }
            }
            fileWriter.print("\n");
            for (int j = 0; j < partners.size(); j++) {
                fileWriter.print(partners.get(j).getId());
                if (j < partners.size() - 1) { fileWriter.print(","); }
            }
            fileWriter.print("\n");
        }
        fileWriter.close();
    }

}