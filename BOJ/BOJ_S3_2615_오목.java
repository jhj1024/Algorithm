import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S3_2615_오목
* @date 2020.08.26
* @link https://www.acmicpc.net/problem/2615
* @mem
* @time
* @caution
* [고려사항] (1,1)이 시작임
* [입력사항] 19*19 크기의 바둑판 정보
* [출력사항] 첫줄에 검은색이 이겼을 경우에는 1을, 흰색이 이겼을 경우에는 2를, 아직 승부가 결정되지 않았을 경우에는 0을 출력, 둘째 줄에 연속된 다섯 개의 바둑알 중 가장 처음에 위치한 바둑알의 좌표
* 
* 맵에 일단 저장 후, 검은색, 흰색 좌표는 각각의 어레이에 저장
* 가장 처음에 위치한 거를 찾는 거니까 (오른쪽 탐색, 아래 탐색, 오른아래 탐색) 세가지로 확인 -> 5번째 끝에 있는게 다른 색이면 탐색할 가치가 없음
* 크기가 3인 배열을 만들어서 중간에 막히면 -1을 넣고, 더이상 그부분 탐색 안함
*/

public class BOJ_S3_2615_오목 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R = 20, C = 20, Answer = 0, r, c;
    static int[][] map;
    static List<Stone> stones;
    static int[][] dirs = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        map = new int[R][C];
        stones = new ArrayList<>();
        for(int i = 1; i < R; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j = 1; j < C; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if(map[i][j] != 0) { //바둑알이면
                    stones.add(new Stone(i, j));
                }
            }
        }
        
        for(Stone stone : stones) {
            if(check(stone)) { //승부가 갈리면
                break;
            }
        }
        
        System.out.println(Answer);
        if(Answer != 0) {
            System.out.println(r + " " + c);            
        }
    }
    
    static boolean check(Stone start) {
        int color = map[start.r][start.c];
        int[] count = new int[4]; //오른쪽, 아래, 오른아래, 오른 위 이동 네가지
        
        int dr, dc;
        for(int d = 0; d < dirs.length; d++) {
            //일단 탐색할 가치가 있는지 확인
            dr = start.r + dirs[d][0]*4;
            dc = start.c + dirs[d][1]*4;
            if(!isIn(dr, dc ) || color != map[dr][dc]) {
                continue;
            }
            
            //이전 위치에 같은 색이 있다? 그럼 6목이라 넘어간거
            dr = start.r - dirs[d][0];
            dc = start.c - dirs[d][1];
            if(isIn(dr, dc ) && color == map[dr][dc]) {
                continue;
            }

            for(int i = 1; i <= 5; i++) { //출발점을 뺀 4개가 다 같은 색인지 확인하고
                dr = start.r + dirs[d][0]*i;
                dc = start.c + dirs[d][1]*i;
                if(!isIn(dr, dc ) || color != map[dr][dc]) {
                    break;
                }
                count[d]++;
            }
        }

        if(count[0] == 4 || count[1] == 4 || count[2] == 4 || count[3] == 4) {
            Answer = color;
            r = start.r;
            c = start.c;
            return true;
        }
        return false;
    }
    
    static boolean isIn(int r, int c) {
        return (0 < r && r < R && 0 < c && c < C); //범위 안에 있는지 확인(1부터 시작)
    }
    
    
    static class Stone implements Comparable<Stone>{
        int r, c;

        public Stone(int r, int c) {
            super();
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

        @Override
        public int compareTo(Stone o) { //열 오름차순
            if(this.r == o.r) {
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.c, o.c);
        }   
    }
    
    static String src = 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 1 2 2 2 2 2 2 1 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 2 1 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
}
