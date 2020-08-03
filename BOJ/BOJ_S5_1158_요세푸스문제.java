/**
* @author JUNG
* @name BOJ_S5_1158_요세푸스문제
* @date 2020.08.03
* @link https://www.acmicpc.net/problem/1158
* @mem
* @time
* @caution
* [고려사항] queue말고 그냥 linkedlist를 이용해서도 만들어보기
* [입력사항]
* [출력사항]
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S5_1158_요세푸스문제 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
	public static void main(String[] args) throws Exception {
		StringTokenizer tokens = new StringTokenizer(input.readLine(), " ");
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		
		Queue<Integer> circle = new LinkedList<Integer>();
		
		for(int i = 1; i <= N; i++) { //큐에 1~n을 저장
			circle.offer(i);
		}
		
		output.append("<");
		
		int cnt = 0;
		while(cnt != N) {
			for(int i = 1; i < K; i++) {
				circle.offer(circle.poll());
			}
			output.append(circle.poll()).append(", ");
			cnt++;
		}
		
		output.replace(output.length()-2, output.length(), ">");
		System.out.println(output);

	}

}
