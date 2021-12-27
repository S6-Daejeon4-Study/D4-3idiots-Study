import java.util.*;

class Solution {
    class Car implements Comparable<Car> {
		int s;
		int e;

	    Car(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Car o) {
			if (this.e == o.e) {
				return this.s - o.s;
			} else
				return this.e - o.e;
		}
	}
    
    public int solution(int[][] routes) {
        int answer=0;
        List<Car> cars = new ArrayList<>();
		for (int i = 0; i < routes.length; i++) {
			cars.add(new Car(routes[i][0], routes[i][1]));
		}
		Collections.sort(cars);
		int end = -30001;
		for (int i = 0; i < cars.size(); i++) {
			if (end < cars.get(i).s) {
				answer++;
				end = cars.get(i).e;
			}

		}
		return answer;
	}

}