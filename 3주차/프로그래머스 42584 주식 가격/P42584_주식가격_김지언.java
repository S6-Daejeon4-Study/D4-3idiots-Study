class Solution {
	public class Data {
		int start, price;

		public Data(int start, int price) {
			this.start = start;
			this.price = price;
		}
	}

	public int[] solution(int[] prices) {
		Stack<Data> stack = new Stack<>();

		for (int i = 0; i < prices.length; i++) {
			while (!stack.isEmpty() && stack.peek().price > prices[i]) {
				Data top = stack.pop();
				prices[top.start] = i - top.start;
			}
			stack.push(new Data(i, prices[i]));
		}

		while (!stack.isEmpty()) {
			Data top = stack.pop();
			prices[top.start] = prices.length - 1 - top.start;
		}

		return prices;
	}
}
