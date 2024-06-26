import java.io.*;
import java.util.*;

public class Main {

    static int K, N;
    static int[] cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cnt = new int[K+1];
        go(new int[N], 0);
        System.out.println(sb);
    }

    static void go(int[] arr, int idx){
        if(idx == N){
            for(int i: arr){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 1; i <=K; i++){
            if(con(arr, idx, i)){
                arr[idx] = i;
                go(arr,idx+1);
            }
        }
    }

    static boolean con(int[] arr, int idx, int n){
        int cnt = 0;
        if(idx-1 >= 0){
            if(arr[idx-1] == n)
                cnt++;
        }
        if(idx-2 >= 0){
            if(arr[idx-2] == n)
                cnt++;
        }
        return cnt < 2;
    }
}