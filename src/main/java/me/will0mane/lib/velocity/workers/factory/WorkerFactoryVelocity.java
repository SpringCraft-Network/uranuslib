package me.will0mane.lib.velocity.workers.factory;

import com.velocitypowered.api.proxy.ProxyServer;
import me.will0mane.lib.uranus.worker.Worker;
import me.will0mane.lib.uranus.worker.factory.WorkerFactory;
import me.will0mane.lib.velocity.workers.WorkerVelocity;
import me.will0mane.lib.velocity.workers.tasks.WorkerTaskVelocity;

public class WorkerFactoryVelocity extends WorkerFactory<WorkerTaskVelocity> {

    private final ProxyServer server;

    public WorkerFactoryVelocity(ProxyServer server) {
        this.server = server;
    }

    @Override
    public Worker<WorkerTaskVelocity> craft() {
        return new WorkerVelocity(server);
    }
}
