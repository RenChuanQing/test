package lesson2;
//这种方式不能得出结论
public class Advantage {
    private static final long count = 10_0000_0000;
    private static final int num = 20;
    private static void incraetment(){
        for(int i= 0;i<count;i++){
            i++;
        }
    }
    private static void  serial(){//串行
        long begin = System.currentTimeMillis();
        for(int i = 0;i<num;i++){
            incraetment();
        }
        long end = System.currentTimeMillis();
        System.out.printf("串行：%s毫秒\n",end-begin);
    }
    private static void concurrence(){
        long begin = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                incraetment();
            }
        }).start();
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        long end = System.currentTimeMillis();
        System.out.printf("并行：%s毫秒\n",end-begin);
    }
    public static void main(String[] args) {
        concurrence();
        serial();
    }
}
