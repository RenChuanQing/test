package lesson5;

public class BreadOperator {
    //库存面包数量：上限100，下限0
    public static volatile int SUM;

    public static void main(String[] args) {
        //启动5个生产者生产面包
        for(int i = 0;i<5;i++){
            new Thread(new Producer(),"面包师傅"+i).start();
        }
        //启动20个消费者消费面包
        for(int i = 0;i<10;i++){
            new Thread(new Cosumer(),"消费者"+i).start();
        }
    }
    //默认生产者：面包师，一次生产三个面包,每个面包师傅生产20次
    private static class Producer implements Runnable{

        @Override
        public void run() {
            try {
                for(int i = 0;i<20;i++){
                    synchronized (BreadOperator.class){
                        while(SUM+3 > 100){//生产结束库存不能超出
                            //释放对象锁，要让其他线程进入同步代码块
                            BreadOperator.class.wait();
                        }
                        SUM += 3;//生产面包
                        Thread.sleep(10);
                        //notify()/notifyAll()都是通知调用wait()阻塞线程
                        //notify()随机唤醒一个wait()阻塞线程，notifyAll()唤醒所有wait()阻塞线程
                        //在synchronizeed结束之后，wait()和synchronizeed代码阻塞的现场恒，都会被唤醒
                        BreadOperator.class.notifyAll();
                        System.out.println(Thread.currentThread().getName()+",生产了，库存为:"+SUM);
                    }
                    //Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //默认消费者：一次消费一个面包
    private static class Cosumer implements Runnable{

        @Override
        public void run() {
            try {
                while (true){
                    synchronized (BreadOperator.class){
                        while(SUM == 0){//库存为0，不能够继续消费
                            BreadOperator.class.wait();
                        }
                        SUM--;
                        BreadOperator.class.notifyAll();
                        System.out.println(Thread.currentThread().getName()+",消费了，库存为:"+SUM);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
