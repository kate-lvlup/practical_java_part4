package com.softserve.edu.sprint4.task4;

class ArrayUtil {
    public static <T> T setAndReturn(T[] arrays, T element, int position) {
        if (position < 0 || position >= arrays.length) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        arrays[position] = element;
        return arrays[position];

    }

}
