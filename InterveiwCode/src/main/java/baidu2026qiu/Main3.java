package baidu2026qiu;

import java.util.Arrays;
import java.util.Scanner;

public class Main3 {
    static final int max = 1 << 20;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }
        if(n == 1){
            System.out.println(0);
            return;
        }
        long[] dp = new long[max];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        // dp[i] 表示前i个数中，选择一个数，使得这个数与i异或的结果最小
        for (int i = 0; i < n; i++){
            for (int j = 0; j < max; j++){
                dp[j] = Math.min(dp[j], dp[j ^ a[i]] + Math.abs(a[i] - j));
            }
        }
        System.out.println(dp[max - 1]);
    }
}
