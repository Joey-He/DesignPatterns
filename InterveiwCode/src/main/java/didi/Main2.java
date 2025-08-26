package didi;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入地图尺寸（N行N列，若为非正方形，可改为输入行数m和列数n）
        System.out.print("请输入地图的行数（m）和列数（n）：");
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        // 1-based 数组存储宝藏价值（避免处理0索引边界）
        int[][] treasure = new int[m + 1][n + 1];
        System.out.println("请按行输入宝藏价值（共" + m + "行，每行" + n + "个数字）：");
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                treasure[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        // DP数组定义：dp[i][j][0]未带当前宝藏，dp[i][j][1]带当前宝藏
        // 初始值设为-1表示“不可达”（因宝藏价值非负，-1可区分无效状态）
        int[][][] dp = new int[m + 1][n + 1][2];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j][0] = dp[i][j][1] = -1;
            }
        }

        // 1. 初始化起点(1,1)：两种初始状态（未带/带宝藏）
        dp[1][1][0] = 0;                  // 未带走(1,1)宝藏，价值0
        dp[1][1][1] = treasure[1][1];     // 带走(1,1)宝藏，价值为该房间宝藏值

        // 2. 处理第一行（i=1，仅能右走，因无上方房间）
        for (int j = 2; j <= n; j++) {
            // 第一行只能从左侧右走到达，需满足“左侧带宝藏”（右走规则）
            if (dp[1][j - 1][1] != -1) {
                // 右走带走当前房间(j)的宝藏，价值=左侧带宝藏价值 + 当前宝藏
                dp[1][j][1] = dp[1][j - 1][1] + treasure[1][j];
            }
            // 第一行无法下走，dp[1][j][0]（未带当前宝藏）始终不可达，保持-1
        }

        // 3. 处理第一列（j=1，仅能下走，因无左侧房间）
        for (int i = 2; i <= m; i++) {
            // 第一列只能从上方下走到达，需满足“上方未带宝藏”（下走规则）
            if (dp[i - 1][1][0] != -1) {
                // 下走不带走当前房间(i)的宝藏，价值=上方未带宝藏价值
                dp[i][1][0] = dp[i - 1][1][0];
            }
            // 第一列无左侧房间，dp[i][1][1]（带当前宝藏）始终不可达，保持-1
        }

        // 4. 处理其他房间（i≥2，j≥2，可下走或右走）
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                // 计算dp[i][j][0]（未带当前宝藏）：仅能从上方下走到达
                if (dp[i - 1][j][0] != -1) {
                    dp[i][j][0] = dp[i - 1][j][0];
                }

                // 计算dp[i][j][1]（带当前宝藏）：仅能从左侧右走到达
                if (dp[i][j - 1][1] != -1) {
                    dp[i][j][1] = dp[i][j - 1][1] + treasure[i][j];
                }
            }
        }

        // 5. 遍历所有房间，找到最大宝藏价值（所有可达状态中的最大值）
        int maxValue = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 取当前房间“未带”和“带”宝藏的最大值（排除不可达状态-1）
                int currentMax = Math.max(
                        dp[i][j][0] == -1 ? 0 : dp[i][j][0],
                        dp[i][j][1] == -1 ? 0 : dp[i][j][1]
                );
                if (currentMax > maxValue) {
                    maxValue = currentMax;
                }
            }
        }

        System.out.println("能带走的最大宝藏价值为：" + maxValue);
    }
}
