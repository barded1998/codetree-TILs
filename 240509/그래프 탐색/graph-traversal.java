import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList();
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        System.out.println(dfs(1)-1);
    }

    static int dfs(int start){
        Queue<Integer> que = new LinkedList();
        que.add(start);
        visited[start] = true;
        while(!que.isEmpty()){
            int poll= que.poll();
            for(int i = 0;i < graph.get(poll).size(); i++){
                int next = graph.get(poll).get(i);
                if(!visited[next]){
                    visited[next] = true;
                    que.add(next);
                }
            }
        }

        int cnt = 0;

        for(int i = 0; i < N+1; i++){
            if(visited[i])
            cnt++;
        }
        return cnt;
    }
}