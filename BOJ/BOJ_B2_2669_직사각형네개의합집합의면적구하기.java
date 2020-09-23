import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B2_2669_직사각형네개의합집합의면적구하기
* @date 2020.09.23
* @link https://www.acmicpc.net/problem/2669
* 
* [입력사항] 네 개의 직사각형들의 좌표(왼쪽 아래 꼭짓점, 오른쪽 위 꼭짓점)
* [출력사항] 네개의 직사각형이 차지하는 면적
* 
* 모든 x좌표와 y좌표는 1이상이고 100이하인 정수 >> 100*100 좌표 안에 모두 들어감
* 
*/

public class BOJ_B2_2669_직사각형네개의합집합의면적구하기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N = 4, Answer;
    static Rectangle[] rtgs;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        int lx, ly, rx, ry;

        //입력
        rtgs = new Rectangle[4];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());

            //직사각형 시작 좌표와 가로/세로 길이 저장
            lx = Integer.parseInt(tokens.nextToken()); 
            ly = Integer.parseInt(tokens.nextToken()); 
            rx = Integer.parseInt(tokens.nextToken());  
            ry = Integer.parseInt(tokens.nextToken());             
            rtgs[i] = new Rectangle(lx, ly, rx-lx, ry-ly);
        }
        
        //알고리즘
        Answer = 0;
        map = new int[101][101];

        int x, y, r, c;
        for(int idx = 0; idx < rtgs.length; idx++) {
            x = rtgs[idx].x;
            y = rtgs[idx].y;
            r = rtgs[idx].r;
            c = rtgs[idx].c;
            
            for(int i = x; i < x+r; i++) {
                for(int j = y; j < y+c; j++) {
                    if(map[i][j] == 0) {
                        Answer++; 
                        map[i][j] = 1;
                    }                   
                }
            }
        }

        //출력
        System.out.println(Answer);
    }

    static class Rectangle {
        int x, y, r, c;

        public Rectangle(int x, int y, int r, int c) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Rectangle [x=");
            builder.append(x);
            builder.append(", y=");
            builder.append(y);
            builder.append(", r=");
            builder.append(r);
            builder.append(", c=");
            builder.append(c);
            builder.append("]");
            return builder.toString();
        }

    }
}
