import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S4_14501_퇴사
* @date 2020.10.23
* @link https://www.acmicpc.net/problem/14501
* 
* [입력사항] 퇴사까지 남은 날짜 N, N개의 상담 기간 T와 금액 P
* [출력사항] 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수
*/

public class BOJ_S4_14501_퇴사 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    static counsel[] counsels;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());       
        counsels =  new counsel[N];
        
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            counsels[i] = new counsel(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
        }

        //알고리즘 
        Answer = Integer.MIN_VALUE;
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, 0); //i번째 상담부터 시작
            visited[i] = false;
        }
        
        //출력
        System.out.println(Answer);
        
    }
    
    public static void dfs(int i, int sum) {        
        if(i >= N) { //퇴사 날짜가 되면
            Answer = Math.max(Answer, sum); //그때까지 한 상담의 총 금액 최대값 업데이트
            return;
        }
        
        int t = counsels[i].t; //해당 상담의 기간
        int p = counsels[i].p; //해당 상담의 금액
        
        if(i+t <= N) { //상담이 퇴사 날짜를 넘기지 않는다면
            visited[i] = true;
            dfs(i+t, sum+p); //이번 상담 진행
        }
        
        visited[i] = false; 
        dfs(i+1, sum); //이번 상담 제외 
    }

    static class counsel{
        int t, p;

        public counsel(int t, int p) {
            this.t = t;
            this.p = p;
        }       
    }
}
