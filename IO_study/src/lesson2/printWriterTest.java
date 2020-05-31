package lesson2;

public class printWriterTest {
    public static void main(String[] args) {
        System.out.println(999999);
      /*  Scanner sc = new Scanner(System.in);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (sc.nextInt()==0)
                        throw new RuntimeException("i==0");
                    System.out.println();
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    String stackTrace = sw.toString();
                    System.out.println(stackTrace);
                    e.printStackTrace();
                }

            }
        }).start();*/
    }
}
