import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_S3_2193_이친수
* @date 2020.09.22
* @link https://www.acmicpc.net/problem/2193
* @mem
* @time
* @caution
* 
* 이친수는 0으로 시작하지 않는다.
* 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
* 예를 들면 1, 10, 100, 101, 1000, 1001 등이 이친수가 된다. 
* 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.
* N(1 ≤ N ≤ 90)이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.
* 
* >>피보나치 dp[N] = dp[N-1] + dp[N-2]
* 
* 입력받는 수가 1부터 시작이기 때문에 dp[2]를 먼저 정의해두면 1을 입력받았을 때 범위문제로 런타임에러 발생.
* 자리수가 최대 90이기 때문에 int로 하면 오버플로우 발생. 출력 타입을 long으로 설정.
* 
*/

public class BOJ_S3_2193_이친수 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(input.readLine());
        long[] dp = new long[N+1];
        
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        System.out.println(dp[N]);
    }

}
