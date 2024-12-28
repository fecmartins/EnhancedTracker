package com.martins.generics;

import java.util.Arrays;

public class CustomArrayGenericsList<T> {

    private Object[] data;
    private static int DEFAULT_SIZE = 5;
    private int size = 0; // number of elements in the array

    public CustomArrayGenericsList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    public void add(T value) {
       if (ifFull()) {
           resize();
       }
       data[size++] = value;
    }

    private void resize() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private boolean ifFull() {
        return size == data.length;
    }

    public T remove() {
        T removed = (T) data[--size];
        return removed;
    }

    public T get(int index) {
        return (T) (data[index]);
    }

    public int size() {
        return size;
    }

    public void set(int index, T value) {
        data[index] = value;
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
//       ArrayList list = new ArrayList<>();
//        list.add("Hello");
//        System.out.println(list.get(0));

        CustomArrayGenericsList<Integer> list2 = new CustomArrayGenericsList<>();

        for (int i = 0 ; i < 8; i++) {
            list2.add(2 * i);
        }

        System.out.println(list2);
    }
}
