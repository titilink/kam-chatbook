package com.java8.gt.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：和集合一样，流只能被消费一次，如果需要再消费，需要重新获取流。
 *       流中的lambda表达式不会改变状态。
 *
 * @author ganting
 * @date 2018-01-18
 * @since v1.0
 */
public class StreamOnce {

    public static void main(String[] args) {
        StreamOnce streamOnce = new StreamOnce();
//        streamOnce.streamOnce();

        List<String> lists = Arrays.asList("A", "B", "C");
        System.out.println(streamOnce.streamNotChangeState(lists));
        System.out.println(streamOnce.streamNotChangeState(lists));
    }

    private void streamOnce() {
        Stream<String> s = Arrays.asList("A", "B", "C").stream();

        s.forEach(System.out :: println);
        s.forEach(System.out :: println); //流已经被消费过了
    }

    /**
     * 虽然传进来集合引用，但方法内的stream没有改变引用的状态
     *
     * @param lists
     */
    private List<String> streamNotChangeState(List<String> lists) {
        return lists.stream().map(s -> s + "1").collect(Collectors.toList()); //流中的lambda表达式不会改变状态
    }

}
