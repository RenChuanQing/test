package lesson3;

public class UnsafeThread {
     private static final int NUM = 20;
     private static final int COUNT = 10000;
     private static int SUM;//int 数据类型在-128~127之间的处于方法区的常量池里，超过的处于堆中
    public static void main(String[] args) {
        //同时启动

        for(int i = 0;i<NUM;i++){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                   for(int j = 0;j<COUNT;j++){
                       SUM++;
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
}
