import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D3_5215_햄버거 다이어트
 * @date 2020.07.29 
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT&categoryId=AWT-lPB6dHUDFAVT&categoryType=CODE
 * @mem
 * @time
 * @caution 정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거를 조합해주는 프로그램
 * [고려사항] 햄버거의 선호도는 조합된 재료들의 맛에 대한 점수의 합으로 결정한다.같은 재료를 여러 번 사용할 수 없음(중복불가)
 * [입력사항]
 * [출력사항]
 */

public class SWEA_D3_5215_Hamburger {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, L;
    static int[] point;
    static int[] kcal;
    static int Answer;
    
    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        StringTokenizer tokens = null;
        for (int test_case = 1; test_case <= T; test_case++) {
            // 재료의 개수 N, 제한 칼로리 L 입력
            tokens = new StringTokenizer(input.readLine(), " ");
            N = Integer.parseInt(tokens.nextToken());
            L = Integer.parseInt(tokens.nextToken());

            // 각 재료의 점수와 칼로리 입력
            point = new int[N];
            kcal = new int[N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine(), " ");
                point[i] = Integer.parseInt(tokens.nextToken()); // 재료 점수
                kcal[i] = Integer.parseInt(tokens.nextToken()); // 칼로리
            }

            /* --------------------- 알고리즘  --------------------- */
            Answer = 0;
            CalPoint(0, 0, 0); //점수&칼로리 계산
            System.out.printf("#%d %d\n", test_case, Answer);
        }
    }
    
    //재귀를 이용하여 칼로리 계산
    public static void CalPoint(int sump, int sumk, int idx) {
    	if(sumk > L) return;
    	if(idx == N) {
    		Answer = Answer < sump ? sump : Answer; //비교하여 큰 점수를 Answer에 저장
    		return;
    	}
    	CalPoint(sump, sumk, idx+1);//해당 재료 추가 안함
    	CalPoint(sump+point[idx], sumk+kcal[idx], idx+1); //해당 재료 추가
    	
    }
    
}
