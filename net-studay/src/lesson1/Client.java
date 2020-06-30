package lesson1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9000;

    private static final ExecutorService POOL = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        Socket client = new Socket(HOST, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (sc.hasNextLine()){
                    String line = sc.nextLine();
                    out.println(line);
                }
            }
        }).start();

        out.println("我是大佬");
        String str ;
        //阻塞等待服务端
        while ((str = in.readLine()) != null&&!str.equals("exit")){//io流在结束时才会为空
            System.out.println(str);
        }
        out.println("exit");
        client.close();

    }
}
