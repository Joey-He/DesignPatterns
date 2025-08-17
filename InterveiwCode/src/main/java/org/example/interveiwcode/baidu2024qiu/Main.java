package org.example.interveiwcode.baidu2024qiu;
// 题目地址
// https://kamacoder.com/problempage.php?pid=1357
import java.util.Scanner;

public class Main {
    static final int day = 24*3600;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strs = new String[n];
        int [] times = new int[n];
        for (int i = 0; i < n; i++){
            strs[i] = scanner.next();
        }
        scanner.close();
        // 讲时间字符串转换成秒数
        for(int i = 0; i < n; i++){
            String[] parts = strs[i].split(":");
            int seconds = Integer.parseInt(parts[0]) * 3600 + Integer.parseInt(parts[1]) * 60 + Integer.parseInt(parts[2]);
            times[i] = seconds;
        }
        for(int i = 1; i < n; i++){
            double ans = 0;
            int t = times[i] - times[i-1];
            if(t < 0){
                ans = (day + t)/60.0;
            }else{
                ans =t/60.0;
            }
            System.out.println(String.format("%.2f", ans));
        }
    }
}
