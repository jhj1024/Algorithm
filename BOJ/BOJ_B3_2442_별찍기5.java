import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B3_2442_별찍기5
* @date 2020.10.07
* @link https://www.acmicpc.net/problem/2442
* 
* [입력사항] 출력할 줄의 수 N
* [출력사항] 첫째 줄에는 별 1개, 둘째 줄에는 별 3개, ..., N번째 줄에는 별 2×N-1개를 출력(가운데 정렬)
*/

public class BOJ_B3_2442_별찍기5 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(input.readLine());

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
