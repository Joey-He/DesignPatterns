package diji;

import java.util.*;

class Solution1 {

    /* Write Code Here */
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int[] res = new int[queries.length];
        int n = queries.length;
        for (int i = 0; i < n; i++) {
            int size = queries[i][1];
            int min = Integer.MAX_VALUE;
            int ans = -1;
            for (int j = 0; j < rooms.length; j++) {
                if (rooms[j][1] >= size) {
                    int abs = Math.abs(rooms[j][0] - queries[i][0]);
                    if (abs < min) {
                        ans = j + 1;
                        min = abs;
                        // 如果abs相等，则取房间rooms编号最小的
                    }else if(abs == min){
                        ans = Math.min(ans,j+1);
                    }
                }
            }
            res[i] = ans;
        }
        return  res;
    }
}

public class Main2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] res;

        int rooms_rows = 0;
        int rooms_cols = 0;
        rooms_rows = in.nextInt();
        rooms_cols = in.nextInt();

        int[][] rooms = new int[rooms_rows][rooms_cols];
        for(int rooms_i=0; rooms_i<rooms_rows; rooms_i++) {
            for(int rooms_j=0; rooms_j<rooms_cols; rooms_j++) {
                rooms[rooms_i][rooms_j] = in.nextInt();
            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }


        int queries_rows = 0;
        int queries_cols = 0;
        queries_rows = in.nextInt();
        queries_cols = in.nextInt();

        int[][] queries = new int[queries_rows][queries_cols];
        for(int queries_i=0; queries_i<queries_rows; queries_i++) {
            for(int queries_j=0; queries_j<queries_cols; queries_j++) {
                queries[queries_i][queries_j] = in.nextInt();
            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }


        res = new Solution1().closestRoom(rooms, queries);
        for(int res_i=0; res_i < res.length; res_i++) {
            System.out.println(String.valueOf(res[res_i]));
        }

    }
}
