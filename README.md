**Tributary: An Event Streaming Library**

This project involves the design and implementation of a simplified event streaming library, inspired by Apache Kafka. 

**1. Getting Started**

* **Prerequisites:** Java Development Kit (JDK) installed and configured.
* **Project Setup:** 
    * Clone this repository.
    * Build the project using Maven or Gradle (instructions in the project's build file).

**2. Overview**

* **Introduction:** Event-Driven Architecture is crucial for modern, distributed systems. This project aims to provide hands-on experience in designing and implementing a foundational component of such systems: an event streaming library.
* **Key Concepts:**
    * **Topics:** Logical groupings of related events.
    * **Partitions:** Subdivisions of a topic, allowing for parallel consumption.
    * **Producers:** Entities that publish events to topics.
    * **Consumers:** Entities that subscribe to topics and process events.
    * **Consumer Groups:** Groups of consumers that collectively consume all partitions of a topic.
    * **Message Lifecycle:** The journey of an event from production to consumption, including:
        * Production: Event creation and publication.
        * Partitioning: Assignment of events to partitions.
        * Consumption: Processing of events by consumers.
        * Rebalancing: Redistribution of partitions among consumers within a group.

**3. Engineering Requirements**

* **Message Structure:** Define a suitable structure for events, including headers (timestamp, ID, payload type, key), and a generic payload field.
* **Producer Functionality:**
    * Support for random and manual partition allocation.
    * Efficient and thread-safe event publishing.
* **Consumer Functionality:**
    * Consume events from assigned partitions sequentially.
    * Track consumed offsets to ensure no message is missed.
    * Support for different consumption strategies (e.g., polling, push).
* **Consumer Groups:**
    * Implement consumer group management, including:
        * Creation, deletion, and modification of consumer groups.
        * Dynamic membership changes (add/remove consumers).
    * Implement rebalancing strategies:
        * Range rebalancing: Even distribution of partitions.
        * Round Robin rebalancing: Cyclic assignment of partitions.
* **Message Replay:** Allow consumers to replay events from a specific offset within a partition.
* **Concurrency and Thread Safety:** Ensure proper synchronization and data consistency in a multi-threaded environment.

**4. Interface**

* **Java API:** Design and document a well-defined Java API for interacting with the library:
    * Classes for topics, partitions, producers, consumers, and consumer groups.
    * Methods for creating, managing, and interacting with these entities.
* **Philosophy:**
    * Provide a flexible and extensible foundation for building various event-driven systems.
    * Encourage clean separation of concerns between producers, consumers, and the underlying infrastructure.
* **Command-Line Interface (CLI):** Develop a CLI for interacting with the system, allowing users to:
    * Create and manage topics and partitions.
    * Create and manage producers and consumers.
    * Publish and consume events.
    * Control consumer groups and rebalancing.

**6. Credits**

* This project is inspired by Apache Kafka, but does not require prior knowledge of Kafka.

