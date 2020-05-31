package lesson4;

public class SafeThread {
    private static final int NUM = 20;
    private static final int COUNT = 10000;
    private static int SUM;
    public static void main(String[] args) {
        for(int i = 0;i<NUM;i++){
            //局部变量 int 数据类型在-128~127之间的处于方法区的常量池里，超过的处于堆中
            //基本类型变量，变量处于栈中，值处于常量池中
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0;j<COUNT;j++){
                        synchronized (SafeThread.class){
                            SUM++;
                        }
                        /*synchronized (this){
                            SUM++;
                        }*/
                        //increament();
                        //increament2();
                        //SUM++;
                    }
                }
            });
            thread.start();

        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(SUM);
    }

    public static synchronized void increament() {//synchronized 锁，用作关键字可修饰
        // 1.静态方法，
        // 2.实例方法，
        // 3.代码块：synchronized(对象){}
        SUM++;
    }// //等同于synchronized (SafeThread.class){SUM++;}
    public  synchronized void increament2() {//synchronized 锁，用作关键字可修饰
       //等同于synchronized (this){SUM++;}
        SUM++;
    }
}


