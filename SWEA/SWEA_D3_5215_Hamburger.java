package com.ssafy.algo.d0729;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D3_5215_햄버거 다이어트
 * @date 2020.07.29
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT&categoryId=AWT-lPB6dHUDFAVT&categoryType=CODE
 * @mem
 * @time
 * @caution 정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거를 조합해주는 프로그램
 * [고려사항] 햄버거의 선호도는 조합된 재료들의 맛에 대한 점수의 합으로 결정.같은 재료를 여러 번 사용할 수 없음
 * [입력사항]
 * [출력사항]
 */

public class SWEA_D3_5215_Hamburger {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, L;
    static int Answer;

    public static void main(String[] args) throws Exception {
        //input = new BufferedReader(new StringReader(src));
        T = Integer.parseInt(input.readLine());

        StringTokenizer tokens = null;
        for (int test_case = 1; test_case <= T; test_case++) {
            // 재료의 개수 N, 제한 칼로리 L 입력
            tokens = new StringTokenizer(input.readLine(), " ");
            N = Integer.parseInt(tokens.nextToken());
            L = Integer.parseInt(tokens.nextToken());

            // 각 재료의 점수와 칼로리 입력
            int[] point = new int[N];
            int[] kcal = new int[N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine(), " ");
                point[i] = Integer.parseInt(tokens.nextToken()); // 재료 점수
                kcal[i] = Integer.parseInt(tokens.nextToken()); // 칼로리
            }

            /* --------------------- 알고리즘  --------------------- */
            Answer = 0;
            
            //*부분집합 만드는 알고리즘을 응용함. 1~N개의 요소를 가지는 모든 부분집합 생성
            List<Integer> list = new ArrayList<>(); // 점수합을 저장하는 리스트
            for (int i = 0; i < (1 << N); i++) {
                int sump = 0, sumk = 0;
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) > 0) {
                        sump += point[j]; // 점수 합하기
                        sumk += kcal[j]; // 칼로리 합하기
                    }
                }
                if (sumk <= L) {
                    list.add(sump); // 제한 칼로리를 넘지 않으면 리스트에 저장
                }
            }
            Answer = Collections.max(list); // 리스트에서 최대값 구하기
            System.out.printf("#%d %d\n", test_case, Answer);
        }

    }
}
