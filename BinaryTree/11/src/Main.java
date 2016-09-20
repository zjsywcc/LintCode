import java.util.ArrayList;
import java.util.List;

public class Main {

    public static class TreeNode {
        int val;
        TreeNode left ;
        TreeNode right ;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> list = new ArrayList<Integer>();
        inorderReverse(root, list);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i : list) {
            if(i >= k1 && i <= k2) {
                result.add(i);
            }
        }
        return result;
    }

    public void inorderReverse(TreeNode root, List<Integer> list) {
        if(root != null) {
            inorderReverse(root.left, list);
            list.add(root.val);
            inorderReverse(root.right, list);
        }
    }
}
