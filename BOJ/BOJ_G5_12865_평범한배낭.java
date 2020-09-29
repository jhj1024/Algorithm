import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_12865_평범한배낭
* @date 2020.09.29
* @link https://www.acmicpc.net/problem/12865
* 
* [입력사항] 물품의 수 N, 준서가 버틸 수 있는 무게 K / 물건의 무게와 가치
* [출력사항] 배낭에 넣을 수 있는 물건들의 가치합의 최댓값
* 
* >> dp 이용
*/

public class BOJ_G5_12865_평범한배낭 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, W;
    static int[] weights, profits;
    
    public static void main(String[] args) throws Exception{
        //입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        W = Integer.parseInt(tokens.nextToken());
        
        weights = new int[N+1];
        profits = new int[N+1];
        
        for(int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(input.readLine());
            weights[i] = Integer.parseInt(tokens.nextToken());
            profits[i] = Integer.parseInt(tokens.nextToken());
        }        
        
        //알고리즘
        int[] dp = new int[W+1];
        for(int i = 1; i <= N; i++) { //모든 아이템 반복
            for(int w = W; w >= weights[i]; w--) {  //가방 최대 무게부터 자기 무게까지 시도
                if(dp[w] < profits[i] + dp[w-weights[i]]) {
                    dp[w] = profits[i] + dp[w-weights[i]];
                }
            }
        }
        
        System.out.println(dp[W]); ////마지막 아이템까지 고려한 W무게를 만족하는 최대가치
    }

}
