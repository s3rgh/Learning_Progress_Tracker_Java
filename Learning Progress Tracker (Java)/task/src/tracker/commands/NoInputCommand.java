package tracker.commands;

import tracker.Receiver;

public class NoInputCommand implements Command {
    private final Receiver receiver;

    public NoInputCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.sayNoInput();
    }
}
