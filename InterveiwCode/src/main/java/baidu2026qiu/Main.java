package baidu2026qiu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//斐波拉契数列 ，计算第r到第l项出现多少个奇数,使用动态规划dp[i]表示0到i有多少个奇数
public class Main {
//    public static int getOddCount(int[] fib,int l, int r){
//        int count = 0;
//        for (int i = l; i <= r; i++){
//            if (fib[i] % 2 != 0){
//                count++;
//            }
//        }
//        return count;
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max = 0;
        for(int i = 0; i < n; i++){
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            if (r > max) {
                max = r;
          }
        }
        int[] dp = new int[max+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= max; i++){
            dp[i] = dp[i-1] + dp[i-2];

        }
//        int max = 0;
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            int l = scanner.nextInt();
//            int r = scanner.nextInt();
//            if (r > max) {
//                max = r;
//            }
//            list.add(l);
//            list.add(r);
//        }
//        int[] fib = new int[max+1];
//        fib[0] = 0;
//        fib[1] = 1;
//        for (int i = 2; i <= max; i++){
//            fib[i] = fib[i-1] + fib[i-2];
//        }
//        for(int i = 0; i < list.size(); i+=2) {
//            System.out.println(getOddCount(fib, list.get(i), list.get(i+1)));
//        }
    }
}
