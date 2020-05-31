package lesson2;

public class DomanThread {//守护线程

    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(9999999999999l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //Thread.setDaemon(true);
        thread.start();
    }
}
