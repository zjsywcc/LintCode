import java.util.*;

public class Main {

    public static void main(String[] args) {
        DirectedGraphNode n0 = new DirectedGraphNode(0);
        DirectedGraphNode n1 = new DirectedGraphNode(1);
        DirectedGraphNode n2 = new DirectedGraphNode(2);
        DirectedGraphNode n3 = new DirectedGraphNode(3);
        DirectedGraphNode n4 = new DirectedGraphNode(4);
        DirectedGraphNode n5 = new DirectedGraphNode(5);
        DirectedGraphNode n6 = new DirectedGraphNode(6);
        DirectedGraphNode n7 = new DirectedGraphNode(7);
        DirectedGraphNode n8 = new DirectedGraphNode(8);
        DirectedGraphNode n9 = new DirectedGraphNode(9);

        ArrayList<DirectedGraphNode> next0 = new ArrayList<DirectedGraphNode>();
        next0.add(n1);
        next0.add(n4);
        next0.add(n5);
        next0.add(n7);
        next0.add(n8);
        next0.add(n9);
        n0.neighbors = next0;

        ArrayList<DirectedGraphNode> next1 = new ArrayList<DirectedGraphNode>();
        next1.add(n3);
        next1.add(n4);
        next1.add(n6);
        n1.neighbors = next1;

        ArrayList<DirectedGraphNode> next2 = new ArrayList<DirectedGraphNode>();
        next2.add(n8);
        next2.add(n3);
        next2.add(n4);
        next2.add(n5);
        next2.add(n7);
        n2.neighbors = next2;

        ArrayList<DirectedGraphNode> next3 = new ArrayList<DirectedGraphNode>();
        next3.add(n6);
        n3.neighbors = next3;

        ArrayList<DirectedGraphNode> next4 = new ArrayList<DirectedGraphNode>();
        next4.add(n3);
        next4.add(n6);
        n4.neighbors = next4;

        ArrayList<DirectedGraphNode> next5 = new ArrayList<DirectedGraphNode>();
        next5.add(n1);
        next5.add(n3);
        next5.add(n4);
        next5.add(n6);
        next5.add(n9);
        n5.neighbors = next5;

        ArrayList<DirectedGraphNode> next6 = new ArrayList<DirectedGraphNode>();
        n6.neighbors = next6;

        ArrayList<DirectedGraphNode> next7 = new ArrayList<DirectedGraphNode>();
        next7.add(n9);
        next7.add(n3);
        next7.add(n4);
        next7.add(n5);
        next7.add(n1);
        n7.neighbors = next7;

        ArrayList<DirectedGraphNode> next8 = new ArrayList<DirectedGraphNode>();
        next8.add(n9);
        next8.add(n3);
        next8.add(n4);
        next8.add(n6);
        n8.neighbors = next8;

        ArrayList<DirectedGraphNode> next9 = new ArrayList<DirectedGraphNode>();
        next9.add(n3);
        next9.add(n4);
        next9.add(n6);
        n9.neighbors = next9;

        ArrayList<DirectedGraphNode> list = new ArrayList<DirectedGraphNode>();
        list.add(n0);
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(n5);
        list.add(n6);
        list.add(n7);
        list.add(n8);
        list.add(n9);

        printList(topSort(list));
        printList(topSort1(list));
    }

    static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        if(graph == null || graph.size() == 0) {
            return result;
        }
        HashMap<DirectedGraphNode, HashSet<DirectedGraphNode>> prevNodeMap = new HashMap<DirectedGraphNode, HashSet<DirectedGraphNode>>();
        for(DirectedGraphNode node : graph) {
            ArrayList<DirectedGraphNode> neighbors = node.neighbors;
            for(DirectedGraphNode next : neighbors) {
                HashSet<DirectedGraphNode> prev = prevNodeMap.get(next);
                if(prev == null) {
                    prev = new HashSet<DirectedGraphNode>();
                    prev.add(node);
                    prevNodeMap.put(next, prev);
                } else {
                    prev.add(node);
                    prevNodeMap.put(next, prev);
                }
            }
        }
        HashMap<DirectedGraphNode, Boolean> flag = new HashMap<DirectedGraphNode, Boolean>();
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        for(DirectedGraphNode node : graph) {
            if(prevNodeMap.get(node) == null) {
                queue.offer(node);
                flag.put(node, true);
                deletePrev(graph, prevNodeMap, node);
            }
        }
        while(!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            result.add(node);
            for(DirectedGraphNode next : node.neighbors) {
                if(flag.get(next) == null && prevNodeMap.get(next).size() == 0) {
//                        System.out.println(String.format("flag: %s hashSetSize: %s", flag.get(next), prevNodeMap.get(next).size()));
                    queue.offer(next);
                    flag.put(next, true);
                    deletePrev(graph, prevNodeMap, next);
                }
            }
        }
        if(result.size() == graph.size()) {
            return result;
        }
        return result;
    }

    public static void deletePrev(ArrayList<DirectedGraphNode> graph, HashMap<DirectedGraphNode, HashSet<DirectedGraphNode>> prevNodeMap, DirectedGraphNode node) {
        for(DirectedGraphNode crt : graph) {
            HashSet<DirectedGraphNode> prevNodes = prevNodeMap.get(crt);
            if(prevNodes != null) {
                if (prevNodes.contains(node)) {
                    prevNodes.remove(node);
                }
                prevNodeMap.put(crt, prevNodes);
            }
        }
    }

    public static void printList(ArrayList<DirectedGraphNode> list) {
        for(DirectedGraphNode node : list) {
            System.out.print(node.label + " ");
        }
        System.out.println();
    }

    public static ArrayList<DirectedGraphNode> topSort1(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }
        }
        Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                q.offer(node);
                result.add(node);
            }
        }
        while (!q.isEmpty()) {
            DirectedGraphNode node = q.poll();
            for (DirectedGraphNode n : node.neighbors) {
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) {
                    result.add(n);
                    q.offer(n);
                }
            }
        }
        return result;
    }
}
