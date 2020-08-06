import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_2961_도영이가만든맛있는음식
* @date 2020.08.06
* @link https://www.acmicpc.net/problem/2961
* @mem
* @time
* @caution
* [고려사항] 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다. 
* [입력사항] 재료의 개수 / 재료의 신맛과 쓴맛
* [출력사항] 재료는 적어도 하나 사용한 요리 중 신맛과 쓴맛의 차이가 가장 작은 요리의 차이
* 
* 부분집합을 만들면서 신맛과 쓴맛의 차이가 가장 적은 것을 계속 업데이트
* 
*/

public class BOJ_S1_2961_도영이가만든맛있는음식 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int N, Answer = Integer.MAX_VALUE;
	static pair[] ingredients;
	static StringTokenizer tokens = null;
	
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(src));
		
		//입력
		N = Integer.parseInt(input.readLine());
		
		int sour = 1, bitter = 0; 
		ingredients = new pair[N];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int sourness = Integer.parseInt(tokens.nextToken());
			int bitterness = Integer.parseInt(tokens.nextToken());
			
        	sour *= sourness;
        	bitter += bitterness;
        	
			ingredients[i] = new pair(sourness, bitterness);	
			Answer = Math.min(Answer, Math.abs(sour-bitter)); //비트마스킹을 이용한 부분집합만들기에는 전체 집합이 포함되어 있지 않아서 따로 계산
		}
		
		makeSubset();
		System.out.println(Answer);		
	}
	
    static void makeSubset() { //비트마스킹을 이용한 부분집합만들기
        for (int i = 1; i < (1 << N) - 1; i++) {
            int sour = 1, bitter = 0; //신맛은 곱셈이니까 1, 쓴맛은 덧셈이니까 0으로 초기화
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) <= 0) {
                	//요리의 신맛과 쓴맛 계산 결과를 저장
                	sour *= ingredients[j].sourness;
                	bitter += ingredients[j].bitterness;			
                }
            } 
            Answer = Math.min(Answer, Math.abs(sour-bitter)); //신맛과 쓴맛의 차이가 가장 적은 것을 저장            
        }
    }

	static class pair{
		int sourness, bitterness;

		public pair(int sourness, int bitterness) {
			super();
			this.sourness = sourness;
			this.bitterness = bitterness;
		}
	}
	
	static String src = "4\r\n" + 
			"1 7\r\n" + 
			"2 6\r\n" + 
			"3 8\r\n" + 
			"4 9";
}
