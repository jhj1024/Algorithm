import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
* @author JUNG
* @name BOJ_S1_2667_단지번호붙이기
* @date 2020.08.05
* @link https://www.acmicpc.net/problem/2667
* @mem
* @time
* @caution 
* [고려사항] 대각선상에 집이 있는 경우는 연결된 것이 아니다. (사방연결만 가능)
* [입력사항]
* [출력사항]
*/

public class BOJ_S1_2667_단지번호붙이기_DFS {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static String line;
    static int cnt, N, Answer=0;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<Integer> houseCnt = new ArrayList<Integer>();
    
    public static void main(String[] args) throws Exception{
        input = new BufferedReader(new StringReader(src));
        
        //입력
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        
        for(int i = 0; i < N; i++) {
            line = input.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0'; //char에서 int로 변환
            }
        }        

        /* -------------------- 알고리즘  -------------------- */
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    cnt = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    Answer++; //단지 수 증가
                    houseCnt.add(cnt); //단지 내 집의 개수 추가
                }
            }
        } 
        
        Collections.sort(houseCnt); //집의 개수는 오름차순 정렬
        
        output.append(Answer).append("\n");
        for(Integer data : houseCnt) {
            output.append(data).append("\n");
        }
        System.out.print(output);       
    }
    
    static void dfs(int r, int c) {
        for(int d = 0; d < dirs.length; d++) {
            int dr = r + dirs[d][0];
            int dc = c + dirs[d][1];
            
            if(0 <= dr && dr < N && 0 <= dc && dc < N && map[dr][dc] == 1 && !visited[dr][dc]) { //연결되어있으면
                visited[dr][dc] = true; //방문표시
                cnt++;
                dfs(dr, dc);//재귀
            }
        }
    }
    
    
    static String src = "7\r\n" + 
            "0110100\r\n" + 
            "0110101\r\n" + 
            "1110101\r\n" + 
            "0000111\r\n" + 
            "0100000\r\n" + 
            "0111110\r\n" + 
            "0111000";

}
