public class Main {

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};

        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(validTree(n1, edges1));
    }

    static class UnionFind {
        int[] parent;
        int[] size;

        UnionFind(int size) {
            this.parent = new int[size];
            this.size = new int[size];

            for(int i = 0; i < size; i++) {
                parent[i] = i;
                this.size[i] = 1;
            }
        }

        private int find(int id) {
            int fa = parent[id];
            while(fa != parent[fa]) {
                fa = parent[fa];
            }
            return fa;
        }

        private void union(int e1, int e2) {
            int parent1 = find(e1);
            int parent2 = find(e2);
            if(parent1 == parent2) {
                return;
            }
            if(size[parent1] > size[parent2]) {
                parent[parent2] = parent1;
                size[parent1] += size[parent2];
            } else {
                parent[parent1] = parent2;
                size[parent2] += size[parent1];
            }
        }
    }

    public static boolean validTree(int n, int[][] edges) {
        if(edges == null || n == 0 || n > edges.length + 1) {
            return false;
        }
        int len = edges.length;
        UnionFind union = new UnionFind(n);
        for(int i = 0; i < len; i++) {
            int e1 = edges[i][0];
            int e2 = edges[i][1];
            if(union.find(e1) != union.find(e2)) {
                union.union(e1, e2);
            } else {
                return false;
            }
        }
        return true;
    }
}
