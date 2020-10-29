import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D4_1249_보급로
* @date 2020.10.29
* @link 
* 
* [입력사항] 테스트케이스 수 T, 지도의 크기 N, N*N 크기의 지도 정보
* [출력사항] 출발지에서 도착지까지 가는 경로 중에 복구 작업에 드는 시간이 가장 작은 경로의 복구 시간
* 
* >> 거리 상관 없이 가장 복구 시간이 적게 드는 경로이기 때문에 우선순위 큐 사용
* 
*/

public class SWEA_D4_1249_보급로 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, Answer;
    static int[][] map;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            //입력
            N = Integer.parseInt(input.readLine());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = input.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0'; //char->int 변환
                }
            }

            //알고리즘
            Answer = 0;
            bfs(new Point(0, 0, 0)); //출발점 저장

            //결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        System.out.println(output);
    }

    public static void bfs(Point start) {
        PriorityQueue<Point> pq = new PriorityQueue<>(); //우선순위큐(기준:복구시간)
        boolean[][] visited = new boolean[N][N]; //방문여부

        //출발점 저장 및 방문처리
        pq.offer(start);
        visited[start.r][start.c] = true;

        //탐색
        while (!pq.isEmpty()) {
            Point p = pq.poll();

            if (p.r == N - 1 && p.c == N - 1) { //목표지점 도착
                Answer = p.sum; //걸린 복구시간 저장
                break;
            }

            //사방탐색
            for (int d = 0; d < dirs.length; d++) {
                int dr = p.r + dirs[d][0];
                int dc = p.c + dirs[d][1];

                //범위 안에 있고, 방문 안한 곳
                if (isIn(dr, dc) && !visited[dr][dc]) {
                    visited[dr][dc] = true; //방문처리
                    pq.offer(new Point(dr, dc, p.sum + map[dr][dc])); //현재 지점과 복구 시간 추가해서 큐에 삽입
                }
            }
        }

    }

    //범위 확인용 메서드
    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }

    //큐 저장용 객체(좌표와 복구시간의 합 저장)
    static class Point implements Comparable<Point> {
        int r, c, sum;

        public Point(int r, int c, int sum) {
            this.r = r;
            this.c = c;
            this.sum = sum;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.sum, o.sum); //우선순위를 정하기 위한 복구 시간 비교 메서드
        }
    }
}
