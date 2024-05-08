import java.io.*;
import java.util.*;

public class Main {

	static int min = Integer.MAX_VALUE;
	static int n, s;
	static int[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
		int sum = 0;
		int end = 0;
		for(int start = 0; start < n; start++){
			while(end < n && sum + arr[end] <= s){
				sum += arr[end++];
			}
			min = Math.min(min, end-start+1);
			sum -= arr[start];
		}


		System.out.println(min);
		
    }

}