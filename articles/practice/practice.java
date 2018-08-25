public class Singleton {
    private static final Singleton singleton = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return singleton;
    }
}

public class Singleton2 {
    private static Singleton3 instance;
    private Singleton3() {}
    static {
        try{
            instance = new Singleton3();
        } catch(Exception) {
            throw new RuntimeException("Exception occured while creating Singleton3 instance.");
        }
    }
    public static Singleton3 getInstance() {
        return instance;
    }
}

public class Singleton3 {
    private static Singleton2 instance;
    private Singleton2() {}
    public static Singleton2 getInstance() {
        if(instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

public class Singleton4 {
    private static Singleton4 instance;
    private Singleton4() {}
    public synchronnized static Singleton4 getInstance() {
        if(instance == null) {
            instance = new Singleton4();
        } 
        return instance;
    }

    public static Singleton4 getInstanceDoubleCheck() {
        if(instance == null) {
            synchronized(Singleton4.class) {
                if(instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

public class Singleton5 {
    private Singleton5() {}

    private static class SingletonHelper {
        private static final Singleton5 instance = new Singleton5(){};
    }

    public static Singleton5() {
        return SingletonHelper.instance;
    }
}

public enum EnumSingleton {
    INSTANCE;
    public static void otherFunc() {}
}

10
0
5 3 0
8 4 0
9 0
9 0
3 0
0
7 9 0
0
9 7 0