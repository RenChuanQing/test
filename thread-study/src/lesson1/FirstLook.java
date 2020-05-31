package lesson1;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

public class FirstLook {
    static int count = 1;
    public static void main(String[] args)throws InterruptedException {

        for(int i = 0 ;i < 20 ;i++){
            System.out.println(2);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0;j<10000;j++){
                        count++;
                        System.out.println(1);
                    }
                }
            }).start();
        }
        System.out.println(Thread.activeCount());
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(count);
    }
   /* public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(9999999L);
                }catch (InterruptedException a){

                }
            }
        }).start();
    }*/
}
