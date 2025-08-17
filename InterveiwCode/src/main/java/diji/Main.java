package diji;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class UnionFind {
    int [] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY ){
            parent[find(y)] = find(x);
        }
    }
}

class Solution {
    /* Write Code Here */
    public String smallestStrWithSwaps(String s, int[][] pairs) {
        int len = s.length();
        // 初始化并查集
        UnionFind uf = new UnionFind(len);
        for(int[] pair : pairs){
            uf.union(pair[0], pair[1]);
        }
        // 按节点分组， 收集每一个组的索引和字符
        Map<Integer, List<Integer>> indexGroups = new HashMap<>();
        for(int i = 0; i < len; i++){
            int root = uf.find(i);
            indexGroups.computeIfAbsent(root, k -> new ArrayList<>()).add( i);
        }
        char[] arr = s.toCharArray();
        for(List<Integer> group : indexGroups.values()){
            List<Character> chars = new ArrayList<>();
            for(int index : group){
                chars.add(arr[index]);
            }
            Collections.sort(chars);
            for(int i = 0; i < group.size(); i++){
                arr[group.get(i)] = chars.get(i);
            }
        }
        return new String(arr);
    }
}

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String res;

        String s;
        try {
            s = in.nextLine();
        } catch (Exception e) {
            s = null;
        }

        int pairs_rows = 0;
        int pairs_cols = 0;
        pairs_rows = in.nextInt();
        pairs_cols = in.nextInt();

        int[][] pairs = new int[pairs_rows][pairs_cols];
        for(int pairs_i=0; pairs_i<pairs_rows; pairs_i++) {
            for(int pairs_j=0; pairs_j<pairs_cols; pairs_j++) {
                pairs[pairs_i][pairs_j] = in.nextInt();
            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }


        res = new Solution().smallestStrWithSwaps(s, pairs);
        System.out.println(res);
    }
}

