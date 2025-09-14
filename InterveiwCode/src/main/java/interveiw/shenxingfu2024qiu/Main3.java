package interveiw.shenxingfu2024qiu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
// 完全背包问题
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] woodlen = new int[n];
        for (int i = 0; i < n; i++){
            woodlen[i] = scanner.nextInt();
        }
        int[] dp = new int[m+1];
        for(int i = 0; i <= m; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = woodlen[i]; j <= m; j++){
                if(dp[j-woodlen[i]] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j-woodlen[i]]+1);
                }
            }
        }
        System.out.println(dp[m] == Integer.MAX_VALUE ? -1 : dp[m]);
    }
}
