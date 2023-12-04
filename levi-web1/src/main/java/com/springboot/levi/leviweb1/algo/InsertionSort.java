package com.springboot.levi.leviweb1.algo;

import java.util.Arrays;

/**
 * 插入排序（Insertion Sort）是一种简单直观的排序算法，它逐步构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
 * <p>
 * 在插入排序中，首先将第一个元素视为已排序序列，然后将未排序部分的元素逐个插入到已排序序列的正确位置。算法的关键是找到正确的插入位置并移动元素。
 * <p>
 * 插入排序的时间复杂度为O(n^2)，是一个稳定的排序算法。尽管插入排序在大规模数据集上的性能不如一些高级排序算法，但它对于小型数据集或者已经近乎有序的数据集具有较好的性能。
 */
public class InsertionSort {

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // 将大于key的元素往后移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // 插入key到正确的位置
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        insertionSort(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
