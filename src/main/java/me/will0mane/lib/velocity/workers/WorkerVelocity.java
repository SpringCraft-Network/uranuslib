package me.will0mane.lib.velocity.workers;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.Scheduler;
import me.will0mane.lib.uranus.worker.Worker;
import me.will0mane.lib.uranus.worker.task.WorkerTask;
import me.will0mane.lib.velocity.workers.tasks.WorkerTaskVelocity;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class WorkerVelocity implements Worker<WorkerTaskVelocity> {

    private final Scheduler scheduler;

    public WorkerVelocity(Object plugin, ProxyServer server) {
        this.scheduler = server.getScheduler();
    }

    @Override
    public void wipe() {
        Worker.taskMap.values().forEach(WorkerTask::cancelTask);
        Worker.taskMap.clear();
    }

    private Scheduler.TaskBuilder create(Consumer<Worker<WorkerTaskVelocity>> consumer) {
        return create(() -> consumer.accept(this));
    }

    private Scheduler.TaskBuilder create(WorkerTask workerTask) {
        return create(workerTask::run);
    }

    private Scheduler.TaskBuilder create(Runnable runnable) {
        return scheduler.buildTask(getPlugin(), runnable);
    }

    @Override
    public WorkerTask later(Consumer<Worker<WorkerTaskVelocity>> consumer, long delay) {
        int taskID = this.getRandomID();
        WorkerTaskVelocity task = new WorkerTaskVelocity.Impl(this, taskID);
        task.setUsedID(taskID);
        this.addTask(task, taskID);
        task.setTask(create(consumer).delay(delay, TimeUnit.MILLISECONDS).schedule());
        return task;
    }

    @Override
    public WorkerTask later(WorkerTask task, long delay) {
        int taskID = this.getRandomID();
        task.setUsedID(taskID);
        this.addTask(task, taskID);
        create(task).delay(delay, TimeUnit.MILLISECONDS).schedule();
        return task;
    }

    @Override
    public WorkerTask laterAsync(Consumer<Worker<WorkerTaskVelocity>> consumer, long delay) {
        return later(consumer,delay);
    }

    @Override
    public WorkerTask laterAsync(WorkerTask worker, long delay) {
        return later(worker, delay);
    }

    @Override
    public WorkerTask timer(Consumer<Worker<WorkerTaskVelocity>> consumer, long after, long delay) {
        int taskID = this.getRandomID();
        WorkerTaskVelocity task = new WorkerTaskVelocity.Impl(this, taskID);
        task.setUsedID(taskID);
        this.addTask(task, taskID);
        task.setTask(create(consumer).repeat(delay, TimeUnit.MILLISECONDS).schedule());
        return task;
    }

    @Override
    public WorkerTask timer(WorkerTask task, long after, long delay) {
        int taskID = this.getRandomID();
        task.setUsedID(taskID);
        this.addTask(task, taskID);
        create(task).delay(delay, TimeUnit.MILLISECONDS).schedule();
        return task;
    }

    @Override
    public WorkerTask timerAsync(Consumer<Worker<WorkerTaskVelocity>> consumer, long after, long delay) {
        return timer(consumer, after, delay);
    }

    @Override
    public WorkerTask timerAsync(WorkerTask worker, long after, long delay) {
        return timer(worker, after, delay);
    }

    @Override
    public WorkerTask now(Consumer<Worker<WorkerTaskVelocity>> consumer) {
        int taskID = this.getRandomID();
        WorkerTaskVelocity task = new WorkerTaskVelocity.Impl(this, taskID);
        task.setUsedID(taskID);
        this.addTask(task, taskID);
        task.setTask(create(consumer).schedule());
        return task;
    }

    @Override
    public WorkerTask now(WorkerTask task) {
        int taskID = this.getRandomID();
        task.setUsedID(taskID);
        this.addTask(task, taskID);
        create(task).schedule();
        return task;
    }

    @Override
    public WorkerTask nowAsync(Consumer<Worker<WorkerTaskVelocity>> consumer) {
        return now(consumer);
    }

    @Override
    public WorkerTask nowAsync(WorkerTask worker) {
        return now(worker);
    }

    @Override
    public Object getPlugin() {
        return null;
    }
}
