package interveiw.shunfeng2024qiu;

import java.util.*;

public class Main2 {
    static List<Integer> list = new ArrayList<>();
    static int res = 0;
    public static void backtarcking(int[] rooms, List<Integer> list,  boolean[] used, int n){
        if(list.size() == n){
            res++;
            return;
        }
        for(int i = 0; i < n; i++){
            if(!used[i]) {
                if (list.size() == 0 || rooms[i] % list.get(list.size() - 1) == 0 || list.get(list.size() - 1) % rooms[i] == 0) {
                    list.add(rooms[i]);
                    used[i] = true;
                    backtarcking(rooms, list, used, n);
                    used[i] = false;
                    list.remove(list.size() - 1);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] rooms = new int[n];
        for(int i = 0; i < n; i++){
            rooms[i] = scanner.nextInt();
        }
        boolean[] used = new boolean[n];
        backtarcking(rooms, list, used, n);
        System.out.println(res);
    }
}
