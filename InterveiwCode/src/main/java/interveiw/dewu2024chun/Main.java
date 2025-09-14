package interveiw.dewu2024chun;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }
        Arrays.sort(a);
        if(n == 2){
            System.out.println((a[1]- a[0]));
        }
        int max = 0;
        for(int i = n-1; i > 0; i--){
            if(i>=2){
                int curmax = Math.max(a[i] - a[i-1], a[i] - a[i-2]);
                max = Math.max(max, curmax);
            }else if(i == 1){
                max = Math.max(a[i] - a[i-1], max);
            }
        }
        System.out.println(max);
    }
}
