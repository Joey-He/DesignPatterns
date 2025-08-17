package xiaomi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取题目总和M参数选手总数N
        int N = scanner.nextInt();
        int M  = scanner.nextInt();


        // 储存每位选手的答题范围[L, R]
        int[][] players = new int[N][2];
        for (int i = 0; i < N; i++) {
            players[i][0] = scanner.nextInt();  //L
            players[i][1] = scanner.nextInt(); //  R
        }
        scanner.close();

        int max = 0;
        // 遍厉所有可能的分割点k(1 <=k < M)
        for(int x = 1; x <= M; x++){
            for(int y = x; y < M; y++){
                int count = 0;
                for(int[] player : players){
                    int L = player[0];
                    int R = player[1];
                    // 覆盖第一轮 （1-k) : 选手L <= 1) && (R >= k）
                    if((L <= x) && (R >= y)){
                        count++;
                    }
                }
                max = Math.max(max, count);
            }
        }
        System.out.println(max);
    }
}
