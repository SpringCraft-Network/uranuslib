package me.will0mane.lib.uranus.worker.factory;

import me.will0mane.lib.uranus.worker.Worker;
import me.will0mane.lib.uranus.worker.task.WorkerTask;

public abstract class WorkerFactory<T extends WorkerTask> {

    public abstract Worker<T> craft();

}
