package tributary.core.rebalancingStrategies;

import java.util.List;

import tributary.core.Consumer;
import tributary.core.Partition;

public class RangeStrategy implements RebalancingStrategy {
    private void allocateUnevenly(List<Partition> partitions, List<Consumer> consumers) {
        int numPartitions = partitions.size() / consumers.size();
        int curConsumer = 0;
        while (curConsumer < consumers.size()) {
            if (curConsumer == 0) {
                for (int i = 0; i < numPartitions + 1; i++) {
                    consumers.get(curConsumer).addPartition(partitions.get(i));
                }
            } else {
                int start = curConsumer * numPartitions + 1;
                for (int i = start; i < start + numPartitions; i++) {
                    consumers.get(curConsumer).addPartition(partitions.get(i));
                }
            }
            curConsumer++;

        }
    }

    private void allocateEvenly(List<Partition> partitions, List<Consumer> consumers) {
        int numPartitions = partitions.size() / consumers.size();
        int curConsumer = 0;
        while (curConsumer < consumers.size()) {
            for (int i = 0; i < numPartitions; i++) {
                consumers.get(curConsumer).addPartition(partitions.get(i));
            }
            curConsumer++;
        }
    }

    @Override
    public void allocatePartition(List<Partition> partitions, List<Consumer> consumers) {
        for (Consumer consumer : consumers) {
            consumer.clearPartitions();
        }
        if (partitions.size() % consumers.size() != 0) {
            allocateUnevenly(partitions, consumers);
        } else {
            allocateEvenly(partitions, consumers);
        }

    }

}
