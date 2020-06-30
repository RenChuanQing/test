package lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 9000;
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true){
            //阻塞等待，直到有新的客户端连接
            Socket socket = serverSocket.accept();
            pool.execute(new Task(socket));
        }
    }
    private static class Task implements Runnable{
        private Socket client;
        public Task(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                String str ;
                int i = 0;
                //阻塞等待服务端
                while ((str = in.readLine()) != null&&!str.equals("exit")){
                    System.out.println(str);
                    out.println(i+"你是大佬");
                    i++;
                }
                out.println("exit");
                client.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
