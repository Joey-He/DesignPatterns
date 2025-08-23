package graph.islands.number;

import java.util.Scanner;
// 岛屿数量 -- dfs版本
public class IslandsNumberDFS {
    // 遍历方向
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void dfs(int[][] island, int x, int y, boolean[][] visited){
        for(int k = 0; k < 4; k++){
            int newX = x + dir[k][0];
            int newY = y + dir[k][1];
            if(newY<0||newX<0||newX>= island.length||newY>=island[0].length)
                continue;
            if(island[newX][newY] == 1 && visited[newX][newY] == false){
                visited[newX][newY] = true;
                dfs(island, newX, newY, visited);
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
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(island[i][j] == 1 && visited[i][j] == false){
                   ans++;
                   visited[i][j] = true;
                   dfs(island, i, j, visited);
                }
            }
        }
        System.out.println(ans);
    }
}
