import java.io.*;
import java.util.*;

public class Main {

    static int K, M;
    static int[][] origin = new int[5][5], dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Queue<Integer> fills = new LinkedList<>();

    static class Turn implements Comparable<Turn> {
        int r, c, d, value;
        int[][] graph;

        public Turn(int r, int c, int d, int[][] graph) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.graph = graph;
            calc();
        }

        @Override
        public int compareTo(Turn o) {
            if (o.value == this.value) {
                if (this.d == o.d) {
                    if (this.c == o.c) {
                        return this.r - o.r;
                    }
                    return this.c - o.c;
                }
                return this.d - o.d;
            }
            return o.value - this.value;
        }

        void calc() {
            for (int k = 0; k < d * 2; k++) {
                int start = graph[r - 1][c - 1];
                int curR = r - 1;
                int curC = c - 1;
                int curD = 0;
                for (int i = 0; i < 7; i++) {
                    int nextR = curR + dir[curD][0];
                    int nextC = curC + dir[curD][1];
                    if (!in3By3(r, c, nextR, nextC)) {
                        curD = (curD + 1) % 4;
                        nextR = curR + dir[curD][0];
                        nextC = curC + dir[curD][1];
                    }
                    graph[curR][curC] = graph[nextR][nextC];
                    curR = nextR;
                    curC = nextC;
                }
                graph[curR][curC] = start;
            }
            value = calcValue(graph);
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            fills.add(Integer.parseInt(st.nextToken()));
        }


        for (int i = 0; i < K; i++) {
            PriorityQueue<Turn> pq = new PriorityQueue<>();
            int answer = 0;
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    for (int l = 1; l <= 3; l++) {
                        pq.add(new Turn(j, k, l, copy()));
                    }
                }
            }
            if (pq.peek().value == 0) {
                break;
            }
            Turn max = pq.poll();
            answer += max.value;
            origin = max.graph;
//            print(origin);
            for (int k = 0; k < 5; k++) {
                for (int j = 4; j >= 0; j--) {
                    if (origin[j][k] == 0) {
                        origin[j][k] = fills.poll();
                    }
                }
            }
//            print(origin);
            int x;
            while ((x = calcValue(origin)) >= 3) {
                answer += x;
//                print(origin);
                for (int k = 0; k < 5; k++) {
                    for (int j = 4; j >= 0; j--) {
                        if (origin[j][k] == 0) {
                            origin[j][k] = fills.poll();
                        }
                    }
                }
//                print(origin);
            }


//            System.out.println("answer = " + answer);

            sb.append(answer).append(" ");
        }

        System.out.println(sb);
    }

    static boolean in5By5(int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }

    static boolean in3By3(int r1, int c1, int r2, int c2) {
        return Math.abs(r2 - r1) <= 1 && Math.abs(c2 - c1) <= 1;
    }

    static void print(int[][] graph) {
        System.out.println("==========");
        for (int i = 0; i < graph.length; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }

    static int[][] copy() {
        int[][] temp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                temp[i][j] = origin[i][j];
            }
        }
        return temp;
    }

    static int calcValue(int[][] graph) {
        boolean[][] visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(visited[i], false);
        }
        int answer = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    int v = bfs(i, j, graph, visited);
                    if (v >= 3) {
                        answer += v;
                    }
                }
            }
        }
        return answer;
    }

    static int bfs(int r, int c, int[][] graph, boolean[][] visited) {
        Queue<Node> que = new LinkedList<>();
        visited[r][c] = true;
        que.add(new Node(r, c));

        int v = 1;
        int p = graph[r][c];
        while (!que.isEmpty()) {
            Node poll = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = poll.r + dir[i][0];
                int nc = poll.c + dir[i][1];
                if (in5By5(nr, nc) && !visited[nr][nc] && graph[nr][nc] == p) {
                    que.add(new Node(nr, nc));
                    v++;
                    visited[nr][nc] = true;
                }
            }
        }

        if (v >= 3) {
            que.add(new Node(r, c));
            graph[r][c] = 0;
            while (!que.isEmpty()) {
                Node poll = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = poll.r + dir[i][0];
                    int nc = poll.c + dir[i][1];
                    if (in5By5(nr, nc) && graph[nr][nc] == p) {
                        que.add(new Node(nr, nc));
                        graph[nr][nc] = 0;
                    }
                }
            }
        }
        return v;
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}