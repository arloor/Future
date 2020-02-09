package main;

import main.ListenableFuture.FutureListener;
import main.ListenableFuture.SomeFutureTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        FutureListener listener = (someFutureTask -> {
            System.out.println("任务完成");
            System.out.println("结果是："+someFutureTask.get0());
        });
        SomeFutureTask<String> someFutureTask =
                new SomeFutureTask<String>(() -> "done").addListener(listener); // 增加listener
        pool.execute(someFutureTask);
        pool.execute(someFutureTask);
        pool.execute(someFutureTask);
        // 关闭线程池
//        pool.shutdownNow();
    }
}
