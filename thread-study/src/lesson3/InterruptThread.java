package lesson3;

public class InterruptThread {

    //中断一个线程，但是线程没有处理中断
    public static void test1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        });
        thread.start();
        thread.interrupt();
    }

    public static void test2() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println(i + ":" + Thread.currentThread().isInterrupted());
                }
                /*while (!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName());
                }*/
            }
        });
        thread.start();//thread的中断标志位：false
        //thread.sleep(1);
        thread.interrupt();//thread的中断标志位：true
    }

    public static void test3() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.sleep(99);
                    //线程处于调用wait()/jion()/sleep()阻塞时，如果把当前线程给终端，会直接抛出一个异常
                    //抛出异常后，中断标志位会重置
                    System.out.println(Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().isInterrupted());

                }
            }
        });
        thread.start();//thread的中断标志位：false
        //thread.sleep(1);
        thread.interrupt();//thread的中断标志位：true
        // System.out.println(1);
    }

    public static void test4() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    //System.out.println(Thread.interrupted());
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();//thread的中断标志位：false
        //thread.sleep(1);
        thread.interrupt();//thread的中断标志位：true

    }

    public static void main(String[] args) throws InterruptedException {
        // test1();
        // test2();
        //test3();
       /* while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(2);*/
       test4();
    }
}
