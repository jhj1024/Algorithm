import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D3_9229_한빈이와 Spot Mart
* @date 2020.08.03
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
* @mem
* @time
* @caution
* [고려사항] N개의 과자 봉지 중 두 개를 골라서 두 개의 합이 M을 넘지 않는 최대값 구하기(조합)
* [입력사항]
* [출력사항]
*/
public class SWEA_D3_9229_한빈이와SpotMart {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int[] snacks = null;
    static int T, N, M, Answer;
    
	public static void main(String[] args) throws Exception{
		input = new BufferedReader(new StringReader(src));
		T = Integer.parseInt(input.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			//입력
			tokens = new StringTokenizer(input.readLine(), " ");
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			snacks = new int[N];
			tokens = new StringTokenizer(input.readLine(), " ");
			for(int i = 0; i < N; i++) {				
				snacks[i] = Integer.parseInt(tokens.nextToken());
			}
			
			//알고리즘
			Answer = -1;
			makeCombination(2, 0, new int[2]); //전체에서 2개를 뽑는 조합 알고리즘 적용
			System.out.printf("#%d %d\n", tc, Answer);
		}

	}
	
	public static void makeCombination(int n, int start, int[] temp) {
		if(n == 0) {
			if(temp[0] + temp[1] <= M) //두 과자의 무게의 합이 M보다 작으면
				Answer = Math.max(Answer, (temp[0] + temp[1])); //Answer에 최대값 저장
			return;
		}
		
		for(int i = start; i < snacks.length; i++) {
			temp[n-1]= snacks[i];
			makeCombination(n-1, i+1, temp);
		}
	}
	
	
	static String src = "4\r\n" + 
			"3 45\r\n" + 
			"20 20 20\r\n" + 
			"6 10\r\n" + 
			"1 2 5 8 9 11\r\n" + 
			"4 100\r\n" + 
			"80 80 60 60\r\n" + 
			"4 20\r\n" + 
			"10 5 10 16";

}
