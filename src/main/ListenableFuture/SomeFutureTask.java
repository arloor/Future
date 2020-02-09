package main.ListenableFuture;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class SomeFutureTask<V> extends FutureTask implements ListenAble<V> {
    private final List<FutureListener<V>> listeners = new CopyOnWriteArrayList<>();

    //future.get()会返回callable的结果
    public SomeFutureTask(Callable callable) {
        super(callable);
    }

    //future.get()会返回第二个参数
    public SomeFutureTask(Runnable runnable, Object o) {
        super(runnable, o);
    }

    @Override
    public SomeFutureTask<V> addListener(FutureListener<V> listener) {
        listeners.add(listener);
        return this;
    }

    @Override
    public boolean removeListener(FutureListener listener) {
        return listeners.remove(listener);
    }

    @Override
    public void run() {
        super.run();
        Iterator<FutureListener<V>> iterator=listeners.iterator();
        while(iterator.hasNext()){
            FutureListener<V> listener=iterator.next();
            listener.onDone(this);
        }

    }

    /**
     * 阻塞get，不抛出任何异常
     * @return
     */
    public Object get0() {
        Object result = null;
        try {
            result = super.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
