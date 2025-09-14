package interveiw.xiecheng2024chun;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.next();
        scanner.close();
        int y = 0, o = 0, u = 0;
        for(char a : n.toCharArray()){
            if(a == 'y'){
                y++;
            }else if(a == 'o'){
                o++;
            }else if(a == 'u'){
                u++;
            }
        }
        System.out.println(Math.min(y, Math.min(o, u)));
    }
}
