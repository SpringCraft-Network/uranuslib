package me.will0mane.lib.bukkit.worker.factory;

import me.will0mane.lib.bukkit.worker.WorkerBukkit;
import me.will0mane.lib.bukkit.worker.tasks.WorkerTaskBukkit;
import me.will0mane.lib.uranus.worker.Worker;
import me.will0mane.lib.uranus.worker.factory.WorkerFactory;
import org.bukkit.plugin.Plugin;

public class WorkerFactoryBukkit extends WorkerFactory<WorkerTaskBukkit> {

    private final Plugin plugin;

    public WorkerFactoryBukkit(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Worker<WorkerTaskBukkit> craft() {
        return new WorkerBukkit(plugin);
    }
}
