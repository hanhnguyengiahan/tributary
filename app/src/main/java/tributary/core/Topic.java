package tributary.core;

import java.util.List;

import tributary.core.rebalancingStrategies.RebalancingStrategy;

public class Topic<T> {
    private String id;
    private List<Partition> partitions;
    private List<ConsumerGroup> consumerGroups;

    public Topic(String id) {
        this.id = id;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public void addPartition(String partitionId) {
        partitions.add(new Partition(partitionId));
    }

    public void addConsumerGroup(String consumerGroupId, RebalancingStrategy rebalancingStrategy) {
        consumerGroups.add(new ConsumerGroup(consumerGroupId, this, rebalancingStrategy));
    }

    public String show() {
        // TODO
        return "hi";
    }
}
