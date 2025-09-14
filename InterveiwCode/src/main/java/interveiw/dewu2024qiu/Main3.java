package interveiw.dewu2024qiu;
import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str = scanner.next();
        int ans = 0, num = 0;
        for(int i = 0; i < n; i++){
            if(str.charAt(i) == ')'){
                num--;
                if(num == 0){
                    ans = Math.max(ans, i+1);
                }else if(num < 0){
                    System.out.println(i);
                    ArrayList<Object> list = new ArrayList<>();
                    list.forEach(item -> System.out.println(item));
                    return;

                }
            }else{
                num++;
            }
        }
        System.out.println( ans);
        // 定义一个存储（ ）的栈 答案正确 结果超时
//        Stack<String> stack = new Stack<>();
//        int count = 0;
//        int ans = 0;
//        for(String s : str.split("")){
//            if(s.equals("(")){
//                stack.push(s);
//            }else if(s.equals(")")){
//                if(stack.isEmpty()){
//                    break;
//                }
//                stack.pop();
//                count = count + 2;
//                if(stack.isEmpty()){
//                    ans = ans + count;
//                    count = 0;
//                }
//            }
//        }
//        System.out.println(ans);
    }
}
