import java.util.*;


public class Main {

    public static void main(String[] args) {
//        int[][] buildings = new int[][]{{1, 3, 3}, {2, 4, 4}, {5, 6, 1}};
        int[][] buildings = new int[][]{{1, 2, 1}, {1, 2, 2}, {1, 2, 3}};
        ArrayList<ArrayList<Integer>> lists = buildingOutline(buildings);
        printTriple(lists);
    }

    public static class Quat {
        int x;
        int id;
        int status; // 1 - start 0 - end
        int height;
        public Quat(int start, int id, int status, int height) {
            this.x = start;
            this.id = id;
            this.status = status;
            this.height = height;
        }

        public static Comparator<Quat> QuatComparator = new Comparator<Quat>() {
            @Override
            public int compare(Quat q1, Quat q2) {
                if (q1.x == q2.x) {
                    return q2.status - q1.status; //start在前
                } else {
                    return q1.x - q2.x;
                }
            }
        };
    }

    public static class Tuple {
        int x;
        int height;

        public Tuple(int x, int height) {
            this.x = x;
            this.height = height;
        }
    }

    public static ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if(buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return lists;
        }
        List<Quat> line = new ArrayList<Quat>();

        int len = buildings.length;
        for(int i = 0; i < len; i++) {
            line.add(new Quat(buildings[i][0], i, 1, buildings[i][2]));
            line.add(new Quat(buildings[i][1], i, 0, buildings[i][2]));
        }

        Collections.sort(line, Quat.QuatComparator);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(len,
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            }
        );
        List<Tuple> result = new ArrayList<Tuple>();
        int prevH = 0;
        for(Quat quat : line) {
            if(quat.status == 1) {
                queue.offer(quat.height);
            } else {
                queue.remove(quat.height);
            }
            int maxH = 0;
            if (!queue.isEmpty()) {
                maxH = queue.peek();
            }
            if (maxH != prevH) {
                result.add(new Tuple(quat.x, maxH));
            }
            prevH = maxH;
        }
        List<Tuple> mergedResult = new ArrayList<Tuple>();
        Tuple prev = result.get(0);
        for(int i = 1; i < result.size(); i++) {
            if(result.get(i).x != prev.x) {
                mergedResult.add(prev);
            }
            prev = result.get(i);
        }
        mergedResult.add(prev);
        printTuple(mergedResult);
        Tuple prevTuple = mergedResult.get(0);
        for(int i = 1; i < mergedResult.size(); i++) {
            Tuple crt = mergedResult.get(i);
            ArrayList<Integer> list = new ArrayList<Integer>();
            if(prevTuple.height != 0) {
                list.add(prevTuple.x);
                list.add(crt.x);
                list.add(prevTuple.height);
                lists.add(list);
            }
            prevTuple = crt;
        }
        return lists;
    }

    public static void printTuple(List<Tuple> result) {
        for(Tuple tuple : result) {
            System.out.println(String.format("index: %s height: %s", tuple.x, tuple.height));
        }
    }

    public static void printTriple(ArrayList<ArrayList<Integer>> lists) {
        for(ArrayList<Integer> list : lists) {
            System.out.println(String.format("st: %s  end: %s height: %s", list.get(0), list.get(1), list.get(2)));
        }
    }
}
