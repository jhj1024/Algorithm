import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_17070_파이프옮기기1
* @date 2020.09.23
* @link https://www.acmicpc.net/problem/17070
* 
* [입력사항] 집의 크기 N(3 ≤ N ≤ 16) / N*N 크기의 집의 상태(빈 칸은 0, 벽은 1)
* [출력사항] 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수
* 
* 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로
* 파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 파이프는 밀면서 회전시킬 수 있다. 
* 회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향
* 
* 파이프가 가로로 놓여진 경우에 가능한 이동 방법은 총 2가지, 
* 세로로 놓여진 경우에는 2가지, 
* 대각선 방향으로 놓여진 경우에는 3가지가 있다.
* 
*/

public class BOJ_G5_17070_파이프옮기기1 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        //입력
        N = Integer.parseInt(input.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        //알고리즘
        dp = new int[N][N][3];
        dp[0][1][0] = 1; //(0,1)에 가로방향의 파이프 위치.

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //가로
                if (canMove(i, j - 1) && map[i][j] == 0) {
                    dp[i][j][0] += (dp[i][j - 1][0] + dp[i][j - 1][2]);
                }

                //세로
                if (canMove(i - 1, j) && map[i][j] == 0) {
                    dp[i][j][1] += (dp[i - 1][j][1] + dp[i - 1][j][2]);

                }

                //대각
                if (canMove(i - 1, j - 1) && canMove(i - 1, j) && canMove(i, j - 1) && map[i][j] == 0) {
                    dp[i][j][2] += (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]);

                }
            }
        }

        Answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];

        //출력
        System.out.println(Answer);
    }

    public static boolean canMove(int x, int y) {
        return (0 <= x && x < N && 0 <= y && y < N && map[x][y] == 0);
    }

}
