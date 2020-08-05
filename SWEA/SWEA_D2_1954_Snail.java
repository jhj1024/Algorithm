import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
* @author JUNG
* @name 1954 달팽이 숫자 
* @date 2020.07.28
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq&categoryId=AV5PobmqAPoDFAUq&categoryType=CODE#;return%20false;
* @mem
* @time
* @caution " "로 숫자 띄어쓰기 하기
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class SWEA_D2_1954_Snail {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output;
	
	public static void main(String[] args) throws Exception{
		input = new BufferedReader(new StringReader(src)); //입력방향 설정, 제출 시 주석 처리
		int T = Integer.parseInt(input.readLine());
		
		for(int tc = 1; tc <= T; tc++) {			
			//달팽이 크기 입력
			int N = Integer.parseInt(input.readLine());
					
			//달팽이모양으로 행렬에 숫자 저장
			int[][] snail = new int[N][N]; //N*N 행렬 생성
			int r=0, c=-1, cnt=1; //행렬 시작 좌표 및 시작 숫자
			
			for(int i = 0; i < N; i++) {
				snail[r][++c] = cnt++;
			}
			
			N--;
			int dir = 1; //진행 방향
			while(N != 0) {
				if(dir == 1) {
					//아래
					for(int i = 0; i < N; i++) {
						snail[++r][c] = cnt++;
					}
					
					//왼쪽
					for(int i = 0; i < N; i++) {
						snail[r][--c] = cnt++;
					}
				} 
				else {
					//위
					for(int i = 0; i < N; i++) {
						snail[--r][c] = cnt++;
					}
					
					//오른쪽
					for(int i = 0; i < N; i++) {
						snail[r][++c] = cnt++;
					}
					

				}				
				
				N--;
				dir *= -1;
			}
			
			//테스트케이스 번호 출력
			System.out.printf("#%d\n", tc);
			
			//달팽이 출력
			output = new StringBuilder(); //output String 초기화
			for(int i = 0; i < snail.length; i++) {
				for(int j = 0; j < snail.length; j++) {
					output.append(snail[i][j]).append(" ");
				}
				output.append("\n");
			}
			
			System.out.print(output);
		}

	}
	static String src = "2\r\n" + "3\r\n" + "4\r\n";

}
