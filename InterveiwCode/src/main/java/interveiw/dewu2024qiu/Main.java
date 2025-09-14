package interveiw.dewu2024qiu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = new String();
        String m = new String();
        n = scanner.next();
        m = scanner.next();
        int res = 0;
        for(int i = 0; i < 4; i++){
            int q = n.charAt(i);
            int p = m.charAt(i);
            if(q == p){
                continue;
            }else if(q > p){
                res += q-p;
            }else{
                res += 10 - q + p;
            }
        }
        System.out.println(res);
    }
}
