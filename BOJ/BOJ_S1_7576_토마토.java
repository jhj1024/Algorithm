import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_7576_토마토
* @date 2020.08.05
* @link https://www.acmicpc.net/problem/7576
* @mem 121788
* @time 548
* @caution 
* [고려사항] 
* [입력사항] M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수, 0은 안익은 토마토, 1은 익은토마토, -1은 빈 공간.
* [출력사항] 익은토마토는 하루가 지나면 사방의 안익은 토마토를 익힌다고 할 때, 모든 토마토가 다 익는 날짜 출력. 평생 다 안익는 토마토가 있는 경우 -1 출력.
*/

public class BOJ_S1_7576_토마토 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, cnt, Answer = -1;
    static Queue<pair> queue = new LinkedList<pair>();
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws Exception {
        //입력
        input = new BufferedReader(new StringReader(src));
        
        tokens = new StringTokenizer(input.readLine(), " ");
        C = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[R][C];
        cnt = R*C; //안익은 토마토 개수(0이되면 모든 토마토가 익은 것.)
        
        for(int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            for(int j = 0; j < C; j++) {
                int state = Integer.parseInt(tokens.nextToken());
                map[i][j] = state;
                if(state == -1) {
                    cnt--; //빈 공간이므로 빼줌
                } else if(state == 1) {
                    queue.offer(new pair(i,j)); //처음부터 익은토마토의 좌표 저장
                    cnt--; //익은녀석이니까 빼줌
                }
            }
        }
        
        /* ----------------------- 알고리즘 ----------------------- */
       
        //모든 토마토는 동시에 익기 때문에 큐를 한번 돌 때 1일이 지난다고 가정
        int size = 0;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(--size >= 0) {
                pair p = queue.poll();
                int r = p.i;
                int c = p.j;
                
                //사방탐색
                for(int d = 0; d < dirs.length; d++) {
                    int dr = r + dirs[d][0];
                    int dc = c + dirs[d][1];
                    
                    if(0 <= dr && dr < R && 0 <= dc && dc < C && map[dr][dc] == 0) { //안익은 토마토 발견
                        map[dr][dc] = 1; //토마토 익히고 
                        queue.offer(new pair(dr, dc)); //큐에 위치 저장  
                        cnt--; //전체 개수에서 빼기
                    }                   
                }                
            }
            Answer++; //하루 지남 (다 익고나서 하루를 더 보내버리기때문에 처음 Answer를 -1로 설정)
        }
        
        if(cnt != 0) {
            Answer = -1; //안익은 녀석이 남아있으니까 -1로 설정
        }
        
        System.out.println(Answer);
        
    }
    static class pair {
        int i;
        int j;
        
        public pair(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
       
    static String src = "2 2\r\n" + 
            "1 -1\r\n" + 
            "-1 1";

}
