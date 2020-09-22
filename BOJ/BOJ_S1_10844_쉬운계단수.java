import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_S1_10844_쉬운계단수
* @date 2020.09.22
* @link https://www.acmicpc.net/problem/10844
* 
* 45656 > 인접한 모든 자리수의 차이가 1인 수 = 계단 수
* N(1<=N<=100)이 주어질 때, 길이가 N인 계단 수가 총 몇 개인지 구하시오.
* -> 정답을 1,000,000,000으로 나눈 나머지로 출력할 것.
* 
* D[i][j] = D[i - 1][j - 1] + D[i - 1][j + 1]
* i = 자리수
* j = 마지막 자리의 숫자
* 
*/

public class BOJ_S1_10844_쉬운계단수 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static long[][] dp;
    static int N;
    static long Answer;
    static int mod = 1000000000;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        dp = new long[N + 1][10];

        for(int i = 1; i < 10; i++) { //1자리는 모두 1로 채움(자동으로 dp[1][0]은 0)
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1]; //1만 붙일 수 있음
            dp[i][9] = dp[i - 1][8]; //8만 붙일 수 있음
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod; //끝자리가 j-1, j+1인 경우.
            }
        }

        Answer = 0;
        for(int i = 0; i < 10; i++) {
            Answer += dp[N][i]; //끝이 0~9인 경우 모두 더함(1자리와는 다르게 N자리는 끝이 0~9)
        }
        
        System.out.println(Answer%mod); //출력
    }


}
