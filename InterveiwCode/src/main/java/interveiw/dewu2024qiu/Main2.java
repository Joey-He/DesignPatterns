package interveiw.dewu2024qiu;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] red = new int[n];
        int[] ple = new int[n];
        for(int i = 0; i < n; i++){
            red[i] = scanner.nextInt();
        }
        for(int i = 0; i < n; i++){
            ple[i] = scanner.nextInt();
        }
        scanner.close();
        int[] d = new int[n + 1]; // 额外加一个位置作为哨兵
        d[0] = 1;

        for (int i = 1; i < n; i++) {
            int t1 = red[i] - red[i - 1];
            int t2 = ple[i] - ple[i - 1];
            d[i] = (t1 == t2) ? 1 : 0;
        }

        d[n] = 0; // 哨兵，保证最后一个是不等的
        int ans = 1;
        int j = 0;

        for (int i = 0; i <= n; i++) {
            if (d[i] == 0) {
                ans = Math.max(ans, i - j);
                j = i;
            }
        }
        // 使用PrintWriter优化输出
        PrintWriter out = new PrintWriter(System.out);
        out.println(ans);
        out.flush();
    }
}
