package didi;

import java.util.*;
// 带走宝藏
public class Main {
    static void solve(int[][] a, int n){
        // dp[i][j][0] 表示带走宝藏，dp[i][j][1]表示不带走宝藏
        long[][][] dp = new long[n+1][n+1][2];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j][0] = dp[i][j][1] =-1;
            }
        }
        // 1. 初始化起点(1,1)：两种初始状态（未带/带宝藏）
        dp[1][1][0] = 0;                  // 未带走(1,1)宝藏，价值0
        dp[1][1][1] = a[1][1];     // 带走(1,1)宝藏，价值为该房间宝藏值

        // 2. 处理第一行（i=1，仅能右走，因无上方房间）
        for (int j = 2; j <= n; j++) {
            // 第一行只能从左侧右走到达，需满足“左侧带宝藏”（右走规则）
            if (dp[1][j - 1][1] != -1) {
                // 右走带走当前房间(j)的宝藏，价值=左侧带宝藏价值 + 当前宝藏
                dp[1][j][1] = dp[1][j - 1][1] + a[1][j];
            }
            // 第一行无法下走，dp[1][j][0]（未带当前宝藏）始终不可达，保持-1
        }

        // 3. 处理第一列（j=1，仅能下走，因无左侧房间）
        for (int i = 2; i <= n; i++) {
            // 第一列只能从上方下走到达，需满足“上方未带宝藏”（下走规则）
            if (dp[i - 1][1][0] != -1) {
                // 下走不带走当前房间(i)的宝藏，价值=上方未带宝藏价值
                dp[i][1][0] = dp[i - 1][1][0];
            }
            // 第一列无左侧房间，dp[i][1][1]（带当前宝藏）始终不可达，保持-1
        }

        // 4. 处理其他房间（i≥2，j≥2，可下走或右走）
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                // 计算dp[i][j][0]（未带当前宝藏）：仅能从上方下走到达
                if (dp[i - 1][j][0] != -1) {
                    dp[i][j][0] = dp[i - 1][j][0];
                }

                // 计算dp[i][j][1]（带当前宝藏）：仅能从左侧右走到达
                if (dp[i][j - 1][1] != -1) {
                    dp[i][j][1] = dp[i][j - 1][1] + a[i][j];
                }
            }
        }

        // 5. 遍历所有房间，找到最大宝藏价值（所有可达状态中的最大值）
        long maxValue = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 取当前房间“未带”和“带”宝藏的最大值（排除不可达状态-1）
                long currentMax = Math.max(
                        dp[i][j][0] == -1 ? 0 : dp[i][j][0],
                        dp[i][j][1] == -1 ? 0 : dp[i][j][1]
                );
                if (currentMax > maxValue) {
                    maxValue = currentMax;
                }
            }
        }

        System.out.println("能带走的最大宝藏价值为：" + maxValue);
//        // 初始化dp
//        dp[1][1][0] = 0;
//        dp[1][1][1] = a[1][1];
//
//        for(int j =2; j <= n; j++){
//            if(dp[1][j-1][1] != -1){
//                dp[1][j][1] = dp[1][j-1][1] + a[1][j];
//            }
//        }
//        for(int i = 2; i <= n; i++){
//            if(dp[i-1][1][0] != -1){
//                dp[i][1][0] = dp[i-1][1][0];
//            }
//        }
//
//        for(int i = 2; i <= n; i++){
//            for(int j = 2; j <= n; j++){
//                if(dp[i-1][j][0] != -1){
//                    dp[i][j][0] = dp[i-1][j][0];
//                }
//                if(dp[i][j-1][1] != -1){
//                    dp[i][j][1] = dp[i][j-1][1] + a[i][j];
//                }
//            }
//        }
//        long max = 0;
//        for(int i = 1; i <= n; i++){
//            for(int j = 1; j <= n; j++){
//                long curmax = Math.max(
//                        dp[i][j][0] == -1 ? 0 : dp[i][j][0],
//                        dp[i][j][1] == -1 ? 0 : dp[i][j][1]
//                );
//                if(curmax > max){
//                    max = curmax;
//                }
//            }
//        }
//        System.out.println(max);

//        for(int i = 2; i <= n; i++){
//            for(int j = 2; j <= n; j++){
//                // 跳过初始位置
//                    if(i == 1 && j == 1) continue;
//                // 不带走宝藏
//                if(i > 1 && dp[i-1][j][0] != Long.MIN_VALUE){
//                    dp[i][j][0] = dp[i-1][j][0];
//                }
//                // 带走宝藏 从上方来的
//                long max = Long.MIN_VALUE;
//                if(i > 1 && dp[i-1][j][0] != Long.MIN_VALUE){
//                    max = Math.max(max, dp[i-1][j][0]);
//                }
//                // 带走宝藏 从左方来的
//                if(j > 1 && dp[i][j-1][1] != Long.MIN_VALUE){
//                    max = Math.max(max, dp[i][j-1][1]);
//                }
//                if(max != Long.MIN_VALUE){
//                    dp[i][j][1] = max + a[i][j];
//                }
//            }
//        }
//        long ans = 0;
//        for(int i = 1; i <= n; i++){
//            if(dp[i][n][0] > ans) ans = Math.max(ans, dp[i][n][0]);
//            if(dp[i][n][1] > ans) ans = Math.max(ans, dp[i][n][1]);
//        }
//        for(int j = 1; j <= n; j++){
//            if(dp[n][j][0] > ans) ans = Math.max(ans, dp[n][j][0]);
//            if(dp[n][j][1] > ans) ans = Math.max(ans, dp[n][j][1]);
//        }
//        System.out.println(ans);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int m = sc.nextInt();
            int[][] a = new int[m+1][m+1];
            for(int j = 1; j <= m; j++){
                for(int k = 1; k <= m; k++){
                    a[j][k] = sc.nextInt();
                }
            }
            solve(a,n);
        }
        sc.close();
    }
}
