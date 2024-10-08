package tracker.dto;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Student {
    private String id;
    private final String firstName;
    private final String secondName;
    private final String email;
    private int javaPoints;
    private int dsaPoints;
    private int dbPoints;
    private int springPoints;
    private boolean javaNotified;
    private boolean dsaNotified;
    private boolean dbNotified;
    private boolean springNotified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    public Student(String string) {
        var strings = string.trim().split(" ");
        var firstName = strings[0];
        var lastName = Arrays.stream(strings).skip(1)
                .limit(strings.length - 2)
                .collect(Collectors.joining(" "));
        var email = strings[strings.length - 1];
        this.firstName = firstName;
        this.secondName = lastName;
        this.email = email;
        this.javaPoints = 0;
        this.dsaPoints = 0;
        this.dbPoints = 0;
        this.springPoints = 0;
        this.javaNotified = false;
        this.dsaNotified = false;
        this.dbNotified = false;
        this.springNotified = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJavaPoints(int javaPoints) {
        this.javaPoints = javaPoints;
    }

    public void setDsaPoints(int dsaPoints) {
        this.dsaPoints = dsaPoints;
    }

    public void setDbPoints(int dbPoints) {
        this.dbPoints = dbPoints;
    }

    public void setSpringPoints(int springPoints) {
        this.springPoints = springPoints;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public int getDbPoints() {
        return dbPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public boolean isJavaNotified() {
        return javaNotified;
    }

    public void setJavaNotified(boolean javaNotified) {
        this.javaNotified = javaNotified;
    }

    public boolean isDsaNotified() {
        return dsaNotified;
    }

    public void setDsaNotified(boolean dsaNotified) {
        this.dsaNotified = dsaNotified;
    }

    public boolean isDbNotified() {
        return dbNotified;
    }

    public void setDbNotified(boolean dbNotified) {
        this.dbNotified = dbNotified;
    }

    public boolean isSpringNotified() {
        return springNotified;
    }

    public void setSpringNotified(boolean springNotified) {
        this.springNotified = springNotified;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void printPoints() {
        System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n",
                this.getId(), this.getJavaPoints(), this.getDsaPoints(), this.getDbPoints(), this.getSpringPoints());
    }
}
