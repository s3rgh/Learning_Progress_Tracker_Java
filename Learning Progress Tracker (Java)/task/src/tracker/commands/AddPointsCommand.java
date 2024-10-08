package tracker.commands;

import tracker.Receiver;

public class AddPointsCommand implements Command {
    private final Receiver receiver;

    public AddPointsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.addPoints();
    }
}
