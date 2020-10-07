import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B3_2438_별찍기1
* @date 2020.10.07
* @link https://www.acmicpc.net/problem/2438
* 
* [입력사항] 출력할 줄의 수 N
* [출력사항] 첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 줄에는 별 N개를 출력
*/

public class BOJ_B3_2438_별찍기1 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    
    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(input.readLine());
        
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= i; j++) {
                output.append("*");
            }
            output.append("\n");
        }
        
        System.out.println(output);
    }
}