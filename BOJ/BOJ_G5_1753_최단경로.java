import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_1753_최단경로
* @date 2020.09.01
* @link https://www.acmicpc.net/problem/1753
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 정점의 개수 V와 간선의 개수 E, 시작 정점의 번호 K, E개의 u에서 v로 가는 가중치 w인 간선
* [출력사항] V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로값
* 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력
* 
* 시작점에서 다른 모든 정점으로의 최단 경로 & 가중치는 10 이하의 자연수 = 다익스트라
* 범위가 1≤V≤20,000, 1≤E≤300,000로 엄청 크기 때문에 인접 행렬보다 인접리스트로 관리.
* 
*/

public class BOJ_G5_1753_최단경로 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = null;
    static int V, E, K, u, v, w, Answer;
    static LinkNode[] graph;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        tokens = new StringTokenizer(input.readLine());
        V = Integer.parseInt(tokens.nextToken()) + 1; //정점을 1부터 시작하도록 설정
        E = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(input.readLine());

        graph = new LinkNode[V]; //그래프 생성
        for(int i = 0; i < E; i++) { //단방향 그래프 정보를 받아와 저장
            tokens = new StringTokenizer(input.readLine());
            u = Integer.parseInt(tokens.nextToken());
            v = Integer.parseInt(tokens.nextToken());
            w = Integer.parseInt(tokens.nextToken());
            graph[u] = new LinkNode(v, w, graph[u]); //u에서 v로 가는데 w비용이 걸린다는 것을 저장. 링크는 최근의 자기 주소(가장 나중에 들어간 것부터 거슬로 올라가기 위해)  
        }
        
        //알고리즘
        dijkstra(K);
        System.out.println();
        dijkstraPQ(K); //우선순위큐 사용

    }

    public static void dijkstraPQ(int start) {
        PriorityQueue<LinkNode> pq = new PriorityQueue<>();        
        boolean[] visited = new boolean[V]; //방문 여부
        int[] distance = new int[V]; //모든 정점까지의 거리 관리 -> 최소값으로 계속 업데이트
        
        //출발점을 제외하고 모두 탐색할 계획
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        pq.offer(new LinkNode(start, 0, 0));
        
        while(!pq.isEmpty()) {
            LinkNode min = pq.poll(); //최소 정점 꺼내기
            visited[min.vertex] = true; //방문 처리
            
            //연결 가능한 정점을 찾아 최소값 업데이트
            LinkNode next = graph[min.vertex]; //minVertex와 연결된 정점과 가중치 저장
            while(next != null){ //next가 null일때까지
                //미방문이고 등록된 거리보다 현재 최소 정점을 거쳐서 온 거리가 짧으면 업데이트
                if(!visited[next.vertex] && min.dist + next.cost < distance[next.vertex]) {
                    distance[next.vertex] = min.dist + next.cost;
                    pq.offer(new LinkNode(next.vertex, next.cost, distance[next.vertex]));
                }
                
                //다음노드로 이동
                next = next.link;
            } 
        }

        //출력 조건에 맞게 출력
        output = new StringBuilder();
        for(int i = 1; i < V; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                output.append("INF");
            }else {
                output.append(distance[i]);
            }
            output.append("\n");
        }
        System.out.print(output);
        
    }
    
    public static void dijkstra(int start) {
        boolean[] visited = new boolean[V]; //방문 여부
        int[] distance = new int[V]; //모든 정점까지의 거리 관리 -> 최소값으로 계속 업데이트
        
        //출발점을 제외하고 모두 탐색할 계획
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        
        //모든 정점들에 대해서 처리
        for(int i = 0; i < V; i++) {
            //아직 미방문인 지점 대상으로 최소 비용의 정점 찾기
            int minVertex = 0, minCost = Integer.MAX_VALUE;
            for(int j = 1; j < V; j++) {
                if(!visited[j] && distance[j] < minCost) { //방문 안하고 거리가 최소이면
                    minCost = distance[j]; //임시로 최소 거리로 저장
                    minVertex = j; //임시로 최소 정점 저장
                }
            }
            //최종으로 최소 정점인 것을 방문 처리
            visited[minVertex] = true;
            
            //연결 가능한 정점을 찾아 최소값 업데이트
            LinkNode next = graph[minVertex]; //minVertex와 연결된 정점과 가중치 저장
            while(next != null){ //next가 null일때까지
                //미방문이고 등록된 거리보다 현재 최소 정점을 거쳐서 온 거리가 짧으면 업데이트
                if(!visited[next.vertex] && minCost + next.cost < distance[next.vertex]) {
                    distance[next.vertex] = minCost + next.cost;
                }
                
                //다음노드로 이동
                next = next.link;
            }        
        }
        
        //출력 조건에 맞게 출력
        output = new StringBuilder();
        for(int i = 1; i < V; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                output.append("INF");
            }else {
                output.append(distance[i]);
            }
            output.append("\n");
        }
        System.out.print(output);
        
    }

    static class LinkNode implements Comparable<LinkNode>{
        int vertex, cost; //정점, 비용
        int dist; //현재 정점까지의 누적 비용
        LinkNode link;

        //그래프 생성을 위한 생성자
        public LinkNode(int no, int cost, LinkNode link) {
            super();
            this.vertex = no;
            this.cost = cost;
            this.link = link;
        }

        //PQ를 위한 생성자
        public LinkNode(int no, int cost, int dist) {
            this.vertex = no;
            this.cost = cost;
            this.dist = dist;
        }

        @Override
        public int compareTo(LinkNode o) {
            return Integer.compare(this.dist, o.dist);
        }  
    }
    
    static String src = 
            "5 6\r\n" + 
            "1\r\n" + 
            "5 1 1\r\n" + 
            "1 2 2\r\n" + 
            "1 3 3\r\n" + 
            "2 3 4\r\n" + 
            "2 4 5\r\n" + 
            "3 4 6";
}
