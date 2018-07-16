package com.wangrupeng.pattern.command;

public class TestUse {
    public static void main(String args[]) throws Exception{
        //接收者
        MakeFile makeFile = new MakeFile();
        //命令
        CommandCreate create = new CommandCreate(makeFile);
        CommandDelete delete = new CommandDelete(makeFile);
        //请求者
        Client client = new Client();
        //执行命令
        client.setCommand(create).executeCommand("D:\\HUST\\AutumnRecruit2018\\code\\AutumnRecruitProject\\resources\\test1.txt");
        client.setCommand(create).executeCommand("D:\\HUST\\AutumnRecruit2018\\code\\AutumnRecruitProject\\resources\\test2.txt");
        client.setCommand(delete).executeCommand("D:\\HUST\\AutumnRecruit2018\\code\\AutumnRecruitProject\\resources\\test2.txt");
    }
}//执行完后在D盘会有一个test1.txt的文件，test2.txt本页创建了，不过又被删除了。。
