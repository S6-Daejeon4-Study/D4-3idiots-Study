import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Car[] cars = new Car[routes.length];
        
        for (int i = 0; i < routes.length; i++)
            cars[i] = new Car(routes[i][0], routes[i][1]);
        
        Arrays.sort(cars);
        
				// 람다식을 활용한 간단한 정렬방법
        // Arrays.sort(routes, (car1, car2) -> Integer.compare(car1[1], car2[1]));
        
        int camera = -30001;
        
        for (Car car : cars) {
            if (camera < car.in) { // 현재 카메라가 설치되어 있는 지점보다 나중에 들어오 차량이라면
                camera = car.out; // 그 차량의 진출지점을 카메라 지점으로 설정하고
                answer++; // 카메라 개수를 추가한뒤 다음 차량에 대해서 또 진입시간을 비교해가면서 탐색
            }
        }
        
        return answer;
    }
}

class Car implements Comparable<Car> { // 차량의 고속도로 진입, 진출 지점정보를 가지고있는 클래스
    int in; // 진입 지점
    int out; // 진출 지점
    
    public Car(int in, int out) {
        this.in = in;
        this.out = out;
    }
    
    @Override
    public int compareTo(Car o) {
        return this.out - o.out; // 진출 지점 오름차순
    }
}