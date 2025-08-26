package graph.islands.BuildIsland;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MainDFS {
    static int area;
    static int mark;
    static int[][] dir = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static void dfs(int[][] island, int x, int y, boolean[][] visited) {
//        // 当遇到边界，直接return
//        if (x < 0 || x >= island.length || y < 0 || y >= island[0].length) return;
//        // 遇到已经访问过的或者遇到海水，直接返回
//        if (visited[x][y] || island[x][y] == 0) return;
//
//        visited[x][y] = true;
//        area++;
//        island[x][y] = mark;
//
//        // 继续向下层搜索
//        dfs(island, x, y + 1, visited);
//        dfs(island, x, y - 1, visited);
//        dfs(island, x + 1, y, visited);
//        dfs(island, x - 1, y, visited);
        area++;
        visited[x][y] = true;
        island[x][y] = mark;
        for(int k = 0; k < 4; k++){
            int newX = x + dir[k][0];
            int newY = y + dir[k][1];
            if(newY<0||newX<0||newX>= island.length||newY>=island[0].length)
                continue;
            if (!visited[newX][newY] && island[newX][newY] == 1){
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
        // 定义一个HashMap，记录某片岛屿的标记号和面积
        HashMap<Integer, Integer> getSize = new HashMap<>();
        // 定义一个HashSet，用来判断某一位置水四周是否存在不同标记编号的岛屿
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                island[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        mark = 2;
        // 定义一个boolean变量，看看DFS之后，是否全是岛屿
        boolean isAllIsland = true;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if (island[i][j] == 0) isAllIsland = false;
                if(island[i][j] == 1){
                    area = 0;
                    dfs(island, i, j, visited);
                    getSize.put(mark, area);
                    mark++;
                }
            }
        }
        int result = 0;
        if (isAllIsland) result =  m * n;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(island[i][j] == 0){
                    set.clear();
                    int curSize = 1;
                    for(int k = 0; k < 4; k++){
                        int newX = i + dir[k][0];
                        int newY = j + dir[k][1];
                        if(newY<0||newX<0||newX>= island.length||newY>=island[0].length)
                            continue;
                        int curmark = island[newX][newY];
                        if (set.contains(curmark) || !getSize.containsKey(curmark)) continue;
                        curSize += getSize.get(curmark);
                        set.add(curmark);
                    }
                    result = Math.max(result, curSize);
                }
            }
        }
        System.out.println( result);
    }
}
