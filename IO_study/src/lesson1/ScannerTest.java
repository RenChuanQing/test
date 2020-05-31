package lesson1;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0;i<3;i++){

            //多线程的阻塞也是需要满足一定条件才返回
            //sc.has...类型：
            //1.都是阻塞式方法，造成当前线程的阻塞
            //2.满足一定条件才返回
            // 3.hasNext需要满足换行符或者空格等特殊字符,hasNextLine需要满足换行符
            int num = sc.nextInt();
            for (int j = 0;j<num;j++){
                int n = sc.nextInt();
                System.out.println(n);
            }
        }

    }
}
