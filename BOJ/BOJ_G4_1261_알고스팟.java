import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_1261_알고스팟
* @date 2020.08.12
* @link https://www.acmicpc.net/problem/1261
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 가로 크기 M, 세로 크기 N / N*M 크기의 미로의 상태
* [출력사항] (N, M)으로 이동하기 위해 최소 몇개의 벽을 부수어야 하는지
* 
* 일반적인 큐: 이전에 방문한 곳이면 방문 못함 = 운이 안좋게 비용이 높은 녀석이 먼저 방문하면 비용이 낮은 녀석이 방문못하고 결국 최소값을 못찾게 됨.
* 우선순위 큐: 비용이 낮은 녀석이 먼저 나오게 함으로써 비용이 높은 녀석이 먼저 방문하는 것을 사전차단할 수 있음.
*/

public class BOJ_G4_1261_알고스팟 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    static int Answer = Integer.MAX_VALUE, R, C;
    static int[][] map;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        C = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());
        map = new int[R][C];
        
        for(int i = 0; i < R; i++) {
            String line = input.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j)-'0';
            }
        }
        
        bfs(0,0); //출발점
        
        System.out.println(Answer);
    }
    
    static void bfs(int r, int c) {
        boolean[][] visited = new boolean[R][C];
        PriorityQueue<node> queue = new PriorityQueue<>(); //우선순위 큐???????? cost가 작은 녀석이 우선이 되도록 함
        
        queue.offer(new node(r, c, 0));
        visited[r][c] = true;
        
        while(!queue.isEmpty()) {
            node n = queue.poll();
            
            //도착점인지 확인
            if(n.r == R-1 && n.c == C-1) {
                Answer = Math.min(Answer, n.cost);
            }            
                       
            for(int d= 0; d < dirs.length; d++) {
                int dr = n.r + dirs[d][0];
                int dc = n.c + dirs[d][1];
                
                if(isIn(dr, dc) && !visited[dr][dc]) {
                    visited[dr][dc] = true;
                    
                    //우선순위 큐를 이용하면 비용이 낮은 녀석이 먼저 튀어나와서 쓸데없이 비용이 높은녀석때문에 방문못하는 일 방지
                    if(map[dr][dc] == 1) {
                        queue.offer(new node(dr, dc, n.cost+1)); //코스트가 높아지면 우선순위 낮아짐
                    }else { 
                        queue.offer(new node(dr, dc, n.cost)); //코스트가 유지되면 우선순위가 유지되거나 높아짐
                    }                    
                }               
            }  
        }     
    }
    
    static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }
    
    
    static class node implements Comparable<node>{
        int r, c, cost;

        public node() {}
        
        public node(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
        
        public node(int r, int c, int cost) {
            this(r, c);
            this.cost = cost;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(r);
            builder.append(", ");
            builder.append(c);
            builder.append(", cost=");
            builder.append(cost);
            return builder.toString();
        }

        @Override
        public int compareTo(node o) { //우선순위 큐에서 우선순위 정렬을 위한 비교메서드
            return Integer.compare(this.cost, o.cost);
        }        
    }   

}
