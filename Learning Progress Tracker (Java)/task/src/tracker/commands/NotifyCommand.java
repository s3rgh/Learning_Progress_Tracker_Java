package tracker.commands;

import tracker.Receiver;

public class NotifyCommand implements Command {
    private final Receiver receiver;

    public NotifyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.sendNotification();
    }
}
