package lesson5;
//单例模式
//饿汉模式（空间换时间）
public class Singleton1 {
    private static Singleton1 SINGLETON1 = new Singleton1();
    private Singleton1(){ }
    public static Singleton1 getInstance(){
        return SINGLETON1;
    }
}
//懒汉模式（时间换空间）
class Singleton2{
    private static Singleton2 INSTANCE = null;
    private Singleton2(){}
    public static Singleton2 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
}
//懒汉式-线程安全版（性能低）
class Singleton3 {
    private static Singleton3 INSTANCE = null;
    private Singleton3(){}
    public static synchronized Singleton3 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}
//懒汉式-线程安全版（性能高）
class Singleton4 {
    private static volatile Singleton4 INSTANCE = null;//volatile保证可见性
    private Singleton4() {}
    public static Singleton4 getINSTANCE() {
        if (INSTANCE == null) {//双重校验锁，一重校验确保可以进行并发操作
            synchronized (Singleton4.class) {
                if (INSTANCE == null) {//二重校验，确保不会多次new，保证单例模式
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }
}
class Contry{
    public static void main(String[] args) {
        Singleton1 singleton1;
        Singleton3 singleton2;
        System.out.println();

    }
        }