import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_1965_상자넣기
* @date 2020.09.24
* @link https://www.acmicpc.net/problem/1965
*  
* [입력사항] 상자의 개수 n, 각 상자의 크기
* [출력사항] 한 줄에 넣을 수 있는 최대의 상자 개수
* 
* >> 최장 증가 부분 수열(LIS)
*/

public class BOJ_S2_1965_상자넣기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    static int[] box, LIS;
    
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        box = new int[N];
        LIS = new int[N];
        
        tokens = new StringTokenizer(input.readLine());
        for(int i = 0; i < N; i++) {
            box[i] = Integer.parseInt(tokens.nextToken());
        }
        
        //알고리즘
        Answer = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            LIS[i] = 1;
            for(int j = 0; j < i; j++) {
                if(box[j] < box[i] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j]+1;
                }
            }
            Answer = Math.max(Answer, LIS[i]);
        }
        
        
        //출력
        System.out.println(Answer);

    }

}
