import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G2_3109_빵집
* @date 2020.08.27
* @link https://www.acmicpc.net/problem/3109
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 마을 크기 R*C, R*C 크기의 마을 정보
* [출력사항] 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수
* 
* 첫째 열은 근처 빵집의 가스관이고, 마지막 열은 원웅이의 빵집
* 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결
* 
*/

public class BOJ_G2_3109_빵집 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, Answer = 0;
    static char[][] map;
    static boolean[][] visited;
    static int[] dirs = {-1, 0, 1}; //동시에 가장 많은 파이프를 설치하려면 순서가 오른 위 - 오른 - 오른아래 순으로 해야함  
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        
        map = new char[R][];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
           map[i] = input.readLine().toCharArray();
        }

        for(int r = 0; r < R; r++) { //0~R행에 차례대로 파이프라인 설치 시도
            setPipelines(r, 0);
        }
        
        System.out.println(Answer);
    }

    private static boolean setPipelines(int r, int c) { 
        if(c == C-1) { //원웅이네 빵집 도착 = 설치 성공
            Answer++; //설치한 파이프라인 개수 증가
            return true;
        }       
        
        for(int d = 0; d < dirs.length; d++) {
            int dr = r + dirs[d];
            int dc = c+1;
            
            if(isIn(dr, dc) && !visited[dr][dc] && map[dr][dc] == '.') { //방문한 적 없고 파이프라인을 이을 수 있을 때               
                visited[dr][dc] = true; //방문표시
                if(setPipelines(dr, dc)) { //해당 루트로 파이프라인을 설치해보니 성공했으면
                    return true; //모든 경우의 수가 아니라 동시에 많은 파이프를 설치하는 것이므로 다른 경우 생각할 필요 없이 이걸로 고정
                }
                //visited[dr][dc] = false; //이미 실패한 경로이기 때문에 다음에 또 여길 와서 같은 실수를 반복할 수 없음. 그러므로 false처리 없이 true로 유지. 
            }
        }
        return false; //파이프라인 설치 실패
    }

    private static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c <C);
    }

    static String src = "5 5\r\n" + 
            ".xx..\r\n" + 
            "..x..\r\n" + 
            ".....\r\n" + 
            "...x.\r\n" + 
            "...x.";
}
