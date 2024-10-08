package tracker.utils;

import tracker.dto.Student;
import tracker.dto.Submission;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static tracker.enums.Course.*;

public class StatisticsUtils {
    public static void printCoursesStats(HashMap<String, Student> students, List<Submission> submissions) {
        var javaEnrolled = students.entrySet().stream().filter(v -> v.getValue().getJavaPoints() > 0).count();
        var dsaEnrolled = students.entrySet().stream().filter(v -> v.getValue().getDsaPoints() > 0).count();
        var dbEnrolled = students.entrySet().stream().filter(v -> v.getValue().getDbPoints() > 0).count();
        var springEnrolled = students.entrySet().stream().filter(v -> v.getValue().getSpringPoints() > 0).count();

        var javaAvgPoints = submissions.stream()
                .filter(x -> x.javaPoint() > 0)
                .mapToInt(Submission::javaPoint)
                .average()
                .orElse(0.0);

        var dsaAvgPoints = submissions.stream()
                .filter(x -> x.dsaPoint() > 0)
                .mapToInt(Submission::dsaPoint)
                .average()
                .orElse(0.0);

        var dbAvgPoints = submissions.stream()
                .filter(x -> x.dbPoint() > 0)
                .mapToInt(Submission::dbPoint)
                .average()
                .orElse(0.0);

        var springAvgPoints = submissions.stream()
                .filter(x -> x.springPoint() > 0)
                .mapToInt(Submission::springPoint)
                .average()
                .orElse(0.0);

        var javaActivity = submissions.stream().filter(v -> v.javaPoint() > 0).count();
        var dsaActivity = submissions.stream().filter(v -> v.dsaPoint() > 0).count();
        var dbActivity = submissions.stream().filter(v -> v.dbPoint() > 0).count();
        var springActivity = submissions.stream().filter(v -> v.springPoint() > 0).count();

        var mostPopular = getMostPopular(javaEnrolled, dsaEnrolled, dbEnrolled, springEnrolled);
        var highest = getHighestActivity(javaActivity, dsaActivity, dbActivity, springActivity);
        var leastPopular = getLeastPopular(javaEnrolled, dsaEnrolled, dbEnrolled, springEnrolled);
        var lowestActivity = getLowestActivity(javaActivity, dsaActivity, dbActivity, springActivity);

        System.out.printf("Most popular: %s%n", String.join(", ", mostPopular));
        System.out.printf("Least popular: %s%n", mostPopular.size() == 4 ? "n/a" : String.join(", ", leastPopular));
        System.out.printf("Highest activity: %s%n", String.join(", ", highest));
        System.out.printf("Lowest activity: %s%n", highest.size() == 4 ? "n/a" : String.join(", ", lowestActivity));
        System.out.printf("Easiest course: %s%n", getEasiestCourse(javaAvgPoints, dsaAvgPoints, dbAvgPoints, springAvgPoints));
        System.out.printf("Hardest course: %s%n", getHardestCourse(javaAvgPoints, dsaAvgPoints, dbAvgPoints, springAvgPoints));
    }

