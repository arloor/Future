package main.ListenableFuture;

public interface FutureListener<V>{
    // 执行清理、通知等工作
    void onDone(SomeFutureTask<V> someFutureTask);
}
