package com.example.demo.apec.hutools.generic;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.Cloneable;
import com.mchange.v2.codegen.bean.CloneableExtension;

/**
 * @author ronaldo
 * @date 2019/7/4 9:12
 * @description
 */
public class Cat implements Cloneable<Cat> {

    private String name = "miaomiao";
    private int age = 10;

    @Override
    public Cat clone() {
        try {
            return (Cat) super.clone();
        } catch (CloneNotSupportedException e) {
           throw new CloneRuntimeException(e);
        }
    }
}
