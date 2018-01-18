package com.java8.gt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：领域类，领域类是有生命周期的对象，VO对象时不可变对象
 *
 * @author ganting
 * @date 2018-01-17
 * @since v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple {

    private Integer heavy;

    private String color;

}
