package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
// 临接表 -- 求图起始点到终点的所有路径
public class Maindfs2 {
    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    static void dfs(List<LinkedList<Integer>> graph, int start, int end) {
        if (start == end) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int next : graph.get(start)) {
            //如果图中有环
            if(!path.contains(next)) {
                path.add(next);
                dfs(graph, next, end);
                path.remove(path.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<LinkedList<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <=  n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            graph.get(x).add(y);
        }
        path.add(1);
        dfs(graph, 1, n);
        if (result.isEmpty()) System.out.println(-1);
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size() - 1));
        }
    }
}
