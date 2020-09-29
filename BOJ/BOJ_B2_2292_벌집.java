import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B2_2292_벌집
* @date 2020.09.29
* @link https://www.acmicpc.net/problem/2292
* 
* [입력사항] 벌집 번호 N
* [출력사항] 1번 부터 몇개의 방을 지나는지 출력
* 
* >>DP 이용
* //dp[i-1] <= N < dp[i]이면 1에서 N까지 가는 데 i-1개의 방을 건넌다.
* i=3부터 dp[i] = dp[i-1] + (6*(i-2))의 규칙이 성립
* 
*/

public class BOJ_B2_2292_벌집 {
    static final int max = 18260; //1,000,000,000을 넣었을 때 18258이 나온다. 그러므로 최대 방 개수를 미리 정해둠
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N, Answer;
    
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        
        int[] dp = new int[max];         
        dp[1] = 1; //시작점=도착점이므로 한칸
        dp[2] = 2; //바로 이웃한 칸이므로 두칸(2~7번이 여기에 해당한다.)

        if(N < 2) {
            Answer = 1;
        }else {            
            int idx = 3; //i=3부터 dp[i] = dp[i-1] + (6*(i-2))의 규칙이 성립한다.
            
            while(true) {
                dp[idx] = dp[idx-1] + (6*(idx-2)); //1부터 idx거리에 떨어진 번호들 중 가장 작은 수 저장
                if(dp[idx-1] <= N && N < dp[idx]) { //dp[i-1] <= N < dp[i]이면
                    Answer = idx-1; // 1에서 N까지 가는 데 i-1개의 방을 건넌다.
                    break;
                }
                idx++;
            }
        }
        System.out.println(Answer);
    }

}
