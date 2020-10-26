import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B2_2869_달팽이는올라가고싶다
* @date 2020.10.26
* @link https://www.acmicpc.net/problem/2869
* 
* [입력사항] 달팽이가 낮에 올라가는 거리 A, 밤에 자는동안 미끄러지는 거리B, 막대의 높이 V
* [출력사항] 달팽이가 나무 막대를 모두 올라가는데 며칠이 걸리는지 출력
*/

public class BOJ_B2_2869_달팽이는올라가고싶다 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int A, B, V, Answer;
    
    public static void main(String[] args) throws Exception{
        //입력
        tokens = new StringTokenizer(input.readLine());
        
        A = Integer.parseInt(tokens.nextToken());
        B = Integer.parseInt(tokens.nextToken());
        V = Integer.parseInt(tokens.nextToken());
        
        //알고리즘
        Answer = (V-A) / (A-B); //A-B로 간 거리
                
        if((V-A) % (A-B) == 0) {
            Answer += 1; //A로 이동
        }else {
            Answer += 2; //A-B로 이동 + A로 이동
        }
        
        //출력
        System.out.println(Answer);
    }
    
}
