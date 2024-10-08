package tracker.database;

import tracker.dto.Student;
import tracker.dto.Submission;
import tracker.utils.NotificationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static tracker.enums.Commands.*;
import static tracker.enums.Course.*;
import static tracker.utils.CredentialValidatorUtils.validateCredentials;
import static tracker.utils.NotificationUtils.*;
import static tracker.utils.ScannerUtils.getScannerInstance;
import static tracker.utils.StatisticsUtils.*;

public class StudentsDataBase {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(9999);
    private static StudentsDataBase STUDENTS_DATABASE;
    private static int LAST_ADDED = 0;
    private final HashMap<String, Student> STUDENTS;
    private final List<Submission> submissions = new ArrayList<>();

    private StudentsDataBase() {
        STUDENTS = new HashMap<>();
    }

    public static StudentsDataBase getStudentsDataBase() {
        if (STUDENTS_DATABASE == null) {
            STUDENTS_DATABASE = new StudentsDataBase();
        }
        return STUDENTS_DATABASE;
    }

    public void addStudent() {
        System.out.println("Enter student credentials or 'back' to return:");
        var string = getScannerInstance().nextLine();
        while (true) {
            if (string.equals(BACK.getCommand())) {
                System.out.printf("Total %d students have been added.\n", LAST_ADDED);
                LAST_ADDED = 0;
                break;
            }
            if (validateCredentials(string)) {
                var student = new Student(string);
                student.setId(String.valueOf(ID_GENERATOR.incrementAndGet()));
                if (STUDENTS.containsKey(student.getEmail())) {
                    System.out.println("This email is already taken.");
                    string = getScannerInstance().nextLine();
                    continue;
                } else {
                    STUDENTS.put(student.getEmail(), student);
                }
                LAST_ADDED++;
                System.out.println("The student has been added.");
            } else {
                string = getScannerInstance().nextLine();
                continue;
            }
            string = getScannerInstance().nextLine();
        }
    }

    public void printStudentsList() {
        if (STUDENTS.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Students:");
        for (Map.Entry<String, Student> s : STUDENTS.entrySet()) {
            System.out.printf("%s\n", s.getValue().getId());
        }
    }

    public void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            var string = getScannerInstance().nextLine();

            if (string.equals(BACK.getCommand())) {
                break;
            }

            if (!string.matches("\\w+\\s\\d+\\s\\d+\\s\\d+\\s\\d+$")) {
                System.out.println("Incorrect points format.");
                continue;
            }

            var points = string.split("\\s+");

            var id = points[0];
            var javaPoints = Integer.parseInt(points[1]);
            var dsaPoints = Integer.parseInt(points[2]);
            var dbPoints = Integer.parseInt(points[3]);
            var springPoints = Integer.parseInt(points[4]);

            if (javaPoints < 0 || dsaPoints < 0 || dbPoints < 0 || springPoints < 0) {
                System.out.println("Incorrect points format.");
                continue;
            }

            if (STUDENTS.entrySet().stream().noneMatch(e -> e.getValue().getId().equals(id))) {
                System.out.printf("No student is found for id=%s.\n", id);
                continue;
            }

            var entry = STUDENTS.entrySet().stream().
                    filter(e -> e.getValue().getId().equals(id))
                    .findFirst()
                    .orElseThrow();

            var st = entry.getValue();
            st.setJavaPoints(st.getJavaPoints() + javaPoints);
            st.setDsaPoints(st.getDsaPoints() + dsaPoints);
            st.setDbPoints(st.getDbPoints() + dbPoints);
            st.setSpringPoints(st.getSpringPoints() + springPoints);
            submissions.add(new Submission(Integer.parseInt(id), javaPoints, dsaPoints, dbPoints, springPoints));
            System.out.println("Points updated.");
        }
    }

    public void findStudent() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            var string = getScannerInstance().nextLine();

            if (string.equals(BACK.getCommand())) {
                break;
            }

            if (STUDENTS.entrySet().stream().noneMatch(e -> e.getValue().getId().equals(string))) {
                System.out.printf("No student is found for id=%s.\n", string);
            } else {
                STUDENTS.entrySet()
                        .stream()
                        .filter(e -> e.getValue().getId().equals(string))
                        .findFirst()
                        .orElseThrow().getValue().printPoints();
            }
        }
    }

    public void getStatistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        printCoursesStats(STUDENTS, submissions);
        while (true) {
            var string = getScannerInstance().nextLine();
            if (string.equals(BACK.getCommand())) {
                break;
            } else if (string.equalsIgnoreCase(JAVA.getCourseName())) {
                showJavaStats(STUDENTS);
            } else if (string.equalsIgnoreCase(DSA.getCourseName())) {
                showDsaStats(STUDENTS);
            } else if (string.equalsIgnoreCase(DATABASES.getCourseName())) {
                showDbStats(STUDENTS);
            } else if (string.equalsIgnoreCase(SPRING.getCourseName())) {
                showSpringStats(STUDENTS);
            } else {
                unknown();
            }
        }
    }

    public void sendNotification() {
        sendNotificationsToStudents(STUDENTS);
    }
}
