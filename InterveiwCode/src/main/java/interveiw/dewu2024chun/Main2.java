package interveiw.dewu2024chun;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }
        int[]dp = new int[m+1];
        for(int i = 0; i <= m; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = m; j >= a[i]; j--){
                if(dp[j-a[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - a[i]] + 1);
                }
            }
        }
        System.out.println(dp[m] == Integer.MAX_VALUE ? "No solution" : dp[m]);
    }
}
