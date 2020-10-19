import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_2178_미로탐색
* @date 2020.10.12
* @link https://www.acmicpc.net/problem/2178
* 
* [입력사항] 미로의 크기 R, C / R*C 크기의 미로 정보
* [출력사항] (R,C)로 이동하는 최소 칸 수
*/

public class BOJ_S1_2178_미로탐색 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, Answer;
    static int[][] map;
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    public static void main(String[] args) throws Exception{
        //입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        
        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            String line = input.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        //알고리즘
        Answer = 0;
        bfs(new Point(0, 0));
        
        //출력
        System.out.println(Answer);
    }

    public static void bfs(Point start) {
        int[][] visited = new int[R][C];
        Queue<Point> q = new LinkedList<Point>();
        
        q.offer(new Point(start));
        visited[start.x][start.y] = 1;
        
        while(!q.isEmpty()) {
            Point p = q.poll();
            
            if(p.x == R-1 && p.y == C-1) {
                Answer = visited[p.x][p.y];
                break;
            }
                     
            for(int d = 0; d < dirs.length; d++) {
                int dr = p.x + dirs[d][0];
                int dc = p.y + dirs[d][1];
                
                if(isIn(dr, dc) && visited[dr][dc] == 0) {
                    visited[dr][dc] = visited[p.x][p.y]+1;
                    q.offer(new Point(dr, dc));
                }                
            }
        }        
    }
    
    public static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C && map[r][c] == 1);
    }
}









