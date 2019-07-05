package com.example.demo.apec.hutools.generic;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ronaldo
 * @date 2019/7/4 9:18
 * @description
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Dog dog = new Dog();
//        dog.setName("儿哈");
//        dog.setAge(2);
        Dog clone = dog.clone();
        log.info("clone object is :{}",clone);
        //实现Cloneable接口后克隆的对象是浅克隆，要想实现深克隆
        Dog dog1 = new Dog();
        Dog deepCloneDog = ObjectUtil.cloneByStream(dog1);
        Dog deepCloneDog1 = ObjectUtil.clone(dog1);
        log.info("deepCloneDog object is :{},deepCloneDog1={}",deepCloneDog,deepCloneDog1);
    }
}
