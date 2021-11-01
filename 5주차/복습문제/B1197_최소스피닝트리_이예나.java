package day1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1197_최소스피닝트리_이예나 {

	static boolean union(int a,int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) {return false;}
		else {
		parent[rootB] = rootA;
		return true;
		
		}
	}
	static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
		
	}
	static void init(int V) {
		
		for(int i=0;i<=V;i++) {
			parent[i]=i;
		}
	}
	static class Node implements Comparable<Node>{
		int first;
		int second;
		int weight;
		Node(int f, int s, int w){
			this.first=f;
			this.second=s;
			this.weight=w;
		}
		@Override
		public int compareTo(Node o) {
			if(this.weight ==o.weight) {
					if(this.first==o.first) {
						return this.second-o.second;
					}
				return this.first -o.second;
			}
			return this.weight - o.weight;
		}
		
	}
	
	static int []parent;
	static int V,E;
	static List<Node> nodes;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V+1];
		nodes = new ArrayList<>();
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes.add(new Node(a,b,c));
			
		}
		
		init(V);
		Collections.sort(nodes);
		
		int sum=0;
		for(int i=0;i<nodes.size();i++) {
			Node n = nodes.get(i);
//			System.out.println(n.first+" "+n.second+" "+n.weight);
			if(union(n.first,n.second)) {
//				System.out.println(n.weight+" 더함");
				sum+=n.weight;
			}
		}
		System.out.println(sum);
	}

}
