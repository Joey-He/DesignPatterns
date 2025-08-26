package graph.ACcollection;

import java.util.Scanner;

// 图中寻找寻找存在的路径
class ACcollection {

    int[] parent;
    /**
     * 构造函数
     * @param n
     */
    public ACcollection(int n){
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i; // 初始化：每个节点的父节点是自己
        }
    }
    /**
     * 查找
     * @param u
     * @return
     */
    public int find(int u){
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }
    /**
     * 讲v的根节点指向u的根节点 合并
     * @param u
     * @param v
     */
    public void union(int u, int v){
        u = find(u);
        v = find(v);
        if(u == v) return;
        parent[v] = u;
    }
    public boolean isConnected(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //代表n个节点 m条边
        int n = sc.nextInt();
        int m = sc.nextInt();
        ACcollection ac = new ACcollection(n);
        int[][] edges = new int[m][2];
        for(int i = 0; i < m; i++){
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            ac.union(edges[i][0], edges[i][1]);
        }
        int q = sc.nextInt();
        int p = sc.nextInt();
        if(ac.isConnected(p, q)){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
}
