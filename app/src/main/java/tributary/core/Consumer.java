package tributary.core;

import java.util.HashMap;

public class Consumer {
    private String id;
    private HashMap<Partition, Integer> partitions;
    public Consumer(String id) {
        this.id = id;
    }
    public void consumeMessage(Partition partition) {
        int recentConsumedOffset = partitions.get(partition);
        // if recentConsumedOffset is not out of bound
        if (recentConsumedOffset < partition.getMessages().size()) {
            partitions.remove(partition);
            partitions.put(partition, recentConsumedOffset + 1);
        }
    }
    public void consumeMessages(Partition partition, int numMessages) {
        int recentConsumedOffset = partitions.get(partition);
        int i = 1;
        while (i < numMessages) {
            if (recentConsumedOffset + i < partition.getMessages().size()) {
                i++;
            }
        }
        if (i == 1) {
            consumeMessage(partition);
            return;
        }
        partitions.remove(partition);
        partitions.put(partition, recentConsumedOffset + i);
    }
    public void parallelConsume(Partition partition) {
        // TODO
    }
    public void addPartition(Partition partition) {
        partitions.put(partition, 0);
    }
    public void clearPartitions() {
        partitions = new HashMap<>();
    }
}
