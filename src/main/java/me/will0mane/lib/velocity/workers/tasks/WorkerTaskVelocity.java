package me.will0mane.lib.velocity.workers.tasks;

import com.velocitypowered.api.scheduler.ScheduledTask;
import me.will0mane.lib.uranus.worker.Worker;
import me.will0mane.lib.uranus.worker.task.WorkerTask;

public abstract class WorkerTaskVelocity implements WorkerTask {

    private final Worker<WorkerTaskVelocity> worker;
    private int usedTaskID;

    private ScheduledTask task;

    protected WorkerTaskVelocity(Worker<WorkerTaskVelocity> worker) {
        this.worker = worker;
        this.usedTaskID = -1;
    }

    protected WorkerTaskVelocity(Worker<WorkerTaskVelocity> worker, int taskID) {
        this.worker = worker;
        this.usedTaskID = taskID;
    }

    @Override
    public void setUsedID(int id) {
        this.usedTaskID = id;
    }

    @Override
    public int getUsedTaskID() {
        return usedTaskID;
    }

    public void setTask(ScheduledTask task) {
        this.task = task;
    }

    @Override
    public void cancelTask() {
        task.cancel();
    }

    public abstract void trigger();

    @Override
    public void run() {
        trigger();
    }

    @Override
    public Worker<WorkerTaskVelocity> getWorker() {
        return worker;
    }

    public static final class Impl extends WorkerTaskVelocity {

        public Impl(Worker<WorkerTaskVelocity> worker) {
            super(worker);
        }

        public Impl(Worker<WorkerTaskVelocity> worker, int taskID) {
            super(worker, taskID);
        }

        @Override
        public void trigger() {
        }
    }
}
