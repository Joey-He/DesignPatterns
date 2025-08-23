package graph.islands.totalArea;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 孤岛面积 -- dfs版本 and bfs版本
public class TotalArea {
    // 遍历方向
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int area = 0;
//    // dfs
//    public static void dfs(int[][] island, int x, int y){
//        island[x][y] = 0;
//        for(int k = 0; k < 4; k++){
//            int newX = x + dir[k][0];
//            int newY = y + dir[k][1];
//            if(newY<0||newX<0||newX>= island.length||newY>=island[0].length)
//                continue;
//            if(island[newX][newY] == 1){
//                dfs(island, newX, newY);
//            }
//        }
//    }
private static void bfs(int[][] grid, int x, int y) {
    Queue<int[]> que = new LinkedList<>();
    que.offer(new int[]{x, y});
    grid[x][y] = 0; // 只要加入队列，立刻标记
    while (!que.isEmpty()) {
        int[] cur = que.poll();
        int curx = cur[0];
        int cury = cur[1];
        for (int i = 0; i < 4; i++) {
            int nextx = curx + dir[i][0];
            int nexty = cury + dir[i][1];
            if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue; // 越界了，直接跳过
            if (grid[nextx][nexty] == 1) {
                que.offer(new int[]{nextx, nexty});
                grid[nextx][nexty] = 0; // 只要加入队列立刻标记
            }
        }
    }
}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] island = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                island[i][j] = scanner.nextInt();
            }
        }
        // 遍历上边和下边
        for(int j = 0; j < n; j++){
            if(island[0][j] == 1 ){
                bfs(island, 0, j);
            }
            if(island[m-1][j] == 1){
                bfs(island, m-1, j);
            }
        }
        // 遍历左边和右边
        for(int i = 0; i < m; i++){
            if(island[i][0] == 1){
                bfs(island, i, 0);
            }
            if(island[i][n-1] == 1){
                bfs(island, i, n-1);
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(island[i][j] == 1){
                    area++;
                }
            }
        }
        System.out.println(area);
    }
}