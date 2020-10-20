/**
* @author JUNG
* @name BOJ_B2_1592_영식이와친구들
* @date 2020.10.16
* @link https://www.acmicpc.net/problem/1592
* 
* [입력사항] 영식이와 친구들의 수 N / 받을 공의 횟수 M / 공을 던질 위치 L
* [출력사항] 게임이 끝날때까지 공을 던지는 횟수
* 
* 1~N까지 원형으로 앉아 있음
* 1번부터 시작. 
* 받은 횟수가 홀수이면 현재위치에서 시계 방향으로 L번째에 있는 사람에게 전달
* 받은 횟수가 짝수이면 현재위치에서 반시계 방향으로 L번째에 있는 사람에게 전달
* 
* N개의 배열을 만들어서 자신이 받은 공 횟수 저장
* 공 횟수가 M개이면 종료
* Answer에 전체 공 전달 횟수 저장
* 
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B2_1592_영식이와친구들 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, L, Answer;
    
    public static void main(String[] args) throws Exception{
        //입력
        tokens = new StringTokenizer(input.readLine());
        
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());
        
        //알고리즘
        Answer = 0;
        int[] cnt = new int[N];
        
        int it = 0; //첫번째 시작
        while(true) {   
            if(++cnt[it] == M) { //받은 공이 M개이면 게임 끝
                break;
            }
            
            if(cnt[it]%2 == 0) { //짝수 = 반시계 = 뺄셈
                it -= L;
                if(it < 0) {
                    it += N;
                }               
            }else { //홀수 = 시계 = 덧셈
                it += L;
                if(it >= N) {
                    it -= N;
                }
            }    
            Answer++;
        }
        
        System.out.println(Answer);
    }

}






















