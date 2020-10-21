pimport java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_13023_ABCDE
* @date 2020.10.21
* @link https://www.acmicpc.net/problem/13023
* 
* [입력사항] 사람 수 N, 친구 관계 수 M / M개의 친구 관계
* [출력사항] 제의 조건에 맞는 A, B, C, D, E가 존재하면 1을 없으면 0을 출력
* 
* A는 B와 친구다.
* B는 C와 친구다.
* C는 D와 친구다.
* D는 E와 친구다.
* >>체인 관계. DFS로 깊이가 5 이상인지 확인하기
*/

public class BOJ_G5_13023_ABCDE {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, Answer;
    static List<Integer>[] friends;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception{
        //입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        
        friends = new List[N];
        for(int i = 0; i < N; i++) {
            friends[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            
            friends[from].add(to);
            friends[to].add(from);            
        }        
        
        //알고리즘
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                
                if((Answer = dfs(i, 1)) == 1) {
                    break;
                }
                
                visited[i] = false;
            }
        }

        //출력
        System.out.println(Answer);
    }
    
    public static int dfs(int start, int depth) {
        if(depth >= 5) {
            return 1;
        }
        
        for(int i = 0; i < friends[start].size(); i++) {
            int next = friends[start].get(i);
            
            if(!visited[next]) {
                visited[next] = true;
                if(dfs(next, depth+1) == 1) {
                    return 1;
                }
                visited[next] = false;
            }
        }
        return 0;
    }
}











