package tracker.commands;

import tracker.Receiver;

public class BackCommand implements Command {
    private final Receiver receiver;

    public BackCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.back();
    }
}
