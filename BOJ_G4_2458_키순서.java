package com.ssafy.algo.d1104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_2458_키순서
* @date 2020.11.04
* @link https://www.acmicpc.net/problem/2458
* 
* [입력사항] 학생들의 수 N과 두 학생 키를 비교한 횟수 M / M개의 비교 결과(번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미)
* [출력사항] 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력
* 
* 모든 학생과 연결되어 있다 = 자신이 키가 몇 번째인지 알 수 있다
* 
*/

public class BOJ_G4_2458_키순서 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, Answer;
    static int[][] visited;
    
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        visited = new int[N][N];
        for(int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            visited[a][b] = 1; //a에게 b는 큰 친구
            visited[b][a] = -1; //b에게 a는 작은친구
        }
        
        //알고리즘
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                
                
            }
        }
        

    }
    

    

}
