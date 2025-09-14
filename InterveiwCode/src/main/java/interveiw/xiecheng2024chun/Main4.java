package interveiw.xiecheng2024chun;

import java.io.*;

public class Main4 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        if(s.length() == 2 || s.isEmpty() ){
            out.println("[]");
            out.flush();
            return;
        }
        String[] sp = s.substring(1, s.length() - 1).split("\\,"); //先去掉 头尾的方括号，再split 分割
        long[][] arr = new long[sp.length][2];
        for(int i = 0; i < sp.length; i++){
            int len = sp[i].length();
            if(sp[i].charAt(len - 1) != ')'){
                arr[i] = new long[]{Long.parseLong(sp[i]), 1L};
                continue;
            }
            String[] small = sp[i].substring(0, len - 1).split("\\(");
            arr[i][0] = Integer.parseInt(small[0]);
            arr[i][1] = Integer.parseInt(small[1]);
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if(arr.length == 1){
            sb.append(arr[0][0]).append("(").append(arr[0][1]).append(")");
        }else{
            long count = arr[0][1];
            for(int i = 1; i < arr.length; i++){
                if(arr[i][0] == arr[i-1][0]){
                    count = count + arr[i][1];
                }else if(arr[i][0] != arr[i-1][0]){
                    sb.append(arr[i-1][0]).append("(").append(count).append(")").append(",");
                    count = arr[i][1];
                }
            }
            sb.append(arr[arr.length - 1][0]).append("(").append(count).append(")");
        }
        sb.append("]");
        out.println(sb);
        out.flush();
    }
}
