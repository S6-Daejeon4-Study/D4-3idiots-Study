package day0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B8979_올림픽_이예나 {

	static class Medal implements Comparable<Medal> {
		int num;
		int gold;
		int selver;
		int bronze;

		Medal(int num, int gold, int selver, int bronze) {
			this.num = num;
			this.gold = gold;
			this.selver = selver;
			this.bronze = bronze;
		}

		@Override
		public int compareTo(Medal o) {
			if (this.gold == o.gold) {
				
				if(this.selver == o.selver)
					return o.bronze-this.bronze;
				else return o.selver-this.selverl
			}
			else return o.gold-this.gold;
			
		}
	}

	static List<Medal> contryList;
	static Medal [] contryArr;
	static int [] order;
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		contryList = new ArrayList<>();
		order = new int [N+1];
		int want = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			contryList.add(new Medal(num, g, s, b));
		}
		Collections.sort(contryList);

		for (int c = 0; c < contryList.size(); c++) {
			Medal now = contryList.get(c);
			Medal bef = null;
			if (c != 0) {
				bef = contryList.get(c - 1);
				System.out.println("bef: "+bef.num + " " + bef.gold + " " + bef.selver + " " + bef.bronze + " " + order[c-1]);

				if (bef.gold == now.gold && bef.selver == now.selver && bef.bronze == now.bronze) {
					order[c] = order[c - 1];
				}
				else order[c]=c+1;
			}

			else
				order[c] = c + 1;
			System.out.println(now.num + " " + now.gold + " " + now.selver + " " + now.bronze + " " + order[c]);

			if (contryList.get(c).num == want) {
				System.out.println(order[c]);
			}

//		}

	}

	}
	}
