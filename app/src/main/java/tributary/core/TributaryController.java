package tributary.core;

import java.util.HashMap;
import java.util.List;

import tributary.core.allocateStrategies.AllocateStrategy;
import tributary.core.rebalancingStrategies.RebalancingStrategy;

public class TributaryController {
    private HashMap<String, Topic> topics;
    private HashMap<String, Producer> producers;
    private HashMap<String, ConsumerGroup> consumerGroups;
    public TributaryController() {
        topics = new HashMap<>();
        producers = new HashMap<>();
        consumerGroups = new HashMap<>();
    }

    public void createIntegerTopic(String id) {
        topics.put(id, new Topic<Integer>(id));
    }
    public void createStringTopic(String id) {
        topics.put(id, new Topic<String>(id));
    }
    public void createPartition(String topicId, String partitionId) {
        Topic topic = topics.get(topicId);
        topic.addPartition(partitionId);
    }
    public void createConsumerGroup(String consumerGroupId, Topic topic, RebalancingStrategy rebalancingStrategy) {
        topic.addConsumerGroup(consumerGroupId, rebalancingStrategy);
    }
    public void createConsumer(ConsumerGroup consumerGroup, String consumerId) {
        consumerGroup.addConsumer(consumerId);
    }
    public void deleteConsumer(ConsumerGroup consumerGroup, String consumerId) {
        consumerGroup.deleteConsumer(consumerId);
    }
    public void createIntegerProducer(String id, AllocateStrategy allocateStrategy) {
        producers.put(id, new Producer<Integer>(id, allocateStrategy));
    }
    public void createStringProducer(String id, AllocateStrategy allocateStrategy) {
        producers.put(id, new Producer<String>(id, allocateStrategy));
    }
    public void produceMessage(Producer producer, Topic topic, Message message, Partition partition) {
        producer.produceMessage(topic, message, partition);
    }
    public void consumeMessage(Consumer consumer, Partition partition) {
        consumer.consumeMessage(partition);
    }
    public void consumeMessages(Consumer consumer, Partition partition, int numMessages) {
        consumer.consumeMessages(partition, numMessages);
    }
    public String showTopic(Topic topic) {
        return topic.show();
    }
    public String showConsumerGroup(ConsumerGroup consumerGroup) {
        return consumerGroup.show();
    }
    public void parallelProduce(Producer producer, Topic topic, List<Message> messages) {
        producer.parallelProduce(topic, messages);
    }
    public void parallelConsume(Consumer consumer, Partition partition) {
        consumer.parallelConsume(partition);
    }
    public void setConsumerGroupRebalancing(ConsumerGroup consumerGroup, RebalancingStrategy rebalancingStrategy) {
        consumerGroup.setRebalancingStrategy(rebalancingStrategy);
    }
}
