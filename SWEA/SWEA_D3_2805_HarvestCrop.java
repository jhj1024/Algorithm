package com.ssafy.algo.d0729.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
* @author JUNG
* @name SWEA_D3_2805_농작물 수확하기
* @date 2020.07.29
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB
* @mem
* @time
* @caution 
* [고려사항] 정사각형 안에서 만들 수 있는 마름모 부위만 더하기
* [입력사항]
* [출력사항]
*/

public class SWEA_D3_2805_HarvestCrop {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int Answer = 0;

    public static void main(String[] args) throws Exception {
        //input = new BufferedReader(new StringReader(src));
        int T, N;

        T = Integer.parseInt(input.readLine()); // 테스트케이스 개수
        for (int test_case = 1; test_case <= T; test_case++) {
            // 농장 크기 입력
            N = Integer.parseInt(input.readLine());
            int[][] map = new int[N][N]; // N*N배열 생성

            // 각 농작물 가치 입력
            for (int i = 0; i < N; i++) {
                String row = input.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = row.charAt(j) - '0'; //char에서  int로 변환
                }
            }

            /* --------------------- 알고리즘  --------------------- */
            Answer = 0;
            
            int jump = N/2; //건너띌 개수
            int cnt = 1; //더할 개수

            for(int r = 0; r < N; r++) {
                for(int i = 0; i < cnt; i++) {
                    Answer += map[r][jump+i]; //더할 개수 만큼 농작물 가치 더하기
                }
                
                if(r < N/2) { //중간으로 갈때까지 건너뛰는 칸은 한칸씩 줄어들고 더하는 개수는 2씩 증가(삼각형)
                    jump--;
                    cnt += 2;
                }
                else{ //중간이후부터는 건너뛰는 칸이 한칸씩 늘어나고 더하는 개수는 2씩 감소(역삼각형)
                    jump++;
                    cnt -= 2;
                }
            }

            System.out.printf("#%d %d\n", test_case, Answer);

        }
    }
}
