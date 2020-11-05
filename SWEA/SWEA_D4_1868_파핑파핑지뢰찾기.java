import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D4_1868_파핑파핑지뢰찾기
* @date 2020.11.05
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc&categoryId=AV5LwsHaD1MDFAXc&categoryType=CODE
* 
* [입력사항] 테스트 케이스의 수 T / 보드의 크기 N / N*N 크기의 보드 정보
* [출력사항] 최소 몇 번의 클릭을 해야 지뢰가 없는 모든 칸에 숫자가 표시될 것인지 출력
* 
* 최소로 클릭하는 방법은 최대한 한번 클릭했을 때 0이어서 주변애들을 연쇄로 보여주는 방법
* 클릭했더니 0나오는 횟수 + 클릭했더니 숫자 나오는 횟수 
* >> 클릭했더니 숫자나오는 횟수 = 전체 빈칸 - 0클릭해서 딸려나오는 개수
* 
*/

public class SWEA_D4_1868_파핑파핑지뢰찾기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, zero, chain, Answer;
    static int[][] map;
    static List<Point> mine;
    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            //출력
            N = Integer.parseInt(input.readLine());

            map = new int[N][N];
            mine = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String line = input.readLine();
                for (int j = 0; j < N; j++) {
                    if (line.charAt(j) == '*') {
                        map[i][j] = -1;
                        mine.add(new Point(i, j));
                    } else {
                        map[i][j] = 0;
                    }
                }
            }

            //알고리즘
            zero = 0;
            chain = 0;
            visited = new boolean[N][N];
            process();

            //결과저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        //출력
        System.out.println(output);
    }

    public static void process() {
        //지뢰 기준 8방 탐색하기
        for (Point m : mine) {
            visited[m.r][m.c] = true;
            count(m.r, m.c);
        }

        //0 기준 8방 탐색해서 한번 클릭에 딸려나오는 개수 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    search(new Point(i, j));
                    zero++;
                }
            }
        }

        Answer = zero + (((N * N) - mine.size()) - chain); //클릭했더니 0나오는 횟수 + 클릭했더니 숫자 나오는 횟수 
    }

    public static void count(int r, int c) { //지뢰 주변 팔방 탐색
        for (int d = 0; d < dirs.length; d++) {
            int dr = r + dirs[d][0];
            int dc = c + dirs[d][1];

            if (isIn(dr, dc) && map[dr][dc] != -1) { //지금은 빈칸이었지만
                map[dr][dc]++; //클릭하면 숫자임
            }
        }
    }

    public static void search(Point start) { //0주변 팔방 탐색
        Queue<Point> q = new LinkedList<>();

        q.offer(start);
        visited[start.r][start.c] = true;
        chain++;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int d = 0; d < dirs.length; d++) {
                int dr = p.r + dirs[d][0];
                int dc = p.c + dirs[d][1];

                if (isIn(dr, dc) && !visited[dr][dc]) {
                    if (map[dr][dc] == 0) { //탐색 대상 
                        q.offer(new Point(dr, dc));
                    }

                    //0이든 숫자든 일단 start눌렀을때 딸려나오는 애들이므로 방문처리와 딸려나오는 개수 추가
                    visited[dr][dc] = true;
                    chain++;
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

}
