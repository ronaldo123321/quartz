package com.example.demo.apec.effective;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 * @author ronaldo
 * @date 2019/7/2 12:24
 * @description
 */
public class Stack {

    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object object){
        ensureCapacity();
        elements[size++] = object;
    }

    public Object pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        elements[size] = null;
        return elements[--size];
    }


    private void ensureCapacity(){
        if(elements.length == size){
            elements = Arrays.copyOf(elements,2*size + 1);
        }
    }
}
