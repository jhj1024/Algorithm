import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_2458_키순서
* @date 2020.11.04
* @link https://www.acmicpc.net/problem/2458
* 
* [입력사항] 학생들의 수 N과 두 학생 키를 비교한 횟수 M / M개의 비교 결과(번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미)
* [출력사항] 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력
* 
* 플로이드 와샬로 연결
* 모든 학생과 연결되어 있는지 확인
* 
*/

public class BOJ_G4_2458_키순서 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, Answer;
    static int[][] students;
    
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        students = new int[N][N];
        for(int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken())-1;
            int b = Integer.parseInt(tokens.nextToken())-1;
            students[a][b] = 1; //a < b
        }
        
        //알고리즘
        for(int k = 0; k < N; k++) { //경유
            for(int i = 0; i < N; i++) { //시작
                for(int j = 0; j < N; j++) { //도착
                    if(students[i][k] == 1 && students[k][j] == 1) {
                        students[i][j] = 1; //i - k - j 로 연결
                    }                   
                }
            }          
        }
        
        Answer = 0;
        for(int i = 0; i < N; i++) { //연결여부 확인
            boolean connected = true;
            for(int j = 0; j < N; j++) {
                if(i == j) {
                    continue; //자기자신은 제외
                }
                
                if(students[i][j] == 0 && students[j][i] == 0) { //한개라도 연결되지 않은 학생이 있다면
                    connected = false;
                }
            }
            
            if(connected) { //모두 연결
                Answer++;
            }            
        }

        System.out.println(Answer);

    }
}
