package graph.islands.Circumference;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] island = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                island[i][j] = scanner.nextInt();
            }
        }

        int count = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (island[i][j] == 1) {
                    for (int[] dir : directions) {
                        int newX = i + dir[0];
                        int newY = j + dir[1];
                        if (newX < 0 || newX >= m || newY < 0 || newY >= n || island[newX][newY] == 0) {
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(count);
        scanner.close();
    }
}
