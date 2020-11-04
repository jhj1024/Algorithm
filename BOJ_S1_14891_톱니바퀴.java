package com.ssafy.algo.d1104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name BOJ_S1_14891_톱니바퀴
 * @date 2020.11.04
 * @link https://www.acmicpc.net/problem/14891
 * 
 * [입력사항] 4개의 톱니바퀴 상태(상태는 8개의 정수로 이루어져있고, 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로 나타나있다.)
 * 회전 횟수 K / K개의 회전 방법(회전시킬 톱니바퀴의 번호, 방향(1: 시계방향, -1: 반시계방향)
 * [출력사항] 총 K번 회전시킨 이후에 네 톱니바퀴의 점수의 합을 출력
 * 
 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
 * 
 */

public class BOJ_S1_14891_톱니바퀴 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N = 4, M = 8, K, Answer;
    static int[][] sawtooth;
    static Rotate[] rotates;

    public static void main(String[] args) throws Exception {
        // 입력
        sawtooth = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = input.readLine();
            for (int j = 0; j < M; j++) {
                sawtooth[i][j] = line.charAt(j) - '0';
            }
        }
        
        K = Integer.parseInt(input.readLine());
        
        rotates = new Rotate[K];
        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(input.readLine());
            rotates[i] = new Rotate(Integer.parseInt(tokens.nextToken())-1, Integer.parseInt(tokens.nextToken()));
        }

        
        // 알고리즘
        for (int i = 0; i < K; i++) {
            bfs(rotates[i]);
        }

        Answer = calc();

        // 출력
        System.out.println(Answer);
    }
    
    public static void bfs(Rotate rotate) {
        Queue<Rotate> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        q.offer(rotate);
        visited[rotate.num] = true;

        while (!q.isEmpty()) {
            Rotate r = q.poll();

            // 왼쪽(r.num의 6번이랑 r.num - 1의 2번과 비교)
            if (r.num - 1 >= 0 && !visited[r.num - 1] && sawtooth[r.num][6] != sawtooth[r.num - 1][2]) {
                visited[r.num - 1] = true;
                q.offer(new Rotate(r.num - 1, r.dir * -1));
            }

            // 오른쪽(r.num의 2번이랑 r.num + 1의 6번과 비교)
            if (r.num + 1 < N && !visited[r.num + 1] && sawtooth[r.num][2] != sawtooth[r.num + 1][6]) {
                visited[r.num + 1] = true;
                q.offer(new Rotate(r.num + 1, r.dir * -1));
            }
            
            // 회전
            if (r.dir == 1) { // 시계방향
                int top = sawtooth[r.num][M - 1];
                for (int i = M - 1; i >= 1; i--) {
                    sawtooth[r.num][i] = sawtooth[r.num][i-1];
                }
                sawtooth[r.num][0] = top;
                
            } else { // 반시계방향
                int top = sawtooth[r.num][0];
                for (int i = 1; i < M; i++) {
                    sawtooth[r.num][i - 1] = sawtooth[r.num][i];
                }
                sawtooth[r.num][M - 1] = top;
            }
        }
    }
    
    private static int calc() {
        int sum = 0;
        
        for(int i = 0; i < N; i++) {
            if(sawtooth[i][0] == 1) {
                sum += (int) Math.pow(2, i);
            }
        }        
        return sum;
    }
    
    static class Rotate {
        int num, dir;

        public Rotate(int num, int dir) {
            super();
            this.num = num;
            this.dir = dir;
        }
    }
}
