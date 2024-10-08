package tracker.commands;

import tracker.Receiver;

public class ExitCommand implements Command {
    private final Receiver receiver;

    public ExitCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.sayBye();
    }
}
