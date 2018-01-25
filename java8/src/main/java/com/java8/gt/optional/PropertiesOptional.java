package com.java8.gt.optional;

import java.util.Optional;
import java.util.Properties;

/**
 * 描述：Optional<T>是java8引入的用语解决null空指针问题的类型。
 *     主要包括以下几个方法：
 *       1、类比Stream<T>包括：
 *       <code>
 *          public <U> Optional<U> map(Function<? super T, ? extends U> mapper>);
 *          public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper);
 *          public Optional<T> filter(Predicate<? super T> predicate);
 *       </code>
 *       2、取值：
 *       <code>
 *          public T get();  为null时抛出NullPointException
 *          public T orElse(T other);  默认值
 *          public T orElseGet(Supplier<? extends T> supplier);  延迟初始化默认值
 *          public <X extends Throwable> T orElseThrow(Supplier<? extends X> supplier); 延迟返回一个自定义异常
 *       </code>
 *       3、判断：
 *       <code>
 *           public boolean isPresent();
 *           public void ifPresent(Consumer<? super T> consumer);  传入一个消费者，当有值时执行消费者
 *       </code>
 *       4、初始化：
 *       <code>
 *           public static <T> Optional<T> of(T value); 如果为空，抛出NullPointException
 *           public static <T> Optional<T> ofNullable(T value);  如果为空，返回空值
 *           public static <T> Optional<T> empty();  初始化一个空值
 *       </code>
 *
 *     下面以一个获取属性文件字段值为例演示Optional的用法
 *
 * @author sandy
 * @date 2018/1/23
 * @since v1.0
 */
public class PropertiesOptional {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");

        PropertiesOptional propertiesOptional = new PropertiesOptional();
        System.out.println(propertiesOptional.readDuration(properties, "a")); // 5
        System.out.println(propertiesOptional.readDuration(properties, "b")); // 0
        System.out.println(propertiesOptional.readDuration(properties, "c")); // 0
        System.out.println(propertiesOptional.readDuration(properties, "d")); // 0
    }

    /**
     * 按属性key读取属性值value，如果是一个代表正整数的字符串，就返回该整数值，否则返回0
     *
     * @param prop 属性列表
     * @param name 属性key
     */
    private int readDuration(Properties prop, String name) {
        return Optional.ofNullable((String) prop.get(name))
            .flatMap(PropertiesOptional::stringToInt)
            .filter(i -> i > 0)
            .orElse(0);
    }

    private static Optional<Integer> stringToInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }

}
