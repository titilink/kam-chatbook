package com.chatbook.gt.section3;

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
public class Package implements Cloneable {

    private String name;

    private Color color;

    @Override
    public Package clone() {
        try {
            Package pkg = (Package) super.clone();
            //新增：新生成一个对象，切断与原对象关联，实现深拷贝
            pkg.setColor(new Color(pkg.getColor().getName()));
            return pkg;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void print() {
        System.out.println("package name=[" + name + "]" + ", package color=[" + color.getName() + "]");
    }

}
