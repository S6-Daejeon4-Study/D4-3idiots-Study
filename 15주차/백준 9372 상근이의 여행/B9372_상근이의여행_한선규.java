import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9372_Main {

    static int[] root;
    static int N, M, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            root = new int[N + 1];
            makeSet();

            int cnt = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (union(x, y))
                    cnt++;
            }

            // 주어지는 비행 스케쥴은 항상 연결 그래프를 이루는데
            // 상근이가 가장 적은 종류의 비행기를 타고 모든 국가들을 여행할 수 있는(모든 정점을 방문할 수 있는) 비행기 종료의 최소 개수는 결국 간선의 개수...이므로 N-1과 동일하다..!
//            System.out.println(N-1);
            System.out.println(cnt);
        }

    }

    private static void kruskal() {
        // #1. 그래프의 간선들을 가중치의 오름차순으로 정렬
        // #2. 정렬된 간선 리스트에서 순서대로 사이클을 형성하지 않는 간선 선택
        // #2-1. 가장 낮은 가중치를 먼저 선택
        // #2-2. 사이클을 형성하는 간선을 제외
        // #2-3. 해당 간선을 현재의 MST의 집합에 추가
    }

    private static void makeSet() {
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }
    }

    private static  int find(int x) {
        if (root[x] == x)
            return x;
        // find 하면서 만난 모든 값의 부모 노드를 root로 만든다. (path compression)
        return root[x] = find(root[x]);
    }

    private static boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) return false;

        root[yRoot] = xRoot;
        return true;
    }
}
