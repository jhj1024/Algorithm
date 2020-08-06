import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D4_4301_콩많이심기
* @date 2020.08.06
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWLv-yZah48DFAVV&categoryId=AWLv-yZah48DFAVV&categoryType=CODE
* @mem
* @time
* @caution
* [고려사항] R*C 맵에서 콩들 사이의 거리가 2가 되지 않도록 하면서 최대한 많은 콩 심기
* [입력사항] 테스트케이스 수 / 가로길이 C 세로길이 R
* [출력사항] 최대로 많이 심은 콩 개수
* 
* 사방탐색을 하되, 거리가 2인 사방에 콩이 없어야 콩을 심을 수 있다.
* 
*/

public class SWEA_D4_4301_콩많이심기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens = null;
	
	static int T, C, R, Answer;
	static boolean[][] map;
	static int[][] dirs = {{2, 0}, {-2, 0}, {0, 2}, {0, -2}};
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(input.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			//입력
			tokens = new StringTokenizer(input.readLine());
			C = Integer.parseInt(tokens.nextToken()); //
			R = Integer.parseInt(tokens.nextToken());
			
			map = new boolean[R][C];
			
			//알고리즘
			Answer = 0;
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(!map[i][j]) {
						plantBean(i, j); //빈 땅이면 콩을 심을 수 있는지 확인
					}					
				}
			}
			output.append("#").append(tc).append(" ").append(Answer).append("\n"); //정답		
		}	
		System.out.println(output); //출력
	}
	
	static void plantBean(int r, int c) {
		boolean isAlreadyPlanted = false;
		
		for(int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];
			
			if(0 <= dr && dr < R && 0 <= dc && dc < C && map[dr][dc]) { //거리가 2인 위치에 이미 콩이 심어져있으면				
				isAlreadyPlanted = true; //이미 있어서 못심음
				break;
			}
		}
		
		if(!isAlreadyPlanted) { //거리가 2인 위치에 콩이 하나도 심어져있지 않으면
			map[r][c] = true;//콩 심기
			Answer++;
		}		
	}

}
