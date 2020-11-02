import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D0_5656_벽돌깨기
* @date 2020.11.02
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo&categoryId=AWXRQm6qfL0DFAUo&categoryType=CODE
* 
* [입력사항] 테스트 케이스의 개수 T / 구슬을 쏘는 횟수 N / 보드의 크기 W, H / W*H개의 벽돌 정보
* [출력사항] 최대로 많은 벽돌을 제거한 뒤 남은 벽돌의 개수
* 
* 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
* 벽돌은 숫자 1 ~ 9 로 표현되며, 구술이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 - 1 ) 칸 만큼 같이 제거된다.
* 
* 구슬 쏠 위치를 정하는 중복순열 + 벽돌 제거 범위 체크를 위한 bfs
* 벽돌을 제거한 후에는 벽돌을 모두 아래로 내린다(사이 빈공간이 없게끔)
* >>107,416 kb / 1,512 ms
* 
* 구슬을 쏘는 위치 순서를 정하는 알고리즘을 중복순열에서 이전 구슬을 쏜 결과 재사용이 가능한 dfs로 변경
* >> 88,716 kb / 258 ms
* 
*/

public class SWEA_D0_5656_벽돌깨기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, R, C, Answer, total, result;
    static int[][] original;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            //입력
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());
            R = Integer.parseInt(tokens.nextToken());

            total = 0;
            original = new int[R][C];
            for (int i = 0; i < R; i++) {
                tokens = new StringTokenizer(input.readLine());
                for (int j = 0; j < C; j++) {
                    original[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            //알고리즘
            Answer = Integer.MAX_VALUE;
            dfs(original, 0); //구슬 쏘는 위치  

            //결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }
        //출력
        System.out.println(output);
    }

    public static void dfs(int map[][], int n) {
        if (n == N) {
            Answer = Math.min(Answer, count(map));
            return;
        }
        
        int[][] copy = new int[R][C];

        for (int j = 0; j < C; j++) {
            copyMap(map, copy);        
            for(int i = 0; i < R; i++) {
                if(map[i][j] != 0) {
                    bfs(copy, new Point(i, j, map[i][j]));
                    clean(copy);
                    dfs(copy, n+1);
                    break;
                }
            }
        }
        
        Answer = Math.min(Answer, count(map));
    }

    public static void bfs(int[][] map, Point start) {
        Queue<Point> q = new LinkedList<Point>();

        q.offer(start);
        map[start.r][start.c] = 0;
        
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 1; i < p.n; i++) {
                for (int d = 0; d < dirs.length; d++) {
                    int dr = p.r + dirs[d][0] * i;
                    int dc = p.c + dirs[d][1] * i;

                    if (isIn(dr, dc) && map[dr][dc] != 0) {
                        q.offer(new Point(dr, dc, map[dr][dc]));
                        map[dr][dc] = 0; //벽돌 제거     
                    }
                }
            }
        }
    }

    public static void clean(int[][] map) {
        //사이 빈 공간이 없도록 벽돌을 아래로 끌어내린다.        
        Queue<Integer> q;

        for (int c = 0; c < C; c++) {
            q = new LinkedList<Integer>();
            for (int r = R - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    q.offer(map[r][c]);
                    map[r][c] = 0;
                }
            }

            int len = q.size();
            for (int i = 1; i <= len; i++) {
                map[R - i][c] = q.poll();
            }
        }
    }
    
    public static int count(int[][] map) {
        int cnt = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }     

    public static void copyMap(int[][] map, int[][] copy) {
        for (int i = 0; i < R; i++) {
            copy[i] = Arrays.copyOf(map[i], C);
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }

    static class Point {
        int r, c, n;

        public Point(int r, int c, int n) {
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }
}
