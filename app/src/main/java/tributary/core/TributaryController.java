package tributary.core;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import tributary.core.allocateStrategies.AllocateStrategy;
import tributary.core.allocateStrategies.ManualStrategy;
import tributary.core.allocateStrategies.RandomStrategy;
import tributary.core.rebalancingStrategies.RangeStrategy;
import tributary.core.rebalancingStrategies.RebalancingStrategy;
import tributary.core.rebalancingStrategies.RoundRobinStrategy;

public class TributaryController {
    private HashMap<String, Topic> topics;
    private HashMap<String, Producer> producers;
    private HashMap<String, ConsumerGroup> consumerGroups;
    private HashMap<String, Consumer> consumers;
    private HashMap<String, Partition> partitions;
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
        Partition newPartition = new Partition(partitionId);
        partitions.put(partitionId, newPartition);
        Topic topic = topics.get(topicId);
        topic.addPartition(newPartition);
    }
    public RebalancingStrategy findRebalancingStrategy(String strategy) {
        if (strategy.equals("round robin")) {
            return new RoundRobinStrategy();
        } return new RangeStrategy();
    }
    public AllocateStrategy findAllocateStrategy(String strategy) {
        if (strategy.equals("manual")) {
            return new ManualStrategy();
        } return new RandomStrategy();
    }
    public void createConsumerGroup(String consumerGroupId, String topicId, String rebalancingStrategy) {
        Topic topic = topics.get(topicId);
        topic.addConsumerGroup(consumerGroupId, findRebalancingStrategy(rebalancingStrategy));
    }
    public void createConsumer(String consumerGroupId, String consumerId) {
        Consumer newConsumer = new Consumer(consumerId);
        ConsumerGroup consumerGroup = consumerGroups.get(consumerGroupId);
        consumers.put(consumerId, newConsumer);
        consumerGroup.addConsumer(newConsumer);
    }
    public void deleteConsumer(String consumerGroupId, String consumerId) {
        ConsumerGroup consumerGroup = consumerGroups.get(consumerGroupId);
        Consumer consumer = consumers.get(consumerId);
        consumerGroup.deleteConsumer(consumer);
    }
    public void createIntegerProducer(String id, String allocateStrategy) {
        producers.put(id, new Producer<Integer>(id, findAllocateStrategy(allocateStrategy)));
    }
    public void createStringProducer(String id, String allocateStrategy) {
        producers.put(id, new Producer<String>(id, findAllocateStrategy(allocateStrategy)));
    }
    public void produceMessage(String producerId, String topicId, String messageFile, String partitionId) {
        Producer producer = producers.get(producerId);
        Topic topic = topics.get(topicId);
        Partition partition = partitions.get(partitionId);
        Message newMessage = parseJSON(messageFile);
        producer.produceMessage(topic, newMessage, partition);
    }
    public void consumeMessage(String consumerId, String partitionId) {
        Consumer consumer = consumers.get(consumerId);
        Partition partition = partitions.get(partitionId);
        consumer.consumeMessage(partition);
    }
    public void consumeMessages(String consumerId, String partitionId, int numMessages) {
        Consumer consumer = consumers.get(consumerId);
        Partition partition = partitions.get(partitionId);
        consumer.consumeMessages(partition, numMessages);
    }
    public String showTopic(String topicId) {
        Topic topic = topics.get(topicId);
        return topic.show();
    }
    public String showConsumerGroup(String consumerGroupId) {
        ConsumerGroup consumerGroup = consumerGroups.get(consumerGroupId);
        return consumerGroup.show();
    }
    // public void parallelProduce(String producer, String topic, List<Message> messages) {

    //     producer.parallelProduce(topic, messages);
    // }
    // public void parallelConsume(String consumer, String partition) {
    //     consumer.parallelConsume(partition);
    // }
    public void setConsumerGroupRebalancing(String consumerGroupId, String rebalancingStrategy) {
        ConsumerGroup consumerGroup = consumerGroups.get(consumerGroupId);
        consumerGroup.setRebalancingStrategy(findRebalancingStrategy(rebalancingStrategy));
    }
}
