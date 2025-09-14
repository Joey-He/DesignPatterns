package interveiw.xiecheng2024chun;

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        // 加速输入输出
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine().trim()); // 读取测试组数
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim()); // 节点数量
            int[] nums = new int[n];

            // 读取权值
            String[] weightInput = br.readLine().trim().split(" ");
            for (int i = 0; i < n; ++i) {
                nums[i] = Integer.parseInt(weightInput[i]);
            }

            // 读取边（这里读取但不使用）
            for (int i = 0; i < n - 1; ++i) {
                br.readLine(); // 读取边
            }

            // 统计权值为奇数的节点数量
            int oddCount = 0;
            for (int num : nums) {
                if (num % 2 != 0) {
                    ++oddCount;
                }
            }

            // 判断结果并输出
            if (oddCount % 2 == 0 || (n - oddCount) % 2 == 0) {
                bw.write("Yes\n");
            } else {
                bw.write("No\n");
            }
        }
        bw.flush(); // 刷新输出流
    }
}