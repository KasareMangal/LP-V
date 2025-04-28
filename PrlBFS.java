package LP5;

import java.util.*;
import java.util.concurrent.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class PrlBFS {

    static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();
            if (temp.left == null) {
                temp.left = new Node(data);
                return root;
            } else {
                q.add(temp.left);
            }
            if (temp.right == null) {
                temp.right = new Node(data);
                return root;
            } else {
                q.add(temp.right);
            }
        }
        return root;
    }

    static void bfsParallel(Node head) {
        if (head == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Future<?>> futures = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                if (currNode == null) continue;

                futures.add(executor.submit(() -> {
                    System.out.print("\t" + currNode.data);
                }));

                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }

            // Wait for all printing tasks to complete for this level
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        executor.shutdown();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root = null;
        char ans;

        do {
            System.out.print("\nEnter data => ");
            int data = sc.nextInt();

            root = insert(root, data);

            System.out.print("Do you want to insert one more node? (y/n) ");
            ans = sc.next().charAt(0);

        } while (ans == 'y' || ans == 'Y');

        System.out.println("\nParallel BFS Traversal:");
        bfsParallel(root);

        sc.close();
    }
}

