import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_2589_보물섬
* @date 2020.09.29
* @link https://www.acmicpc.net/group/ranklist/8570
* 
* [입력사항] 보물지도의 크기 R, C / R*C크기의 L과 W로 표시된 보물 지도 정보
* [출력사항] 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간
* 
* 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다
* 
* >>BFS 사용
* 육지 좌표만 따로 저장 후, 해당 육지에서 가장 멀리 갈 수 있는 다른 육지와의 거리를 저장한다.
* 
*/

public class BOJ_G5_2589_보물섬 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, Answer;
    static char[][] map;
    
    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken()); //보물지도 행
        C = Integer.parseInt(tokens.nextToken()); //보물지도 열
        map = new char[R][C]; //보물지도 크기는 R*C;
        
        List<Point> list = new ArrayList<Point>(); //육지 정보 저장
        for(int i = 0; i < R; i++) {
            String line = input.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'L') { //육지이면
                    list.add(new Point(i, j, 0)); //육지인 곳의 좌표 모두 저장
                }
            }
        }
        
        //알고리즘
        Answer = Integer.MIN_VALUE; 
        int distance;
        for(int i = 0; i < list.size(); i++) {
            distance = bfs(list.get(i)); //해당 점을 시작점으로 가장 멀리까지 갈 수 있는 점까지의 최단거리 구하기
            Answer = Math.max(Answer, distance); //최단거리들 중 가장 먼 거리 저장
        }

        //출력(가장 멀리 떨어진 두 곳을 최단거리로 이동하는 시간)
        System.out.println(Answer);     
        
    } // end of execute

    public static int bfs(Point start) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};      
        Queue<Point> q = new LinkedList<Point>();
        boolean[][] visited = new boolean[R][C];
        
        //시작점 큐에 넣기
        q.offer(start);
        visited[start.x][start.y] = true;
        
        Point p = q.peek();
        while(!q.isEmpty()) {
            p = q.poll();
            for(int d = 0; d < dirs.length; d++) { //사방탐색
                int dr = p.x + dirs[d][0];
                int dc = p.y + dirs[d][1];
                
                if(isIn(dr, dc) && map[dr][dc] == 'L' && !visited[dr][dc]) { //맵 안에 있고, 육지이며, 방문가능하면
                    visited[dr][dc] = true; //방문처리
                    q.offer(new Point(dr, dc, p.cnt+1)); //큐에 넣기(이동 거리 +1 추가)
                }
            }
        }
        
        return p.cnt; //가장 마지막까지 간 거리 저장        
    }
    
    public static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }

    static class Point{
        int x, y, cnt; //좌표와 이동거리 저장

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }       
    }
    
}
