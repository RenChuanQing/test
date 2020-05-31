public class TestStringBuilderStringBuffer {
    //线程安全测试
    public static void safeTest() {
        String s = new String();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                     while (stringBuffer.length()<100) {
                         stringBuffer.append("a");
                         //stringBuffer.append('a');

                     }
                }
            }).start();
        }
        for (int i = 0;i<3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (stringBuilder.length()<100) {
                        stringBuilder.append("a");
                        //stringBuilder.append("s");
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(stringBuffer.length());
        System.out.println(stringBuilder.length());
        if(stringBuffer.length()>100){
            System.out.println("stringBuffer线程不安全");
        }
        if(stringBuilder.length()>100){
            System.out.println("stringBuiler线程不安全");
        }
    }
    //性能测试
    public static void performanceTest() {
        // TODO Auto-generated method stub
        String str="abc";
        long starttime=System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            str=str+"abc";
        }
        long endtime=System.currentTimeMillis();
        String strtime=endtime-starttime+"";
        System.out.println("string花费时间"+strtime+"毫秒");

        StringBuffer strBuf=new StringBuffer("abc");
        long starttime1=System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            strBuf=strBuf.append("abc");
        }
        long endtime1=System.currentTimeMillis();
        String strtime1=endtime1-starttime1+"";
        System.out.println("stringBuffer花费时间"+strtime1+"毫秒");
        StringBuilder strbui=new StringBuilder("abc");
        long starttime2=System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            strbui=strbui.append("abc");
        }
        long endtime2=System.currentTimeMillis();
        String strtime2=endtime2-starttime2+"";
        System.out.println("stringBuilder花费时间"+strtime2+"毫秒");

    }

    public static void main(String[] args) {

        //safeTest();

        performanceTest();
    }
}
