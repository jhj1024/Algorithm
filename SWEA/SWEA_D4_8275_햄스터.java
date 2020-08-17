import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D4_8275_햄스터
 * @date 2020.08.17
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWxQ310aOlQDFAWL&categoryId=AWxQ310aOlQDFAWL&categoryType=CODE
 * @mem
 * @time
 * @caution [고려사항] 
 * [입력사항] 햄스터 우리 개수 N / 각 우리의 최대 햄스터 수 X / 기록 개수 M / I번 우리에서 R번 우리까지의 햄스터 수는 S마리 
 * [출력사항] 기록을 모두 만족하는 햄스터 수 배치
 * 
 * 가능한 방법이 여러 가지일 경우, 햄스터 수가 가장 많은 것을 출력 만약 모든 기록을 만족하는 햄스터 배치가 없다면, -1을 출력
 * 모든 경우의 수를 만들고, 조건을 만족하는 녀석만 뽑기
 */

public class SWEA_D4_8275_햄스터 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens = null;
	static int T, N, M, X, max;
	static int[][] records;
	static int[] Answer;

	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(src));

		// 입력
		T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			N = Integer.parseInt(tokens.nextToken());
			X = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());

			records = new int[M][3];
			for (int m = 0; m < M; m++) {
				tokens = new StringTokenizer(input.readLine(), " ");
				records[m][0] = Integer.parseInt(tokens.nextToken());
				records[m][1] = Integer.parseInt(tokens.nextToken());
				records[m][2] = Integer.parseInt(tokens.nextToken());
			}

			// 알고리즘
			max = Integer.MIN_VALUE;
			Answer = null;
			makeCases(1, new int[N + 1], 0); // 1. 모든 경우의 수 만들기 2. 그 중 조건에 맞는 놈만 고르기

			// 정답
			output.append("#").append(t); 
			if(Answer == null) {
				output.append(" ").append(-1);
			}else {
				for(int i = 0; i < N; i++) {
					output.append(" ").append(Answer[i]);
				}
			}
			output.append("\n");
		}
		
		System.out.println(output);
	}

	static void makeCases(int n, int[] temp, int sum) {
		if (n > N) {
			check(temp, sum); // 만들면 조건에 맞는지 체크
			return;
		}

		for (int i = 0; i <= X; i++) {
			temp[n] = i;
			makeCases(n + 1, temp, sum + i);
		}
	}

	static void check(int[] cases, int sum) {
		boolean isAnswer = true;
		if (max < sum) { // 전체 햄스터의 수가 가장 큰 경우만 조건 따져줌
			outer: for (int m = 0; m < M; m++) { // 기록(=조건)의 수만큼 반복
				int cnt = 0;
				for (int l = records[m][0]; l <= records[m][1]; l++) {
					cnt += cases[l];
				}

				if (cnt != records[m][2]) {
					isAnswer = false;
					break outer;
				}
			}
			if (isAnswer) {
				Answer = Arrays.copyOfRange(cases, 1, N + 1);// 조건에 모두 맞으면 정답 후보로 넣기
				max = sum;
			}
		}
	}

	static String src = "6\r\n" + 
						"3 5 1\r\n" + 
						"1 2 5\r\n" + 
						"3 5 2\r\n" + 
						"1 2 6\r\n" + 
						"2 3 7\r\n" + 
						"4 5 2\r\n" + 
						"1 3 15\r\n" + 
						"3 4 4\r\n" + 						
						"5 5 3\r\n" +
						"1 3 10\r\n" +
						"2 4 10\r\n" +
						"3 5 10\r\n" +
						"1 1 1\r\n" + 
						"1 1 0\r\n" + 
						"6 10 1\r\n" + 
						"1 6 59\r\n";
}
