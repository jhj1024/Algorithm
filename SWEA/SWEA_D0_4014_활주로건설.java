import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D0_4014_활주로건설
 * @date 2020.10.28
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&categoryId=AWIeW7FakkUDFAVH&categoryType=CODE
 * 
 * [입력사항] 테스트 케이스의 개수 T / 지도의 한 변의 크기인 N 과 경사로의 길이 X / N * N 크기의 지형 정보
 * [출력사항] 활주로를 건설할 수 있는 경우의 수
 * 
 * 경사로는 높이 차이가 1 이고 낮은 지형의 높이가 동일하게 X만큼 연속되는 곳에 설치 할 수 있다
 */

public class SWEA_D0_4014_활주로건설 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, X, Answer;
    static int[][] mapR, mapC;


    public static void main(String[] args) throws Exception {
        // 테스트 케이스
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());
            X = Integer.parseInt(tokens.nextToken());

            mapR = new int[N][N];
            mapC = new int[N][N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    mapR[i][j] = Integer.parseInt(tokens.nextToken());
                    mapC[j][i] = mapR[i][j];
                }
            }

            // 알고리즘
            Answer = 0;
            runway(mapR); //가로
            runway(mapC); //세로

            // 결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        // 출력
        System.out.println(output);
    }

    public static void runway(int[][] map) {
        for (int i = 0; i < N; i++) {
            int cnt = 1; // 높이가 같은 지대의 수
            for (int j = 1; j < N; j++) {
                int prev = map[i][j - 1];
                int cur = map[i][j];
                
                if (prev == cur) { //같은 높이 연속
                    cnt++;
                }

                else if (prev - cur == 1 && cnt >= 0) { //내리막
                    cnt = -X + 1;
                }
                    
                
                else if (prev - cur == -1 && cnt >= X) { // 오르막, 같은 높이 연속 규칙 지킴
                    cnt = 1;
                }                    
                
                
                else { //그외
                    cnt = -1;
                    break;
                }
            }
            if (cnt >= 0)
                Answer++;
        }
    }
}
