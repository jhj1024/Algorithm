import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B3_2440_별찍기3
* @date 2020.10.07
* @link https://www.acmicpc.net/problem/2440
* 
* [입력사항] 출력할 줄의 수 N
* [출력사항] 첫째 줄에는 별 N개, 둘째 줄에는 별 N-1개, ..., N번째 줄에는 별 1개 출력
*/

public class BOJ_B3_2440_별찍기3 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    
    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(input.readLine());
        
        for(int i = N; i >= 1; i--) {
            for(int j = 1; j <= i; j++) {
                output.append("*");
            }
            output.append("\n");
        }
        
        System.out.println(output);
    }
}
