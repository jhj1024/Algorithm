import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S5_2563_색종이
* @date 2020.09.21
* @link https://www.acmicpc.net/problem/2563
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 색종이의 수 N / N개의 (색종이의 왼쪽 변과 도화지의 왼쪽 변 사이의 거리, 색종이의 아래쪽 변과 도화지의 아래쪽 변 사이의 거리)
* [출력사항] 색종이가 붙은 검은 영역의 넓이
* 
* 
*/

public class BOJ_S5_2563_색종이 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static Point[] papers;
    static int N, Answer;
    
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        papers = new Point[N];
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            papers[i] = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
        }

        //알고리즘
        Answer = 0;
        int[][] map = new int[100][100];
        
        for(Point paper : papers) {
            for(int i = paper.x; i < paper.x+10; i++) {
                for(int j = paper.y; j < paper.y+10; j++) {
                    if(i < 101 && j < 101) {
                        if(map[i][j] == 0) {
                            map[i][j] = 1;
                            Answer++;
                        }
                    }
                }
            }
        }

        //출력
        System.out.println(Answer);
    }
}
