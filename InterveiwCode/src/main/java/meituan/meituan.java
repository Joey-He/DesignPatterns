package meituan;

import java.util.Scanner;
//代码解析 美团算法题
//状态定义：dp[x] 表示击败 x 只怪兽时的最大经验值，初始时仅 dp[0] = 0（未击败任何怪兽），其余状态为不可达（负无穷）。
//状态转移：
//放走怪兽：对于当前第 i 只怪兽，若选择放走，所有已有的状态（击败 x 只）的经验值都增加 i（直接累加）。
//击败怪兽：若选择击败，当前击败数量从 x 变为 x+1，经验值增加 a[i] * (1 + (x+1)%10)。此时需注意：需先减去 “放走时累加的 i”，再加上击败的经验（因两种选择互斥）。
//倒序更新：处理每只怪兽时，从 x = i-1 倒序遍历 dp 数组，避免当前轮的更新影响同轮未处理的状态（防止重复计算）。
//结果计算：遍历所有可能的击败数量（0 到 n），取 dp 数组中的最大值即为最大经验值。

public class meituan  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 怪兽数量
        int[] a = new int[n + 1]; // a[1..n] 存储每只怪兽的基础经验
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        // dp[x] 表示击败x只怪兽时的最大经验值
        // 最多击败n只怪兽，初始化为负无穷（除dp[0]）
        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Long.MIN_VALUE;
        }
        dp[0] = 0; // 初始状态：击败0只，经验0

        for (int i = 1; i <= n; i++) { // 依次处理第i只怪兽
            // 必须从x = i-1开始倒序更新，避免覆盖当前轮未处理的状态
            for (int x = i - 1; x >= 0; x--) {
                if (dp[x] == Long.MIN_VALUE) {
                    continue; // 该状态不可达，跳过
                }

                // 选择1：放走第i只怪兽，所有状态的经验值增加i
                dp[x] += i;

                // 选择2：击败第i只怪兽，更新x+1的状态
                int newX = x + 1;
                long gain = a[i] * (1 + (newX % 10));// 击败的经验值
                dp[newX] = Math.max(dp[newX], dp[x] - i + gain);
            }
        }

        // 最大经验值是所有可能击败数量中的最大值
        long maxExp = 0;
        for (long val : dp) {
            if (val > maxExp) {
                maxExp = val;
            }
        }
        System.out.println(maxExp);
    }
}