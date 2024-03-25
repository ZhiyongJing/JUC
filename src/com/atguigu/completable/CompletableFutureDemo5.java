package com.atguigu.completable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CompletableFutureDemo5 {
    private static Integer num = 10;

    /**
     * 先对一个数加 10,然后取平方
     * thenAccept 消费处理结果, 接收任务的处理结果，并消费处理，无返回结果。
     * @param args
     */
    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始");
        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("加 10 任务开始");
                num += 10;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return num;
        }).thenApply(integer -> {
            return num * num;
        }).thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("子线程全部处理完成,最后调用了 accept,结果为:" +
                        integer);
            }
        });
        System.out.println("主线程结束");
    }
}