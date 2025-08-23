package erm;

import java.util.*;

public class Main {

    public static int minOperationsToBeautifulHost(int n, int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        int operations = 0;

        for (int i = 0; i < n; i++) {
            // 计算所有比 arr[i] 小的元素的 XOR
            int xor = 0;
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                int key = entry.getKey();
                int freq = entry.getValue();
                if (key < arr[i] && freq % 2 == 1) { // 奇数次贡献 1
                    xor ^= 1;
                }
            }
            if (xor != 0) {
                // 当前元素不满足条件，需要修改
                operations++;
                // 假设修改成一个合适值，使 XOR=0
                // 不必真的改值，只统计次数即可
            }

            // 更新当前元素计数
            count.put(arr[i], count.getOrDefault(arr[i], 0) + 1);
        }

        return operations;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(minOperationsToBeautifulHost(n, arr));
    }
}