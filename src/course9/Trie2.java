package algorithmbasic.class9;

import java.util.HashMap;

public class Trie2 {
    // 内部类
    public static class Node {
        int pass;
        int end;
        HashMap<Integer, Node> map;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.map = new HashMap<>();
        }
    }

    // 属性：
    static Node root;

    // 构造器：
    public Trie2() {
        root = new Node();
    }

    // 方法：
    public static void insert(String word) {
        if (word == null) {
            return;
        }
        char[] str = word.toCharArray();
        Node cur = root;
        cur.pass++;
        for (char c : str) {
            int path = c;
            if (cur.map.get(path) == null) {
                cur.map.put(path, new Node());
            }
            cur = cur.map.get(path);
            cur.pass++;
        }
        cur.end++;
    }

    public static int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] str = word.toCharArray();
        Node cur = root;
        for (char c : str) {
            int path = c;
            if (!cur.map.containsKey(path)) {
                return 0;
            }
            cur = cur.map.get(path);
        }
        return cur.end;
    }

    public static int profixNumber(String line) {
        if (line == null) {
            return 0;
        }
        char[] str = line.toCharArray();
        Node cur = root;
        for (int i = 0; i < str.length; i++) {
            int path = str[i];
            if (!cur.map.containsKey(path)) {
                return 0;
            }
            cur = cur.map.get(path);
        }
        return cur.pass;
    }

    public static void remove(String line) {
        if (search(line) == 0) {
            return;
        }
        char[] str = line.toCharArray();
        Node cur = root;
        cur.pass--;
        for (int i = 0; i < str.length; i++) {
            int path = str[i];
            if (--cur.map.get(path).pass == 0) {
                cur.map.remove(path);
                return;
            }
            cur = cur.map.get(path);
            cur.pass--;
        }
        cur.end--;
    }


    public static void main(String[] args) {
        int testTime = 10000;
        int maxArrayLength = 100;
        int maxString = 100;
        int i = 0;
        for (i = 0; i < testTime; i++) {
            System.out.println("test begin");
            // 随机产生一个字符串数组
            String[] stringLine = prodectString(maxArrayLength, maxString);
            Trie trie1 = new Trie();
            Trie2 trie2 = new Trie2();
            double decide = Math.random();
            if (decide < 0.25) {
                for (int j = 0; j < 5; j++) {
                    int k = (int) (Math.random() * stringLine.length); // [0 , stringLine.length - 1]
                    System.out.println(k);
                    System.out.println(stringLine.length);
                    trie1.insert(stringLine[k]);
                    trie2.insert(stringLine[k]);
                }
            } else if (decide < 0.5) {
                int k = (int) (Math.random() * stringLine.length); // [0 , stringLine.length - 1]
                trie1.remove(stringLine[k]);
                trie2.remove(stringLine[k]);
                System.out.println(stringLine.length);
                System.out.println(k);
            } else if (decide < 0.75) {
                int x = (int) (Math.random() * 3 + 2);
                for (int j = 0; j < x; j++) {
                    int k = (int) (Math.random() * stringLine.length); // [0 , stringLine.length - 1]
                    int m = (int) (Math.random() * stringLine[k].length()); // [0,line-1]
                    String pre = stringLine[k].substring(0, m);
                    int n1 = trie1.prefixNumber(pre);
                    int n2 = trie2.profixNumber(pre);
                    if (n1 != n2) {
                        System.out.println("00ps");
                        System.out.println(stringLine[k]);
                        System.out.println(pre);
                        break;
                    }
                }
            } else {
                int x = (int) (Math.random() * 2 + 3);
                for (int j = 0; j < x; j++) {
                    int k = (int) (Math.random() * stringLine.length); // [0 , stringLine.length - 1]
                    System.out.println(k);
                    System.out.println(stringLine.length);
                    if (trie1.search(stringLine[k]) != trie2.search(stringLine[k])) {
                        System.out.println("oops");
                        break;
                    }
                }
            }
        }
        if(i == testTime) {
            System.out.println("great");
        }else {
            System.out.println("fuck");
        }
    }

    public static String[] prodectString(int maxArrayLength, int maxString) {
        int arraySize = (int) (Math.random() * maxArrayLength + 1); // [1,10]
        String[] stringLine = new String[arraySize];
        for (int i = 0; i < stringLine.length; i++) {
            int StringLength = (int) (Math.random() * maxString); // [0,9]
            // [97 , 122] a - z
            char[] ch = new char[StringLength];
            for (int j = 0; j < ch.length; j++) {
                ch[j] = (char) ((int) (Math.random() * 26 + 97));
            }
            stringLine[i] = new String(ch);
        }
        return stringLine;
    }
}
