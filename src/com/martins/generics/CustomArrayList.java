package com.martins.generics;

import java.util.Arrays;

public class CustomArrayList {

    private int[] data;
    private static int DEFAULT_SIZE = 5;
    private int size = 0; // number of elements in the array

    public CustomArrayList() {
        this.data = new int[DEFAULT_SIZE];
    }

    public void add(int value) {
       if (ifFull()) {
           resize();
       }
       data[size++] = value;
    }

    private void resize() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private boolean ifFull() {
        return size == data.length;
    }

    public int remove(int i) {
        int value = data[size - 1];
        data[size - 1] = 0;
        size--;
        return value;
    }

    public int get(int index) {
        return data[index];
    }

    public int size() {
        return size;
    }

    public int set(int index, int value) {
        int oldValue = data[index];
        data[index] = value;
        return oldValue;
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

        CustomArrayList list = new CustomArrayList();

        list.add(10);
        list.add(20);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);

    }
}
