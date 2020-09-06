import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D5_7396_종구의딸이름짓기
* @date 2020.09.06
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWm8hNu6llcDFASj&categoryId=AWm8hNu6llcDFASj&categoryType=CODE
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 테스트 케이스의 수 T, 알파벳보드의 행 크기 R과 열 크기 C, R*C 크기의 알파벳보드 정보
* [출력사항] 알파벳보드에서 만들 수 있는 이름 중에서 사전 순으로 가장 앞선 것
*
* (0, 0)에서 (R-1, C-1)로 이동하는 경로.
* 오른쪽 OR 아래로만 이동 가능
* 
* 일반적인 BFS, DFS를 사용하면 시간초과가 나기때문에 큐에서 넣을때도 고려해줘야하고
* 큐에서 뺄 때도 경로이동을 할 가치가 있는지 고려해준다.
*
*/

public class SWEA_D5_7396_종구의딸이름짓기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, R, C;
    static StringBuilder Answer;
    static char[][] map;
    static int[][] dirs = {{0, 1}, {1, 0}}; //오른쪽, 아래 이동

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //테스트케이스 수
        T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            //입력
            tokens = new StringTokenizer(input.readLine());
            R = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());

            map = new char[R][C];
            for (int i = 0; i < R; i++) {
                String line = input.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            //알고리즘
            Answer = new StringBuilder();
            bfs();

            output.append("#").append(t).append(" ").append(Answer.toString()).append("\n");
        }
        System.out.println(output);
    }

    public static void bfs() {
        Queue<Point> pq = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        pq.offer(new Point(0, 0));
        visited[0][0] = true;
        Answer.append(map[0][0]);

        char currentMIN = Character.MAX_VALUE;
        char nextMIN = Character.MAX_VALUE;
        while (!pq.isEmpty()) {
            int size = pq.size();
            while(--size >= 0) {
                Point p = pq.poll();

                if(currentMIN < map[p.r][p.c]) { //가장 작은 알파벳이 아니면 탐색 가치 없음
                    continue;
                }
                
                if (p.r == R - 1 && p.c == C - 1) { //도착
                    return;
                }
                
                //다음 level에서 가장 작거나 같을 알파벳만 빼고는 탐색 가치없음
                for(int d = 0; d < dirs.length; d++) {
                    int dr = p.r + dirs[d][0];
                    int dc = p.c + dirs[d][1];
                    
                    if(isIn(dr, dc) && !visited[dr][dc]) {
                        visited[dr][dc] = true;
                        if(nextMIN >= map[dr][dc]) {
                            nextMIN = map[dr][dc];
                            pq.offer(new Point(dr, dc));
                        }
                    }
                }                
            }
            Answer.append(nextMIN); //다음 LEVEL의 가장 작은 알파벳을 이름에 추가
            currentMIN = nextMIN; //현재 가장 작은 알파벳 업데이트
            nextMIN = Character.MAX_VALUE; //다음 레벨의 가장 작은 알파벳 초기화
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }

    static class Point{
        int r, c;

        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(r);
            builder.append(", ");
            builder.append(c);
            return builder.toString();
        }
    }


    static String src =
            "3\r\n" +
                "2 5\r\n" +
                "adbfc\r\n" +
                "dcghi\r\n" +
                "5 5\r\n" +
                "bbbbb\r\n" +
                "bbbbb\r\n" +
                "bazbb\r\n" +
                "bzbbb\r\n" +
                "bbbbb\r\n" +
                "4 5\r\n" +
                "ponoc\r\n" +
                "ohoho\r\n" +
                "hlepo\r\n" +
                "mirko";
}
