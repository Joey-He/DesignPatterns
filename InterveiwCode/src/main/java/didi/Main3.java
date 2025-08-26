package didi;

import java.util.*;

public class Main3 {
    static int solve(int[] a){
        int opNum = 0;
        while(true){
            int max = -1;
            int index = -1;
            for(int i = 1; i < a.length; i++){
                if(a[i] > max){
                    max = a[i];
                    index = i;
                }
            }
            if(max < a[0]){
                break;
            }
            int x = max/2;
            a[0] += x;
            a[index] -= x;
            opNum++;
        }
        return opNum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int m = sc.nextInt();
            int[] a = new int[m];
            for(int j = 0; j < m; j++){
                a[j] = sc.nextInt();
            }
            System.out.println(solve(a));
        }
    }
}
