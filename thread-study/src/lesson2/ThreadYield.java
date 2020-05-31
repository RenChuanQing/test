package lesson2;

public class ThreadYield {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
