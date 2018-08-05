package com.wangrupeng.exam.pinduoduo.real.q2;
import java.util.Scanner;

public class Main {
    public static int IntMatch(String string){
        if (string.equals("0")) return 1;
        if (string.startsWith("0"))
            return 0;
        else
            return 1;
    }
    public static int isFloatMatch(String string){
        if (string.endsWith("0")) return 0;
        if (string.startsWith("0"))
            return 1;
        else return string.length()-1;


    }
    public static int process(String string){
        int count = 0;
        for (int i = 1; i < string.length(); i++){
            count += IntMatch(string.substring(0, i)) * isFloatMatch(string.substring(i, string.length())) +
                IntMatch(string.substring(0, i)) * IntMatch(string.substring(i, string.length()))  +
                isFloatMatch(string.substring(0, i)) * IntMatch(string.substring(i, string.length())) +
                isFloatMatch(string.substring(0, i)) * isFloatMatch(string.substring(i, string.length()));
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(process(input));
    }
}
