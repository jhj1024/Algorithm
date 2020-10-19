import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B5_2558_A+B(2)
* @date 2020.10.09
* @link https://www.acmicpc.net/problem/2558

* [입력사항] 두 정수 A와 B
* [출력사항] A+B
*/

public class BOJ_B5_2558_ABplus2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int A, B, Answer;
    public static void main(String[] args) throws Exception {
        A = Integer.parseInt(input.readLine());
        B = Integer.parseInt(input.readLine());
        Answer = A + B;
        System.out.println(Answer);
    }

}
