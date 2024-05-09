import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, k = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n];
		for(int i = 0; i < n; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}

		int start =1, end = 100_001;
		while(start <= end) {
			int mid = (start + end) / 2;
			if(calc(mid) >= m){
				k = Math.max(k, mid);
				start = mid + 1;
			} else {
				end = mid-1;
			}
		}

		System.out.println(k);


    }	

	static int calc(int x){
		int cnt = 0;
		for(int i : arr){
			cnt += i / x;
		}
		return cnt;
	}

}