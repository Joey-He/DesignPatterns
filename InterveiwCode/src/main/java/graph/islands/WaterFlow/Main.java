package graph.islands.WaterFlow;

import java.util.Scanner;

public class Main {
    static boolean up = false;
    static boolean down = false;
    static int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static void bfs(int[][] island, int x, int y, boolean[][] visited){
        for(int i = 0; i < 4; i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextX < 0 || nextY < 0){
                up = true;
                continue;
            }
            if(nextX >= island.length || nextY >= island[0].length){
                down = true;
                continue;
            }
//            if(up && down){
//                return;
//            }
            if(island[nextX][nextY] <= island[x][y] && !visited[nextX][nextY]){
                visited[nextX][nextY] = true;
                bfs(island, nextX, nextY, visited);
                visited[nextX][nextY] = false;
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
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                    up = false;
                    down = false;
                    bfs(island, i, j, visited);
                    if(up && down){
                        System.out.println(i + " " + j);
                    }
            }
        }
    }
}
