package tracker.commands;

import tracker.Receiver;

public class ListCommand implements Command {
    private final Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.printStudentsList();
    }
}
