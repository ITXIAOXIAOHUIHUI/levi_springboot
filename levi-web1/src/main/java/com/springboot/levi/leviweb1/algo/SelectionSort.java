package com.springboot.levi.leviweb1.algo;


import java.util.Arrays;

/**
 * 是一种简单的排序算法，它每次从未排序的部分选择最小（或最大）的元素，然后将其放到已排序部分的末尾。
 * 以下是选择排序的Java实现：
 */
public class SelectionSort {


    /**
     * 在选择排序中，每一轮都会选择未排序部分的最小元素，将其与未排序部分的第一个元素交换位置。重复这个过程直到整个数组有序。
     *
     * 选择排序的时间复杂度为O(n^2)，性能相对较差，不适用于大规模数据集。其他更高效的排序算法如快速排序、
     * 归并排序等在实际应用中更为常见。
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // 在未排序部分找到最小元素的索引
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 将找到的最小元素与当前位置交换
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        selectionSort(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }




}
