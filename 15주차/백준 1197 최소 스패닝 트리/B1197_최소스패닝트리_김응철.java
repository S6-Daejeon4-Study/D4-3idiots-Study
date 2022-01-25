import java.util.*;
import java.io.*;

public class Main_최소스패닝트리_1197 {

    static int[] parents;
    static int V,E;

    private static void make(){
        for(int i=1;i<=V;i++){
            parents[i] = i;
        }
    }
    private static int find(int a){
        if(a==parents[a]) return a;
        return parents[a] = find(parents[a]); // path compression
    }

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }


    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V+1];
        Edge[] edge = new Edge[E];



        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            edge[i] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        } // input

        Arrays.sort(edge); // 간선 오름차순 정렬

        make();

        int cnt=0, result = 0;
        for(Edge e : edge){
            if(union(e.A, e.B)){
                result += e.C;
                if(++cnt == V-1) break;
            }
        }

        System.out.println(result);


    }

    static class Edge implements Comparable<Edge>{

        int A,B,C;

        public Edge(int a, int b, int c) {
            A = a;
            B = b;
            C = c;
        }

        public int getA() {
            return A;
        }

        public void setA(int a) {
            A = a;
        }

        public int getB() {
            return B;
        }

        public void setB(int b) {
            B = b;
        }

        public int getC() {
            return C;
        }

        public void setC(int c) {
            C = c;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "A=" + A +
                    ", B=" + B +
                    ", C=" + C +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.C, o.C);
        }
    }


}
