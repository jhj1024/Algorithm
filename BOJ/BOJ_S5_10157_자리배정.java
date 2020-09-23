import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S5_10157_자리배정
* @date 2020.09.23
* @link https://www.acmicpc.net/problem/10157
* 
* [입력사항] 공연장 크기  C와 R(5 ≤ C, R ≤ 1,000), 어떤 관객의 대기번호 K(1 ≤ K ≤ 100,000,000)
* [출력사항] 대기번호 K인 관객에게 배정될 좌석번호 (x,y)
*            만일 모든 좌석이 배정되어 해당 대기번호의 관객에게 좌석을 배정할 수 없는 경우 = 0
*            
*            
* 대기번호를 가진 사람들에 대하여 (1,1)위치 좌석부터 시작하여 
* 반시계방향으로 돌아가면서 비어있는 좌석에 관객을 순서대로 배정
* 
*/

public class BOJ_S5_10157_자리배정 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, K;
    static Point Answer;


    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine(), " ");
        C = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(input.readLine());

        if (K > C * R) {
            System.out.println(0); //좌석 배정 불가
        } else {
            int a[][] = new int[R][C];

            int left = 0;
            int right = 0;
            int top = 0;
            int bottom = 0;
            int seq = 0;
            while (true) {
                for (int i = R - 1 - bottom; i >= 0 + top; i--) {
                    a[i][left] = ++seq;
                }
                left++;
                if (seq == R * C)
                    break;

                for (int i = left; i < C - right; i++) {
                    a[top][i] = ++seq;
                }
                top++;
                if (seq == R * C)
                    break;

                for (int i = top; i < R - bottom; i++) {
                    a[i][C - 1 - right] = ++seq;
                }
                right++;
                if (seq == R * C)
                    break;

                for (int i = C - 1 - right; i >= 0 + left; i--) {
                    a[R - 1 - bottom][i] = ++seq;
                }
                bottom++;
                if (seq == R * C)
                    break;
            }


            for (int i = 0; i < R; i++) {

                for (int j = 0; j < C; j++) {
                    if (a[i][j] == K)
                        System.out.println((j + 1) + " " + (R - i));
                }
            }
        }
    }

}
