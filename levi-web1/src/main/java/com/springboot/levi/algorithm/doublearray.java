package com.springboot.levi.algorithm;

/**
 * @program: levi_springboot
 * @description:
 * 在一个二维数组array中（每个一维数组的长度相同）这个说明列是相同的，
 * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 *
 * 给定 target = 3，返回 false。
 * @author: jhh
 * @create: 2022-12-01 11:00
 */
public class doublearray {

    public boolean isContain(int target,int[][] array){
        int toatal = array.length;
        //长度是4
        int lineLength = array[0].length;
        //j = 3
        for(int i = 0,j = lineLength - 1 ; i < toatal && j >= 0;){
            System.out.println(j);
            if(array[i][j] == target){
                return true;
            }else if(array[i][j] < target){
                i++;
            } else{
                System.out.println(i+"==="+j);
                j--;
                if(array[i][j] == target){
                    return true;
                }
                //System.out.println(i+"-----"+j +"value"+array[i][j]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int i = 10;
        int j = 11;

        System.out.println(i++);
        System.out.println("================");
        System.out.println(i);
        System.out.println(++j);
        System.out.println("================");
        System.out.println(j);
    }
}
