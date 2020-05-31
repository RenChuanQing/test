package lesson7;

import java.util.concurrent.*;


/*
* 定时器：
* 1.在约定好的时间点上，执行某个任务
* 2.间隔时间到了，不定的执行任务
* */
public class MyTimer {
    private BlockingQueue<MyTimerTask> queue = new PriorityBlockingQueue();

    public MyTimer(int count){
        for(int i = 0;i<count;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            MyTimerTask timerTask = queue.take();//blockingQueue本身就是线程安全的
                            synchronized (queue){
                                long current = System.currentTimeMillis();
                                if(timerTask.next > current){
                                    queue.wait(timerTask.next-current);
                                    queue.put(timerTask);
                                }else {
                                    timerTask.task.run();
                                    if(timerTask.period>0){
                                        timerTask.next = timerTask.next+timerTask.period;
                                        queue.put(timerTask);
                                    }
                                }
                            }

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /*
    * 定时任务
    * */
   /* public void schedule(Runnable task,long delay,long period){
        try {
            Thread.sleep(delay);
            new Thread(task).start();
            while (period>0){
                Thread.sleep(period);
                new Thread(task).start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        *//*long current = System.currentTimeMillis();
        while(true){
            long next = System.currentTimeMillis();
            long cut = next-current;
            if(cut==delay ){
                new Thread(task).start();
            }else if((cut-next)%period == 0){
                new Thread(task).start();
            }
        }*//*

    }*/
    public void schedule(Runnable task,long delay,long period){
        try {
            queue.put(new MyTimerTask(task,System.currentTimeMillis()+delay,period));
            synchronized (queue){
                queue.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyTimerTask implements Runnable,Comparable<MyTimerTask> {
        //定时任务
        private Runnable task;
        private  long next;
        private long period;

        public MyTimerTask(Runnable task, long next, long period) {
            this.task = task;
            this.next = next;
            this.period = period;
        }

        @Override
        public void run() {

        }

        @Override
        public int compareTo(MyTimerTask o) {
            return Long.compare(next,o.next);
        }
    }


    public static void main(String[] args) {

        /*new MyTimer().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("起床了");
            }
        },3000,1000);*/

        /*Date date = new Date();//无参构造方法：返回系统当前时间
        Date date1 = new Date(999999999);//以格林威治时间1970-01-01开始，经过给定数量的毫秒
        //DateFormat
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(date));
        System.out.println(df.format(date1));
        long current = System.currentTimeMillis();
        System.out.println(current);*/
        /*TimerTask timerTask = new TimerTask(){//定时器

            @Override
            public void run() {
                System.out.println("起床了");
            }
        };
        new Timer().schedule(timerTask,3000,1000);*/
       // ExecutorService pool = Executors.newFixedThreadPool(3);//核心线程为3个，没有非核心线程
        //ExecutorService pool = Executors.newCachedThreadPool();//核心线程为0，非核心线程不限
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);//核心线程
        //ExecutorService pool = Executors.newSingleThreadExecutor();//默认只有一个线程
        /*pool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("起床了");
            }
        },1,TimeUnit.SECONDS);
*/
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("起床了");
            }
        },1, 1,TimeUnit.SECONDS);
    }


}
