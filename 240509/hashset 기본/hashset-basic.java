import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new HashSet();
        StringBuilder sb= new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int x =0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()){
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    set.add(x);
                    break;
                case "find":
                     x = Integer.parseInt(st.nextToken());
                    sb.append(set.contains(x)).append("\n");
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    set.remove(x);
                    break;
            }
        }
        System.out.println(sb);
    }

}