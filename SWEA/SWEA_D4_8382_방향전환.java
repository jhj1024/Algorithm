import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D4_8382_방향전환
 * @date 2020.08.11
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWyNQrCahHcDFAVP&categoryId=AWyNQrCahHcDFAVP&categoryType=CODE
 * @mem
 * @time
 * @caution 
 * [고려사항] 상/하 이동과 좌/우 이동을 번갈아서 해야함 [입력사항] 테스트케이스 T, (x1, y1), (x2, y2)
 * [출력사항] (x1, y1)에서 (x2, y2)로 이동하기 위한 최소 이동 횟수
 * 
 * (0,0)에서 (MIN, MAX)로 이동하는 것으로 생각해보기
 * |x1-x2|와 |y1-y2|를 구해서 둘 중 최대값 MAX와 최소값 MIN 정함 
 * 
 * 두 개가 같으면 
 * Answer = MAX + MIN (서로 번갈아서 이동하면 됨)
 * 
 * 두 개가 다를 때는
 * (0,0)에서 (MIN, MIN)으로 가기 위해 먼저 MIN*2번 이동
 * (MIN, MIN)에서 (MIN , MAX)로 가는 경우 = (0,0)에서 (0, MAX)로 가는 경우
 * MAX-MIN이 홀수(1)면 (가로)만 가면 되니까 (MAX-MIN)*2-1.
 * MAX-MIN이 짝수(2)면 (가로세로가로세로)로 가니까 (MAX-MIN)*2.
 */

public class SWEA_D4_8382_방향전환 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens = null;
	static StringBuilder output = new StringBuilder();
	static int T, MAX, MIN, Answer = 0;
	static int[] num;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(input.readLine());

		int[] num = new int[6]; // x1, y1, x2, y2, |x1-x2|, |y1-y2|
		for (int tc = 1; tc <= T; tc++) {
			num = new int[6]; // x1, y1, x2, y2, |x1-x2|, |y1-y2|
			Answer = 0;
			tokens = new StringTokenizer(input.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				num[i] = Integer.parseInt(tokens.nextToken());
			}
			num[4] = Math.abs(num[0] - num[2]); // |x1-x2|
			num[5] = Math.abs(num[1] - num[3]); // |y1-y2|
			
			MAX = Math.max(num[4], num[5]);
			MIN = Math.min(num[4], num[5]);			

			if (MAX == MIN) { // 같으면
				Answer = MAX + MIN;
			}else {
				Answer = MIN*2;
				Answer += (MAX-MIN)%2 == 0 ? (MAX-MIN)*2 : (MAX-MIN)*2-1;
			}
			
			output.append("#").append(tc).append(" ").append(Answer).append("\n");
		}
		System.out.println(output);
	}
}