    private static List<String> getMostPopular(long javaEnrolled, long dsaEnrolled, long dbEnrolled, long springEnrolled) {
        if (javaEnrolled == 0 && dsaEnrolled == 0 && dbEnrolled == 0 && springEnrolled == 0) {
            return List.of("n/a");
        }

        var enrolledMap = new HashMap<String, Long>();
        enrolledMap.put(JAVA.getCourseName(), javaEnrolled);
        enrolledMap.put(DSA.getCourseName(), dsaEnrolled);
        enrolledMap.put(DATABASES.getCourseName(), dbEnrolled);
        enrolledMap.put(SPRING.getCourseName(), springEnrolled);

        var max = enrolledMap.entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .get()
                .getValue();

        return enrolledMap.entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), max))
                .map(Map.Entry::getKey)
                .toList();
    }

    private static List<String> getLeastPopular(long javaEnrolled, long dsaEnrolled, long dbEnrolled, long springEnrolled) {
        if (javaEnrolled == 0 && dsaEnrolled == 0 && dbEnrolled == 0 && springEnrolled == 0) {
            return List.of("n/a");
        }

        var enrolledMap = new HashMap<String, Long>();
        enrolledMap.put(JAVA.getCourseName(), javaEnrolled);
        enrolledMap.put(DSA.getCourseName(), dsaEnrolled);
        enrolledMap.put(DATABASES.getCourseName(), dbEnrolled);
        enrolledMap.put(SPRING.getCourseName(), springEnrolled);

        var min = enrolledMap.entrySet()
                .stream()
                .min(Comparator.comparingLong(Map.Entry::getValue))
                .get()
                .getValue();

        return enrolledMap.entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), min))
                .map(Map.Entry::getKey)
                .toList();
    }

    private static List<String> getHighestActivity(long javaActivity, long dsaActivity, long dbActivity, long springActivity) {
        if (javaActivity == 0 && dsaActivity == 0 && dbActivity == 0 && springActivity == 0) {
            return List.of("n/a");
        }

        var activity = new HashMap<String, Long>();
        activity.put(JAVA.getCourseName(), javaActivity);
        activity.put(DSA.getCourseName(), dsaActivity);
        activity.put(DATABASES.getCourseName(), dbActivity);
        activity.put(SPRING.getCourseName(), springActivity);

        var max = activity.entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .get()
                .getValue();

        return activity.entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), max))
                .map(Map.Entry::getKey)
                .toList();
    }

    private static List<String> getLowestActivity(long javaActivity, long dsaActivity, long dbActivity, long springActivity) {
        if (javaActivity == 0 && dsaActivity == 0 && dbActivity == 0 && springActivity == 0) {
            return List.of("n/a");
        }

        var activity = new HashMap<String, Long>();
        activity.put(JAVA.getCourseName(), javaActivity);
        activity.put(DSA.getCourseName(), dsaActivity);
        activity.put(DATABASES.getCourseName(), dbActivity);
        activity.put(SPRING.getCourseName(), springActivity);

        var min = activity.entrySet()
                .stream()
                .min(Comparator.comparingLong(Map.Entry::getValue))
                .get()
                .getValue();

        return activity.entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), min))
                .map(Map.Entry::getKey)
                .toList();
    }

    private static String getHardestCourse(double javaPoints, double dsaPoints, double dbPoints, double springPoints) {
        if (javaPoints == 0 && dsaPoints == 0 && dbPoints == 0 && springPoints == 0) {
            return "n/a";
        }

        var pointsMap = new HashMap<String, Double>();
        pointsMap.put(JAVA.getCourseName(), javaPoints);
        pointsMap.put(DSA.getCourseName(), dsaPoints);
        pointsMap.put(DATABASES.getCourseName(), dbPoints);
        pointsMap.put(SPRING.getCourseName(), springPoints);
        return pointsMap.entrySet()
                .stream()
                .max((p1, p2) -> Double.compare(p2.getValue(), p1.getValue()))
                .get()
                .getKey();
    }

    private static String getEasiestCourse(double javaPoints, double dsaPoints, double dbPoints, double springPoints) {
        if (javaPoints == 0 && dsaPoints == 0 && dbPoints == 0 && springPoints == 0) {
            return "n/a";
        }
        var pointsMap = new HashMap<String, Double>();
        pointsMap.put(JAVA.getCourseName(), javaPoints);
        pointsMap.put(DSA.getCourseName(), dsaPoints);
        pointsMap.put(DATABASES.getCourseName(), dbPoints);
        pointsMap.put(SPRING.getCourseName(), springPoints);
        return pointsMap.entrySet()
                .stream()
                .min((p1, p2) -> Double.compare(p2.getValue(), p1.getValue()))
                .get()
                .getKey();
    }

    public static void showJavaStats(Map<String, Student> students) {
        System.out.println("Java");
        System.out.printf("%-10s %-10s %-10s%n", "id", "points", "completed");
        students.values()
                .stream()
                .filter(x -> x.getJavaPoints() > 0)
                .sorted(Comparator.comparingInt(Student::getJavaPoints).reversed())
                .forEach(s -> System.out.printf(Locale.US, "%-10s %-10d %.1f%%%n",
                        s.getId(),
                        s.getJavaPoints(),
                        new BigDecimal(s.getJavaPoints())
                                .multiply(new BigDecimal(100))
                                .divide(new BigDecimal(JAVA.getMaxPoint()), 2, RoundingMode.HALF_UP)));
    }

    public static void showDsaStats(Map<String, Student> students) {
        System.out.println("DSA");
        System.out.printf("%-10s %-10s %-10s%n", "id", "points", "completed");
        students.values()
                .stream()
                .filter(x -> x.getDsaPoints() > 0)
                .sorted(Comparator.comparing(Student::getDsaPoints).reversed())
                .forEach(s -> System.out.printf(Locale.US, "%-10s %-10d %.1f%%%n",
                        s.getId(),
                        s.getDsaPoints(),
                        new BigDecimal(s.getDsaPoints())
                                .multiply(new BigDecimal(100))
                                .divide(new BigDecimal(DSA.getMaxPoint()), 2, RoundingMode.HALF_UP)));
    }

    public static void showDbStats(Map<String, Student> students) {
        System.out.println("Databases");
        System.out.printf("%-10s %-10s %-10s%n", "id", "points", "completed");
        students.values()
                .stream()
                .filter(x -> x.getDbPoints() > 0)
                .sorted(Comparator.comparing(Student::getDbPoints).reversed())
                .forEach(s -> System.out.printf(Locale.US, "%-10s %-10d %.1f%%%n",
                        s.getId(),
                        s.getDbPoints(),
                        new BigDecimal(s.getDbPoints())
                                .multiply(new BigDecimal(100))
                                .divide(new BigDecimal(DATABASES.getMaxPoint()), 2, RoundingMode.HALF_UP)));
    }

    public static void showSpringStats(Map<String, Student> students) {
        System.out.println("Spring");
        System.out.printf("%-10s %-10s %-10s%n", "id", "points", "completed");
        students.values()
                .stream()
                .filter(x -> x.getSpringPoints() > 0)
                .sorted(Comparator.comparing(Student::getSpringPoints).reversed())
                .forEach(s -> System.out.printf(Locale.US, "%-10s %-10d %.1f%%%n",
                        s.getId(),
                        s.getSpringPoints(),
                        new BigDecimal(s.getSpringPoints())
                                .multiply(new BigDecimal(100))
                                .divide(new BigDecimal(SPRING.getMaxPoint()), 2, RoundingMode.HALF_UP)));
    }

    public static void unknown() {
        System.out.println("Unknown course.");
    }
}
