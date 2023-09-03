package me.will0mane.libs.uranusspigot.worker.tasks;

import java.util.function.Consumer;
import me.will0mane.libs.uranusspigot.worker.Worker;

public class ConsumerTask extends WorkerTask {
   private final Consumer<Worker<?>> consumer;

   public ConsumerTask(Worker<?> worker, Consumer<Worker<?>> consumer) {
      super(worker);
      this.consumer = consumer;
   }

   public ConsumerTask(Worker<?> worker, Consumer<Worker<?>> consumer, int taskID) {
      super(worker, taskID);
      this.consumer = consumer;
   }

   public void run() {
      if (this.consumer != null) {
         this.consumer.accept(super.getWorker());
      }
   }
}
