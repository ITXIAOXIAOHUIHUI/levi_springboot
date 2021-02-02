package com.springboot.levi.leviweb1.java.doubletree;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序遍历：根节点左节点右节点
 * 中序遍历：左节点根节点右节点
 * 后序遍历：左节点右节点跟节点
 *
 * @author jianghaihui
 * @date 2021/1/25 17:45
 */
public class DoubleTree {

    /**
     * 前序遍历
     */
    List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        // 如果根节点为空，则直接返回空列表
        if (root == null) {
            return new ArrayList<>();
        }
        //节点不为空，将节点的值添加进列表中
        list.add(root.val);
        //判断此节点的左节点是否为空，如果不为空则将递归遍历左子树
        if (root.left != null) {
            preorderTraversal(root.left);
        }
        //判断此节点的右节点是否为空，如果不为空则将递归遍历右子树
        if (root.right != null) {
            preorderTraversal(root.right);
        }
        //最后返回列表
        return list;
    }



}
