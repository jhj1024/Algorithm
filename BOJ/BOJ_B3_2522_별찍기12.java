import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B3_2522_별찍기12
* @date 2020.10.07
* @link https://www.acmicpc.net/problem/2522
* 
* [입력사항] 출력할 줄의 수 N
* [출력사항] 별찍기5를 90도 회전한 결과
*/

public class BOJ_B3_2522_별찍기12 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(input.readLine());

        for (int i = 1; i <= N; i++) {
            for(int j= 1; j <= N-i; j++) {
                output.append(" ");
            }
            for(int j= 1; j <= i; j++) {
                output.append("*");
            }
            output.append("\n");
        }
        
        for (int i = 1; i <= N-1; i++) {
            for(int j= 1; j <= i; j++) {
                output.append(" ");
            }
            for(int j= 1; j <= N-i; j++) {
                output.append("*");
            }
            output.append("\n");
        }
        
        System.out.println(output);
    }
}
