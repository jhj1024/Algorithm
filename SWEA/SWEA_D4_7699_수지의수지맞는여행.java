import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D4_7699_수지의수지맞는여행
* @date 2020.08.25
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWqUzj0arpkDFARG&categoryId=AWqUzj0arpkDFARG&categoryType=CODE
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 테스트케이스 개수 T / 섬의 크기 R, C / R*C개의 명물 
* [출력사항] 수지가 여행하면서 볼 수 있는 명물의 최대값
* 
* 한 갈래의 방문이 끝나면 방문처리 초기화해주기(그 갈래길로 안갈거니께)
* 
*/

public class SWEA_D4_7699_수지의수지맞는여행 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens = null;
	static int T, R, C, Answer;
	static int[][] map;
	static boolean[] aVisited;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(src));
		
		//입력
		T = Integer.parseInt(input.readLine());
		for(int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(input.readLine());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			
			map = new int[R][C];
			aVisited = new boolean[26]; //0 ~ 25니까 26개
			for(int i = 0; i < R; i++) {
				String line = input.readLine();
				for(int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j) - 'A'; //A ~ Z를 0 ~25 숫자로 변경
				}
			}
			
			//알고리즘
			Answer = Integer.MIN_VALUE;
			aVisited[map[0][0]] = true;
			dfs(0, 0, 1);
			
			output.append("#").append(t).append(" ").append(Answer).append("\n");
		}
		System.out.println(output);
	}
	
	static void dfs(int r, int c, int cnt) {	   
	    for(int d = 0; d < dirs.length; d++) {
	        int dr = r + dirs[d][0];
	        int dc = c + dirs[d][1];
	        
	        if(isIn(dr, dc) && !aVisited[map[dr][dc]]) {
	            aVisited[map[dr][dc]] = true;
	            dfs(dr, dc, cnt+1);
	            aVisited[map[dr][dc]] = false;
	        }	        
	    }
	    
	    Answer = Math.max(Answer, cnt);
	}
	
	static boolean isIn(int r, int c) {
	    return (0 <= r && r < R && 0 <= c && c < C);
	}
	
	static String src = "3\r\n" + 
			"2 4\r\n" + 
			"CZAB\r\n" + 
			"ADCB\r\n" + 
			"3 6\r\n" + 
			"HFDFFB\r\n" + 
			"AJHGDH\r\n" + 
			"DGAGEH\r\n" + 
			"5 5\r\n" + 
			"IEFCJ\r\n" + 
			"FHFKC\r\n" + 
			"FFALF\r\n" + 
			"HFGCF\r\n" + 
			"HMCHH";
}
