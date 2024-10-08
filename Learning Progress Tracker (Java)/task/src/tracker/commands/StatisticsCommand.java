package tracker.commands;

import tracker.Receiver;

public class StatisticsCommand implements Command {
    private Receiver receiver;

    public StatisticsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.statistics();
    }
}
