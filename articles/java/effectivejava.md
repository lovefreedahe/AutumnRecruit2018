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