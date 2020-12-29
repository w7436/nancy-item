package nancy.util;

/**
 * @ClassName countHolder
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/14 20:18
 * @Version 1.0
 **/
public class countHolder {

    private static final ThreadLocal<Integer> COUNT = new ThreadLocal<>();
    //1.可以直接返回ThreadLocal变量
    //2.建议提供get、set、remove

    public static void set(Integer count){
        COUNT.set(count);
    }

    public static Integer get(){
        return COUNT.get();
    }

    public static void remove(){
        COUNT.remove();
    }
}
