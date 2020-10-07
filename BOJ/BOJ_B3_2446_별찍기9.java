import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B3_2446_별찍기9
* @date 2020.10.07
* @link https://www.acmicpc.net/problem/2446
* 
* [입력사항] 출력할 줄의 수 N
* [출력사항] 별찍기 6 + 별찍기 5 결과
*/

public class BOJ_B3_2446_별찍기9 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    
    public static void main(String[] args) throws Exception{
        int N = Integer.parseInt(input.readLine());
        
        for (int i = N; i > 1; i--) {
            //N-i개 공백
            for (int j = 1; j <= N - i; j++) {
                output.append(" ");
            }

            //2i-1개 별
            for (int j = 1; j <= 2 * i - 1; j++) {
                output.append("*");
            }
            output.append("\n");
        }
               
        for (int i = 1; i <= N; i++) {
            //N-i개 공백
            for (int j = 1; j <= N - i; j++) {
                output.append(" ");
            }

            //2i-1개 별
            for (int j = 1; j <= 2 * i - 1; j++) {
                output.append("*");
            }
            output.append("\n");
        }
        

        System.out.println(output);
    }
}
