package interveiw.shenxingfu2024qiu;

import java.util.*;
import java.io.*;

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        long[] results = new long[T];
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] tasks = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                tasks[i] = Integer.parseInt(st.nextToken());
            }

            long low = 0;
            long high = (long) 1e18;

            while (low <= high) {
                long mid = low + (high - low) / 2;
                if (check(mid, tasks, k)) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            results[t] = low;
        }

        for (long res : results) {
            System.out.println(res);
        }
    }

    private static boolean check(long mid, int[] tasks, int k) {
        if (mid == 0) {
            return k == 0;
        }

        int n = tasks.length;
        int[] L = new int[n];
        int[] R = new int[n];

        // Calculate L array (left to right)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long currentSum = 0;
        for (int i = 0; i < n; i++) {
            if (tasks[i] <= mid) {
                maxHeap.offer(tasks[i]);
                currentSum += tasks[i];
                while (currentSum > mid && !maxHeap.isEmpty()) {
                    int maxVal = maxHeap.poll();
                    currentSum -= maxVal;
                }
            }
            L[i] = maxHeap.size();
        }

        // Calculate R array (right to left)
        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>(Collections.reverseOrder());
        long currentSum2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (tasks[i] <= mid) {
                maxHeap2.offer(tasks[i]);
                currentSum2 += tasks[i];
                while (currentSum2 > mid && !maxHeap2.isEmpty()) {
                    int maxVal = maxHeap2.poll();
                    currentSum2 -= maxVal;
                }
            }
            R[i] = maxHeap2.size();
        }

        // Check all possible cases
        if (R[0] >= k) {
            return true;
        }
        if (L[n - 1] >= k) {
            return true;
        }
        for (int i = 0; i < n - 1; i++) {
            if (L[i] + R[i + 1] >= k) {
                return true;
            }
        }

        return false;
    }
}