package baidu2026qiu;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0){
            // 液体总类
            int n = scanner.nextInt();
            // 最多的结果改变次数
            int m = scanner.nextInt();
            // 液体的融值a[i]
            int[] a = new int[n];
            for(int i = 0; i < n; i++){
                a[i] = scanner.nextInt();
            }
            if(n == 0){
                System.out.println(0);
            }

            // 二分查找解决
            int minA = a[0], maxA = a[0];
            for(int num : a){
                minA = Math.min(minA, num);
                maxA = Math.max(maxA, num);
            }
            int left = 0, right = (maxA-minA)/2;
            int ans =right;
            while(left < right){
                int mid = left + (right - left + 1)/2;
                if(check(a, n, mid, m)){
                    ans = mid;
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            System.out.println(ans);
        }
        scanner.close();
    }
    public static boolean check(int[] a, int n, int mid, int m){
        int count = 0;
        for(int i = 0; i < n; i++){
            count += a[i]/mid;
        }
        return count <= m;
    }
}
