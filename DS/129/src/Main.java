public class Main {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"null", "21->9->null", "14->null", "null"};
        ListNode[] listNodes = createNodeList(strings);
        printHash(listNodes);
        ListNode[] listNodes1 = rehashing(listNodes);
        printHash(listNodes1);
    }

    public static ListNode[] rehashing(ListNode[] hashTable) {
        int len = hashTable.length;
        int newLen = len * 2;
        ListNode[] newHash = new ListNode[newLen];
        for(int i = 0; i < len; i++) {
            ListNode node = hashTable[i];
            while(node != null) {
                ListNode newNode = new ListNode(node.val);
                int index = (node.val % newLen + newLen) % newLen;
                if(newHash[index] == null) {
                    newHash[index] = newNode;
                    newNode.next = null;
                } else {
                    ListNode node1 = newHash[index];
                    while(node1.next != null) {
                        node1 = node1.next;
                    }
                    node1.next = newNode;
                    newNode.next = null;
                }
                node = node.next;
            }
        }
        return newHash;
    }

    public static ListNode createNode(String str) {
        if(str.equals("null")) {
            return null;
        } else {
            String[] strings = str.split("->");
            ListNode node = new ListNode(Integer.parseInt(strings[0]));
            ListNode crt = node;
            for(int i = 1; i < strings.length; i++) {
                if(strings[i].equals("null")) {
                    crt.next = null;
                } else {
                    crt.next = new ListNode(Integer.parseInt(strings[i]));
                    crt = crt.next;
                }
            }
            return node;
        }
    }

    public static ListNode[] createNodeList(String[] strings) {
        int len = strings.length;
        ListNode[] listNodes = new ListNode[len];
        for(int i = 0; i < len; i++) {
            listNodes[i] = createNode(strings[i]);
        }
        return listNodes;
    }

    public static void printHash(ListNode[] listNodes) {
        int len = listNodes.length;
        String[] log = new String[len];
        for(int i = 0; i < len; i++) {
            ListNode node = listNodes[i];
            String str = "";
            while(node != null) {
                str += node.val + "->";
                node = node.next;
            }
            str += "null";
            log[i] = str;
        }
        for(int i = 0; i < len; i++) {
            if(i == 0) {
                System.out.print("[");
            }
            if(i != len -1) {
                System.out.print(log[i] + ", ");
            } else {
                System.out.println(log[i] + "]");
            }
        }
    }
}
