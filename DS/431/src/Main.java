import java.util.*;

public class Main {

    public static void main(String[] args) {
        UndirectedGraphNode a = new UndirectedGraphNode(1);
        UndirectedGraphNode b = new UndirectedGraphNode(2);
        UndirectedGraphNode c = new UndirectedGraphNode(3);
        ArrayList<UndirectedGraphNode> neighA = new ArrayList<UndirectedGraphNode>();
        neighA.add(b);
        neighA.add(c);
        ArrayList<UndirectedGraphNode> neighB = new ArrayList<UndirectedGraphNode>();
        neighB.add(a);
        neighB.add(c);
        ArrayList<UndirectedGraphNode> neighC = new ArrayList<UndirectedGraphNode>();
        neighA.add(a);
        neighA.add(b);
        a.neighbors = neighA;
        b.neighbors = neighB;
        c.neighbors = neighC;
        UndirectedGraphNode d = new UndirectedGraphNode(4);
        ArrayList<UndirectedGraphNode> list = new ArrayList<UndirectedGraphNode>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        List<List<Integer>> lists;
        lists = connectedSet(list);
        printLists(lists);
    }

    static class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }



    public static List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        HashMap<UndirectedGraphNode, Boolean> map = new HashMap<UndirectedGraphNode, Boolean>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nodes == null || nodes.size() == 0) {
            return result;
        }
        for(UndirectedGraphNode node : nodes) {
            map.put(node, false);
        }
        for(UndirectedGraphNode node : nodes) {
            if (!map.get(node)) {
                List<Integer> res = new ArrayList<Integer>();
                bfs(node, res, map);
                result.add(res);
            }
        }
        return result;
    }

    //BFS version
    public static void bfs(UndirectedGraphNode node, List<Integer> result, HashMap<UndirectedGraphNode, Boolean> map) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        map.put(node, true);
        while(!queue.isEmpty()) {
            UndirectedGraphNode out = queue.poll();
            result.add(out.label);
            ArrayList<UndirectedGraphNode> neigh = out.neighbors;
            for(UndirectedGraphNode n : neigh) {
                if (!map.get(n)) {
                    queue.offer(n);
                    map.put(n, true);
                }
            }
        }
    }

    public static void printLists(List<List<Integer>> lists) {
        for(List<Integer> list : lists) {
            for(Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }




}
