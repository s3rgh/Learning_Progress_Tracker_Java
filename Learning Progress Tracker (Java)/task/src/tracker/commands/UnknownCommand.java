package tracker.commands;

import tracker.Receiver;

public class UnknownCommand implements Command {
    private final Receiver receiver;

    public UnknownCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.sayUnknown();
    }
}
