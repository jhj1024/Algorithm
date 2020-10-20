import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S4_1244_스위치켜고끄기
* @date 2020.10.19
* @link https://www.acmicpc.net/problem/1244
* 
* [입력사항] 스위치 개수 N, N개의 스위치 상태, 학생수 M, M개의 학생 성별과 숫자
* [출력사항] 스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력
*/

public class BOJ_S4_1244_스위치켜고끄기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N, M;
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        int[] switchs = new int[N+1];
        tokens = new StringTokenizer(input.readLine());
        for(int i = 1; i <= N; i++) {
            switchs[i] = Integer.parseInt(tokens.nextToken()); //스위치 상태
        }
        
        M = Integer.parseInt(input.readLine());
        int[][] students = new int[M][2];
        for(int i = 0; i < M; i++) {
           tokens = new StringTokenizer(input.readLine()); 
           students[i][0] = Integer.parseInt(tokens.nextToken()); //성별
           students[i][1] = Integer.parseInt(tokens.nextToken()); //숫자
        }
        
        //알고리즘
        for(int[] student : students) {
            if(student[0] == 1) { //남자
                int n = student[1];
                int cnt = 1;
                
                while(n*cnt <= N) {
                    switchs[n*cnt] = switchs[n*cnt] == 0 ? 1: 0;
                    cnt++;
                }               
            }else { //여자
                int n = student[1];
                int cnt = 1;
                while(true) {
                    if((n-cnt >= 1) && (n+cnt <= N) && (switchs[n-cnt] == switchs[n+cnt])) {
                        cnt++;
                    }else {
                        break;
                    }                    
                }
                
                switchs[n] = (switchs[n]== 0 ? 1 : 0);
                for(int i = 1; i < cnt; i++) {
                    switchs[n+i] = (switchs[n+i]== 0 ? 1 : 0);
                    switchs[n-i] = (switchs[n-i]== 0 ? 1 : 0);
                }               
            }
            
        }
        
        //출력
        for(int i = 1; i < switchs.length; i++) {
            output.append(switchs[i]);
            
            if(i % 20 == 0) {
                output.append("\n");
            }else {
                output.append(" ");
            }
        } 
        System.out.println(output);
    }
}
