import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D4_3124_최소스패닝트리
* @date 2020.09.04
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 정점의 개수 V, 간선의 개수 E, E개의 간선 정보(시작 정점, 도착 정점, 가중치) -> 무향그래프이므로 반대로도 연결해줘야함
* [출력사항] 최소 스패닝 트리의 가중치
* 
* 최소 스패닝 트리 -> 프림, 크루스칼
* 
* V(1≤V≤100,000)와 E(1≤E≤200,000)의 크기가 커서 answer의 타입은 long을 써줘야 한다.
* 
*/

public class SWEA_D4_3124_최소스패닝트리 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int T, V, E;
    static long Answer;
    static Edge[] edgeList; //간선리스트
    static int[] parents; //서로소집합(싸이클 여부 확인용)
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //테스트케이스 수
        T = Integer.parseInt(input.readLine());
        for(int t = 1; t <= T; t++) {
            //입력
            tokens = new StringTokenizer(input.readLine(), " ");
            V = Integer.parseInt(tokens.nextToken());
            E = Integer.parseInt(tokens.nextToken());
            edgeList = new Edge[E]; //간선중심: 간선 개수만큼 생성
            parents = new int[V]; //정점중심: 정점 개수만큼 생성
            makeSet();
            
            int from, to, weight;
            for(int i = 0; i< E; i++) {
                tokens = new StringTokenizer(input.readLine(), " ");
                from = Integer.parseInt(tokens.nextToken())-1;
                to = Integer.parseInt(tokens.nextToken())-1;
                weight = Integer.parseInt(tokens.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }
                       
            //알고리즘
            Answer = 0;
            
            //가중치를 기준으로 오름차순
            Arrays.sort(edgeList);      
            
            //정렬된 간선을 확인하여 싸이클 없이 모든 정점을 탐색할 수 있는지 확인
            int cnt = 0;
            for(Edge edge : edgeList) {
                if(unionSet(edge.from, edge.to)) { //간선이 선택되면(true)
                    Answer += edge.weight; //가중치 추가
                    if(++cnt == V-1) { //선택된 간선의 개수가 V-1개가 되면 완성
                        break;
                    }
                }           
            }
            
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }
        System.out.println(output);    
    }
    
    static void makeSet() {
        for(int i = 0; i < V; i++) {
            parents[i] = i; //대표자는 일단 자기 자신
        }
    }
    
    static int findSet(int n) {
        if(parents[n] == n) {
            return n;
        } else {
            return parents[n] = findSet(parents[n]); //패스 압축
        }
    }
    
    static boolean unionSet(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        
        if(a != b) {
            parents[b] = a;
        
            return true;
            
        } else { //서로의 부모가 같다 = 싸이클을 돈다
            return false;
        }
    }
    
    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }       
    }
    
    static String src = 
            "1\r\n" + 
            "3 3\r\n" + 
            "1 2 1\r\n" + 
            "2 3 2\r\n" + 
            "1 3 3";
}
