package org.example.duoyi.dp.bag01;

import java.util.Scanner;

public class Main {
    public  static void main(String[] args){
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
        // 一位滚动数组
        int[] dp = new int[m+1];
        for(int i = 0; i < n; i++){
            for(int j = m; j>=weigths[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - weigths[i]]+values[i]);
            }
        }
        System.out.println(dp[m]);
        // 二维dp数组
//        int[][] dp = new int[n][m+1];
//        // 对dp进行初始化
//        for(int i = 0; i < n; i++){
//            dp[i][0] = 0;
//        }
//        for(int j = weigths[0]; j <=m; j++){
//            dp[0][j] = values[0];
//        }
//        // 先遍厉物品 再遍厉背包容量
//        for(int i = 1; i < n; i++){
//            for(int j = 1; j <= m; j++){
//                if(j < weigths[i]){
//                    dp[i][j] = dp[i-1][j];
//                }else{
//                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weigths[i]] + values[i]);
//                }
//            }
//        }
//        System.out.println(dp[n-1][m]);
    }
}
