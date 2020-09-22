import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B1_14696_딱지놀이
* @date 2020.09.22
* @link https://www.acmicpc.net/problem/14696
*  
* [입력사항] 딱지놀이의 총 라운드 수 N / 각 라운드마다 A가 낸 딱지의 수 M, M개의 딱지 /  각 라운드마다 B가 낸 딱지의 수 L, L개의 딱지 
* [출력사항] N개의 라운드 결과(A, B, D(무승부))
* 
* 별, 동그라미, 네모, 세모를 각각 숫자 4, 3, 2, 1로 표현
* 
* 만약 두 딱지의 별의 개수가 다르다면, 별이 많은 쪽의 딱지가 이긴다.
* 별의 개수가 같고 동그라미의 개수가 다르다면, 동그라미가 많은 쪽의 딱지가 이긴다.
* 별, 동그라미의 개수가 각각 같고 네모의 개수가 다르다면, 네모가 많은 쪽의 딱지가 이긴다.
* 별, 동그라미, 네모의 개수가 각각 같고 세모의 개수가 다르다면, 세모가 많은 쪽의 딱지가 이긴다.
* 별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부이다.
* 
*/

public class BOJ_B1_14696_딱지놀이 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N;
    static int[][] cardA, cardB;
    
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        
        cardA = new int[N][]; cardB = new int[N][]; //초기화
        
        int cnt;
        for(int i = 0; i < N; i++) {
            cardA[i] = new int[4]; cardB[i] = new int[4]; //초기화
            
            tokens = new StringTokenizer(input.readLine()); //a
            cnt = Integer.parseInt(tokens.nextToken());
            for(int a = 0; a < cnt; a++) {
                cardA[i][Integer.parseInt(tokens.nextToken())-1]++;
            }           
            
            tokens = new StringTokenizer(input.readLine()); //b
            cnt = Integer.parseInt(tokens.nextToken());
            for(int b = 0; b < cnt; b++) {
                cardB[i][Integer.parseInt(tokens.nextToken())-1]++;
            }   
        }
        
        //알고리즘
        for(int i = 0; i < N; i++) {           
            if(cardA[i][3] != cardB[i][3]) { //별(3)
                output.append(cardA[i][3] > cardB[i][3] ? 'A' : 'B').append("\n");
            }else if(cardA[i][2] != cardB[i][2]) { //동그라미(2)
                output.append(cardA[i][2] > cardB[i][2] ? 'A' : 'B').append("\n");
            }else if(cardA[i][1] != cardB[i][1]) { //네모(1)
                output.append(cardA[i][1] > cardB[i][1] ? 'A' : 'B').append("\n");
            }else if(cardA[i][0] != cardB[i][0]) { //세모(0)
                output.append(cardA[i][0] > cardB[i][0] ? 'A' : 'B').append("\n");
            }else {  //무승부
                output.append('D').append("\n");
            }
        }

        //출력
        System.out.println(output);
    }

}
