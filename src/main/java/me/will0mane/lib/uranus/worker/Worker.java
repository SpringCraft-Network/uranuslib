package me.will0mane.lib.uranus.worker;

import com.google.common.collect.Maps;
import me.will0mane.lib.uranus.worker.task.WorkerTask;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public interface Worker<T extends WorkerTask> {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    Map<Integer, WorkerTask> taskMap = Maps.newHashMap();

    default ThreadLocalRandom getRandom() {
        return random;
    }

    void wipe();

    WorkerTask later(Consumer<Worker<T>> consumer, long delay);
    WorkerTask later(WorkerTask worker, long delay);
    WorkerTask laterAsync(Consumer<Worker<T>> consumer, long delay);
    WorkerTask laterAsync(WorkerTask worker, long delay);
    WorkerTask timer(Consumer<Worker<T>> consumer, long after, long delay);
    WorkerTask timer(WorkerTask worker, long after, long delay);
    WorkerTask timerAsync(Consumer<Worker<T>> consumer, long after, long delay);
    WorkerTask timerAsync(WorkerTask worker, long after, long delay);
    WorkerTask now(Consumer<Worker<T>> consumer);
    WorkerTask now(WorkerTask worker);
    WorkerTask nowAsync(Consumer<Worker<T>> consumer);
    WorkerTask nowAsync(WorkerTask worker);

    default int getRandomID() {
        return random.nextInt(0, 999999);
    }

    default void addTask(WorkerTask task) {
        addTask(task, getRandomID());
    }

    default void addTask(WorkerTask task, int taskID) {
        taskMap.put(taskID, task);
    }

    default void removeTask(WorkerTask workerTask) {
        removeTask(workerTask.getUsedTaskID());
    }

    default void removeTask(int taskID) {
        getTask(taskID).ifPresent(WorkerTask::cancelTask);
        taskMap.remove(taskID);
    }

    default void cancelTask(int taskID) {
        WorkerTask workerTask = taskMap.get(taskID);

        if (workerTask != null) {
            workerTask.cancelTask();
            taskMap.remove(taskID);
        }
    }

    default Optional<WorkerTask> getTask(int taskID) {
        return !taskMap.containsKey(taskID) ? Optional.empty() : Optional.of(taskMap.get(taskID));
    }

    default Map<Integer, WorkerTask> getTaskMap() {
        return taskMap;
    }

    Object getPlugin();
}
