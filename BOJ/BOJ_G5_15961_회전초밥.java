import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name BOJ_G5_15961_회전초밥
 * @date 2020.08.28
 * @link https://www.acmicpc.net/problem/15961
 * @mem
 * @time
 * @caution
 * [고려사항]
 * [입력사항] 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c, N개의 초밥 정보
 * [출력사항] 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댓값
 * 
 * 윈도우를 1칸씩 옆으로 옮겨서 초밥의 최대가짓수 찾기 -> deque로 앞에 빼고, 뒤에 새로운 걸 넣는 방식 사용
 * 회전초밥이라는 이름답게 처음과 끝이 연결되어 있다는 것도 고려해줘야함.
 * 가장 최대값은 k+1로 정해져 있음
 * 
 */

public class BOJ_G5_15961_회전초밥 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    static int N, d, k, c, Answer;
    static int[] sushis;

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        d = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());
        c = Integer.parseInt(tokens.nextToken());

        sushis = new int[N];
        for (int i = 0; i < N; i++) {
            sushis[i] = Integer.parseInt(input.readLine());
        }

        //알고리즘    
        Answer = Integer.MIN_VALUE;
        select();


        //출력
        System.out.println(Answer);
    }


    public static void select() {
        int cnt = 0;
        Deque<Integer> select = new ArrayDeque<Integer>(); //k개의 초밥 저장
        int[] visited = new int[d + 1]; //1~d 종류    

        //일단 k개를 덱에 넣고 시작
        for (int i = 0; i < k; i++) {
            if (visited[sushis[i]]++ == 0) {               
                cnt++;
            }
            select.offerLast(sushis[i]);
        }
        
        //c가 0개면 가짓수에 1 추가
        if(visited[c] == 0) {
            visited[c]++;
            cnt++;
        }
                        
        int idx = 0;
        for (int i = k; i < N+k; i++) {
            //System.out.println(select + " " + cnt);
            if(cnt == k+1) { //먹을 수 있는 종류의 최댓값
                return;
            }
            
            if(i >= N) {
                idx = i-N;
            }else {
                idx = i;
            }
            
            //앞에 빼고, 방문 해제하고
            if(--visited[select.pollFirst()] == 0) {
                cnt--;
            }
            
            
            //새로운건 뒤에 넣고 방문처리
            select.offerLast(sushis[idx]);
            if(visited[sushis[idx]]++ == 0) { 
                cnt++;
            }            
            
            //c가 0개면 가짓수에 1 추가
            if(visited[c] == 0) {
                visited[c]++;
                cnt++;
            }
            
            Answer = Math.max(Answer, cnt);            
        }
    }


    static String src =
            "8 50 4 7\r\n" + 
            "2\r\n" + 
            "7\r\n" + 
            "9\r\n" + 
            "25\r\n" + 
            "7\r\n" + 
            "9\r\n" + 
            "7\r\n" + 
            "30";
}
