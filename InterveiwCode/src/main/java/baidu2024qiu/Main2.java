package baidu2024qiu;

import java.util.Scanner;

public class Main2 {
    static void getop(double a, double b) {
        if (a > b) System.out.println(">"); ;
        if (a < b) System.out.println("<");;
        if (a == b) System.out.println("=");;
    }
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 0; i < n; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            String op = scanner.next();
            switch (op){
                case "+":
                    System.out.println("=");
                    break;
                case "-": getop(a,b);
                    break;
                case "*":
                    System.out.println("=");
                    break;
                case "/": getop(a,b);
                    break;
                case "^":
                    if(Math.pow(a,b) - Math.pow(b,a) > 0){
                        System.out.println(">");
                    }else if(Math.pow(a,b) - Math.pow(b,a) < 0){
                        System.out.println("<");
                    }else{
                        System.out.println("=");
                    }
                    break;
                default:
                    System.out.println("Invalid operator.");
                    break;
            }
        }
    }
}
