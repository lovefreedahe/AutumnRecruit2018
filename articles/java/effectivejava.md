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


