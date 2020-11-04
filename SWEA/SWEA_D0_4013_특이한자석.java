import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D0_4013_특이한자석
 * @date 2020.11.04
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH&categoryId=AWIeV9sKkcoDFAVH&categoryType=CODE
 * 
 * [입력사항] 테스트 케이스의 개수 T / 4개의 자석 상태(상태는 8개의 정수로 이루어져있고, 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로
 * 나타나있다.)
 * 회전 횟수 K / K개의 회전 방법(회전시킬 톱니바퀴의 번호, 방향(1: 시계방향, -1: 반시계방향)
 * [출력사항] 모든 자석의 회전이 끝난 후 획득한 점수의 총 합
 * 
 * 하나의 자석이 1 칸 회전될 때, 붙어 있는 자석은 서로 붙어 있는 날의 자성이 다를 경우에만 반대 방향으로 1 칸 회전된다.
 * 
 * 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.
 * 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.
 * 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.
 * 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.
 */

public class SWEA_D0_4013_특이한자석 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N = 4, M = 8, K, Answer;
    static int[][] sawtooth;
    static Rotate[] rotates;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            K = Integer.parseInt(input.readLine());
            
            sawtooth = new int[N][M];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for (int j = 0; j < M; j++) {
                    sawtooth[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            rotates = new Rotate[K];
            for (int i = 0; i < K; i++) {
                tokens = new StringTokenizer(input.readLine());
                rotates[i] = new Rotate(Integer.parseInt(tokens.nextToken())-1, Integer.parseInt(tokens.nextToken()));
            }

            // 알고리즘
            for (int i = 0; i < K; i++) {
                bfs(rotates[i]);
            }
            
            Answer = calc();

            // 결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        // 출력
        System.out.println(output);
    }

    private static int calc() {
        int sum = 0;
        
        for(int i = 0; i < N; i++) {
            if(sawtooth[i][0] == 1) {
                sum += (int) Math.pow(2, i);
            }
        }        
        return sum;
    }

    public static void bfs(Rotate rotate) {
        Queue<Rotate> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        q.offer(rotate);
        visited[rotate.num] = true;

        while (!q.isEmpty()) {
            Rotate r = q.poll();

            // 왼쪽(r.num의 6번이랑 r.num - 1의 2번과 비교)
            if (r.num - 1 >= 0 && !visited[r.num - 1] && sawtooth[r.num][6] != sawtooth[r.num - 1][2]) {
                visited[r.num - 1] = true;
                q.offer(new Rotate(r.num - 1, r.dir * -1));
            }

            // 오른쪽(r.num의 2번이랑 r.num + 1의 6번과 비교)
            if (r.num + 1 < N && !visited[r.num + 1] && sawtooth[r.num][2] != sawtooth[r.num + 1][6]) {
                visited[r.num + 1] = true;
                q.offer(new Rotate(r.num + 1, r.dir * -1));
            }
            
            // 회전
            if (r.dir == 1) { // 시계방향
                int top = sawtooth[r.num][M - 1];
                for (int i = M - 1; i >= 1; i--) {
                    sawtooth[r.num][i] = sawtooth[r.num][i-1];
                }
                sawtooth[r.num][0] = top;
                
            } else { // 반시계방향
                int top = sawtooth[r.num][0];
                for (int i = 1; i < M; i++) {
                    sawtooth[r.num][i - 1] = sawtooth[r.num][i];
                }
                sawtooth[r.num][M - 1] = top;
            }
        }
    }

    static class Rotate {
        int num, dir;

        public Rotate(int num, int dir) {
            super();
            this.num = num;
            this.dir = dir;
        }
    }
}
