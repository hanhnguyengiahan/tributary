package tributary.core;

import java.util.List;

public class Partition {
    private String id;
    private List<Message> messages;

    public Partition(String id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
