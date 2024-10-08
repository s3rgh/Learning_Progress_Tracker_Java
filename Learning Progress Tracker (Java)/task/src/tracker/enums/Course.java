package tracker.enums;

public enum Course {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    private final String courseName;
    private final int maxPoint;

    Course(String courseName, int maxPoint) {
        this.courseName = courseName;
        this.maxPoint = maxPoint;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxPoint() {
        return maxPoint;
    }
}
