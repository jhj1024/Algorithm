import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B3_2445_별찍기8
* @date 2020.10.07
* @link https://www.acmicpc.net/problem/2445
* 
* [입력사항] 출력할 줄의 수 N
* [출력사항] 별찍기7을 90도 회전한 결과
*/

public class BOJ_B3_2445_별찍기8 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(input.readLine());

        //절반은 역삼각형 공백
        for (int i = 1; i <= N; i++) {

            //i개 별 찍고 2N-2i개 공백 찍고 다시 i개 별
            for (int j = 1; j <= i; j++) {
                output.append("*");
            }

            for (int j = 1; j <= 2*N-2*i; j++) {
                output.append(" ");
            }

            for (int j = 1; j <= i; j++) {
                output.append("*");
            }

            output.append("\n");
        }
        
        //절반은 삼각형 공백
        for (int i = N-1; i >= 1; i--) {

            //i개 별 찍고 2N-2i개 공백 찍고 다시 i개 별
            for (int j = 1; j <= i; j++) {
                output.append("*");
            }

            for (int j = 1; j <= 2*N-2*i; j++) {
                output.append(" ");
            }

            for (int j = 1; j <= i; j++) {
                output.append("*");
            }

            output.append("\n");
        }
        
        System.out.println(output);
    }
}
