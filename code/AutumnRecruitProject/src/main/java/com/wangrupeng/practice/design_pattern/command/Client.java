package com.wangrupeng.practice.design_pattern.command;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client {
    public static void main(String[] args) {
        FileOperator fileOperator = new FileOperator();
        Command command = new MakeDir(fileOperator);
        command.execute("test");

    }
}
