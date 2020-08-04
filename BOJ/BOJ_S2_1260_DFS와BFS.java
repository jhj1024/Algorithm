import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_1260_DFS와BFS
* @date 2020.08.04
* @link https://www.acmicpc.net/problem/1260
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class BOJ_S2_1260_DFS와BFS {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N, M, V;
    
    static List<Integer>[] graph; // 각 정점별로 갈 수 있는 인접 정점의 정보를 담을 배열
    static boolean[] visited; //방문 체크
        
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		V = Integer.parseInt(tokens.nextToken());

		//graph의 크기는 N+1(1부터 시작하므로)
		graph = new List[N+1];
		
		//그래프 초기화 (데이터 추가 삽입 삭제가 없으므로 어레이가 적절)
		for(int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		//값을 읽어서 그래프 구성하기
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			
			graph[from].add(to); //from에서 갈 수 있는 to 표시.
			graph[to].add(from); //양방향이므로 to에서 갈 수 있는 from 표시.
		}
		
		//오름차순 정렬
		for(int i = 1; i < graph.length; i++) {
			Collections.sort(graph[i]);
		}
		
		//탐색하기위한 방문 배열 생성
		visited = new boolean[N+1];
		dfs(V);
		output.append("\n");
		bfs(V);
		System.out.println(output);
		
	}
	
	static void dfs(int node) {
		visited[node] = true; //방문 처리
		output.append(node).append(" ");
		
		//방문하지 않은 자식을 찾아 탐색
		List<Integer> childs = graph[node];
		for(int i = 0; i < childs.size(); i++) {
			int child = childs.get(i);
			if(!visited[child]) {
				dfs(child);
			}
			
		}
	}
	
	static void bfs(int start) {
		Arrays.fill(visited, false); //초기화

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start); //시작 노드 추가
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			Integer from = queue.poll();
			output.append(from).append(" ");
			
			//방문하지 않은 자식을 찾아 탐색
			List<Integer> childs = graph[from];
			for(int i = 0; i < childs.size(); i++) {
				int child = childs.get(i);
				if(!visited[child]) {
					visited[child] = true;
					queue.offer(child);
				}
				
			}
		}		
	}
	
	
	static String src = "4 5 1\r\n" + 
			"1 2\r\n" + 
			"1 3\r\n" + 
			"1 4\r\n" + 
			"2 4\r\n" + 
			"3 4";
}
