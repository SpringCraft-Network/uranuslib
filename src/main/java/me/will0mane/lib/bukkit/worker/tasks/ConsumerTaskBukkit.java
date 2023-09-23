package me.will0mane.lib.bukkit.worker.tasks;

import me.will0mane.lib.uranus.worker.Worker;

import java.util.function.Consumer;

public class ConsumerTaskBukkit extends WorkerTaskBukkit {
   private final Consumer<Worker<WorkerTaskBukkit>> consumer;

   public ConsumerTaskBukkit(Worker<WorkerTaskBukkit> workerBukkit, Consumer<Worker<WorkerTaskBukkit>> consumer) {
      super(workerBukkit);
      this.consumer = consumer;
   }

   public ConsumerTaskBukkit(Worker<WorkerTaskBukkit> workerBukkit, Consumer<Worker<WorkerTaskBukkit>> consumer, int taskID) {
      super(workerBukkit, taskID);
      this.consumer = consumer;
   }

   public void run() {
      if (this.consumer != null) {
         this.consumer.accept(super.getWorker());
      }
   }
}
