import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_1931_회의실배정
* @date 2020.08.06
* @link https://www.acmicpc.net/problem/1931
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 회의의 수 / 회의의 시작시간과 끝나는시간
* [출력사항] 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수
* 
* 종료시간을 기준으로 오름차순하여 확인하는 방법을 사용했지만
* List를 사용하여 현재 끝나는 시간보다 시작시간이 빠른 회의는 삭제해나가는 방법을 선택하니 시간초과가 났다.
* 
* 시작할 수없는 회의를 삭제해나가는 방법 대신, 시작 가능한 회의를 찾는 방법으로 바꾸니 통과했다.
* 
*/

public class BOJ_S2_1931_회의실배정 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens = null;
	static int Answer = 0;
	
	public static void main(String[] args) throws Exception{
		input = new BufferedReader(new StringReader(src));
		//입력
		int N = Integer.parseInt(input.readLine());
		
		pair[] times = new pair[N];
	
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int start = Integer.parseInt(tokens.nextToken());
			int end = Integer.parseInt(tokens.nextToken());;
			times[i] = new pair(start, end);
		}
		
		Arrays.sort(times); //종료시간 오름차순 정렬
		
		int end = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			if(times[i].start >= end) { //시작시간이 이전 회의 종료시간보다 뒤면 회의 시작	
				end = times[i].end;
				Answer++;
			}
		}		
		System.out.println(Answer);		
	}
	
	static class pair implements Comparable<pair>{
		int start, end;

		public pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(pair o) {
			//종료시간 오름차순 정렬	
			if(this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}		
	}
		
	static String src = "11\r\n" + 
			"1 4\r\n" + 
			"3 5\r\n" + 
			"0 6\r\n" + 
			"5 7\r\n" + 
			"3 8\r\n" + 
			"5 9\r\n" + 
			"6 10\r\n" + 
			"8 11\r\n" + 
			"8 12\r\n" + 
			"2 13\r\n" + 
			"12 14";
}
