package tracker.enums;

public enum Commands {
    ADD("add students"),
    LIST("list"),
    FIND("find"),
    POINTS("add points"),
    BACK("back"),
    STATS("statistics"),
    EXIT("exit"),
    NOTIFY("notify");

    private final String command;

    public String getCommand() {
        return command;
    }

    Commands(String command) {
        this.command = command;
    }
}
