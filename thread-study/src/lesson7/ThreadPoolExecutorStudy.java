package lesson7;

import java.util.concurrent.*;

public class ThreadPoolExecutorStudy {
    public static void main(String[] args) {

        //复杂方式创建线程池
        ExecutorService pool = new ThreadPoolExecutor(

                3,//核心线程数（正式员工）
                5,//最大线程数（正式员工+临时工）
                //临时工（非核心线程）：
                // 1.当核心线程忙不过来则雇佣（创建）
                // 2.当空闲时间超出设置的时间范围则解雇（回收）


                10,//时间数量
                TimeUnit.SECONDS,//时间单位

                //阻塞队列：存放任务的数据结构
                new ArrayBlockingQueue<>(1000),


                //线程池创建Thread线程的工厂类，没有提供的话就使用线程池内部默认的创建方式
                new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        },
                //拒绝策略
                new ThreadPoolExecutor.CallerRunsPolicy());
                // CallerRunsPolicy()：当任务队列已满，则execute代码所属线程自行执行该任务（例如主线程）
                //AbortPolicy()：当任务队列已满，直接抛出异常
                //DiscardOldestPolicy():从阻塞队列丢弃最旧的任务(队首)
                //DiscardPolicy()：从阻塞队列丢弃最新的任务（队尾）



        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("送快递到北京，A同学");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("送快递到新疆，B同学");
            }
        });
        System.out.println("我正在做事情");
    }
}
