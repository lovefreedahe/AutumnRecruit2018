package com.wangrupeng.practice.design_pattern.prototype.seriliazed;

import java.io.*;

public class SerializeTest {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        PrototypeSerialize serialize = new PrototypeSerialize();
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("test"));
        outputStream.writeObject(serialize);
        outputStream.close();
        File file = new File("test");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        PrototypeSerialize serialize1 = (PrototypeSerialize) inputStream.readObject();
        System.out.println(serialize.hashCode());
        System.out.println(serialize1.hashCode());
    }
}
