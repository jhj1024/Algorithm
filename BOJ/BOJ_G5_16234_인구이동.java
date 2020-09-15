import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.awt.Point;

/**
* @author JUNG
* @name BOJ_G5_16234_인구이동
* @date 2020/09/15
* @link https://www.acmicpc.net/problem/16234
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] N(땅 크기), L(인구 차이 최솟값), R(인구차이 최댓값), N×N개의 나라에 대한 각 인구수
* [출력사항] 인구 이동 발생 횟수
* 
* bfs로 연합국가를 찾고, 하루에 연합국가가 1개 이상 나올 수 있으므로 temp에 임시로 연합 결과를 저장한 뒤
* 모든 탐색이 끝난 후에 map을 temp로 업데이트 해준다.
* 연합이 발생하지 않을때까지 반복한다.
* 
*/

public class BOJ_G5_16234_인구이동 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int[][] map, temp;
    static boolean[][] visited;
    static int N, L, R, Answer;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        Answer = 0;
        int cnt = 0;
        while (true) {//더이상 인구이동을 할 수 없을때까지
            cnt = 0;
            init();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) { //방문 안한 곳이면 bfs 돌기
                        cnt += (bfs(new Point(i, j)));
                    }
                }
            }
            map = temp; //map 업데이트
            if (cnt != 0) {
                Answer++;
            } else {
                break;
            }
        }

        System.out.println(Answer);
    }

    public static int bfs(Point point) {
        Queue<Point> queue = new LinkedList<Point>();
        List<Point> list = new ArrayList<Point>(); //연합된 국가들의 좌표
        int sum = 0; //연합국가들의 인구수

        //시작점 방문표시 및 저장
        visited[point.x][point.y] = true;
        queue.offer(point);
        list.add(point);
        sum += map[point.x][point.y];

        //탐색
        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < dirs.length; d++) {
                int dr = p.x + dirs[d][0];
                int dc = p.y + dirs[d][1];

                if (isIn(dr, dc) && !visited[dr][dc]) {
                    int diff = Math.abs(map[p.x][p.y] - map[dr][dc]);
                    if (L <= diff && diff <= R) { //국경을 열 수 있으면 큐와 list에 저장
                        visited[dr][dc] = true;
                        queue.offer(new Point(dr, dc));
                        list.add(new Point(dr, dc));
                        sum += map[dr][dc];
                    }
                }
            }
        }

        if (list.size() > 1) { //연합이 발생했을 때 연합국가의 인구수 조정(temp에 저장)
            int pop = sum / list.size();
            for (Point p : list) {
                temp[p.x][p.y] = pop;
            }
            return 1;
        } else {
            return 0;
        }


    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }

    public static void init() {
        temp = new int[N][];
        for (int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(map[i], N);
        }
        visited = new boolean[N][N];
    }
}
