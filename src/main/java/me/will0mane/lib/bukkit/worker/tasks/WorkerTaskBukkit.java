package me.will0mane.lib.bukkit.worker.tasks;

import me.will0mane.lib.uranus.worker.Worker;
import me.will0mane.lib.uranus.worker.task.WorkerTask;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class WorkerTaskBukkit extends BukkitRunnable implements WorkerTask {
   private final Worker<WorkerTaskBukkit> workerBukkit;
   private int usedTaskID;

   protected WorkerTaskBukkit(Worker<WorkerTaskBukkit> workerBukkit) {
      this.workerBukkit = workerBukkit;
      this.usedTaskID = -1;
   }

   protected WorkerTaskBukkit(Worker<WorkerTaskBukkit> workerBukkit, int taskID) {
      this.workerBukkit = workerBukkit;
      this.usedTaskID = taskID;
   }

   @Override
   public void cancelTask() {
      cancel();
   }

   @Override
   public void setUsedID(int taskID) {
      this.usedTaskID = taskID;
   }

   @Override
   public synchronized void cancel() {
      Bukkit.getScheduler().cancelTask(this.getTaskId());
      this.workerBukkit.getTaskMap().remove(this.usedTaskID);
   }

   public Worker<WorkerTaskBukkit> getWorker() {
      return this.workerBukkit;
   }

   @Override
   public int getUsedTaskID() {
      return this.usedTaskID;
   }
}
