import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S5_2477_참외밭
* @date 2020.09.23
* @link https://www.acmicpc.net/problem/2477
* 
* [입력사항] 1m^2의 넓이에 자라는 참외의 개수 K, 육각형의 임의의 한 꼭짓점에서 출발하여 반시계방향으로 둘레를 돌면서 지나는 변의 방향과 길이
* [출력사항] 참외밭에서 자라는 참외의 수
* 
* 참외밭은 ㄱ-자 모양이거나 ㄱ-자를 90도, 180도, 270도 회전한 모양(┏, ┗, ┛ 모양)의 육각형
* 오른쪽은 동쪽, 왼쪽은 서쪽, 아래쪽은 남쪽, 위쪽은 북쪽
* 동쪽은 1, 서쪽은 2, 남쪽은 3, 북쪽은 4
* 
* 큰 사각형에서 작은 사각형을 빼면 참외밭의 면적 계산 가능
* 
* 패턴 1-3 / 2-4 / 3-2 / 4-1
* 
*/

public class BOJ_S5_2477_참외밭 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int K, small, big, Answer;
    static int[] dirs;
    static int[] lens;
    static int[] pattern = {0, 3, 4, 2, 1};

    public static void main(String[] args) throws Exception {
        //입력
        K = Integer.parseInt(input.readLine());

        int dir, len, maxr = Integer.MIN_VALUE, maxc = Integer.MIN_VALUE;
        dirs = new int[6];
        lens = new int[6];
        for (int i = 0; i < 6; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            dir = Integer.parseInt(tokens.nextToken());
            len = Integer.parseInt(tokens.nextToken());

            dirs[i] = dir;
            lens[i] = len;

            if (dir == 3 || dir == 4) {
                maxc = Math.max(maxc, len);
            } else {
                maxr = Math.max(maxr, len);
            }
        }

        big = maxr * maxc;

        //알고리즘
        int i = 0;
        for (i = 0; i < 5; i++) {
            if(pattern[dirs[i]] == dirs[i+1]) { //패턴에 일치하면 작은 사각형 발견
                small = lens[i] * lens[i+1];
                break;
            }
        }
        if (i == 5) { //반복문 돌때까지 패턴을 못찾음 -> len[5], len[0]이 작은 사각형
            small = lens[5] * lens[0];
        }

        Answer = (big - small)*K;

        //출력        
        System.out.println(Answer);
    }

}


