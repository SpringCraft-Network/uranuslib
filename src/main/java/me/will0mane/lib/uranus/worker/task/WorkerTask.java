package me.will0mane.lib.uranus.worker.task;

import me.will0mane.lib.uranus.worker.Worker;

public interface WorkerTask {

    void setUsedID(int id);
    int getUsedTaskID();

    void cancelTask();
    void run();

    Worker getWorker();

}
