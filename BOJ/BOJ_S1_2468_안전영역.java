import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
* @author JUNG
* @name BOJ_S1_2468_안전영역
* @date 2020.08.07
* @link https://www.acmicpc.net/problem/2468
* @mem
* @time
* @caution 
* [고려사항] 각 지대 높이만큼 물이 차오를 때마다 물보다 높은 지대의 영역이 몇개인지 체크하고, 최대값 저장
* [입력사항] 정사각형 map의 너비 N / 각 지대의 높이
* [출력사항] 물에 잠기지 않는 안전한 영역의 최대 개수
* 
* 그전까지는 0과 1로 된 map에서 bfs/dfs를 해봤다면, 이번 문제는 특정 조건(h보다 클 때)에서 탐색이 발동되는 문제이다.
* 아무 지역도 물에 잠기지 않을 수도 있다는 조건이 포함되어있으므로, 물의 높이가 0인 경우도 추가로 고려해줘야 한다.
*/

public class BOJ_S1_2468_안전영역 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, cnt = 0, Answer = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
 
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        TreeSet<Integer> heights = new TreeSet<Integer>(); //지대의 높이 저장(중복 제거)
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];

        heights.add(0); //아무 지역도 물에 잠기지 않을 수 있다는 조건이 있으므로 0도 삽입
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            for(int j = 0; j < N; j++) {
                int h = Integer.parseInt(tokens.nextToken());
                map[i][j] = h; //각 지대 높이 저장
                heights.add(h); //지대 높이(나중에 물 높이로 쓸 예정) 추가
            }
        }
        
        /* --------------------------- 알고리즘 --------------------------- */
        //물 높이 개수만큼 반복하면서 해당 높이 이상의 지대만 사방탐색하여 남은 영역 개수 구하기(B F S)
        while(!heights.isEmpty()) {
            cnt = 0;
            int h = heights.pollFirst(); //가장 작은 높이 하나 꺼내기
            visited = new boolean[N][N]; //방문표시 배열 초기화
            
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(map[r][c] > h && !visited[r][c]) { //물에 잠기지 않았고, 방문하지 않은 곳이면
                        bfs(r, c, h); //방문하여 안전지역 탐색
                        cnt++; //안전지역 개수 증가
                    }
                }
            }
            Answer = Math.max(Answer, cnt); //안전지역 개수의 최대값 저장
        }
        System.out.println(Answer);
    }
    
    static void bfs(int i, int j, int height) {
        Queue<pair> queue = new LinkedList<pair>();
        
        queue.offer(new pair(i, j)); //시작지점 넣기
        visited[i][j] = true; //방문표시
        
        while(!queue.isEmpty()) {
            pair p = queue.poll();
            int r = p.i, c = p.j;
            
            for(int d = 0; d < dirs.length; d++) {
                int dr = r + dirs[d][0];
                int dc = c + dirs[d][1];
                
                if(isIn(dr, dc) && map[dr][dc] > height && !visited[dr][dc]) { //지대 영역 안에 있고, 물 높이보다 지대가 높고, 방문하지 않은 곳이면
                    visited[dr][dc] = true; //방문표시
                    queue.offer(new pair(dr, dc)); //큐에 저장
                }
            }
            
        }        
    }
    
    static boolean isIn(int i, int j) { //지대 영역 안에 있는지 확인하는 메서드
        return (0 <= i && i < N && 0 <= j && j < N);
    }
    
    
    static class pair{
        int i, j;

        public pair(int i, int j) {
            this.i = i;
            this.j = j;
        }       
    }
    
    static String src = "5\r\n" + 
            "1 1 1 1 1\r\n" + 
            "1 1 1 1 1\r\n" + 
            "1 1 1 1 1\r\n" + 
            "1 1 1 1 1\r\n" + 
            "1 1 1 1 1";
}
