import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name BOJ_G5_2636_치즈
 * @date 2020.10.28
 * @link https://www.acmicpc.net/problem/2636
 * 
 * [입력사항] 판의 세로와 가로 길이 C, R / R*C 크기의 치즈 정보(1:있 0:없)
 * [출력사항] 치즈가 모두 녹아서 없어지는 데 걸리는 시간 / 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수
 * 
 * 판의 가장자리(<그림 1>에서 네모 칸에 X친 부분)에는 치즈가 놓여 있지 않다.
 * 치즈는 1시간마다 바깥쪽 테두리가 녹는다 (안쪽 구멍은 테두리가 열릴때까지 영향을 주지 않음)
 * 
 */


public class BOJ_G5_2636_치즈 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, Time, Count, cheese;
    static int[][] map;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //사방탐색
    static List<Point> border; //테두리 좌표 저장(나중에 한꺼번에 0으로 변경)

    public static void main(String[] args) throws Exception {
        // 입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        map = new int[R][C];
        cheese = 0;
        for (int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if(map[i][j] == 1) {
                    cheese++; //총 치즈조각 개수
                }
            }
        }

        // 알고리즘
        Time = 0; //다 녹을때까지 걸리는 시간
        Count = 0; //이번 시간에 녹는 치즈 테두리
        while((cheese -= Count) != 0) { //치즈가 다 녹을때까지 반복       
            bfs(new Point(0, 0)); //치즈 테투리를 찾으러....
            Time++; //1시간 경과
        }

        // 출력
        System.out.println(Time);
        System.out.println(Count);
    }
    
    static void bfs(Point start) {
        border = new ArrayList<>(); //치즈테두리 좌표 모음
        boolean[][] visited = new boolean[R][C]; //방문여부
        Queue<Point> q = new LinkedList<>(); //치즈가 아닌 공간 탐색
        
        //시작점 저장 및 방문 표시
        q.offer(start);
        visited[start.r][start.c] = true;
        
        //치즈가 아닌 공간을 모두 탐색할때까지 반복
        while(!q.isEmpty()) {
            Point p = q.poll();
            
            for(int d = 0; d < dirs.length; d++) { //사방탐색
                int dr = p.r + dirs[d][0];
                int dc = p.c + dirs[d][1];
                
                if(isIn(dr, dc) && visited[dr][dc] == false) { //범위안에 있고 방문하지 않은 지점
                    visited[dr][dc] = true; //방문처리
                    if(map[dr][dc] == 1) { //치즈 테두리 (0으로 만들어야 하는 곳)
                        border.add(new Point(dr, dc));
                    }else { //치즈가 아닌 공간 (탐색해야 하는 곳)
                        q.offer(new Point(dr, dc));
                    }
                }
            }
        }
 
        cheese();
    }
    
    static void cheese() { //치즈테두리 0으로 만들기
        Count = border.size(); //이번 시간에 녹을 치즈조각의 수
        
        for(int i = 0; i < Count; i++) {
            Point p = border.get(i);
            map[p.r][p.c] = 0; //녹여버리기
        }
    }

    
    static boolean isIn(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
    
    static class Point{
        int r, c;

        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
        
    }
}
