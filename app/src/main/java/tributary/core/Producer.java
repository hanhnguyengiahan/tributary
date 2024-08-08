package tributary.core;

import java.util.List;

import tributary.core.allocateStrategies.AllocateStrategy;

public class Producer<T> {
    private AllocateStrategy allocateStrategy;
    private String id;
    public Producer(String id, AllocateStrategy allocateStrategy) {
        this.id = id;
        this.allocateStrategy = allocateStrategy;
    }
    public void produceMessage(Topic topic, Message message, Partition partition) {
        allocateStrategy.allocateMessagesToPartition(topic, message, partition);
    }
    public void parallelProduce(Topic topic, List<Message> messages) {
        // TODO
    }
}
