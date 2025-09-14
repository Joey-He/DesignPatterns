package interveiw.shunfeng2024qiu;

import java.io.*;
import java.util.Scanner;

public class Main1 {
    // 判断字符是否合法
    public static boolean cheak(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // 读取换行符
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = scanner.nextLine();
        }
        for(String str: strs){
            if(str.contains("_")){
                // 包含下划线
                boolean containsUpper = false;
                for(char c: str.toCharArray()){
                    if(Character.isUpperCase(c)){
                        containsUpper = true;
                        break;
                    }
                }
                if(containsUpper){
                    System.out.println("indistinct");
                }else{
                    if(str.charAt(0) == '_' || str.charAt(str.length() - 1) == '_' || str.contains("__")){
                        System.out.println("indistinct");
                    }else{
                        System.out.println(str);
                    }
                }
            }else{
                // 不包含下划线
                if(Character.isUpperCase(str.charAt(0))){
                    System.out.println("indistinct");
                }else{
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < str.length(); i++){
                        if(Character.isUpperCase(str.charAt(i))){
                            sb.append("_");
                            sb.append(Character.toLowerCase(str.charAt(i)));
                        }else{
                            sb.append(str.charAt(i));
                        }
                    }
                    System.out.println(sb);
                }
            }
        }
        scanner.close();
    }
}
