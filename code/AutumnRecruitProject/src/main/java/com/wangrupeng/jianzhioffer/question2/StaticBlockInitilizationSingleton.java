package com.wangrupeng.jianzhioffer.question2;

public class StaticBlockInitilizationSingleton {
    private static StaticBlockInitilizationSingleton instalce;
    private StaticBlockInitilizationSingleton() {}

    static {
        try {
            instalce = new StaticBlockInitilizationSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockInitilizationSingleton getInstalce() {
        return instalce;
    }
}
