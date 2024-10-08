package tracker.commands;

import tracker.Receiver;

public class AddCommand implements Command {
    private final Receiver receiver;

    public AddCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.addStudent();
    }
}
