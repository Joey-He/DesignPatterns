package org.example.duoyi.dp.bag01;

import java.util.Scanner;
// 完全背包
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 物品数量
        int m = scanner.nextInt(); // 背包容量
        int[] values = new int[n];  // 物品体积
        int[] weigths = new int[n];  // 物品价值
        for (int i = 0; i < n; i++) {
            weigths[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }
        scanner.close();
        // 二维dp
        int[][] dp = new int[n][m+1];
        // 初始化dp
        for(int i = 0; i < n; i++){
            dp[i][0] = 0;
        }
        for(int j = weigths[0]; j <=m; j++){
            dp[0][j] = dp[0][j-weigths[0]] + values[0];
        }
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= m; j++){
                if(j < weigths[i]){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-weigths[i]] + values[i]);
                }
            }
        }
        System.out.println(dp[n-1][m]);
    }
}
