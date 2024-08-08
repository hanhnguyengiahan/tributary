package tributary.core;
import java.time.LocalDate;
public class MessageHeader {
    private LocalDate timeCreated;
    private String id;
    private Class<?> payloadType;
    public MessageHeader(LocalDate timeCreated, String id, Class<?> payloadType) {
        this.timeCreated = timeCreated;
        this.id = id;
        this.payloadType = payloadType;
    }
}
