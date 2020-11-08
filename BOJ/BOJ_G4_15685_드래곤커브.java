import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_15685_드래곤커브
* @date 2020.11.06
* @link https://www.acmicpc.net/problem/15685

* [입력사항] 드래곤 커브의 개수 N / N개의 드래곤 커브의 정보(x, y, d, g)
* [출력사항] 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수
* 
* x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대
* 0 ≤ x ≤ 100, 0 ≤ y ≤ 100만 유효한 좌표
* 
* 1세대: 0세대에서 반시계
* 2세대: 0세대 반대 + 1세대
* 3세대: 0세대 반대 + 1세대 반대 + 2세대
* 
*/

public class BOJ_G4_15685_드래곤커브 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, Answer;
    static int[][] map;
    static Dragon[] dragons;

    public static void main(String[] args) throws Exception {
        N = 100 + 1;
        M = Integer.parseInt(input.readLine());

        dragons = new Dragon[M];
        int x, y, d, g;
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine());
            y = Integer.parseInt(tokens.nextToken());
            x = Integer.parseInt(tokens.nextToken());
            d = Integer.parseInt(tokens.nextToken());
            g = Integer.parseInt(tokens.nextToken());

            dragons[i] = new Dragon(x, y, d, g);
        }

        //알고리즘
        map = new int[N][N];
        for (int i = 0; i < M; i++) {
            makeGen(dragons[i]);
        }

        Answer = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (check(i, j)) {
                    Answer++;
                }
            }
        }

        System.out.println(Answer);
    }

    public static void makeGen(Dragon dragon) { //드래곤커브 세대 만들기
        int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

        int[] dir = new int[(int) Math.pow(2, dragon.g)];

        int dr, dc;
        
        //0세대        
        dr = dragon.x; dc = dragon.y;
        int d = dragon.d;
        
        map[dr][dc]++;
        dr += dirs[d][0]; dc += dirs[d][1];
        map[dr][dc]++;       
        dir[0] = d;

        //1세대
        if (dragon.g >= 1) {
            d = dragon.d+1 == 4 ? 0 : dragon.d+1;
            
            map[dr][dc]++;
            dr += dirs[d][0]; dc += dirs[d][1];
            map[dr][dc]++;
            dir[1] = d;
        }
        
        
        for(int i = 2; i <= dragon.g; i++) {
            int cnt = (int) Math.pow(2, (i-1)); //시작점
            int idx = cnt;
            
            //절반은 반대로
            for(int j = 0; j < cnt/2; j++) {
                d = (dir[j] + 2)%4;
                
                map[dr][dc]++;
                dr += dirs[d][0]; dc += dirs[d][1];
                map[dr][dc]++;
                dir[idx++] = d;                
            }
            
            //절반은 그대로
            for(int j = cnt/2; j < cnt; j++) {
                d = dir[j];
                
                map[dr][dc]++;
                dr += dirs[d][0]; dc += dirs[d][1];
                map[dr][dc]++;
                dir[idx++] = d; 
            }
        }
    }

    public static boolean check(int r, int c) { //정사각형 좌표가 모두 드래곤커브 좌표에 있는지 보기        
        int[][] dirs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

        for (int d = 0; d < dirs.length; d++) {
            int dr = r + dirs[d][0];
            int dc = c + dirs[d][1];

            if (map[dr][dc] == 0) { //드래곤 좌표에 포함되어있지 않으면
                return false;
            }
        }
        return true;
    }

    static class Dragon {
        int x, y, d, g;

        public Dragon(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }
}
