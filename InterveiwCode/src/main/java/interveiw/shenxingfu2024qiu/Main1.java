package interveiw.shenxingfu2024qiu;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str = scanner.next();
        if(n == 1 || str.isEmpty()){
            System.out.println(0);
        }
        int ans = 0;
        for(int i = 1; i < n; i++){
            if(str.charAt(i) == str.charAt(i-1)){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
