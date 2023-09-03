package me.will0mane.lib.uranus.executor;

import lombok.SneakyThrows;
import me.will0mane.lib.uranus.executor.exception.ExecutorDownException;

import java.util.UUID;

@SuppressWarnings("unused")
public abstract class Executor<T,R> {

    private boolean executorDown = false;
    private final UUID executorID = UUID.randomUUID();

    public void executorDown(){
        executorDown = true;
    }

    public void start(){
        executorDown = false;
    }

    @SneakyThrows
    public R process(T data){
        if(executorDown) {
            throw new ExecutorDownException(executorID);
        }
        return workerTask(data);
    }

    protected abstract R workerTask(T data);


}

