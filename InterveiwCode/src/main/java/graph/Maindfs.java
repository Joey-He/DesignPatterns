package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// 临接矩阵--求图起始点到终点的所有路径
public class Maindfs {
    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    public static void dfs(int[][] graph, int start, int end){
        if(start == end){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 1; i <= graph.length; i++){
            if(graph[start][i] == 1 && !path.contains(i)){
                path.add(i);
                dfs(graph, i, end);
                path.remove(path.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] graph = new int[n+1][n+1];
        for(int i = 0; i < m; i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            graph[x][y] = 1;
        }
        path.add(1);
        dfs(graph, 1, n);
        if(result.isEmpty()){
            System.out.println(-1);
        }
        for(List<Integer> list : result){
            for(int i = 0; i < list.size() - 1; i++){
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size()-1));
        }
    }
}
