import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_3190_뱀
* @date 2020.10.29
* @link https://www.acmicpc.net/problem/3190
* 
* [입력사항] 보드의 크기 N / 사과의 개수 K / K개의 사과 위치 정보(R,C) / 
*            뱀의 방향 변환 횟수 L / L개의 뱀의 방향 변환 정보(X,S)
* [출력사항] 게임이 몇 초에 끝나는지 출력
* 
* 
* 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다. >> 뱀이 위치
* 뱀은 처음에 오른쪽을 향한다.
* 게임 시작 시간으로부터 X초 뒤에 왼쪽('L') 또는 오른쪽('D')로 90도 방향 회전
* 
* 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
* 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
* 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
* 
* 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
* 
* 오른쪽 이동중: L: 위   D: 아래
* 왼쪽 이동중:   L: 아래 D: 위
* 위쪽 이동중:   L: 왼   D: 오른
* 아래쪽 이동중: L: 오른 D: 왼
* 
*/

public class BOJ_G5_3190_뱀 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, K, R, C, L, X, Answer;
    static String S;
    static int[][] map;
    static Queue<CMD> q;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //0:위 1:아래 2:왼 3:오

    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine()); //보드의 크기
        map = new int[N+1][N+1];
        
        K = Integer.parseInt(input.readLine()); //사과의 개수
        for(int i = 0; i < K; i++) {
            tokens = new StringTokenizer(input.readLine());
            R = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());
            
            map[R][C] = 1; //보드에서 사과 위치 마킹
        }
        
        q = new LinkedList<>();
        L = Integer.parseInt(input.readLine()); //방향 변환 횟수
        for(int i = 0; i < L; i++) {
            tokens = new StringTokenizer(input.readLine());
            X = Integer.parseInt(tokens.nextToken());
            S = tokens.nextToken();
            
            q.offer(new CMD(X, S));
        }

        //알고리즘
        Answer = 1;
        BAM(); //뱀 이동 시뮬레이션

        //출력
        System.out.println(Answer);
    }
    
    public static void BAM() {   
        Deque<Point> bam = new ArrayDeque<Point>();        
        Point head = new Point(1, 1), tail = head; //현재 뱀 머리와 꼬리 좌표
        bam.offer(head);
        map[head.r][head.c] = 2; //뱀 위치 표시
        
        int currentDir = 3; //뱀 현재 방향(초기 방향: 오른쪽)
        
        while(true) { //벽에 닿거나 자기자신과 부딪히기 전까지 반복
            int dr = head.r + dirs[currentDir][0];
            int dc = head.c + dirs[currentDir][1];
            
            if(!isIn(dr, dc)) {
                break; //게임 종료
            }
            
            if(map[dr][dc] != 1) { //사과가 없으면
                tail = bam.pollLast(); //뱀 꼬리 삭제
                map[tail.r][tail.c] = 0; //보드에서 꼬리 칸 좌표를 0으로 변경                
            }
            
            head = new Point(dr, dc);
            bam.addFirst(head); //뱀 머리 추가
            map[dr][dc] = 2; //뱀 머리 이동
            
            if(!q.isEmpty() && Answer == q.peek().x) {
                currentDir = dir(currentDir, q.peek().dir); //방향 전환
                q.poll();
            }
            
            Answer++; //시간 증가
        }
    }    
    
    public static boolean isIn(int r, int c) { //벽이나 자기 몸에 부딪히지는 않는지 확인
        return (0 < r && r <= N && 0 < c && c <= N) && (map[r][c] != 2); 
    }
    
    public static int dir(int currentDir, String S) { //방향 전환 후 방향 계산
        if(currentDir == 3) { //오
            if(S.equals("L")) {
                return 0; //위
            }else {
                return 1; //아래
            }
        }else if(currentDir == 2) { //왼
            if(S.equals("L")) {
                return 1; //아래
            }else {
                return 0; //위
            }
        }else if(currentDir == 1) {//아래
            if(S.equals("L")) {
                return 3; //오
            }else {
                return 2; //왼
            }
        }else {//위
            if(S.equals("L")) {
                return 2; //왼
            }else {
                return 3; //오
            }
        }
    }
    
    static class CMD{ //방향 전환 정보 저장
        int x;
        String dir;
        
        public CMD(int x, String dir) {
            super();
            this.x = x;
            this.dir = dir;
        }
    }
    
    static class Point{ //뱀 좌표 정보 저장
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }        
    }

}
