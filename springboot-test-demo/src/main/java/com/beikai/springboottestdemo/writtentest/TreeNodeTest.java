package com.beikai.springboottestdemo.writtentest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName TreeNodeTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/25 21:51
 * @Version 1.0
 * 关于二叉树 镜面反转的测试
 **/
public class TreeNodeTest {

    class TreeNode{
        private int value;

        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 方式 1
     * @param treeNode
     * @return
     *
     * 判断当前节点是否为空,如果不为空,交换左右字节点, 递归的遍历
     */
    public TreeNode change(TreeNode treeNode){

        if (null == treeNode){
            return null;
        }

        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;


        change(treeNode.left);
        change(treeNode.right);

        return treeNode;
    }

    /**
     * 方式 2
     * @param treeNode
     * @return
     *
     * 判断当前节点是否为空,如果不为空,子节点先交换, 递归交换
     *
     */
    public TreeNode change2(TreeNode treeNode){

        if (null == treeNode){
            return null;
        }

        // 获取子节点
        TreeNode right = change(treeNode.right);
        TreeNode left = change(treeNode.left);

        // 子节点指针互换
        treeNode.right = left;
        treeNode.left = right;

        return treeNode;
    }

    /**
     * 方式 3
     * @param treeNode
     * @return
     *  非递归,从上到下把节点添加到队列中,并且交换两个子节点的位置(如果不为空)
     */
    public TreeNode change3(TreeNode treeNode){

        if (treeNode == null){
            return null;
        }

        // 创建队列用于存储 获取到的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);

        while (! queue.isEmpty()){
            TreeNode currTree = queue.poll();

            // 子节点值进行交换
            TreeNode temp = currTree.left;
            currTree.right = currTree.left;
            currTree.left = temp;

            // 如果子节点不为空,添加到队列中
            if (null != currTree.left){
                queue.add(currTree.left);
            }
            if (null != currTree.right){
                queue.add(currTree.right);
            }
        }

        return treeNode;
    }

    /**
     * 方式4
     * @param treeNode
     * @return
     *
     * 通过栈的方式,当前如果是null ,返回为null ,如果不为空,存进栈中,交换子节点
     */
    public TreeNode change4(TreeNode treeNode){
        if (null == treeNode){
            return null;
        }
        // 存进栈中
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);

        while (!stack.empty()){
            // 获取栈最顶端的值,并删除
            TreeNode currTree = stack.pop();

            // 子节点交换
            TreeNode temp = currTree.left;
            currTree.left = currTree.right;
            currTree.right = temp;

            if (null != currTree.right){
                stack.push(currTree.right);
            }
            if (null != currTree.left){
                stack.push(currTree.left);
            }
        }

        return treeNode;
    }
}
