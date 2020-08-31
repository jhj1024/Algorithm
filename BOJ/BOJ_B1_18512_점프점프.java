import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B1_18512_점프점프
* @date 2020.08.31
* @link https://www.acmicpc.net/problem/18512
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] A와 B의 멀리뛰기 거리, A와 B의 시작점
* [출력사항] 두 학생이 공통적으로 지나는 지점 중에서 가장 가까운 지점, 두 학생이 공통적으로 지나는 지점이 없다면 -1을 출력
*/

public class BOJ_B1_18512_점프점프 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); //입력스트림
    static StringTokenizer tokens = null;
    static int X, Y, H1, H2, Answer;
    
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine(), " "); //한 줄 입력받아 " "을 기준으로 쪼겜
        X = Integer.parseInt(tokens.nextToken()); //A의 뜀뛰기 보폭
        Y = Integer.parseInt(tokens.nextToken()); //B의 뜀뛰기 보폭
        H1 = Integer.parseInt(tokens.nextToken()); //A의 집
        H2 = Integer.parseInt(tokens.nextToken()); //B의 집
        
        int x = 0, y = 0; //A와 B의 뜀뛰기 횟수
        while(true) { //답이 나올때까지 반복
            if(X*x > 10000 || Y*y > 10000) { //출발지점에서 10000미터 이상 진행한 경우
                Answer = -1; //오늘은 만날수 없으니 -1
                break; //반복문 탈출
            }

            if(X*x + H1 == Y*y + H2) { //뜀뛰기로 도착한 지점이 같으면
                Answer = X*x + H1; //만난 지점 저장
                break; //반복문 탈출
            }
            
            if(X*x + H1 > Y*y + H2) { //A가 더 멀리 있으면
                y++; //B가 한번 더 뜀뛰기
            }else { //반대로 B가 더 멀리 있으면
                x++; //A가 한번 더 뜀뛰기
            }
        }
        System.out.println(Answer); //정답 출력
    }
}
