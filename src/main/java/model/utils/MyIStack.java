package model.utils;


import exceptions.MyException;

import java.util.List;

public interface MyIStack<T> {
    MyIStack<T> clone1();

    T pop() throws MyException;
    void push(T element);
    T peek();
    boolean isEmpty();
    List<T> getReversed();
}