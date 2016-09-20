import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static class TreeNode {
        int val;
        TreeNode left ;
        TreeNode right ;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        List<String> treeList = Arrays.asList(new String[]{"3", "1", "5", "#", "#", "4"});
        TreeNode head = createTree(treeList);
        insertNode(head, new TreeNode(2));
        traverse(head);
    }

    public static TreeNode insertNode(TreeNode root, TreeNode node) {
        if(root == null) {
            return node;
        }
        reverse(root, node);
        return root;
    }

    public static void reverse(TreeNode root, TreeNode node) {
        if(root == null) {
            return;
        }
        if(node.val > root.val) {
            if(root.right == null) {
                root.right = node;
                return;
            } else {
                reverse(root.right, node);
            }
        }
        if(node.val < root.val) {
            if(root.left == null) {
                root.left = node;
                return;
            } else {
                reverse(root.left, node);
            }
        }
        return;
    }

    public static TreeNode createTree(List<String> treeList) {
        if(treeList == null || treeList.size() == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int index = 0;
        TreeNode head = generateNode(treeList.get(index++));
        if(head != null)
            queue.offer(head);
        while(!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if(index < treeList.size()) {
                treeNode.left = generateNode(treeList.get(index++));
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
            }
            if(index < treeList.size()) {
                treeNode.right = generateNode(treeList.get(index++));
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
        }
        return head;
    }

    public static TreeNode generateNode(String str) {
        if(str.equals("#")) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(str));
        }
    }

    public static void traverse(TreeNode root) {
        if(root != null) {
            traverse(root.left);
            System.out.print(root.val+" ");
            traverse(root.right);
        }
    }


}
