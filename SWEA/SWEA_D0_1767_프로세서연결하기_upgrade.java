import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D0_1767_프로세서연결하기
* @date 2020.09.02
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf&categoryId=AV4suNtaXFEDFAUf&categoryType=CODE&&&
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 테스트 케이스의 개수 T / 멕시노스 크기 N / N*N크기의 멕시노스의 초기 상태
* [출력사항] 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합
* 
* 멕시노스의 가장자리에 위치한 Core는 이미 전원이 연결된 것으로 간주한다
* 최대한 많은 Core에 전원을 연결해도, 전원이 연결되지 않는 Core가 존재할 수 있다.
*/

public class SWEA_D0_1767_프로세서연결하기_upgrade {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, maxCore, minLine;
    static int[][] Mexynos;
    static List<Core> cores;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //테스트케이스 수
        T = Integer.parseInt(input.readLine().trim());
        for (int t = 1; t <= T; t++) {
            //입력
            N = Integer.parseInt(input.readLine().trim());

            Mexynos = new int[N][N];
            cores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    Mexynos[i][j] = Integer.parseInt(tokens.nextToken());
                    if (Mexynos[i][j] == 1 && ((i != 0) && (i != N - 1) && (j != 0) && (j != N - 1))) { //벽에 붙어있지 않은 코어면                       
                        cores.add(new Core(i, j)); //탐색할 리스트에 저장
                    }
                }
            }

            //알고리즘
            maxCore = Integer.MIN_VALUE;
            minLine = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            output.append("#").append(t).append(" ").append(minLine).append("\n");
        }
        System.out.println(output);
    }

    public static void dfs(int idx, int coreCnt, int lineLen) {
        //남은 코어를 전부 더해도 현재 maxcore보다 작을 때, 그 이후 탐색을 중단
        if ((cores.size() - idx + coreCnt) < maxCore) {
            return;
        }

        //모든 코어를 확인했으면 maxCore와 minLine 확인하고 종료
        if(idx == cores.size()) {
            if(maxCore < coreCnt) { //연결한 코어수가 많으면 minLine 업데이트
                maxCore = coreCnt;
                minLine = lineLen;
            } else if(maxCore == coreCnt) { //코어수가 같으면 더 작은 전선길이로 업데이트
                minLine = Math.min(minLine, lineLen);
            }
            return;
        }
        
        int r, c, dr, dc, len;
        for(int d = 0; d < dirs.length; d++) {
            r = cores.get(idx).r;
            c = cores.get(idx).c;
            len = 0;
            
            dr = r; dc = c;
            while(true) {
                dr += dirs[d][0];
                dc += dirs[d][1];
                
                if(!isIn(dr, dc)) {                    
                    break;
                } else if(Mexynos[dr][dc] == 1) { //전선을 깔 수 없으니까 len 되돌려놓고 종료                    
                    len = 0;
                    break;
                } else {
                    len++;
                } 
            }
            
            if(len != 0) { //전선깔기 가능
                dr = r; dc = c;
                while(true) {
                    dr += dirs[d][0];
                    dc += dirs[d][1];
                    
                    if(!isIn(dr, dc)) {                    
                        break;
                    } 
                    
                    Mexynos[dr][dc] = 1;
                }
                
                dfs(idx+1, coreCnt+1, lineLen+len);
                
                dr = r; dc = c;
                while(true) {
                    dr += dirs[d][0];
                    dc += dirs[d][1];
                    
                    if(!isIn(dr, dc)) {                    
                        break;
                    } 
                    
                    Mexynos[dr][dc] = 0;
                }
            }
            
            else { //전선깔기 불가능                
                dfs(idx+1, coreCnt, lineLen);
            }
        }
        return;
    }

    public static boolean isIn(int dr, int dc) {
        return (0 <= dr && dr < N && 0 <= dc && dc < N);
    }

    static class Core {
        int r, c;

        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static String src =
            "1\r\n" + 
            "9\r\n" + 
            "1 1 1 1 1 1 1 1 1\r\n" + 
            "1 0 0 0 1 0 0 0 0\r\n" + 
            "0 0 1 0 0 0 0 1 0\r\n" + 
            "1 0 0 0 1 0 0 0 0\r\n" + 
            "1 1 0 1 1 0 0 0 0\r\n" + 
            "1 0 0 0 0 0 1 0 0\r\n" + 
            "1 0 1 0 0 0 0 0 1\r\n" + 
            "1 0 0 0 1 0 1 0 0\r\n" + 
            "1 0 1 0 0 0 0 0 0";

}
