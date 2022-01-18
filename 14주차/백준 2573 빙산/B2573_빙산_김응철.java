import java.util.*;
import java.io.*;

public class Main_빙산_2573 {

    static int N,M, years =0;
    static int[][] arr;
    static boolean[][] visited;
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // Input

        while(true){
            int count=0;
            visited = new boolean[N][M];

            for(int i=1;i<N;i++){
                for(int j=1;j<M;j++){
                    if(arr[i][j]!=0 && !visited[i][j]){
                        count++;
                        if(count==2){
                            System.out.println(years);
                            System.exit(0);
                        }
                        bfs(i,j,arr[i][j]);
                    }
                }
            }
            if(count == 0){
                System.out.println(0);
                System.exit(0);
            }

            years++;

        }

    }

    public static void bfs(int i,int j,int height){

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(i,j,height));

        visited[i][j] = true;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            int x = now.x, y = now.y, nx = 0,ny = 0;

            for(int d=0;d<4;d++){
                nx = x + di[d];
                ny = y + dj[d];
                if(visited[nx][ny])
                    continue;
                if(arr[nx][ny] == 0) {
                    if (arr[x][y] > 0)
                        arr[x][y] -= 1;
                }
                else{
                    queue.offer(new Node(nx,ny,arr[nx][ny]));
                    visited[nx][ny] = true;
                }
            }


        }
    }

    static class Node{
        int x,y,height;

        public Node(int x,int y,int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", height=" + height +
                    '}';
        }
    }

}
