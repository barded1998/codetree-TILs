import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        go(new int[M],1, 0);
        System.out.println(sb);
    }

    static void go(int[] arr, int next, int idx){
        if(idx == M){
            for(int i: arr){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = next; i <= N; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[idx] = i;
                go(arr, i, idx+1); 
                visited[i] = false;
            }
        }
    }
}