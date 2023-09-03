package me.will0mane.lib.uranusspigot.worker.tasks;

import me.will0mane.lib.uranusspigot.worker.Worker;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class WorkerTask extends BukkitRunnable {
   private final Worker<?> worker;
   private int usedTaskID;

   protected WorkerTask(Worker<?> worker) {
      this.worker = worker;
      this.usedTaskID = -1;
   }

   protected WorkerTask(Worker<?> worker, int taskID) {
      this.worker = worker;
      this.usedTaskID = taskID;
   }

   public void setUsedID(int taskID) {
      this.usedTaskID = taskID;
   }

   public synchronized void cancel() {
      Bukkit.getScheduler().cancelTask(this.getTaskId());
      this.worker.getTaskMap().remove(this.usedTaskID);
   }

   public Worker<?> getWorker() {
      return this.worker;
   }

   public int getUsedTaskID() {
      return this.usedTaskID;
   }
}
