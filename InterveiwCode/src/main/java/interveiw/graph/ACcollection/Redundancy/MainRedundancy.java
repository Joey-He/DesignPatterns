package interveiw.graph.ACcollection.Redundancy;

import java.util.Scanner;

// 冗余连接 并查集
class Solution {

    private int[] parent;

    public  Solution(int n){
        this.parent = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            parent[i] = i;
        }
    }

    public int find(int u){
        if(parent[u] == u) return u;
        return  parent[u] = find(parent[u]);
    }

    public boolean isConnected(int v, int u){
        u = find(u);
        v = find(v);
        return u == v;
    }

    public void union(int v, int u){
        u = find(u);
        v = find(v);
        if(u != v){
            parent[u] = v;
        }
    }
}
public class MainRedundancy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 节点个数以及边的个数
        int n = scanner.nextInt();
        Solution solution = new Solution(n);
        for(int i = 0; i < n; i++){
            int v = scanner.nextInt();
            int u = scanner.nextInt();
            if(i == 0){
                solution.union(v,u);
                continue;
            }
            if(solution.isConnected(v,u)){
                System.out.println(v + " " + u);
            }else{
                solution.union(v,u);
            }
        }
    }
}
