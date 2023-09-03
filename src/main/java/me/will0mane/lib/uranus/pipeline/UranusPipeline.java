package me.will0mane.lib.uranus.pipeline;

@SuppressWarnings({"unchecked", "unused"})
public abstract class UranusPipeline<T> {

    public T[] bus;
    public int limit;

    protected UranusPipeline(int limit) {
        bus = (T[]) new Object[ limit ];
        this.limit = limit;
    }

    public void write(int index, T data) {
        checkSize(index);
        this.bus[ index ] = data;
    }

    public T read(int index) {
        checkSize(index);
        return this.bus[ index ];
    }

    public void checkSize(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= limit) throw new ArrayIndexOutOfBoundsException();
    }
}
