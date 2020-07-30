import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_2493_탑
* @date 2020.07.30
* @link https://www.acmicpc.net/problem/2493
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class BOJ_G5_2493_탑 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output = new StringBuffer();
	static Stack<pair> towers = new Stack<>();
	
	public static void main(String[] args) throws Exception{		
		int N = Integer.parseInt(input.readLine());
				
		StringTokenizer tokens = new StringTokenizer(input.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(tokens.nextToken()); //숫자 입력
			
			while(!towers.isEmpty()) {
				if(towers.peek().height > num) { //수신 가능한 탑이 있으면
					//문자열에는 수신받는 탑의 번호 넣고, 현재 탑은 스택에 넣은 뒤 탈출					
					output.append(towers.peek().idx).append(" ");		
					towers.push(new pair(i, num));
					break;
				} else { //수신 가능한 탑이 없으면
					//top을 빼고 다시 반복
					towers.pop();
				}
			}
			if(towers.isEmpty()) { 
				//스택이 비어있으면 output 문자열에는 0넣고 스택에 현재 탑 넣기				
				output.append("0").append(" ");
				towers.push(new pair(i, num));
			} 
		}
		System.out.println(output); //결과 출력
	}
	
	public static class pair{ //java에는 pair 자료형이 없어서 생성
		int idx, height;
		public pair(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
}
