import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G3_17472_다리만들기2
* @date 2020.09.04
* @link https://www.acmicpc.net/problem/17472
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 지도의 세로 크기 N과 가로 크기 M, N*M크기의 지도 정보
* [출력사항] 모든 섬을 연결하는 다리 길이의 최솟값을 출력. 모든 섬을 연결하는 것이 불가능하면 -1을 출력
* 
* 
* 섬 영역 확인하기 > bfs
* 모든 섬을 연결하는 다리 길이의 최소값 찾기 > 프림
* 
*/

public class BOJ_G3_17472_다리만들기2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int R, C, islandNum, cnt, Answer;
    static int[][] map, distance;
    static boolean[][] visited;
    static List<List<Land>> islands;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        tokens = new StringTokenizer(input.readLine(), " ");
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        
        map = new int[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        
        //알고리즘
        //1. 섬 찾고 좌표들 저장        
        islands = new ArrayList<List<Land>>();
        islandNum = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    bfs(new Land(i, j));
                    islandNum++;
                }
            }
        }
        
        //2. 섬과 섬 사이에 다리 연결하는 경우 모두 생성 - 최소 거리만 저장
        distance = new int[islandNum][islandNum];   
        for(int i = 0; i < islandNum; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        
        for(int i = 0; i < islandNum-1; i++) {
            for(int j = i+1; j < islandNum; j++) {
                connect(i, j);
            }
        }       
        
        //3. 섬을 모두 연결하는 다리 길이의 최소값 찾기
        cnt = 0;
        Answer = 0;
        primPQ();
        
        //출력
        if(cnt != islandNum) { //모두 연결이 안된 상태면
            Answer = -1;
        }
        System.out.println(Answer);
    }
      
    public static void primPQ() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] minCost = new int[islandNum]; //최소 비용 관리              
        boolean[] visited = new boolean[islandNum]; //방문 관리

        Arrays.fill(minCost, Integer.MAX_VALUE); //최소값저장을 위해 일단 최대값 저장

        //0번 정점을 시작점으로하고 큐에 저장
        minCost[0] = 0;
        pq.offer(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();

            if (!visited[minEdge.idx]) { //방문 안한 정점이면 연결하고 비용 넣기               
                cnt++;
                Answer += minEdge.cost;
                visited[minEdge.idx] = true;
            }

            //선택된 최소 비용 정점과 다른 미방문 정점과 비용 계산하여 최소값 업데이트
            for (int i = 0; i < islandNum; i++) {
                if (!visited[i] && distance[minEdge.idx][i] != 0 && distance[minEdge.idx][i] < minCost[i]) {
                    minCost[i] = distance[minEdge.idx][i]; //최소 비용 업데이트
                    pq.offer(new Edge(i, minCost[i]));
                }
            }
        }
    }    
    
    public static void connect(int a, int b) {
        List<Land> from = islands.get(a);
        List<Land> to = islands.get(b);

        for(int i = 0; i < from.size(); i++) {
            for(int j = 0; j < to.size(); j++) {
                Land l1 = from.get(i);
                Land l2 = to.get(j);
                
                //일직선상에 있으면 그 사이에 다른섬이 가로막지 않는지 확인 후 거리 저장하기
                if(l1.r == l2.r) {
                    int start = l1.c < l2.c ? l1.c : l2.c;
                    int end = l1.c > l2.c ? l1.c : l2.c;
                    int d = 0;
                                       
                    for(int k = start+1; k < end; k++) {
                        if(map[l1.r][k] == 1) { //중간에 다른 섬이 가로막고 있으면
                            d = 0; //없었던 일로...
                            break; 
                        }else {
                            d++; //다리 연결 중...
                        }
                    }
                    
                    if(d >= 2) { //다리 연결이 가능하면
                        distance[a][b] = distance[b][a] = Math.min(distance[a][b], d);
                    }                     
                }
                
                else if(l1.c == l2.c) {
                    int start = l1.r < l2.r ? l1.r : l2.r;
                    int end = l1.r > l2.r ? l1.r : l2.r;
                    int d = 0;
                    
                    for(int k = start+1; k < end; k++) {
                        if(map[k][l1.c] == 1) { //중간에 다른 섬이 가로막고 있으면
                            d = 0; //없었던 일로...
                            break; 
                        }else {
                            d++; //다리 연결 중...
                        }
                    }
                    
                    if(d >= 2) { //다리 연결이 가능하면
                        distance[a][b] = distance[b][a] = Math.min(distance[a][b], d);
                    } 
                }               
            }   
        }
        
        distance[a][a] = distance[b][b] = 0;
        
        if(distance[a][b] == Integer.MAX_VALUE) {
            distance[a][b] = distance[b][a] = 0;
        }
    }
    
    public static void bfs(Land land) {
        islands.add(new ArrayList<>());
        Queue<Land> queue = new LinkedList<>();
        
        queue.offer(land);
        visited[land.r][land.c] = true;
        islands.get(islandNum).add(new Land(land.r, land.c));
        
        while(!queue.isEmpty()) {
            Land l = queue.poll();
            for(int d = 0; d < dirs.length; d++) {
                int dr = l.r + dirs[d][0];
                int dc = l.c + dirs[d][1];
                
                if(isIn(dr, dc) && map[dr][dc] == 1 && !visited[dr][dc]) {
                    visited[dr][dc] = true;
                    queue.offer(new Land(dr, dc));
                    islands.get(islandNum).add(new Land(dr, dc));
                }
            }
        }  
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }
    
    static class Land{
        int r, c;

        public Land(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            builder.append(r);
            builder.append(", ");
            builder.append(c);
            builder.append(")");
            return builder.toString();
        }        
        
        
    }    
    
    static class Edge implements Comparable<Edge> { //간선 비용 저장
        int idx;
        int cost;

        public Edge(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    
    static String src = 
            "3 7\r\n" + 
            "1 0 0 1 0 0 1\r\n" + 
            "1 0 0 1 0 0 1\r\n" + 
            "1 1 1 1 0 0 0"; //2
    
//            "10 10\r\n" + 
//            "0 0 0 1 1 0 0 0 0 0\r\n" + 
//            "0 0 0 1 0 0 0 0 0 1\r\n" + 
//            "0 0 0 1 1 0 0 0 0 0\r\n" + 
//            "0 0 0 1 1 0 0 0 0 0\r\n" + 
//            "1 0 0 1 0 0 0 0 0 1\r\n" + 
//            "0 0 0 1 1 0 0 0 0 0\r\n" + 
//            "0 0 0 1 1 0 0 0 0 0\r\n" + 
//            "0 0 0 0 0 0 0 0 0 0\r\n" + 
//            "0 0 0 0 0 0 0 0 0 1\r\n" + 
//            "0 0 1 1 1 1 0 0 1 1"; //11
    
//            "8 8\r\n" + 
//            "0 0 1 0 0 0 0 1\r\n" + 
//            "0 0 1 1 0 0 0 1\r\n" + 
//            "0 0 1 1 1 0 0 1\r\n" + 
//            "0 0 0 0 0 0 0 0\r\n" + 
//            "0 0 0 0 0 0 0 0\r\n" + 
//            "0 0 0 0 0 0 0 0\r\n" + 
//            "0 0 0 0 0 0 0 0\r\n" + 
//            "0 0 0 0 1 0 0 1"; //8
    
//            "6 10\r\n" + 
//            "1 1 1 1 1 1 1 1 1 1\r\n" + 
//            "1 0 0 0 0 0 1 0 0 0\r\n" + 
//            "1 0 1 0 1 0 1 0 0 1\r\n" + 
//            "1 0 1 1 1 0 1 0 0 1\r\n" + 
//            "0 0 0 0 0 0 0 0 0 1\r\n" + 
//            "1 1 0 1 1 1 1 1 0 1"; //12
    
//            "7 8\r\n" + 
//            "0 0 0 0 0 0 1 1\r\n" + 
//            "1 1 0 0 0 0 1 1\r\n" + 
//            "1 1 0 0 0 0 0 0\r\n" + 
//            "1 1 0 0 0 1 1 0\r\n" + 
//            "0 0 0 0 0 1 1 0\r\n" + 
//            "0 0 0 0 0 0 0 0\r\n" + 
//            "1 1 1 1 1 1 1 1";//9
            
//            "7 8\r\n" + 
//            "0 0 0 1 1 0 0 0\r\n" + 
//            "0 0 0 1 1 0 0 0\r\n" + 
//            "1 1 0 0 0 0 1 1\r\n" + 
//            "1 1 0 0 0 0 1 1\r\n" + 
//            "1 1 0 0 0 0 0 0\r\n" + 
//            "0 0 0 0 0 0 0 0\r\n" + 
//            "1 1 1 1 1 1 1 1"; //10
    
//            "7 8\r\n" + 
//            "1 0 0 1 1 1 0 0\r\n" + 
//            "0 0 1 0 0 0 1 1\r\n" + 
//            "0 0 1 0 0 0 1 1\r\n" + 
//            "0 0 1 1 1 0 0 0\r\n" + 
//            "0 0 0 0 0 0 0 0\r\n" + 
//            "0 1 1 1 0 0 0 0\r\n" + 
//            "1 1 1 1 1 1 0 0"; //9
            
//            "7 7\r\n" + 
//            "1 1 1 0 1 1 1\r\n" + 
//            "1 1 1 0 1 1 1\r\n" + 
//            "1 1 1 0 1 1 1\r\n" + 
//            "0 0 0 0 0 0 0\r\n" + 
//            "1 1 1 0 1 1 1\r\n" + 
//            "1 1 1 0 1 1 1\r\n" + 
//            "1 1 1 0 1 1 1"; //-1
    
}
