import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_11048_이동하기
* @date 2020.09.23
* @link https://www.acmicpc.net/problem/11048

* [입력사항] 가로 크기 M, 세로 크기 N / N*M 크기의 미로의 상태
* [출력사항] (N, M)으로 이동할 때 가져올 수 있는 사탕 개수의 최댓값\
* 
* 동적 계획법(Dynamic Programming, DP)의 Bottom-Up 방식으로 문제 풀이.
*/

public class BOJ_S1_11048_이동하기_DP {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    static int R, C;
    static int[][] map;
    static int[][] dirs = {{1, 0}, {0, 1}};
    
    public static void main(String[] args) throws Exception {
    	input = new BufferedReader(new StringReader(src));
    	
    	//입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        map = new int[R+1][C+1];
        
        for(int i = 1; i <= R; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            for(int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        //알고리즘
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                map[i][j] = map[i][j] + Math.max(map[i-1][j], map[i][j-1]);
            }
        }
        
        //출력
        System.out.println(map[R][C]);
    }

    static String src = 
            "3 4\r\n" + 
            "1 2 3 4\r\n" + 
            "0 0 0 5\r\n" + 
            "9 8 7 6"; //31
    
    //    "3 3\r\n" + 
    //    "1 0 0\r\n" + 
    //    "0 1 0\r\n" + 
    //    "0 0 1"; //3
    //    
    //    "4 3\r\n" + 
    //    "1 2 3\r\n" + 
    //    "6 5 4\r\n" + 
    //    "7 8 9\r\n" + 
    //    "12 11 10"; //47
    
}
