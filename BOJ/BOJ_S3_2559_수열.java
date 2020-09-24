import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S3_2559_수열
* @date 2020.09.24
* @link https://www.acmicpc.net/problem/2559
* 
* [입력사항] 온도를 측정한 전체 날짜 N, 합을 구하기 위한 연속적인 날짜 K 
*            매일 측정한 온도 정보 
* [출력사항] 연속적인 K일의 온도의 합이 최대가 되는 값
* 
* 반복문으로 구하는 방법도 통과되지만, 시간을 줄이기 위해 덱을 사용.
* 윈도우를 한칸씩 옆으로 옮기며 온도의 합 업데이트
*/

public class BOJ_S3_2559_수열 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, K, Answer;
    static int[] nums;
    static Deque<Integer> dq;
        
    public static void main(String[] args) throws Exception{
        //입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        
        nums = new int[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokens.nextToken());           
        }
                
        //알고리즘
        int cnt = 0;
        dq = new ArrayDeque<Integer>();
        for(int i = 0; i < K; i++) {
            dq.addLast(nums[i]); //가장 처음 연속K개의 합 저장
            cnt += nums[i];
        }
        
        //이제부터는 앞에 빼고 - 뒤에 넣는 방식으로 계산
        Answer = cnt;
        for(int i = K; i < N; i++) {
            cnt -= dq.pollFirst(); //앞에 빼고
            dq.addLast(nums[i]);
            cnt += nums[i]; //뒤에 넣기
            
            Answer = Math.max(Answer, cnt); //최대값 업데이트
        }

        //출력
        System.out.println(Answer);
    }
}
