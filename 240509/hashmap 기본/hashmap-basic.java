import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> map = new HashMap();
        StringBuilder sb= new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Integer key =-1;
        Integer value = -1;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()){
                case "add":
                     key = Integer.parseInt(st.nextToken());
                     value = Integer.parseInt(st.nextToken());
                    map.put(key, value);
                    break;
                case "find":
                     key = Integer.parseInt(st.nextToken());
                    value = map.get(key);
                    if(value == null){
                        sb.append("None\n");
                    } else{
                        sb.append(value).append("\n");
                    }
                    break;
                case "remove":
                    key = Integer.parseInt(st.nextToken());
                    map.remove(key);
                    break;
            }
        }
        System.out.println(sb);
    }

}