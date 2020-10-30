import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name BOJ_G1_1194_달이차오른다가자
 * @date 2020.10.30
 * @link https://www.acmicpc.net/problem/1194
 * 
 * [입력사항] 미로의 세로 크기 N과 가로 크기 M / N*M 크기의 미로 정보
 * [출력사항] 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력, 미로를 탈출 할 수 없으면, -1을 출력
 * 
 * 빈 곳 : 언제나 이동할 수 있다. ('.‘로 표시됨)
 * 벽 : 절대 이동할 수 없다. (‘#’)
 * 열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. (a - f)
 * 문 : 대응하는 열쇠가 있을 때만 이동할 수 있다. (A - F)
 * 민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다. (숫자 0)
 * 출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. (숫자 1)
 * 
 * 열쇠는 여러 번 사용할 수 있다.
 */

public class BOJ_G1_1194_달이차오른다가자 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, Answer;
    static int[][] map;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        // 입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        Point min = null;
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = input.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0') {
                    min = new Point(i, j, (1 << 0));
                }
            }
        }

        // 알고리즘
        Answer = -1;
        bfs(min);

        // 출력
        System.out.println(Answer);
    }

    public static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        int[][][] visited = new int[N][M][1 << 7];

        //시작점 처리
        q.offer(start);
        visited[start.r][start.c][start.keys] = 1;

        // 탐색
        while (!q.isEmpty()) {
            Point p = q.poll();

            // 미로탈출
            if (map[p.r][p.c] == '1') {
                Answer = visited[p.r][p.c][p.keys]-1;
                break;
            }

            // 사방탐색
            for (int d = 0; d < dirs.length; d++) {
                int dr = p.r + dirs[d][0];
                int dc = p.c + dirs[d][1];

                if (isIn(dr, dc) && map[dr][dc] != '#' && visited[dr][dc][p.keys] == 0) {                                                                      // 방문 가능
                    // 키가 있는 장소면
                    if ('a' <= map[dr][dc] && map[dr][dc] <= 'z') {
                        int key = 1 << (map[dr][dc]-'a'+1);
                        visited[dr][dc][p.keys | key] = visited[p.r][p.c][p.keys] + 1; //or 연산으로 키 추가
                        q.offer(new Point(dr, dc, p.keys | key));
                    }

                    // 문이고
                    else if ('A' <= map[dr][dc] && map[dr][dc] <= 'Z') {
                        //그에 맞는 키가 있으면
                        int key = 1 << (map[dr][dc]-'A'+1);
                        if((p.keys & key) > 0) { //해당 key가 존재하면
                            visited[dr][dc][p.keys] = visited[p.r][p.c][p.keys] + 1; //문 통과
                            q.offer(new Point(dr, dc, p.keys));
                        }                        
                    }

                    // 그외
                    else {
                        visited[dr][dc][p.keys] = visited[p.r][p.c][p.keys] + 1;
                        q.offer(new Point(dr, dc, p.keys));
                    }
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M);
    }

    static class Point {
        int r, c, keys;

        public Point(int r, int c, int keys) {
            super();
            this.r = r;
            this.c = c;
            this.keys = keys;
        }
    }
}
