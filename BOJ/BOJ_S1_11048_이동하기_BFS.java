import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_11048_이동하기
* @date 2020.08.18
* @link https://www.acmicpc.net/problem/11048
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 가로 크기 M, 세로 크기 N / N*M 크기의 미로의 상태
* [출력사항] (N, M)으로 이동할 때 가져올 수 있는 사탕 개수의 최댓값\
* 
* bfs로 풀 때는, 해당 위치 방문 여부 + 해당 위치까지의 누적 사탕개수의 최대값을 업데이트 해줘야함.
* 방문여부가 없을 경우 누적 사탕개수가 변하지 않는 것이 방문하지 않아서인지, 현재 위치의 사탕개수가 0인지 구분할 수없어서 무조건 큐에 넣어 메모리 초과가 발생함.
* 
* ** 더 좋은 방법은 dp이기 때문에 내일 dp로 풀어보기 **
*/

public class BOJ_S1_11048_이동하기_BFS {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    static int Answer = Integer.MIN_VALUE, R, C;
    static int[][] map;
    static int[][] dirs = {{1, 0}, {0, 1}};
    
    public static void main(String[] args) throws Exception {
    	input = new BufferedReader(new StringReader(src));
    	
    	//입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        map = new int[R][C];
        
        for(int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        bfs(0,0); //출발점
        
        System.out.println(Answer);
    }

    static void bfs(int r, int c) {
        boolean[][] visited = new boolean[R][C];
        int[][] result = new int[R][C];       

        Queue<node> queue = new LinkedList<node>();        
        queue.offer(new node(r, c)); 
        visited[r][c] = true;
        result[r][c] = map[r][c];        
        
        while(!queue.isEmpty()) {
            node n = queue.poll();
            
            //도착점인지 확인
            if(n.r == R-1 && n.c == C-1) {
                Answer = Math.max(Answer, result[n.r][n.c]);
                break;
            }            
                       
            for(int d= 0; d < dirs.length; d++) {
                int dr = n.r + dirs[d][0];
                int dc = n.c + dirs[d][1];
                
                if(isIn(dr, dc)){
                	if(!visited[dr][dc]) {
                		visited[dr][dc] = true;
                		queue.offer(new node(dr, dc));
                		result[dr][dc] = result[n.r][n.c] + map[dr][dc];
                	}
                	else if(result[dr][dc] <= result[n.r][n.c] + map[dr][dc]){
                		queue.offer(new node(dr, dc));
                		result[dr][dc] = result[n.r][n.c] + map[dr][dc];
                	}	
                }          
            }  
        }     
    }
    
    static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }
    
    
    static class node{
        int r, c;

        public node() {}

        public node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}       
    }   
    
    static String src = "3 3\r\n" + 
    		"0 0 100\r\n" + 
    		"1 1 0\r\n" + 
    		"1 1 0";
}
