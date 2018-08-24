package com.wangrupeng.practice.design_pattern.command;

public class DeleteDir implements Command {
    FileOperator fileOperator;

    public DeleteDir(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
    }

    @Override
    public void execute(String path) {
        fileOperator.deleteDir(path);
    }
}
