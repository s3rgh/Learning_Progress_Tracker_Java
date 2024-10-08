package tracker;

import tracker.commands.Command;

public class Invoker {
    private final Command exit;
    private final Command emptyCommand;
    private final Command unknownCommand;
    private final Command addStudentCommand;
    private final Command backCommand;
    private final Command listCommand;
    private final Command addPointsCommand;
    private final Command findCommand;
    private final Command statisticsCommand;
    private final Command notifyCommand;

    public Invoker(Command exit,
                   Command emptyCommand,
                   Command unknownCommand,
                   Command addStudentCommand,
                   Command backCommand,
                   Command listCommand,
                   Command addPointsCommand,
                   Command findCommand,
                   Command statisticsCommand,
                   Command notifyCommand) {
        this.exit = exit;
        this.emptyCommand = emptyCommand;
        this.unknownCommand = unknownCommand;
        this.addStudentCommand = addStudentCommand;
        this.backCommand = backCommand;
        this.listCommand = listCommand;
        this.addPointsCommand = addPointsCommand;
        this.findCommand = findCommand;
        this.statisticsCommand = statisticsCommand;
        this.notifyCommand = notifyCommand;
    }

    public void exit() {
        exit.execute();
    }

    public void empty() {
        emptyCommand.execute();
    }

    public void unknown() {
        unknownCommand.execute();
    }

    public void add() {
        addStudentCommand.execute();
    }

    public void back() {
        backCommand.execute();
    }

    public void list() {
        listCommand.execute();
    }

    public void addPoints() {
        addPointsCommand.execute();
    }

    public void find() {
        findCommand.execute();
    }

    public void statistics() {
        statisticsCommand.execute();
    }

    public void sendNotification() {
        notifyCommand.execute();
    }
}
