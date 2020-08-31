import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_18513_샘터
* @date 2020.08.31
* @link https://www.acmicpc.net/problem/18513
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] N개의 샘터, K채의 집, N개의 샘터 좌표
* [출력사항] 모든 집에 대한 불행도의 합의 최솟값
* 
* 무조건 반복문으로 해결하려고 했더니 시간초과 발생.
* bfs를 이용하여 집을 지을 수 있으면 해당좌표로부터 -1, 1거리에 떨어진 거리에 다시 집을 지을 수 있는지 확인하기 위해 큐에 넣는 행동 반복
* 
*/

public class BOJ_G5_18513_샘터 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, K;
    static long Answer; //데이터타입을 long으로 해야 통과
    static Set<Integer> homes;
    static Queue<Pair> queue;
    static int[] dirs = {-1, 1};
    
    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine(), " ");        
        N = Integer.parseInt(tokens.nextToken()); //샘터의 수
        K = Integer.parseInt(tokens.nextToken()); //지으려고 하는 집의 수
        
        queue = new LinkedList<Pair>(); //샘터 주변을 탐색하며 집을 짓기위한 큐.
        homes = new HashSet<Integer>(); //집 좌표 저장 -> 음수도 존재하고 범위도 넓어서 boolean 배열이 아닌 integer를 저장하는 set 사용 
        tokens = new StringTokenizer(input.readLine(), " ");
        for(int n = 0; n < N; n++) {
            int chicken = Integer.parseInt(tokens.nextToken()); //샘터 좌표 저장 
            homes.add(chicken); //집 좌표와 샘터 좌표가 같을 수 없으므로 좌표 표시
            queue.offer(new Pair(chicken, chicken)); //샘터 주변에 집짓게 큐에 저장
        }   
        
        //알고리즘
        Answer = 0;
        bfs();

        System.out.println(Answer); //정답 출력        
    }
    
    public static void bfs() {
        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            
            for(int d = 0; d < dirs.length; d++) {
                int newHome = p.home + dirs[d];
                
                if(!homes.contains(newHome)) { //집을 지을 수 있다면
                    Answer += Math.abs(p.chicken - newHome);//불행도 계산하여 누적
                    queue.offer(new Pair(newHome, p.chicken));//큐에 집어넣기
                    homes.add(newHome);//좌표 표시
                    
                    if(--K == 0) {//모든 집을 다 지었다면
                        return; //집짓기 종료
                    }
                }
            }
        }
    }
    
    static class Pair{
        int home, chicken;

        public Pair(int home, int chicken) {
            this.home = home;
            this.chicken = chicken;
        }        
    }
}
