package lesson7;
/*
* 有三个线程，分别打印A,B,C
* 打印结果：循环打印ABC，打印十次
* */
public class SequencePrint {

    public static volatile int INDEX;


    public static void print(String[] s,int num){
        for(int i = 0;i<s.length;i++){
            final int k = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            for (int j = 0; j < num; j++) {
                                synchronized (SequencePrint.class) {
                                    while (INDEX % s.length != k) {
                                        SequencePrint.class.wait();
                                    }
                                    INDEX++;
                                    System.out.print(s[k]);
                                    SequencePrint.class.notifyAll();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }).start();
        }


    }


    public static void main(String[] args) {
        print(new String[]{"A","B","C"},10);
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        print(new String[]{"D","F","C"},10);

    }






   /* public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0;i<10;i++){
                        synchronized (SequencePrint.class){
                            while (INDEX%3!=0){
                                SequencePrint.class.wait();
                            }
                            INDEX++;
                            System.out.print("A");
                            SequencePrint.class.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0;i<10;i++){
                        synchronized (SequencePrint.class){
                            while (INDEX%3!=1){
                                SequencePrint.class.wait();
                            }
                            INDEX++;
                            System.out.print("B");
                            SequencePrint.class.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0;i<10;i++){
                        synchronized (SequencePrint.class){
                            while (INDEX%3!=2){
                                SequencePrint.class.wait();
                            }
                            INDEX++;
                            System.out.println("C");
                            SequencePrint.class.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/
}
