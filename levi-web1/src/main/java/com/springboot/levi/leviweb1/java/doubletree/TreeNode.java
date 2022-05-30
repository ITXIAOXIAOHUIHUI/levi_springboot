package com.springboot.levi.leviweb1.java.doubletree;

/**
 * @author jianghaihui
 * @date 2021/1/25 17:52
 */
public class TreeNode {
    int val;

    TreeNode left;

    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
