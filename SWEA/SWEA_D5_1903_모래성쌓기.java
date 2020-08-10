import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;ㅇ
import java.util.Queue;
import java.util.StringTokenizer;
/**
* @author JUNG
* @name SWEA_D5_1903_모래성쌓기
* @date 2020.08.10
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PNx_KACIDFAUq
* @mem
* @time
* @caution
* [고려사항] 현재 격자의 주변 8방향(상하좌우 대각선)에 모래가 있지 않은 칸의 개수가 현재 칸의 튼튼함보다 많거나 같을 경우 무너짐
* [입력사항] 테스트케이스 T / 모래성 크기 R, C / 문자열(1~9와 .) 격자의 최 외곽부는 모두 ‘.’
* [출력사항] 모래성의 형태가 더 이상 변하지 않게 될때까지의 파도의 횟수
* 
* ******* 발상의 전환 *******
* 모래알(숫자) 기준이 아니라 빈공간(.)을 기준으로 하기
* >>>>이렇게 하면 bfs level구하는 문제로 간단하게 해결됨.
* 
* 처음부터 빈 공간의 좌표를 큐에 집어넣고, 빈공간에서 팔방탐색을 해서 주변에 숫자를 1감소시킨다.
* 0이된 녀석은 빈공간으로 간주하고, 큐에 넣는다.
* 
*/

public class SWEA_D5_1903_모래성쌓기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens = null;
	
	static int T, R, C, Answer = 0, size = 0;
	static int[][] map;
	static Queue<pair> blank;
	
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
	
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(src));
		T = Integer.parseInt(input.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			//입력
			tokens = new StringTokenizer(input.readLine(), " ");
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			
			map = new int[R][C];
			blank = new LinkedList<pair>(); //0이 아닌 녀석들 모아놓기
			for(int i = 0; i < R; i++) {
				String line = input.readLine();
				for(int j = 0; j < C; j++) {
					if(line.charAt(j) == '.') {
						map[i][j] = 0; //모래가 없는 부분은 대충 0으로 넣음
						blank.offer(new pair(i, j)); //모래알 없는 곳을 공략		
					}else {
						map[i][j] = line.charAt(j) - '0'; //char -> int 변환								
					}					
				}
			}
			
			//알고리즘
			Answer = -1; size = 0; //더이상 변하지 않는데 파도가 한번 더 치므로 -1로 저장

			//빈공간 주변의 숫자는 1씩 감소시키기
			//파도 횟수 = level
			while(!blank.isEmpty()) {
				size = blank.size();
				while(--size >= 0) {
					pair p = blank.poll();
					for(int d = 0; d < dirs.length; d++) {
						int dr = p.i + dirs[d][0];
						int dc = p.j + dirs[d][1];
						
						if(0 <= dr && dr < R && 0 <= dc && dc < C && map[dr][dc] != 0) {
							if(--map[dr][dc] == 0) { //0이 되면
								blank.offer(new pair(dr, dc)); //다음 타임에 쓰일 빈공간으로 넣기
							}
						}
					}					
				}
				Answer++;				
			}					
			output.append("#").append(tc).append(" ").append(Answer).append("\n");
		}
		
		//출력
		System.out.println(output);
	}
			
	static class pair{
		int i, j;

		public pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "[" + i + ", " + j + "]";
		}
	
	}
	
	static String src = "2\r\n" + 
			"5 6\r\n" + 
			"......\r\n" + 
			".939..\r\n" + 
			".3428.\r\n" + 
			".9393.\r\n" + 
			"......\r\n" + 
			"10 10\r\n" + 
			"..........\r\n" + 
			".99999999.\r\n" + 
			".9.323239.\r\n" + 
			".91444449.\r\n" + 
			".91444449.\r\n" + 
			".91444449.\r\n" + 
			".91444449.\r\n" + 
			".91232329.\r\n" + 
			".99999999.\r\n" + 
			"..........";

}
