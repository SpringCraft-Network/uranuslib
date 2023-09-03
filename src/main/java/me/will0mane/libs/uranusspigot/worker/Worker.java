package me.will0mane.libs.uranusspigot.worker;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import com.google.common.collect.Maps;
import me.will0mane.libs.uranusspigot.worker.tasks.ConsumerTask;
import me.will0mane.libs.uranusspigot.worker.tasks.WorkerTask;
import me.will0mane.libs.uranusspigot.worker.type.WorkerType;
import org.bukkit.plugin.Plugin;

public class Worker<T extends WorkerType> {
   private static final ThreadLocalRandom random = ThreadLocalRandom.current();
   private final Map<Integer, WorkerTask> taskMap = Maps.newHashMap();
   private final Plugin plugin;

   public Worker(Plugin plugin) {
      this.plugin = plugin;
   }

   public int toHash(T data) {
      return data.hashCode();
   }

   public void wipe() {
      this.taskMap.values().forEach(WorkerTask::cancel);
      this.taskMap.clear();
   }

   public WorkerTask later(Consumer<Worker<?>> consumer, long delay) {
      int taskID = this.getRandomID();
      WorkerTask task = new ConsumerTask(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskLater(this.getPlugin(), delay);
      return task;
   }

   public WorkerTask later(WorkerTask task, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskLater(this.getPlugin(), delay);
      return task;
   }

   public WorkerTask laterAsync(Consumer<Worker<?>> consumer, long delay) {
      int taskID = this.getRandomID();
      WorkerTask task = new ConsumerTask(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskLaterAsynchronously(this.plugin, delay);
      return task;
   }

   public WorkerTask laterAsync(WorkerTask task, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskLaterAsynchronously(this.getPlugin(), delay);
      return task;
   }

   public WorkerTask timer(Consumer<Worker<?>> consumer, long after, long delay) {
      int taskID = this.getRandomID();
      WorkerTask task = new ConsumerTask(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskTimer(this.plugin, after, delay);
      return task;
   }

   public WorkerTask timer(WorkerTask task, long after, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskTimer(this.getPlugin(), after, delay);
      return task;
   }

   public WorkerTask timerAsync(Consumer<Worker<?>> consumer, long after, long delay) {
      int taskID = this.getRandomID();
      WorkerTask task = new ConsumerTask(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskTimerAsynchronously(this.plugin, after, delay);
      return task;
   }

   public WorkerTask timerAsync(WorkerTask task, long after, long delay) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskTimerAsynchronously(this.getPlugin(), after, delay);
      return task;
   }

   public WorkerTask now(Consumer<Worker<?>> consumer) {
      int taskID = this.getRandomID();
      WorkerTask task = new ConsumerTask(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTask(this.plugin);
      return task;
   }

   public WorkerTask now(WorkerTask task) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTask(this.getPlugin());
      return task;
   }

   public WorkerTask nowAsync(Consumer<Worker<?>> consumer) {
      int taskID = this.getRandomID();
      WorkerTask task = new ConsumerTask(this, consumer, taskID);
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskAsynchronously(this.plugin);
      return task;
   }

   public WorkerTask nowAsync(WorkerTask task) {
      int taskID = this.getRandomID();
      task.setUsedID(taskID);
      this.addTask(task, taskID);
      task.runTaskAsynchronously(this.getPlugin());
      return task;
   }

   private int getRandomID() {
      return random.nextInt(0, 999999);
   }

   public void addTask(WorkerTask task) {
      this.addTask(task, this.getRandomID());
   }

   public void addTask(WorkerTask task, int taskID) {
      this.taskMap.put(taskID, task);
   }

   public void removeTask(WorkerTask task) {
      this.removeTask(task.getTaskId());
   }

   public void removeTask(int taskID) {
      this.getTask(taskID).ifPresent(WorkerTask::cancel);
      this.taskMap.remove(taskID);
   }

   private Optional<WorkerTask> getTask(int taskID) {
      return !this.taskMap.containsKey(taskID) ? Optional.empty() : Optional.of(this.taskMap.get(taskID));
   }

   public void cancelTask(int taskID) {
      WorkerTask workerTask = this.taskMap.get(taskID);

      if (workerTask != null) {
         workerTask.cancel();
         this.taskMap.remove(taskID);
      }
   }

   public Map<Integer, WorkerTask> getTaskMap() {
      return this.taskMap;
   }

   public Plugin getPlugin() {
      return this.plugin;
   }
}
