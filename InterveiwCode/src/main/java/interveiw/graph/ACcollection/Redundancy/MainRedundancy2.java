package interveiw.graph.ACcollection.Redundancy;

import java.util.*;

// 冗余连接2
public class MainRedundancy2 {
    /**
     * 并查集模板
     */
    static class Disjoint {

        private final int[] father;

        public Disjoint(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        public void join(int n, int m) {
            n = find(n);
            m = find(m);
            if (n == m) return;
            father[n] = m;
        }

        public int find(int n) {
            return father[n] == n ? n : (father[n] = find(father[n]));
        }

        public boolean isSame(int n, int m) {
            return find(n) == find(m);
        }
    }
    // 有向图的边
    static class Edge {
        int u;
        int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    // 有向图的节点
    static class Node {
        int id;
        int in;
        int out;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Edge> edges= new ArrayList<>();
        Node[] nodeMap = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodeMap[i] = new Node();
        }
        Integer doubleIn = null;
        for(int i = 0; i < n; i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            nodeMap[v].in++;
            edges.add(new Edge(u,v));
            if(!(nodeMap[v].in < 2)){
                doubleIn = v;
            }
        }
        // 存在度为2度点
        Edge result = null;
        if(doubleIn != null){
            List<Edge> doubleInEdges = new ArrayList<>();
            for(Edge edge : edges){
                if(edge.v == doubleIn){
                    doubleInEdges.add(edge);
                }
                if(doubleInEdges.size() == 2) break;
            }
            Edge edge = doubleInEdges.get(1);
            if(isTree(edges, edge, nodeMap)){
                result = edge;
            } else {
                result = doubleInEdges.get(0);
            }
        }else{
            // 如果不存在度为2的点， 表示存在一个有向环
                result = getRemoveEdge(edges, nodeMap);
        }
        System.out.println(result.u + " " + result.v);
    }
    // 删除一条边后 判断是否为有向树
    static boolean isTree(List<Edge> edges, Edge edge, Node[] nodeMap){
        Disjoint disjoint = new Disjoint(nodeMap.length + 1);
        for(Edge e : edges){
            if(e == edge) continue;
            if(disjoint.isSame(e.u, e.v)){
                return false;
            }
            disjoint.join(e.u, e.v);
        }
        return true;
    }
    // 没有度入度为2的节点
    static Edge getRemoveEdge(List<Edge> edges, Node[] nodeMap){
        Disjoint disjoint = new Disjoint(nodeMap.length);
        for(Edge e : edges){
            if(disjoint.isSame(e.u, e.v)){
                return e;
            }
            disjoint.join(e.u, e.v);
        }
        return null;
    }
}
