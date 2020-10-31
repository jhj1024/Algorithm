import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D0_1952_수영장
 * @date 2020.10.30
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&categoryId=AV5PpFQaAQMDFAUq&categoryType=CODE
 * 
 * [입력사항] 테스트 케이스의 개수 T / 각 이용권의 요금 / 1~12월까지 각 달의 이용 계획
 * [출력사항] 수영장을 이용하는 경우 중 가장 적게 지출하는 비용
 * 
 * 이용권 종류: 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권
 * 
 * >>dp를 이용하면 빠르게 풀 수 있다.
 */

public class SWEA_D0_1952_수영장 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, Answer;
    static int[] ticket, months, dp;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            ticket = new int[4]; // 각 이용 요금
            tokens = new StringTokenizer(input.readLine());
            for (int i = 0; i < ticket.length; i++) {
                ticket[i] = Integer.parseInt(tokens.nextToken());
            }

            months = new int[12]; // 1월부터 12월
            tokens = new StringTokenizer(input.readLine());
            for (int i = 0; i < months.length; i++) {
                months[i] = Integer.parseInt(tokens.nextToken());
            }

            // 알고리즘
            Answer = Integer.MAX_VALUE;
            
            dp = new int[12+3]; //3달치 비교를 위해 12+3 길이 사용
            int oneDay, oneMonth, threeMonths;
            for(int i = 3; i < 15; i++) {
                oneDay = months[i-3]*ticket[0]; //1일권
                oneMonth = ticket[1]; //1달권
                threeMonths = ticket[2]; //3달권
                
                dp[i] = Math.min(Math.min(dp[i-1]+oneDay, dp[i-1]+oneMonth), dp[i-3]+threeMonths); //이전 금액에 1일권, 1달권, 3달권을 더했을 때 가장 작은 금액 저장
            }

            Answer = Math.min(dp[14], ticket[3]); //1년치와 비교
            
            //결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        //출력
        System.out.println(output);
    }
}


