package tracker.utils;

import tracker.dto.Student;

import java.util.Map;

import static tracker.enums.Course.*;

public class NotificationUtils {
    private static final String TEMPLATE = """
            To: %s
            Re: Your Learning Progress
            Hello, %s! You have accomplished our %s course!""";

    public static void sendNotificationsToStudents(Map<String, Student> students) {
        var counter = 0;
        for (Student student : students.values()) {
            var check = false;
            var email = student.getEmail();
            var fullName = String.join(" ", student.getFirstName(), student.getSecondName());

            if (student.getJavaPoints() >= JAVA.getMaxPoint() && !student.isJavaNotified()) {
                check = true;
                student.setJavaNotified(true);
                System.out.printf((TEMPLATE) + "%n", email, fullName, JAVA.getCourseName());
            }

            if (student.getDsaPoints() >= DSA.getMaxPoint() && !student.isDsaNotified()) {
                check = true;
                student.setDsaNotified(true);
                System.out.printf((TEMPLATE) + "%n", email, fullName, DSA.getCourseName());
            }

            if (student.getDbPoints() >= DATABASES.getMaxPoint() && !student.isDbNotified()) {
                check = true;
                student.setDbNotified(true);
                System.out.printf((TEMPLATE) + "%n", email, fullName, DATABASES.getCourseName());
            }

            if (student.getSpringPoints() >= SPRING.getMaxPoint() && !student.isSpringNotified()) {
                check = true;
                student.setSpringNotified(true);
                System.out.printf((TEMPLATE) + "%n", email, fullName, SPRING.getCourseName());
            }
            if (check) {
                counter++;
            }
        }
        System.out.printf("Total %d students have been notified.%n", counter);
    }
}
