package tracker;

import tracker.database.StudentsDataBase;

import static tracker.database.StudentsDataBase.getStudentsDataBase;

public class Receiver {
    private final StudentsDataBase studentsDataBase = getStudentsDataBase();

    public void sayBye() {
        System.out.println("Bye!");
    }

    public void sayUnknown() {
        System.out.println("Unknown command!");
    }

    public void sayNoInput() {
        System.out.println("No input.");
    }

    public void addStudent() {
        studentsDataBase.addStudent();
    }

    public void printStudentsList() {
        studentsDataBase.printStudentsList();
    }

    public void back() {
        System.out.println("Enter 'exit' to exit the program.");
    }

    public void addPoints() {
        studentsDataBase.addPoints();
    }

    public void find() {
        studentsDataBase.findStudent();
    }

    public void statistics() {
        studentsDataBase.getStatistics();
    }

    public void sendNotification() {
        studentsDataBase.sendNotification();
    }
}
