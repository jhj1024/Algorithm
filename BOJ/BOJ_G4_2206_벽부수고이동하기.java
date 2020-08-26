import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_2206_벽부수고이동하기 
* @date 2020.08.26
* @link https://www.acmicpc.net/problem/2206
* @mem
* @time
* @caution
* [고려사항] 벽을 부수고 이동한 경우와, 벽을 부수지 않고 이동한 경우의 방문여부를 구분하여 저장해줘야 한다.
* [입력사항]
* [출력사항]
*/

public class BOJ_G4_2206_벽부수고이동하기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, Answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] dirs = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
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
        
        bfs();       
        System.out.println(Answer);
    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[R][C][2];   

        visited[0][0][0] = true;
        Point p = new Point(0, 0, 1, 0);
        queue.offer(p);

        while(!queue.isEmpty()) {
            p = queue.poll();
            
            if(p.r == R-1 && p.c == C-1) {
                Answer = Math.min(Answer, p.cnt);
            }
            
            for(int d = 0; d < dirs.length; d++) {
                int dr = p.r + dirs[d][0];
                int dc = p.c + dirs[d][1];
                
                if(isIn(dr, dc)) {
                    if(map[dr][dc] == 1 && !visited[dr][dc][p.crush] && p.crush == 0) { //벽이고 빙문한 전적 없고 부술 수 있음                       
                        visited[dr][dc][p.crush+1] = true;
                        queue.offer(new Point(dr, dc, p.cnt + 1, p.crush + 1));
                    }
                    else if(map[dr][dc] == 0 && !visited[dr][dc][p.crush]){ //그냥 갈 수있고 방문한 전적 없음
                        visited[dr][dc][p.crush] = true;
                        queue.offer(new Point(dr, dc, p.cnt + 1, p.crush));
                    }
                }              
            }
        }
        
        if(Answer == Integer.MAX_VALUE) { //도착한 녀석이 없었다면
            Answer = -1;
        }
            
    }
    
    static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0<= c && c < C);
    }
    
    static class Point{
        int r, c, cnt, crush;
        
        public Point(int r, int c, int cnt, int crush) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.crush = crush;
        }
    }
    
    static String src = "6 4\r\n" + 
            "0100\r\n" + 
            "1110\r\n" + 
            "1000\r\n" + 
            "0000\r\n" + 
            "0111\r\n" + 
            "0000";
}
