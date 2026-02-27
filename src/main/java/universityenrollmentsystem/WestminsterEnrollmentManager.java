/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;

import javax.lang.model.element.NestingKind;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class WestminsterEnrollmentManager implements EnrollmentManager{
    
    // ArrayList
    public ArrayList<Person> personList;
    public int person_limit;
    
    public WestminsterEnrollmentManager(int maxMemebersNumber){
        personList = new ArrayList<Person>();
        person_limit = maxMemebersNumber;
    }

    @Override
    public boolean runMenu() {
         boolean exit = false; 
        
        // Run console menu
        System.out.println("\n-- UNIVERSITY ENROLLMENT SYSTEM CONSOLE MENU--");
        
        System.out.println("To save and exit, press 0");
        System.out.println("To Add a new person, press 1");
        System.out.println("To Print the list of all people press 2");
        System.out.println("To Open GUI, press 3");
        System.out.println("To List people by role, press 4");
        System.out.println("To Print list sorted by role and name, press 5");

        
        // Switch based on selected option
        Scanner s = new Scanner(System.in);
        int choice;
        
        // Basic error handling for choice input
        try {
             choice = s.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number from the menu.");
            s.nextLine(); // Clear the buffer
            return false; // Rerun the menu
        }
       
        switch(choice){
            
             // Exit
            case 0:
                exit = true;
                break;
            
            // Add person
            case 1:
                this.addPerson();
                break;
            // Print person list
            case 2:
                this.printPersonList();
                break;
            
            // GUI
            case 3:
                this.runGUI();
                break;

            // List people by role
            case 4:
                this.listPeopleByRole();
                break;

            // Print sorted list
            case 5:
                this.printListSortedByRoleAndName();
                break;

            default:
                System.out.println("Invalid option selected.");
        }
        
        return exit;
    }

    @Override
    public void addPerson() {
        Scanner s = new Scanner (System.in);
        
        if(personList.size() < person_limit){
            System.out.println("Press 1 if you want to add a Student");
            System.out.println("Press 2 if you want to add a Lecturer");
            System.out.println("Press 3 if you want to add a Research Assistant");
            
            
            int choicePerson;
            try {
                choicePerson = s.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1, 2 (or 3 for Task 01).");
                s.nextLine();
                return;
            }
            s.nextLine(); // Consume newline
            
            // common questions
            System.out.println("Enter the first name");
            String name = s.nextLine();
            
            System.out.println("Enter the last name");
            String surname = s.nextLine();
            
            System.out.println("Enter the ID");
            String ID = s.nextLine();
            
            
            System.out.println("Enter the date of birth (dd/MM/yyyy format only!)");
            LocalDate date = null;
            boolean parsingSucceds = false;
            while(!parsingSucceds){
                String dob = s.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
                try{
                    date = LocalDate.parse(dob, formatter);
                    parsingSucceds = true;  
                }catch(DateTimeParseException e){
                    System.out.println("Enter the correct format. It should be dd/MM/yyyy!");
                    parsingSucceds = false;
                }
            }
            
            // check if the person is a student, lecturer, or administrator
            switch(choicePerson){
                case 1:
                    // it is a student
                    System.out.println("Enter the course title");
                    String courseTitle = s.nextLine();
                    
                    System.out.println("Enter the number of modules enrolled:");
                    int modulesEnrolled;
                    try {
                        modulesEnrolled = s.nextInt();
                    } catch (java.util.InputMismatchException e) {
                         System.out.println("Invalid input for modules. Setting to 0.");
                         modulesEnrolled = 0;
                         s.nextLine();
                    }
                    
                    // create a new Student and add to the list
                    Student student = new Student(name, surname);
                    student.setCourseTitle(courseTitle);
                    student.setModulesEnrolled(modulesEnrolled);
                    student.setDob(date);
                    student.setID(ID);
                    this.addPersonToList(student);
                    
                    break;
                    
                case 2:
                    //it is a lecturer
                    System.out.println("Enter the office number");
                    int officeNum;
                    try {
                        officeNum = s.nextInt();
                    } catch (java.util.InputMismatchException e) {
                         System.out.println("Invalid input for office number. Setting to 0.");
                         officeNum = 0;
                         s.nextLine();
                    }
                    s.nextLine(); // Consume newline
                    
                    System.out.println("Enter the specialisation/department");
                    String specialisation = s.nextLine();
                    
                    // create a new lecturer and add to the list
                    Lecturer lecturer = new Lecturer(name, surname);
                    lecturer.setOfficeNumber(officeNum);
                    lecturer.setSpecialisation(specialisation);
                    lecturer.setDob(date);
                    lecturer.setID(ID);
                    this.addPersonToList(lecturer);
                    
                    break;

                case 3:
                    //is is a Research assistant
                    System.out.println("Enter the project title :");
                    String projectTitle = s.nextLine();
                    System.out.println("Enter the number of hours per week ");
                    int hoursPerWeek;
                    try{
                        hoursPerWeek = s.nextInt();
                    }catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input for Hours Per Week. Setting to 0.");
                        hoursPerWeek = 0;
                        s.nextLine();
                    }
                    s.nextLine(); //Consume newline

                    System.out.println("Enter the supervisor name :");
                    String supervisorName = s.nextLine();

                    // create a new Research Assistant add to the list
                    ResearchAssistant researchAssistant = new ResearchAssistant(name,surname);
                    researchAssistant.setProjectTitle(projectTitle);
                    researchAssistant.setHoursPerWeek(hoursPerWeek);
                    researchAssistant.setSupervisorName(supervisorName);
                    researchAssistant.setDob(date);
                    researchAssistant.setID(ID);
                    this.addPersonToList(researchAssistant);

                    break;
                
                
                default:
                    System.out.println("Invalid person type selected. Person not added.");
            }
            
        }
        else {
            System.out.println("The system is at full capacity.");
        }
    }
    
    public void addPersonToList(Person person){
        
        //check if there are space available
        if(this.personList.size() < person_limit) {
            personList.add(person);
            System.out.println("Person added successfully.");
        }
        else{
            System.out.println("No more space in the list");
        }
    }

    @Override
    public void printPersonList() {
        
        
        if (!personList.isEmpty()){
            System.out.println("\n--- ENROLLED PEOPLE LIST ---");
            for(Person member : personList) {
                System.out.println(member.toString());
            }
            System.out.println("---------------------------\n");
        }
        else{
            System.out.println("There are no people in the system.");
        }
    }

    @Override
    public void runGUI() {
        UniversityTableGUI table = new UniversityTableGUI(personList);
        table.setVisible(true);
    }

    @Override
    public void listPeopleByRole() {
        System.out.println("\n-- List People By Role --");
        System.out.println("Press 1 to list Students ");
        System.out.println("Press 2 to list Lecturers");
        System.out.println("Press 3 to list Research Assistants");
        System.out.println("Press 0 to Cancel ");

        Scanner sc = new Scanner(System.in);
        int choice;

        try{
            choice = sc.nextInt();
        }catch (java.util.InputMismatchException e){
            System.out.println("Invalid input. Please enter a number from the menu.");
            sc.nextLine(); // clear buffer
            return;
        }

        switch (choice){
            case 0:
                System.out.println("Cancelled.");
                break;

            case 1:
                //List all students
                System.out.println("\n-- STUDENT LIST --");
                boolean foundStudent = false;
                for (Person person : personList) {
                    if (person instanceof Student) {
                        System.out.println(person.toString());
                        foundStudent = true;
                    }
                }
                if (!foundStudent) {
                    System.out.println("There are no students currently enrolled in the system.");
                }
                System.out.println("--------------------\n");
                break;

            case 2:
                // List all Lecturers
                System.out.println("\n--- LECTURER LIST ---");
                boolean foundLecturer = false;
                for (Person person : personList) {
                    if (person instanceof Lecturer) {
                        System.out.println(person.toString());
                        foundLecturer = true;
                    }
                }
                if (!foundLecturer) {
                    System.out.println("There are no lecturers currently enrolled in the system.");
                }
                System.out.println("---------------------\n");
                break;

            case 3 :
                // List all ResearchAssistants
                System.out.println("\n--- RESEARCH ASSISTANTS LIST ---");
                boolean foundResearchAssistant = false;
                for (Person person : personList) {
                    if (person instanceof ResearchAssistant) {
                        System.out.println(person.toString());
                        foundResearchAssistant = true;
                    }
                }
                if (!foundResearchAssistant) {
                    System.out.println("There are no research assistant currently enrolled in the system.");
                }
                System.out.println("---------------------\n");
                break;
            default:
                System.out.println("Invalid option selected. Please choose 0, 1,2, or 3.");
        }
    }
    public void printListSortedByRoleAndName() {

        if (personList.isEmpty()) {
            System.out.println("There are no people in the system to sort.");
            return;
        }

        // Step 1: create a copy so the original personList order is preserved
        ArrayList<Person> sortedCopy = new ArrayList<>(personList);

        // Step 2: build a Comparator that ranks by role, then surname, then name
        Comparator<Person> byRoleThenName = new Comparator<Person>() {

            // Maps a Person to its role rank: Student = 1, Lecturer = 2, Other = 3
            private int getRoleRank(Person p) {
                if (p instanceof Student)  return 1;
                if (p instanceof Lecturer) return 2;
                return 3; // any future subclass falls here
            }

            @Override
            public int compare(Person p1, Person p2) {
                // First compare by role rank
                int rankDiff = Integer.compare(getRoleRank(p1), getRoleRank(p2));
                if (rankDiff != 0) return rankDiff;

                // Within the same role, compare by surname (case-insensitive)
                int surnameDiff = p1.getSurname().compareToIgnoreCase(p2.getSurname());
                if (surnameDiff != 0) return surnameDiff;

                // If surname is also equal, compare by first name (case-insensitive)
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        };

        // Step 3: sort the copy using the Collections API
        Collections.sort(sortedCopy, byRoleThenName);

        // Step 4: print the sorted list
        System.out.println("\n--- PEOPLE LIST (sorted by role, then surname, then name) ---");
        for (Person person : sortedCopy) {
            System.out.println(person.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

}

