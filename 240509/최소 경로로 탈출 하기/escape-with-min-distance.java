import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] graph, dir = {{-1,0}, {1,0}, {0,-1},{0,1}};
    static int[][] visited;
    static class Node {
        int r, c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new int[n][m];
       for(int i = 0; i < n; i++){
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       }
       System.out.println(bfs()-1);
    }
    static int bfs(){
        Queue<Node> que = new LinkedList();
        que.add(new Node(0,0));
        visited[0][0] = 1;
        while(!que.isEmpty()){
            Node poll = que.poll();
            for(int i =0; i < 4; i++){
                int nr = poll.r + dir[i][0];
                int nc = poll.c + dir[i][1];
                if(inGraph(nr, nc) && graph[nr][nc] == 1 && visited[nr][nc] == 0){
                    que.add(new Node(nr, nc));
                    visited[nr][nc] = visited[poll.r][poll.c]+1;
                }
            }
        }
        return visited[n-1][m-1];
        
    }

    static boolean inGraph(int r, int c){
        return 0 <= r && r < n && 0 <= c && c < m;
    }

}