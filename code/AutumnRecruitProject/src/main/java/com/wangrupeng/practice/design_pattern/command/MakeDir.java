package com.wangrupeng.practice.design_pattern.command;

public class MakeDir implements Command {
    FileOperator fileOperator;
    public MakeDir(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
    }
    @Override
    public void execute(String path) {
        fileOperator.createDir(path);
    }
}
