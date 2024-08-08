package tributary.core;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    private String id;
    private List<Partition> partitions;
    public Consumer(String id) {
        this.id = id;
    }
    public void consumeMessage(Partition partition) {
        // TODO
    }
    public void consumeMessages(Partition partition, int numMessages) {
        // TODO
    }
    public void parallelConsume(Partition partition) {
        // TODO
    }
    public void addPartition(Partition partition) {
        partitions.add(partition);
    }
    public void clearPartitions() {
        partitions = new ArrayList<>();
    }
}
