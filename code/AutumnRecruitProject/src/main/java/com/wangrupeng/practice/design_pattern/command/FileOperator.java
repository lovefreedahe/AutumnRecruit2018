package com.wangrupeng.practice.design_pattern.command;

public class FileOperator {

    public boolean createDir(String path) {
        System.out.println("Create " + path);
        return true;
    }

    public boolean deleteDir(String path) {
        System.out.println("Delete " + path);
        return true;
    }

}
