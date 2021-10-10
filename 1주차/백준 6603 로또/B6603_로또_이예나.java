import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B6603_로또_이예나 {
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	static final int MAX = 6;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			if(k == 0) {	
				System.out.println(sb.toString());
				return;
			}
			
			result = new int[k];
			
			for(int i = 0; i < k; i++) {
				int num = Integer.parseInt(st.nextToken());
				result[i] = num;
			}
			
			permutation(k, 0, 0, new int[6], new boolean[k]);
			
			sb.append("\n");
		}
	}
	
	static void permutation(int n, int r, int index, int[] arr, boolean[] used) {
		if(r == MAX) {	
			for(int ele : arr) {
				sb.append(ele).append(" ");
			}
			
			sb.append("\n");
			return;
		}
		
		if(index >= n) {	
			return;
		}
		
		if(used[index]) {
			permutation(n, r, index + 1, arr, used);
		}
		else {
			for(int i = index; i < n; i++) {
				used[i] = true;
				arr[r] = result[i];
				permutation(n, r + 1, i + 1, arr, used);
				used[i] = false;
			}
		}
	}
}