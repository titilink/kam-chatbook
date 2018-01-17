package com.chatbook.gt.section2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：如何编写一个equals方法
 *
 * @author ganting
 * @date 2018-01-16
 * @since v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point<T> {

    private T x;

    private T y;

    @Override
    public String toString() {
        return "x=[" + x + "], y=[" + y + "]";
    }

    @Override
    public boolean equals(Object other) {
        // 为了性能，比较对象引用
        if ( this == other ) return true;
        // 为了安全，比较空值
        if ( null == other ) return false;
        // 为了类型安全，比较类型
        if ( ! (this.getClass() == other.getClass()) ) return false;
        // 类型转换
        Point<T> otherPoint = (Point) other;
        // 业务字段比较
        if ( this.getX() == otherPoint.getX() && this.getY() == otherPoint.getY() ) return true;

        return false;
    }

}
