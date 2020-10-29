import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_2638_치즈
* @date 2020.10.29
* @link https://www.acmicpc.net/problem/2638
* 
* [입력사항] 보드 크기 N, M / N*M 크기의 보드 정보(치즈:1)
* [출력사항] 치즈가 모두 녹아 없어지는데 걸리는 시간
* 
* 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 
* 정확히 한시간만에 녹아 없어져 버린다.
* 
* 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다.
*/

public class BOJ_G4_2638_치즈 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, Count, Answer;
    static int[][] map;
    static int[][] visited;
    static List<Point> cheeses;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        int cheese = 0;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 1) {
                    cheese++;
                }
            }
        }

        //알고리즘
        Answer = 0;        
        while ((cheese -= Count) != 0) {
            Count = 0;
            bfs(new Point(0, 0));
            Answer++;
        }

        //출력
        System.out.println(Answer);
    }

    public static void bfs(Point start) {
        Queue<Point> q = new LinkedList<Point>();
        visited = new int[N][M]; //방문안함:0 치즈아님:1 치즈:2
        cheeses = new ArrayList<Point>();

        //시작점 방문처리
        q.offer(start);
        visited[start.r][start.c] = 1;

        while (!q.isEmpty()) {
            Point p = q.poll();

            //사방탐색
            for (int d = 0; d < dirs.length; d++) {
                int dr = p.r + dirs[d][0];
                int dc = p.c + dirs[d][1];

                if (isIn(dr, dc) && visited[dr][dc] == 0) {
                    if (map[dr][dc] == 0) { //치즈 아님
                        visited[dr][dc] = 1; //방문처리
                        q.offer(new Point(dr, dc));
                    }

                    else { //치즈임       
                        visited[dr][dc] = 2; //방문처리
                        cheeses.add(new Point(dr, dc));
                    }
                }
            }
        }

        melt();
    }

    public static void melt() {
        //치즈 테두리 녹일때 사방탐색해서 (map[dr][dc] == 0 && visited[dr][dc] == true)가 두개 이상이어야지 녹이기
        for (Point c : cheeses) {
            int cnt = 0;
            
            //사방탐색
            for (int d = 0; d < dirs.length; d++) {
                int dr = c.r + dirs[d][0];
                int dc = c.c + dirs[d][1];

                if (isIn(dr, dc) && map[dr][dc] == 0 && visited[dr][dc] == 1) {
                    cnt++;
                }
            }

            if (cnt >= 2) { //치즈가 외부와 2개 이상 접촉해있으면
                map[c.r][c.c] = 0; //녹이기
                Count++;
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M);
    }


    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}


