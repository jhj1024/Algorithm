import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_1697_숨바꼭질
* @date 2020.08.07
* @link https://www.acmicpc.net/problem/1697
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 수빈이 위치 N, 동생 위치 K
* [출력사항] 수빈이가 동생을 찾을 수 있는 가장 빠른 시간
* 
* 수빈이는 매 초 X-1 또는 X+1 또는 2*X로 이동
* 
* X-1 또는 X+1 또는 2*X 각 경우를 재귀로 넣는 방법은?
* 
* 최단시간 = bfs의 level
* 
*/

public class BOJ_S1_1697_숨바꼭질 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, K, Answer = 0;
    
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        
        bfs(N);
        
        System.out.println(Answer);        
    }
    
    static void bfs(int n) {
        int MAX = 100000;
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[MAX+1]; //방문표시배열.
        
        int size = 0;
        
        queue.offer(n);
        visited[n] = true;
               
        outer: while(!queue.isEmpty()) {
            size = queue.size();
            while(--size >= 0) {
                int num = queue.poll();
                
                if(num == K) //이번 텀에 만날 수 있으면
                    break outer; //탈출

                if(num*2 <= MAX && !visited[num*2]) { //방문한 적 없으면
                    visited[num*2] = true;
                    queue.offer(num*2);
                }
                if(num+1 <= MAX && !visited[num+1]) { //방문한 적 없으면
                    visited[num+1] = true;
                    queue.offer(num+1);
                }
                
                if(num-1 >= 0 && !visited[num-1]) { //방문한 적 없으면
                    visited[num-1] = true;
                    queue.offer(num-1);
                }
            }
            Answer++;
        }
    }

}
















