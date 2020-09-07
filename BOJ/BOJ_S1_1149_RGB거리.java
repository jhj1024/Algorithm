import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_1149_RGB거리
* @date 2020.09.07
* @link https://www.acmicpc.net/status?from_problem=1&problem_id=1149
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 집의 수 N, 각 집을 빨,초,파로 칠하는 비용
* [출력사항] 모든 집을 칠하는 비용의 최솟값
* 
* 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
* N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
* i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
* 
* >> 다이나믹 프로그래밍
*/

public class BOJ_S1_1149_RGB거리 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N = 0;
    static int[][] RGB;
    static int Answer;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        N = Integer.parseInt(input.readLine());        
        RGB = new int[N+1][3]; //N개의 건물(1부터 시작) * RGB칠하는 가격
        
        for(int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        
        for(int i = 1; i <= N; i++) {
            RGB[i][0] += Math.min(RGB[i-1][1], RGB[i-1][2]);
            RGB[i][1] += Math.min(RGB[i-1][0], RGB[i-1][2]);
            RGB[i][2] += Math.min(RGB[i-1][0], RGB[i-1][1]);
        }
               
        //출력
        System.out.println(Math.min(RGB[N][0], Math.min(RGB[N][1], RGB[N][2])));
    }
    
    static String src = 
            "3\r\n" + 
        "26 40 83\r\n" + 
        "49 60 57\r\n" + 
        "13 89 99";
}
