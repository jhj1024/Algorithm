import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_17471_게리맨더링
* @date 2020.09.02
* @link https://www.acmicpc.net/problem/17471
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 구역의 개수 N, 1번 구역부터 N번 구역까지의 인구수, 각 구역과 인접한 구역의 정보
* [출력사항] 백준시를 두 선거구로 나누었을 때, 두 선거구의 인구 차이의 최솟값을 출력한다. 두 선거구로 나눌 수 없는 경우에는 -1을 출력한다.
*
* 두 구역으로 나누는 방법: 부분집합
* 만들어진 선거구 내 지역이 모두 인접한지 확인 -> bfs 사용
* 
*/

public class BOJ_G5_17471_게리맨더링 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    static int[] population;
    static int[][] adjList;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        N = Integer.parseInt(input.readLine());
                
        population = new int[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(tokens.nextToken());
        }
        
        adjList = new int[N][];        
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());            
            
            int cnt = Integer.parseInt(tokens.nextToken());
            adjList[i] = new int[cnt];            
            for(int j = 0; j < cnt; j++) {
                adjList[i][j] = Integer.parseInt(tokens.nextToken())-1;
            }
        }
        
        //알고리즘
        Answer = Integer.MAX_VALUE;
        makeSubset(); 
        
        if(Answer == Integer.MAX_VALUE) {
            Answer = -1;
        }
        System.out.println(Answer);
    }
    
    public static void makeSubset() {
      //1. 부분집합 만들기
        List<Integer> district1 = null, district2 = null;
        for(int i = 1; i < (1 << N)/2; i++) {
            district1 = new ArrayList<Integer>();
            district2 = new ArrayList<Integer>();
            for(int j = 0; j < N; j++) {
                if((i & (1 << j)) <= 0) {
                    district1.add(j);
                }else {
                    district2.add(j);
                }
            }
            
            if(check(district1) && check(district2)) { //2. 두 선거구 내 지역이 모두 인접한지 확인
                Answer = Math.min(Answer, Math.abs(calc(district1) - calc(district2))); //3. 인구차이의 최소값 저장
            }         
        }
    }
    
    public static boolean check(List<Integer> district) { //2. 선거구 지역이 모두 인접해있는지 확인
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] canVisit = new boolean[N];
        
        for(int i : district) {
            canVisit[i] = true; //방문 가능한 지역만 true로 설정
        }
        
        //한 지역에서 시작해서 선거구 내 모든 지역을 탐색할 수 있으면 인접하다고 할 수 있음.
        int cnt = 1;
        queue.offer(district.get(0));
        canVisit[district.get(0)] = false; //방문표시
        
        while(!queue.isEmpty()) {
            for(int node : adjList[queue.poll()]) {
                if(canVisit[node]) { //방문 가능한 지역이면
                    queue.offer(node);
                    canVisit[node] = false;
                    cnt++;
                }
            }
        }
        
        if(cnt == district.size()) { //선거구 내 지역을 모두 방문한 경우
            return true;
        }else {
            return false;
        }
    }
    
    public static int calc(List<Integer> district) { //3. 선거구 지역의 인구수 합산
        int result = 0;
        
        for(int i : district) {
            result += population[i];
        }

        return result;
    }
    
    static String src = 
            "6\r\n" + 
            "2 3 4 5 6 7\r\n" + 
            "2 2 3\r\n" + 
            "2 1 3\r\n" + 
            "2 1 2\r\n" + 
            "2 5 6\r\n" + 
            "2 4 6\r\n" + 
            "2 4 5";
}
