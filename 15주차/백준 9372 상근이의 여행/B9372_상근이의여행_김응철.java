import java.util.*;
import  java.io.*;

public class Main_상근이의여행_9372 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            int cnt=0;
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            boolean[][] arr = new boolean[N+1][N+1];
            boolean[] check = new boolean[N+1];

            for(int m=0;m<M;m++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                arr[a][b] = true;
            } // input

            System.out.println(N-1);

        }

    }
}
