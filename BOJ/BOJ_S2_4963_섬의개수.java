import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_4963_섬의개수
* @date 2020.08.05
* @link https://www.acmicpc.net/problem/4963
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] W H / W*H 개수의 땅(1) 바다(1) / 마지막은 0 0
* [출력사항] 섬의 개수(땅이 사방 , 대각선으로 이어져 있으면 하나의 섬이다.)
*/

public class BOJ_S2_4963_섬의개수 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    
    static int R, C, Answer;
    static int[][] map;    
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
  
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        while(true) { //0 0을 입력받을때까지 테스트 반복
            //입력
            tokens = new StringTokenizer(input.readLine(), " ");
            C = Integer.parseInt(tokens.nextToken()); //너비 = 열
            R = Integer.parseInt(tokens.nextToken()); //높이 = 행
            
            if(R == 0 && C == 0) { //0 0을 입력받으면 탈출
                break;
            }
            
            map = new int[R][C];
            for(int i = 0; i < R; i++) {
                tokens = new StringTokenizer(input.readLine(), " ");
                for(int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken()); //너비 = 열
                }
            }
            
            /* --------------- 알고리즘  --------------- */
            Answer = 0;
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(map[i][j] == 1) { //아직 방문 안한 땅이면
                        bfs(new pair(i, j)); //새로운 섬을 bfs로 방문하기
                        Answer++; //방문이 끝나면 하나의 섬을 모두 탐색 한 것
                    }
                }
            }
           
            output.append(Answer).append("\n"); //섬의 개수 넣기
        }       
        System.out.print(output); //최종 출력
    }
    
    static void bfs(pair start) {
        Queue<pair> queue = new LinkedList<pair>();
        
        queue.offer(start);
        map[start.i][start.j] = -1; //방문표시하기
        
        while(!queue.isEmpty()) {
            pair p = queue.poll();
            int r = p.i;
            int c = p.j;
            
            for(int d = 0; d < dirs.length; d++) {
                int dr = r + dirs[d][0];
                int dc = c + dirs[d][1];
                
                if(0 <= dr && dr < R && 0 <= dc && dc < C && map[dr][dc] == 1) { //인접한 땅 발견
                    map[dr][dc] = -1;//방문표시
                    queue.offer(new pair(dr, dc)); //큐에 넣기
                }
            }
        }
        
    }

    static class pair{
        int i, j;

        public pair() {}
        
        public pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
    }
    
    static String src = "1 1\r\n" + 
            "0\r\n" + 
            "2 2\r\n" + 
            "0 1\r\n" + 
            "1 0\r\n" + 
            "3 2\r\n" + 
            "1 1 1\r\n" + 
            "1 1 1\r\n" + 
            "5 4\r\n" + 
            "1 0 1 0 0\r\n" + 
            "1 0 0 0 0\r\n" + 
            "1 0 1 0 1\r\n" + 
            "1 0 0 1 0\r\n" + 
            "5 4\r\n" + 
            "1 1 1 0 1\r\n" + 
            "1 0 1 0 1\r\n" + 
            "1 0 1 0 1\r\n" + 
            "1 0 1 1 1\r\n" + 
            "5 5\r\n" + 
            "1 0 1 0 1\r\n" + 
            "0 0 0 0 0\r\n" + 
            "1 0 1 0 1\r\n" + 
            "0 0 0 0 0\r\n" + 
            "1 0 1 0 1\r\n" + 
            "0 0";

}
