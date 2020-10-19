import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B3_10950_A+B (3)
* @date 2020.10.08
* @link https://www.acmicpc.net/problem/10950
* 
* [입력사항] 테스트 케이스의 개수 T / 두 정수 A와 B
* [출력사항] 각 테스트 케이스마다의 A+B 결과
*/

public class BOJ_B3_10950_ABPlus3 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, A, B, Answer;
    
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(input.readLine());
        
        for(int t = 0; t < T; t++) {
            tokens = new StringTokenizer(input.readLine());
            
            A = Integer.parseInt(tokens.nextToken());
            B = Integer.parseInt(tokens.nextToken());
            
            output.append(A+B).append("\n");
        }
        System.out.println(output);
    }
}
