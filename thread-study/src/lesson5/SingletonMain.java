package lesson5;

public class SingletonMain {
    public static void main(String[] args)throws InterruptedException {
        //Singleton1 singleton1 = Singleton1.getInstance();
        for(int i = 0;i<5;i++){
            new Thread (new Runnable(){
                @Override
                public void run() {
                   Singleton1 singleton1 = Singleton1.getInstance();
                }
            }).start();
        }
    }
}
