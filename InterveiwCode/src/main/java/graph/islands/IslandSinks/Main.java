package graph.islands.IslandSinks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // //dfs版本
//    static void dfs(int[][] island, int x, int y, boolean[][] visited){
//        visited[x][y] = true;
//        for(int k = 0; k < 4; k++){
//            int newX = x + dir[k][0];
//            int newY = y + dir[k][1];
//            if(newX < 0 || newX >= island.length || newY < 0 || newY >= island[0].length || island[newX][newY] == 0) continue;
//            if(island[newX][newY] == 1 && !visited[newX][newY]){
//                dfs(island, newX, newY, visited);
//            }
//        }
//    }
    // bfs版本
    static void bfs(int[][] island, int x, int y, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] node = q.poll();
            int curX = node[0];
            int curY = node[1];
            for(int k = 0; k < 4; k++){
                int newX = curX + dir[k][0];
                int newY = curY + dir[k][1];
                if(newX < 0 || newX >= island.length || newY < 0 || newY >= island[0].length || island[newX][newY] == 0) continue;
                if(island[newX][newY] == 1 && !visited[newX][newY]){
                    q.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
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
        // 遍历上边和下边
        for(int j = 0; j < n; j++){
            if(island[0][j] == 1 ){
                bfs(island, 0, j, visited);
            }
            if(island[m-1][j] == 1){
                bfs(island, m-1, j, visited);
            }
        }
        // 遍历左边和右边
        for(int i = 0; i < m; i++){
            if(island[i][0] == 1){
                bfs(island, i, 0 ,visited);
            }
            if(island[i][n-1] == 1){
                bfs(island, i, n-1, visited);
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(island[i][j] == 1 && visited[i][j] == false){
                    island[i][j] = 0;
                    System.out.print(island[i][j] + " ");
                }else{
                    System.out.print(island[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
