package com.chatbook.gt.section1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：[描述]
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

}
