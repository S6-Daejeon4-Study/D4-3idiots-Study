import java.io.*;
import java.util.*;

public class Main_계단오르기_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N+1];
        int[] s = new int[N+1];

        for(int i=0;i<=N;i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        s[1] = a[1];

        if(N>1){
          s[2] = a[1]+a[2];
        }

        for(int i=3;i<=N;i++){
            s[i] = Math.max(s[i-3] + a[i-1],s[i-2]) + a[i];
        }

        System.out.println(s[N-1]);
    }

}
