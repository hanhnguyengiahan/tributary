package tributary.core;

public class Message<T> {
    private MessageHeader header;
    private String key;
    private T value;

    public Message(MessageHeader header, String key, T value) {
        this.header = header;
        this.key = key;
        this.value = value;
    }
}
