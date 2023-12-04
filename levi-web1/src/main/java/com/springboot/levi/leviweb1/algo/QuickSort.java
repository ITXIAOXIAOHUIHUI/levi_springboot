package com.springboot.levi.leviweb1.algo;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            // 递归排序基准左右两侧
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int arr[], int startIndex, int endIndex) {
        //基准元素(可取随机位置)
        int p = arr[startIndex];
        //左指针
        int l = startIndex;
        //右指针
        int r = endIndex;

        while (l != r) {
            //控制右指针向左移动，找到小于基准元素的那个数
            while ((l < r) && (arr[r] > p)) {
                r--;
            }
            //控制左指针向右移动，找到大于基准元素的那个数
            while ((l < r) && (arr[l] <= p)) {
                l++;
            }
            //交换l指针和r指针所指的元素
            if (l < r) {
                int tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
            }
        }

        //交换基准元素和重合点的元素
        arr[startIndex] = arr[l];
        arr[l] = p;
        return l;
    }


    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(arr));

        quickSort(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
