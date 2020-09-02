import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
* 
*/

public class SWEA_D0_1767_프로세서연결하기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, maxCore, minLine;
    static int[][] Mexynos;
    static List<Core> cores;
    static Map<Core, Core> connected;
    static int[][] dirs = {{-1, 0}, {1, 0},{0, -1}, {0, 1}};
    
    //상(0, c), 하(N-1, c), 좌(r, 0), 우(r, C-1)
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //테스트케이스 수
        T = Integer.parseInt(input.readLine().trim());
        for(int t = 1; t <= T; t++) {
            //입력
            N = Integer.parseInt(input.readLine().trim());
            
            Mexynos = new int[N][N];
            cores = new ArrayList<>();
            connected = new HashMap<>();
            for(int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine(), " ");
                for(int j = 0; j < N; j++) {
                    Mexynos[i][j] = Integer.parseInt(tokens.nextToken());
                    if(Mexynos[i][j] == 1) { //벽에 붙어있지 않은 코어면                       
                        if((i != 0) && (i != N-1) && (j != 0) && (j != N-1)) {
                            cores.add(new Core(i, j)); //탐색할 리스트에 저장
                        }else { //벽에 붙어있는 코어면
                            connected.put(new Core(i, j), new Core(i, j)); //검사할 리스트에 저장
                        }
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
        if((cores.size() - idx + coreCnt) < maxCore) {
            return;
        }
        
        //모든 코어를 확인했으면 maxCore와 minLine 확인하고 종료
        if(idx == cores.size()) {
            //System.out.println(connected);
            if(maxCore < coreCnt) { //연결한 코어수가 많으면 minLine 업데이트
                maxCore = coreCnt;
                minLine = lineLen;
            } else if(maxCore == coreCnt) { //코어수가 같으면 더 작은 전선길이로 업데이트
                minLine = Math.min(minLine, lineLen);
            }
            return;
        }
        
        //전선 설치 가능한지 확인
        boolean canConnect = true;
        int r, c, dr, dc;
        
        //상
        canConnect = true;
        r = cores.get(idx).r; dr = 0; dc = cores.get(idx).c;
        for(Core key : connected.keySet()) {
            Core value = connected.get(key);
            
            //dc가 연결된 전선의 열 사이에 있고 연결된 전선의 행이 dc보다 작으면 실패
            if((key.r <= r || value.r <= r) && ((key.c <= dc && dc <= value.c) || (value.c <= dc && dc <= key.c))) {
                canConnect = false;
                break;
            }           
        }

        if(!canConnect) { //연결 불가능하면 그냥 dfs 돌리기
            dfs(idx+1, coreCnt, lineLen);
        }else { //연결가능하면 connected에 넣고 dfs돌린 뒤, map에서 빼기
            connected.put(cores.get(idx), new Core(dr, dc));
            dfs(idx+1, coreCnt+1, lineLen + r);
            connected.remove(cores.get(idx));
        }

        //하
        canConnect = true;
        r = cores.get(idx).r; dr = N-1; dc = cores.get(idx).c;
        for(Core key : connected.keySet()) {
            Core value = connected.get(key);
            
            //dc가 연결된 전선의 열 사이에 있고 연결된 전선의 행이 dc보다 크면 실패
            if((key.r >= r || value.r >= r) && ((key.c <= dc && dc <= value.c) || (value.c <= dc && dc <= key.c))) {
                canConnect = false;
                break;
            }           
        }

        if(!canConnect) { //연결 불가능하면 그냥 dfs 돌리기
            dfs(idx+1, coreCnt, lineLen);
        }else { //연결가능하면 connected에 넣고 dfs돌린 뒤, map에서 빼기
            connected.put(cores.get(idx), new Core(dr, dc));
            dfs(idx+1, coreCnt+1, lineLen + (N-r-1));
            connected.remove(cores.get(idx));
        }  
        
        //좌
        canConnect = true;
        c = cores.get(idx).c; dr = r; dc = 0;
        for(Core key : connected.keySet()) {
            Core value = connected.get(key);
            
            //dr이 연결된 전선의 행 사이에 있고 연결된 전선의 열이 dc보다 작으면 실패
            if((key.c <= c || value.c <= c) && ((key.r <= dr && dr <= value.r) || (value.r <= dr && dr <= key.r))) {
                canConnect = false;
                break;
            }           
        }
        if(!canConnect) { //연결 불가능하면 그냥 dfs 돌리기
            dfs(idx+1, coreCnt, lineLen);
        }else { //연결가능하면 connected에 넣고 dfs돌린 뒤, map에서 빼기
            connected.put(cores.get(idx), new Core(dr, dc));
            dfs(idx+1, coreCnt+1, lineLen + c);
            connected.remove(cores.get(idx));
        }
        
        //우
        canConnect = true;
        c = cores.get(idx).c; dr = r; dc = N-1;
        for(Core key : connected.keySet()) {
            Core value = connected.get(key);
            
            //dr이 연결된 전선의 행 사이에 있고 연결된 전선의 열이 dc보다 크면 실패
            if((key.c >= c || value.c >= c) && ((key.r <= dr && dr <= value.r) || (value.r <= dr && dr <= key.r))) {
                canConnect = false;
                break;
            }           
        }
        if(!canConnect) { //연결 불가능하면 그냥 dfs 돌리기
            dfs(idx+1, coreCnt, lineLen);
        }else { //연결가능하면 connected에 넣고 dfs돌린 뒤, map에서 빼기
            connected.put(cores.get(idx), new Core(dr, dc));
            dfs(idx+1, coreCnt+1, lineLen + (N-c-1));
            connected.remove(cores.get(idx));
        }

        return;
    }

    static class Core {
        int r, c;

        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            builder.append(r);
            builder.append(", ");
            builder.append(c);
            builder.append(")");
            return builder.toString();
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
