package com.example.demo.apec.hutools.generic;

import cn.hutool.core.clone.CloneSupport;
import lombok.Data;

/**
 * @author ronaldo
 * @date 2019/7/4 9:17
 * @description
 */
@Data
public class Dog extends CloneSupport<Dog> {

    private String name = "miaomiao1";
    private int age = 20;
}
