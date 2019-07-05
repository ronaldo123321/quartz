package com.example.demo.apec.hutools.generic.convert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.thread.ThreadUtil;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ronaldo
 * @date 2019/7/4 9:26
 * @description 字符串转换工具类使用
 */
public class demo1 {

    public static void main(String[] args) {
        //1.转换为字符串
        int a = 20;
        String s = Convert.toStr(a);
        long[] b = {1,2,3,4};
        String str = Convert.toStr(b);
        System.out.println("s="+s+" str="+str);
        //2.转换为指定类型数组
        String[] c = {"1","2","3"};
        Integer[] array = Convert.toIntArray(c);

        long[] d = {1,2,3,4};
        Integer[] integers = Convert.toIntArray(d);
        String e = "2017-08-09";
        System.out.println("转换后的结果是："+array.toString());
        System.out.println("转换后的结果是："+integers.toString());
        System.out.println("转换日期是："+Convert.toDate(e));
        //3.转换为指定数据类型
        Object[] aa = {"a", "你", "好", "", 1};
        String convert = Convert.convert(String.class, aa);
        System.out.println("转换后的结果是："+convert);
        System.out.println("转为List后："+Convert.convert(List.class,aa));
        //4.unicode与字符串转换  Convert.UnicodeToStr
        String ac = "我是一个小小的可爱的字符串";
        String s1 = Convert.strToUnicode(ac);
        System.out.println(s1);

        //5.时间单位转换
        long time = 89710;
        long convertTime = Convert.convertTime(time, TimeUnit.SECONDS, TimeUnit.HOURS);
        System.out.println("时间转换后的结果："+convertTime);

        //6.自定义转换器
        int y = 8980;
        ConverterRegistry instance = ConverterRegistry.getInstance();
        String convert1 = instance.convert(String.class, y);
        System.out.println();

        ThreadUtil.execAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行异步线程1");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("执行异步线程2");
            }
        });

    }

}
