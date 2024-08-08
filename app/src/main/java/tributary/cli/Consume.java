package tributary.cli;

public class Consume {
    public static void consumeEvent(String command) {
        String[] args = command.split(" ");
        String consumerId = args[2];
        String partitionId = args[3];
    }
}
