package tracker;

import tracker.commands.*;

import static tracker.enums.Commands.*;
import static tracker.utils.ScannerUtils.getScannerInstance;

public class Client {
    public void run() {
        System.out.println("Learning Progress Tracker");
        var receiver = new Receiver();
        var invoker = new Invoker(
                new ExitCommand(receiver),
                new NoInputCommand(receiver),
                new UnknownCommand(receiver),
                new AddCommand(receiver),
                new BackCommand(receiver),
                new ListCommand(receiver),
                new AddPointsCommand(receiver),
                new FindCommand(receiver),
                new StatisticsCommand(receiver),
                new NotifyCommand(receiver)
        );

        var scanner = getScannerInstance();

        while (true) {
            var string = scanner.nextLine();
            if (string.equals(EXIT.getCommand())) {
                invoker.exit();
                break;
            } else if (string.equals(ADD.getCommand())) {
                invoker.add();
            } else if (string.equals(LIST.getCommand())) {
                invoker.list();
            } else if (string.equals(POINTS.getCommand())) {
                invoker.addPoints();
            } else if (string.equals(STATS.getCommand())) {
                invoker.statistics();
            } else if (string.equals(FIND.getCommand())) {
                invoker.find();
            } else if (string.equals(NOTIFY.getCommand())) {
                invoker.sendNotification();
            } else if (string.isBlank()) {
                invoker.empty();
            } else if (string.equals(BACK.getCommand())) {
                invoker.back();
            } else {
                invoker.unknown();
            }
        }
    }
}
