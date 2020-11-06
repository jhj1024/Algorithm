import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D0_2105_디저트카페
 * @date 2020.11.06
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu&categoryId=AV5VwAr6APYDFAWu&categoryType=CODE
 * 
 * [입력사항] 테스트 케이스의 개수 T / 지도 한 변의 길이 N / N * N 크기의 디저트 카페 정보
 * [출력사항] 디저트를 가장 많이 먹을 때의 디저트 수 / 디저트를 먹을 수 없는 경우 정답은 -1
 * 
 * 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다
 * 카페 투어 중에 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안 된다.
 * 같이 왔던 길을 다시 돌아가는 것도 안 된다.
 */

public class SWEA_D0_2105_디저트카페 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, Answer;
    static int[][] map;
    static int[][] dirs = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}}; // 오른아래-왼아래-왼위-오른위
    static boolean[][] visited;
    static Set<Integer> dessert;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            N = Integer.parseInt(input.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            // 알고리즘
            Answer = Integer.MIN_VALUE;
            dessert = new HashSet<Integer>();
            visited = new boolean[N][N];
            for(int i = 0; i < N-1; i++) {
                for(int j = 1; j < N-1; j++) {
                    Point start = new Point(i, j);
                    Point move = new Point(i + dirs[0][0], j+dirs[0][1]);
                    
                    visited[move.r][move.c] = true;//방문처리 
                    dessert.add(map[move.r][move.c]);
                    dfs(start, move, 0); //탐색     
                    dessert.remove(map[move.r][move.c]);
                    visited[move.r][move.c] = false;//되돌리기
                }
            }

            if(Answer < 4) { //크기가 4보다 작다는 것은 마름모를 완성하지 못한 것
                Answer = -1;
            }           
            
            // 결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        // 출력
        System.out.println(output);
    }

    public static void dfs(Point start, Point current, int dir) { // 이동 방향과 현재 좌표저장
        if(start.check(current)) { //시작점과 현재 좌표가 같으면 마름모를 완성한 것
            System.out.println(dessert);
            Answer = Math.max(Answer, dessert.size());
            return;
        }
        
      //맵 안에 존재하고 방문 안한 좌표고 새로운 디저트라면 넣으세요
        for(int d = dir; d < dirs.length; d++) {
            int dr = current.r + dirs[d][0];
            int dc = current.c + dirs[d][1];            
            
            if(isIn(dr, dc) && !visited[dr][dc] && !dessert.contains(map[dr][dc])) {
                visited[dr][dc] = true;//방문처리
                dessert.add(map[dr][dc]);//디저트 넣기
                dfs(start, new Point(dr, dc), d);
                visited[dr][dc] = false;//방문해제
                dessert.remove(map[dr][dc]);//디저트 빼기
            }  
        }
    }
    
    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }
    
    static class Point{
        int r, c;

        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
        
        public boolean check(Point c) {
            return (this.r == c.r && this.c == c.c);
        }
    }
}