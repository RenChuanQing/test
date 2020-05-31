package lesson6;

public class MyBlockingQueue<E> {
    private Object[] items;

    private int takeIndex;//弹出元素的索引
    private int putIndex;//添加元素的索引
    private int size;//有效容量

    public MyBlockingQueue(int capacity){
        items = new Object[capacity];
    }
    public synchronized void put(E e) throws InterruptedException {
        while(size == items.length){//达到上限，需要等待
            wait();
        }
        items[putIndex] = e;//存放元素
        putIndex = (putIndex+1)%items.length;//存放元素的索引++，需考虑循环索引大小超过数组长度的情况
        size++;
        notifyAll();
    }
    public synchronized E pop() throws InterruptedException {
        while (size == 0){//达到下限，需要等待
            wait();
        }
        E e =  (E)items[takeIndex];
        takeIndex = (takeIndex+1)%items.length;
        size--;
        notifyAll();
        return e;

    }
    private static int SUM;
    private static class Producer implements Runnable{
        @Override
        public void run() {
            SUM+=3;
            System.out.println("生产，库存为："+SUM);
        }
    }
    private static class Cosumer implements Runnable{
        @Override
        public void run() {
            SUM--;
            System.out.println("消费，库存为："+SUM);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue(100);
        for(int i = 0;i<5;i++){
            final int k = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0;j<100;j++) {
                        try {
                            myBlockingQueue.put(k * 100 + j);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        while (true){
            int num = myBlockingQueue.pop();
            System.out.println(num);
        }

       /* MyBlockingQueue<Runnable> myBlockingQueue = new MyBlockingQueue(100);
        for(int i = 0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            myBlockingQueue.put(new Producer());
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for(int i = 0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            myBlockingQueue.pop(new Cosumer());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        //main线程取出生产/消费任务
        while (true){
            Runnable runnable  = myBlockingQueue.pop();
            runnable.run();

        }*/

    }
}
