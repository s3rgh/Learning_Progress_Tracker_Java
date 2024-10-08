package tracker.commands;

import tracker.Receiver;

public class FindCommand implements Command {
    private final Receiver receiver;

    public FindCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.find();
    }
}
