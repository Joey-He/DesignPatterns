package graph.islands;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 岛屿数量 -- bfs
public class IslandsNumberDFS {
    // 遍历方向
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void bfs(int[][] island, int x, int y, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int curX = node[0];
            int curY = node[1];
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dir[i][0];
                int nextY = curY + dir[i][1];
                if (nextX < 0 || nextX >= island.length || nextY < 0 || nextY >= island[0].length)
                    continue;
                if (!visited[nextX][nextY] && island[nextX][nextY] == 1) {
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }//定义坐标队列
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] island = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                island[i][j] = scanner.nextInt();
                visited[i][j] = false;
            }
        }
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(island[i][j] == 1 && visited[i][j] == false){
                    ans++;
                    visited[i][j] = true;
                    bfs(island, i, j, visited);
                }
            }
        }
        System.out.println(ans);
    }
}
