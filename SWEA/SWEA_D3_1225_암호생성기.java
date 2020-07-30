import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D3_1225_암호생성기
* @date 2020.07.30
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14uWl6AF0CFAYD&categoryId=AV14uWl6AF0CFAYD&categoryType=CODE
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class SWEA_D3_1225_암호생성기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer output;
	static Queue<Integer> code;
	
	public static void main(String[] args) throws Exception {	
		input = new BufferedReader(new StringReader(src));
		String line = null;
		StringTokenizer tokens = null;
		int T = 0, N = 8;
		Queue<Integer> code;
		while((line = input.readLine()) != null) {
			T = Integer.parseInt(line);
			
			code = new LinkedList<Integer>();
			tokens = new StringTokenizer(input.readLine(), " ");
			for(int i = 0; i < N; i++) {
				code.offer(Integer.parseInt(tokens.nextToken())); //큐에 8개의 숫자 집어넣기
			}
			
			int idx = 0;
			while(true) {
				if(++idx ==6) { //idx는 1~5사이로만 유지되어야 함.
					idx = 1;
				}
				
				if(code.peek() - idx <= 0) {
					code.poll();
					code.offer(0); //0으로 만들어 맨 뒤에 붙여놓고 종료
					break;
				}else {
					code.offer(code.poll()-idx); //그렇지 않으면 1 줄이고 뒤에 붙이기
				}				
			}
			
			output = new StringBuffer();
			output.append("#").append(T).append(" ");
			while(!code.isEmpty()){
				output.append(code.poll()).append(" ");
			}			
			System.out.println(output);
		}
	}
	static String src = "1\r\n" + 
			"9550 9556 9550 9553 9558 9551 9551 9551 \r\n" + 
			"2\r\n" + 
			"2419 2418 2423 2415 2422 2419 2420 2415 \r\n" + 
			"3\r\n" + 
			"7834 7840 7840 7835 7841 7835 7835 7838 \r\n" + 
			"4\r\n" + 
			"4088 4087 4090 4089 4093 4085 4090 4084 \r\n" + 
			"5\r\n" + 
			"2945 2946 2950 2948 2942 2943 2948 2947 \r\n" + 
			"6\r\n" + 
			"670 667 669 671 670 670 668 671 \r\n" + 
			"7\r\n" + 
			"8869 8869 8873 8875 8870 8872 8871 8873 \r\n" + 
			"8\r\n" + 
			"1709 1707 1712 1712 1714 1710 1706 1712 \r\n" + 
			"9\r\n" + 
			"10239 10248 10242 10240 10242 10242 10245 10235 \r\n" + 
			"10\r\n" + 
			"6580 6579 6574 6580 6583 6580 6577 6581 \r\n" + 
			"";
}