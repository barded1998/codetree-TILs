import java.io.*;
import java.util.*;

public class Main {

    static int R, C, K;
    //  북동남서
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] visited, exit;
    static int[][] dist;

    static class Golem {
        int r, c, d, idx;

        public Golem(int idx, int r, int c, int d) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Golem{" +
                "r=" + r +
                ", c=" + c +
                ", d=" + d +
                ", idx=" + idx +
                '}';
        }

        //  북동남서
//        static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean possibleNorth() {
            if (r == -2) {
                int northR = 0;
                int northC = c;
                if (inGraph(northR, northC) && !visited[northR][northC])
                    return true;
            }
            if (r == R - 1) {
                return false;
            }
            //맨밑
            int northR = r + 1;
            int northC = c;
            for (int i = 0; i < 4; i++) {
                if (i == 0)
                    continue;
                int nr = northR + dir[i][0];
                int nc = northC + dir[i][1];
                if (!inGraph(nr, nc) || visited[nr][nc])
                    return false;

            }
            return true;
        }

        void moveNorth() {
            if (inGraph(r, c))
                visited[r][c] = false;
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (inGraph(nr, nc))
                    visited[nr][nc] = false;
            }
            r++;
            if (inGraph(r, c))
                visited[r][c] = true;
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (inGraph(nr, nc))
                    visited[nr][nc] = true;
            }
        }

        boolean possibleWest() {
            if (r == -2) {
                return true;
            }
            if (r == R - 2) {
                return false;
            }
            //맨밑
            int westR = r;
            int westC = c - 1;
            for (int i = 0; i < 4; i++) {
                if (i == 1)
                    continue;
                int nr = westR + dir[i][0];
                int nc = westC + dir[i][1];
                if (nr < 0) {
                    continue;
                }
                if (!inGraph(nr, nc) || visited[nr][nc])
                    return false;
            }
            westR = r + 1;
            westC = c - 1;
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 1)
                    continue;
                int nr = westR + dir[i][0];
                int nc = westC + dir[i][1];
                if (nr < 0) {
                    continue;
                }
                if (!inGraph(nr, nc) || visited[nr][nc])
                    return false;
            }
            return true;
        }

        void moveWest() {
            if (inGraph(r, c))
                visited[r][c] = false;
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (inGraph(nr, nc))
                    visited[nr][nc] = false;
            }
            c--;
            r++;
            if (inGraph(r, c))
                visited[r][c] = true;
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (inGraph(nr, nc))
                    visited[nr][nc] = true;
            }
            d = (d + 3) % 4;
        }

        boolean possibleEast() {
            //맨밑
            if (r == R - 2) {
                return false;
            }
            int eastR = r;
            int eastC = c + 1;
            for (int i = 0; i < 4; i++) {
                if (i == 3)
                    continue;
                int nr = eastR + dir[i][0];
                int nc = eastC + dir[i][1];
                if (nr < 0) {
                    continue;
                }
                if (!inGraph(nr, nc) || visited[nr][nc])
                    return false;
            }

            eastR = r + 1;
            eastC = c + 1;
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 3)
                    continue;
                int nr = eastR + dir[i][0];
                int nc = eastC + dir[i][1];
                if (nr < 0) {
                    continue;
                }
                if (!inGraph(nr, nc) || visited[nr][nc])
                    return false;
            }
            return true;
        }

        void moveEast() {
            if (inGraph(r, c))
                visited[r][c] = false;
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (inGraph(nr, nc))
                    visited[nr][nc] = false;
            }
            c++;
            r++;
            if (inGraph(r, c))
                visited[r][c] = true;
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (inGraph(nr, nc))
                    visited[nr][nc] = true;
            }
            d = (d + 1) % 4;
        }


        boolean inVisited() {
            //맨밑
            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if (!inGraph(nr, nc))
                    return false;
            }
            return true;
        }

        void calcDist() {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (inGraph(nr, nc))
                exit[nr][nc] = true;
            for (int i = 0; i < 4; i++) {
                nr = r + dir[i][0];
                nc = c + dir[i][1];
                if (inGraph(nr, nc))
                    dist[nr][nc] = idx;
            }
            dist[r][c] = idx;
        }
    }


    static ArrayList<Golem> golems = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        exit = new boolean[R][C];
        dist = new int[R][C];
        int answer = 0;
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            Golem golem = new Golem(i, -2, c, d);
            golems.add(golem);
            while (true) {
                if (golem.r == R - 2) {
                    break;
                } else if (golem.possibleNorth()) {
                    golem.moveNorth();
                } else if (golem.possibleWest()) {
                    golem.moveWest();
                } else if (golem.possibleEast()) {
                    golem.moveEast();
                } else {
                    break;
                }
            }
//            print();
//            printExit();
            if (!golem.inVisited()) {
                visited = new boolean[R][C];
                exit = new boolean[R][C];
                dist = new int[R][C];
                golems = new ArrayList<>();
            } else {
                for (Golem g : golems) {
                    g.calcDist();
                }
//                printExit();
//                printDist();
//                System.out.println(golem);
//                int cd = calcDist(golem);
//                System.out.println("cd = " + cd);
                answer += calcDist(golem);
            }
        }
        System.out.println(answer);
    }

    static int calcDist(Golem golem) {
        int max = golem.r + 1;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(golem.idx, golem.r, golem.c));
        while (!que.isEmpty()) {
            Node poll = que.poll();
            max = Math.max(max, poll.r);
            if (exit[poll.r][poll.c]) {
                for (int i = 0; i < 4; i++) {
                    int nr = poll.r + dir[i][0];
                    int nc = poll.c + dir[i][1];
                    if (inGraph(nr, nc) && dist[nr][nc] != 0) {
                        que.add(new Node(dist[nr][nc], nr, nc));
                        dist[nr][nc] = 0;
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int nr = poll.r + dir[i][0];
                    int nc = poll.c + dir[i][1];
                    if (inGraph(nr, nc) && poll.idx == dist[nr][nc]) {
                        que.add(new Node(poll.idx, nr, nc));
                        dist[nr][nc] = 0;
                    }
                }
            }
        }

        return max+1;
    }

    static class Node {
        int idx, r, c;

        public Node(int idx, int r, int c) {
            this.idx = idx;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                "idx=" + idx +
                ", r=" + r +
                ", c=" + c +
                '}';
        }
    }


    static boolean inGraph(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    static void print() {
        System.out.println("====visited====");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(visited[i][j] ? "O " : "X ");
            }
            System.out.println();
        }
    }

    static void printExit() {
        System.out.println("====exit====");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(exit[i][j] ? "O " : "X ");
            }
            System.out.println();
        }
    }

    static void printDist() {
        System.out.println("====exit====");
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }
    }


}