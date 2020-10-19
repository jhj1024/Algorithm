import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_11725_트리의부모찾기
* @date 2020.10.13
* @link https://www.acmicpc.net/problem/11725
* 
* [입력사항] 노드의 개수 N / 트리 상에서 연결된 두 정점
* [출력사항] 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력
* 
* DFS 사용
*/

public class BOJ_S2_11725_트리의부모찾기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static Map<Integer, List<Integer>> tree;
    static StringBuilder output = new StringBuilder();
    static List<Integer>[] map;
    static int[] visited;
    
    public static void main(String[] args) throws Exception{
        //입력
        int N = Integer.parseInt(input.readLine());
        
        map = new List[N+1];
        for(int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<Integer>();
        }        
        
        for(int i = 0; i < N-1; i++) {
            tokens = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            
            map[from].add(to);
            map[to].add(from);
        }
        
        //알고리즘
        visited = new int[N+1];
        visited[1] = 0;
        dfs(1);

        //출력
        for(int i = 2; i < visited.length; i++) {
            output.append(visited[i]).append("\n");
        }
        System.out.println(output);
    }
    
    public static void dfs(int parent) {
        for(int i = 0; i < map[parent].size(); i++) {
            int node = map[parent].get(i);
            if(visited[node] == 0) {
                visited[node] = parent;
                dfs(node);
            }
        }
    }
}
