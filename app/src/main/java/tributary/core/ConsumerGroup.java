package tributary.core;

import java.util.HashMap;
import java.util.List;
import tributary.core.rebalancingStrategies.RebalancingStrategy;

public class ConsumerGroup {
    private String id;
    private Topic subscribedTopic;
    private RebalancingStrategy rebalancingStrategy;
    private List<Consumer> consumers;

    public ConsumerGroup(String id, Topic subscribedTopic, RebalancingStrategy rebalancingStrategy) {
        this.id = id;
        this.subscribedTopic = subscribedTopic;
        this.rebalancingStrategy = rebalancingStrategy;
    }

    public void addConsumer(String consumerId) {
        consumers.add(new Consumer(consumerId));
        rebalancingStrategy.allocatePartition(subscribedTopic.getPartitions(), consumers);
    }

    public void deleteConsumer(String consumerId) {
        consumers.remove(consumerId);
        rebalancingStrategy.allocatePartition(subscribedTopic.getPartitions(), consumers);
    }

    public String show() {
        // TODO:
        return "hihi";
    }
    public void setRebalancingStrategy(RebalancingStrategy rebalancingStrategy) {
        this.rebalancingStrategy = rebalancingStrategy;
    }
}
