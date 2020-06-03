package lesson2;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class FileOperatorTest {
    @Test
    public void fileRead1() throws IOException {
        //File类测试   jckadsic
        /*String path = "E:\\java_code\\files\\";
        String name = "demo.txt";//可以替换成你想检测的文件
        String pathname = path + name;
        File file = new File(pathname);
        if (!file.exists()){
            try {
                file.createNewFile();
                System.out.println("文件创建成功");
            } catch (IOException e) {
                System.out.println("文件创建异常！");
            }
        }else {
            System.out.println("文件已经存在！");
        }
        System.out.println("文件"+name+"size:"+file.length());
        System.out.println("文件"+name + "最近修改时间"+new Date(file.lastModified()));
*/

        //System.out.println(File.separator);


       /* 文件内容打印至控制台
       FileInputStream fis = new FileInputStream(
                new File("E:\\JavaSpace\\JavaWeb13\\IO_study\\text"));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }*/

       /*
       // 目录创建测试
        String path = "E:\\java_code\\files\\";
        String dir_name = "demo_dirs";//想要创建的目录路径
        //String dir_name = "demo\\dir1\\dir2\\dir3";
        String pathname = path + dir_name;
        File file = new File(pathname);
        if(!file.exists()){
            try {
                file.mkdir();
            } catch (Exception e) {
                System.out.println("目录创建异常");
            }
        }else {
            System.out.println("目录已存在");
        }

        //路径创建测试
        File pfile = file.getParentFile();
        System.out.println(file.getParent());
        if(!pfile.exists()){
            pfile.mkdirs();
            System.out.println("路径"+pfile.getAbsolutePath()+"不存在，创建");
        }
        if(!file.exists()){
            file.createNewFile();
        }*/


       /* //文件拷贝
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\p.tiff");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\p .tiff");
        int len;
        byte [] bytes = new byte[1024];
        while ((len = fileInputStream.read(bytes))!=-1){
            fileOutputStream.write(bytes,0,len);
        }
        fileInputStream.close();
        fileOutputStream.close();*/

    }
    /*public void listFiles  (File file){
        //打印目录下第一级信息
        listAllFiles(file);
        if(file.exists() && file.isDirectory()){
            //file.isDirectory()：file是否是目录
            //file.isFile():file是否是文件
            File[] result = file.listFiles();
            for(File file1 : result){
                System.out.println(file1);
            }
        }

    }
    public void listAllFiles  (File file){
        //打印文件列表下所有信息
        if(file.isDirectory()){
            File[] result = file.listFiles();
            if(result!=null){
                for(File file1:result){
                    listAllFiles(file1);
                }
            }
        }else {
            System.out.println(file);
        }

    }*/
    @Test//使用缓冲流实现拷贝
    public void testBufferStreamCopy() throws IOException {
        File file = new File("bit.txt");
        if(!file.isFile()){
            return;
        }
        BufferedInputStream bfis = new BufferedInputStream(
                new FileInputStream(file));
        BufferedOutputStream bfos = new BufferedOutputStream(
                new FileOutputStream("src\\" +file.getName()));//copy到src目录下
        byte bytes[] = new byte[1024];
        int temp = 0;
        while ((temp = bfis.read(bytes)) != -1){
            bfos.write(bytes,0,temp);
            bfos.flush();
            bfos.close();
            bfis.close();
            System.out.println("copy成功！");
        }
    }
    @Test//有无缓冲的效率对比
    public void testBufferByte() throws IOException {
        File file = new File("bit.txt");
        BufferedInputStream bfis = new BufferedInputStream(new FileInputStream(file));
        int temp = 0;
        long time = System.currentTimeMillis();
        while ((temp = bfis.read()) != -1){
            //System.out.println((char) temp);
        }
        time = System.currentTimeMillis()-time;
        bfis.close();
        System.out.println("缓冲流读：" + time + "ms");
        FileInputStream fis = new FileInputStream(file);
        temp = 0;
        time = System.currentTimeMillis();
        while((temp = fis.read()) != -1){
            //System.out.println((char)temp);
        }
        time = System.currentTimeMillis()-time;
        fis.close();
        System.out.println("非缓冲区读：" + time + "ms");
    }

    //使用字符流FileReader和FileWriter复制文件
    @Test
    public void testReader_Writer() throws IOException {
        FileReader fileReader = new FileReader("bit.txt");
        FileWriter fileWriter = new FileWriter("bit1.txt");
        int ch;
        while ((ch = fileReader.read()) != -1){
            fileWriter.write(ch);
        }
        fileWriter.close();
        fileReader.close();

    }

    @Test
    public void testBufferReader_Writer() throws IOException {
        FileReader fileReader = new FileReader("bit.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("bit2.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String content = "";
        while ((content = bufferedReader.readLine()) != null){
            bufferedWriter.write(content+"\r\n");
        }
        fileReader.close();
        bufferedReader.close();
        bufferedWriter.close();
        fileWriter.close();

    }
    @Test
    public void testInputReader(){
        InputStream inputStream = System.in;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedWriter = new BufferedWriter(new FileWriter("ab.txt"));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                if("exit".equals(line)){
                    break;
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testOutputReader(){
        OutputStream outputStream = System.out;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedReader = new BufferedReader(new FileReader("a.txt"));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                if("exit".equals(line)){
                    break;
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//实例化一个Scanner对象
        int i = sc.nextInt();//从键盘识别一个int型数值
        System.out.println(i);//打印该数值
    }
}
