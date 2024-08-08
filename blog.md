# Tributary - A Simplified Apache Kafka

## Preliminary Design

### Analysis of Engineering Requirements

The whole system is actually not very complicated if we proceed to model.

Based on the engineering requirements, we can come up with some of the core classes:

1. Producer
2. Consumer

3. ConsumerGroup

   - contains a number of consumers

4. Partition
5. Topic
   - contains a number of partitions
6. Event
   - This might be an interface? Since we want a flexible way to store different types of events, maybe make this an interface? Still not finalised yet tho
7. Message

Other than that, we think that it may be very useful have a that represents the rebalancing and allocation strategies. We may lean towards the decision of applying the **_Strategy Pattern_** for these strategies.

Further analysis in the domain modelling of the specs will be done in the UML diagram.

Other than that, maybe we'll go into somewhat in-depth analysis of what are these classes doing.

We may have a **TributaryController** which manages everything: consumers, producers, and partitions.

Each producer has its own allocation strategy, and may know how to:

- send event to a partition based on the key encapsulated in the message
- allocate event to a random partition

Each consumer group has its own rebalancing strategy and will reallocate partitions to their consumers after adding/deleting a consumer.

Consumers obviously should be able to consume events and replay events as well. This means we will probably need to store an internal offset/index (something kind of like a file pointer in File Systems) in order to keep track of where we left off, or replay by changing the offset. Consumers will also need to store these messages.

Topic contains a number of partitions and each partition may also have an internal index which points to keep track where they left off. Partitions need to save themselves a list of messages as well, so they can support playback.

A message is probably composed of a payload type, id, time created, key which indicates partition, and value which is an object referring to the information of a topic.

### Usability Tests List

List of scenarios:

1. Simple system with 1 producer, 1 topic with 1 partition, 1 consumer group with 1 consumer.
   - Test that a message lifecycle can be completed
   - Check that the id and contents of the message is correct
2. System with 2 producers, 2 topics with 1 partition each (1 of type Integer, 1 of type String), 2 consumer groups with 1 consumer each.
   - Test that messages of type Integer/String can be consumed correctly
3. System with 3 producers, 1 topics with 3 partitions, 1 consumer group with 3 consumers.
   - Test that parallel producing events can be handled by the topics correctly. We check this by calling a cli command "show topic"
4. System with 3 producers, 1 topics with 3 partitions, 1 consumer group with 3 consumers.
   - Test that consuming events parallel is handle by 3 consumers correctly.

### Initial UML Diagram

[Intial UML Diagram](InitialTributaryUML.pdf)
