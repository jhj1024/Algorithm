import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B1_10163_색종이
* @date 2020.09.22
* @link https://www.acmicpc.net/problem/10163
* [입력사항] 색종이의 수 N (1 ≤ N ≤ 100) / N장의 색종이 좌표, 가로길이, 세로길리
* [출력사항] 입력에서 주어진 순서대로 각 색종이가 보이는 부분의 면적. 색종이가 보이지 않는다면 정수 0을 출력
* 
* 색종이가 놓이는 평면은 가로 최대 101칸, 세로 최대 101칸으로 구성된 격자 모양
* 가장 왼쪽 아래의 칸이 (0,0) -> 그냥 입력좌표를 왼쪽 위 꼭짓점이라고 생각하면 해결
* 
*/

public class BOJ_B1_10163_색종이 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int x, y, r, c, N;
    static int[][] map;
    static paper[] papers;
    static int[] areas;
    
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        
        map = new int[101][101];
        papers = new paper[N+1];
        areas = new int[N+1];        
        
        for(int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(input.readLine());
            x = Integer.parseInt(tokens.nextToken());
            y = Integer.parseInt(tokens.nextToken());
            r = Integer.parseInt(tokens.nextToken());
            c = Integer.parseInt(tokens.nextToken());

            papers[i] = new paper(x, y, r, c, r*c);
        }
        
        //알고리즘
        for(int n = 1; n <= N; n++) {
            x = papers[n].x;
            y = papers[n].y;
            r = papers[n].r;
            c = papers[n].c;
            
            for(int i = x; i < x+r; i++) {
                for(int j = y; j < y+c; j++) {
                    if(map[i][j] != 0) { //겹치는 경우
                        papers[map[i][j]].area--;                       
                    }
                    map[i][j] = n; //덮어쓰기
                }
            }
        }
        
        //출력
        for(int i = 1; i <= N; i++) {
            if(papers[i].area < 0) {
                System.out.println(0);
            }else {
                System.out.println(papers[i].area);
            }
        }
    }
    
    static class paper{
        int x, y, r, c, area;

        public paper(int x, int y, int r, int c, int area) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.c = c;
            this.area = area;
        }

    }
    
}
