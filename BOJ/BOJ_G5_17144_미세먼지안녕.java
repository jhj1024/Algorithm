package com.ssafy.algo.d0902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name BOJ_G5_17144_미세먼지안녕
 * @date 2020.09.02
 * @link https://www.acmicpc.net/problem/17144
 * @mem
 * @time
 * @caution
 * [고려사항]
 * [입력사항] 집의 크기 R, C / 시간 T / R, C 크기의 방 정보(-1은 공기청정기가 설치된 곳)
 * [출력사항] T초가 지난 후 방에 남아있는 미세먼지의 양
 * 
 * 1초동안 일어나는 일
 * 
 * 1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
 * (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
 * 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
 * 확산되는 양은 Ar,c/5이고 소수점은 버린다.
 * (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
 * 
 * 2. 공기청정기가 작동한다.
 * 공기청정기에서는 바람이 나온다.
 * 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
 * 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
 * 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다
 * 
 * 1초 동안 미세먼지 확산이 동시에 일어나기 때문에 작업결과는 dusts에 저장하고 나중에 합산을 map에 넣어주는 방식
 * 
 */

public class BOJ_G5_17144_미세먼지안녕 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, T, Answer;
    static int[][] map;
    static int purifier1 = -1, purifier2 = -1; //공청기는 무조건 0열에 설치되어있음
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //입력
        tokens = new StringTokenizer(input.readLine(), " ");
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == -1 && purifier1 < 0) { //공기청정기면
                    purifier1 = i;
                    purifier2 = i + 1;
                }   
            }
        }
        
        //알고리즘
        map[purifier1][0] = 0;
        map[purifier2][0] = 0;
        
        for (int i = 0; i < T; i++) { //T초동안 반복
            moveDust(); //1. 미세먼지 확산 
            operatePurifier(); //2. 공기청정기 작동 
        }
        
        Answer = 0;       
        for(int[] m : map) {
            for(int d : m) {
                Answer += d;
            }
        }
        System.out.println(Answer);
        
    }

    public static void moveDust() {
        int[][] temp = new int[R][C];
        
        //일단 맵에 있는 미세먼지를 찾아 확산시키고, 새로운 맵에 저장
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) { //미세먼지                    
                    int cnt = 0;

                    if (map[i][j] < 5) {
                        temp[i][j] += map[i][j]; //확산 못하니까 그대로 저장
                        continue;
                    }

                    for (int d = 0; d < dirs.length; d++) { //네방향 확산
                        int dr = i + dirs[d][0];
                        int dc = j + dirs[d][1];

                        if (!isIn(dr, dc) || (dr == purifier1 && dc == 0) || (dr == purifier2 && dc == 0)) {
                            continue;
                        }

                        //구역 안에 있고 공청기 위치가 아니면
                        temp[dr][dc] += map[i][j]/5;
                        cnt++;

                    }
                    temp[i][j] += map[i][j] - (map[i][j]/5 * cnt); ////확산되고 남은 녀석 저장
                }
            }
        }
        
        for(int i = 0; i < R; i++) { //맵 업데이트
            map[i] = Arrays.copyOf(temp[i], C);
        }
    }

    public static void operatePurifier() {
        //purifier1은 반시계방향
        
        // 맨 왼쪽 아래로 이동
        for (int r = purifier1 - 1; r >= 0; r--) {
            map[r + 1][0] = map[r][0];
        }
        
        map[purifier1][0] = 0;        
        
        // 맨 처음 줄 왼쪽으로
        for (int c = 1; c < C; c++) {
            map[0][c - 1] = map[0][c];
        }

        // 맨 오른쪽 위로 이동
        for (int r = 1; r <= purifier1; r++) {
            map[r - 1][C-1] = map[r][C-1];
        }

        // 맨아래를 오른쪽으로
        for (int c = C - 2; c >= 0; c--) {
            map[purifier1][c + 1] = map[purifier1][c];
        }

        //purifier2는 시계방향        
        // 왼쪽 위로 이동
        for (int r = purifier2+1; r < R; r++) {
            map[r - 1][0] = map[r][0];
        }   
        
        map[purifier2][0] = 0;
        
        // 맨아래를 왼쪽으로
        for (int c = 1; c < C; c++) {
            map[R-1][c - 1] = map[R-1][c];
        }
        
        // 맨 오른쪽 아래로 이동
        for (int r = R - 2; r >= purifier2; r--) {
            map[r + 1][C-1] = map[r][C-1];
        }
        
        // 맨 처음 줄 오른쪽으로
        for (int c = C - 2; c >= 0; c--) {
            map[purifier2][c + 1] = map[purifier2][c];
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }

    static String src =
                "7 8 1\r\n" + 
                "0 0 0 0 0 0 0 9\r\n" + 
                "0 0 0 0 3 0 0 8\r\n" + 
                "-1 0 5 0 0 0 22 0\r\n" + 
                "-1 8 0 0 0 0 0 0\r\n" + 
                "0 0 0 0 0 10 43 0\r\n" + 
                "0 0 5 0 15 0 0 0\r\n" + 
                "0 0 40 0 0 0 20 0"; //188

//                "7 8 2\r\n" + 
//                "0 0 0 0 0 0 0 9\r\n" + 
//                "0 0 0 0 3 0 0 8\r\n" + 
//                "-1 0 5 0 0 0 22 0\r\n" + 
//                "-1 8 0 0 0 0 0 0\r\n" + 
//                "0 0 0 0 0 10 43 0\r\n" + 
//                "0 0 5 0 15 0 0 0\r\n" + 
//                "0 0 40 0 0 0 20 0"; //188

//                "7 8 3\r\n" + 
//                "0 0 0 0 0 0 0 9\r\n" + 
//                "0 0 0 0 3 0 0 8\r\n" + 
//                "-1 0 5 0 0 0 22 0\r\n" + 
//                "-1 8 0 0 0 0 0 0\r\n" + 
//                "0 0 0 0 0 10 43 0\r\n" + 
//                "0 0 5 0 15 0 0 0\r\n" + 
//                "0 0 40 0 0 0 20 0"; //186

//                "7 8 4\r\n" + 
//                "0 0 0 0 0 0 0 9\r\n" + 
//                "0 0 0 0 3 0 0 8\r\n" + 
//                "-1 0 5 0 0 0 22 0\r\n" + 
//                "-1 8 0 0 0 0 0 0\r\n" + 
//                "0 0 0 0 0 10 43 0\r\n" + 
//                "0 0 5 0 15 0 0 0\r\n" + 
//                "0 0 40 0 0 0 20 0"; //178

//                "7 8 5\r\n" + 
//                "0 0 0 0 0 0 0 9\r\n" + 
//                "0 0 0 0 3 0 0 8\r\n" + 
//                "-1 0 5 0 0 0 22 0\r\n" + 
//                "-1 8 0 0 0 0 0 0\r\n" + 
//                "0 0 0 0 0 10 43 0\r\n" + 
//                "0 0 5 0 15 0 0 0\r\n" + 
//                "0 0 40 0 0 0 20 0"; //172

//                "7 8 20\r\n" + 
//                "0 0 0 0 0 0 0 9\r\n" + 
//                "0 0 0 0 3 0 0 8\r\n" + 
//                "-1 0 5 0 0 0 22 0\r\n" + 
//                "-1 8 0 0 0 0 0 0\r\n" + 
//                "0 0 0 0 0 10 43 0\r\n" + 
//                "0 0 5 0 15 0 0 0\r\n" + 
//                "0 0 40 0 0 0 20 0"; //71

//                "7 8 30\r\n" + 
//                "0 0 0 0 0 0 0 9\r\n" + 
//                "0 0 0 0 3 0 0 8\r\n" + 
//                "-1 0 5 0 0 0 22 0\r\n" + 
//                "-1 8 0 0 0 0 0 0\r\n" + 
//                "0 0 0 0 0 10 43 0\r\n" + 
//                "0 0 5 0 15 0 0 0\r\n" + 
//                "0 0 40 0 0 0 20 0"; //52

//                "7 8 50\r\n" + 
//                "0 0 0 0 0 0 0 9\r\n" + 
//                "0 0 0 0 3 0 0 8\r\n" + 
//                "-1 0 5 0 0 0 22 0\r\n" + 
//                "-1 8 0 0 0 0 0 0\r\n" + 
//                "0 0 0 0 0 10 43 0\r\n" + 
//                "0 0 5 0 15 0 0 0\r\n" + 
//                "0 0 40 0 0 0 20 0"; //46

}

