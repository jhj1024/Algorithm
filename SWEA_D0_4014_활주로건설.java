package com.ssafy.d1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D0_4014_활주로건설
 * @date 2020.10.28
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&categoryId=AWIeW7FakkUDFAVH&categoryType=CODE
 * 
 * [입력사항] 테스트 케이스의 개수 T / 지도의 한 변의 크기인 N 과 경사로의 길이 X / N * N 크기의 지형 정보
 * [출력사항] 활주로를 건설할 수 있는 경우의 수
 * 
 * 경사로는 높이 차이가 1 이고 낮은 지형의 높이가 동일하게 X만큼 연속되는 곳에 설치 할 수 있다
 */

public class SWEA_D0_4014_활주로건설 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, X, Answer;
    static int[][] map;


    public static void main(String[] args) throws Exception {
        // 테스트 케이스
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());
            X = Integer.parseInt(tokens.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            // 알고리즘
            Answer = 0;
            for (int i = 0; i < N; i++) {
                runway(i);
            }

            // 결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        // 출력
        System.out.println(output);
    }

    public static void runway(int i) {
        // 가로모드(행)
        int j = 0, cmp = map[i][0], cnt = 1, current = 0; // 1 증가 -1 감소
        boolean up = false, down = false;
        for (j = 1; j < N; j++) {
            if (cmp == map[i][j]) { // 높이유지
                cnt++;
                current = 0;
            } else if (Math.abs(cmp - map[i][j]) == 1) { // 높이가 1 차이
                if (cmp < map[i][j]) { // 증가
                    // cnt 개수가 X 미만이면 이건 만들 수 없는 활주로
                    if (cnt < X) {
                        break;
                    }

                    if (down == true) {
                        break; // 감소했다가 증가했다는거 > 분지 > 예외
                    }

                    up = true;
                    current = 1;

                } else { // 감소
                    if (up == false && down == true) {
                        // cnt 개수가 X 미만이면 이건 만들 수 없는 활주로
                        if (cnt < X) {
                            break;
                        }
                    }
                    down = true;
                    current = -1;
                }

                cnt = 1;
                cmp = map[i][j];

            } else { // 높이가 1 이상 차이나는건 만들 수 없는 활주로
                break;
            }
        }

        if (j == N) {
            System.out.println(i);

            if (current == 0 && cnt >= X) {
                Answer++;
            } else if (current == 1) {
                Answer++;
            }
        }


        // 세로모드(열)
        j = 0;
        cmp = map[0][i];
        cnt = 1;
        current = 0; // 1 증가 -1 감소
        up = false;
        down = false;
        for (j = 1; j < N; j++) {
            if (cmp == map[j][i]) { // 높이유지
                cnt++;
                current = 0;
            } else if (Math.abs(cmp - map[j][i]) == 1) { // 높이가 1 차이
                if (cmp < map[j][i]) { // 증가

                    // cnt 개수가 X 미만이면 이건 만들 수 없는 활주로
                    if (cnt < X) {
                        break;
                    }

                    if (down == true) {
                        break; // 감소했다가 증가했다는거 > 분지 > 예외
                    }

                    up = true;
                    current = 1;
                } else { // 감소
                    if (up == false && down != false) {
                        // cnt 개수가 X 미만이면 이건 만들 수 없는 활주로
                        if (cnt < X) {
                            break;
                        }
                    }
                    down = true;
                    current = -1;
                }

                cnt = 1;
                cmp = map[j][i];

            } else { // 높이가 1 이상 차이나는건 만들 수 없는 활주로
                break;
            }
        }

        if (j == N) {
            System.out.println(i);
            if (current == 0 && cnt >= X) {
                Answer++;
            } else if (current == 1 || current == 0) {
                Answer++;
            }
        }

    }

}
