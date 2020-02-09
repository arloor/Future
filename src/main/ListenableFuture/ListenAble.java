package main.ListenableFuture;

public interface ListenAble<V> {

    SomeFutureTask<V> addListener(FutureListener<V> listener);

    boolean removeListener(FutureListener<V> listener);
}
