package com.ssafy.algo.d0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name JUNGOL_1681_해밀턴순환회로
* @date 2020.09.03
* @link http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954&sca=99&sfl=wr_hit&stx=1681
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 배달해야 하는 장소의 수 N, N*N크기의 장소 이동 비용
* [출력사항] 회사에서 출발하여 오늘 배달해야 하는 모든 장소를 한 번씩 들러 물건을 배달하고 회사에 돌아오기 위한 최소의 비용
* 
* 회사에 다시 돌아오는 것이기 때문에 최소신장트리 안됨 -> 모든 경우 탐색 = DFS.
* 
*/

public class JUNGOL_1681_해밀턴순환회로 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    static int[][] map;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
        //입력
        N = Integer.parseInt(input.readLine().trim());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        
        //알고리즘
        Answer = Integer.MAX_VALUE;
        visited = new boolean[N]; //방문한 정점 관리
        visited[0] = true; //시작 정점은 미리 방문 처리
        dfs(0, 0, 0);
        System.out.println(Answer);
    }
    
    static void dfs(int idx, int cnt, int total) {
        if(total > Answer) { //이미 비용이 더 크다면 더 탐색할 필요 없음.
            return; 
        }
        
        if(cnt == N-1) { //모든 정점을 돌았을 때
            if(map[idx][0] != 0) {
                total += map[idx][0]; //다시 처음으로 돌아가는 비용 추가
                Answer = Math.min(Answer, total); //최종 비용의 최소값 저장
            }
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(!visited[i] && map[idx][i] != 0) { //방문하지 않은 인접 정점이면
                visited[i] = true;
                dfs(i, cnt+1, total+map[idx][i]); //total에 현재 정점에서 해당 정점으로 가는 비용 추가
                visited[i] = false;
            }
        }
    }
}
