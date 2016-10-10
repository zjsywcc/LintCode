package com.company;

import java.util.*;

public class Main {

    public static class TreeNode {
        int val;
        TreeNode left ;
        TreeNode right ;
        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    public static void main(String[] args) {
	    int[] nums = new int[]{2, 5, 6, 0, 3, 1};
        int len = nums.length;
//        TreeNode root = createTree(0, len - 1, nums);
        TreeNode root = maxTree(nums);
        List<List<Integer>> result = levelOrder(root);
        for(List<Integer> list : result) {
            for(int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    //Recursive version
    public static TreeNode createTree(int i, int j, int[] nums) {
        if(i == j) {
            return new TreeNode(nums[i]);
        }
        else if(i > j) {
            return null;
        } else {
            int maxIndex = findMaxIndex(i, j, nums);
            TreeNode root = new TreeNode(nums[maxIndex]);
            root.left = createTree(i, maxIndex - 1, nums);
            root.right = createTree(maxIndex + 1, j, nums);
            return root;
        }
    }

    public static int findMaxIndex(int i, int j, int[] nums) {
        int max = Integer.MIN_VALUE;
        int index = i;
        for(int k = i; k <= j; k++) {
            if(nums[k] > max) {
                max = nums[k];
                index = k;
            }
        }
        return index;
    }

    //Bottom-up version
    public static TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        for (int i = 0; i < A.length; i++) {
            TreeNode node = new TreeNode(A[i]);
            while (!stack.isEmpty() && node.val >= stack.peek().val) {
                node.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = node;
            }
            stack.push(node);
        }

        TreeNode rst = stack.pop();
        while(!stack.isEmpty()) {
            rst = stack.pop();
        }
        return rst;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if(root == null) {
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if(treeNode.left != null)
                    queue.offer(treeNode.left);
                if(treeNode.right != null)
                    queue.offer(treeNode.right);
            }
            lists.add(list);
        }
        return lists;
    }
}
