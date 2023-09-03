package me.will0mane.lib.uranus.executor.hash;

import me.will0mane.lib.uranus.executor.Executor;

import java.util.function.Function;

@SuppressWarnings("unused")
public class HashExecutor<T,R> extends Executor<T,R> {

    private final Function<T, R> function;

    public HashExecutor(Function<T,R> function){
        this.function = function;
    }

    @Override
    protected R workerTask(T data) {
        return function.apply(data);
    }
}