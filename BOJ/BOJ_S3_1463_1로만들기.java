/**
* @author JUNG
* @name BOJ_S3_1463_1로만들기
* @date 2020.09.22
* @link https://www.acmicpc.net/problem/1463
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 1보다 크거나 같고, 10^6보다 작거나 같은 정수 N
* [출력사항] 연산을 하는 횟수의 최솟값
* 
* 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
* 2. X가 2로 나누어 떨어지면, 2로 나눈다.
* 3. 1을 뺀다.
* 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 
* 연산을 사용하는 횟수의 최솟값을 출력하시오.
* 
* >>DP 사용
* 
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_S3_1463_1로만들기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        dp = new int[N+1];
        
        dp[1] = 0;
        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i-1]+1;
            if(i%2 == 0) {
                dp[i] = Math.min(dp[i], dp[i/2]+1);
            }
            if(i%3 == 0) {
                dp[i] = Math.min(dp[i], dp[i/3]+1);
            }
        }
        
        System.out.println(dp[N]);
    }
}
