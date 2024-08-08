package tributary.cli;

public class Produce {
    public static void produceEvent(String command) {
        String[] args = command.split(" ");
        String producerId = args[2];
        String topicId = args[3];
        String eventJSONFile = args[4]; // TODO Need to parse this, also parse the partition key from JSON as well.

    }
}
