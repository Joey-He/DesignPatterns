package interveiw.shenxingfu2024qiu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    public static boolean cheak(String s){
        if(s.isEmpty() || s.length() > 63){
            return false;
        }
        if(s.charAt(0) == '-' || s.charAt(s.length() - 1) == '-'){
            return false;
        }
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '-'){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        if(s.isEmpty() || s.length() > 255){
            System.out.println("false");
            return;
        }
        if(s.charAt(0) == '.' || s.charAt(s.length() - 1) == '.'){
            System.out.println("false");
            return;
        }
        String[] sp = s.split("\\.");
        if(sp.length <= 1){
            System.out.println("false");
            return;
        }
        for(int i = 0; i < sp.length; i++){
            if(!cheak(sp[i])){
                System.out.println("false");
                return;
            }
        }
        System.out.println("true");
    }
}
