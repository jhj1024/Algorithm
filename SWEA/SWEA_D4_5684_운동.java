import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
* @author JUNG
* @name SWEA_D4_5684_운동
* @date 2020.08.
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRxnnah2sDFAUo&categoryId=AWXRxnnah2sDFAUo&categoryType=CODE
* @mem
* @time
* @caution
* [고려사항] 두 마을을 왕복하는 경우도 사이클에 포함, 같은 시작점과 도착점을 가진 간선은 최대 한 개 등장
* [입력사항] 테스트케이스의 개수 T / 건물의 수 N과 도로의 수 M / 도로의 정보 s, e, c
* [출력사항] 도로의 길이의 합이 가장 작은 사이클
* 
* 사이클을 이루는 도로의 길이의 합
* 두 마을을 왕복하는 경우도 사이클에 포함
* 
* dfs로 탐색할 때, 싸이클이면 현재까지의 도로의 길이를 저장.
* 만약 현재 최소값보다 도로의 길이가 길면 더 탐색하지 않고 끊음.
* 
* 
*/

public class SWEA_D4_5684_운동 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, M, S, E, C, Answer;
    static List<Edge>[] edges;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //테스트케이스 수
        T = Integer.parseInt(input.readLine().trim());
        for (int t = 1; t <= T; t++) {
            //입력
            tokens = new StringTokenizer(input.readLine().trim(), " ");
            N = Integer.parseInt(tokens.nextToken());
            M = Integer.parseInt(tokens.nextToken());

            Answer = Integer.MAX_VALUE;
            edges = new ArrayList[N + 1];
            for (int i = 0; i < N+1; i++) {
                edges[i] = new ArrayList<>();
            }

            for (int m = 0; m < M; m++) {
                tokens = new StringTokenizer(input.readLine().trim(), " ");
                S = Integer.parseInt(tokens.nextToken());
                E = Integer.parseInt(tokens.nextToken());
                C = Integer.parseInt(tokens.nextToken());

                edges[S].add(new Edge(E, C));

                if (S == E) { //같은 시작점과 도착점을 가진 간선
                    Answer = Math.min(Answer, C);
                }
            }

            //알고리즘           
            for(int i = 1; i <= N; i++) {
                visited = new boolean[N+1];
                visited[i] = true;
                dfs(i, 0, i);
            }

            if (Answer == Integer.MAX_VALUE) {
                Answer = -1;
            }

            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }
        System.out.println(output);
    }

    static void dfs(int current, int len, int start) {
        if (len > Answer) {
            return;
        }

        for (Edge next : edges[current]) {
            if(!visited[next.end]) {
                visited[next.end] = true;
                dfs(next.end, len+next.cost, start);
            }else if(next.end == start) { //싸이클이면
                Answer = Math.min(Answer, len + next.cost);
                return;
            }
        }
    }

    static class Edge {
        int end, cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static String src = "1\r\n" +
        "3 4\r\n" +
        "1 2 1\r\n" +
        "3 2 1\r\n" +
        "1 3 5\r\n" +
        "2 3 2";
}
