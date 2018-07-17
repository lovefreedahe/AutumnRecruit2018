
<!-- TOC -->

- [第2章 创建和销毁对象](#第2章-创建和销毁对象)
    - [第1条 考虑用静态工厂方法代替构造器](#第1条-考虑用静态工厂方法代替构造器)
    - [第2条 遇到多个构造器参数时要考虑用构建器](#第2条-遇到多个构造器参数时要考虑用构建器)
    - [第3条 用私有构造器或者枚举型强化Singleton属性](#第3条-用私有构造器或者枚举型强化singleton属性)
    - [第4条 通过私有构造器强化不可实例化的能力](#第4条-通过私有构造器强化不可实例化的能力)
    - [第5条 避免创建不必要的对象](#第5条-避免创建不必要的对象)
    - [第6条 消除过期的对象引用](#第6条-消除过期的对象引用)
    - [第7条 避免使用终结方法](#第7条-避免使用终结方法)
- [第3章 对于多有对象都适用的方法](#第3章-对于多有对象都适用的方法)
    - [第8条 覆盖equals时请遵守通用约定](#第8条-覆盖equals时请遵守通用约定)
    - [第9条 覆盖equals是总要覆盖hashCode](#第9条-覆盖equals是总要覆盖hashcode)
    - [第10条 始终覆盖toString](#第10条-始终覆盖tostring)
    - [第11条 紧身覆盖clone](#第11条-紧身覆盖clone)

<!-- /TOC -->

# 第2章 创建和销毁对象
## 第1条 考虑用静态工厂方法代替构造器
* 优势
    1. 有名称
    2. 不必在每一次调用的时候都创建一个实例
    3. 可以返回任何子类型的对象
        
    4. 创建参数化类型实例的时候，可以使代码更简洁
    ```java
    public class MapUtils {
        public static <K,V>HashMap<K,V> newInstance() {
            return new HashMap<K,V>();
        }

        public static void main(String[] args) {
            Map<String, List<String>> map = MapUtils.newInstance();
        }
    }  
    ```
* 缺点
    1. 类如果不含有公有的或者受保护的构造器，就不能被实例化
    2. 实际上和其他的静态方法没有任何区别

* 常用名称  
    valueOf, of, getInstance, newInstance, getType, newType

## 第2条 遇到多个构造器参数时要考虑用构建器  

```java  
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    public static class Builder {
        //Required parameters
        private final int servingSize;
        private final int servings;

        //Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val) {
            calories = val;
            return this;
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
    }

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new Builder(240,8).calories(8).fat(0).build();
    }
}
```  

## 第3条 用私有构造器或者枚举型强化Singleton属性
> 单元素的枚举类型已经成为实现Singleton的最佳方法  
```java
public enum Elvis {
    INSTANCE;
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void leaveTheBuilding() {

    }
}
```

## 第4条 通过私有构造器强化不可实例化的能力
```java
public class UtilityClass {
    private UtilityClass() {
        throw new AssertionError();
    }
}
```

## 第5条 避免创建不必要的对象
```java
String s = new String("test"); //DO NOT DO THIS!
String s = "test";
```

> 对于所有在同一台虚拟机中运行的代码，只要它们包含相同的字符串字面常量，该对象就会被重用[JLS,3.10.5]  

> 同时有构造器和静态工厂，优先使用静态工厂。构造器每次调用都会创建一个新的对象，静态工厂不会。  

```java
public class Person {
    private final Date birthDate = null;

    //DO NOT DO THIS!
    public boolean isBabyBoomer() {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }
}
```
```java
public class Person2 {
    private final Date birthDate = null;
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }
    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }
}
```
> 要优先使用基本类型而不是装箱基本类型  
下面这段代码如果sum使用Long类型，会重复创建2^31个Long实例。
```java
public class AutoBoxing {
    public static void main(String[] args) {
        //DO NOT DO THIS
        //Long sum = 0L;
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
```
> 由于小对象的构造器只做很少量的显示工作，所以，小对象的创建和挥手动作是非常廉价的。  
> 除非对象池(object pool)中的对象非常重量级，否则维护对象池来避免重复创建对象不是好方法。  


## 第6条 消除过期的对象引用
```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(Object e) {
        ensureCapacity();
        elements[++size] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        //memory leak happens here
        return elements[--size];
    }
    public Object popTrue() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object e = elements[--size];
        elements[size] = null;
        return e;
    }

    public void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size - 1);
        }
    }
}
```
> 清空对象应该是一种例外，而不是规范行为。  

> 一旦数组元素变成了非法活动的部分，就手工清空这些数组元素。

常见来源
* 缓存  
    用WeakHashMap代替缓存
* 缓存的生命周期是否有意义不是不容易确定。  
    清除工作可以由一个后台线程(Timer或者ScheduledTheadPoolExecutor)完成或者在Tina及新条目的时候处理。LinkedHashmap类利用removeEldestEntry。
* 监听和其他回调  

## 第7条 避免使用终结方法
终结方法(finializer)通常是不可预测的，也是非常危险的，一般情况下是不必要的。  
```java
Foo foo = new Foo();
try {
    //Do what must be done with foo
    ...
} finally {
    foo.terminate();
}
```

# 第3章 对于多有对象都适用的方法
## 第8条 覆盖equals时请遵守通用约定
覆盖满足条件
* 类的每个实例本质上都是唯一的
* 不关心类是否提供了"逻辑相等(logical equality)"的测试功能
* 超类已经覆盖了equals, 从超类继承过来的行为对于子类也合适
* 类是私有的或包级私有的, 可以确定它的equals方法永远不会被调用。

等价关系(equivalence relation)
* 自反性(reflexive)
* 对称性(symmetric)
* 传递性(transitive)
* 一致性(consistent)
* 非空性(Nonnullity)

实现技巧
1. 使用==操作符检查“参数是否为正确的引用”
2. 使用instanceof检查“参数是否为正确的类型”
3. 吧参数转换成正确的类型
4. 对于该类中的每个“关键(gisnificant)”域，检查参数中的域是否与该对象中对应的域匹配
5. 单元测试equales方法是否满足对称性、传递性、一致性。

## 第9条 覆盖equals是总要覆盖hashCode
* 只要equals没有被修改，每次调用hashCode都返回相同的整数。在一个程序的多次执行过程中，可以不一致。
* 两个对象equals相等，则hashCode也相等
* 如果equals不相等，则hashCode不一定不相等，但是给不一样的hashCode可以提高 散列表(hash table)的性能。

## 第10条 始终覆盖toString

## 第11条 紧身覆盖clone





