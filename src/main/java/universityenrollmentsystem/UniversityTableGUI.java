/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class UniversityTableGUI extends JFrame {

    JTable myTable;
    UniversityTableModel tableModel;
    ArrayList<Person> list;

    // contructor
    public UniversityTableGUI(ArrayList<Person> list){

        //set the title
        this.setTitle("People in University Enrollment System");

        // initialise and instantiate the instance variable
        this.list = list;
        tableModel = new UniversityTableModel(list);
        myTable =  new JTable(tableModel);

        // set the size of the frame
        setBounds(20,20,800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed to DISPOSE to avoid closing main console

        // sorting
        myTable.setAutoCreateRowSorter(true);

        // add the table to the panel
        JScrollPane scrollPane = new JScrollPane(myTable);
        scrollPane.setPreferredSize(new Dimension(380,280));

        // button panel to hold both buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // existing Statistics button
        JButton button = new JButton("Statistics");
        buttonPanel.add(button);

        // ActionListener for the Statistics button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int totalStudents = 0;
                int totalLecturers = 0;
                int totalResearches = 0;
                int totalModules = 0;

                for (Person person : list) {
                    if (person instanceof Student) {
                        totalStudents++;
                        totalModules += ((Student) person).getModulesEnrolled();
                    } else if (person instanceof Lecturer) {
                        totalLecturers++;
                    } else if (person instanceof ResearchAssistant){
                        totalResearches ++;
                    }
                }

                String message = "=== System Statistics ===\n"
                        + "Total people:    " + list.size() + "\n"
                        + "Students:        " + totalStudents + "\n"
                        + "Lecturers:       " + totalLecturers + "\n"
                        + "Research Assistant:       "+ totalResearches + "\n"
                        + "Total modules enrolled (all students): " + totalModules;

                JOptionPane.showMessageDialog(
                        UniversityTableGUI.this,
                        message,
                        "Statistics",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // new Show CS Students button
        JButton csButton = new JButton("Show CS Students");
        buttonPanel.add(csButton);

        // ActionListener for the Show CS Students button
        csButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Build filtered list of Students whose courseTitle contains "computer"
                ArrayList<Person> csStudents = new ArrayList<>();
                for (Person person : list) {
                    if (person instanceof Student) {
                        Student s = (Student) person;
                        if (s.getCourseTitle() != null &&
                                s.getCourseTitle().toLowerCase().contains("computer")) {
                            csStudents.add(s);
                        }
                    }
                }

                // If no CS students found, show a dialog and leave the table unchanged
                if (csStudents.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            UniversityTableGUI.this,
                            "No Computer Science students found in the system.",
                            "Show CS Students",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                // Create a new model with the filtered list and update the table
                myTable.setModel(new UniversityTableModel(csStudents));
            }
        });

        // add the panel to the frame
        add(scrollPane,BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}