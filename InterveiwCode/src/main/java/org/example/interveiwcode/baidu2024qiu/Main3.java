package org.example.interveiwcode.baidu2024qiu;
import java.io.*;
import java.util.*;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int q = Integer.parseInt(firstLine[2]);

        // Build graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Read types
        int[] type = new int[n+1];
        String[] types = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            type[i] = Integer.parseInt(types[i-1]);
        }

        // Build subgraph for type 1 nodes
        List<List<Integer>> adj_g = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj_g.add(new ArrayList<>());
        for (int u = 1; u <= n; u++) {
            if (type[u] == 1) {
                for (int v : adj.get(u)) {
                    if (type[v] == 1) {
                        adj_g.get(u).add(v);
                    }
                }
            }
        }

        int[] compId = new int[n+1];
        Arrays.fill(compId, -1);
        List<Integer> compSize = new ArrayList<>();
        List<Integer> compNSize = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        int compCount = 0;

        for (int u = 1; u <= n; u++) {
            if (type[u] == 1 && !visited[u]) {
                int cid = compCount++;
                List<Integer> compNodes = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(u);
                visited[u] = true;
                compId[u] = cid;

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    compNodes.add(node);
                    for (int neighbor : adj_g.get(node)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            compId[neighbor] = cid;
                            queue.add(neighbor);
                        }
                    }
                }

                compSize.add(compNodes.size());

                Set<Integer> nSet = new HashSet<>();
                for (int node : compNodes) {
                    for (int neighbor : adj.get(node)) {
                        if (type[neighbor] == 2) {
                            nSet.add(neighbor);
                        }
                    }
                }
                compNSize.add(nSet.size());
            }
        }

        // Process queries
        String[] queries = br.readLine().split(" ");
        for (int i = 0; i < q; i++) {
            int u = Integer.parseInt(queries[i]);
            if (type[u] == 2) {
                bw.write("1 ");
            } else {
                int cid = compId[u];
                bw.write((compSize.get(cid) + compNSize.get(cid)) + " ");
            }
        }
        bw.flush();
    }
}