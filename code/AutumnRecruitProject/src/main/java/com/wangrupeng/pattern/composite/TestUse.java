package com.wangrupeng.pattern.composite;

import java.util.Iterator;

public class TestUse {
    public static void main(String[] args) {
        Component root = new Folder("root");
        Component folder1 = new Folder("java");
        Component folder2 = new Folder("c#");
        Component file1 = new File("info.txt");
        root.addFolder(folder1).addFolder(folder2).addFile(file1); //一级目录
        folder1.addFile(new File("info.java"));
        Iterator<Component> iterator = root.iterator();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            if (component instanceof Folder) {
                System.out.print("Folder:");
            } else {
                System.out.print("File:");
            }
            component.display();
        }

    }
}
