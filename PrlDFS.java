package LP5;

import java.util.*;

public class PrlDFS {

    static List<Integer>[] graph;
    static boolean[] visited;

    public static void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int currNode = stack.pop();

            if (!visited[currNode]) {
                visited[currNode] = true;
                System.out.print(currNode + " ");

                for (int adjNode : graph[currNode]) {
                    if (!visited[adjNode]) {
                        stack.push(adjNode);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter No of Node, Edges, and Start node: ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        int startNode = sc.nextInt();

        graph = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            visited[i] = false; // no need parallel, Java arrays initialize false
        }

        System.out.println("Enter Pair of edges:");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(startNode);

        sc.close();
    }
}

