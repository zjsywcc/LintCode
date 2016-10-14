import java.util.*;

public class Main {

    public static void main(String[] args) {
        DirectedGraphNode a = new DirectedGraphNode(0);
        DirectedGraphNode b = new DirectedGraphNode(1);
        DirectedGraphNode c = new DirectedGraphNode(2);
        ArrayList<DirectedGraphNode> neighA = new ArrayList<DirectedGraphNode>();
        neighA.add(b);
        neighA.add(c);
        ArrayList<DirectedGraphNode> neighB = new ArrayList<DirectedGraphNode>();
        neighB.add(c);
        ArrayList<DirectedGraphNode> neighC = new ArrayList<DirectedGraphNode>();
        a.neighbors = neighA;
        b.neighbors = neighB;
        c.neighbors = neighC;
        DirectedGraphNode d = new DirectedGraphNode(3);
        DirectedGraphNode e = new DirectedGraphNode(4);
        DirectedGraphNode f = new DirectedGraphNode(5);
        ArrayList<DirectedGraphNode> neighD = new ArrayList<DirectedGraphNode>();
        ArrayList<DirectedGraphNode> neighE = new ArrayList<DirectedGraphNode>();
        ArrayList<DirectedGraphNode> neighF = new ArrayList<DirectedGraphNode>();
        neighD.add(e);
        neighF.add(e);
        d.neighbors = neighD;
        e.neighbors = neighE;
        f.neighbors = neighF;
        ArrayList<DirectedGraphNode> list = new ArrayList<DirectedGraphNode>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        List<List<Integer>> lists = connectedSet(list);
        printLists(lists);
    }

    //Union find version
    static class Union {

        int[] parent;
        int[] size;

        public Union(int n) {
            parent = new int[n];
            size = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if(parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

        public int compressFind(int x) {
            int fa = parent[x];
            while(parent[fa] != fa) {
                fa = parent[fa];
            }
            int crt = x;
            while(parent[crt] != fa) {
                int next = parent[crt];
                parent[crt] = fa;
                crt = next;
            }
            return fa;
        }

        public void union(int x, int y) {
            int parentX = compressFind(x);
            int parentY = compressFind(y);
            if(parentX == parentY) {
                return;
            }
            if(size[parentX] > size[parentY]) {
                parent[parentY] = parentX;
                size[parentX] += size[parentY];
            } else {
                parent[parentX] = parentY;
                size[parentY] += size[parentX];
            }
        }
    }

    static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static List<List<Integer>> connectedSet(ArrayList<DirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nodes == null || nodes.size() == 0) {
            return result;
        }
        HashMap<DirectedGraphNode, Boolean> map = new HashMap<DirectedGraphNode, Boolean>();
        int n = nodes.size();
        int[] toReal = new int[n];
        HashMap<Integer, Integer> toIndex = new HashMap<Integer, Integer>();
        int x = 0;
        for(DirectedGraphNode node : nodes) {
            map.put(node, false);
            toReal[x] = node.label;
            toIndex.put(node.label, x);
            x++;
        }
        Union union = new Union(n);
        for(DirectedGraphNode node : nodes) {
            if (!map.get(node)) {
                bfs(union, node, map, toIndex);
            }
        }
        for(int i = 0; i < n; i++) {
            System.out.print("parent "+i +": "+union.parent[i]+" ");
        }
        HashSet<Integer> index = new HashSet<Integer>();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < n; i++) {
            int fa = union.parent[i];
            index.add(fa);
            List<Integer> list = hashMap.get(fa);
            if(list == null) {
                list = new ArrayList<Integer>();
                list.add(toReal[i]);
                hashMap.put(fa, list);
            } else {
                list.add(toReal[i]);
                hashMap.put(fa, list);
            }
        }
        List<Integer> indexList = new ArrayList<Integer>(index);
        for(int i : indexList) {
            result.add(hashMap.get(i));
        }
        return result;
    }

    public static void bfs(Union union, DirectedGraphNode node, HashMap<DirectedGraphNode, Boolean> map, HashMap<Integer, Integer> toIndex) {
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        queue.offer(node);
        map.put(node, true);
        while(!queue.isEmpty()) {
            DirectedGraphNode out = queue.poll();
            ArrayList<DirectedGraphNode> neigh = out.neighbors;
            for(DirectedGraphNode n : neigh) {
                union.union(toIndex.get(out.label), toIndex.get(n.label));
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
