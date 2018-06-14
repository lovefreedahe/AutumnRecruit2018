package com.wangrupeng.jianzhioffer.question2;


import java.io.*;

public class SingletonSerializedTest {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.ser"));
        out.writeObject(instanceOne);
        out.close();

        ObjectInput input = new ObjectInputStream(new FileInputStream("file.ser"));
        SerializedSingleton instanceTwo = (SerializedSingleton)input.readObject();
        input.close();
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }
}
