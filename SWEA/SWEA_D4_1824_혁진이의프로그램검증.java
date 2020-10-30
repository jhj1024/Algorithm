import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D4_1824_혁진이의프로그램검증
 * @date 2020.10.30
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4yLUiKDUoDFAUx&categoryId=AV4yLUiKDUoDFAUx&categoryType=CODE
 * 
 * [입력사항] 테스트 케이스의 수 T / 행의 길이 R, 열의 길이 C / R*C 크기의 명령어 정보
 * [출력사항] 프로그램이 정지할 수 있으면 “YES”를 출력하고, 아니면 “NO”를 출력
 * 
 * < 이동 방향을 왼쪽으로 바꾼다.
 * > 이동 방향을 오른쪽으로 바꾼다.
 * ^ 이동 방향을 위쪽으로 바꾼다.
 * v 이동 방향을 아래쪽으로 바꾼다.
 * _ 메모리에 0이 저장되어 있으면 이동 방향을 오른쪽으로 바꾸고, 아니면 왼쪽으로 바꾼다.
 * | 메모리에 0이 저장되어 있으면 이동 방향을 아래쪽으로 바꾸고, 아니면 위쪽으로 바꾼다.
 * ? 이동 방향을 상하좌우 중 하나로 무작위로 바꾼다. 방향이 바뀔 확률은 네 방향 동일하다.
 * . 아무 것도 하지 않는다.
 * @ 프로그램의 실행을 정지한다.
 * 0~9 메모리에 문자가 나타내는 값을 저장한다.
 * + 메모리에 저장된 값에 1을 더한다. 만약 더하기 전 값이 15이라면 0으로 바꾼다.
 * - 메모리에 저장된 값에 1을 뺀다. 만약 빼기 전 값이 0이라면 15로 바꾼다.
 * 
 * 가장 처음 위치는 제일 왼쪽 위에 있는 문자이고, 이동 방향은 오른쪽이다.가장 처음에는 메모리에 0이 저장되어 있다.
 * 다음 이동이 2차원 격자의 바깥으로 이동하는 방향이면, 반대편에 있는 위치로 이동한다.
 * 
 * 똑같은 상태로 방문했던 곳 도착하면 이건 정지할 수 없다고 판단하기
 * 
 */

public class SWEA_D4_1824_혁진이의프로그램검증 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, R, C;
    static String Answer;
    static char[][] cmds;
    static Queue<Point> q;
    static boolean[][][][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U D L R

    public static void main(String[] args) throws Exception {
        // 테스트 케이스
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            tokens = new StringTokenizer(input.readLine());
            R = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());

            boolean Escape = false;
            cmds = new char[R][C];
            for (int i = 0; i < R; i++) {
                String line = input.readLine();
                for (int j = 0; j < C; j++) {
                    cmds[i][j] = line.charAt(j);
                    if (cmds[i][j] == '@') {
                        Escape = true;
                    }
                }
            }

            Answer = null;
            if (!Escape) {
                Answer = "NO";
            } else {
                validate();
            }

            // 결과저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        // 출력
        System.out.println(output);

    }

    public static void validate() {
        q = new LinkedList<Point>();
        visited = new boolean[R][C][4][16]; // 행-열-방향-메모리 방문처리

        Point head = new Point(0, 0, 3, 0); // 초기 방향 오른쪽 고정
        q.offer(head);
        visited[0][0][3][0] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();

            Point next = new Point(current.r, current.c, current.dir, current.mem);
            char cmd = cmds[current.r][current.c];
            switch (cmd) {
                case '@':
                    Answer = "YES";
                    return;
                case '<':
                    next.dir = 2; // 왼
                    break;
                case '>':
                    next.dir = 3; // 오
                    break;
                case '^':
                    next.dir = 0; // 위
                    break;
                case 'v':
                    next.dir = 1; // 아래
                    break;
                case '_':
                    next.dir = current.mem == 0 ? 3 : 2;
                    break;
                case '|':
                    next.dir = current.mem == 0 ? 1 : 0;
                    break;
                case '?':
                    // 빌런
                    for(int i = 0; i < 4; i++) {
                        next.dir = i;
                        move(next);
                    }
                    continue;
                case '.':
                    // 아무것도 안함
                    break;
                case '+':
                    next.mem = ++current.mem > 15 ? 0 : current.mem;
                    break;
                case '-':
                    next.mem = --current.mem < 0 ? 15 : current.mem;
                    break;
                default: // 숫자
                    next.mem = cmd - '0';
                    break;
            }
            move(next);
        }
        
        Answer = "NO";
    }

    public static void move(Point next) {
        int dr = next.r + dirs[next.dir][0];
        int dc = next.c + dirs[next.dir][1];

        // 범위를 벗어나면 반대편 위치로 이동
        if (dr < 0) {
            dr = R - 1;
        } else if (dc < 0) {
            dc = C - 1;
        } else if (dr == R) {
            dr = 0;
        } else if (dc == C) {
            dc = 0;
        }

        if (!visited[dr][dc][next.dir][next.mem]) {
            visited[dr][dc][next.dir][next.mem] = true;
            q.offer(new Point(dr, dc, next.dir, next.mem));
        }
    }

    static class Point {
        int r, c, dir, mem;

        public Point(int r, int c, int dir, int mem) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.mem = mem;
        }
    }
}


