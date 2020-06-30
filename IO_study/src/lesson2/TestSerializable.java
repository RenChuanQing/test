package lesson2;

import org.junit.Test;

import java.io.*;

public class TestSerializable {
    @Test
    public void test() throws IOException {
        OutputStream outputStream = new FileOutputStream(new File("hello.txt"));
        outputStream.write("比特欢迎你".getBytes("ISO8859-1"));
        outputStream.close();
       // System.getProperties().list(System.out);
    }
    public static void main(String[] args) throws Exception {
        //serializablePerson();
        Person person =deserializePerson();
        System.out.println(person.toString());
    }
    private static void serializablePerson() throws IOException {
        Person person = new Person();
        person.setName("bit");
        person.setAge(10);
        person.setSex("男");
        person.setStuID(100);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(new File("e:/person.txt"))
        );
        objectOutputStream.writeObject(person);
        System.out.println("person对象序列化成功");
        objectOutputStream.close();
    }
    private static Person deserializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream objecInputStream = new ObjectInputStream(
                new FileInputStream(new File("e:/person.txt")));
        Person person = (Person)objecInputStream.readObject();
        System.out.println("person 对象反序列化成功！");
        return person;
    }
}

class Person implements Serializable {
    private static final long serialVersionUID =1L;
    private String name;
    private int age;
    private String sex;

    transient private int stuID;//transient修饰的变量，不能被序列化

    private static int count = 99;//静态变量不能被序列化


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public int getStuID() {
        return stuID;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }


    @Override
    public String toString() {
        return "Person{"+
                "name'"+name+'\''+
                ",age="+age+"" +
                ",sex='"+sex+'\''+
                ",stuID"+stuID+
                ",count="+count+
                '}';
    }
}