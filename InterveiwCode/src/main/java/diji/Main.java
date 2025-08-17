package diji;

import java.util.*;


class UnionFind {
    int [] parent; // 存储每个节点的父节点

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 初始化：每个节点的父节点是自己
        }
    }
    // 查找节点x的根节点（带路径压缩，优化查询效率）
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // 递归找到根节点，并压缩路径
        }
        return parent[x];
    }
    // 合并节点x和y所在的分量
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY ){
            parent[rootY] = rootX; // 将y的根节点指向x的根节点，实现合并
        }
    }
}

class Solution {
    /* Write Code Here */
    public String smallestStrWithSwaps(String s, int[][] pairs) {
        int len = s.length();
        // 第一步：合并所有关联的索引（构建连通分量）
        UnionFind uf = new UnionFind(len);
        for(int[] pair : pairs){
            uf.union(pair[0], pair[1]); // 合并索引a和b所在的分量
        }
        // 第二步：按连通分量分组（同一组的索引可互换）
        Map<Integer, List<Integer>> indexGroups = new HashMap<>();
        for(int i = 0; i < len; i++){
            int root = uf.find(i);  // 找到当前索引的根节点（代表连通分量）
            // 按根节点分组，收集同一分量的所有索引
            indexGroups.computeIfAbsent(root, k -> new ArrayList<>()).add( i);
        }
        // 第三步：对每个组内的字符排序，得到最小字符串
        char[] arr = s.toCharArray(); // 将字符串转为字符数组（便于修改）
        for(List<Integer> group : indexGroups.values()){
            // 提取组内所有字符
            List<Character> chars = new ArrayList<>();
            for(int index : group){
                chars.add(arr[index]);
            }
            // 字符按字典序排序
            Collections.sort(chars);

            // 将排序后的字符放回原索引位置
            for(int i = 0; i < group.size(); i++){
                arr[group.get(i)] = chars.get(i);
            }
        }
        return new String(arr); // 转换为字符串返回
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

