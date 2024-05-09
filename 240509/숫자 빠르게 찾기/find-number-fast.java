import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
	static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++){
			int x = Integer.parseInt(br.readLine());
			int bs = bs(x);
			sb.append(bs(x)).append("\n");
		}
		System.out.println(sb);
    }	

	static int bs(int x){
		int start = 0;
		int end = n-1;
		while(start <= end){
			int mid = (start + end) / 2;
			if(arr[mid] == x){
				return mid+1;
			} else if(arr[mid] > x){
				end = mid-1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

}