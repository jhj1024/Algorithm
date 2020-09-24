import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B2_13300_방배정
* @date 2020.09.23
* @link https://www.acmicpc.net/problem/13300
* 
* [입력사항] 수학여행에 참가하는 학생 수 N, 한 방에 배정할 수 있는 최대 인원 수 K
*             N 개의 각 줄에는 학생의 성별 S(여학생인 경우에 0, 남학생인 경우에 1)와 학년 Y 
* [출력사항] 학생들을 모두 배정하기 위해 필요한 최소한의 방의 수
* 
* 남학생은 남학생끼리, 여학생은 여학생
* 한 방에는 같은 학년의 학생들을 배정
* 
*/

public class BOJ_B2_13300_방배정 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, K, Answer;
    static int[][] students;
    
    public static void main(String[] args) throws Exception{        
        //입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        
        students = new int[6+1][2]; //1~6학년 / 성별(0, 1)
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            int gender = Integer.parseInt(tokens.nextToken());
            int grade = Integer.parseInt(tokens.nextToken());
            
            students[grade][gender]++; //학생수 증가            
        }
        
        //알고리즘
        Answer = 0;
        for(int i = 1; i < students.length; i++) { //1~6학년까지 탐색
            
            //여학생            
            if(students[i][0]%K == 0) {
                Answer += students[i][0]/K;
            }else {
                Answer += students[i][0]/K + 1;
            }
                       
            //남학생
            if(students[i][1]%K == 0) {
                Answer += students[i][1]/K;
            }else {
                Answer += students[i][1]/K + 1;
            }

        }

        //출력
        System.out.println(Answer);
    }
}

