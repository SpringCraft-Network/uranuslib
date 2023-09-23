package me.will0mane.lib.bukkit.worker;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import com.google.common.collect.Maps;
import me.will0mane.lib.uranus.worker.Worker;
import me.will0mane.lib.uranus.worker.task.WorkerTask;
import me.will0mane.lib.bukkit.worker.tasks.ConsumerTaskBukkit;
import me.will0mane.lib.bukkit.worker.tasks.WorkerTaskBukkit;
import org.bukkit.plugin.Plugin;

public class WorkerBukkit implements Worker<WorkerTaskBukkit> {
   private final Map<Integer, WorkerTask> taskMap = Maps.newHashMap();
   private final Plugin plugin;

   public WorkerBukkit(Plugin plugin) {
      this.plugin = plugin;
   }

   public void wipe() {
      this.taskMap.values().forEach(WorkerTask::cancelTask);
      this.taskMap.clear();
   }

   @Override
   public WorkerTask later(Consumer<Worker<WorkerTaskBukkit>> consumer, long delay) {
      int taskID = this.getRandomID();
      WorkerTaskBukkit task = new ConsumerTaskBukkit(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskLater(this.getPlugin(), delay);
      return task;
   }

   @Override
   public WorkerTask later(WorkerTask task, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      ((WorkerTaskBukkit)task).runTaskLater(this.getPlugin(), delay);
      return task;
   }

   @Override
   public WorkerTask laterAsync(Consumer<Worker<WorkerTaskBukkit>> consumer, long delay) {
      int taskID = this.getRandomID();
      WorkerTaskBukkit task = new ConsumerTaskBukkit(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskLaterAsynchronously(this.plugin, delay);
      return task;
   }

   @Override
   public WorkerTask laterAsync(WorkerTask task, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      ((WorkerTaskBukkit)task).runTaskLaterAsynchronously(this.getPlugin(), delay);
      return task;
   }

   @Override
   public WorkerTask timer(Consumer<Worker<WorkerTaskBukkit>> consumer, long after, long delay) {
      int taskID = this.getRandomID();
      WorkerTaskBukkit task = new ConsumerTaskBukkit(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskTimer(this.plugin, after, delay);
      return task;
   }

   @Override
   public WorkerTask timer(WorkerTask task, long after, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      ((WorkerTaskBukkit)task).runTaskTimer(this.getPlugin(), after, delay);
      return task;
   }

   @Override
   public WorkerTask timerAsync(Consumer<Worker<WorkerTaskBukkit>> consumer, long after, long delay) {
      int taskID = this.getRandomID();
      WorkerTaskBukkit task = new ConsumerTaskBukkit(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskTimerAsynchronously(this.plugin, after, delay);
      return task;
   }

   @Override
   public WorkerTask timerAsync(WorkerTask task, long after, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      ((WorkerTaskBukkit)task).runTaskTimerAsynchronously(this.getPlugin(), after, delay);
      return task;
   }

   @Override
   public WorkerTask now(Consumer<Worker<WorkerTaskBukkit>> consumer) {
      int taskID = this.getRandomID();
      WorkerTaskBukkit task = new ConsumerTaskBukkit(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTask(this.plugin);
      return task;
   }

   @Override
   public WorkerTask now(WorkerTask task) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      ((WorkerTaskBukkit)task).runTask(this.getPlugin());
      return task;
   }

   @Override
   public WorkerTask nowAsync(Consumer<Worker<WorkerTaskBukkit>> consumer) {
      int taskID = this.getRandomID();
      WorkerTaskBukkit task = new ConsumerTaskBukkit(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskAsynchronously(this.plugin);
      return task;
   }

   @Override
   public WorkerTask nowAsync(WorkerTask task) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      ((WorkerTaskBukkit)task).runTaskAsynchronously(this.getPlugin());
      return task;
   }

   @Override
   public Plugin getPlugin() {
      return this.plugin;
   }
}
