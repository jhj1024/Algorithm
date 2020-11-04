package com.ssafy.algo.d1104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JUNG
 * @name BOJ_G4_2239_스도쿠
 * @date 2020.11.04
 * @link https://www.acmicpc.net/problem/2239
 * 
 * [입력사항] 9*9 크기의 보드 정보
 * [출력사항] 9개의 줄에 9개의 숫자로 답을 출력(답이 여러 개 있다면 그 중 사전식으로 앞서는 것을 출력)
 */

public class BOJ_G4_2239_스도쿠 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static int N = 9;
    static int[][] map;
    static List<Point> blanks;

    public static void main(String[] args) throws Exception {
        // 입력
        blanks = new ArrayList<Point>();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = input.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
                if (map[i][j] == 0) {
                    blanks.add(new Point(i, j));
                }
            }
        }

        // 알고리즘
        dfs(0);
    }

    public static void dfs(int idx) {
        if (idx == blanks.size()) { // 끝까지 도달?
            // 출력
            for (int[] m : map) {
                for (int n : m) {
                    output.append(n);
                }
                output.append("\n");
            }
            System.out.println(output);

            // 바로 시스템 종료
            System.exit(0);
        }

        for (int num = 1; num <= 9; num++) {
            if (check(idx, num)) {
                map[blanks.get(idx).r][blanks.get(idx).c] = num; // 넣었다가
                dfs(idx + 1); // 탐색
                map[blanks.get(idx).r][blanks.get(idx).c] = 0; // 빼기
            }
        }
    }

    public static boolean check(int idx, int number) {
        int r = blanks.get(idx).r;
        int c = blanks.get(idx).c;

        // 같은 행, 열에서 겹치는 숫자라면 탈출해
        for (int i = 0; i < N; i++) {
            if (map[r][i] == number || map[i][c] == number) {
                return false;
            }
        }

        // 3X3 보드 안에서 겹치는 숫자라면 탈출해
        int dr = (r / 3) * 3;
        int dc = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[dr + i][dc + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
