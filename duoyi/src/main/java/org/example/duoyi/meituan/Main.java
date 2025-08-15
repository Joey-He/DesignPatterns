package org.example.duoyi.meituan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //读取输入
        int N= scanner.nextInt(); //包裹数量
        int[] A = new int[N];
        for (int i = 0; i < N; i++){
            A[i] = scanner.nextInt();
        }
        int M = scanner.nextInt();
        int[] B = new int[M];
        for (int i = 0; i < M; i++){
            B[i] = scanner.nextInt();
        }

        int[] C = new int[M];
        for (int i = 0; i < M; i++){
            C[i] = scanner.nextInt();
        }
        // 包裹按位置升序排序
        Arrays.sort(A);
        // 站点按位置升序排序
        Integer[] statioins = new Integer[M];
        for(int i = 0; i < M; i++){
            statioins[i] = B[i];
        }
        Arrays.sort(statioins, Comparator.comparingInt(a -> B[a]));

        int[] sortedB = new int[M];
        int[] sortedC = new int[M];
        for(int i = 0; i < M; i++){
            int idx = statioins[i];
            sortedB[i] = B[idx];
            sortedC[i] = C[idx];
        }
        // 使用前缀数组计算费用
        long[] prefix = new long[N + 1];
        for(int i = 0; i < N; i++){
            prefix[i + 1] = prefix[i] + A[i];
        }
        // dp数组 ：dp[i] 表示处理前i个包裹的最小费用
        long[] dp = new long[N + 1];
        Arrays.fill(dp, Long.MAX_VALUE);

        for(int i = 1; i < N; i++){
            for(int k = 0; k < M; k++){
                int statioinpos = sortedB[k];

                int left = 0, rigth =i;
                while(left < rigth){
                    int mid = left + (rigth - left) / 2;
                    if(A[mid] < statioinpos){
                        left = mid + 1;
                    }else{
                        rigth = mid;
                    }
                }
                int j = left - 1;
                if(j < i -1){
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[j] + prefix[i] - prefix[j] + sortedC[k]);
            }
        }
    }
}
