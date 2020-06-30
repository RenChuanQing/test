package lesson7;

import lesson6.MyBlockingQueue;

public class MyThreadPoll {

    private int size;
    private MyBlockingQueue<Runnable> queue;

    public MyThreadPoll(int size, int capcity) {
        this.size = size;
        queue = new MyBlockingQueue<>(capcity);
        //创建正式员工
        for(int i = 0;i<size;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //正式员工一直运行
                        while (true){
                            //从仓库取出包裹
                            // 1.成功取出包裹（任务），方法返回
                            // 2.仓库里取不出包裹（其他员工正在取取，阻塞在synchronized代码行，仓库没有包裹wait方法阻塞)

                            Runnable task = queue.pop();

                            task.run();//正式员工执行任务
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

    public void execute(Runnable task){
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MyThreadPoll pool = new MyThreadPoll(5,100);
        for(int i = 0;i<10;i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.print("A");
                }
            });
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.print("B");
                }
            });
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.print("C");
                }
            });
        }

    }
}